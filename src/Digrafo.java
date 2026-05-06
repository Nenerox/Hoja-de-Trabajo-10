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
        this.verticeIndex = new HashMap<>();
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
    }

    public void addArista(V Vinicio, V Vfinal, E label){
        int index1 = verticeIndex.get(Vinicio);
        int index2 = verticeIndex.get(Vfinal);
        matriz[index1][index2] = (Integer) label;
    }

    public void removeVertice(V vertice){
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
        int index1 = verticeIndex.get(Vinicio);
        int index2 = verticeIndex.get(Vfinal);
        matriz[index1][index2] = -1;
        algoritmoFloyd(); // Recalcular rutas más cortas después de eliminar una arista
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

    public void calcularCentro() {
        // Implementación para calcular el centro del grafo
    }
    
}
