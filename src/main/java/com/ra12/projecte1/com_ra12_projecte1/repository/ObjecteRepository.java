package com.ra12.projecte1.com_ra12_projecte1.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ObjecteRepository {

     @Autowired
    JdbcTemplate jdbcTemplate;

    private static final class ObjectRowMapper implements RowMapper<Object> {

        @Override
        public Object mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {

            User user = new User();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setDescription(rs.getString("description"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setUltimAccess(rs.getTimestamp("ultimAcces"));
            user.setDataCreated(rs.getTimestamp("dataCreated"));
            user.setDataUpdated(rs.getTimestamp("dataUpdated"));
            user.setImagePath(rs.getString("imagePath"));

            return user;
        }

    }

    public List<Object> findAll() {
        String sql = "SELECT * FROM objects";
        return jdbcTemplate.query(sql, new ObjectRowMapper());
    }

}
