package com.example.service.Impl;

import com.example.dao.PlanUserDao;
import com.example.entity.Plan;
import com.example.service.PlanUserService;
import com.example.utils.response.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanUserServiceImpl implements PlanUserService {
    private final PlanUserDao planUserDao;

    @Autowired
    public PlanUserServiceImpl(PlanUserDao pu){
        this.planUserDao = pu;
    }
    @Override
    public RespEntity addUser(int planId, int userId) {
        return planUserDao.addUser(planId, userId);
    }

    @Override
    public RespEntity delUser(int planId, int userId) {
        return planUserDao.delUser(planId, userId);
    }

    @Override
    public RespEntity modRole(int planId, int oldAdmin, int newAdmin) {
        return planUserDao.modRole(planId, oldAdmin, newAdmin);
    }

    @Override
    public RespEntity getPlans(int userId) {
        return planUserDao.getPlans(userId);
    }

    @Override
    public RespEntity getMyPlan(int userId) {
        return planUserDao.getMyPlan(userId);
    }
}
