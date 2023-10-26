package com.example.serviceFakeNews.dto;

import java.util.ArrayList;
import java.util.List;

public class SiteClusteringDtoMapper {


    static SiteDto articleToSite(ArticleWithSchema article) {
        SiteDto siteDto = new SiteDto();

        siteDto.setSite(article.getArticle());
        siteDto.setTitle(article.getTitle());
        siteDto.setDescription(article.getDescription());
        siteDto.setImage(article.getImage());

        return siteDto;
    }

    public static List<SiteClusteringDto> articlesToSites(List<ArticleWithSchema> articles) {

        if (articles.isEmpty())
            return new ArrayList<>();

        List<SiteClusteringDto> siteClusteringDtos = new ArrayList<>();
        String cluster = "";

        for (ArticleWithSchema article : articles) {
            if (!article.getCluster().equals(cluster)) {
                SiteClusteringDto dto = new SiteClusteringDto(cluster);
                siteClusteringDtos.add(dto);
                cluster = article.getCluster();
            }
            siteClusteringDtos.get(Math.max(siteClusteringDtos.size() - 1, 0))
                    .getDto().add(articleToSite(article));
        }

        return siteClusteringDtos;

    }


}
