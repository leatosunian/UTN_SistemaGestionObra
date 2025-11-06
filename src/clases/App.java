package clases;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

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

    public Obra buscarPorNombre(String nombre) {
        if (obras == null || nombre == null) return null;
        for (Obra obra : obras) {
            if (obra != null && nombre.equalsIgnoreCase(obra.getNombre())) {
                return obra;
            }
        }
        return null;
    }

    public void eliminarPorNombre(String nombre) {
        ListIterator<Obra> iterator = obras.listIterator();
        boolean found = false;
        while (iterator.hasNext()){
            if (iterator.next().getNombre().equalsIgnoreCase(nombre)){
                found=true;
                iterator.remove();
            }
        }
        if (found){
            System.out.println("Se encontro y elimino la obra:" + nombre);
        }
        else {
            System.out.println("No se encontro o no se pudo eliminar la obra:" + nombre);
        }

    }
}
