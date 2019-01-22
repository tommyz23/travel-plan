package com.example.dao;

import com.example.entity.Photo;
import com.example.utils.response.RespEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface PhotoDao {
    List<Photo> getList(int planId);
    List<Photo> getThePhoto(int planId, int uploaderId);
    RespEntity add(int planId, int uploaderId, String path);
    RespEntity del(int id);
    RespEntity delAll(int planId);
    RespEntity delAll(int planId, int uploaderId);
    String getPath(int id);
}
