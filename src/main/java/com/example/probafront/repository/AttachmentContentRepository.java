package com.example.probafront.repository;

import com.example.probafront.entity.AttachmentContent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AttachmentContentRepository extends JpaRepository<AttachmentContent, UUID> {

    Optional<AttachmentContent> findByAttachmentId(UUID attachment_id);
}
