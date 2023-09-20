package com.feeham.security.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AllControllers {
    @RequestMapping("/")
    public String index() {
        return "This is home page";
    }
    @RequestMapping("/about")
    public String about() {
        return "This is about page";
    }
    @RequestMapping("/contact")
    public String contact() {
        return "This is contact page";
    }
    @RequestMapping("/edit_profile")
    public String editProfile() {
        return "This is edit profile page";
    }
    @RequestMapping("/register")
    public String register() {
        return "This is register page";
    }
    @RequestMapping("/logout")
    public String logout() {
        return "This is logout page";
    }
    @RequestMapping("/profile")
    public String profile() {
        return "This is profile page";
    }
    @RequestMapping("/adminDashboard")
    public String admin() {
        return "This is admin dashboard page";
    }
}
