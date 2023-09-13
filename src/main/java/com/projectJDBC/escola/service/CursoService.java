package com.projectJDBC.escola.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectJDBC.escola.model.Curso;
import com.projectJDBC.escola.model.Turma;
import com.projectJDBC.escola.repository.CursoRepository;
import com.projectJDBC.escola.repository.TurmaRepository;

@Service
public class CursoService {
	
	@Autowired
	private final CursoRepository cursoRepository;
	
	@Autowired
	private final TurmaRepository turmaRepository;
	
	
	@Autowired
    public CursoService(CursoRepository cursoRepository, TurmaRepository turmaRepository) {
        this.cursoRepository = cursoRepository;
        this.turmaRepository = turmaRepository;
    }
	
	public List<Curso> findCursos() {
		List<Curso> teste = cursoRepository.listarCursos();
		return teste;
	}
	
	public void cadastrarCurso(Curso curso) {
        cursoRepository.salvarCurso(curso);
    }
	
	public void excluirCurso(int codigo) {
		List<Turma> turmasAssociadas = turmaRepository.buscarTurmasPorCurso(codigo);
		for (Turma turma : turmasAssociadas) {
			turmaRepository.deletarTurmaPorCodigo(turma.getCodigo());
	    }
        cursoRepository.excluirCurso(codigo);
    }
	
	public void atualizarCurso(Curso curso) {
        cursoRepository.atualizarCurso(curso);
    }
	
}
