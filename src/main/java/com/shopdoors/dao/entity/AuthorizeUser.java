package com.shopdoors.dao.entity;

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
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class AuthorizeUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate = LocalDate.now();

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(length = 1000, nullable = false, unique = true)
    private String nickName;

    @Column(length = 1000)
    private String firstName;

    @Column(length = 1000)
    private String secondName;

    @Column(length = 1000)
    private String thirdName;

    @Column(length = 25)
    private String gender;

    @Column
    private LocalDate birthDate;

    @Column(unique = true)
    private Integer phoneNumber;

    @Column(length = 1000)
    private String address;

    @Column(length = 1000)
    private String info;

    @Column(length = 300)
    private String imgPath;

    @Override
    public String toString() {
        return email;
    }
}