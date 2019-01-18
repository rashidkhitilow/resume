/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author rashid.khitilov
 */
public abstract class AbstractDAO {//DAO Data Access Object
        public  Connection connect() throws Exception {//JDBC java database connectivity//JDBC API specification
//        Class.forName("com.mysql.jdbc.Driver");//bu klasi load ele deyende bu klasin static bloku ise dusur 
        //ve bu klasin obyektini driver arrayina push edir, obyekt ozu ozunu yaradir ve push edir arraya
//        com.mysql.jdbc.Driver s;
        String url = "jdbc:mysql://localhost:3306/resume";
        String username = "root";
        String password = "";
        Connection c = DriverManager.getConnection(url, username, password);
        return c;
    }
}
