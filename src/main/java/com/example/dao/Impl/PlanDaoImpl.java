package com.example.dao.Impl;

import com.example.dao.PlanDao;
import com.example.entity.Plan;
import com.example.utils.RowMapper.PlanRowMapper;
import com.example.utils.response.RespCode;
import com.example.utils.response.RespEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.quartz.QuartzProperties;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class PlanDaoImpl implements PlanDao {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PlanDaoImpl(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RespEntity getPlan(String id) {
        String sql = "SELECT * FROM PLAN WHERE ID=?";
        Plan plan;
        try {
            plan = jdbcTemplate.queryForObject(sql, new PlanRowMapper(), id);
            return new RespEntity(RespCode.SUCCESS, plan);
        }catch (DataAccessException e){
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }

    @Override
    public RespEntity getPlanList() {
        List<Plan> list;
        String sql = "SELECT * FROM PLAN WHERE TIME>?";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String now = sdf.format(new Date());
        try {
            list = jdbcTemplate.query(sql, new Object[]{now}, new PlanRowMapper());
            return new RespEntity(RespCode.SUCCESS, list);
        } catch (DataAccessException e){
            System.out.println(e);
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }

    @Override
    public RespEntity insert(String userId_s, String name, String site, String time, int peopleNumber) {
        int userId = Integer.parseInt(userId_s);
        String sqlHead = "INSERT INTO PLAN (";
        String sqlMid = ") VALUE (";
        String sqlEnd = ")";
        String key = "NAME,SITE,TIME,PEOPLE_NUMBER";
        String values = "'" + name + "','" + site + "','" + time + "','" + peopleNumber + "'";
        String sql0 = sqlHead + key + sqlMid + values + sqlEnd;
        String sql1 = "INSERT INTO PLAN_USER (PLAN_ID,USER_ID,ROLE) VALUE (?,?,0)";
        int insert0, insert1;
        System.out.println(sql0 + "/" +sql1);
        try{
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                public PreparedStatement createPreparedStatement(Connection conn)
                        throws SQLException {
                    PreparedStatement ps = conn.prepareStatement(sql0, Statement.RETURN_GENERATED_KEYS);
                    return ps;
                }
            }, keyHolder);
            int planId = keyHolder.getKey().intValue();
            insert1 = jdbcTemplate.update(sql1, planId, userId);
            if (insert1 == 0)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.SUCCESS, planId);
        }catch (DataAccessException e){
            System.out.println(e);
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }

    @Override
    public RespEntity update(String userId, String name, String id_s, String site, String time, int peopleNumber) {
        int id = Integer.parseInt(id_s);
        StringBuilder sb = new StringBuilder();
        if (name != null)
            sb.append("name='").append(name).append("',");
        if (site != null)
            sb.append("site='").append(site).append("',");
        if (time != null)
            sb.append("time='").append(time).append("',");
        if (peopleNumber != -1)
            sb.append("people_number='").append(peopleNumber).append("',");
        String sqlHead = "UPDATE PLAN SET ";
        String param = sb.toString().substring(0, sb.length() - 1);
        String conditions = "WHERE ID=?";
        String sql = sqlHead + param + conditions;
        int update;
        try {
            update = jdbcTemplate.update(sql, id);
            if (update == 0)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.SUCCESS, update);
        }catch (DataAccessException e){
            System.out.println(e);
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }

    @Override
    public RespEntity delete(String userId_s, String id_s) {
        String sql0 = "DELETE FROM PLAN WHERE ID=?";
        String sql1 = "DELETE FROM PLAN_USER WHERE PLAN_ID=? AND USER_ID=?";
        int userId = Integer.parseInt(userId_s);
        int id =Integer.parseInt(id_s);
        int del0, del1;
        try {
            del1 = jdbcTemplate.update(sql1, id, userId);
            del0 = jdbcTemplate.update(sql0, id);
            if (del0 == 0 || del1 == 0)
                return new RespEntity(RespCode.WARN);
            return new RespEntity(RespCode.SUCCESS, del0&del1);
        }catch (DataAccessException e){
            System.out.println(e);
            return new RespEntity(RespCode.WARN, e.getMessage());
        }
    }
}
