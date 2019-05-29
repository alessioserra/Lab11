package it.polito.tdp.bar.model;

import java.time.LocalTime;

public class Evento implements Comparable<Evento>{
	
	//Tipologie di evento che possono accadere
	public enum TipoEvento{
		
		ARRIVO, //Il gruppo di clienti arriva al bar
		TAVOLO_LIBERO, //Il gruppo di clienti trova un tavolo libero
		BANCONE, //Gruppo di clienti si accontenta di andare al bancone
		USCITA, //Il cliente se ne va dal bar (Contento o scontento)
		
	}
	
	private LocalTime ora ; // Timestamp dell'evento
	private TipoEvento tipo ; // Tipologia d evento
	private GruppoClienti gruppoClienti ; //Gruppo di clienti coinvolti nell'evento
	
	//Costruttore
	public Evento(LocalTime ora, TipoEvento tipo, GruppoClienti gruppoClienti) {
		this.ora = ora;
		this.tipo = tipo;
		this.gruppoClienti = gruppoClienti;
	}

	//Getters
	public LocalTime getOra() {
		return ora;
	}

	public TipoEvento getTipo() {
		return tipo;
	}

	public GruppoClienti getGruppoClienti() {
		return gruppoClienti;
	}

	@Override
	public String toString() {
		return ora+" - "+tipo+" - "+gruppoClienti;
	}

	@Override
	public int compareTo(Evento o) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
