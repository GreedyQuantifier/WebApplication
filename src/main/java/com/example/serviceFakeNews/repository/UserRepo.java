package com.example.serviceFakeNews.repository;

import com.example.serviceFakeNews.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<UserEntity,Long> {
    UserEntity findByEmail(String email);
}
