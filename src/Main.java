import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        int opcion = -1;
        TxtReader reader = new TxtReader();
        String[] caminos = reader.readFile("src\\guategrafo.txt", "\n");
        Scanner scanner = new Scanner(System.in);

        Digrafo<String, Integer> digrafo = new Digrafo<>(caminos.length * 2);

        try {
            // Agregar vértices y aristas al grafo
            for (int i = 0; i < caminos.length; i++) {
                String[] partes = caminos[i].split(" ");
                digrafo.addVertice(partes[0].strip());
                digrafo.addVertice(partes[1].strip());
                digrafo.addArista(partes[0].strip(), partes[1].strip(), Integer.parseInt(partes[2].strip()));
            }
        } catch (Exception e) {
            System.err.println("Error al procesar el grafo: " + e.getMessage());
        }

        System.out.println("Grafo creado con éxito.");

        while (opcion !=0) {
            System.out.println("Seleccione una opción:");
            System.out.println("1. Eliminar ciudad \n 2. Eliminar camino \n 3. Agregar camino \n 4. Calcular ruta más corta \n 5. Calcular centro \n 0. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            switch (opcion) {
                case 2:
                    System.out.print("Ingrese el nombre de la ciudad a eliminar: ");
                    String ciudadEliminar = scanner.nextLine().strip();
                    digrafo.removeVertice(ciudadEliminar);
                    break;
                    
                case 3:
                    System.out.print("Ingrese el nombre de la primera ciudad: ");
                    String ciudad1 = scanner.nextLine().strip();
                    System.out.print("Ingrese el nombre de la segunda ciudad: ");
                    String ciudad2 = scanner.nextLine().strip();
                    digrafo.removeArista(ciudad1, ciudad2);
                    break;

                case 4:
                    System.out.print("Ingrese el nombre de la primera ciudad: ");
                    String ciudad1Agregar = scanner.nextLine().strip();
                    System.out.print("Ingrese el nombre de la segunda ciudad: ");
                    String ciudad2Agregar = scanner.nextLine().strip();
                    System.out.print("Ingrese el peso del camino: ");
                    int peso = scanner.nextInt();
                    scanner.nextLine(); // Consumir el salto de línea
                    digrafo.addArista(ciudad1Agregar, ciudad2Agregar, peso);
                    break;

                case 5:
                    System.out.print("Ingrese el nombre de la primera ciudad: ");
                    String origen = scanner.nextLine().strip();
                    System.out.print("Ingrese el nombre de la segunda ciudad: ");
                    String destino = scanner.nextLine().strip();
                    digrafo.RutaMasCorta(origen, destino);
                    break;
                case 6:
                    System.out.println("La ciudad central es:");
                    //digrafo.calcularCentro();
                case 0:
                    System.out.println("Saliendo");
                    break;

                default:
                    throw new AssertionError();
            }

        }

    }
}
