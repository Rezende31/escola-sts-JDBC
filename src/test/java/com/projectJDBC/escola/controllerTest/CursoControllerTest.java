package com.projectJDBC.escola.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.projectJDBC.escola.controller.CursoController;
import com.projectJDBC.escola.model.Curso;
import com.projectJDBC.escola.service.CursoService;

public class CursoControllerTest {

	private CursoController cursoController;
    private CursoService cursoService;

    @BeforeEach
    public void setUp() {
        cursoService = mock(CursoService.class);
        cursoController = new CursoController(cursoService);
    }
    
    @Test
    public void testFindCurso() {
        Curso cursoTeste = new Curso();
        cursoTeste.setCodigo(1);
        cursoTeste.setNome("Curso de Teste");
        Curso curso = new Curso();
        curso.setCodigo(2);
        curso.setNome("Curso Teste 2");
        List<Curso> list = new ArrayList<>();
        list.add(cursoTeste);
        list.add(curso);

        when(cursoService.findCursos()).thenReturn(list);
        ResponseEntity<List<Curso>> response = cursoController.findCurso();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(list, response.getBody());
    }

    @Test
    public void testCadastrarCurso() {
        Curso cursoTeste = new Curso();
        cursoTeste.setNome("Novo Curso");
        cursoTeste.setDescricao("Descrição do Novo Curso");
        cursoTeste.setDuracao(120); 

        doNothing().when(cursoService).cadastrarCurso(cursoTeste);
        ResponseEntity<String> response = cursoController.cadastrarCurso(cursoTeste);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
    }
    
    @Test
    public void testExcluirCurso() {
    	int codigoCurso = 1;
    	
    	ResponseEntity<String> response = cursoController.excluirCurso(codigoCurso);
    	doNothing().when(cursoService).excluirCurso(codigoCurso);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Curso excluído com sucesso!", response.getBody());
    }
    
    @Test
    public void testAtualizarCurso() {
        Curso cursoTeste = new Curso();
        cursoTeste.setCodigo(1);
        cursoTeste.setNome("Curso de Teste Atualizado");

        doNothing().when(cursoService).atualizarCurso(cursoTeste);
        ResponseEntity<String> response = cursoController.atualizarCurso(cursoTeste);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Curso atualizado com sucesso!", response.getBody());
    }
    
    
    
}
