package com.geekbrains.geek.market.utils;

import com.geekbrains.geek.market.entities.User;
import lombok.Data;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.PostConstruct;

@Data
public class CurrentUser {

    public User user;

    public String username;
    public Long id;

    public void init(){
        username = user.getUsername();
        id = user.getId();
    }

}
