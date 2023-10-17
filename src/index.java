public class Main {
    public static void main(String[] args) {
        BibliotecaAmparoCodina biblioteca = new BibliotecaAmparoCodina();

        Material material1 = new Material("Libro 1", "Editorial A", 2022, "Ficción", "Autor 1", "libro");
        Material material2 = new Material("Artículo 1", "Editorial B", 2021, "Ciencia", "Autor 2", "artículo");

        biblioteca.agregarMaterial(material1);
        biblioteca.agregarMaterial(material2);

        biblioteca.mostrarCatalogoEnAPA();

        Map<String, Integer> generoCount = biblioteca.contarMaterialesPorGenero();
        System.out.println("Conteo por género: " + generoCount);

        Map<String, Integer> autorCount = biblioteca.contarMaterialesPorAutor();
        System.out.println("Conteo por autor: " + autorCount);

        Map<Integer, Integer> añoCount = biblioteca.contarMaterialesPorAño();
        System.out.println("Conteo por año de publicación: " + añoCount);

        biblioteca.guardarCatalogoEnCSV("catalogo.csv");

        // Para cargar el catálogo desde un archivo CSV
        // biblioteca.cargarCatalogoDesdeCSV("catalogo.csv");
    }
}

