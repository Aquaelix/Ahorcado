package dad.javafx.ahorcado.mvc.palabras;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;

public class PalabrasController implements Initializable{

	private PalabrasModel model = new PalabrasModel();
	
	@FXML
	private BorderPane palabrasView;

	@FXML
	private Button addButton;

	@FXML
	private Button removeButton;

	@FXML
	private ListView<String> palabrasList;

	@FXML
	void onAddButton(ActionEvent event) {
		
		TextInputDialog idInput = new TextInputDialog();
		idInput.setTitle("Palabra a añadir");
		idInput.setHeaderText("¿Cuál es la nueva palabra?.");
		idInput.setContentText("Palabra: ");
		
		Optional<String> respuesta = idInput.showAndWait();
		if(respuesta.isPresent()) {
			if(respuesta.get().length()>0) {
				respuesta.ifPresent((string) ->  model.getPalabras().add(string));
			}
		}
	}

	@FXML
	void onRemoveAction(ActionEvent event) {
		
		if(model.getSeleccionada() != null) {
			model.getPalabras().remove(model.getSeleccionada());
		}else if(model.getPalabras().size() != 0) {
			model.getPalabras().remove(model.getPalabras().size()-1);
		}else {
			Alert alerta = new Alert(AlertType.ERROR);
			alerta.setTitle("Error");
			alerta.setHeaderText("Ha surgido un problema...");
			alerta.setContentText("La lista está vacia");
			alerta.showAndWait();
		}
	}

	public BorderPane getView() {
		return palabrasView;
	}

	public PalabrasModel getModel() {
		return model;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		palabrasList.itemsProperty().bind(model.palabrasProperty());
		
		model.seleccionadaProperty().bind(palabrasList.getSelectionModel().selectedItemProperty());
		
	}
	
	public PalabrasController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PalabrasView.fxml"));
		loader.setController(this);
		loader.load();
	
	}
	
}
