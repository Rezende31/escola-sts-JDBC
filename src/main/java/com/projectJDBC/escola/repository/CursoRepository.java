package com.projectJDBC.escola.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.projectJDBC.escola.db.DB;
import com.projectJDBC.escola.model.Curso;

@Repository
public class CursoRepository {

	@Autowired
	private static Connection conn = DB.getConnection();
	
	private final JdbcTemplate jdbcTemplate;
	
	@Autowired
    public CursoRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


	public List<Curso> listarCursos() {
		List<Curso> cursos = new ArrayList<>();
		Connection conn = DB.getConnection();
		String sql = "SELECT * FROM curso";

		try (PreparedStatement ps = conn.prepareStatement(sql); ResultSet resultSet = ps.executeQuery()) {

			while (resultSet.next()) {
				String nome = resultSet.getString("Nome");
				String descricao = resultSet.getString("Descricao");
				int duracao = resultSet.getInt("Duracao");
				int codigo = resultSet.getInt("Codigo");

				Curso curso = new Curso();
				curso.setCodigo(codigo);
				curso.setNome(nome);
				curso.setDescricao(descricao);
				curso.setDuracao(duracao);

				cursos.add(curso);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
//        DB.closeConnection();
		return cursos;
	}
	
	//TESTE COM JDBC DE SALVAR CURSO
	public void salvarCurso(Curso curso) {
        String sql = "INSERT INTO Curso (Nome, Descricao, Duracao) VALUES (?, ?, ?)";
        jdbcTemplate.update(sql, curso.getNome(), curso.getDescricao(), curso.getDuracao());
    }

	public void excluirCurso(int codigo) {
		
		String sql = "DELETE FROM Curso WHERE Codigo = ?";
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setInt(1, codigo);
			stmt.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException("Erro ao excluir o curso", e);
		}
//		DB.closeConnection();
	}

	
	public void atualizarCurso(Curso curso) {
        String sql = "UPDATE Curso SET Nome = ?, Descricao = ?, Duracao = ? WHERE Codigo = ?";
        try (
             PreparedStatement pr = conn.prepareStatement(sql)) {
        	pr.setString(1, curso.getNome());
            pr.setString(2, curso.getDescricao());
            pr.setInt(3, curso.getDuracao());
            pr.setInt(4, curso.getCodigo());
            
            pr.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar o curso", e);
        }
	}

}
