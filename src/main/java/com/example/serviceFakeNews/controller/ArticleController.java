package com.example.serviceFakeNews.controller;

import com.example.serviceFakeNews.service.ArticleService;
import com.example.serviceFakeNews.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity createAuthToken(@RequestParam String name, @RequestParam String pass) {
        return authService.responseEntity(name, pass);
    }

    @GetMapping("/data/")
    public ResponseEntity getData() {
        return ResponseEntity.ok(articleService.getData());
    }

    @GetMapping("/data/{schema}/")
    public ResponseEntity getDataWithSchema(@PathVariable String schema) {
        return ResponseEntity.ok(articleService.getArticleWithClustering(schema));
    }
}