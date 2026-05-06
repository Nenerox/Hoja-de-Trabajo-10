import java.util.HashMap;
import java.util.Map;

public class Digrafo<V, E> {
    
	private int matriz[][];
    private int matrizDistancias[][];
    private int matrizRutas[][];
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
                matriz[i][j] = -1;
                matrizRutas[i][j] = -1;
            }
        }
    }

    public void addVertice(V vertice){
        verticeIndex.put(vertice, contVertices);
        contVertices++;
        if (contVertices > tamaño) {
            int aux[][] = matriz.clone();
            int auxRutas[][] = matrizRutas.clone();
            matriz = new int[contVertices][contVertices];
            for (int i = 0; i < contVertices; i++) {
                for (int j = 0; j < contVertices; j++) {
                    if (i >= tamaño || j >= tamaño) {
                        matriz[i][j] = -1;
                        matrizRutas[i][j] = -1;
                    } else {
                        matriz[i][j] = aux[i][j];
                        matrizRutas[i][j] = auxRutas[i][j];
                    }
                }
            }
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
        algoritmoFloyd();
    }

    public void removeVertice(V vertice){
        if (!verticeIndex.containsKey(vertice)) {
            System.out.println("La ciudad " + vertice + " no existe en el grafo.");
            return;
        }
        int index = verticeIndex.get(vertice);
        for (int i = 0; i < tamaño; i++) {
            matriz[index][i] = -1; // Limpiar fila
            matriz[i][index] = -1; // Limpiar columna
        }

        verticeIndex.remove(vertice);
        contVertices--;
        algoritmoFloyd(); // Recalcular rutas más cortas después de eliminar un vértice
    }

    public void removeArista(V Vinicio, V Vfinal){
        if (!verticeIndex.containsKey(Vinicio) || !verticeIndex.containsKey(Vfinal)) {
            System.out.println("Una o ambas ciudades no existen en el grafo.");
            return;
        }
        int index1 = verticeIndex.get(Vinicio);
        int index2 = verticeIndex.get(Vfinal);
        matriz[index1][index2] = -1;
        algoritmoFloyd(); // Recalcular rutas más cortas después de eliminar una arista
    }

    public void imprimirMatriz() {
        System.out.println("Matriz de adyacencia:");
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void algoritmoFloyd() {
        // Implementación del algoritmo de Floyd para calcular la ruta más corta
        matrizDistancias = matriz.clone();
        for (int k = 0; k < tamaño; k++) {
            for (int i = 0; i < tamaño; i++) {
                for (int j = 0; j < tamaño; j++) {
                    if (matrizDistancias[i][k] != -1 && matrizDistancias[k][j] != -1) {
                        int nuevaDistancia = matrizDistancias[i][k] + matrizDistancias[k][j];
                        if (matrizDistancias[i][j] == -1 || nuevaDistancia < matrizDistancias[i][j]) {
                            matrizDistancias[i][j] = nuevaDistancia;
                            matrizRutas[i][j] = k; // Guardar el vértice intermedio
                        }
                    }
                }
            }
        }
    }

    private void imprimirRuta(int indexOrigen, int indexDestino) {
        if (matrizRutas[indexOrigen][indexDestino] == -1) {
            System.out.print(" -> " + indexDestino);
        } else {
            int intermedio = matrizRutas[indexOrigen][indexDestino];
            imprimirRuta(indexOrigen, intermedio);
            imprimirRuta(intermedio, indexDestino);
        }
        
    }

    public void RutaMasCorta(V origen, V destino) {
        int indexOrigen = verticeIndex.get(origen);
        int indexDestino = verticeIndex.get(destino);
        int distancia = matrizDistancias[indexOrigen][indexDestino];
        if (distancia != -1) {
            System.out.println("La distancia más corta entre " + origen + " y " + destino + " es: " + distancia);
            System.out.print("Ruta: " + origen);
            imprimirRuta(indexOrigen, indexDestino);
        } else {
            System.out.println("No hay camino entre " + origen + " y " + destino);
        }
    }

    public V calcularCentro() {
        int indexCentro = -1;
        int minMaxDistancia = Integer.MAX_VALUE;

        for (int i = 0; i < tamaño; i++) {
            int maxDistancia = 0;
            boolean esValido = true;

            for (int j = 0; j < tamaño; j++) {
                if (matrizDistancias[i][j] == -1) {
                    esValido = false; // no hay camino
                    break;
                }
                if (matrizDistancias[i][j] > maxDistancia) {
                    maxDistancia = matrizDistancias[i][j];
                }
            }
            if (esValido && maxDistancia < minMaxDistancia) {
                minMaxDistancia = maxDistancia;
                indexCentro = i;
            }
        }

        if (indexCentro != -1) {
            for (Map.Entry<V, Integer> entrada : verticeIndex.entrySet()) {
                if (entrada.getValue() == indexCentro) {
                    return entrada.getKey();
                }
            }
        }

        System.out.println("No se pudo determinar el centro del grafo.");
        return null;
    }
    
}
