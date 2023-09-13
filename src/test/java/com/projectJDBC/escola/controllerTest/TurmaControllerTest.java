package com.projectJDBC.escola.controllerTest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projectJDBC.escola.controller.TurmaController;
import com.projectJDBC.escola.model.Turma;
import com.projectJDBC.escola.service.TurmaService;

public class TurmaControllerTest {

	private TurmaController turmaController;
	private TurmaService turmaService;

	@BeforeEach
	public void setUp() {
		turmaService = mock(TurmaService.class);
		turmaController = new TurmaController(turmaService);
	}

	@Test
	public void testBuscarTurmaPorCurso() {
		int codigoCurso = 1;

		List<Turma> turmasTeste = new ArrayList<>();
		turmasTeste.add(new Turma());
		turmasTeste.add(new Turma());

		when(turmaService.buscarTurmasPorCurso(codigoCurso)).thenReturn(turmasTeste);
		ResponseEntity<List<Turma>> response = turmaController.buscarTurmasPorCurso(codigoCurso);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(turmasTeste, response.getBody());
	}
	
	@Test
    public void testIncluirTurma() {
        Turma turmaTeste = new Turma();
        turmaTeste.setCodigo(1); 

        ResponseEntity<String> response = turmaController.incluirTurma(turmaTeste);
        verify(turmaService).salvarTurma(turmaTeste);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Turma cadastrada a um curso com sucesso", response.getBody());
    }

}
