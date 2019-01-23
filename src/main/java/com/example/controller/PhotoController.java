package com.example.controller;

import com.example.service.PhotoService;
import com.example.utils.response.RespCode;
import com.example.utils.response.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/photo")
public class PhotoController {

    private final PhotoService photoService;
    @Autowired
    public PhotoController(PhotoService p){
        this.photoService = p;
    }

    @RequestMapping("/getList")
    public RespEntity getList(HttpServletRequest request){
        String planId_s = request.getParameter("planId");
        if (planId_s == null)
            return new RespEntity(RespCode.WRONGPARAM);
        int planId = Integer.parseInt(planId_s);
        return photoService.getList(planId);
    }

    @RequestMapping("/getUserPhoto")
    public RespEntity getThePhoto(HttpServletRequest request){
        String planId_s = request.getParameter("planId");
        String uploaderId_s = request.getParameter("uploaderId");
        if (planId_s == null || uploaderId_s == null)
            return new RespEntity(RespCode.WARN);
        int planId = Integer.parseInt(planId_s);
        int uploaderId = Integer.parseInt(uploaderId_s);
        return photoService.getThePhoto(planId, uploaderId);
    }

    @RequestMapping("/upload")
    public RespEntity add(HttpServletRequest request, @RequestParam("file")MultipartFile file){
        String fileName = file.getOriginalFilename();
        String planId_s = request.getParameter("planId");
        String uploaderId_s = request.getParameter("uploaderId");
        if (planId_s == null || uploaderId_s == null)
            return new RespEntity(RespCode.WARN);
        String path = photoService.upload(file, fileName, planId_s, uploaderId_s);
        int planId = Integer.parseInt(planId_s);
        int uploaderId = Integer.parseInt(uploaderId_s);
        if (path == null)
            return new RespEntity(RespCode.WARN);
        return photoService.add(planId, uploaderId, path);
    }

    @RequestMapping("delete")
    public RespEntity del(HttpServletRequest request){
        String id = request.getParameter("id");
        if (id == null)
            return new RespEntity(RespCode.WARN);
        int photoId = Integer.parseInt(id);
        return photoService.del(photoId);
    }

    @RequestMapping("/deleteAll")
    public RespEntity delAll(HttpServletRequest request){
        String planId_s = request.getParameter("planId");
        if (planId_s == null)
            return new RespEntity(RespCode.WARN);
        int planId = Integer.parseInt(planId_s);
        return photoService.delAll(planId);
    }

    @RequestMapping("/deleteMyAll")
    public RespEntity delMyAll(HttpServletRequest request){
        String planId_s = request.getParameter("planId");
        String uploaderId_s = request.getParameter("uploaderId");
        if (planId_s == null || uploaderId_s == null)
            return new RespEntity(RespCode.WARN);
        int planId = Integer.parseInt(planId_s);
        int uploaderId = Integer.parseInt(uploaderId_s);
        return photoService.delAll(planId, uploaderId);
    }
}
