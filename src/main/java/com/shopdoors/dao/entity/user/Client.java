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
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate registerDate;

    @Column(length = 100, nullable = false, unique = true)
    private String email;

    @Column(length = 100, unique = true)
    private String contractNumber;

    @Column(length = 1000, nullable = false)
    private String firstName;

    @Column(length = 1000)
    private String secondName;

    @Column(length = 1000)
    private String thirdName;

    @Column(unique = true, length = 12, nullable = false)
    private String phoneNumber;

    @Override
    public String toString() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client that = (Client) o;
        return id.equals(that.id)
                && Objects.equals(registerDate, that.registerDate)
                && email.equals(that.email)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(secondName, that.secondName)
                && Objects.equals(thirdName, that.thirdName)
                && Objects.equals(phoneNumber, that.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                id,
                registerDate,
                email,
                firstName,
                secondName,
                thirdName,
                phoneNumber
        );
    }
}