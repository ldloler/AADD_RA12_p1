package com.ra12.projecte1.com_ra12_projecte1.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ra12.projecte1.com_ra12_projecte1.dto.ObjectRequest;
import com.ra12.projecte1.com_ra12_projecte1.dto.ObjectResponse;

@Repository
public class ObjecteRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final class ObjecteRowMapper implements RowMapper<ObjectResponse> {

        @Override
        public ObjectResponse mapRow(@SuppressWarnings("null") ResultSet rs, int rowNum) throws SQLException {

            ObjectResponse objecte = new ObjectResponse();
            objecte.setId(rs.getLong("id"));
            objecte.setTitulo(rs.getString("titulo"));
            objecte.setImage_path(rs.getString("image_path"));
            objecte.setUser(rs.getString("user"));
            objecte.setDescription(rs.getString("descripcion"));
            objecte.setaCanvi(rs.getString("aCanvi"));
            objecte.setFav(rs.getBoolean("isFav"));
            objecte.setDataCreated(rs.getTimestamp("dataCreated"));
            objecte.setDataUpdated(rs.getTimestamp("dataUpdated"));

            return objecte;
        }

    }

    public List<ObjectResponse> findAll() {
        String sql = "SELECT * FROM objecte";
        return jdbcTemplate.query(sql, new ObjecteRowMapper());
    }
    
    public ObjectResponse findObjecteById(Long id){
        String sql = "SELECT * FROM objecte WHERE id = ?";
        return jdbcTemplate.query(sql, new ObjecteRowMapper(), id).get(0);
    }

    public int save(ObjectRequest objecte) {
        String sql = "INSERT INTO db_trokka.objecte (titulo, user, descripcion, aCanvi, isFav) VALUES(?, ?, ?, ?, ?)";

        int numReg = jdbcTemplate.update(sql, objecte.getTitulo(), objecte.getUser(), objecte.getaCanvi(), objecte.getDescription(),
                objecte.isFav());

        return numReg;
    }

    public int update(Long id, ObjectRequest objecte){
        String sql = "UPDATE objecte SET user = ?, descripcion = ?, aCanvi = ?, isFav = ? WHERE id = ?";
        return jdbcTemplate.update(sql, objecte.getUser(), objecte.getDescription(), objecte.getaCanvi(), objecte.isFav(), id);
    }

    public int delete(Long id){
        String sql = "DELETE FROM objecte WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public int delete(){
        String sql = "DELETE FROM objecte";
        return jdbcTemplate.update(sql);
    }

    public int uploadImage(Long id, String image_path){
        String sql = "UPDATE objecte SET image_path = ? WHERE id = ?";
        return jdbcTemplate.update(sql, image_path, id);
    }

}
