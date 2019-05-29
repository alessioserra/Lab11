package it.polito.tdp.bar.model;

import java.time.LocalTime;
import java.util.Random;

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
	private Tavolo tavolo;
	
	//Ogni gruppo di clienti che creo è NEW (appena arrivato)
	public GruppoClienti(int id, LocalTime oraArrivo) {
		
		this.id=id;
		this.oraArrivo=oraArrivo;
		this.stato=StatoClienti.NEW;
		//numero casuale
		Random random = new Random();
		this.numeroClienti = random.nextInt(9)+1; //Numero di clienti tra 1 e 10
		this.tolleranza = random.nextFloat()-((float)(0.1)); //Tolleranza tra 0 e 0.9
		this.tavolo = null;
	}

	//Getters and Setters
	public int getNumeroClienti() {
		return numeroClienti;
	}
	
	public Tavolo getTavolo() {
		return tavolo;
	}

	public void setTavolo(Tavolo tavolo) {
		this.tavolo = tavolo;
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
