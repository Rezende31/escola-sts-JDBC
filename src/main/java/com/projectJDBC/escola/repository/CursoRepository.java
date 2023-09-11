package com.projectJDBC.escola.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.projectJDBC.escola.db.DB;
import com.projectJDBC.escola.model.Curso;

@Repository
public class CursoRepository {

	@Autowired
	private static Connection conn = DB.getConnection();
//
//	public CursoRepository(Connection conn) {
//		this.conn = conn;
//	}

	public List<Curso> listarCursos()  {
        List<Curso> cursos = new ArrayList<>();
        
        

        String sql = "SELECT * FROM curso";

        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet resultSet = ps.executeQuery()) {

            while (resultSet.next()) {
                String nome = resultSet.getString("Nome");
                String descricao = resultSet.getString("Descricao");
                Double duracao = resultSet.getDouble(1);

                Curso curso = new Curso();
                curso.setNome(nome);
                curso.setDescricao(descricao);
                curso.setDuracao(duracao);

                cursos.add(curso);
            }
        } catch (SQLException e) {
			e.printStackTrace();
		}
        DB.closeConnection();
        return cursos;
	}


}
