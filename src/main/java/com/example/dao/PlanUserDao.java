package com.example.dao;

import com.example.utils.response.RespEntity;
import org.springframework.stereotype.Component;

@Component
public interface PlanUserDao {
    RespEntity addUser(int planId, int userId);
    RespEntity delUser(int planId, int userId);
    RespEntity modRole(int planId, int oldAdmin, int newAdmin);
    RespEntity getPlans(int userId);
    RespEntity getMyPlan(int userId);
}
