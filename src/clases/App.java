package clases;

import java.util.ArrayList;
import java.util.List;

public class App {
    // lista de todas las obras
    private ArrayList<Obra> obras;

    // constructor para iniciarlizar la aplicacion y la lista de obras
    public App() {
        this.obras = new ArrayList<>();
    }

    // getters y setters
    public ArrayList<Obra> getObras() {
        return obras;
    }

    public void setObras(ArrayList<Obra> obras) {
        this.obras = obras;
    }

    public void agregarObra(Obra obra) {
        if (obra != null) {
            this.obras.add(obra);
        }
    }
}
