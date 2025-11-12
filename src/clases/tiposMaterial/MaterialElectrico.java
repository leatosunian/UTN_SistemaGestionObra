package clases.tiposMaterial;

import clases.Interfaces.Mantenible;
import clases.Material;

public class MaterialElectrico extends Material implements Mantenible {
    private String tipoMaterial;

    public MaterialElectrico(
            String nombre,
            String unidadMedida,
            double precioUnitario,
            double cantidadEstimadaTotal,
            double cantidadAcopiadaObra,
            double cantidadEnProveedor,
            double cantidadConsumida,
            String tipoMaterial
    ) {
        super(nombre, unidadMedida, precioUnitario,
                cantidadEstimadaTotal, cantidadAcopiadaObra, cantidadEnProveedor, cantidadConsumida);
        this.tipoMaterial = tipoMaterial;
    }
    public MaterialElectrico() {

    }
    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    @Override
    public void realizarMantenimiento() {
        System.out.println("Verificando conexiones y aislantes del material eléctrico: " + getNombre());
    }

    @Override
    public int obtenerFrecuenciaMantenimiento() {
        return 12; // cada 12 meses
    }
    @Override
    public String toString() {
        return "MaterialElectrico{" +
                "tipoMaterial='" + tipoMaterial + "\n" +
                "} " + super.toString();
    }
}