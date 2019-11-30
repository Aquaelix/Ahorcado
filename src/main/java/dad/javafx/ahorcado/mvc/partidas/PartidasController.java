package dad.javafx.ahorcado.mvc.partidas;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.javafx.ahorcado.mvc.Usuario;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class PartidasController implements Initializable {

	private PartidasModel model = new PartidasModel();
	
	private Image[] imagenes = {
			new Image(getClass().getResource("/hangman/9.png").toString()),	
			new Image(getClass().getResource("/hangman/8.png").toString()),	
			new Image(getClass().getResource("/hangman/7.png").toString()),	
			new Image(getClass().getResource("/hangman/6.png").toString()),	
			new Image(getClass().getResource("/hangman/5.png").toString()),	
			new Image(getClass().getResource("/hangman/4.png").toString()),	
			new Image(getClass().getResource("/hangman/3.png").toString()),	
			new Image(getClass().getResource("/hangman/2.png").toString()),
			new Image(getClass().getResource("/hangman/1.png").toString()),	
	};
	
	@FXML
	private BorderPane view;

	@FXML
	private TextField insertarField;

	@FXML
	private Button letraButton;

	@FXML
	private Button resolverButton;

	@FXML
	private ImageView imageView;

	@FXML
	private Label palabraLabel;

	@FXML
	private Label letrasLabel;

	@FXML
	private Label puntosLabel;

	@FXML
	void onLetraAction(ActionEvent event) {
		
		if(model.getLetras().length()!=1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("No hay letra");
			alert.setContentText("No has insertado una letra");
			alert.setTitle("Error");
			alert.showAndWait();
		}else {
			
			int aciertos = 0;
			char letra = model.getLetras().charAt(0);
			String palabra = model.getPalabra();
			char[] tempCharArray = model.getOculta().toCharArray();
			String erradas = model.getLetrasErradas();
			boolean nuevaLetra = true;
			
			for(int i=0; i<erradas.length(); i++) {
				if(erradas.charAt(i)==letra) {
					nuevaLetra = false;
				}
			}
			
			if(nuevaLetra) {
				
				for(int i=0; i<palabra.length(); i++) {
					if(letra == palabra.charAt(i)) {
						tempCharArray[i] = letra;
						aciertos++;
					}
				}
				
				if(aciertos==0) {
						Alert alert = new Alert(AlertType.ERROR);
						alert.setHeaderText("No has encontrado una letra");
						alert.setContentText("Suerte con tu próximo intento");
						alert.setTitle("Mala suerte");
						alert.showAndWait();
						
						model.setLetrasErradas(model.getLetrasErradas().concat(" "+letra));
						model.setVidas(model.getVidas()-1);
						cambioImagen();
					
				}else {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setHeaderText("Has encontrado una letra");
					alert.setContentText("Ya estás más cerca el final");
					alert.setTitle("¡Qué suerte!");
					alert.showAndWait();
					model.setOculta(String.valueOf(tempCharArray));
					model.setPuntos(model.getPuntos()+aciertos);
				}
			}else {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setHeaderText("Ya has puesto esta letra");
				alert.setContentText("Prueba una letra diferente esta vez");
				alert.setTitle("¡Qué descuido!");
				alert.showAndWait();
			}
		}
	}

	@FXML
	void onResolverAction(ActionEvent event) {

		if(model.getLetras().equals(model.getPalabra())) {

			Alert alert=new Alert(AlertType.CONFIRMATION);
			alert.setTitle("¡Felicidades!");
			alert.setHeaderText("Palabra acertada");
			alert.setContentText("¡Has acertado la palabra!");
			alert.showAndWait();
			
			int puntos = 0;
			
			for(int i=0; i<model.getOculta().length(); i++) {
				if(model.getOculta().charAt(i) == '_') {
					puntos++;
				}
			}
			
			model.setPuntos(model.getPuntos()+puntos);
			
			nuevoJuego();
			
		}else {
			
			Alert alert=new Alert(AlertType.ERROR);
			alert.setTitle("¡Buen intento!");
			alert.setHeaderText("Pero... has fallado");
			alert.setContentText("Pierdes puntos");
			alert.showAndWait();
			
			model.setPuntos(model.getPuntos()-model.getLetras().length());
			model.setVidas(model.getVidas()-1);
			cambioImagen();
		}
		
	}

	public void nuevoJuego() {
		
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Ahorcado");
		alert.setHeaderText("¿Nueva partida?");
		alert.setContentText("¿Desea una nueva palabra?");
		
		Optional<ButtonType> result = alert.showAndWait();
		
		if(result.get() == ButtonType.OK) {
			nuevaPalabra();
		
		}else {
			ingresarUsuario();
		}
		
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
		letrasLabel.textProperty().bind(model.letrasErradasProperty());
		palabraLabel.textProperty().bind(model.ocultaProperty());
		model.letrasProperty().bind(insertarField.textProperty());
		
		puntosLabel.textProperty().bind(model.puntosProperty().asString());
	
		model.setVidas(imagenes.length-1);
		model.setLetrasErradas("");
	}

	public void nuevaPalabra() {
		
		char[] palabra;
		String oculta = "";
		model.setLetrasErradas("");
		
		try {
			palabraRandom();
			palabra = model.getPalabra().toCharArray();
			
			model.setOculta("");
			
			for(int i=0; i<palabra.length; i++) {
				if(palabra[i] != ' ') {
					oculta+="_";
				}else {
					oculta+=" ";
				}
			model.setOculta(oculta);
			}
			
		} catch (SinPalabrasException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Sin palabras");
			alert.setContentText("No quedan palabras en la lista");
			alert.setTitle("Error");
			alert.showAndWait();
			
			ingresarUsuario();
		}
		
	}
	
	private void palabraRandom() throws SinPalabrasException {
		System.out.println(model.getPalabras().size());
		if(model.getPalabras().size()==0)
			throw new SinPalabrasException("No hay palabras en la lista");
		
		String palabra;
		int random = (int) (Math.random() * (model.getPalabras().size()));

		palabra = model.getPalabras().get(random);	
		model.getPalabras().remove(random);

		model.setPalabra(palabra);
		
	}
	
	public PartidasController() throws IOException {
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/PartidasView.fxml"));
		loader.setController(this);
		loader.load();
	
	}

	private void cambioImagen() {
		
		if(model.getVidas() == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Sin vidas");
			alert.setContentText("No te quedan más vidas");
			alert.setTitle("Ups...");
			alert.showAndWait();
			
			ingresarUsuario();
			
		}else
			imageView.setImage(imagenes[model.getVidas()-1]);
		
	}
	
	private void ingresarUsuario() {
		
		TextInputDialog usuario = new TextInputDialog();
		usuario.setTitle("¿Usuario...?");
		usuario.setHeaderText("¿Cuál es tu nombre de usuario?.");
		usuario.setContentText("Nombre: ");
		
		Optional<String> respuesta = usuario.showAndWait();
		if(respuesta.isPresent()) {
			respuesta.ifPresent((string) ->  model.getPuntuaciones().add(new Usuario(string, model.getPuntos())));
			FXCollections.sort(model.getPuntuaciones());	
		}

	}

	public BorderPane getView() {
		return view;
	}
	
	public PartidasModel getModel() {
		return model;
	}
}
