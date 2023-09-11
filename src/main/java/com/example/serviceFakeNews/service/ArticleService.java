package com.example.serviceFakeNews.service;

import com.example.serviceFakeNews.dto.*;
import com.example.serviceFakeNews.entity.ArticleEntity;
import com.example.serviceFakeNews.entity.CommentEntity;
import com.example.serviceFakeNews.exception.NotFoundException;
import com.example.serviceFakeNews.repository.ArticleRepo;
import com.example.serviceFakeNews.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepo articleRepo;

    @Autowired
    private CommentService commentService;

    @Autowired
    private AuthService authService;


    public Model getPage(String site, Model model) throws NotFoundException {
        Optional<ArticleEntity> page = Optional.ofNullable(articleRepo.findByArticle(site));
        if (page.isEmpty())
            throw new NotFoundException("Not article");


        List<CommentEntity> comments = commentService.getComments(page.get().getId());

        model.addAttribute("page", page.get());
        model.addAttribute("comments", comments);
        model.addAttribute("metrics", ModelDtoMapper.toModelDto(page.get(), comments));


        return model;
    }


    public Model getIndex(Model model, String page, String countOnPage, String query, String schema) {
        int count = (int) articleRepo.count();
        System.out.println(count);
        PaginationUtil.Pagination pagination =
                PaginationUtil.pagination(count, page, countOnPage, PaginationUtil.createQuery(schema, query));
        Pageable of = PageRequest.of(pagination.getCurrPage() - 1, pagination.getCurrPage());

        model.addAttribute("pagination", pagination);

        if (!schema.isEmpty())
            model.addAttribute("articlesClustering",
                    SiteClusteringDtoMapper.articlesToSites(getArticleWithClustering(schema, pagination.getCurrPage(), of)));
        else
            model.addAttribute("articles",
                    articleRepo.findAll(of).stream().map(SiteDtoMapper::toSiteDto).toList());
        model.addAttribute("auth", authService);

        return model;
    }

    public List<ModelDto> getData() {
        List<ModelDto> list =
                articleRepo.findAll().stream().map(entity ->
                        ModelDtoMapper.toModelDto(entity, commentService.getComments(entity.getId())))
                        .collect(Collectors.toList());

        return list;
    }


    public List<ArticleWithSchema> getArticleWithClustering(String schema) {
        return articleRepo.findBySchema(schema);
    }

    public List<ArticleWithSchema> getArticleWithClustering(String schema, Integer page, Pageable of) {
        return articleRepo.findBySchemaWithPageable(of, schema);
    }

}
