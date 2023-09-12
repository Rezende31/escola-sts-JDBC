package com.projectJDBC.escola.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectJDBC.escola.model.Curso;
import com.projectJDBC.escola.service.CursoService;

@RestController
@RequestMapping(value = "/cursos")
public class CursoController {
	
	@Autowired
	private final CursoService cursoService;
	
	@Autowired
    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }
	
	@GetMapping()
	public ResponseEntity<List<Curso>> findCourse(){
		List<Curso> teste = cursoService.findCursos();
		return ResponseEntity.ok().body(teste);
	}
	
	@PostMapping("/cadastro")
    public ResponseEntity<String> cadastrarCurso(@RequestBody Curso curso) {
        cursoService.cadastrarCurso(curso);
        return ResponseEntity.ok("Curso cadastrado com sucesso!");
    }
	
	@DeleteMapping("/excluir/{codigo}")
    public ResponseEntity<String> excluirCurso(@PathVariable int codigo) {
        cursoService.excluirCurso(codigo);
        return ResponseEntity.ok("Curso excluído com sucesso!");
    }
	
	@PutMapping("/atualizar/{nome}")
    public ResponseEntity<String> atualizarCurso(@PathVariable String nome, @RequestBody Curso curso) {
		if (nome.equals(curso.getNome())) {
            return ResponseEntity.badRequest().body("O nome no caminho não corresponde ao nome no corpo da solicitação.");
        }
        cursoService.atualizarCurso(curso);
        return ResponseEntity.ok("Curso atualizado com sucesso!");
    }

}
