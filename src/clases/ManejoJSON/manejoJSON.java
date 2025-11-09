package clases.ManejoJSON;
import clases.App;
import clases.CertificadoAvance;
import clases.Obra;
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

    public static void guardarApp(App app){
        try {
            JSONArray jObras = obrasToJson(app.getObras());
            JSONObject jApp = new JSONObject();
            JSONObject jRaiz = new JSONObject();
            jApp.put("obras", jObras);
            jRaiz.put("app", jApp);



            try (FileWriter file = new FileWriter(ARCHIVO_OBRAS)) {
                file.write(jRaiz.toString(4)); // con indentación
                System.out.println("App guardada correctamente en " + ARCHIVO_OBRAS);
            } catch (IOException | JSONException e) {
                e.printStackTrace();
            }
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
    // 🔹 Devuelve todas las obras en formato JSON
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
                array.put(obj);
            }

            JSONObject jsonFinal = new JSONObject();
            jsonFinal.put("certificados", array);
            return jsonFinal;
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject MaterialesToJSON(MaterialHandler<Material> materiales) {
        JSONArray array = new JSONArray();

        try {
            for (Material material : materiales.getListaMateriales()) {
                JSONObject obj = new JSONObject();
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
public static App mapeoApp(){
        App app = new App();
    List<Obra> obras = new ArrayList<>();
    try {
        JSONObject json = new JSONObject(JSONUtiles.leer(ARCHIVO_OBRAS));
        JSONObject jApp = json.getJSONObject("app");
          jsonToObras(jApp);
app.setObras(obras);
            System.out.println("Obras cargadas correctamente desde " + ARCHIVO_OBRAS);
    } catch (JSONException e) {
        throw new RuntimeException(e);
    }
return app;
}
  // Mapeo del Json
    public static List<Obra> jsonToObras(JSONObject jApp) {
        List<Obra> obras = new ArrayList<>();

        try {

            JSONArray arrayObras = jApp.getJSONArray("obras");

            // Iterar el array JSON y reconstruir cada Obra
            for (int i = 0; i < arrayObras.length(); i++) {
                JSONObject jObra = arrayObras.getJSONObject(i);
                obras.add(jsonToObra(jObra)); // Llama al método de reconstrucción
            }

            System.out.println("Obras cargadas correctamente desde " + ARCHIVO_OBRAS);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }


        return obras;
    }


    public static Obra jsonToObra(JSONObject jObra) throws JSONException {
        // 1. Reconstrucción de la Obra (asume setters en la clase Obra)
        Obra obra = new Obra();

        obra.setNombre(jObra.getString("nombre"));
        obra.setDescripcion(jObra.getString("descripcion"));
        obra.setUbicacion(jObra.getString("ubicacion"));

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

            String tipoMaterial = jMat.getString("tipo"); //
            Material material;
            if (tipoMaterial.equals("Acabado")) {
                 material = new MaterialAcabado(); // Instancia subclase concreta
            } else if (tipoMaterial.equals("Electrico")) {
                material = new MaterialElectrico();    // Instancia otra subclase concreta
            } else if(tipoMaterial.equals("Estructural")) {
                material = new MaterialEstructural(); // Instancia otra subclase concreta
            }else if(tipoMaterial.equals("Fontaneria")) {
                material = new MaterialFontaneria(); // Instancia otra subclase concreta
            }else{
                // Manejo de error o valor predeterminado
                throw new JSONException("Tipo de material desconocido: " + tipoMaterial);
            }
            // Asignación de propiedades comunes
            material.setNombre(jMat.getString("nombre"));
            material.setUnidadMedida(jMat.getString("unidadMedida"));
            material.setPrecioUnitario(jMat.getDouble("precioUnitario"));
            material.setCantidadEstimadaTotal(jMat.getDouble("cantidadEstimadaTotal"));
            material.setCantidadAcopiadaObra(jMat.getDouble("cantidadAcopiadaObra"));
            material.setCantidadEnProveedor(jMat.getDouble("cantidadEnProveedor"));
            material.setCantidadConsumida(jMat.getDouble("cantidadConsumida"));


            listaMateriales.add(material);
        }
        handler.setListaMateriales(listaMateriales);
        return handler;
    }

    public static CertificadoHandler jsonToCertificados(JSONObject jCertificados) throws JSONException {
        CertificadoHandler handler = new CertificadoHandler();

        JSONArray array = jCertificados.getJSONArray("certificados");

        // Define el formato de fecha para la deserialización
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < array.length(); i++) {
            JSONObject jCert = array.getJSONObject(i);
            CertificadoAvance certificado = new CertificadoAvance(); // Asume constructor vacío o setters

            // Mapeo de campos
            certificado.setId(jCert.getInt("id"));

            // Mapeo de fecha: convierte String a objeto de fecha (ej. LocalDate)
            String fechaStr = jCert.getString("fecha");
            certificado.setFecha(LocalDate.parse(fechaStr, formatter));

            certificado.setPorcentajeAvance(jCert.getDouble("porcentajeAvance"));
            certificado.setMontoCertificado(jCert.getDouble("montoCertificado"));
            certificado.setDescripcionTrabajo(jCert.getString("descripcionTrabajo"));


            handler.agregarCertificado(certificado);
        }

        return handler;
    }
}








