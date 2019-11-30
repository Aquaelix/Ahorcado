package dad.javafx.ahorcado.mvc;

public class Usuario implements Comparable<Usuario> {

	private String nombre;
	private int puntos;
	
	public Usuario(String nombre, int puntos) {
		this.nombre = nombre;
		this.puntos = puntos;
	}
	
	@Override
	public int compareTo(Usuario o) {
		if(puntos < o.puntos)
			return 1;
		if(puntos > o.puntos)
			return -1;
		return 0;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", puntos=" + puntos + "]";
	}

}
