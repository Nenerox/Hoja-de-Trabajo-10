# Hoja-de-Trabajo-10 Algoritmo de Floyd en Grafos Dirigidos

Este proyecto implementa un grafo dirigido  y aplica el algoritmo de Floyd para calcular la ruta más corta entre cualquier par de ciudades.

Además, permite determinar el centro del grafo, es decir, la ciudad cuya distancia máxima al resto de ciudades es mínima.

El sistema simula una red de carreteras en Guatemala, donde los nodos representan ciudades y las aristas representan distancias en kilómetros.

## Formato del archivo de entrada

El archivo .txt debe contener una conexión por línea:

Ciudad1 Ciudad2 Distancia

### Ejemplo:

Guatemala Antigua 45

Antigua Escuintla 25

Escuintla SantaLucia 15

* El grafo es **dirigido**
* Las distancias son enteros (KM)
* No usar espacios en nombres de ciudades

## Funcionalidades

### 1. Ruta más corta

Permite ingresar dos ciudades y muestra:

* Distancia mínima
* Ruta completa

### 2. Centro del grafo

Calcula la ciudad más "céntrica" del grafo:

### 3. Modificación del grafo

* Eliminar conexión entre ciudades
* Agregar nueva conexión con peso

### 4. Mostrar matrices

* Matriz de adyacencia
* Matriz de distancias (resultado de Floyd)

## Consideraciones

* El grafo debe estar conectado para calcular el centro
* El algoritmo recalcula rutas después de cada modificación
* Los nombres de ciudades deben coincidir exactamente
