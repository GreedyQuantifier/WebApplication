package com.example.serviceFakeNews.dto;

import com.example.serviceFakeNews.entity.ArticleEntity;


public class SiteDtoMapper {

    public static SiteDto toSiteDto(ArticleEntity entity) {
        SiteDto siteDto = new SiteDto();
        siteDto.setSite(entity.getArticle());
        siteDto.setImage(entity.getImage());
        siteDto.setTitle(entity.getTitle());
        siteDto.setDescription(entity.getDescription());
        return siteDto;
    }


}

