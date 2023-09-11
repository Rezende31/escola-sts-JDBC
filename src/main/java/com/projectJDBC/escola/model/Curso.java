package com.projectJDBC.escola.model;

public class Curso {
	
	private String nome;
	private String descricao;
	private Double duracao;
	
	public Curso() {
		super();
	}

	public Curso(String nome, String descricao, Double duracao) {
		super();
		this.nome = nome;
		this.descricao = descricao;
		this.duracao = duracao;
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

	public Double getDuracao() {
		return duracao;
	}

	public void setDuracao(Double duracao) {
		this.duracao = duracao;
	}

}
