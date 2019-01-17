package com.example.dao.Impl;

import com.example.dao.PlanUserDao;
import com.example.entity.PlanUser;
import com.example.utils.RowMapper.PlanUserRowMapper;
import com.example.utils.response.RespCode;
import com.example.utils.response.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

@Repository
public class PlanUserDaoImpl implements PlanUserDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlanUserDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RespEntity addUser(int planId, int userId) {
        String sql = "INSERT INTO PLAN_USER(PLAN_ID,USER_ID,ROLE) VALUES(?,?,FALSE)";
        return getRespEntity(planId, userId, sql);
    }

    @Override
    public RespEntity delUser(int planId, int userId) {
        String sql = "DELETE FROM PLAN_USER WHERE PLAN_ID=? AND USER_ID=?";
        return getRespEntity(planId, userId, sql);
    }

    private RespEntity getRespEntity(int planId, int userId, String sql) {
        int flag;
        try {
            flag = jdbcTemplate.update(sql, planId, userId);
            if (flag == 0)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.SUCCESS, flag);
        } catch (DataAccessException e){
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }

    @Override
    public RespEntity modRole(int planId, int oldAdmin, int newAdmin) {
        String mod = "UPDATE PLAN_USER SET ROLE=? WHERE PLAN_ID=? AND USER_ID=?";
        List<Object[]> params = new ArrayList<>();
        Object[] paramOld ={FALSE, planId, oldAdmin};
        Object[] paramNew = {TRUE, planId, newAdmin};
        params.add(paramOld);
        params.add(paramNew);
        int[] update;
        try {
            update = jdbcTemplate.batchUpdate(mod, params);
            if (update[0] == 0 || update[1] == 0)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.SUCCESS, update);
        }catch (DataAccessException e){
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }

    @Override
    public RespEntity getPlans(int userId) {
        String sql = "SELECT * FROM PLAN_USER WHERE USER_ID=?";
        return getRespEntity(userId, sql);
    }

    @Override
    public RespEntity getMyPlan(int userId) {
        String sql = "SELECT * FROM PLAN_USER WHERE USER_ID=? AND ROLE=TRUE";
        return getRespEntity(userId, sql);
    }

    private RespEntity getRespEntity(int userId, String sql) {
        List<PlanUser> list;
        try {
            list = jdbcTemplate.query(sql, new PlanUserRowMapper(), userId);
            if (list == null)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.SUCCESS, list);
        }catch (DataAccessException e){
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }
}
