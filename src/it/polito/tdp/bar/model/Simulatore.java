package it.polito.tdp.bar.model;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

import it.polito.tdp.bar.model.Evento.TipoEvento;

public class Simulatore {

	    //Creo Coda degli eventi
		private PriorityQueue<Evento> queue = new PriorityQueue<>() ;
		
		// Modello del Mondo
		private List<GruppoClienti> clienti;
		private List<Tavolo> tavoli;
		
		// Parametri di simulazione (Che l'utente può modificare in ogni momento)
        private int numeroClienti = 2000; //numero di gruppi che arrivan
		
		// Statistiche da calcolare
		private int numero_totale_clienti;
		private int numero_clienti_soddisfatti;
		private int numero_clienti_insoddisfatti;
		
		//Getter per le statistiche
		public int getClientiTot() {
			return numero_totale_clienti;
		}
		
		public int getSoddisfatti() {
			return numero_clienti_soddisfatti;
		}
		
		public int getInsoddisfatti() {
			return numero_clienti_insoddisfatti;
		}
		 
		// Variabili interne
		private LocalTime T_inizio = LocalTime.of(8, 0);
		
		//Costruttore
		public Simulatore() {
			this.clienti = new ArrayList<>();
			this.tavoli=new ArrayList<>();
		}

		//Inizializzazione della simulazione
		public void init() {
			
			LocalTime oraArrivo = T_inizio;
			
			//Pulisco dalla simulazione precedente
			if (!clienti.isEmpty()) clienti.clear();
			
			//Creo tutti i clienti
			for (int i=0; i<numeroClienti;i++) {
				GruppoClienti c = new GruppoClienti(i,oraArrivo);
				clienti.add(c);
			}

			//Ogni cliente arriva dopo un tempo compreso nell'intervallo [1,10] min
			oraArrivo = oraArrivo.plus(Duration.ofMinutes((long)(Math.random()*10)));
			
			if (!tavoli.isEmpty()) tavoli.clear();
			//Creo tavoli liberi
			tavoli.add(new Tavolo(10));
			tavoli.add(new Tavolo(10));		
			tavoli.add(new Tavolo(8));
			tavoli.add(new Tavolo(8));
			tavoli.add(new Tavolo(8));
			tavoli.add(new Tavolo(8));		
			tavoli.add(new Tavolo(6));
			tavoli.add(new Tavolo(6));
			tavoli.add(new Tavolo(6));
			tavoli.add(new Tavolo(6));			
			tavoli.add(new Tavolo(4));
			tavoli.add(new Tavolo(4));
			tavoli.add(new Tavolo(4));
			tavoli.add(new Tavolo(4));
			tavoli.add(new Tavolo(4));
			
			//Creare coda di eventi iniziali
			queue.clear();
			
			for (GruppoClienti c : clienti) {
				
				queue.add(new Evento(c.getOraArrivo(), TipoEvento.ARRIVO, c));
			}
			
			//Resettare le statistiche
			this.numero_totale_clienti = 0;
			this.numero_clienti_soddisfatti = 0;
			this.numero_clienti_insoddisfatti = 0;
		}
		
		//Eseguo simulazione
		public void run() {
			
			while (!queue.isEmpty()) {
				
				//Estraggo evento dalla coda
				Evento ev = queue.poll();
				
				//Acquisisco variabili fuori dallo switch
				GruppoClienti c = ev.getGruppoClienti(); 
				int numPersone = c.getNumeroClienti();
				
				//Stampo eventi nella console
				System.out.println(ev.toString());
				
				
				//Gestisco i diversi casi
				switch (ev.getTipo()) {
					
				case ARRIVO:
					
					//Aggiorno numero clienti totali
				    numero_totale_clienti = numero_totale_clienti + numPersone;
					
					Tavolo tavolo=null;
					
					//Verifico se ci sono tavoli liberi
					for (Tavolo t : tavoli) {
						if (numPersone >= t.getNumeroPosti()/2 ) tavolo=t;
					}
					
					//Tavolo libero
					if (tavolo!=null) {
						c.setTavolo(tavolo);
					    queue.add(new Evento(ev.getOra(),TipoEvento.TAVOLO_LIBERO,c));
					    tavoli.remove(tavolo); //Rimuovo il tavolo dalla lista dei tavoli liberi
					    }
					//Tavolo occupato ma vanno al bancone
					else if (tavolo==null && c.getTolleranza()>0.45) {
						queue.add(new Evento(ev.getOra(),TipoEvento.BANCONE,c));
					}
					//Tavolo occupato e vanno via -> Insoddisfatti
					else {
						queue.add(new Evento(ev.getOra(),TipoEvento.USCITA,c));
						numero_clienti_insoddisfatti = numero_clienti_insoddisfatti + numPersone;
					}
					
					break;
					
				case TAVOLO_LIBERO:
					
					Random random = new Random();
					int permanenza = random.nextInt(60)+60;
					queue.add(new Evento(ev.getOra().plus(Duration.ofMinutes(permanenza)),TipoEvento.USCITA,c));
					//Cliente ha trovato il tavolo -> Soddisfatto
					numero_clienti_soddisfatti=numero_clienti_soddisfatti + numPersone; 
					break;
					
				case BANCONE:
					queue.add(new Evento(ev.getOra(),TipoEvento.USCITA,c));
					numero_clienti_soddisfatti = numero_clienti_soddisfatti + numPersone;
					break;
					
				case USCITA:
					
					if (c.getTavolo()!=null) {
						Tavolo tav = c.getTavolo();
						//Il tavolo torna libero
						tavoli.add(tav);
					    }		
					break;
				
				}
			
	        }
	}
}

