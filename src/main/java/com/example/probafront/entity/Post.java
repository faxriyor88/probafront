package com.example.probafront.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity

public class Post {
    @Id
    private Integer id;
    private String postt;
    private String what;
}