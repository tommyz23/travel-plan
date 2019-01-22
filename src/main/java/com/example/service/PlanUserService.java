package com.example.service;

import com.example.utils.response.RespEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public interface PlanUserService {
    RespEntity addUser(int planId, int userId);
    RespEntity delUser(int planId, int userId);
    RespEntity modRole(int planId, int oldAdmin, int newAdmin);
    RespEntity getPlans(int userId);
    RespEntity getMyPlan(int userId);

}
