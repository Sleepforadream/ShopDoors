package com.shopdoors.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProfileDto {
    private MultipartFile img;
    private String nickName;
    private String firstName;
    private String secondName;
    private String thirdName;
    private String gender;
    private String birthDate;
    private String info;
    private String address;
    private String imgProfileName;
}
