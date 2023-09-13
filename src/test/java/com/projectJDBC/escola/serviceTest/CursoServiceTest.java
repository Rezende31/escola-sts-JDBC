package com.projectJDBC.escola.serviceTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.projectJDBC.escola.model.Curso;
import com.projectJDBC.escola.model.Turma;
import com.projectJDBC.escola.repository.CursoRepository;
import com.projectJDBC.escola.repository.TurmaRepository;
import com.projectJDBC.escola.service.CursoService;

public class CursoServiceTest {
	
	private CursoService cursoService;
    private CursoRepository cursoRepository;
    private TurmaRepository turmaRepository;

    @BeforeEach
    public void setUp() {
        cursoRepository = mock(CursoRepository.class);
        turmaRepository = mock(TurmaRepository.class);
        cursoService = new CursoService(cursoRepository, turmaRepository);
    }

    @Test
    public void testFindCursos() {
        List<Curso> cursosTeste = new ArrayList<>();
        cursosTeste.add(new Curso());
        cursosTeste.add(new Curso());

        when(cursoRepository.listarCursos()).thenReturn(cursosTeste);
        List<Curso> cursosEncontrados = cursoService.findCursos();

        assertEquals(cursosTeste, cursosEncontrados);
    }
    
    @Test
    public void testCadastrarCurso() {
    	Curso curso = new Curso();
    	curso.setCodigo(1);
    	curso.setNome("Nome teste");
    	
    	doNothing().when(cursoRepository).salvarCurso(curso);
    	cursoService.cadastrarCurso(curso);
    	verify(cursoRepository).salvarCurso(curso);
    }
    
    @Test
    public void testExcluirCurso() {
    	int codigoCurso = 1;
    	List<Turma> turmasAssociadas = new ArrayList<>();
        turmasAssociadas.add(new Turma(codigoCurso, null, null, "", null));
        when(turmaRepository.buscarTurmasPorCurso(codigoCurso)).thenReturn(turmasAssociadas);
        cursoService.excluirCurso(codigoCurso);
        for (Turma turma : turmasAssociadas) {
            verify(turmaRepository).deletarTurmaPorCodigo(turma.getCodigo());
        }
        verify(cursoRepository).excluirCurso(codigoCurso);
    }

}
