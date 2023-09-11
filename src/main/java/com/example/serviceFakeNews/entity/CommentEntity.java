package com.example.serviceFakeNews.entity;

import lombok.*;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "comments")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq")
    @Column(name = "comment_id")
    Long id;


    @Column(name = "article_id", nullable = false)
    Long idArticle;

    @Column(nullable = false)
    String author;

    Long rate;

    @Type(type = "timestamp")
    Date date;

    @Type(type = "text")
    String text;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CommentEntity that = (CommentEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArticle, author, text);
    }
}
