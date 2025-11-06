import clases.App;
import clases.Material;
import clases.Obra;
import clases.exceptions.obraException.ObraInexistenteException;
import clases.handlers.MaterialHandler;
import clases.handlers.ObraHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static App app;
    private static ArrayList<Obra> listaObras;

    public static void main(String[] args) {

        // iniciarlizar la aplicacion y la lista de obras
        app = new App();
        listaObras = app.getObras();

        // inicializar el handler de materiales
        MaterialHandler<Material> materialHandler = new MaterialHandler<>();
        ObraHandler obraHandler = new ObraHandler();

        Scanner scanner = new Scanner(System.in);
        int opcionPrincipal;

        System.out.println("\n===== SISTEMA DE GESTIÓN DE OBRA ===== \n");

        do {
            System.out.println("===== MENÚ PRINCIPAL =====");
            System.out.println("1. Ingresar como Administrador");
            System.out.println("2. Ingresar como Usuario de Obra");
            System.out.println("3. Ingresar como Inversor");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcionPrincipal = scanner.nextInt();
            System.out.println();

            switch (opcionPrincipal) {
                case 1:
                    menuAdministrador(scanner, materialHandler, obraHandler);
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

    // ================= MENÚ ADMINISTRADOR =================
    public static void menuAdministrador(Scanner scanner, MaterialHandler<Material> materialHandler, ObraHandler obraHandler) {
        int opcion;
        do {
            System.out.println("===== MENÚ ADMINISTRADOR =====");
            System.out.println("1. Nueva obra");
            System.out.println("2. Eliminar obra");
            System.out.println("3. Cargar material");
            System.out.println("4. Eliminar material");
            System.out.println("5. Editar material");
            System.out.println("6. Consultar certificados de avance");
            System.out.println("7. Crear certificado de avance");
            System.out.println("8. Exportar certificado de avance en JSON");
            System.out.println("9. Exportar datos de obra en JSON");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            System.out.println();

            switch (opcion) {
                case 1:
                    // creacion de obra
                    System.out.println("→ Crear nueva obra...\n");

                    Obra nuevaObra = obraHandler.crearObra();

                    if (nuevaObra != null) {
                        listaObras.add(nuevaObra);
                        app.setObras(listaObras);
                        System.out.println("Obra '" + nuevaObra.getNombre() + "' creada correctamente.\n");
                    } else {
                        throw new ObraInexistenteException("Error al crear la obra.\n");
                    }

                    break;
                case 2:
                    // eliminacion de obra
                    System.out.println("→ Eliminar obra...\n");
                    System.out.print("Ingrese el nombre de la obra a eliminar: ");
                    scanner.nextLine();
                    String nombreObraEliminar = scanner.nextLine().trim();
                    boolean eliminado = obraHandler.eliminarPorNombre(listaObras, nombreObraEliminar);
                    if (eliminado) {
                        app.setObras(listaObras);
                        System.out.println("Obra '" + nombreObraEliminar + "' eliminada correctamente.\n");
                    } else {
                        System.out.println("No se encontró una obra con el nombre '" + nombreObraEliminar + "'.\n");
                    }

                    break;
                case 3:
                    // agregar material a obra
                    System.out.println("→ Agregar material a obra...\n");
                    // buscar obra para agregar material
                    System.out.print("Ingrese el nombre de la obra: ");
                    scanner.nextLine();
                    String nombreObra = scanner.nextLine().trim();
                    Obra obra = obraHandler.buscarPorNombre(listaObras, nombreObra);

                    // si la obra no existe, mostrar mensaje de error
                    if (obra == null) {
                        throw new ObraInexistenteException("La obra '" + nombreObra + "' no existe.\n");
                    } else {
                        // crear nuevo material
                        Material nuevoMaterial = materialHandler.crearMaterial();

                        // agregar material a la obra
                        obra.agregarMaterial(nuevoMaterial);

                        // actualizar la lista de obras en la app
                        app.setObras(listaObras);

                        System.out.println("Material '" + nuevoMaterial.getNombre() + "' agregado a la obra '" + nombreObra + "'.\n");
                    }

                    break;
                case 4:
                    // eliminar material de obra
                    System.out.println("→ Eliminar material de obra...\n");
                    break;
                case 5:
                    // editar material de obra
                    System.out.println("→ Editar material de obra...\n");
                    Obra o = listaObras.getFirst();
                    MaterialHandler<Material> m = m.setListaMateriales(o.getMateriales());
                    for (Material ma : m){
                        System.out.println(ma.getNombre());
                    }
                    m.buscarMaterialPorNombre(scanner.nextLine().trim());

                    break;
                case 6:
                    // consultar certificados de avance
                    System.out.println("→ Consultar certificados de avance...\n");
                    break;
                case 7:
                    // crear certificado de avance
                    System.out.println("→ Crear certificado de avance...\n");

                    break;
                case 8:
                    // exportar certificado de avance en JSON
                    System.out.println("→ Exportar certificado de avance en JSON...\n");
                    break;
                case 9:
                    // exportar datos de obra en JSON
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

    // ================= MENÚ USUARIO DE OBRA =================
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

    // ================= MENÚ INVERSOR =================
    public static void menuInversor(Scanner scanner) {
        int opcion;
        do {
            System.out.println("===== MENÚ INVERSOR =====");
            System.out.println("1. Consultar certificados de avance");
            System.out.println("0. Volver al menú principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            System.out.println();

            switch (opcion) {
                case 1:
                    System.out.println("→ Consultar certificados de avance...");
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
