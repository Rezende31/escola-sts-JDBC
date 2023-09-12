package com.projectJDBC.escola.repository;

import java.sql.Connection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.projectJDBC.escola.db.DB;
import com.projectJDBC.escola.model.Turma;

@Repository
public class TurmaRepository {
	
	private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TurmaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	@Autowired
	private static Connection conn = DB.getConnection();
	
	@SuppressWarnings("deprecation")
	public List<Turma> buscarTurmasPorCurso(int codigoCurso) {
        String sql = "SELECT * FROM Turma WHERE Curso = ?";
        
        return jdbcTemplate.query(sql, new Object[]{codigoCurso}, (rs, rowNum) -> {
            Turma turma = new Turma();
            turma.setCodigo(rs.getInt("Codigo"));
            turma.setInicio(rs.getDate("Inicio"));
            turma.setFim(rs.getDate("Fim"));
            turma.setLocal(rs.getString("Local"));
            // Outros campos da Turma, se necessário
            return turma;
        });
    }

}
