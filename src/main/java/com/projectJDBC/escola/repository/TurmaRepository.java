package com.projectJDBC.escola.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.projectJDBC.escola.model.Curso;
import com.projectJDBC.escola.model.Turma;

@Repository
public class TurmaRepository {
	
	private final JdbcTemplate jdbcTemplate;

    @Autowired
    public TurmaRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
	
	
	@SuppressWarnings("deprecation")
	public List<Turma> buscarTurmasPorCurso(int codigoCurso) {
		
        String sql = "SELECT\r\n"
        		+ "    t.Codigo AS TurmaCodigo,\r\n"
        		+ "    t.Inicio AS DataInicio,\r\n"
        		+ "    t.Fim AS DataFim,\r\n"
        		+ "    t.Local AS LocalTreinamento,\r\n"
        		+ "    c.Codigo AS CursoCodigo,\r\n"
        		+ "    c.Nome AS NomeCurso,\r\n"
        		+ "    c.Descricao AS DescricaoCurso,\r\n"
        		+ "    c.Duracao AS DuracaoCurso\r\n"
        		+ "	FROM\r\n"
        		+ "    Turma t\r\n"
        		+ "INNER JOIN\r\n"
        		+ "    Curso c ON t.Curso = c.Codigo\r\n"
        		+ "    where t.Curso = ?";
        return jdbcTemplate.query(sql, new Object[]{codigoCurso}, (rs, rowNum) -> {
            Turma turma = new Turma();
            turma.setCodigo(rs.getInt("TurmaCodigo"));
            turma.setInicio(rs.getDate("DataInicio"));
            turma.setFim(rs.getDate("DataFim"));
            turma.setLocal(rs.getString("LocalTreinamento"));
            
            Curso curso = new Curso();
    		curso.setCodigo(codigoCurso);
    		curso.setDescricao(rs.getString("DescricaoCurso"));
    		curso.setDuracao(rs.getInt("DuracaoCurso"));
    		curso.setNome(rs.getString("NomeCurso"));
            turma.setCurso(curso);
            return turma;
        });
    }
	
	public void salvarTurma(Turma turma) {
        String sql = "INSERT INTO Turma (Inicio, Fim, Local, Curso) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(
            sql,
            turma.getInicio(),
            turma.getFim(),
            turma.getLocal(),
            turma.getCurso().getCodigo()
        );
    }
	
	public void atualizarTurma(Turma turma) {
        String sql = "UPDATE Turma SET Inicio = ?, Fim = ?, Local = ?, Curso = ? WHERE Codigo = ?";
        jdbcTemplate.update(
            sql,
            turma.getInicio(),
            turma.getFim(),
            turma.getLocal(),
            turma.getCurso().getCodigo(),
            turma.getCodigo()
        );
    }

}
