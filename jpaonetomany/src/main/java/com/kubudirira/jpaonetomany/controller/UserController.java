package com.kubudirira.jpaonetomany.controller;



import com.kubudirira.jpaonetomany.model.Post;
import com.kubudirira.jpaonetomany.model.User;
import com.kubudirira.jpaonetomany.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/user")
@Slf4j
public class UserController {


    private final UserService userService;

    @GetMapping("/all")
    public List<User> findAllUsers(){
        return userService.findAll();
    }
    @PostMapping("/save")
    public void save(@RequestBody User user){
        userService.save(user);
    }
    @PostMapping("/assignPost/{user_id}")
    public void assignPassport(@RequestBody Post post, @PathVariable int user_id){

        User user = userService.findById(user_id).get();
        List<Post> posts = user.getPost();
        posts.add(post);
        user.setPost(posts);
        userService.save(user);
    }
    @GetMapping("/delete/{id}")
    public void delete(@PathVariable int id){
        userService.deleteById(id);
    }

    @PostMapping("/updateUser/{user_id}")
    public void updateUser(@RequestBody User user, @PathVariable int user_id) {
        userService.update(user_id,user);
    }

    @PutMapping("/updatePost/{user_id}")
    public String updatePost(@RequestBody Post post, @PathVariable int user_id) {
        //COMPLETE THIS PART
        User user = userService.findById(user_id).get();
        List<Post> posts = user.getPost();
        String s="Post not found with "+ post.getId() + " for this user "+ user.getFirst_name();

        //make a loop for the list to iterate through the values this is to get the index of the
        // of a post and check the id if it matches with the post id from the client
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId() == post.getId()) {
                posts.set(i, post); //update the on index i
                user.setPost(posts); //set the all the posts and the updated post
                userService.save(user); //save the user to the database
                s=post.toString();
                return s + "\nPost updated successfully";
            }
        }

        return s;

    }



}
