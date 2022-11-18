package com.springboot.Mall.service;

import com.springboot.Mall.entity.User;
import com.springboot.Mall.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public void createUser(String userId, String pw, String nickname){
        User user = new User();
        user.setUserId(userId);
        user.setPw(passwordEncoder.encode(pw));
        user.setNickname(nickname);
        user.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss")));
        userRepository.save(user);
    }

    public User getUser(Long uId) throws Exception {
        Optional<User> user = this.userRepository.findById(uId);
        if(user.isPresent()){
            return user.get();
        }else {
            throw new Exception();
        }
    }

    public void deleteUser(User user){
        this.userRepository.delete(user);
    }

    public void modifyUser(User user, String pw, String nickname){
        if(pw != null){
            user.setPw(passwordEncoder.encode(pw));
        }
        user.setNickname(nickname);
        this.userRepository.save(user);
    }

}
