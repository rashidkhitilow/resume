/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.dao.inter;

import com.company.entity.EmploymentHistory;
import java.util.List;

/**
 *
 * @author rashid.khitilov
 */
public interface EmploymentHistoryDaoInter {
    public List<EmploymentHistory> getAllEmploymentHistoryById(int userId);
}   
