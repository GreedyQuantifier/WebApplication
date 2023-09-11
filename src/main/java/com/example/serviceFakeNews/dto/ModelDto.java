package com.example.serviceFakeNews.dto;

import com.example.serviceFakeNews.entity.CommentEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

@Getter
@Setter
@ToString
public class ModelDto {
    String site;


    Long view;
    Long rating;


    Long countComments = 0l;
    Long uniqUser = 0l;

    Long rateAuthorsComments = 0l;
    Long cdAnswer = 0l;
    Long dateAgo = 0l;
    Long numberRound = 0l;


}
