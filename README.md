# Aventura En La Mazmorra
### Autor: Alex José De Mattos Pérez  
### Materia: Programación Avanzada (Séptimo Semestre, Ingeniería Mecatrónica)

---

## Descripción del juego
"Aventura En La Mazmorra" es un juego de aventura en consola en el que los jugadores exploran una mazmorra repleta de enemigos, objetos y desafíos. El objetivo principal del juego es derrotar a todos los enemigos en la mazmorra, recolectar la llave que estos dejan al ser vencidos y encontrar la salida para avanzar al siguiente nivel. Los jugadores también pueden interactuar con un mercader para intercambiar objetos y mejorar sus probabilidades de supervivencia en esta aventura.

El juego está diseñado como un proyecto académico para la materia de **Programación Avanzada**, utilizando conceptos de **Programación Orientada a Objetos (POO)**.

---

## Requisitos previos
- **Consola compatible con ANSI**: Asegúrate de estar ejecutando el juego en una consola que soporte colores ANSI, como *Git Bash*, *PowerShell*, o terminales basadas en Unix (Linux/macOS).
- **Java**: El juego está escrito en **Java**, por lo que necesitas tener el **JDK** instalado en tu sistema para compilar y ejecutar el código.
  
---

## Instrucciones de instalación
1. **Descargar el proyecto**:
   Clona o descarga el repositorio del proyecto desde GitHub en tu computadora.

2. **Compilación**:
   Navega a la carpeta raíz del proyecto y compila los archivos `.java` con el siguiente comando:
   ```
   javac -d . src/juegoMazmorra/*.java
   ```

3. **Ejecución**:
   Una vez compilado, ejecuta el juego utilizando el siguiente comando:
   ```
   java juegoMazmorra.Main
   ```

---

## Cómo jugar
### Movimiento y controles básicos:
- Usa las teclas **W**, **A**, **S**, **D** para moverte:
  - **W**: Moverte hacia arriba.
  - **A**: Moverte hacia la izquierda.
  - **S**: Moverte hacia abajo.
  - **D**: Moverte hacia la derecha.
  
- **E**: Presiona esta tecla para abrir el **inventario** y usar pociones.
- **M**: Interactúa con el **Mercader** cuando lo encuentres.

### Objetivo principal:
- Explora la mazmorra buscando enemigos, objetos y la salida.
- **Derrota a todos los enemigos** para conseguir la llave, que es necesaria para desbloquear la salida.
- Usa el inventario de manera estratégica, curándote con pociones de recuperación o aumentando tu poder con pociones de fuerza.
- **Avanza de nivel** al encontrar la salida una vez que tengas la llave.

---

## Mecánicas de juego

### Combate:
- Al encontrarte con un enemigo, el juego automáticamente iniciará un combate.
- Tu personaje comienza con **100 puntos de salud** y un daño base de **15 puntos**.
- Los enemigos pueden ser de dos tipos:
  - **Goblin**: Hace 15 puntos de daño y tiene 50 puntos de salud.
  - **Dragón**: Hace 25 puntos de daño y tiene 70 puntos de salud.

El combate continuará hasta que uno de los dos pierda toda su salud.

### Objetos:
- A lo largo de la mazmorra, podrás encontrar diferentes objetos que se agregarán a tu inventario:
  - **Poción de recuperación**: Restaura 30 puntos de salud.
  - **Poción de fuerza**: Aumenta tu daño en 15 puntos.
  - **Llave**: Solo se obtiene tras derrotar a todos los enemigos. Es necesaria para poder escapar de la mazmorra.

### Interacción con el Mercader:
- El mercader se encuentra siempre en la esquina superior derecha del mapa.
- No podrás caminar sobre su casilla. Al intentar interactuar con él, se abrirá automáticamente el menú de comercio.
- Podrás intercambiar **2 pociones de fuerza** por **1 poción de recuperación**.

---

## Información adicional

### Colores:
- El juego utiliza colores para mejorar la experiencia visual:
  - **Menú principal**: Todo el texto es de color **amarillo**.
  - **Salud del jugador**: Siempre se muestra en color **verde**.
  - **Enemigos**: Los nombres de los enemigos se muestran en color **rojo** cuando los encuentras.
  - **Objetos**: Cuando recoges una poción, el texto aparece en **azul**.
  - **Mercader**: Al encontrarte con el mercader, la palabra **mercader** aparece en **morado**.

### Niveles:
- Cada vez que completas un nivel, avanzas a otro con un mapa más grande. La mazmorra crece en **5 columnas y 5 filas** adicionales con cada nuevo nivel.
- Tu salud y objetos se mantienen al pasar de nivel, pero la llave se elimina, y deberás derrotar a todos los enemigos de nuevo para obtener una nueva llave.

---

## Créditos
**Aventura En La Mazmorra** fue desarrollado por **Alex José De Mattos Pérez** como parte de un proyecto académico en la materia de **Programación Avanzada**, durante el **séptimo semestre** de la carrera de **Ingeniería Mecatrónica**.

---

Si tienes alguna duda o sugerencia, no dudes en comunicarte con el autor. ¡Disfruta la aventura!

---
