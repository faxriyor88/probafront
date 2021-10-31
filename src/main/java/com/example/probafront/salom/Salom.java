package com.example.probafront.salom;

import com.example.probafront.entity.Post;
import com.example.probafront.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class Salom {

    @Autowired
    PostRepository postRepository;
    @GetMapping("/api/get")
    public List<Post> salom(){
        return postRepository.findAll();
    }

    @PostMapping("/api/post")
    public String post(@RequestBody Post post){
        postRepository.save(post);
        return "Saqlandi";
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
