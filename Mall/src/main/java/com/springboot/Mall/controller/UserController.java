package com.springboot.Mall.controller;

import com.springboot.Mall.dto.UserCreateDto;
import com.springboot.Mall.dto.UserModifyDto;
import com.springboot.Mall.entity.User;
import com.springboot.Mall.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signup(UserCreateDto userCreateDto){
        return "user/signup";
    }

    @PostMapping("/signup")
    public String signup(UserCreateDto userCreateDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "user/signup-form";
        }
        if(!userCreateDto.getPw1().equals(userCreateDto.getPw2())){
            bindingResult.rejectValue("pw2", "passwordInCorrect", "패스워드 불일치");

            return "user/signup_form";
        }

        try {
            this.userService.createUser(userCreateDto.getId(), userCreateDto.getPw1(), userCreateDto.getNickname());
        }catch (DataIntegrityViolationException e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미등록된사용자");
        }catch (Exception e){
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            System.out.println(e.getMessage());
            return "user/signup_form";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login(){
        return "user/login_form";
    }

    @PreAuthorize(("isAuthenticated()"))
    @GetMapping("/modify")
    public String userModify(UserModifyDto userModifyDto, Principal principal) throws Exception {
        User user = this.userService.getUser(Long.valueOf(principal.getName()));

        userModifyDto.setId(user.getUId());
        userModifyDto.setNickname(user.getNickname());

        return "user/modify";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/modify")
    public String userModify(UserModifyDto userModifyDto, Principal principal, BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            return "redirect:/";
        }
        User user = this.userService.getUser(Long.valueOf(principal.getName()));
        if(userModifyDto.getAfterPw().isEmpty()){
            this.userService.modifyUser(user,null, userModifyDto.getNickname());
        }else {
            this.userService.modifyUser(user, userModifyDto.getAfterPw(), userModifyDto.getNickname());
        }

        return "redirect:/logout";
    }
}
