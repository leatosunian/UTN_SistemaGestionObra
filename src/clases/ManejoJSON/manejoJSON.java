package clases.ManejoJSON;
import clases.App;
import clases.CertificadoAvance;
import clases.Obra;
import clases.exceptions.materialExceptions.TipoMaterialInexistenteException;
import clases.handlers.CertificadoHandler;
import clases.tiposMaterial.MaterialAcabado;
import clases.tiposMaterial.MaterialElectrico;
import clases.tiposMaterial.MaterialEstructural;
import clases.tiposMaterial.MaterialFontaneria;
import org.json.*;
import clases.Material;
import clases.handlers.MaterialHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class manejoJSON {
    private static final String ARCHIVO_OBRAS = "obras.json";

    public static void guardarApp(App app) {
        try {
            JSONArray jObras = obrasToJson(app.getObras());
            JSONObject jApp = new JSONObject();
            JSONObject jRaiz = new JSONObject();
            jApp.put("obras", jObras);
            jRaiz.put("app", jApp);


            try (FileWriter file = new FileWriter(ARCHIVO_OBRAS)) {
                file.write(jRaiz.toString(4));
                System.out.println("App guardada correctamente en " + ARCHIVO_OBRAS);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public static JSONArray obrasToJson(List<Obra> obras) {
        JSONArray arrayObras = new JSONArray();

        for (Obra obra : obras) {
            arrayObras.put(obraToJSON(obra));
        }

        return arrayObras;
    }

    public static JSONObject obraToJSON(Obra obra) {
        try {
            JSONObject jObra = new JSONObject();
            jObra.put("certificados", CertificadosToJSON(obra.getCertificados()));
            jObra.put("nombre", obra.getNombre());
            jObra.put("descripcion", obra.getDescripcion());
            jObra.put("ubicacion", obra.getUbicacion());

            jObra.put("materiales", MaterialesToJSON(obra.getMateriales()));


            return jObra;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }


    public static JSONObject CertificadosToJSON(CertificadoHandler certificados) {
        try {
            JSONArray array = new JSONArray();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

            for (CertificadoAvance certificado : certificados.getCertificados()) {
                JSONObject obj = new JSONObject();
                obj.put("id", certificado.getId());
                obj.put("fecha", certificado.getFecha().format(formatter));
                obj.put("porcentajeAvance", certificado.getPorcentajeAvance());
                obj.put("montoCertificado", certificado.getMontoCertificado());
                obj.put("descripcionTrabajo", certificado.getDescripcionTrabajo());


                JSONArray materialesUsadosArray = getMaterialesUsadosArray(certificado);
                obj.put("materialesUsados", materialesUsadosArray);

                array.put(obj);
            }

            JSONObject jsonFinal = new JSONObject();
            jsonFinal.put("certificados", array);
            return jsonFinal;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    private static JSONArray getMaterialesUsadosArray(CertificadoAvance certificado) throws JSONException {
        JSONArray materialesUsadosArray = new JSONArray();
        if (certificado.getMateriales() != null) {
            for (Material m : certificado.getMateriales()) {
                JSONObject jMat = new JSONObject();
                jMat.put("nombre", m.getNombre());
                jMat.put("cantidadUsada", m.getCantidadConsumida());
                materialesUsadosArray.put(jMat);
            }
        }
        return materialesUsadosArray;
    }


    public static JSONObject MaterialesToJSON(MaterialHandler<Material> materiales) {
        JSONArray array = new JSONArray();

        try {
            for (Material material : materiales.getListaMateriales()) {
                JSONObject obj = new JSONObject();
                obj.put("tipo", material.getClass().getSimpleName());
                obj.put("nombre", material.getNombre());
                obj.put("unidadMedida", material.getUnidadMedida());
                obj.put("precioUnitario", material.getPrecioUnitario());
                obj.put("cantidadEstimadaTotal", material.getCantidadEstimadaTotal());
                obj.put("cantidadAcopiadaObra", material.getCantidadAcopiadaObra());
                obj.put("cantidadEnProveedor", material.getCantidadEnProveedor());
                obj.put("cantidadConsumida", material.getCantidadConsumida());
                obj.put("cantidadFaltanteComprar", material.getCantidadFaltanteComprar());
                array.put(obj);
            }

            JSONObject jsonFinal = new JSONObject();
            jsonFinal.put("materiales", array);
            return jsonFinal;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }



    /// FUNCIONES MAPEO ///



    public static App mapeoApp() {
        App app = new App();
        try {
            JSONObject json = new JSONObject(JSONUtiles.leer(ARCHIVO_OBRAS));
            JSONObject jApp = json.getJSONObject("app");
            app.setObras(jsonToObras(jApp));
            System.out.println("Obras cargadas correctamente desde " + ARCHIVO_OBRAS);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        return app;
    }


    public static List<Obra> jsonToObras(JSONObject jApp) {
        List<Obra> obras = new ArrayList<>();

        try {

            JSONArray arrayObras = jApp.getJSONArray("obras");

            for (int i = 0; i < arrayObras.length(); i++) {
                JSONObject jObra = arrayObras.getJSONObject(i);
                obras.add(jsonToObra(jObra));
            }

            System.out.println("Obras cargadas correctamente desde " + ARCHIVO_OBRAS);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return obras;
    }


    public static Obra jsonToObra(JSONObject jObra) throws JSONException {

        Obra obra = new Obra();

        obra.setNombre(jObra.optString("nombre",""));
        obra.setDescripcion(jObra.optString("descripcion",""));
        obra.setUbicacion(jObra.optString("ubicacion", ""));

        JSONObject jMateriales = jObra.getJSONObject("materiales");
        obra.setMateriales(jsonToMateriales(jMateriales));

        JSONObject jCertificados = jObra.getJSONObject("certificados");
        obra.setCertificados(jsonToCertificados(jCertificados));

        return obra;
    }


    public static MaterialHandler<Material> jsonToMateriales(JSONObject jMateriales) throws JSONException {

        List<Material> listaMateriales = new ArrayList<>();
        JSONArray array = jMateriales.getJSONArray("materiales");
        MaterialHandler<Material> handler = new MaterialHandler<>();
        for (int i = 0; i < array.length(); i++) {
            JSONObject jMat = array.getJSONObject(i);


            String tipoMaterial = jMat.optString("tipo", "MaterialEstructural");
            Material material = getMaterial(tipoMaterial);

            material.setNombre(jMat.optString("nombre", ""));
            material.setUnidadMedida(jMat.optString("unidadMedida", ""));
            material.setPrecioUnitario(jMat.optDouble("precioUnitario", 0.0));
            material.setCantidadEstimadaTotal(jMat.optDouble("cantidadEstimadaTotal", 0.0));
            material.setCantidadAcopiadaObra(jMat.optDouble("cantidadAcopiadaObra", 0.0));
            material.setCantidadEnProveedor(jMat.optDouble("cantidadEnProveedor", 0.0));
            material.setCantidadConsumida(jMat.optDouble("cantidadConsumida", 0.0));

            switch (material) {
                case MaterialAcabado m -> m.setTipoMaterial("MaterialAcabado");
                case MaterialElectrico m -> m.setTipoMaterial("MaterialElectrico");
                case MaterialEstructural m -> m.setTipoMaterial("MaterialEstructural");
                case MaterialFontaneria m -> m.setTipoMaterial("MaterialFontaneria");
                default -> {
                }
            }

            listaMateriales.add(material);
        }
        handler.setListaMateriales(listaMateriales);
        return handler;
    }

    private static Material getMaterial(String tipoMaterial) throws JSONException {
        return switch (tipoMaterial) {
            case "MaterialAcabado" -> new MaterialAcabado();

            case "MaterialElectrico" -> new MaterialElectrico();

            case "MaterialEstructural" -> new MaterialEstructural();

            case "MaterialFontaneria" -> new MaterialFontaneria();

            default ->
                    throw new TipoMaterialInexistenteException("Tipo de material desconocido: " + tipoMaterial);
        };
    }

    public static CertificadoHandler jsonToCertificados(JSONObject jCertificados) throws JSONException {
        CertificadoHandler handler = new CertificadoHandler();

        if (!jCertificados.has("certificados")) return handler;

        JSONArray array = jCertificados.getJSONArray("certificados");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < array.length(); i++) {
            JSONObject jCert = array.getJSONObject(i);
            CertificadoAvance certificado = new CertificadoAvance();


            certificado.setId(jCert.optInt("id", 0));
            String fechaStr = jCert.optString("fecha", "");
            if (!fechaStr.isEmpty()) {
                certificado.setFecha(LocalDate.parse(fechaStr, formatter));
            }
            certificado.setPorcentajeAvance(jCert.optDouble("porcentajeAvance", 0.0));
            certificado.setMontoCertificado(jCert.optDouble("montoCertificado", 0.0));
            certificado.setDescripcionTrabajo(jCert.optString("descripcionTrabajo", ""));


            List<Material> materialesUsados = new ArrayList<>();
            JSONArray jMaterialesUsados = jCert.optJSONArray("materialesUsados");
            if (jMaterialesUsados != null) {
                for (int j = 0; j < jMaterialesUsados.length(); j++) {
                    JSONObject jMat = jMaterialesUsados.getJSONObject(j);

                    Material m = new MaterialEstructural();
                    m.setNombre(jMat.optString("nombre", ""));
                    m.setCantidadConsumida(jMat.optDouble("cantidadUsada", 0.0));

                    materialesUsados.add(m);
                }
            }
            certificado.setMateriales(materialesUsados);

            handler.agregarCertificado(certificado);
        }

        return handler;
    }
}








