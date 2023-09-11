package com.example.serviceFakeNews.dto;

import com.example.serviceFakeNews.entity.ArticleEntity;
import com.example.serviceFakeNews.entity.CommentEntity;

import java.util.List;

public class ModelDtoMapper {
    public static ModelDto toModelDto(ArticleEntity entity, List<CommentEntity> list) {
        ModelDto modelDto = new ModelDto();
        modelDto.setSite(entity.getArticle());
        modelDto.setRating(entity.getRate());
        modelDto.setView(entity.getView());

//        modelDto.calcParams(list);

        return modelDto;
    }

}
