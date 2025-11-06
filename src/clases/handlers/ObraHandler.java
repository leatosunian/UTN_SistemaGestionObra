package clases.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import clases.Obra;

public class ObraHandler {

    public Obra crearObra() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = sc.nextLine().trim();
        System.out.print("Descripcion: ");
        String descripcion = sc.nextLine().trim();
        System.out.print("Ubicacion: ");
        String ubicacion = sc.nextLine().trim();

        Obra obra = new Obra();
        obra.setNombre(nombre);
        obra.setDescripcion(descripcion);
        obra.setUbicacion(ubicacion);

        obra.setMateriales(new MaterialHandler<>());
        obra.setCertificados(new ArrayList<>());


        return obra;
    }

    public Obra buscarPorNombre(List<Obra> listaObras, String nombre) {
        if (listaObras == null || nombre == null) return null;
        for (Obra obra : listaObras) {
            if (obra != null && nombre.equals(obra.getNombre())) {
                return obra;
            }
        }
        return null;
    }

    public boolean eliminarPorNombre(List<Obra> listaObras, String nombre) {
        Obra found = buscarPorNombre(listaObras, nombre);
        if (found == null) return false;
        return listaObras.remove(found);
    }
}