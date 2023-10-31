package com.kubudirira.jpaonetomany.service;

import com.kubudirira.jpaonetomany.model.Post;
import com.kubudirira.jpaonetomany.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    public User save(User user) ;
    public List<User> saveAll(List<User> userList);
    public Optional<User> findById(int id);
    public List<User> findAll();
    public User update(int id, User user) ;
    public void delete(User user) ;
    void deleteById(int id);
}
