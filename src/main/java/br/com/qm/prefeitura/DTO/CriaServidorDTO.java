package br.com.qm.prefeitura.DTO;

import javax.persistence.Column;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.br.CPF;
import org.springframework.beans.BeanUtils;

import com.sun.istack.NotNull;

import br.com.qm.prefeitura.entity.Servidor;

public class CriaServidorDTO {
	@NotBlank
	private String nome;
	@CPF
	@Column(unique = true)
	private String cpf;
	@NotNull
	@Min(value = 1045)
	private double salario;
	@NotBlank
	private String cargo;

	public CriaServidorDTO(@NotBlank String nome, @CPF String cpf, @Min(1045) double salario, @NotBlank String cargo) {
		super();
		this.nome = nome;
		this.cpf = cpf;
		this.salario = salario;
		this.cargo = cargo;
	}
	
	public Servidor toEntity() {
		Servidor servidor = new Servidor();
		BeanUtils.copyProperties(this, servidor);
		
		return servidor;
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

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public String getCargo() {
		return cargo;
	}

	public void setCargo(String cargo) {
		this.cargo = cargo;
	}

}
