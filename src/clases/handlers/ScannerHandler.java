package clases.handlers;

import clases.CertificadoAvance;
import clases.Material;
import clases.Obra;
import clases.exceptions.materialExceptions.CantidadExcedidaException;
import clases.exceptions.materialExceptions.MaterialInexistenteException;
import clases.tiposMaterial.MaterialAcabado;
import clases.tiposMaterial.MaterialElectrico;
import clases.tiposMaterial.MaterialEstructural;
import clases.tiposMaterial.MaterialFontaneria;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
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

                System.out.println("\n===== DATOS ACTUALES: =====");
                System.out.println(material);
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
                boolean correcto = false;

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
                        while (!correcto){
                            try{
                                System.out.print("Nuevo precio unitario: ");
                                material.setPrecioUnitario(scanner.nextDouble());
                                scanner.nextLine();
                                correcto = true;
                            }catch (InputMismatchException e){
                                System.out.println("Precio invalido. Intente nuevamente.");
                                scanner.nextLine();
                            }
                        }
                        break;
                    case 4:
                        while (!correcto){
                            try{
                                System.out.print("Nueva cantidad estimada total: ");
                                material.setCantidadEstimadaTotal(scanner.nextDouble());
                                scanner.nextLine();
                                correcto = true;
                            }catch (InputMismatchException e){
                                System.out.println("Cantidad invalida. Intente nuevamente.");
                                scanner.nextLine();
                            }
                        }
                        break;
                    case 5:
                        while (!correcto) {
                            try {
                                System.out.print("Nueva cantidad acopiada en obra: ");
                                material.setCantidadAcopiadaObra(scanner.nextDouble());
                                scanner.nextLine();
                                correcto = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Cantidad invalida. Intente nuevamente.");
                                scanner.nextLine();
                            } catch (CantidadExcedidaException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;

                    case 6:
                        while (!correcto) {
                            try {
                                System.out.print("Nueva cantidad en proveedor: ");
                                material.setCantidadEnProveedor(scanner.nextDouble());
                                scanner.nextLine();
                                correcto = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Cantidad invalida. Intente nuevamente.");
                                scanner.nextLine();
                            } catch (CantidadExcedidaException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;

                    case 7:
                        while (!correcto) {
                            try {
                                System.out.print("Nueva cantidad consumida: ");
                                material.setCantidadConsumida(scanner.nextDouble());
                                scanner.nextLine();
                                correcto = true;
                            } catch (InputMismatchException e) {
                                System.out.println("Cantidad invalida. Intente nuevamente.");
                                scanner.nextLine();
                            } catch (CantidadExcedidaException e) {
                                System.out.println("Error: " + e.getMessage());
                            }
                        }
                        break;

                    case 0:
                        System.out.println("Volviendo al menú anterior...");
                        break;

                    default:
                        System.out.println("Opción inválida. Intente nuevamente.");
                }
            } while (opcion != 0);

            System.out.println("Cambios guardados correctamente.\n");
        }
    }

    public static Material crearMaterial() {
        Scanner scanner = new Scanner(System.in);
        boolean correcto = false;
        double precioUnitario = 0;
        double cantTotal = 0;
        double cantObra = 0;
        double cantProv = 0;
        double cantCons = 0;

        System.out.println("\n===== CREAR NUEVO MATERIAL =====");

        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();

        System.out.print("Unidad de medida (kg, m3, unidades, etc.): ");
        String unidadMedida = scanner.nextLine();

        while (!correcto){
            try{
                System.out.print("Precio unitario: ");
                precioUnitario = scanner.nextDouble();
                scanner.nextLine();
                correcto = true;
            }catch (InputMismatchException e){
                System.out.println("Precio invalido. Intente nuevamente");
                scanner.nextLine();
            }
        }

        correcto = false;
        while (!correcto) {
            try {
                System.out.print("Cantidad estimada total: ");
                cantTotal = scanner.nextDouble();
                scanner.nextLine();
                correcto = true;
            } catch (InputMismatchException e) {
                System.out.println("Cantidad invalida. Intente nuevamente");
                scanner.nextLine();
            }
        }

        correcto = false;
        while (!correcto) {
            try {
                System.out.print("Cantidad acopiada en obra: ");
                cantObra = scanner.nextDouble();
                scanner.nextLine();

                // Validar la suma parcial antes de continuar
                if (cantObra > cantTotal) {
                    throw new CantidadExcedidaException(
                            "La cantidad acopiada no puede superar la cantidad estimada total."
                    );
                }

                correcto = true;

            } catch (InputMismatchException e) {
                System.out.println("Cantidad invalida. Intente nuevamente");
                scanner.nextLine();

            } catch (CantidadExcedidaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        correcto = false;
        while (!correcto) {
            try {
                System.out.print("Cantidad en proveedor: ");
                cantProv = scanner.nextDouble();
                scanner.nextLine();

                if (cantObra + cantProv > cantTotal) {
                    throw new CantidadExcedidaException(
                            "La suma de acopiada + proveedor supera la cantidad estimada total."
                    );
                }

                correcto = true;

            } catch (InputMismatchException e) {
                System.out.println("Cantidad invalida. Intente nuevamente");
                scanner.nextLine();

            } catch (CantidadExcedidaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        correcto = false;
        while (!correcto) {
            try {
                System.out.print("Cantidad consumida: ");
                cantCons = scanner.nextDouble();
                scanner.nextLine();

                if (cantObra + cantProv + cantCons > cantTotal) {
                    throw new CantidadExcedidaException(
                            "La suma de acopiada + proveedor + consumida supera la cantidad estimada total."
                    );
                }

                correcto = true;

            } catch (InputMismatchException e) {
                System.out.println("Cantidad invalida. Intente nuevamente");
                scanner.nextLine();

            } catch (CantidadExcedidaException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }


        System.out.println("\nSeleccione el tipo de material:");
        System.out.println("1. Fontanería");
        System.out.println("2. Estructural");
        System.out.println("3. Eléctrico");
        System.out.println("4. Acabado");
        System.out.print("Opción: ");
        int opcion = scanner.nextInt();
        scanner.nextLine();

        Material nuevoMaterial = null;

        switch (opcion) {
            case 1:
                nuevoMaterial = new MaterialFontaneria(nombre, unidadMedida, precioUnitario, cantTotal, cantObra, cantProv, cantCons, "MaterialFontaneria");
                break;
            case 2:
                nuevoMaterial = new MaterialEstructural(nombre, unidadMedida, precioUnitario, cantTotal, cantObra, cantProv, cantCons, "MaterialEstructural");
                break;
            case 3:
                nuevoMaterial = new MaterialElectrico(nombre, unidadMedida, precioUnitario, cantTotal, cantObra, cantProv, cantCons, "MaterialElectrico");
                break;
                case 4:
                nuevoMaterial = new MaterialAcabado(nombre, unidadMedida, precioUnitario, cantTotal, cantObra, cantProv, cantCons, "MaterialAcabado");
                break;
            default:
                System.out.println("Opción inválida. No se creó el material.");
                return null;
        }

        System.out.println("\nEl material " + nuevoMaterial.getNombre() + " fue creado correctamente.");

        return nuevoMaterial;
    }

    // Agregar certificado
    public static CertificadoAvance crearCertificado(Obra o){
        if (o.getMateriales().getListaMateriales().isEmpty()){
            System.out.println("Error, No hay Materiales Cargados en el Sistema.");
            return null;
        }

        Scanner scanner = new Scanner(System.in);
        char opcion='n';
        boolean correcto = false;
        LocalDate fecha = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        double porcentajeAvance = 0;
        double montoCertificado = 0;
        double cantidadConsumidaMaterial = 0;

        System.out.println("\n===== CREAR NUEVO CERTIFICADO =====");

        while(fecha == null){
            try{
                System.out.println("Fecha (dd/mm/yyyy): ");
                String input = scanner.nextLine();
                fecha = LocalDate.parse(input, formatter);
            }catch(DateTimeParseException e){
                System.out.println("Fecha invalida, por favor intente nuevamente.");
            }
        }

        while (!correcto){
            try{
                System.out.println("Porcentaje de avance: ");
                porcentajeAvance  = scanner.nextDouble();
                scanner.nextLine();
                correcto = true;
            } catch(InputMismatchException e){
                System.out.println("El dato introducido no es un numero valido, intente otra vez.");
                scanner.nextLine();
            }
        }

        correcto = false;
        while (!correcto){
            try{
                System.out.println("Monto Certificado: ");
                montoCertificado = scanner.nextDouble();
                scanner.nextLine();
                correcto = true;
            }catch(InputMismatchException e){
                System.out.println("El dato introducido no es un numero valido, intente otra vez.");
                scanner.nextLine();
            }
        }

        List<Material> materialesUtilizados = new ArrayList<>();
        do {
            correcto = false;
            System.out.println("Ingrese el nombre del material utilizado: ");
            for (Material m : o.getMateriales().getListaMateriales()){
                System.out.println("- " + m.getNombre());
            }
            String nombreMaterial = scanner.nextLine();
            Material m = o.getMateriales().buscarMaterialPorNombre(nombreMaterial);

            if(m != null){


                while (!correcto){
                    try{
                        System.out.println("Ingrese la cantidad de material consumida: ");
                        cantidadConsumidaMaterial = scanner.nextDouble();
                        scanner.nextLine();
                        correcto = true;
                    }catch(InputMismatchException e){
                        System.out.println("Cantidad invalida. Intente nuevamente.");
                        scanner.nextLine();
                    }
                }

                if (m.getCantidadAcopiadaObra()>=cantidadConsumidaMaterial) {
                    m.consumirMaterial(cantidadConsumidaMaterial);

                    Material matCertificado = new MaterialAcabado(
                            m.getNombre(),
                            m.getUnidadMedida(),
                            m.getPrecioUnitario(),
                            0,
                            0,
                            0,
                            cantidadConsumidaMaterial,
                            "MaterialAcabado"
                    );

                    // Se agrega la copia simplificada al certificado
                    materialesUtilizados.add(matCertificado);
                }
                else {
                    System.out.println("Error, La cantidad que se consumio es mayor a la acopiada");
                }
            }
            else {
                throw new MaterialInexistenteException("Material"+ nombreMaterial + "no encontrado en la obra");
            }

            do {
                System.out.println("Desear cargar otro material? (s/n)");
                opcion = scanner.nextLine().charAt(0);
            } while (opcion != 'n' && opcion != 's');

        }while (opcion != 'n');

        System.out.println("Descripcion Trabajo: ");
        String descripcionTrabajo = scanner.nextLine();

        CertificadoAvance certificado = new CertificadoAvance(fecha, porcentajeAvance, montoCertificado,descripcionTrabajo);
        certificado.setMateriales(materialesUtilizados);

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
