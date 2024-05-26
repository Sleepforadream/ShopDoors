package com.shopdoors.dao.repository;

import com.shopdoors.dao.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByDateDesc();
}