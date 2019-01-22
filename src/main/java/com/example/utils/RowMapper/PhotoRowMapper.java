package com.example.utils.RowMapper;

import com.example.entity.Photo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhotoRowMapper implements RowMapper<Photo> {
    @Override
    public Photo mapRow(ResultSet resultSet, int i) throws SQLException {
        Photo photo = new Photo();
        photo.setPhotoPath(resultSet.getString("photo_path"));
        photo.setPlanId(resultSet.getInt("plan_id"));
        photo.setUploaderId(resultSet.getInt("uploader_id"));
        photo.setId(resultSet.getInt("photo_id"));
        return photo;
    }
}
