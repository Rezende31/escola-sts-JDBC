package com.projectJDBC.escola.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.projectJDBC.escola.model.Curso;
import com.projectJDBC.escola.model.Funcionario;
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
	
	public void deletarTurmaPorCodigo(int codigo) {
        String sql = "DELETE FROM Turma WHERE Codigo = ?";
        jdbcTemplate.update(sql, codigo);
    }
	
	@SuppressWarnings("deprecation")
	public List<Funcionario> listarParticipantesPorCodigoTurma(int codigoTurma) {
        String sql = "SELECT f.* FROM Funcionario f " +
                     "INNER JOIN TurmaParticipante tp ON f.Codigo = tp.Funcionario " +
                     "WHERE tp.Turma = ?";
        return jdbcTemplate.query(
            sql,
            new Object[]{codigoTurma},
            (rs, rowNum) -> {
                Funcionario funcionario = new Funcionario();
                funcionario.setCodigo(rs.getInt("Codigo"));
                funcionario.setNome(rs.getString("Nome"));
                funcionario.setAdmissao(rs.getDate("Admissao"));
                funcionario.setCargo(rs.getString("Cargo"));
                funcionario.setCpf(rs.getString("CPF"));
                funcionario.setNascimento(rs.getDate("Nascimento"));
                funcionario.setStatus(rs.getBoolean("Status"));
//                funcionario.setStatus(rs.getBoolean("Status") ? true : false);
                return funcionario;
            }
        );
    }
	
	public void adicionarParticipante(int codigoTurma, int codigoFuncionario) {
        String sql = "INSERT INTO TurmaParticipante (Turma, Funcionario) VALUES (?, ?)";
        jdbcTemplate.update(sql, codigoTurma, codigoFuncionario);
    }
	
	public void excluirParticipante(int codigoTurma, int codigoFuncionario) {
        String sql = "DELETE FROM TurmaParticipante WHERE Turma = ? AND Funcionario = ?";
        jdbcTemplate.update(sql, codigoTurma, codigoFuncionario);
    }
	
	public boolean turmaCurso(int codigoCurso) {
        String sql = "SELECT COUNT(*) FROM Turma WHERE Curso = ?";
        int count = jdbcTemplate.queryForObject(sql, Integer.class, codigoCurso);
        return count > 0;
    }
	
//	ALTER TABLE Turma
//	ADD CONSTRAINT fk_curso
//	FOREIGN KEY (Curso)
//	REFERENCES Curso(Codigo)
//	ON DELETE CASCADE;
	
//	ALTER TABLE TurmaParticipante
//	ADD CONSTRAINT NomeDaChaveEstrangeira
//	FOREIGN KEY (Turma) REFERENCES Turma(Codigo) ON DELETE CASCADE;

}
