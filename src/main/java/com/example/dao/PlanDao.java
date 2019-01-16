package com.example.dao;

import com.example.utils.response.RespEntity;
import org.springframework.stereotype.Component;

@Component
public interface PlanDao {
    RespEntity getPlan(String id);
    RespEntity getPlanList();
    RespEntity insert(String userId, String name, String site, String time, int peopleNumber);
    RespEntity update(String userId, String name, String id, String site, String time, int peopleNumber);
    RespEntity delete(String userId, String id);
}
