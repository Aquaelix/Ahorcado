package dad.javafx.ahorcado.mvc;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dad.javafx.ahorcado.mvc.palabras.PalabrasController;
import dad.javafx.ahorcado.mvc.partidas.PartidasController;
import dad.javafx.ahorcado.mvc.puntuaciones.PuntuacionController;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class RootController implements Initializable{

	private PartidasController partController = new PartidasController();
	
	private PuntuacionController puntController = new PuntuacionController();
	
	private PalabrasController palController = new PalabrasController();
	
	private RootModel model = new RootModel();
	
	@FXML
	private TabPane view;

	@FXML
	private Tab partidaTab;

	@FXML
	private Tab palabrasTab;

	@FXML
	private Tab puntuacionesTab;

	public TabPane getView() {
		return view;
	}

	public RootModel getModel() {
		return model;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		palabrasTab.setContent(palController.getView());
		puntuacionesTab.setContent(puntController.getView());
		partidaTab.setContent(partController.getView());
		Bindings.bindBidirectional(palController.getModel().palabrasProperty(), model.listaProperty());
		Bindings.bindBidirectional(partController.getModel().puntuacionesProperty(), model.puntuacionesProperty());
		
		partController.getModel().puntuacionesProperty().addListener( (o, ov, nv) -> onActualizarLista());

		view.sceneProperty().addListener((o, ov, nv) -> nuevoJuego() ); 
		
	}
	
	private void onActualizarLista() {
		puntController.getModel().getPuntuaciones().clear();
		puntController.getModel().getPuntuaciones().addAll(toStringList(model.getPuntuaciones()));
	}

	private void nuevoJuego() {
	
		partController.getModel().getPalabras().setAll(palController.getModel().getPalabras());
		partController.getModel().setLetrasErradas("");
		partController.nuevaPalabra();
		
		puntController.getModel().getPuntuaciones().addAll(toStringList(model.getPuntuaciones()));
	
	}

	public RootController(List<String> lista, List<Usuario> puntos) throws IOException {
		
		model.getLista().addAll(lista);
		model.getPuntuaciones().addAll(puntos);
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/RootView.fxml"));
		loader.setController(this);
		loader.load();
		
	}
	
	private List<String> toStringList(List<Usuario> usuarios) {
		
		List<String> lista = new ArrayList<String>();
			
		int i=0;
        
		while(i<usuarios.size()) {
        	lista.add(usuarios.get(i).getNombre()+":"+usuarios.get(i).getPuntos());
        	i++;
        }
		
		return lista;
	}
	
}
