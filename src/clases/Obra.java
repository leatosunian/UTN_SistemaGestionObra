package clases;

import clases.handlers.MaterialHandler;

import java.util.ArrayList;
import java.util.List;

public class Obra {
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private MaterialHandler<Material> materiales;

    private List<CertificadoAvance> certificados;

    // constructor
    public Obra() {
        this.materiales = new MaterialHandler<>();

        this.certificados = new ArrayList<>();
    }

    // getters y setters


    public MaterialHandler<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(MaterialHandler<Material> materiales) {
        this.materiales = materiales;
    }

    public List<CertificadoAvance> getCertificados() {
        return certificados;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setCertificados(List<CertificadoAvance> certificados) {
        this.certificados = certificados;
    }




}
