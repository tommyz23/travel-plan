package com.example.controller;

import com.example.service.PlanService;
import com.example.utils.response.RespCode;
import com.example.utils.response.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/plan")
public class PlanController {
    private final PlanService planService;

    @Autowired
    public PlanController(PlanService p){
        this.planService = p;
    }

    @RequestMapping("/getPlan")
    public RespEntity getPlan(HttpServletRequest request){
        String id = request.getParameter("id");
        if (id == null)
            return new RespEntity(RespCode.WRONGPARAM);
        return planService.getPlan(id);
    }

    @RequestMapping("/getPlanList")
    public RespEntity getPlanList(){
        return planService.getPlanList();
    }

    @RequestMapping("/update")
    public RespEntity update(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String id = request.getParameter("id");
        String site = request.getParameter("site");
        String time = request.getParameter("time");
        String name = request.getParameter("name");
        String peopleNumber = request.getParameter("peopleNumber");
        int p = -1;
        if (userId == null || id == null)
            return new RespEntity(RespCode.WRONGPARAM);
        try{
            if (peopleNumber != null)
                p = Integer.parseInt(peopleNumber);
        }catch (NumberFormatException e){
            return new RespEntity(RespCode.WRONGPARAM);
        }
        return planService.update(userId, id, name, site, time, p);
    }

    @RequestMapping("/insert")
    public RespEntity insert(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String site = request.getParameter("site");
        String time = request.getParameter("time");
        String name = request.getParameter("name");
        String peopleNumber = request.getParameter("peopleNumber");
        int p = 1;
        if (userId == null)
            return new RespEntity(RespCode.WRONGPARAM);
        try{
            if (peopleNumber != null)
                p = Integer.parseInt(peopleNumber);
        }catch (NumberFormatException e){
            return new RespEntity(RespCode.WRONGPARAM);
        }
        return planService.insert(userId, name, site, time, p);
    }

    @RequestMapping("/delete")
    public RespEntity delete(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String id = request.getParameter("id");
        if (userId == null || id == null)
            return new RespEntity(RespCode.WRONGPARAM);
        return planService.delete(userId, id);
    }
}
