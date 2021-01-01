package com.example.demo.controller;


import com.example.demo.ArticleSpecification;
import com.example.demo.model.Article;
import com.example.demo.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RequestMapping("/article")
@RestController
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    //retrieving all articles
    @GetMapping
    public List<Article> findAllArticle(){
        return (List<Article>) articleRepository.findAll();
    }

    //retrieving articles based on their category
    @GetMapping("/search")
    public Page<Article> findArticlesByCategory(
            @RequestParam("category") String category, Pageable pageable
    )
    {
        Specification<Article> spec = Specification.where(new ArticleSpecification(category));
        return articleRepository.findAll(spec,pageable);
    }

    //search in articles for desired string
    @GetMapping("/search/keyword")
    public List<Article> findArticleByKeyword(
            @RequestParam("search_term") String term
    ){
        Specification<Article> spec = ArticleSpecification.articleContains(term);
        List<Article> articles = articleRepository.findAll(spec);
        return articles;
    }

    //save new article
    @PostMapping
    public void saveArticle(@Validated @RequestBody Article article){
        articleRepository.save(article);
    }


}
