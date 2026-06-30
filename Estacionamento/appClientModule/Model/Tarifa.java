package Model;

public class Tarifa {
	    private int idtarifa;
	    private String nome;
	    private double valorHora;
		
	    public Tarifa(int idtarifa, String nome, double valorHora) {
			this.idtarifa = idtarifa;
			this.nome = nome;
			this.valorHora = valorHora;
		}

		public int getIdtarifa() {
			return idtarifa;
		}

		public void setIdtarifa(int idtarifa) {
			this.idtarifa = idtarifa;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public double getValorHora() {
			return valorHora;
		}

		public void setValorHora(double valorHora) {
			this.valorHora = valorHora;
		}
	    
	    
	    
}
