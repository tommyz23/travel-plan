package com.example.service;

import com.example.entity.Photo;
import com.example.utils.response.RespEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Component
public interface PhotoService {
    RespEntity getList(int planId);
    RespEntity getThePhoto(int planId, int uploaderId);
    RespEntity add(int planId, int uploaderId, String path);
    RespEntity del(int id);
    RespEntity delAll(int planId);
    RespEntity delAll(int planId, int uploaderId);
    String upload(MultipartFile file, String name, String planId, String uploaderId);
    byte[] read(String path);
}
