package com.fjl.sbmp.controller;


import com.fjl.sbmp.model.TUser;
import com.fjl.sbmp.service.ITUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 范井龙
 * @since 2019-11-22
 */
@RestController
@RequestMapping("/tUser")
public class TUserController {

    @Autowired
    private ITUserService itUserService;


    @GetMapping
    public List<TUser> find(){
        List<TUser> tUserList = new ArrayList<TUser>();
        try{
            tUserList = itUserService.list();
        }catch (Exception e){
            e.printStackTrace();
        }
        return tUserList;
    }
}
