package com.projectJDBC.escola.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectJDBC.escola.exception.TurmaExistenteException;
import com.projectJDBC.escola.model.Funcionario;
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
    
    public void salvarTurma(Turma turma) {
    	if (turmaRepository.turmaCurso(turma.getCurso().getCodigo())) {
            throw new TurmaExistenteException("Está turma já está atrelada a um curso.");
        }
        turmaRepository.salvarTurma(turma);
    }
    
    public void atualizarTurma(Turma turma) {
        turmaRepository.atualizarTurma(turma);
    }
    
    public void deletarTurmaPorCodigo(int codigo) {
        turmaRepository.deletarTurmaPorCodigo(codigo);
    }
    
    public List<Funcionario> listarParticipantesPorCodigoTurma(int codigoTurma) {
        return turmaRepository.listarParticipantesPorCodigoTurma(codigoTurma);
    }
    
    public void adicionarParticipante(int codigoTurma, int codigoFuncionario) {
        turmaRepository.adicionarParticipante(codigoTurma, codigoFuncionario);
    }
    
    public void excluirParticipante(int codigoTurma, int codigoFuncionario) {
        turmaRepository.excluirParticipante(codigoTurma, codigoFuncionario);
    }
	
}
