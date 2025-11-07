package clases.handlers;

import clases.CertificadoAvance;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.time.format.DateTimeFormatter;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;

public class CertificadoHandler {
    private List<CertificadoAvance> certificados;

    // Constructor
    public CertificadoHandler() {
        this.certificados = new ArrayList<>();
    }

    // Getters y Setters
    public List<CertificadoAvance> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<CertificadoAvance> certificados) {
        this.certificados = certificados;
    }

    // Agregar un certificado.
    public void agregarCertificado (CertificadoAvance certificado){
        this.certificados.add(certificado);
    }

    // Mostrar un certificado.
    public void mostrarCertificado (CertificadoAvance certificado){
        System.out.println("---------------------------");
        System.out.println("Fecha: " + certificado.getFecha());
        System.out.println("Porcentaje de avance: " + certificado.getPorcentajeAvance() + "%");
        System.out.println("Monto certificado: $" + certificado.getMontoCertificado());
        System.out.println("Descripcion de trabajo: " + certificado.getDescripcionTrabajo());
        System.out.println("---------------------------\n");
    }

    // Mostrar TODOS los certificados de la obra.
    public void mostrarCertificados(){
        if (certificados.isEmpty()){
            System.out.println("No hay certificados registrados en esta obra. \n");
            return;
        }
        System.out.println("===== LISTA DE MATERIALES =====");
        for (CertificadoAvance c: certificados) {
            mostrarCertificado(c);
        }
    }





}
