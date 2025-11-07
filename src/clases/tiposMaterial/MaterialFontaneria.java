package clases.tiposMaterial;

import clases.Material;

public class MaterialFontaneria extends Material {
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
    public String toString() {
        return "MaterialFontaneria{" +
                "id=" + getId() +
                ", nombre='" + getNombre() + '\'' +
                ", tipoMaterial='" + tipoMaterial + '\'' +
                '}';
    }
}
