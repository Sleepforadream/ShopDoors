package com.shopdoors.dao.repository.user;

import com.shopdoors.dao.entity.user.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findAllByOrderByDateDesc();
}