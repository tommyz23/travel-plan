package com.example.controller;

import com.example.service.PlanUserService;
import com.example.utils.response.RespCode;
import com.example.utils.response.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/planUser")
public class PlanUserController {
    private final PlanUserService planUserService;

    @Autowired
    public PlanUserController(PlanUserService pu){
        this.planUserService = pu;
    }

    @RequestMapping("/addUser")
    public RespEntity addUser(HttpServletRequest request){
        String uId = request.getParameter("userId");
        String pId = request.getParameter("planId");
        if (uId == null || pId == null)
            return new RespEntity(RespCode.WRONGPARAM);
        int userId = Integer.parseInt(uId);
        int planId = Integer.parseInt(pId);
        return planUserService.addUser(planId, userId);
    }

    @RequestMapping("/delUser")
    public RespEntity delUser(HttpServletRequest request){
        String uId = request.getParameter("userId");
        String pId = request.getParameter("planId");
        if (uId == null || pId == null)
            return new RespEntity(RespCode.WRONGPARAM);
        int userId = Integer.parseInt(uId);
        int planId = Integer.parseInt(pId);
        return planUserService.delUser(planId, userId);
    }

    @RequestMapping("/modRole")
    public RespEntity modRole(HttpServletRequest request){
        String oldId = request.getParameter("oldAdmin");
        String newId = request.getParameter("newAdmin");
        String pId = request.getParameter("planId");
        if (oldId == null || newId == null || pId == null)
            return new RespEntity(RespCode.WRONGPARAM);
        int planId = Integer.parseInt(pId);
        int oldAsmin = Integer.parseInt(oldId);
        int newAdmin = Integer.parseInt(newId);
        return planUserService.modRole(planId, oldAsmin, newAdmin);
    }

    @RequestMapping("/getPlans")
    public RespEntity getPlans(HttpServletRequest request){
        String uId = request.getParameter("userId");
        if (uId == null)
            return new RespEntity(RespCode.WRONGPARAM);
        int userId = Integer.parseInt(uId);
        return planUserService.getPlans(userId);
    }

    @RequestMapping("/getMyPlan")
    public RespEntity getMyPlan(HttpServletRequest request){
        String uId = request.getParameter("userId");
        if (uId == null)
            return new RespEntity(RespCode.WRONGPARAM);
        int userId = Integer.parseInt(uId);
        return planUserService.getMyPlan(userId);
    }
}
