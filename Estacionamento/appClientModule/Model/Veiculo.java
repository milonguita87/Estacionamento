package Model;

import java.time.LocalDateTime;

public class Veiculo {
	private int idveiculo;
	private String placa;
	private LocalDateTime hora_entrada;
	private LocalDateTime hora_saida;
	
	
	public Veiculo(int idveiculo, String placa, LocalDateTime hora_entrada, LocalDateTime hora_saida) {
		this.idveiculo = idveiculo;
		this.placa = placa;
		this.hora_entrada = hora_entrada;
		this.hora_saida = hora_saida;
	}


	public int getIdveiculo() {
		return idveiculo;
	}


	public void setIdveiculo(int idveiculo) {
		this.idveiculo = idveiculo;
	}


	public String getPlaca() {
		return placa;
	}


	public void setPlaca(String placa) {
		this.placa = placa;
	}


	public LocalDateTime getHora_entrada() {
		return hora_entrada;
	}


	public void setHora_entrada(LocalDateTime hora_entrada) {
		this.hora_entrada = hora_entrada;
	}


	public LocalDateTime getHora_saida() {
		return hora_saida;
	}


	public void setHora_saida(LocalDateTime hora_saida) {
		this.hora_saida = hora_saida;
	}
	
	
}
