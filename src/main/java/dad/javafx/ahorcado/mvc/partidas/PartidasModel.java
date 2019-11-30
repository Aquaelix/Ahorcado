package dad.javafx.ahorcado.mvc.partidas;

import dad.javafx.ahorcado.mvc.Usuario;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PartidasModel {

	private StringProperty letras = new SimpleStringProperty();
	private StringProperty letrasErradas = new SimpleStringProperty();
	private StringProperty palabra = new SimpleStringProperty();
	private StringProperty oculta = new SimpleStringProperty();
	
	private IntegerProperty vidas = new SimpleIntegerProperty();
	private IntegerProperty puntos = new SimpleIntegerProperty();
	
	private ListProperty<String> palabras = new SimpleListProperty<String>(this, "palabras", FXCollections.observableArrayList());

	private ListProperty<Usuario> puntuaciones = new SimpleListProperty<Usuario>(this, "puntuaciones", FXCollections.observableArrayList());

	public final StringProperty letrasProperty() {
		return this.letras;
	}

	public final String getLetras() {
		return this.letrasProperty().get();
	}
	
	public final void setLetras(final String letras) {
		this.letrasProperty().set(letras);
	}
	
	public final StringProperty letrasErradasProperty() {
		return this.letrasErradas;
	}
	
	public final String getLetrasErradas() {
		return this.letrasErradasProperty().get();
	}

	public final void setLetrasErradas(final String letrasErradas) {
		this.letrasErradasProperty().set(letrasErradas);
	}

	public final StringProperty palabraProperty() {
		return this.palabra;
	}

	public final String getPalabra() {
		return this.palabraProperty().get();
	}	

	public final void setPalabra(final String palabra) {
		this.palabraProperty().set(palabra);
	}
	
	public final StringProperty ocultaProperty() {
		return this.oculta;
	}

	public final String getOculta() {
		return this.ocultaProperty().get();
	}
	
	public final void setOculta(final String oculta) {
		this.ocultaProperty().set(oculta);
	}
	
	public final IntegerProperty vidasProperty() {
		return this.vidas;
	}
	
	public final int getVidas() {
		return this.vidasProperty().get();
	}
	
	public final void setVidas(final int vidas) {
		this.vidasProperty().set(vidas);
	}

	public final IntegerProperty puntosProperty() {
		return this.puntos;
	}

	public final int getPuntos() {
		return this.puntosProperty().get();
	}	

	public final void setPuntos(final int puntos) {
		this.puntosProperty().set(puntos);
	}
	
	public final ListProperty<String> palabrasProperty() {
		return this.palabras;
	}
	
	public final ObservableList<String> getPalabras() {
		return this.palabrasProperty().get();
	}
	
	public final void setPalabras(final ObservableList<String> palabras) {
		this.palabrasProperty().set(palabras);
	}

	public final ListProperty<Usuario> puntuacionesProperty() {
		return this.puntuaciones;
	}
	
	public final ObservableList<Usuario> getPuntuaciones() {
		return this.puntuacionesProperty().get();
	}
	
	public final void setPuntuaciones(final ObservableList<Usuario> puntuaciones) {
		this.puntuacionesProperty().set(puntuaciones);
	}
		
}
