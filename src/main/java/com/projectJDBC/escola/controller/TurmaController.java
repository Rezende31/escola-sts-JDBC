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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectJDBC.escola.model.Funcionario;
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
	public ResponseEntity<List<Turma>> buscarTurmasPorCurso(@PathVariable int codigoCurso) {
		return ResponseEntity.ok().body(turmaService.buscarTurmasPorCurso(codigoCurso));
	}

	@PostMapping("/incluir")
	public ResponseEntity<String> incluirTurma(@RequestBody Turma turma) {
		turmaService.salvarTurma(turma);
		return ResponseEntity.ok("Turma cadastrada a um curso com sucesso");
	}

	@PutMapping("/atualizar")
	public ResponseEntity<String> atualizarTurma(@RequestBody Turma turma) {
		turmaService.atualizarTurma(turma);
		return ResponseEntity.ok("Turma atualizada com sucesso!");
	}

	@DeleteMapping("/deletar/{codigo}")
	public ResponseEntity<String> deletarTurma(@PathVariable int codigo) {
		turmaService.deletarTurmaPorCodigo(codigo);
		return ResponseEntity.ok("Turma deletada com sucesso!");
	}

	@GetMapping("/listarParticipantes/{codigoTurma}")
	public List<Funcionario> listarParticipantes(@PathVariable int codigoTurma) {
		return turmaService.listarParticipantesPorCodigoTurma(codigoTurma);
	}

	@PostMapping("/adicionarParticipante")
	public ResponseEntity<String> adicionarParticipante(@RequestParam int codigoTurma,
			@RequestParam int codigoFuncionario) {
		turmaService.adicionarParticipante(codigoTurma, codigoFuncionario);
		return ResponseEntity.ok("Participante adicionado com sucesso à turma!");
	}

	@DeleteMapping("/excluirParticipante")
	public ResponseEntity<String> excluirParticipante(@RequestParam int codigoTurma,
			@RequestParam int codigoFuncionario) {
		turmaService.excluirParticipante(codigoTurma, codigoFuncionario);
		return ResponseEntity.ok("Participante excluído com sucesso da turma!");
	}

}
