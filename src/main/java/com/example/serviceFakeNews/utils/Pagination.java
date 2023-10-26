package com.example.serviceFakeNews.utils;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;

public class Pagination {
    int count;
    int articleOnPage;

    @Getter
    int countPage;

    @Getter
    int currPage;

    @Getter
    List<Integer> viewPages = new ArrayList<>();

    @Getter
    String query;

    public Pagination(Long count, String page, String countArticle) {
        this.count = Math.toIntExact(count);

        articleOnPage = validateRequest(countArticle, 1);
        countPage = this.count / articleOnPage;
        currPage = validateCurrentPage(countPage, page);
        list();
    }

    static int validateCurrentPage(int countPage, String value) {
        int number = validateRequest(value, 0);
        return number >= 0 && number <= countPage ? number : 0;
    }

    static int validateRequest(String value, int defaultValue) {
        int number;
        try {
            number = Integer.parseInt(value);
        } catch (Exception e) {
            number = defaultValue;
        }
        return number > 0 ? number : defaultValue;
    }

    public void createQuery(String schema, String query) {
        StringBuilder stringBuilder = new StringBuilder().append("/?page=%s");

        if (schema != null && !schema.isEmpty())
            stringBuilder.append("&schema=").append(schema);

        if (query != null && !query.isEmpty())
            stringBuilder.append("&query=").append(query);

        stringBuilder.append("&count=").append(articleOnPage);


        this.query = stringBuilder.toString();
    }

    private void list() {
        int min = Math.max(currPage - 5, 1);
        int max = Math.min(currPage + 5, countPage);

        for (int i = min; i <= max; i++) {
            viewPages.add(i);
        }
    }

    public Pageable pageable() {
        return PageRequest.of(currPage, articleOnPage);
    }

    public int module(int value, int module) {
        return value < 0 ? module - (value % module) : value % module;
    }

    public int next() {
        currPage = module(++currPage, getCountPage());
        return currPage == 0 ? 1 : currPage;
    }

    public int prev() {
        currPage = module(--currPage, getCountPage());
        return currPage == 0 ? getCountPage() : currPage;
    }
}

