package com.projectJDBC.escola.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectJDBC.escola.model.Curso;
import com.projectJDBC.escola.repository.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private final CursoRepository cursoRepository;
	
	@Autowired
    public CursoService(CursoRepository cursoRepository) {
        this.cursoRepository = cursoRepository;
    }
	
	public List<Curso> findCursos() {
		List<Curso> teste = cursoRepository.listarCursos();
		return teste;
	}
	
	public void cadastrarCurso(Curso curso) {
        cursoRepository.salvarCurso(curso);
    }
	
	public void excluirCurso(int codigo) {
        cursoRepository.excluirCurso(codigo);
    }
	
	public void atualizarCurso(Curso curso) {
        cursoRepository.atualizarCurso(curso);
    }
	

}
