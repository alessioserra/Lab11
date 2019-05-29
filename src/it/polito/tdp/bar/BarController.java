package it.polito.tdp.bar;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.bar.model.Simulatore;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class BarController {
	
	private Simulatore simulatore;
	
	public void setSimulatore(Simulatore simulatore) {
		this.simulatore=simulatore;
	}

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea txtResult;

    @FXML
    private Button handleSimula;

    @FXML
    void doSimula(ActionEvent event) {
    	
    	txtResult.clear();
    	
    	simulatore.init();
    	simulatore.run();
    	
    	txtResult.appendText("Numero clienti TOTALI = "+simulatore.getClientiTot()+"\n");
    	txtResult.appendText("Numero clienti SODDISFATTI = "+simulatore.getSoddisfatti()+"\n");
    	txtResult.appendText("Numero clienti INSODDISFATTI = "+simulatore.getInsoddisfatti()+"\n");

    }

    @FXML
    void initialize() {
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Bar.fxml'.";
        assert handleSimula != null : "fx:id=\"handleSimula\" was not injected: check your FXML file 'Bar.fxml'.";

    }
}
