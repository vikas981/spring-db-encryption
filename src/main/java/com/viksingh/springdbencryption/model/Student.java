package com.viksingh.springdbencryption.model;

import com.viksingh.springdbencryption.configuration.AESEncryptor;
import lombok.*;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Convert(converter = AESEncryptor.class)
    private String firstName;

    @Convert(converter = AESEncryptor.class)
    private String lastName;

    @Convert(converter = AESEncryptor.class)
    private String mobile;


    @Convert(converter = AESEncryptor.class)
    private String college;


}
