package dad.javafx.ahorcado.mvc.puntuaciones;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PuntuacionModel {

	private ListProperty<String> puntuaciones = new SimpleListProperty<String>(this, "puntuaciones", FXCollections.observableArrayList());

	public final ListProperty<String> puntuacionesProperty() {
		return this.puntuaciones;
	}
	

	public final ObservableList<String> getPuntuaciones() {
		return this.puntuacionesProperty().get();
	}
	

	public final void setPuntuaciones(final ObservableList<String> puntuaciones) {
		this.puntuacionesProperty().set(puntuaciones);
	}
	

}
