package clases.handlers;

import clases.Material;
import clases.Obra;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


public class MaterialHandler<T extends Material> {
private List<T> listaMateriales;

    public MaterialHandler() {
        this.listaMateriales = new ArrayList<>();
    }

    public List<T> getListaMateriales() {
        return listaMateriales;
    }

    public void setListaMateriales(List<T> listaMateriales) {
        this.listaMateriales = listaMateriales;
    }

    // Agregar material
    public void agregarMaterial(T material) {
        listaMateriales.add(material);
    }
    // Mostrar un material
    public void mostrarMaterial(T material) {
        System.out.println("---------------------------");
        System.out.println("Nombre: " + material.getNombre());
        System.out.println("Unidad: " + material.getUnidadMedida());
        System.out.println("Precio unitario: $" + material.getPrecioUnitario());
        System.out.println("Cantidad estimada total: " + material.getCantidadEstimadaTotal());
        System.out.println("Cantidad acopiada en obra: " + material.getCantidadAcopiadaObra());
        System.out.println("Cantidad en proveedor: " + material.getCantidadEnProveedor());
        System.out.println("Cantidad consumida: " + material.getCantidadConsumida());
        System.out.println("Cantidad faltante por comprar: " + material.getCantidadFaltanteComprar());
        System.out.println("---------------------------\n");
    }

    // Mostrar todos los materiales
    public void mostrarMateriales() {
        if (listaMateriales.isEmpty()) {
            System.out.println("No hay materiales registrados en la obra.\n");
            return;
        }
        System.out.println("===== LISTA DE MATERIALES =====");
        for (T material : listaMateriales) {
            mostrarMaterial(material);
        }
    }
    // Buscar material por nombre
    public T buscarMaterialPorNombre(String nombre) {
        for (T material : listaMateriales) {
            if (material.getNombre().equalsIgnoreCase(nombre)) {
                return material;
            }
        }
        System.out.println("No existe el material con el nombre: " + nombre);
        return null;
    }

    // Eliminar material por nombre
    public void eliminarPorNombre(String nombre) {
        ListIterator<T> iterator = listaMateriales.listIterator();
        boolean found = false;
        while (iterator.hasNext()){
            if (iterator.next().getNombre().equalsIgnoreCase(nombre)){
                found=true;
                iterator.remove();
            }
        }
        if (found){
            System.out.println("Se encontro y elimino el material:" + nombre);
        }
        else {
            System.out.println("No se encontro o no se pudo eliminar el material:" + nombre);
        }
    }







}
