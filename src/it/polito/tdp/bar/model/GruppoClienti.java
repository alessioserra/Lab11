package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class GruppoClienti {
	
	//Stati in cui si possono trovare i vari gruppi di clienti
	public enum StatoClienti{
		NEW,
		SEDUTO,
		BANCONE,
		OUT,
	}
	
	private int id;
	private StatoClienti stato;
	private LocalTime oraArrivo;
	private int numeroClienti;
	private float tolleranza;
	
	//Ogni gruppo di clienti che creo è NEW (appena arrivato)
	public GruppoClienti(int id, LocalTime oraArrivo) {
		
		this.id=id;
		this.oraArrivo=oraArrivo;
		this.stato=StatoClienti.NEW;
	}

	//Getters and Setters
	public int getNumeroClienti() {
		return numeroClienti;
	}
	
	public float getTolleranza() {
		return tolleranza;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public StatoClienti getStato() {
		return stato;
	}

	public void setStato(StatoClienti stato) {
		this.stato = stato;
	}

	public LocalTime getOraArrivo() {
		return oraArrivo;
	}

	public void setOraArrivo(LocalTime oraArrivo) {
		this.oraArrivo = oraArrivo;
	}

	//ToString
	@Override
	public String toString() {
		return "[ID="+id+"] - "+oraArrivo+" NumeroClienti: "+numeroClienti+" STATO:"+stato;
	}
	
	

}
