package com.example.serviceFakeNews.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ModelDto {
    String site;


    Long view;
    Long rating;


    Long countComments = 0L;
    Long uniqUser = 0L;

    Long rateAuthorsComments = 0L;
    Long cdAnswer = 0L;
    Long dateAgo = 0L;
    Long numberRound = 0L;


}
