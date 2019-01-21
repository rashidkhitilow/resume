/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.main;

import com.company.dao.inter.EmploymentHistoryDaoInter;

/**
 *
 * @author rashid.khitilov
 */
public class Main {

    public static void main(String[] args) throws Exception {
        
//        UserDaoInter userDao = new UserDaoImpl();//thightly coupling - klas direkt diger klasla baglidir.   
        EmploymentHistoryDaoInter userDao = Context.instanceEmploymentHistoryDao();//bu klass dao impl i gormur cunki atdiq Context de ki bu menim mysql le isleyeceyim UserDaoImpl() dir 
//        sabah Oracl istifade etsem bir bunu deyisecem UserDaoImpl2 qoyacam birde proyektde coxlu deyisikliye ehtiyac olmayacaq 
//        implementasiya ile interface i ayirdiq buda loosly coupling(ic ice kecmir iki obyekt bir birinden asli deyil) adlanir

        System.out.println(userDao.getAllEmploymentHistoryById(1));

    }
}
