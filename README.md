# 🏗️ SISTEMA DE GESTIÓN DE OBRA

## 🌟 Descripción del Proyecto

Este proyecto es un Sistema de Gestión de Obra desarrollado en Java, enfocado en el control de materiales, proveedores y el progreso físico de una construcción a través de certificados de avance. Implementa un sistema de roles (Administrador, Usuario de Obra, Inversor) para diferenciar los permisos de acceso y manipulación de datos, utilizando archivos JSON para garantizar la persistencia de los datos de la aplicación.

## 🎯 Objetivos

1.  **Control de Recursos:** Gestionar el stock (estimado, acopiado, consumido) de diferentes tipos de materiales.
2.  **Registro de Avance:** Documentar el progreso de la obra mediante la emisión y consulta de `CertificadoAvance`.
3.  **Funcionalidades por Rol:** Restringir funcionalidades y visibilidad de datos según el perfil de usuario.

## ⚙️ Estructura del Sistema (Clases Principales)

| Clase | Función | Relación |
| :--- | :--- | :--- |
| **`Main`** | Contiene la lógica de los menús y la interacción del usuario por roles. | Dependencia con `App`, `MaterialHandler`, `CertificadoHandler`. |
| **`App`** | Contenedor principal que administra la lista de proyectos (`Obra`). | Agregación de `Obra`s. |
| **`Obra`** | Entidad central del proyecto de construcción. | Composición con `MaterialHandler` y `CertificadoHandler`. |
| **`Material`** | Clase abstracta base para insumos (`MaterialEstructural`, `MaterialAcabado`, etc.). | Herencia. |
| **`MaterialHandler`** | Gestiona las colecciones de materiales y sus tipos. | Genérica (`<T extends Material>`). |
| **`CertificadoAvance`** | Representa un documento de avance de obra. | Gestionado por `CertificadoHandler`. |

## 👥 Roles de Usuario y Permisos

| Rol | Nivel de Acceso | Funcionalidades Clave (Menú) |
| :--- | :--- | :--- |
| **Administrador** | Total (Lectura/Escritura/Mantenimiento) | Creación de Obras, Gestión de Materiales/Emisión de Certificados. |
| **Usuario de Obra** | Operativo (Lectura/Escritura limitada) | Carga, Edición y Consumo de Materiales, Emisión y Consulta de Certificados. |
| **Inversor** | Consulta (Solo Lectura) | Consulta de Certificados de Avance. |

## 🛠️ Tecnologías Utilizadas

* **Lenguaje:** Java
* **Dependencias Externas:**
    * `java.util.Scanner`
    * `java.time.LocalDate`
    * Librería para manejo de JSON (`java.json.*`, usado en la lógica de exportación).

## 🚀 Cómo Ejecutar

1.  **Clonar el Repositorio:**
    ```bash
    git clone [URL_DEL_REPOSITORIO]
    cd [nombre-del-repositorio]
    ```
2.  **Compilar y Ejecutar:**
    * *Usando IDE (ej. IntelliJ, Eclipse):* Importá el proyecto y ejecutá la clase `Main`.
    * *Desde la consola:*
        ```bash
        # Compilar (ejemplo)
        javac Main.java App.java Obra.java Material.java CertificadoAvance.java [otras clases]
        # Ejecutar
        java Main
        ```

Una vez iniciado, el sistema presentará el **MENÚ PRINCIPAL** donde se selecciona el rol de inicio de sesión para continuar con las funcionalidades asignadas a cada tipo de usuario.

---

## 🖼️ Diagrama UML de Clases

<img width="5773" height="2574" alt="uml" src="https://github.com/user-attachments/assets/bae18f37-52ae-4339-9def-e534ed84b3dc" />
