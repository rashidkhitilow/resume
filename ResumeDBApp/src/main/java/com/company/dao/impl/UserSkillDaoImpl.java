/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.entity.Skill;
import com.company.entity.User;
import com.company.entity.UserSkill;
import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.UserSkillDaoInter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rashid.khitilov
 */
public class UserSkillDaoImpl extends AbstractDAO implements UserSkillDaoInter {

    private UserSkill getUserSkill(ResultSet r) throws Exception {
        int userId = r.getInt("id");
        int skillId = r.getInt("skill_id");
        int power = r.getInt("power");
        String skillName = r.getString("skill_name");
        
        Skill skill = new Skill(userId, skillName);
        User user = new User(userId);
        return new UserSkill(null, user, skill, power);
    }

    @Override
    public List<UserSkill> getAllSkillById(int userId) {
        List<UserSkill> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT"
                    + "	u.*,"
                    + "	us.skill_id,"
                    + "	s.`name` AS skill_name,"
                    + "	us.power "
                    + "FROM"
                    + "	`user_skill` us"
                    + "	LEFT JOIN USER u ON u.id = us.user_id"
                    + "	LEFT JOIN skill s ON us.skill_id = s.id "
                    + "WHERE"
                    + "	us.user_id =?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet r = stmt.getResultSet();

            while (r.next()) {//result sql in neticesidi//next varmi sual verir.//kec novbeti setre
                UserSkill u = getUserSkill(r);
                result.add(u);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
