package clases.tiposMaterial;

import clases.Material;

public class MaterialAcabado extends Material {
    private String tipoMaterial;

    public MaterialAcabado(
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
    public MaterialAcabado() {

    }

    public String getTipoMaterial() {
        return tipoMaterial;
    }

    public void setTipoMaterial(String tipoMaterial) {
        this.tipoMaterial = tipoMaterial;
    }


    @Override
    public String toString() {
        return "MaterialAcabado{" +
                "tipoMaterial='" + tipoMaterial + "\n" +
                "} " + super.toString();
    }
}
