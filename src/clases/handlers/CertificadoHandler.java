package clases.handlers;

import clases.CertificadoAvance;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CertificadoHandler {
    private List<CertificadoAvance> certificados;

    public CertificadoHandler() {
        this.certificados = new ArrayList<>();
    }

    public List<CertificadoAvance> getCertificados() {
        return certificados;
    }

    public void setCertificados(List<CertificadoAvance> certificados) {
        this.certificados = certificados;
    }

    // Agregar certificado
    public CertificadoAvance crearCertificado(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n===== CREAR NUEVO CERTIFICADO =====");

        System.out.println("Fecha: ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());

        System.out.println("Porcentaje de avance: ");
        double porcentajeAvance  = scanner.nextDouble();

        System.out.println("Monto Certificado: ");
        double montoCertificado = scanner.nextDouble();

        System.out.println("Descripcion Trabajo: ");
        String descripcionTrabajo = scanner.nextLine();

        CertificadoAvance certificado = new CertificadoAvance();
        certificado.setFecha(fecha);
        certificado.setPorcentajeAvance(porcentajeAvance);
        certificado.setMontoCertificado(montoCertificado);
        certificado.setDescripcionTrabajo(descripcionTrabajo);

        return certificado;
    }


}
