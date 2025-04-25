package com.jingwon.study.userservice.controller;

import com.jingwon.study.userservice.dto.LocalSignupRequest;
import com.jingwon.study.userservice.dto.OAuthSignupRequest;
import com.jingwon.study.userservice.facade.UserFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserFacade userFacade;

    @PostMapping("/signup/local")
    public ResponseEntity<Void> localSignUp(@RequestBody LocalSignupRequest request){
        userFacade.signupLocalUser(request);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/signup/oauth")
    public ResponseEntity<Void> oauthSignUp(@RequestBody OAuthSignupRequest request){
        userFacade.signupOAuthUser(request);
        return ResponseEntity.ok().build();
    }
}
