package com.example.demo;

import com.example.demo.model.Article;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class ArticleSpecification implements Specification<Article> {

    private String category;

    public ArticleSpecification(String category) {
        this.category = category;
    }

    @Override
    public Predicate toPredicate
            (Root<Article> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
        if ( category == null){
            return builder.isTrue(builder.literal(true)); // always return true if there was no filter

        }
        return builder.equal(root.get("category"), this.category); // retrieve articles by category


    }

    //this function is for checking if the article title or main section conatin a sentence or a word
    public static Specification<Article> articleContains(String term) {
        return (artice, cq, cb) -> cb.or(cb.like(artice.get("title"), "%" + term + "%"),cb.like(artice.get("details"),"%" + term + "%"));
    }
}