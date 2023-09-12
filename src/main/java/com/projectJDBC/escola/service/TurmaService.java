package com.projectJDBC.escola.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectJDBC.escola.model.Turma;
import com.projectJDBC.escola.repository.TurmaRepository;

@Service
public class TurmaService {

	private final TurmaRepository turmaRepository;

    @Autowired
    public TurmaService(TurmaRepository turmaRepository) {
        this.turmaRepository = turmaRepository;
    }

    public List<Turma> buscarTurmasPorCurso(int codigoCurso) {
        return turmaRepository.buscarTurmasPorCurso(codigoCurso);
    }
	
}
