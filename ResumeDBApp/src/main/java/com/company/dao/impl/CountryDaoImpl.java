/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.CountryDaoInter;
import com.company.entity.Country;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rashid.khitilov
 */
public class CountryDaoImpl extends AbstractDAO implements CountryDaoInter {
    private Country getCountry(ResultSet r) throws Exception {
        int id = r.getInt("id");
        String name = r.getString("name");
        String nationality = r.getString("nationality");
        return new Country(id, name, nationality);
    }
    @Override
    public List<Country> getAll() {
        List<Country> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT * from country");
            ResultSet r = stmt.getResultSet();

            while (r.next()) {//result sql in neticesidi//next varmi sual verir.//kec novbeti setre
                Country u = getCountry(r);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
