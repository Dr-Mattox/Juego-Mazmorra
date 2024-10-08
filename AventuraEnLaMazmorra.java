package juegoMazmorra;

import java.util.Scanner;

public class AventuraEnLaMazmorra { // clase que le da jugabilidad a la Mazmorra

	// Atributos
	private Mazmorra mazmorra; // La mazmorra es uno de sus atributos
	private Jugador jugador; // tambien el jugador
	private int nivel;
	private Scanner scanner; // Utiliza la biblioteca de Scanner para poder leer datos de entrada del teclado
	private MenuPrincipal menuPrincipal;

	// Constructor
	public AventuraEnLaMazmorra() {
		this.scanner = new Scanner(System.in); // Se abre un scanner para poder leer los datos de entrada
		this.nivel = 1; // Inicia en el nivel 1
		this.menuPrincipal = new MenuPrincipal(); // Para manejar la navegación
	}

	public void jugar(int filas, int columnas, Jugador jugadorExistente) {
		// Mantener el jugador existente entre niveles
		if (jugadorExistente != null) {
			this.jugador = jugadorExistente;
		} else {
			this.jugador = new Jugador(); // Nuevo jugador si es el primer nivel
		}

		mazmorra = new Mazmorra(filas, columnas, this.jugador, this); // Pasar referencia de esta clase al constructor

		while (mazmorra.getJugador().estaVivo()) {
			mazmorra.mostrarMapa();
			// Salud del jugador en color verde
			System.out.println("\u001B[32mSalud del jugador: " + mazmorra.getJugador().getSalud() + "\u001B[0m");
			System.out.println("W(arriba), A(izquierda), S(abajo), D(derecha), E(abrir inventario)");

			char accion = scanner.next().charAt(0);

			if (accion == 'e' || accion == 'E') {
				abrirInventario(); // Método para abrir inventario
			} else {
				mazmorra.moverJugador(accion);
			}
		}

		// Si el jugador es derrotado
		mostrarOpcionesDerrota();
	}

	// Método para mostrar las opciones al ganar
	public void mostrarMensajeVictoria() {
		System.out.println("\n\u001B[35m*******************************************");
		System.out.println("*              ¡VICTORIA!                 *");
		System.out.println("*******************************************\u001B[0m");

		System.out.println("\n\u001B[35mEstadísticas del juego:");
		System.out.println("Enemigos derrotados: " + mazmorra.getEnemigosDerrotados());
		System.out.println("Movimientos realizados: " + mazmorra.getMovimientosRealizados());
		System.out.println("*******************************************\u001B[0m\n");

		// Preguntar si quiere pasar al siguiente nivel o regresar al menú
		System.out.println("\u001B[33m¿Deseas avanzar al siguiente nivel o regresar al menú principal?");
		System.out.println("1. Avanzar al siguiente nivel");
		System.out.println("2. Regresar al menú principal\u001B[0m");

		int opcion = scanner.nextInt();
		if (opcion == 1) {
			avanzarSiguienteNivel(); // Avanzar al siguiente nivel
		} else if (opcion == 2) {
			regresarAlMenu(); // Regresar al menú principal
		} else {
			System.out.println("Opción no válida.");
			mostrarMensajeVictoria(); // Volver a mostrar las opciones si se elige incorrectamente
		}
	}

	// Método para avanzar al siguiente nivel
	private void avanzarSiguienteNivel() {
		nivel++; // Aumenta el nivel
		jugador.eliminarLlave(); // Eliminar la llave
		int nuevasFilas = mazmorra.getFilas() + 5;
		int nuevasColumnas = mazmorra.getColumnas() + 5;
		System.out.println(
				"Avanzando al nivel " + nivel + " con un mapa de " + nuevasFilas + "x" + nuevasColumnas + "...");
		menuPrincipal.iniciarJuegoConJugadorExistente(jugador, nuevasFilas, nuevasColumnas); // Usar el método del menú
	}

	private void abrirInventario() {
		System.out.println("\n\u001B[36mInventario:");
		for (int i = 0; i < mazmorra.getJugador().getInventario().size(); i++) {
			System.out.println((i + 1) + ". " + mazmorra.getJugador().getInventario().get(i).getNombre());
		}
		System.out.println("Selecciona un objeto para usar o presiona 'E' para salir del inventario.\u001B[0m\n");

		while (true) {
			String input = scanner.next();
			if (input.equalsIgnoreCase("e")) {
				break; // Salir del inventario
			} else {
				try {
					int indice = Integer.parseInt(input) - 1;
					mazmorra.getJugador().usarObjeto(indice);
				} catch (NumberFormatException e) {
					System.out.println("Entrada no válida.");
				}
			}
		}
	}

	// Método para mostrar las opciones al ser derrotado
	private void mostrarOpcionesDerrota() {
		System.out.println("\n\u001B[33m¿Deseas regresar al menú principal?");
		System.out.println("1. Sí");
		System.out.println("2. No, salir del juego\u001B[0m ");

		int opcion = scanner.nextInt();
		if (opcion == 1) {
			MenuPrincipal menu = new MenuPrincipal();
			menu.mostrarMenu();
		} else if (opcion == 2) {
			System.out.println("Gracias por jugar. ¡Hasta la próxima!");
			System.exit(0);
		} else {
			System.out.println("Opción no válida.");
			mostrarOpcionesDerrota(); // Volver  a mostrar las opciones si se elige incorrectamente
		}
	}

	public static void regresarAlMenu() {
		System.out.println("¿Deseas regresar al menú principal? (s/n)");
		Scanner scanner = new Scanner(System.in);
		char opcion = scanner.next().charAt(0);
		if (opcion == 's' || opcion == 'S') {
			MenuPrincipal menu = new MenuPrincipal();
			menu.mostrarMenu();
		} else {
			System.out.println("Gracias por jugar. ¡Hasta la próxima!");
			System.exit(0);
		}
	}
}
