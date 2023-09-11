package com.example.serviceFakeNews.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SiteClusteringDto {

    public SiteClusteringDto(String cluster) {
        this.cluster = cluster;
        dto = new ArrayList<>();
    }

    List<SiteDto> dto;
    String cluster;
}
