package com.example.service;

import com.example.utils.response.RespEntity;
import org.springframework.stereotype.Component;

@Component
public interface PlanService {
    RespEntity getPlan(String id);
    RespEntity getPlanList();
    RespEntity insert(String userId, String name, String site, String time, int peopleNumber);
    RespEntity update(String userId, String id, String name, String site, String time, int peopleNumber);
    RespEntity delete(String userId, String id);
}
