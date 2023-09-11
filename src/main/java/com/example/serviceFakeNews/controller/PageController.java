package com.example.serviceFakeNews.controller;


import com.example.serviceFakeNews.exception.NotFoundException;
import com.example.serviceFakeNews.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {

    @Autowired
    private ArticleService service;

    @GetMapping("/")
    public String getIndexWithClustering(@RequestParam(name = "schema", required = false, defaultValue = "") String schema,
                                         @RequestParam(name = "query", required = false, defaultValue = "") String query,
                                         @RequestParam(name = "page", required = false, defaultValue = "1") String page,
                                         @RequestParam(name = "page", required = false, defaultValue = "20") String countOnPage,
                                         Model model) {

        service.getIndex(model, page, countOnPage,query, schema);

        return "index";
    }


    @GetMapping("/page/{topic}")
    public String getTopicPage(@PathVariable(name = "topic") String topic,
                               @RequestParam(name = "lan", defaultValue = "ru", required = false) String lan,
                               Model model) throws NotFoundException {

        service.getPage(topic, model);

        return "topic";

    }
}

