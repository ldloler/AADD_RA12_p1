package com.ra12.projecte1.com_ra12_projecte1.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ra12.projecte1.com_ra12_projecte1.dto.UsuarioRequest;
import com.ra12.projecte1.com_ra12_projecte1.dto.UsuarioResponse;

@Repository
public class UsuarioRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public static final class UsuarioRowMapper implements RowMapper<UsuarioResponse>{

        @Override
        public UsuarioResponse mapRow(ResultSet rs, int rowNum) throws SQLException {
            UsuarioResponse usuario = new UsuarioResponse();
            usuario.setId(rs.getLong("id"));
            usuario.setNom(rs.getString("nombre"));
            usuario.setEmail(rs.getString("email"));
            usuario.setContrasenya(rs.getString("contrasenya"));
            return usuario;
        }
    }

    public int save(UsuarioRequest user){
        String sql = "INSERT INTO usuario (nombre, email, contrasenya) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, user.getNom(), user.getEmail(), user.getContrasenya());
    }

    public List<UsuarioResponse> findAll(){
        String sql = "SELECT * FROM usuario";
        return jdbcTemplate.query(sql, new UsuarioRowMapper());
    }

    public UsuarioResponse findById(Long id){
        String sql = "SELECT * FROM usuario WHERE id = ?";
        return jdbcTemplate.query(sql, new UsuarioRowMapper(), id).get(0);
    }

    public UsuarioResponse findByName(String nombre){
        String sql = "SELECT * FROM usuario WHERE nombre = ?";
        return jdbcTemplate.query(sql, new UsuarioRowMapper(), nombre).get(0);
    }

    public int changeUserName(String nombre_nuevo, String nombre_antiguo){
        String sql = "UPDATE usuario SET nombre = ? WHERE nombre = ?";
        return jdbcTemplate.update(sql, nombre_nuevo, nombre_antiguo);
    }

    public int changePassword(String nombre, String contrasenya){
        String sql = "UPDATE usuario SET contrasenya = ? WHERE nombre  = ?";
        return jdbcTemplate.update(sql, contrasenya, nombre);
    }

    public int deleteUser(UsuarioRequest usuario){
        String sql = "DELETE FROM usuario WHERE nombre = ? AND contrasenya = ?";
        return jdbcTemplate.update(sql, usuario.getNom(), usuario.getContrasenya());
    }

    public UsuarioResponse findByUser(UsuarioRequest usuario){
        String sql = "SELECT * FROM usuario WHERE nombre = ? and contrasenya = ?";
        return jdbcTemplate.query(sql, new UsuarioRowMapper(), usuario.getNom(), usuario.getContrasenya()).get(0);
    }
}
