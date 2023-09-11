package com.example.serviceFakeNews.service;

import com.example.serviceFakeNews.entity.CommentEntity;
import com.example.serviceFakeNews.repository.CommentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepo repo;


    public List<CommentEntity> getComments(Long id) {
        List<CommentEntity> comments = repo.findByIdArticle(id);


        return comments;
    }


}
