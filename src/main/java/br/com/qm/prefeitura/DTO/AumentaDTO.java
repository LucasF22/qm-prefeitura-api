package br.com.qm.prefeitura.DTO;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class AumentaDTO {
	
	@NotNull
	@Min(value = 1)
	private double valorAumenta;

	public AumentaDTO(double valorAumenta) {
		super();
		this.valorAumenta = valorAumenta;
	}
	
	/**
	 * @param valorAumenta
	 */
	public AumentaDTO() {
		
	}

	public double getValorAumenta() {
		return valorAumenta;
	}

	public void setValorAumenta(double valorAumenta) {
		this.valorAumenta = valorAumenta;
	}
}
