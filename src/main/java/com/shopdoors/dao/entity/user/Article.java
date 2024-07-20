package com.shopdoors.dao.entity.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "articles")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 300, nullable = false)
    private String title;

    @Column(length = 100, nullable = false)
    private String intro;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(length = 100, nullable = false)
    private String heading;

    @Column(nullable = false)
    private LocalDateTime date = LocalDateTime.now();

    @Column(length = 300)
    private String imgPath;

    @Column(length = 300, nullable = false)
    private String author;

    @Column(length = 100)
    private String privacy;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", intro='" + intro + '\'' +
                ", text='" + text + '\'' +
                ", heading='" + heading + '\'' +
                ", date=" + date +
                ", imgPath='" + imgPath + '\'' +
                ", author='" + author + '\'' +
                ", privacy='" + privacy + '\'' +
                '}';
    }
}