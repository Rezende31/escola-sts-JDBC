package com.projectJDBC.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectJDBC.escola.model.Curso;
import com.projectJDBC.escola.service.CursoService;

@RestController
@RequestMapping(value = "/curso")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	@GetMapping()
	public ResponseEntity<List<Curso>> findCourse(){
		List<Curso> teste = cursoService.findCursos();
		return ResponseEntity.ok().body(teste);
	}
	
	

}
