package dad.javafx.ahorcado.mvc;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

private RootController controller;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		controller = new RootController(Persistencia.leerDato(), PersistenciaPuntos.leerDato());
		
		Scene scene = new Scene(controller.getView());
		
		primaryStage.setTitle("AhorcadoFXML");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	@Override
	public void stop() throws Exception {
		super.stop();
		Persistencia.guardarDato(controller.getModel().getLista());
		PersistenciaPuntos.guardarDato(controller.getModel().getPuntuaciones());
	}

	public static void main(String[] args) {
		launch(args);
	}
}
