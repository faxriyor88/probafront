package com.example.probafront.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentContent {
    @Id
    @GeneratedValue
    private UUID uuid;
    private byte[] bytes;
    @OnDelete(action = OnDeleteAction.CASCADE)

    @OneToOne
    private Attachment attachment;

    public AttachmentContent(byte[] bytes, Attachment attachment) {
        this.bytes = bytes;
        this.attachment = attachment;
    }

    public AttachmentContent(Attachment attachment) {
        this.attachment = attachment;
    }
}
