import java.io.*;
import java.util.*;

public class Material {
    private String nombre;
    private String editorial;
    private int añoPublicacion;
    private String genero;
    private String autor;
    private String tipo;

    public Material(String nombre, String editorial, int añoPublicacion, String genero, String autor, String tipo) {
        this.nombre = nombre;
        this.editorial = editorial;
        this.añoPublicacion = añoPublicacion;
        this.genero = genero;
        this.autor = autor;
        this.tipo = tipo;
    }

    public String getReferenciaAPA() {
        if (tipo.equals("libro")) {
            return autor + " (" + añoPublicacion + "). " + nombre + ". " + editorial + ".";
        } else if (tipo.equals("DVD")) {
            return autor + " (" + añoPublicacion + "). " + nombre + ". [DVD]. " + editorial + ".";
        }
        // Agregar lógica para otros tipos de material aquí
        return "";
    }
}

public class BibliotecaAmparoCodina {
    private List<Material> catalogo = new ArrayList<>();

    public void agregarMaterial(Material material) {
        catalogo.add(material);
    }

    public String generarReferencia(Material material) {
        return material.getReferenciaAPA();
    }

    public Map<String, Integer> contarMaterialesPorGenero() {
        Map<String, Integer> generoCount = new HashMap<>();
        for (Material material : catalogo) {
            String genero = material.genero;
            generoCount.put(genero, generoCount.getOrDefault(genero, 0) + 1);
        }
        return generoCount;
    }

    public Map<String, Integer> contarMaterialesPorAutor() {
        Map<String, Integer> autorCount = new HashMap<>();
        for (Material material : catalogo) {
            String autor = material.autor;
            autorCount.put(autor, autorCount.getOrDefault(autor, 0) + 1);
        }
        return autorCount;
    }

    public Map<Integer, Integer> contarMaterialesPorAño() {
        Map<Integer, Integer> añoCount = new HashMap<>();
        for (Material material : catalogo) {
            int año = material.añoPublicacion;
            añoCount.put(año, añoCount.getOrDefault(año, 0) + 1);
        }
        return añoCount;
    }

    public void mostrarCatalogoEnAPA() {
        for (Material material : catalogo) {
            System.out.println(generarReferencia(material));
        }
    }

    public void guardarCatalogoEnCSV(String fileName) {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            StringBuilder sb = new StringBuilder();
            sb.append("Nombre,Editorial,Año de Publicación,Género,Autor,Tipo\n");

            for (Material material : catalogo) {
                sb.append(material.nombre).append(",");
                sb.append(material.editorial).append(",");
                sb.append(material.añoPublicacion).append(",");
                sb.append(material.genero).append(",");
                sb.append(material.autor).append(",");
                sb.append(material.tipo).append("\n");
            }

            writer.write(sb.toString());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void cargarCatalogoDesdeCSV(String fileName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            boolean firstLine = true;
            while ((line = reader.readLine()) != null) {
                if (firstLine) {
                    firstLine = false;
                    continue; // Saltar la primera línea de encabezado
                }
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    Material material = new Material(parts[0], parts[1], Integer.parseInt(parts[2]), parts[3], parts[4], parts[5]);
                    catalogo.add(material);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

