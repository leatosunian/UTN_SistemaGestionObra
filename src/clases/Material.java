package clases;

public abstract class Material {
    private static int cantMatsTotales = 1;

    private int id;
    private String nombre;
    private String unidadMedida; // kg, m3, unidades,etc
    private double precioUnitario;
    // Cantidades
    private double cantidadEstimadaTotal; // del presupuesto
    private double cantidadAcopiadaObra;  // físicamente en la obra
    private double cantidadEnProveedor;   // en depósito del proveedor
    private double cantidadConsumida;     // ya usada

    public Material() {
    }

    public Material(String nombre, String unidadMedida, double precioUnitario,
                    double cantidadEstimadaTotal, double cantidadAcopiadaObra, double cantidadEnProveedor,
                    double cantidadConsumida) {
        this.id = cantMatsTotales++;
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.precioUnitario = precioUnitario;
        this.cantidadEstimadaTotal = cantidadEstimadaTotal;
        this.cantidadAcopiadaObra = cantidadAcopiadaObra;
        this.cantidadEnProveedor = cantidadEnProveedor;
        this.cantidadConsumida = cantidadConsumida;
    }

    public static int getCantMatsTotales() {
        return cantMatsTotales;
    }

    public static void setCantMatsTotales(int cantMatsTotales) {
        Material.cantMatsTotales = cantMatsTotales;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public double getPrecioUnitario() {
        return precioUnitario;
    }

    public void setPrecioUnitario(double precioUnitario) {
        this.precioUnitario = precioUnitario;
    }

    public double getCantidadEstimadaTotal() {
        return cantidadEstimadaTotal;
    }

    public void setCantidadEstimadaTotal(double cantidadEstimadaTotal) {
        this.cantidadEstimadaTotal = cantidadEstimadaTotal;
    }

    public double getCantidadAcopiadaObra() {
        return cantidadAcopiadaObra;
    }

    public void setCantidadAcopiadaObra(double cantidadAcopiadaObra) {
        this.cantidadAcopiadaObra = cantidadAcopiadaObra;
    }

    public double getCantidadEnProveedor() {
        return cantidadEnProveedor;
    }

    public void setCantidadEnProveedor(double cantidadEnProveedor) {
        this.cantidadEnProveedor = cantidadEnProveedor;
    }

    public double getCantidadConsumida() {
        return cantidadConsumida;
    }

    public void setCantidadConsumida(double cantidadConsumida) {
        this.cantidadConsumida = cantidadConsumida;
    }

    public double getCantidadFaltanteComprar() {
        return cantidadEstimadaTotal - cantidadAcopiadaObra - cantidadEnProveedor - cantidadConsumida;
    }

    public void recibirMaterial(double cantidad, boolean directoDelProveedor) {
        this.cantidadAcopiadaObra += cantidad;
        if (directoDelProveedor) {
            this.cantidadEnProveedor -= cantidad;
        }
    }

    public void consumirMaterial(double cantidad) {
        this.cantidadConsumida += cantidad;
        this.cantidadAcopiadaObra -= cantidad;
    }




}
