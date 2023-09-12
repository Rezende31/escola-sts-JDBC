package com.projectJDBC.escola.model;

public class Curso {
	
	private int codigo;
	private String nome;
	private String descricao;
	private int duracao;
	
	public Curso() {
		super();
	}

	public Curso(int codigo, String nome, String descricao, int duracao) {
		super();
		this.codigo = codigo;
		this.nome = nome;
		this.descricao = descricao;
		this.duracao = duracao;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getDuracao() {
		return duracao;
	}

	public void setDuracao(int duracao) {
		this.duracao = duracao;
	}

}
