package dad.javafx.ahorcado.mvc;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class RootModel {

	private ListProperty<String> lista = new SimpleListProperty<String>(this, "lista", FXCollections.observableArrayList());
	private ListProperty<Usuario> puntuaciones = new SimpleListProperty<Usuario>(this, "puntuaciones", FXCollections.observableArrayList());
	
	public final ListProperty<String> listaProperty() {
		return this.lista;
	}
	
	public final ObservableList<String> getLista() {
		return this.listaProperty().get();
	}
	
	public final void setLista(final ObservableList<String> lista) {
		this.listaProperty().set(lista);
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
