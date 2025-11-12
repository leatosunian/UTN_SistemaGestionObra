package clases.tiposMaterial;

import clases.Interfaces.Mantenible;
import clases.Material;

public class MaterialFontaneria extends Material implements Mantenible {
    private String tipoMaterial;

    public MaterialFontaneria(
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
    public MaterialFontaneria() {

    }
    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }

    @Override
    public void realizarMantenimiento() {
        System.out.println("Revisando fugas y limpiando válvulas del material: " + getNombre());
    }

    @Override
    public int obtenerFrecuenciaMantenimiento() {
        return 6; // cada 6 meses
    }
    @Override
    public String toString() {
        return "MaterialFontaneria{" +
                "tipoMaterial='" + tipoMaterial + "\n" +
                "} " + super.toString();
    }
}
