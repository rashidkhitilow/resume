/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.bean.User;
import com.company.dao.inter.UserDaoInter;
import java.util.List;

/**
 *
 * @author rashid.khitilov
 */
public class Main {

    public static void main(String[] args) throws Exception {
        
//        UserDaoInter userDao = new UserDaoImpl();//thightly coupling - klas direkt diger klasla baglidir.   
        UserDaoInter userDao = Context.instanceUserDao();//bu klass dao impl i gormur cunki atdiq Context de ki bu menim mysql le isleyeceyim UserDaoImpl() dir 
//        sabah Oracl istifade etsem bir bunu deyisecem UserDaoImpl2 qoyacam birde proyektde coxlu deyisikliye ehtiyac olmayacaq 
//        implementasiya ile interface i ayirdiq buda loosly coupling(ic ice kecmir iki obyekt bir birinden asli deyil) adlanir
        List<User> list = userDao.getAll();
        System.out.println("list = " + list);
        System.out.println("-------------------------------");

        User u = userDao.getById(2);
        u.setName("JOSHGUN");
        userDao.updateUser(u);
        List<User> list2 = userDao.getAll();
        System.out.println("list2=" + list2);
    }
}
