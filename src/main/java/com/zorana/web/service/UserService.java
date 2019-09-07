/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.zorana.web.service;

import com.zorana.web.entity.User;
import java.util.List;

/**
 *
 * @author Zorana
 */
public interface UserService {

    public List<User> getAllUsers();

    public boolean saveUser(User user);
}
