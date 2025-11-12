package clases;

import clases.exceptions.materialExceptions.CantidadExcedidaException;

public abstract class Material {

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
        this.nombre = nombre;
        this.unidadMedida = unidadMedida;
        this.precioUnitario = precioUnitario;
        this.cantidadEstimadaTotal = cantidadEstimadaTotal;
        this.cantidadAcopiadaObra = cantidadAcopiadaObra;
        this.cantidadEnProveedor = cantidadEnProveedor;
        this.cantidadConsumida = cantidadConsumida;
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
        if (cantidadAcopiadaObra + this.cantidadEnProveedor + this.cantidadConsumida > this.cantidadEstimadaTotal) {
            throw new CantidadExcedidaException(
                    "La suma de acopiada + proveedor + consumida supera la cantidad estimada total del material: " + nombre
            );
        }
        this.cantidadAcopiadaObra = cantidadAcopiadaObra;
    }


    public double getCantidadEnProveedor() {
        return cantidadEnProveedor;
    }

    public void setCantidadEnProveedor(double cantidadEnProveedor) {
        double total = this.cantidadAcopiadaObra + cantidadEnProveedor + this.cantidadConsumida;
        if (total > this.cantidadEstimadaTotal) {
            throw new CantidadExcedidaException(
                    "Error: la suma de cantidades supera la estimada total del material '" + nombre + "'. " +
                            "Total actual: " + total + " | Estimada: " + cantidadEstimadaTotal
            );
        }
        this.cantidadEnProveedor = cantidadEnProveedor;
    }

    public double getCantidadConsumida() {
        return cantidadConsumida;
    }

    public void setCantidadConsumida(double cantidadConsumida) {
        double total = this.cantidadAcopiadaObra + this.cantidadEnProveedor + cantidadConsumida;
        if (total > this.cantidadEstimadaTotal) {
            throw new CantidadExcedidaException(
                    "Error: la suma de cantidades supera la estimada total del material '" + nombre + "'. " +
                            "Total actual: " + total + " | Estimada: " + cantidadEstimadaTotal
            );
        }
        this.cantidadConsumida = cantidadConsumida;
    }

    public double getCantidadFaltanteComprar() {
        return cantidadEstimadaTotal - cantidadAcopiadaObra - cantidadEnProveedor - cantidadConsumida;
    }

    public void consumirMaterial(double cantidad) {
        this.cantidadConsumida += cantidad;
        this.cantidadAcopiadaObra -= cantidad;
    }

    @Override
    public String toString() {
        return "Material{" + "\n" +
                "nombre=" + nombre + "\n"+
                " unidadMedida=" + unidadMedida + "\n" +
                " precioUnitario=" + precioUnitario + "\n"+
                " cantidadEstimadaTotal=" + cantidadEstimadaTotal + "\n"+
                " cantidadAcopiadaObra=" + cantidadAcopiadaObra + "\n" +
                " cantidadEnProveedor=" + cantidadEnProveedor + "\n" +
                " cantidadConsumida=" + cantidadConsumida + "\n" +
                '}';
    }
}
