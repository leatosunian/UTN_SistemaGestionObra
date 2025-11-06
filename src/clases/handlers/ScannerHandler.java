package clases.handlers;

import clases.CertificadoAvance;
import clases.Material;
import clases.Obra;
import clases.tiposMaterial.MaterialElectrico;
import clases.tiposMaterial.MaterialEstructural;
import clases.tiposMaterial.MaterialFontaneria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ScannerHandler {

    // Editar material por menú de opciones
    public static void editarMaterial(Material material) {
        Scanner scanner = new Scanner(System.in);

        if (material == null) {
            System.out.println("No existe un material con el nombre '" + material.getNombre() + "'.\n");
        }
        else {
            int opcion;
            do {
                System.out.println("\n===== EDITAR MATERIAL: " + material.getNombre() + " =====");
                System.out.println("1. Editar nombre");
                System.out.println("2. Editar unidad de medida");
                System.out.println("3. Editar precio unitario");
                System.out.println("4. Editar cantidad estimada total");
                System.out.println("5. Editar cantidad acopiada en obra");
                System.out.println("6. Editar cantidad en proveedor");
                System.out.println("7. Editar cantidad consumida");
                System.out.println("0. Volver");
                System.out.print("Seleccione una opción: ");
                opcion = scanner.nextInt();
                scanner.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1:
                        System.out.print("Nuevo nombre: ");
                        material.setNombre(scanner.nextLine());
                        break;
                    case 2:
                        System.out.print("Nueva unidad de medida: ");
                        material.setUnidadMedida(scanner.nextLine());
                        break;
                    case 3:
                        System.out.print("Nuevo precio unitario: ");
                        material.setPrecioUnitario(scanner.nextDouble());
                        break;
                    case 4:
                        System.out.print("Nueva cantidad estimada total: ");
                        material.setCantidadEstimadaTotal(scanner.nextDouble());
                        break;
                    case 5:
                        System.out.print("Nueva cantidad acopiada en obra: ");
                        material.setCantidadAcopiadaObra(scanner.nextDouble());
                        break;
                    case 6:
                        System.out.print("Nueva cantidad en proveedor: ");
                        material.setCantidadEnProveedor(scanner.nextDouble());
                        break;
                    case 7:
                        System.out.print("Nueva cantidad consumida: ");
                        material.setCantidadConsumida(scanner.nextDouble());
                        break;
                    case 0:
                        System.out.println("Volviendo al menú anterior...");
                        break;
                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } while (opcion != 0);

            System.out.println("Cambios guardados correctamente.\n");
            scanner.close();
        }
    }

    public static Material crearMaterial() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n===== CREAR NUEVO MATERIAL =====");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Unidad de medida (kg, m3, unidades, etc.): ");
        String unidadMedida = scanner.nextLine();

        System.out.print("Precio unitario: ");
        double precioUnitario = scanner.nextDouble();

        System.out.print("Cantidad estimada total: ");
        double cantTotal = scanner.nextDouble();

        System.out.print("Cantidad acopiada en obra: ");
        double cantObra = scanner.nextDouble();

        System.out.print("Cantidad en proveedor: ");
        double cantProv = scanner.nextDouble();

        System.out.print("Cantidad consumida: ");
        double cantCons = scanner.nextDouble();
        scanner.nextLine(); // limpiar buffer

        System.out.println("\nSeleccione el tipo de material:");
        System.out.println("1. Fontanería");
        System.out.println("2. Estructural");
        System.out.println("3. Eléctrico");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine(); // limpiar buffer


        Material nuevoMaterial = null;

        switch (opcion) {
            case 1:
                nuevoMaterial = new MaterialFontaneria(nombre, unidadMedida, precioUnitario, cantTotal, cantObra, cantProv, cantCons, "FONTANERIA");
                break;
            case 2:
                nuevoMaterial = new MaterialEstructural(nombre, unidadMedida, precioUnitario, cantTotal, cantObra, cantProv, cantCons, "ESTRUCTURAL");
                break;
            case 3:
                nuevoMaterial = new MaterialElectrico(nombre, unidadMedida, precioUnitario, cantTotal, cantObra, cantProv, cantCons, "ELECTRICO");
                break;
            default:
                System.out.println("Opción inválida. No se creó el material.");
                return null;
        }

        System.out.println("\nEl material " + nuevoMaterial.getNombre() + "fue creado correctamente.");
        scanner.close();
        return nuevoMaterial;
    }


    // Agregar certificado
    public static CertificadoAvance crearCertificado(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("\n===== CREAR NUEVO CERTIFICADO =====");

        System.out.println("Fecha (dd/mm/yyyy): ");
        String input = scanner.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate fecha = LocalDate.parse(input, formatter);

        System.out.println("Porcentaje de avance: ");
        double porcentajeAvance  = scanner.nextDouble();

        System.out.println("Monto Certificado: ");
        double montoCertificado = scanner.nextDouble();

        System.out.println("Descripcion Trabajo: ");
        scanner.nextLine();
        String descripcionTrabajo = scanner.nextLine();

        CertificadoAvance certificado = new CertificadoAvance();
        certificado.setFecha(fecha);
        certificado.setPorcentajeAvance(porcentajeAvance);
        certificado.setMontoCertificado(montoCertificado);
        certificado.setDescripcionTrabajo(descripcionTrabajo);

        return certificado;
    }

    public static Obra crearObra() {
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
        obra.setCertificados(new CertificadoHandler());

        return obra;
    }

}
