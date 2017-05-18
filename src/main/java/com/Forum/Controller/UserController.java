package com.Forum.Controller;

import com.Forum.Entity.User;
import com.Forum.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Created by Tomal on 2017-05-13.
 */
@RequestMapping("/user")
@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUserPanel() {
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(@RequestParam(value = "username", defaultValue = " ") String username,
                          @RequestParam(value = "password", defaultValue = " ") String pass) {


        String redirectUrlError = "/user/register";
        String redirectUrlSuccess = "/user/login";



        Pattern p = Pattern.compile("[a-zA-z0-9]*");
        if (!p.matcher(username).matches()) return "redirect:" + redirectUrlError;
        if (!p.matcher(pass).matches()) return "redirect:" + redirectUrlError;


        userService.addUser(username, pass);
        return "redirect:" + redirectUrlSuccess;

    }


    @RequestMapping(value = {"/login"})
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/admin")
    public String admin() {
        return "admin";
    }


    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        model.addAttribute("allusers", userService.getAllUsers());
        return "listall";
    }

    @RequestMapping(value = "/admin/disable", method = RequestMethod.GET)
    public String disableUser() {
        return "disable_user";
    }

    @RequestMapping(value = "/admin/disable", method = RequestMethod.POST)
    public String disableUserByName(@RequestParam(value = "user_name", defaultValue = "") String user_name) {
        if (!(user_name.equals("admin"))) userService.disableUserByName(user_name);
        return "admin";
    }

    @RequestMapping(value = "/siema", method = RequestMethod.GET)
    public String cypher() {


        return "cipher";


    }

    @RequestMapping(value = "/siemano", method = RequestMethod.GET)
    public String decypher() {


        return "decypher";


    }


    @RequestMapping(value = "/siema", method = RequestMethod.POST)
    public String cypherPOST(@RequestParam(value = "string_to_cypher", defaultValue = " ") String plaintext,
                             @RequestParam(value = "key", defaultValue = "0") String keyString, Model model) {

        int key;
        try {
            key = Integer.parseInt(keyString);
        } catch (Exception e) {
            return "cipher";
        }
        StringBuilder ciphertext = new StringBuilder(plaintext);
        final int DELTA = key;
        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            ciphertext.setCharAt(i, (char) (c + DELTA));
        }


        model.addAttribute("cypher", ciphertext);
        return "cipher";

    }

    @RequestMapping(value = "/siemano", method = RequestMethod.POST)
    public String decypherPOST(@RequestParam(value = "string_to_decypher", defaultValue = " ") String cypheredtext,
                               @RequestParam(value = "key", defaultValue = "0") String keyString, Model model) {

        int key;
        try {
            key = Integer.parseInt(keyString);
        } catch (Exception e) {
            return "cipher";
        }
        StringBuilder deciphertext = new StringBuilder(cypheredtext);
        final int DELTA = key;

        for (int i = 0; i < deciphertext.length(); i++)

        {
            char c = deciphertext.charAt(i);
            deciphertext.setCharAt(i, (char) (c - DELTA));
        }

        model.addAttribute("decypher", deciphertext);
        return "decypher";
    }
}
