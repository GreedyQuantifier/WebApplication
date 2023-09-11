package com.example.serviceFakeNews.entity;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "articles")
public class ArticleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "article_seq")
    @Column(name = "article_id")
    Long id;

    @Column(unique = true, nullable = false)
    String article;

    @Type(type = "text")
    String title;

    @Column(nullable = false)
    @Type(type = "text")
    String body;

    String image;

    String description;

    @JoinColumn(name = "language_id")
    @OneToOne(fetch = FetchType.LAZY)
    @ToString.Exclude
    LanguageEntity language;

    @Type(type = "timestamp")
    Date date;

    Long rate;

    Long view;

    public String getLanguage() {
        return language.getValueLanguage();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ArticleEntity that = (ArticleEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(article, title);
    }
}
