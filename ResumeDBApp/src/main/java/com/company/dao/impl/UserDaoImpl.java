/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Country;
import com.company.entity.User;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserDaoInter;
import java.sql.Connection;
import java.sql.Date;
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

    private User getUser(ResultSet r) throws Exception {
        int id = r.getInt("id");
        String name = r.getString("name");
        String surname = r.getString("surname");
        String email = r.getString("email");
        String phone = r.getString("phone");
        String profileDescription = r.getString("profile_description");
        String address = r.getString("address");
        Date birthdate = r.getDate("birthdate");
        int countryId = r.getInt("birthplace_id");
        int nationalityId = r.getInt("nationality_id");
        String countryNameStr = r.getString("birthplace");
        String nationalitySrt = r.getString("nationality");

        Country birthplace = new Country(countryId, countryNameStr, null);
        Country nationality = new Country(nationalityId, null, nationalitySrt);
        return new User(id, name, surname, phone, email, profileDescription, address, birthdate, birthplace, nationality);
    }
    
    @Override
    public List<User> getAll() {
        List<User> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT"
                    + "	a.*,"
                    + "	c.name as birthplace,"
                    + "	b.nationality "
                    + "FROM"
                    + "	USER a"
                    + "	LEFT JOIN country b ON a.nationality_id = b.id"
                    + "	LEFT JOIN country c ON a.birthplace_id = c.id");
            ResultSet r = stmt.getResultSet();

            while (r.next()) {//result sql in neticesidi//next varmi sual verir.//kec novbeti setre
                User u = getUser(r);
                result.add(u);
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
            stmt.execute("SELECT"
                    + "	a.*,"
                    + "	c.name as birthplace,"
                    + "	b.nationality "
                    + "FROM"
                    + "	USER a"
                    + "	LEFT JOIN country b ON a.nationality_id = b.id"
                    + "	LEFT JOIN country c ON a.birthplace_id = c.id where a.id=" + userId);
            ResultSet r = stmt.getResultSet();

            while (r.next()) {//result sql in neticesidi//next varmi sual verir.//kec novbeti setre
                result = getUser(r);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

    @Override
    public boolean addUser(User u) {
        try (Connection c = connect()) {
//            Statement stmt = c.createStatement();//SQL injection olur bele sorgu yazdiqda ona gore prepared stmt yaziriq
            PreparedStatement stmt = c.prepareStatement("insert into user(name,surname,phone,email) values (?,?,?,?) ");//bu encode edir \ qoyurs
            stmt.setString(1, u.getName());
            stmt.setString(2, u.getSurname());
            stmt.setString(3, u.getPhone());
            stmt.setString(4, u.getEmail());
            return stmt.execute();
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateUser(User u) {
        try (Connection c = connect()) {
//            Statement stmt = c.createStatement();//SQL injection olur bele sorgu yazdiqda ona gore prepared stmt yaziriq
            PreparedStatement stmt = c.prepareStatement("update user set name =?,surname=?,phone=?,email=? where id =? ");//bu encode edir \ qoyurs
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
            return stmt.execute("delete from user where id =" + id);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }


}
