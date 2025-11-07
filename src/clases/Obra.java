package clases;

import clases.handlers.CertificadoHandler;
import clases.handlers.MaterialHandler;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Obra {
    private String nombre;
    private String descripcion;
    private String ubicacion;
    private MaterialHandler<Material> materiales;
    private CertificadoHandler certificados;

    // constructor
    public Obra() {
        this.materiales = new MaterialHandler<>();

        this.certificados = new CertificadoHandler();
    }

    // getters y setters


    public MaterialHandler<Material> getMateriales() {
        return materiales;
    }

    public void setMateriales(MaterialHandler<Material> materiales) {
        this.materiales = materiales;
    }

    public CertificadoHandler getCertificados() {
        return certificados;
    }
    public void setCertificados(CertificadoHandler certificados) {
        this.certificados = certificados;
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

    }





