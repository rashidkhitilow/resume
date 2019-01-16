/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.bean.User;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rashid.khitilov
 */
public class UserDaoImpl extends AbstractDAO implements UserDaoInter {

    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from users");
            ResultSet r = stmt.getResultSet();

            while (r.next()) {//result sql in neticesidi//next varmi sual verir.//kec novbeti setre
                int id = r.getInt("id");
                String name = r.getString("name");
                String surname = r.getString("surname");
                String email = r.getString("email");
                String phone = r.getString("phone");
                result.add(new User(id, name, surname, phone, email));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public User getById(int userId) {
        User result = null;
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("select * from users where id="+userId);
            ResultSet r = stmt.getResultSet();

            while (r.next()) {//result sql in neticesidi//next varmi sual verir.//kec novbeti setre
                int id = r.getInt("id");
                String name = r.getString("name");
                String surname = r.getString("surname");
                String email = r.getString("email");
                String phone = r.getString("phone");
                result = new User(id, name, surname, phone, email);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
//            Statement stmt = c.createStatement();//SQL injection olur bele sorgu yazdiqda ona gore prepared stmt yaziriq
            PreparedStatement stmt = c.prepareStatement("update users set name =?,surname=?,phone=?,email=? where id =? ");//bu encode edir \ qoyurs
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            stmt.setInt(5, u.getId());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean removeUser(int id) {
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            return stmt.execute("delete from users where id =" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}
