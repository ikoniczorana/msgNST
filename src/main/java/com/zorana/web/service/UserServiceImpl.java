package com.zorana.web.service;

import com.zorana.web.entity.User;
import com.zorana.web.repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository repository;

    public UserServiceImpl() {

    }

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        super();
        this.repository = repository;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<User>();
        repository.findAll().forEach(e -> list.add(e));
        
        System.out.println(list.get(0));
        return list;
    }

    @Override
    public boolean saveUser(User user) {
        try {
            repository.save(user);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

}
