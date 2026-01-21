package com.ra12.projecte1.com_ra12_projecte1.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ra12.projecte1.com_ra12_projecte1.model.Objecte;

@Repository
public class ObjecteRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final class ObjecteRowMapper implements RowMapper<Objecte> {

        @Override
        public Objecte mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {

            Objecte objecte = new Objecte();
            objecte.setId(rs.getLong("id"));
            objecte.setImage_path(rs.getString("image_path"));
            objecte.setUser(rs.getString("user"));
            objecte.setDescription(rs.getString("description"));
            objecte.setFav(rs.getBoolean("isfav"));

            return objecte;
        }

    }

    public List<Objecte> findAll() {
        String sql = "SELECT * FROM objects";
        return jdbcTemplate.query(sql, new ObjecteRowMapper());
    }

    public int save(Objecte objecte) {
        String sql = "INSERT INTO db_trokka.objects (image_path, user, description, isfav) VALUES(?, ?, ?, ?)";

        int numReg = jdbcTemplate.update(sql, objecte.getImage_path(), objecte.getUser(), objecte.getDescription(),
                objecte.isFav());

        return numReg;
    }

}
