/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.impl;

import com.company.dao.inter.AbstractDAO;
import com.company.dao.inter.EmploymentHistoryDaoInter;
import com.company.entity.EmploymentHistory;
import com.company.entity.User;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author rashid.khitilov
 */
public class EmploymentHistoryDaoImpl extends AbstractDAO implements EmploymentHistoryDaoInter {

    private EmploymentHistory getEmploymentHistory(ResultSet r) throws Exception {
        String header = r.getString("header");
        String jobDescription = r.getString("job_description");
        Date beginDate = r.getDate("begin_date");
        Date endDate = r.getDate("end_date");
        int userId = r.getInt("user_id");
        
        EmploymentHistory emp = new EmploymentHistory(null, header, beginDate, endDate, jobDescription, new User(userId));
        return emp;
    }

    @Override
    public List<EmploymentHistory> getAllEmploymentHistoryById(int userId) {
        List<EmploymentHistory> result = new ArrayList<>();
        try (Connection c = connect()) {
            PreparedStatement stmt = c.prepareStatement("SELECT * from employment_history where user_id =?");
            stmt.setInt(1, userId);
            stmt.execute();
            ResultSet r = stmt.getResultSet();

            while (r.next()) {//result sql in neticesidi//next varmi sual verir.//kec novbeti setre
                EmploymentHistory emp = getEmploymentHistory(r);
                result.add(emp);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return result;
    }

}
