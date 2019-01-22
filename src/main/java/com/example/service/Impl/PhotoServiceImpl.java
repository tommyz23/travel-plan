package com.example.service.Impl;

import com.example.dao.PhotoDao;
import com.example.entity.Photo;
import com.example.service.PhotoService;
import com.example.utils.FileUtils.FileHelper;
import com.example.utils.response.RespCode;
import com.example.utils.response.RespEntity;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoDao photoDao;

    @Autowired
    public PhotoServiceImpl(PhotoDao photoDao){
        this.photoDao = photoDao;
    }

    @Override
    public RespEntity getList(int planId) {
        List<Photo> photos = photoDao.getList(planId);
        return setContent(photos);
    }

    @Override
    public RespEntity getThePhoto(int planId, int uploaderId) {
        List<Photo> photos =  photoDao.getThePhoto(planId, uploaderId);
        return setContent(photos);
    }

    @Override
    public RespEntity add(int planId, int uploaderId, String path) {
        return photoDao.add(planId, uploaderId, path);
    }

    @Override
    public RespEntity del(int id) {
        String path = photoDao.getPath(id);
        if (path == null)
            return new RespEntity(RespCode.WARN);
        //首先根据图片路径删除图片
        boolean del = FileHelper.del(path);
        //如果删除成功则删除数据库数据
        if (del)
            return photoDao.del(id);
        return new RespEntity(RespCode.WARN);
    }

    @Override
    public RespEntity delAll(int planId) {
        String path = FileHelper.getPath() + planId;
        boolean del = FileHelper.delAll(path);
        if (del)
            return photoDao.delAll(planId);
        return new RespEntity(RespCode.WARN);
    }

    @Override
    public RespEntity delAll(int planId, int uploaderId) {
        String path = FileHelper.getPath() + planId + "\\" + uploaderId;
        boolean del = FileHelper.delAll(path);
        if (del)
            return photoDao.delAll(planId, uploaderId);
        return new RespEntity(RespCode.WARN);
    }

    @Override
    public String upload(MultipartFile file, String name, String planId, String uploaderID) {
        return FileHelper.upload(file, name, planId, uploaderID);
    }

    @Override
    public byte[] read(String path) {
        return FileHelper.read(path);
    }

    private RespEntity setContent(List<Photo> photos){
        for (Photo photo: photos){
            photo.setPhoto(read(photo.getPhotoPath()));
        }
        return new RespEntity(RespCode.SUCCESS, photos);
    }
}
