package clases;

import java.time.LocalDate;

public class CertificadoAvance { // documento de avance de obra
    private static int cantCertificados = 1;

    private int id;
    private LocalDate fecha;
    private double porcentajeAvance;
    private double montoCertificado;
    private String descripcionTrabajo;

    public CertificadoAvance() {
    }

    public CertificadoAvance(LocalDate fecha, double porcentajeAvance, double montoCertificado, String descripcionTrabajo) {
        this.id = cantCertificados++;
        this.fecha = fecha;
        this.porcentajeAvance = porcentajeAvance;
        this.montoCertificado = montoCertificado;
        this.descripcionTrabajo = descripcionTrabajo;
    }

    public static int getCantCertificados() {
        return cantCertificados;
    }

    public static void setCantCertificados(int cantCertificados) {
        CertificadoAvance.cantCertificados = cantCertificados;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public double getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(double porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public double getMontoCertificado() {
        return montoCertificado;
    }

    public void setMontoCertificado(double montoCertificado) {
        this.montoCertificado = montoCertificado;
    }

    public String getDescripcionTrabajo() {
        return descripcionTrabajo;
    }

    public void setDescripcionTrabajo(String descripcionTrabajo) {
        this.descripcionTrabajo = descripcionTrabajo;
    }



    @Override
    public String toString() {
        return "Certificado { id=" + id +
                ", avance=" + porcentajeAvance + "%" +
                ", monto=$" + montoCertificado +
                ", descripcion='" + descripcionTrabajo + "' }";
    }
}
