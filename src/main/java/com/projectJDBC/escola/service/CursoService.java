package com.projectJDBC.escola.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectJDBC.escola.model.Curso;
import com.projectJDBC.escola.repository.CursoRepository;

@Service
public class CursoService {
	
	@Autowired
	private CursoRepository repository;
	
	public List<Curso> findCursos() {
		List<Curso> teste = repository.listarCursos();
		return teste;
	}

}
