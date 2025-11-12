import clases.App;
import clases.CertificadoAvance;
import clases.ManejoJSON.JSONUtiles;
import clases.ManejoJSON.manejoJSON;
import clases.Material;
import clases.Obra;
import clases.exceptions.obraException.ObraInexistenteException;
import clases.handlers.CertificadoHandler;
import clases.handlers.MaterialHandler;
import clases.handlers.ScannerHandler;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        App app = manejoJSON.mapeoApp();


        // inicializar el handler de materiales
        MaterialHandler<Material> materialHandler = new MaterialHandler<>();
        CertificadoHandler certificadoHandler  = new CertificadoHandler();

        Scanner scanner = new Scanner(System.in);
        int opcionPrincipal;

        // menu principal
        System.out.println("\n===== SISTEMA DE GESTIÓN DE OBRA ===== \n");

        do {

            System.out.println("===== MENÚ PRINCIPAL =====");
            System.out.println("1. Ingresar como Administrador");
            System.out.println("2. Ingresar como Usuario de Obra");
            System.out.println("3. Ingresar como Inversor");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcionPrincipal = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (opcionPrincipal) {
                case 1:
                    menuAdministrador(scanner, materialHandler, app, certificadoHandler);

                    break;
                case 2:
                    menuUsuarioObra(scanner);
                    break;
                case 3:
                    menuInversor(scanner);
                    break;
                case 0:
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente nuevamente.\n");
            }
        } while (opcionPrincipal != 0);

        scanner.close();
    }

    // menu rol administrador
    public static void menuAdministrador(Scanner scanner, MaterialHandler<Material> materialHandler, App app, CertificadoHandler certificadoHandler) {
        int opcion;
        do {
            System.out.println("===== MENÚ ADMINISTRADOR =====");
            System.out.println("1. Nueva obra");
            System.out.println("2. Eliminar obra");
            System.out.println("3. Cargar material");
            System.out.println("4. Eliminar material");
            System.out.println("5. Editar material");
            System.out.println("6. Crear certificado de avance");
            System.out.println("7. Consultar certificados de avance");
            System.out.println("8. Exportar datos de obra en JSON");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (opcion) {
                case 1:
                    // creacion de obra
                    System.out.println("→ Crear nueva obra...\n");

                    Obra nuevaObra = ScannerHandler.crearObra();

                    if (nuevaObra !=  null) {

                        app.agregarObra(nuevaObra);
                        System.out.println("Obra '" + nuevaObra.getNombre() + "' creada correctamente.\n");
                    } else {
                        throw new ObraInexistenteException("Error al crear la obra.\n");
                    }

                    break;
                case 2:
                    // eliminacion de obra
                    System.out.println("→ Eliminar obra...\n");
                    System.out.print("Ingrese el nombre de la obra a eliminar: ");
                    app.eliminarPorNombre(scanner.nextLine().trim());

                    break;
                case 3:
                    // agregar material a obra
                    System.out.println("→ Agregar material a obra...\n");
                    // buscar obra para agregar material
                    System.out.print("Ingrese el nombre de la obra: ");

                    Obra obra = app.buscarPorNombre(scanner.nextLine().trim());

                    // si la obra no existe, mostrar mensaje de error
                    if (obra == null) {
                        throw new ObraInexistenteException("La obra introducida no existe.\n");
                    } else {
                        // crear nuevo material
                        Material nuevoMaterial = ScannerHandler.crearMaterial();

                        // agregar material a la obra
                        obra.getMateriales().agregarMaterial(nuevoMaterial);

                        System.out.println("Material '" + nuevoMaterial.getNombre() + "' agregado a la obra '" + obra.getNombre() + "'.\n");
                    }

                    break;
                case 4:
                    // eliminar material de obra
                    System.out.println("→ Eliminar material de obra...\n");
                    break;
                case 5:
                    // editar material de obra
                    System.out.println("→ Editar material de obra...\n");
                    System.out.print("Ingrese el nombre de la obra: ");

                    Obra o = app.buscarPorNombre(scanner.nextLine().trim());

                    // si la obra no existe, mostrar mensaje de error
                    if (o == null) {
                        throw new ObraInexistenteException("La obra introducida no existe.\n");
                    } else {
                        System.out.println("Ingrese el nombre del material a buscar");

                        ScannerHandler.editarMaterial(o.getMateriales().buscarMaterialPorNombre(scanner.nextLine().trim()));
                        o.getMateriales().mostrarMateriales();
                        break;
                    }
                case 6:
                    // crear certificado de avance
                    System.out.println("→ Crear certificado de avance...\n");
                    // buscar obra existente
                    System.out.print("Ingrese el nombre de la obra: ");

                    Obra obra1 = app.buscarPorNombre(scanner.nextLine().trim());

                    // si la obra no existe, mostrar mensaje de error
                    if (obra1 == null) {
                        throw new ObraInexistenteException("La obra introducida no existe.\n");
                    } else {
                        // crear nuevo certificado de avance
                        CertificadoAvance certif = ScannerHandler.crearCertificado(obra1);

                        // agregar certificado a la obra
                        obra1.getCertificados().agregarCertificado(certif);



                        System.out.println("Certificado agregado a la obra '" + obra1.getNombre() + "'.\n");
                    }
                    break;

                case 7:
                    // consultar certificados de avance
                System.out.println("→ Consultar certificados de avance...\n");
                // buscar obra existente
                System.out.print("Ingrese el nombre de la obra: ");

                Obra obra2 = app.buscarPorNombre(scanner.nextLine().trim());

                // si la obra no existe, mostrar mensaje de error
                if (obra2 == null) {
                    throw new ObraInexistenteException("La obra introducida no existe.\n");
                } else {
                    obra2.getCertificados().mostrarCertificados();
                }
                break;
                case 8:
                    // exportar datos de obra en JSON
                            manejoJSON.guardarApp(app);
                    System.out.println("→ Exportar datos de obra en JSON...\n");
                    break;
                case 0:
                    // volver al menú principal
                    System.out.println("Volviendo al menú principal...\n");
                    break;
                default:
                    System.out.println("Opción inválida. Ingresa una opcion correcta.\n");
            }
        } while (opcion != 0);

    }

    // menu rol usuario de obra
    public static void menuUsuarioObra(Scanner scanner) {
        int opcion;
        do {
            System.out.println("===== MENÚ USUARIO DE OBRA =====");
            System.out.println("1. Cargar material");
            System.out.println("2. Eliminar material");
            System.out.println("3. Editar material");
            System.out.println("4. Consultar certificados de avance");
            System.out.println("5. Crear certificado de avance");
            System.out.println("6. Exportar certificado de avance en JSON");
            System.out.println("7. Exportar datos de obra en JSON");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (opcion) {
                case 1:
                    System.out.println("→ Cargando materiales...");
                    break;
                case 2:
                    System.out.println("→ Registrando proveedor...");
                    break;
                case 3:
                    System.out.println("→ Registrando compra...");
                    break;
                case 4:
                    System.out.println("→ Actualizando stock...");
                    break;
                case 5:
                    System.out.println("→ Emisión de certificado de avance...");
                    break;
                case 6:
                    System.out.println("→ Consultar reportes...");
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...\n");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.\n");
            }
        } while (opcion != 0);
    }

    // menu rol inversor
    public static void menuInversor(Scanner scanner) {
        int opcion;
        do {
            System.out.println("===== MENÚ INVERSOR =====");
            System.out.println("1. Consultar certificados de avance");
            System.out.println("2. Exportar certificado de avance en JSON");
            System.out.println("3. Exportar datos de obra en JSON");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (opcion) {
                case 1:
                    System.out.println("→ Consultar certificados de avance...");
                    // buscar obra por nombre y listar sus certificados de avance
                    break;
                case 2:
                    // exportar certificado de avance en JSON
                    System.out.println("→ Exportar certificado de avance en JSON...\n");
                    break;
                case 3:
                    // exportar datos de obra en JSON
                    System.out.println("→ Exportar datos de obra en JSON...\n");
                    break;
                case 0:
                    System.out.println("Volviendo al menú principal...\n");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.\n");
            }
        } while (opcion != 0);
    }


}
