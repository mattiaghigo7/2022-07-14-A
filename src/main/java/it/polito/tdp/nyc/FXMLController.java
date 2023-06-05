package it.polito.tdp.nyc;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.nyc.model.Arco;
import it.polito.tdp.nyc.model.Model;
import it.polito.tdp.nyc.model.NTA;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="btnAdiacenti"
    private Button btnAdiacenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaGrafo"
    private Button btnCreaGrafo; // Value injected by FXMLLoader

    @FXML // fx:id="btnCreaLista"
    private Button btnCreaLista; // Value injected by FXMLLoader

    @FXML // fx:id="clPeso"
    private TableColumn<?, ?> clPeso; // Value injected by FXMLLoader

    @FXML // fx:id="clV1"
    private TableColumn<?, ?> clV1; // Value injected by FXMLLoader

    @FXML // fx:id="clV2"
    private TableColumn<?, ?> clV2; // Value injected by FXMLLoader

    @FXML // fx:id="cmbBorough"
    private ComboBox<String> cmbBorough; // Value injected by FXMLLoader

    @FXML // fx:id="tblArchi"
    private TableView<?> tblArchi; // Value injected by FXMLLoader

    @FXML // fx:id="txtDurata"
    private TextField txtDurata; // Value injected by FXMLLoader

    @FXML // fx:id="txtProb"
    private TextField txtProb; // Value injected by FXMLLoader

    @FXML // fx:id="txtResult"
    private TextArea txtResult; // Value injected by FXMLLoader

    @FXML
    void doAnalisiArchi(ActionEvent event) {
    	List<Arco> result = model.analisiArchi();
    	this.txtResult.setText("PESO MEDIO: "+model.getPesoMedio()+"\n");
    	this.txtResult.appendText("ARCHI CON PESO MAGGIORE DEL PESO MEDIO: "+result.size()+"\n");
    	for(Arco a : result) {
    		this.txtResult.appendText(a+"\n");
    	}
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {
    	String input = this.cmbBorough.getValue();
    	if(input==null) {
    		this.txtResult.setText("Selezionare un borgo!");
    	}
    	model.creaGrafo(input);
    	this.txtResult.setText("Grafo creato!\n");
    	this.txtResult.appendText("Ci sono "+this.model.getVertici()+" vertici.\n");
    	this.txtResult.appendText("Ci sono "+this.model.getArchi()+" archi.\n");
    }

    @FXML
    void doSimula(ActionEvent event) {
    	double prob = Double.parseDouble(this.txtProb.getText());
    	int duration = Integer.parseInt(this.txtDurata.getText());
    	Map<NTA,Integer> condivisioni = model.Simula(prob, duration);
    	for(NTA n : condivisioni.keySet()) {
    		this.txtResult.appendText(n.getNTACode()+" "+condivisioni.get(n)+"\n");
    	}
    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert btnAdiacenti != null : "fx:id=\"btnAdiacenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCreaLista != null : "fx:id=\"btnCreaLista\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clPeso != null : "fx:id=\"clPeso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clV1 != null : "fx:id=\"clV1\" was not injected: check your FXML file 'Scene.fxml'.";
        assert clV2 != null : "fx:id=\"clV2\" was not injected: check your FXML file 'Scene.fxml'.";
        assert cmbBorough != null : "fx:id=\"cmbBorough\" was not injected: check your FXML file 'Scene.fxml'.";
        assert tblArchi != null : "fx:id=\"tblArchi\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtDurata != null : "fx:id=\"txtDurata\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtProb != null : "fx:id=\"txtProb\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Scene.fxml'.";

        
    }
    
    public void setModel(Model model) {
    	this.model = model;
    	this.cmbBorough.getItems().addAll(model.getBorough());
    }

}
