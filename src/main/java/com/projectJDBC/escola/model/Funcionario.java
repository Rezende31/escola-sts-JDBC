package com.projectJDBC.escola.model;

import java.sql.Date;

public class Funcionario {
	
	private int codigo;
    private String nome;
    private String cpf;
    private Date nascimento;
    private String cargo;
    private Date admissao;
    private boolean status;
    
    
	public Funcionario() {
		super();
	}

	public Funcionario(int codigo, String nome, String cpf, Date nascimento, String cargo, Date admissao,
			boolean status) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.cargo = cargo;
		this.admissao = admissao;
		this.status = status;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getNascimento() {
		return nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

	public Date getAdmissao() {
		return admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
    
}
