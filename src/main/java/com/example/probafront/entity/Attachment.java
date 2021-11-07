package com.example.probafront.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Attachment {
    @Id
    @GeneratedValue
    private UUID id;
    private String originalfilename;
    private String content_type;
    private String savefilename;

    public Attachment(String originalfilename, String content_type, String savefilename) {
        this.originalfilename = originalfilename;
        this.content_type = content_type;
        this.savefilename = savefilename;
    }


}

