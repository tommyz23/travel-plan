package com.example.dao.Impl;

import com.example.dao.PhotoDao;
import com.example.entity.Photo;
import com.example.utils.RowMapper.PhotoRowMapper;
import com.example.utils.response.RespCode;
import com.example.utils.response.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class PhotoDaoImpl implements PhotoDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PhotoDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Photo> getList(int planId) {
        String sql = "SELECT * FROM PHOTO WHERE PLAN_ID=?";
        List<Photo> list;
        try {
            list = jdbcTemplate.query(sql, new PhotoRowMapper(), planId);
            return list;
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public List<Photo> getThePhoto(int planId, int uploaderId) {
        String sql = "SELECT * FROM PHOTO WHERE PLAN_ID=? AND UPLOADER_ID=?";
        List<Photo> photo;
        try {
            photo = jdbcTemplate.query(sql, new PhotoRowMapper(), planId, uploaderId);
            return photo;
        }catch (DataAccessException e){
            return null;
        }
    }

    @Override
    public RespEntity add(int planId, int uploaderId, String path) {
        String sql = "INSERT INTO PHOTO(PLAN_ID,UPLOADER_ID,PHOTO_PATH) VALUES (?,?,?)";
        int insert;
        try {
            insert = jdbcTemplate.update(sql, planId, uploaderId, path);
            if (insert == 0)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.SUCCESS, insert);
        }catch (DataAccessException e){
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }

    @Override
    public RespEntity del(int id) {
        String sql = "DELETE FROM PHOTO WHERE PHOTO_ID=?";
        int del;
        try {
            del = jdbcTemplate.update(sql, id);
            if (del == 0)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.SUCCESS, del);
        }catch (DataAccessException e){
            return new RespEntity(RespCode.WARN);
        }
    }

    /*
    删除活动所有照片
     */
    @Override
    public RespEntity delAll(int planId) {
        String sql = "DELETE FROM PHOTO WHERE PLAN_ID=?";
        int del ;
        try {
            del = jdbcTemplate.update(sql, planId);
            if (del == 0)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.SUCCESS, del);
        }catch (DataAccessException e){
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }

    /*
    删除某用户某一活动所有照片
     */
    @Override
    public RespEntity delAll(int planId, int uploaderId) {
        String sql = "DELETE FROM PHOTO WHERE PLAN_ID=? AND UPLOADER_ID=?";
        int del ;
        try {
            del = jdbcTemplate.update(sql, planId, uploaderId);
            if (del == 0)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.SUCCESS, del);
        }catch (DataAccessException e){
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }

    @Override
    public String getPath(int id){
        String sql = "SELECT PHOTO_PATH FROM PHOTO WHERE PHOTO_ID=?";
        String path = jdbcTemplate.queryForObject(sql, String.class, id);
        return path;
    }
}
