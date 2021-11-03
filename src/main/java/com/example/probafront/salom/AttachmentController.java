package com.example.probafront.salom;

import com.example.probafront.entity.Attachment;
import com.example.probafront.entity.AttachmentContent;
import com.example.probafront.repository.AttachmentContentRepository;
import com.example.probafront.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.Optional;
import java.util.UUID;

@RestController
public class AttachmentController {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @PostMapping("/attachmentuploadimage")
    public ResponseEntity<?> upload(MultipartHttpServletRequest request) throws IOException {
        Iterator<String> fileNames = request.getFileNames();
        if (fileNames.hasNext()) {
            MultipartFile file = request.getFile(fileNames.next());
            if (file != null) {
                Attachment attachment = new Attachment(file.getOriginalFilename(), file.getContentType());
                attachmentRepository.save(attachment);
                AttachmentContent attachmentContent = new AttachmentContent(file.getBytes(), attachment);
                attachmentContentRepository.save(attachmentContent);
                return ResponseEntity.ok("Saqlandi");
            }
            return ResponseEntity.status(404).body("Fayl tanlanmangan");

        }
        return ResponseEntity.status(404).body("Fayl tanlanmangan");
    }
    @GetMapping("/download/{id}")
    public void getDbId(@PathVariable UUID id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
            Optional<AttachmentContent> byAttachmentId = attachmentContentRepository.findByAttachmentId(id);
            if (byAttachmentId.isPresent()) {
                response.setHeader("Content-Disposition", "attachment;filename=\"" + attachment.getName() + "\"");
                response.setContentType(attachment.getContent_type());
                FileCopyUtils.copy(byAttachmentId.get().getBytes(), response.getOutputStream());
            }

        }
    }
    @GetMapping("/getAll")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(attachmentRepository.findAll());
    }
    @DeleteMapping("/deleteattach/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
        Optional<Attachment> attachment=attachmentRepository.findById(id);
        if (attachment.isPresent()){
            attachmentRepository.deleteById(id);
            return ResponseEntity.status(201).body("O'chirildi");
        }
        return ResponseEntity.status(404).body("O'chirilmadi");
    }


    //@DeleteMapping("/attachmentdownloadimage")
}
