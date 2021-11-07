package com.example.probafront.salom;

import com.example.probafront.entity.Attachment;
import com.example.probafront.entity.AttachmentContent;
import com.example.probafront.entity.Post;
import com.example.probafront.repository.AttachmentContentRepository;
import com.example.probafront.repository.AttachmentRepository;
import com.example.probafront.repository.PostRepository;
import javassist.bytecode.ByteArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.util.Optional;
import java.util.UUID;


@RestController
public class Salom  {

    @Autowired
    PostRepository postRepository;
    @Autowired
    AttachmentRepository attachmentRepository;
    @Autowired
    AttachmentContentRepository attachmentContentRepository;

    @GetMapping("/api/get/{uuid}")
    public ResponseEntity<?> salom(@PathVariable UUID uuid, HttpServletResponse response) throws IOException {
     // UUID uuid= UUID.fromString("801e2e04-d323-4c15-8bcb-5136a2f6eb44");
        Optional<Attachment> byId = attachmentRepository.findById(uuid);
        if(byId.isPresent()){
            Optional<AttachmentContent> byAttachmentId = attachmentContentRepository.findByAttachmentId(byId.get().getId());
            if (byAttachmentId.isPresent()){

//              AttachmentContent attachmentContent= byAttachmentId.get();
//              response.setContentType(byId.get().getContent_type());
//              response.getOutputStream().write(attachmentContent.getBytes());
//              response.getOutputStream().close();
//                ModelAndView modelAndView=new ModelAndView();
//                Post post=new Post(1,"ioi","kjh");
//                modelAndView.addObject("kkk",post);
//                modelAndView.addObject("klk",response);

                //String base=new String(encode,"UTF-8");
              return ResponseEntity.ok("encode");


        }else {
               return  ResponseEntity.ok("YO'QQQ");
            }

        }else {
            return ResponseEntity.ok("JHG");
        }

    }

    @PostMapping("/api/post")
    public String post(@RequestBody Post post){
        if(postRepository.existsByPosttNotAndWhat(post.getPostt(),post.getWhat())){
            return "Bor";
        }else {
            return "Yo'q";
        }

    }
    @DeleteMapping("/api/delete")
    public String delete(){
        postRepository.deleteAll();
        return "O'chirildi";
    }
    @DeleteMapping("/api/delete/{id}")
    public String deleteOne(@PathVariable Integer id){
        postRepository.deleteById(id);
        return "O'chirildi";
    }
}
