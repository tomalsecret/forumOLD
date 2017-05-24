package com.Forum.Service;

import com.Forum.Dao.UserDao;
import com.Forum.Entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * Created by Tomal on 2017-05-13.
 */
@Service
public class UserService {

    @Autowired
    UserDao userDao;

    public void addUser(String username, String password){
        userDao.addUser(username,password);
    }

    public Collection<User> getAllUsers() {
        return this.userDao.getAllUsers();
    }

    public void disableUserByName(String user_name) {
        this.userDao.disableUserByName(user_name);
    }

    public String getUserName(){
        return this.userDao.getUserName();
    }

    public String getUserById(int id) {
        return this.userDao.getUserById(id);
    }


}
