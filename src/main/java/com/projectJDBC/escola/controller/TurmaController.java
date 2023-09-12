package com.projectJDBC.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectJDBC.escola.model.Turma;
import com.projectJDBC.escola.service.TurmaService;

@RestController
@RequestMapping(value = "/turmas")
public class TurmaController {
	
	private final TurmaService turmaService;

    @Autowired
    public TurmaController(TurmaService turmaService) {
        this.turmaService = turmaService;
    }

    @GetMapping("/buscarPorCurso/{codigoCurso}")
    public List<Turma> buscarTurmasPorCurso(@PathVariable int codigoCurso) {
        return turmaService.buscarTurmasPorCurso(codigoCurso);
    }

}
