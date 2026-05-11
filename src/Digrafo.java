import java.util.HashMap;
import java.util.Map;

public class Digrafo<V, E> {
    
	private int matriz[][];
    private int matrizRutas[][];
    private int matrizDistancia[][];
    private int tamaño;
    private Map<V, Integer> verticeIndex;
    private int contVertices;


    public Digrafo(int tamaño) {
        this.tamaño = tamaño;
        this.matriz = new int[tamaño][tamaño];
        this.matrizRutas = new int[tamaño][tamaño];
        this.verticeIndex = new HashMap<>();
        this.contVertices = 0;
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                if (i == j) {
                    matriz[i][j] = 0; 
                    matrizRutas[i][j] = -1; 
                } else {
                    matriz[i][j] = -1; 
                    matrizRutas[i][j] = -1;
                }
            }
        }
    }

    public void addVertice(V vertice){
        if (!verticeIndex.containsKey(vertice)) {
            verticeIndex.put(vertice, contVertices);
            contVertices++;
        }
    }

    public void addArista(V Vinicio, V Vfinal, E label){
        if (!verticeIndex.containsKey(Vinicio) || !verticeIndex.containsKey(Vfinal)) {
            System.out.println("Una o ambas ciudades no existen en el grafo.");
            return;
        }
        int index1 = verticeIndex.get(Vinicio);
        int index2 = verticeIndex.get(Vfinal);
        matriz[index1][index2] = (Integer) label;
    }

    public void removeArista(V Vinicio, V Vfinal){
        if (!verticeIndex.containsKey(Vinicio) || !verticeIndex.containsKey(Vfinal)) {
            System.out.println("Una o ambas ciudades no existen en el grafo.");
            return;
        }
        int index1 = verticeIndex.get(Vinicio);
        int index2 = verticeIndex.get(Vfinal);
        matriz[index1][index2] = -1;
    }

    public void imprimirMatriz(String tipo) {
        algoritmoFloyd();
        System.out.println("Matriz de " + tipo + ":");
        for (int i = 0; i < contVertices; i++) {
            for (int j = 0; j < contVertices; j++) {
                if (tipo.equalsIgnoreCase("adyacencia")) {
                    System.out.print(matriz[i][j] + " ");
                } else if (tipo.equalsIgnoreCase("distancia")) {
                    System.out.print(matrizDistancia[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    private void algoritmoFloyd() {
        matrizDistancia = new int[contVertices][contVertices];
        for (int i = 0; i < contVertices; i++) {
            for (int j = 0; j < contVertices; j++) {
                matrizDistancia[i][j] = matriz[i][j];
                matrizRutas[i][j] = -1;
            }
        }
        // Implementación del algoritmo de Floyd para calcular la ruta más corta
        for (int k = 0; k < contVertices; k++) {
            for (int i = 0; i < contVertices; i++) {
                for (int j = 0; j < contVertices; j++) {
                    if (matrizDistancia[i][k] != -1 && matrizDistancia[k][j] != -1) {
                        int nuevaDistancia = matrizDistancia[i][k] + matrizDistancia[k][j];
                        if (matrizDistancia[i][j] == -1 || nuevaDistancia < matrizDistancia[i][j]) {
                            matrizDistancia[i][j] = nuevaDistancia;
                            matrizRutas[i][j] = k; // Guardar el vértice intermedio
                        }
                    }
                }
            }
        }
    }

    private V getVerticePorIndex(int index) {
        for (Map.Entry<V, Integer> entrada : verticeIndex.entrySet()) {
            if (entrada.getValue() == index) {
                return entrada.getKey();
            }
        }
        return null;
    }

    private void imprimirRuta(int origen, int destino) {
        if (matrizRutas[origen][destino] == -1) {
            System.out.print(" -> " + getVerticePorIndex(destino));
        } else {
            int intermedio = matrizRutas[origen][destino];
            imprimirRuta(origen, intermedio);
            imprimirRuta(intermedio, destino);
        }
    }

    public void RutaMasCorta(V origen, V destino) {
        algoritmoFloyd();
        int indexOrigen = verticeIndex.get(origen);
        int indexDestino = verticeIndex.get(destino);
        int distancia = matrizDistancia[indexOrigen][indexDestino];
        if (indexOrigen == indexDestino) {
            System.out.println("La distancia es 0");
            System.out.println("Ruta: " + origen);
        } else if (distancia != -1) {
            System.out.println("La distancia más corta entre " + origen + " y " + destino + " es: " + distancia);
            System.out.print("Ruta: " + origen);
            imprimirRuta(indexOrigen, indexDestino);
            System.out.println();
        } else {
            System.out.println("No hay camino entre " + origen + " y " + destino);
        }
    }

    public V calcularCentro() {
        algoritmoFloyd();
        int indexCentro = -1;
        int minMaxDistancia = -1;

        for (int i = 0; i < contVertices; i++) {
            int maxDistancia = 0;
            boolean esValido = true;

            for (int j = 0; j < contVertices; j++) {
                if (matrizDistancia[i][j] == -1) {
                    esValido = false;
                    break;
                }
                if (matrizDistancia[i][j] > maxDistancia) {
                    maxDistancia = matrizDistancia[i][j];
                }
            }
            if (esValido && (minMaxDistancia == -1 || maxDistancia < minMaxDistancia)) {
                minMaxDistancia = maxDistancia;
                indexCentro = i;
            }
        }

        if (indexCentro != -1) {
            return getVerticePorIndex(indexCentro);
        }

        System.out.println("No se pudo determinar el centro del grafo.");
        return null;
    } 
}