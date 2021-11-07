package com.example.probafront.salom;

import com.example.probafront.entity.Attachment;

import com.example.probafront.repository.AttachmentContentRepository;
import com.example.probafront.repository.AttachmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.util.Optional;
import java.util.UUID;

@RestController
public class AttachmentController {
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @PostMapping("/attachmentuploadimage")
    public ResponseEntity<?> upload(MultipartFile file/*@RequestParam("file") MultipartFile file,@RequestBody @RequestParam("post") Post post*/) throws IOException {
           if (file != null) {
            String savefilename = UUID.randomUUID().toString();
            String[] split = file.getOriginalFilename().split("\\.");
            savefilename = savefilename + "." + split[split.length - 1];
            Attachment attachment = new Attachment(file.getOriginalFilename(), file.getContentType(),savefilename);
            attachmentRepository.save(attachment);

            Path path= Paths.get("yuklanganlar/"+savefilename);
            Files.copy(file.getInputStream(),path);

            return ResponseEntity.ok("Saqlandi");
        }
        return ResponseEntity.status(404).body("Fayl tanlanmangan");

    }

    @GetMapping("/download/{id}")
    public void getDbId(@PathVariable UUID id, HttpServletResponse response) throws IOException {
        Optional<Attachment> optionalAttachment = attachmentRepository.findById(id);
        if (optionalAttachment.isPresent()) {
            Attachment attachment = optionalAttachment.get();
                response.setHeader("Content-Disposition", "attachment;filename=\"" + attachment.getOriginalfilename() + "\"");
                response.setContentType(attachment.getContent_type());
                FileInputStream fileInputStream=new FileInputStream("yuklanganlar/"+attachment.getSavefilename());
                FileCopyUtils.copy(fileInputStream, response.getOutputStream());
            }

        }


    @GetMapping("/getAll")
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(attachmentRepository.findAll());
    }

    @DeleteMapping("/deleteattach/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        Optional<Attachment> attachment = attachmentRepository.findById(id);
        if (attachment.isPresent()) {
            attachmentRepository.deleteById(id);
            return ResponseEntity.status(201).body("O'chirildi");
        }
        return ResponseEntity.status(404).body("O'chirilmadi");
    }


    //@DeleteMapping("/attachmentdownloadimage")
}
