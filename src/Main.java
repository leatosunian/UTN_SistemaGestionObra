import clases.App;
import clases.CertificadoAvance;
import clases.Interfaces.Mantenible;
import clases.ManejoJSON.manejoJSON;
import clases.Material;
import clases.Obra;
import clases.exceptions.obraException.ObraInexistenteException;
import clases.handlers.CertificadoHandler;
import clases.handlers.MaterialHandler;
import clases.handlers.ScannerHandler;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        App app = manejoJSON.mapeoApp();

        // inicializar el handler de materiales
        MaterialHandler<Material> materialHandler = new MaterialHandler<>();
        CertificadoHandler certificadoHandler = new CertificadoHandler();

        Scanner scanner = new Scanner(System.in);
        int opcionPrincipal;

        // menu principal
        System.out.println("\n===== SISTEMA DE GESTIÓN DE OBRA ===== \n");

        do {
            System.out.println("\n--------------------------------------------");
            System.out.println("      🏗️  SISTEMA DE GESTIÓN DE OBRA");
            System.out.println("--------------------------------------------");
            System.out.println("              MENÚ PRINCIPAL");
            System.out.println("--------------------------------------------");
            System.out.println(" 1. 👨‍💼  Administrador");
            System.out.println(" 2. 👷  Usuario de Obra");
            System.out.println(" 3. 💰  Inversor");
            System.out.println(" 0. 🚪  Salir");
            System.out.println("--------------------------------------------");
            System.out.print("Seleccione una opción: ");

            opcionPrincipal = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (opcionPrincipal) {
                case 1:
                    // abre submenu administrador
                    menuAdministrador(scanner, materialHandler, app, certificadoHandler);
                    break;
                case 2:
                    // abre submenu usuario de obra
                    menuUsuarioObra(scanner, materialHandler, app, certificadoHandler);
                    break;
                case 3:
                    // abre submenu inversor
                    menuInversor(scanner, app);
                    break;
                case 0:
                    // finalizar ejecución
                    System.out.println("Saliendo del sistema...");
                    break;
                default:
                    // opción inválida
                    System.out.println("Opción no válida. Intente nuevamente.\n");
            }
        } while (opcionPrincipal != 0);

        scanner.close();
    }

    // menu rol administrador
    public static void menuAdministrador(Scanner scanner, MaterialHandler<Material> materialHandler, App app, CertificadoHandler certificadoHandler) {
        int opcion;
        do {
            System.out.println("\n--------------------------------------------");
            System.out.println("        👨‍💼  MENÚ ADMINISTRADOR");
            System.out.println("--------------------------------------------");
            System.out.println(" 1. 🏗️  Nueva obra");
            System.out.println(" 2. 🗑️  Eliminar obra");
            System.out.println(" 3. 📦  Cargar material");
            System.out.println(" 4. ❌  Eliminar material");
            System.out.println(" 5. ✏️  Editar material");
            System.out.println(" 6. 📄  Crear certificado de avance");
            System.out.println(" 7. 🔍  Consultar certificados de avance");
            System.out.println(" 0. 🚪  Volver al menú principal");
            System.out.println("--------------------------------------------");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (opcion) {
                case 1:
                    // creacion de obra
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Crear nueva obra...");
                    System.out.println("--------------------------------------------");

                    Obra nuevaObra = ScannerHandler.crearObra();

                    if (nuevaObra != null) {
                        app.agregarObra(nuevaObra);
                        System.out.println("Obra '" + nuevaObra.getNombre() + "' creada correctamente.\n");
                        manejoJSON.guardarApp(app);
                    } else {
                        throw new ObraInexistenteException("\nError al crear la obra.\n");
                    }
                    break;
                case 2:
                    // eliminacion de obra
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Eliminar obra...");
                    System.out.println("--------------------------------------------");

                    if (app.getObras().isEmpty()) {
                        throw new ObraInexistenteException("No hay obras cargadas en el programa.\n");
                    }
                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();
                    System.out.print("\nIngrese el nombre de la obra a eliminar: \n");

                    app.eliminarPorNombre(scanner.nextLine().trim());
                    manejoJSON.guardarApp(app);
                    break;
                case 3:
                    // agregar material a obra
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Agregar material a obra...");
                    System.out.println("--------------------------------------------");

                    if (app.getObras().isEmpty()) {
                        throw new ObraInexistenteException("No hay obras cargadas en el programa.\n");
                    }

                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();

                    System.out.println("\nIngrese el nombre de la obra:");
                    String nombreObra = scanner.nextLine().trim();

                    Obra obra = app.buscarPorNombre(nombreObra);

                    if (obra != null) {

                        Material nuevoMaterial = ScannerHandler.crearMaterial();


                        obra.getMateriales().agregarMaterial(nuevoMaterial);

                        System.out.println(" Material '" + nuevoMaterial.getNombre() +
                                "' agregado a la obra '" + obra.getNombre() + "'.\n");

                        manejoJSON.guardarApp(app);
                    } else {
                        System.out.println("No se encontró ninguna obra con ese nombre.\n");
                    }
                    break;

                case 4:
                    // eliminar material de obra
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Eliminar material de obra...");
                    System.out.println("--------------------------------------------");

                    if (app.getObras().isEmpty()) {
                        throw new ObraInexistenteException("No hay obras cargadas en el programa.\n");
                    }

                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();

                    System.out.print("\nIngrese el nombre de la obra:\n ");
                    Obra o1 = app.buscarPorNombre(scanner.nextLine().trim());

                    // si la obra no existe, mostrar mensaje de error
                    if (o1 == null) {
                        throw new ObraInexistenteException("La obra introducida no existe.\n");
                    } else {
                        System.out.println("Ingrese el nombre del material a eliminar");
                        o1.getMateriales().eliminarPorNombre(scanner.nextLine().trim());
                        o1.getMateriales().mostrarMateriales();
                        if (!o1.getMateriales().getListaMateriales().isEmpty()) {
                            manejoJSON.guardarApp(app);
                        }
                    }
                    break;
                case 5:
                    // editar material de obra
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Editar material de obra...");
                    System.out.println("--------------------------------------------");


                    if (app.getObras().isEmpty()) {
                        throw new ObraInexistenteException("No hay obras cargadas en el programa.\n");
                    }

                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();

                    System.out.print("\nIngrese el nombre de la obra:\n ");
                    Obra o = app.buscarPorNombre(scanner.nextLine().trim());

                    // si la obra no existe, mostrar mensaje de error
                    if (o == null) {
                        throw new ObraInexistenteException("La obra introducida no existe.\n");
                    } else {
                        System.out.println("Ingrese el nombre del material a editar");
                        for (Material material : o.getMateriales().getListaMateriales()) {
                            System.out.println(material.getNombre());
                        }
                        ScannerHandler.editarMaterial(o.getMateriales().buscarMaterialPorNombre(scanner.nextLine().trim()));
                        o.getMateriales().mostrarMateriales();
                        manejoJSON.guardarApp(app);
                    }
                    break;
                case 6:
                    // crear certificado de avance
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Crear certificado de avance...");
                    System.out.println("--------------------------------------------");

                    if (app.getObras().isEmpty()) {
                        throw new ObraInexistenteException("No hay obras cargadas en el programa.\n");
                    }

                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();

                    // buscar obra existente
                    System.out.print("\nIngrese el nombre de la obra:\n ");
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
                        if (certif != null) {
                            manejoJSON.guardarApp(app);
                        }
                    }
                    break;
                case 7:
                    // consultar certificados de avance
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Consultar certificados de avance...");
                    System.out.println("--------------------------------------------");

                    if (app.getObras().isEmpty()) {
                        throw new ObraInexistenteException("No hay obras cargadas en el programa.\n");
                    }

                    // buscar obra existente
                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();

                    System.out.print("\nIngrese el nombre de la obra:\n ");
                    Obra obra2 = app.buscarPorNombre(scanner.nextLine().trim());

                    // si la obra no existe, mostrar mensaje de error
                    if (obra2 == null) {
                        throw new ObraInexistenteException("\nLa obra introducida no existe.\n");
                    } else {
                        obra2.getCertificados().mostrarCertificados();
                    }
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
    public static void menuUsuarioObra(Scanner scanner, MaterialHandler<Material> materialHandler, App app, CertificadoHandler certificadoHandler) {
        int opcion;
        do {
            System.out.println("\n--------------------------------------------");
            System.out.println("        👷  MENÚ USUARIO DE OBRA");
            System.out.println("--------------------------------------------");
            System.out.println(" 1. 📦  Cargar material");
            System.out.println(" 2. ❌  Eliminar material");
            System.out.println(" 3. ✏️  Editar material");
            System.out.println(" 4. 📄  Crear certificado de avance");
            System.out.println(" 5. 🔍  Consultar certificados de avance");
            System.out.println(" 6. 🧰  Realizar mantenimiento de materiales");
            System.out.println(" 0. 🚪  Volver al menú principal");
            System.out.println("--------------------------------------------");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (opcion) {
                case 1:
                    // agregar material a obra
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Agregar material a obra...");
                    System.out.println("--------------------------------------------");


                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();

                    // buscar obra para agregar material
                    System.out.print("Ingrese el nombre de la obra:\n ");
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
                        manejoJSON.guardarApp(app);
                    }
                    break;
                case 2:
                    // eliminar material de obra
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Eliminar material de obra...");
                    System.out.println("--------------------------------------------");

                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();

                    System.out.print("\nIngrese el nombre de la obra: \n");
                    Obra o1 = app.buscarPorNombre(scanner.nextLine().trim());

                    // si la obra no existe, mostrar mensaje de error
                    if (o1 == null) {
                        throw new ObraInexistenteException("La obra introducida no existe.\n");
                    } else {
                        System.out.println("Ingrese el nombre del material a eliminar");
                        o1.getMateriales().eliminarPorNombre(scanner.nextLine().trim());
                        o1.getMateriales().mostrarMateriales();
                        manejoJSON.guardarApp(app);
                    }
                    break;
                case 3:
                    // editar material de obra
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Editar material de obra...");
                    System.out.println("--------------------------------------------");

                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();

                    System.out.print("\nIngrese el nombre de la obra: \n");
                    Obra o = app.buscarPorNombre(scanner.nextLine().trim());

                    // si la obra no existe, mostrar mensaje de error
                    if (o == null) {
                        throw new ObraInexistenteException("La obra introducida no existe.\n");
                    } else {
                        System.out.println("Ingrese el nombre del material a buscar");

                        ScannerHandler.editarMaterial(o.getMateriales().buscarMaterialPorNombre(scanner.nextLine().trim()));
                        o.getMateriales().mostrarMateriales();
                        manejoJSON.guardarApp(app);
                    }
                    break;
                case 4:
                    // crear certificado de avance
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Crear certificado de avance...");
                    System.out.println("--------------------------------------------");

                    // buscar obra existente

                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();

                    System.out.print("\nIngrese el nombre de la obra: \n");
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
                        manejoJSON.guardarApp(app);
                    }
                    break;
                case 5:
                    // consultar certificados de avance
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Consultar certificados de avance...");
                    System.out.println("--------------------------------------------");

                    // buscar obra existente
                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();

                    System.out.print("\nIngrese el nombre de la obra:\n ");
                    Obra obra2 = app.buscarPorNombre(scanner.nextLine().trim());

                    // si la obra no existe, mostrar mensaje de error
                    if (obra2 == null) {
                        throw new ObraInexistenteException("La obra introducida no existe.\n");
                    } else {
                        obra2.getCertificados().mostrarCertificados();
                    }
                    break;
                case 6:
                    // realizar mantenimiento sobre materiales mantenibles
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Realizar mantenimiento de materiales...");
                    System.out.println("--------------------------------------------");

                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();

                    System.out.print("\nIngrese el nombre de la obra:\n ");
                    Obra obraM = app.buscarPorNombre(scanner.nextLine().trim());

                    if (obraM == null) {
                        throw new ObraInexistenteException("La obra introducida no existe.\n");
                    } else {
                        System.out.println("\nMateriales mantenibles en la obra '" + obraM.getNombre() + "':\n");

                        boolean hayMantenibles = false;

                        for (Material m : obraM.getMateriales().getListaMateriales()) {
                            if (m instanceof Mantenible mantenible) {
                                hayMantenibles = true;
                                System.out.println("• " + m.getNombre());
                                mantenible.realizarMantenimiento();
                                System.out.println("  Frecuencia de mantenimiento: cada "
                                        + mantenible.obtenerFrecuenciaMantenimiento() + " meses.\n");
                            }
                        }

                        if (!hayMantenibles) {
                            System.out.println("No hay materiales que requieran mantenimiento en esta obra.\n");
                        }

                        manejoJSON.guardarApp(app);
                    }
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
    public static void menuInversor(Scanner scanner, App app) {
        int opcion;
        do {
            System.out.println("\n--------------------------------------------");
            System.out.println("          💰  MENÚ INVERSOR");
            System.out.println("--------------------------------------------");
            System.out.println(" 1. 🔍  Consultar certificados de avance");
            System.out.println(" 0. 🚪  Volver al menú principal");
            System.out.println("--------------------------------------------");
            System.out.print("Seleccione una opción: ");

            opcion = scanner.nextInt();
            scanner.nextLine();
            System.out.println();

            switch (opcion) {
                case 1:
                    // consultar certificados de avance
                    System.out.println("\n--------------------------------------------");
                    System.out.println("→ Consultar certificados de avance...");
                    System.out.println("--------------------------------------------");

                    System.out.println("==== TODAS LAS OBRAS ====\n");
                    app.mostrarNombresObras();

                    // buscar obra existente
                    System.out.print("\nIngrese el nombre de la obra: \n");
                    Obra obra2 = app.buscarPorNombre(scanner.nextLine().trim());

                    // si la obra no existe, mostrar mensaje de error
                    if (obra2 == null) {
                        throw new ObraInexistenteException("La obra introducida no existe.\n");
                    } else {
                        obra2.getCertificados().mostrarCertificados();
                    }
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
