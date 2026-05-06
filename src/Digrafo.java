import java.util.HashMap;
import java.util.Map;

public class Digrafo<V, E> {
    
	private int matriz[][];
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
    }

    public void removeArista(V Vinicio, V Vfinal){
        int index1 = verticeIndex.get(Vinicio);
        int index2 = verticeIndex.get(Vfinal);
        matriz[index1][index2] = -1;
    }

    public void imprimirMatriz() {
        for (int i = 0; i < tamaño; i++) {
            for (int j = 0; j < tamaño; j++) {
                System.out.print(matriz[i][j] + " ");
            }
            System.out.println();
        }
    }
}
