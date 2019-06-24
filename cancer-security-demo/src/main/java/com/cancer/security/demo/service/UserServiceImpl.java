package com.cancer.security.demo.service;


import com.cancer.security.demo.entity.User;
import com.cancer.security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public User getUserByUsername() {
        User user = (User)jdbcTemplate.queryForObject(" select * from  user where username ='杨十七' ",
                new RowMapper<Object>() {
                    @Override
                    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
                        User user = new User();
                        String user_id = resultSet.getString("user_id");
                        String username = resultSet.getString("username");
                        String password = resultSet.getString("password");
                        user.setUserId(user_id);
                        user.setUsername(username);
                        user.setPassword(password);
                        return user;
                    }
                });
        return user;
    }

    @Override
    public void register(User user) {

        int update = jdbcTemplate.update(new PreparedStatementCreator() {
            String sql = "INSERT INTO `user`(username,`password`)  VALUES(?,?)";

            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql);
                ps.setString(1, user.getUsername());
                ps.setString(2, user.getPassword());
                return ps;
            }
        });

        System.out.println(update);
    }
}
