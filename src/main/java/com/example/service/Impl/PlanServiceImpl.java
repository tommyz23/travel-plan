package com.example.service.Impl;

import com.example.dao.PlanDao;
import com.example.service.PlanService;
import com.example.utils.response.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PlanServiceImpl implements PlanService {
    private final PlanDao planDao;

    @Autowired
    public PlanServiceImpl(PlanDao planDao){
        this.planDao = planDao;
    }

    @Override
    public RespEntity getPlan(String id) {
        return planDao.getPlan(id);
    }

    @Override
    public RespEntity getPlanList() {
        return planDao.getPlanList();
    }

    @Override
    public RespEntity insert(String userId, String name, String site, String time, int peopleNumber) {
        return planDao.insert(userId, name, site, time, peopleNumber);
    }

    @Override
    public RespEntity update(String userId, String name, String id, String site, String time, int peopleNumber) {
        return planDao.update(userId, id, name, site, time, peopleNumber);
    }

    @Override
    public RespEntity delete(String userId, String id) {
        return planDao.delete(userId, id);
    }
}
