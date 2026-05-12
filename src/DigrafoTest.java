import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class DigrafoTest {

    private Digrafo<String, Integer> digrafo;
    private ByteArrayOutputStream salida;

    @Before
    public void setUp() {
        digrafo = new Digrafo<>(10);

        digrafo.addVertice("A");
        digrafo.addVertice("B");
        digrafo.addVertice("C");
        digrafo.addVertice("D");

        digrafo.addArista("A", "B", 3);
        digrafo.addArista("B", "C", 4);
        digrafo.addArista("A", "C", 10);
        digrafo.addArista("C", "D", 2);

        // Capturar salida para verificar resultados
        salida = new ByteArrayOutputStream();
        System.setOut(new PrintStream(salida));
    }
    
    @Test
    public void testRutaMasCorta() {
        digrafo.RutaMasCorta("A", "C");

        String output = salida.toString();
        assertTrue(output.contains("7")); // A -> B -> C = 7
    }

    @Test
    public void testRutaIntermedia() {
        digrafo.RutaMasCorta("A", "D");

        String output = salida.toString();
        assertTrue(output.contains("A"));
        assertTrue(output.contains("B"));
        assertTrue(output.contains("C"));
        assertTrue(output.contains("D"));
    }

    @Test
    public void testEliminarArista() {
        digrafo.removeArista("A", "B");

        digrafo.RutaMasCorta("A", "B");

        String output = salida.toString();
        assertTrue(output.contains("No hay camino"));
    }

    @Test
    public void testSinConexion() {
        digrafo.addVertice("E");
        digrafo.RutaMasCorta("A", "E");

        String output = salida.toString();
        assertTrue(output.contains("No hay camino"));
    }
}
