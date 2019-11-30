package dad.javafx.ahorcado.mvc.palabras;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PalabrasModel {

	private ListProperty<String> palabras = new SimpleListProperty<String>(this, "palabras", FXCollections.observableArrayList());

	private StringProperty seleccionada = new SimpleStringProperty();
	 
	public final ListProperty<String> palabrasProperty() {
		return this.palabras;
	}
	
	public final ObservableList<String> getPalabras() {
		return this.palabrasProperty().get();
	}
	
	public final void setPalabras(final ObservableList<String> palabras) {
		this.palabrasProperty().set(palabras);
	}

	public final StringProperty seleccionadaProperty() {
		return this.seleccionada;
	}
	
	public final String getSeleccionada() {
		return this.seleccionadaProperty().get();
	}

	public final void setSeleccionada(final String seleccionada) {
		this.seleccionadaProperty().set(seleccionada);
	}		
	
}
