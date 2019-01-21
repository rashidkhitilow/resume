/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.SkillDaoInter;
import com.company.entity.Skill;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rashid.khitilov
 */
public class SkillDaoImpl extends AbstractDAO implements SkillDaoInter {
    private Skill getSkill(ResultSet r) throws Exception {
        int id = r.getInt("id");
        String name = r.getString("name");
        return new Skill(id, name);
    }
    @Override
    public List<Skill> getAll() {
        List<Skill> result = new ArrayList<>();
        try (Connection c = connect()) {
            Statement stmt = c.createStatement();
            stmt.execute("SELECT * from skill");
            ResultSet r = stmt.getResultSet();

            while (r.next()) {//result sql in neticesidi//next varmi sual verir.//kec novbeti setre
                Skill u = getSkill(r);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }



}
