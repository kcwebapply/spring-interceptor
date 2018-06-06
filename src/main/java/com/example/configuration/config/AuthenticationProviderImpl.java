package com.example.configuration.config;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {


    @Override
    public Authentication authenticate(Authentication auth) throws AuthenticationException {
        System.out.println("認証開始"+auth.getName()+" パスワード:"+auth.getCredentials()+"権限"+auth.getAuthorities().toString());
        String userName = auth.getName();
        String password = auth.getCredentials().toString();
        boolean authCheck = authCheck(userName,password);
        if(!authCheck){
            throw new AuthenticationServiceException("ユーザー認証失敗");
        }
        return new UsernamePasswordAuthenticationToken(userName,password,auth.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> token) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(token);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    private boolean authCheck(String username, String password) {
        User user1 = new User("USER","USER");
        User admin = new User("ADMIN","ADMIN");
        List<User> userList = Arrays.asList(user1,admin);

        return userList.stream().anyMatch(user -> {
            return user.getUserName().equals(username) && user.getPassword().equals(password);
        });
    }

    private static class User{
        private String name;
        private String password;
        public User(String name,String password){
            this.name = name;
            this.password = password;
        }
        public String getUserName(){
            return this.name;
        }

        public String getPassword(){
            return  this.password;
        }
    }

}
