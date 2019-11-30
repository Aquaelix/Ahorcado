package dad.javafx.ahorcado.mvc;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class Persistencia {

	private static final File FICHERO = new File(System.getProperty("user.home"), "listaPalabras.txt");
	
	public static void guardarDato(List<String> dato) throws IOException {
		Files.write(FICHERO.toPath(), dato);
	}
	
	public static List<String> leerDato() throws IOException {
		if(FICHERO.exists())
			return Files.readAllLines(FICHERO.toPath());
		return new ArrayList<>();
	}
}
