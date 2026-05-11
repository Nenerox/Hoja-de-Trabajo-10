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

        System.out.println("Grafo creado con éxito");

        while (opcion !=0) {
            System.out.println("Seleccione una opción:");
            System.out.println(" 1. Calcular ruta mas corta \n 2. Ciudad central \n 3. Modificar grafo \n 4. Mostrar matriz \n 0. Salir");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            switch (opcion) {
                case 1:
                    System.out.print("Ingrese el nombre de la primera ciudad: ");
                    String origen = scanner.nextLine().strip();
                    System.out.print("Ingrese el nombre de la segunda ciudad: ");
                    String destino = scanner.nextLine().strip();
                    digrafo.RutaMasCorta(origen, destino);
                    break;
                    
                case 2:
                    System.out.println("La ciudad central es: " + digrafo.calcularCentro());
                    break;

                case 3:
                    System.out.println("Seleccione una opción:");
                    System.out.println(" 1. Eliminar camino \n 2. Agregar camino");
                    int subOpcion = scanner.nextInt();
                    scanner.nextLine(); 
                    switch (subOpcion) {
                        case 1:
                            System.out.print("Ingrese el nombre de la primera ciudad: ");
                            String ciudad1 = scanner.nextLine().strip();
                            System.out.print("Ingrese el nombre de la segunda ciudad: ");
                            String ciudad2 = scanner.nextLine().strip();
                            digrafo.removeArista(ciudad1, ciudad2);
                            break;
                    
                        case 2:
                            System.out.print("Ingrese el nombre de la primera ciudad: ");
                            String ciudad1Agregar = scanner.nextLine().strip();
                            System.out.print("Ingrese el nombre de la segunda ciudad: ");
                            String ciudad2Agregar = scanner.nextLine().strip();
                            System.out.print("Ingrese el peso del camino: ");
                            int peso = scanner.nextInt();
                            scanner.nextLine();
                            digrafo.addArista(ciudad1Agregar, ciudad2Agregar, peso);
                            break;
                        default:
                            throw new AssertionError("Opción no válida");
                    }
                    break;

                case 4:
                    System.out.println("Seleccione una opción:");
                    System.out.println(" 1. Matriz de adyacencia \n 2. Matriz de distancias");
                    int tipo = scanner.nextInt();
                    if (tipo == 1) {
                        digrafo.imprimirMatriz("adyacencia");
                    } else if (tipo == 2) {
                        digrafo.imprimirMatriz("distancia");
                    } else {
                        System.out.println("Opción no válida");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo");
                    break;
                default:
                    throw new AssertionError("Opción no válida");
            }
        }
    }
}
