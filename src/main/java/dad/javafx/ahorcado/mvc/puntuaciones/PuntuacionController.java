package dad.javafx.ahorcado.mvc.puntuaciones;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

public class PuntuacionController implements Initializable {

	private PuntuacionModel model = new PuntuacionModel();
	
	@FXML
	private ListView<String> PuntuacionView;

	public ListView<String> getLista(){
		return PuntuacionView;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		PuntuacionView.itemsProperty().bind(model.puntuacionesProperty());
		
	}

	public PuntuacionController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PuntuacionView.fxml"));
		loader.setController(this);
		loader.load();

	}
	
	public PuntuacionModel getModel() {
		return model;
	}
	
	public ListView<String> getView(){
		return PuntuacionView;
	}
}
