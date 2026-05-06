public class Main {
    public static void main(String[] args) throws Exception {
        TxtReader reader = new TxtReader();
        String[] caminos = reader.readFile("src\\guategrafo.txt", "\n");

        Digrafo<String, Integer> digrafo = new Digrafo<>(caminos.length * 2);

        // Agregar vértices y aristas al grafo
        for (int i = 0; i < caminos.length; i++) {
            String[] partes = caminos[i].split(" ");
            digrafo.addVertice(partes[0].strip());
            digrafo.addVertice(partes[1].strip());
            digrafo.addArista(partes[0].strip(), partes[1].strip(), Integer.parseInt(partes[2].strip()));
        }

        digrafo.imprimirMatriz();
    }
}
