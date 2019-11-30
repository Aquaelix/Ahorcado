package dad.javafx.ahorcado.mvc;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PersistenciaPuntos {

	private static final File FICHERO = new File(System.getProperty("user.home"), "puntuaciones.txt");
	
	private static FileReader fr;
	private static BufferedReader br;
	
	private static FileWriter fw;
	
	public static void guardarDato(List<Usuario> dato) throws IOException {
		
		fw = new FileWriter(FICHERO);
		
		for(int i=0; i<dato.size(); i++) {
			fw.write(dato.get(i).getNombre()+":"+dato.get(i).getPuntos()+"\n");
		}
		fw.close();
	}
	
	public static List<Usuario> leerDato() throws IOException {
		
		List<Usuario> lista = new ArrayList<Usuario>();
		
		if(FICHERO.exists()) {
			fr = new FileReader(FICHERO);
			br  = new BufferedReader(fr);

			String linea;
	        String[] user;
	        
			while((linea=br.readLine())!=null) {
	        	user = linea.split(":");
	        	lista.add(new Usuario(user[0], Integer.valueOf(user[1])));
	        }
			fr.close();
			br.close();
			return lista;
		}
		
		
		return new ArrayList<>();
	}
}
