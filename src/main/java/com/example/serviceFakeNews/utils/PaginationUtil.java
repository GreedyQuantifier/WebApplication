package com.example.serviceFakeNews.utils;

import java.util.ArrayList;
import java.util.List;


public class PaginationUtil {
    public static Pagination pagination(Integer countArticle, String page, String countArticleOnPage, String query) {
        return new Pagination(page, countArticleOnPage, countArticle, query);
    }

    public static String createQuery(String schema, String query) {
        StringBuilder stringBuilder = new StringBuilder().append("/?page=%s");

        if (schema != null && !schema.isEmpty())
            stringBuilder.append("&schema=").append(schema);

        if (query != null && !query.isEmpty())
            stringBuilder.append("$query=").append(query);

        return stringBuilder.toString();
    }

    public static class Pagination {

        Integer countPage;
        Integer countArticle;

        List<Integer> viewPages = new ArrayList<>();
        Integer currPage;

        String query;

        public Pagination(String page, String countArticleOnPage, Integer count, String query) {
            System.out.println(count);
            this.countArticle = validateRequest(countArticleOnPage, 20);
            this.countPage = count / this.countArticle + Math.min(count % this.countArticle, 1);
            this.currPage = validateCurrPage(page, 1);
            this.query = query;
            list();
        }

        private void list() {
            int min = Math.max(currPage - 5, 1);
            int max = currPage + 5 > countPage ? countPage : currPage + 5;

            for (int i = min; i <= max; i++) {
                viewPages.add(i);
            }
        }

        Integer validateCurrPage(String value, Integer defaultValue) {
            Integer number = validateRequest(value, defaultValue);
            return number > 0 && number <= countPage ? number : 1;
        }


        Integer validateRequest(String value, Integer defaultValue) {
            Integer number;
            try {
                number = Integer.parseInt(value);
            } catch (Exception e) {
                number = defaultValue;
            }
            return number;
        }


        public List<Integer> getViewPages() {
            return viewPages;
        }

        public Integer getCurrPage() {
            return currPage;
        }

        public String getQuery() {
            return query;
        }
    }

}
