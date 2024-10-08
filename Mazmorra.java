package juegoMazmorra;

import java.util.ArrayList;
import java.util.Random; //Biblioteca que proporciona numeros random

public class Mazmorra {

	// Atributos
	private char[][] mapa;
	private int filas;
	private int columnas;
	private Jugador jugador;
	private ArrayList<Enemigo> enemigos;
	private ArrayList<Objeto> objetos;
	private Llave llave;
	private int enemigosDerrotados;
	private int movimientosRealizados;
	private Random random;
	private AventuraEnLaMazmorra controlador; // Referencia al controlador para mantener un orden
	private Mercader mercader; // Nuevo mercader
	private int mercaderX, mercaderY; // Coordenadas del Mercader

	// Constructor
	public Mazmorra(int filas, int columnas, Jugador jugadorExistente, AventuraEnLaMazmorra controlador) {
		this.filas = filas;
		this.columnas = columnas;
		this.mapa = new char[filas][columnas];
		this.jugador = jugadorExistente; // Mantener el jugador existente
		this.enemigos = new ArrayList<>();
		this.objetos = new ArrayList<>();
		this.random = new Random();
		this.llave = null;
		this.enemigosDerrotados = 0;
		this.movimientosRealizados = 0;
		this.controlador = controlador; // Inicializar referencia al controlador
		inicializarMazmorra();
	}

	// Método para inicializar el mapa y colocar enemigos, objetos y paredes
	// aleatorias
	private void inicializarMazmorra() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (i == 0 || i == filas - 1 || j == 0 || j == columnas - 1) {
					mapa[i][j] = '#'; // Pared exterior
				} else {
					mapa[i][j] = '.'; // Espacio vacío //primero pone todo vacio
				}
			}
		}

		// Definir la casilla de la salida
		int salidaX = filas - 2;
		int salidaY = columnas - 2;
		mapa[salidaX][salidaY] = 'S'; // Salida

		// Evitar la generación alrededor del jugador
		int jugadorX = 1;
		int jugadorY = 1;

		// Generar enemigos, objetos y paredes aleatoriamente
		for (int i = 1; i < filas - 1; i++) {
			for (int j = 1; j < columnas - 1; j++) {
				// Evitar generar cosas alrededor del jugador o de la salida
				if ((i == salidaX && j == salidaY) || (i == salidaX - 1 && j == salidaY)
						|| (i == salidaX && j == salidaY - 1) || (i == salidaX - 1 && j == salidaY - 1)
						|| (i == jugadorX && j == jugadorY) || (i == jugadorX + 1 && j == jugadorY)
						|| (i == jugadorX && j == jugadorY + 1) || (i == jugadorX + 1 && j == jugadorY + 1)) {
					continue; // No generar nada en la salida, jugador ni sus alrededores
				}

				if (random.nextInt(100) < 10) { // 10% probabilidad de generar algo
					if (random.nextInt(100) < 50) {
						Enemigo enemigo = random.nextBoolean() ? new Goblin() : new Dragon(); // Goblin o Dragón
						enemigos.add(enemigo);
						mapa[i][j] = 'E'; // Colocar enemigo
					} else {
						Objeto objeto = random.nextBoolean() ? new PocionRecuperacion() : new PocionFuerza(); // Poción
																												// de
																												// recuperación
																												// o
																												// fuerza
						objetos.add(objeto);
						mapa[i][j] = 'O'; // Colocar objeto
					}
				} else if (random.nextInt(100) < 15) {
					mapa[i][j] = '#'; // Generar pared aleatoria con 15% de probabilidad
				}
			}
		}

		// Posicionar al jugador
		mapa[jugadorX][jugadorY] = 'J';

		// Generar Mercader en la esquina superior derecha sin objetos, enemigos ni
		// paredes alrededor
		mercaderX = 1;
		mercaderY = columnas - 2;
		mapa[mercaderX][mercaderY] = 'M'; // Mercader
		mercader = new Mercader(); // Crear mercader
	}

	public void interactuarConMercader() {
		if (mercader != null) {
			mercader.interactuarConJugador(jugador);
		}
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void mostrarMapa() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (mapa[i][j] == 'J') {
					System.out.print("\u001B[32m" + mapa[i][j] + "\u001B[0m"); // Verde para el jugador
				} else if (mapa[i][j] == 'S') {
					System.out.print("\u001B[33m" + mapa[i][j] + "\u001B[0m"); // Amarillo para la salida
				} else if (mapa[i][j] == 'E') {
					System.out.print("\u001B[31m" + mapa[i][j] + "\u001B[0m"); // Rojo para enemigos
				} else if (mapa[i][j] == 'O') {
					System.out.print("\u001B[34m" + mapa[i][j] + "\u001B[0m"); // Azul para objetos
				} else if (mapa[i][j] == 'M') {
					System.out.print("\u001B[35m" + mapa[i][j] + "\u001B[0m"); // Morado para el mercader
				} else if (mapa[i][j] == '#') {
					System.out.print("\u001B[30m" + mapa[i][j] + "\u001B[0m"); // Negro para las paredes
				} else {
					System.out.print(mapa[i][j]);
				}
			}
			System.out.println();
		}
	}

	public int getEnemigosDerrotados() {
		return enemigosDerrotados;
	}

	public int getMovimientosRealizados() {
		return movimientosRealizados;
	}

	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}

	private int[] encontrarJugador() {
		for (int i = 0; i < filas; i++) {
			for (int j = 0; j < columnas; j++) {
				if (mapa[i][j] == 'J') {
					return new int[] { i, j }; // Devuelve las coordenadas del jugador
				}
			}
		}
		return null; // Si no se encuentra al jugador (aunque no debería ocurrir)
	}

	// Método para mover al jugador
	public void moverJugador(char direccion) {
		int[] posicion = encontrarJugador();
		if (posicion == null) {
			return; // Asegurarse de que el jugador esté en el mapa
		}
		int x = posicion[0];
		int y = posicion[1];
		int nuevoX = x;
		int nuevoY = y;

		switch (Character.toLowerCase(direccion)) {
		case 'w':
			nuevoX = x - 1;
			break;
		case 'a':
			nuevoY = y - 1;
			break;
		case 's':
			nuevoX = x + 1;
			break;
		case 'd':
			nuevoY = y + 1;
			break;
		}

		if (nuevoX >= 0 && nuevoX < filas && nuevoY >= 0 && nuevoY < columnas && mapa[nuevoX][nuevoY] != '#') {
			if (mapa[nuevoX][nuevoY] == 'S' && !jugador.tieneLlave()) {
				System.out.println("Necesitas una \u001B[33mllave\u001B[0m para abrir la salida.");
				return; // No permitir al jugador moverse a la salida si no tiene la llave
			}

			if (nuevoX == mercaderX && nuevoY == mercaderY) {
				System.out.println("Te has encontrado con el \u001B[35mMercader\u001B[0m.");
				interactuarConMercader(); // Interactuar directamente con el mercader
				return; // No permitir moverse sobre el mercader
			}

			mapa[x][y] = '.'; // Limpiar la posición anterior
			movimientosRealizados++; // Aumentar el contador de movimientos

			if (mapa[nuevoX][nuevoY] == 'E') {
				iniciarCombate(nuevoX, nuevoY); // Iniciar combate en la posición actual
			} else if (mapa[nuevoX][nuevoY] == 'O') {
				Objeto objeto = obtenerObjetoEnPosicion(nuevoX, nuevoY);
				jugador.recogerObjeto(objeto);
				System.out.println("Has encontrado una \u001B[34m" + objeto.getNombre() + ".\u001B[0m");
			} else if (mapa[nuevoX][nuevoY] == 'S') {
				if (jugador.tieneLlave()) {
					controlador.mostrarMensajeVictoria(); // Llamar al método de la clase AventuraEnLaMazmorra
				}
			}
			mapa[nuevoX][nuevoY] = 'J'; // Nueva posición del jugador
		} else {
			System.out.println("No puedes moverte en esa dirección.");
		}
	}

	private void iniciarCombate(int x, int y) {
		Enemigo enemigo = enemigos.remove(0); // Eliminar el primer enemigo de la lista
		String tipoEnemigo = (enemigo instanceof Goblin) ? "goblin" : "dragón";
		// Nombres de enemigos en color rojo
		System.out.println("¡Te has encontrado con un \u001B[31m" + tipoEnemigo + "\u001B[0m!");

		while (jugador.getSalud() > 0 && !enemigo.estaMuerto()) {
			System.out.println("Tu salud: " + jugador.getSalud());
			System.out.println("Salud del " + tipoEnemigo + ": " + enemigo.getSalud());

			enemigo.recibirDaño(jugador.getDaño());

			if (!enemigo.estaMuerto()) {
				jugador.recibirDaño(enemigo.getDaño());
			}
		}

		if (jugador.getSalud() > 0) {
			System.out.println("Has derrotado al " + tipoEnemigo + ".");
			mapa[x][y] = '.'; // Eliminar al enemigo del mapa
			enemigosDerrotados++; // Aumentar el contador de enemigos derrotados
			verificarEnemigosRestantes();
		} else {
			System.out.println("\n\u001B[31mHas sido derrotado...\u001B[0m");
		}
	}

	private Objeto obtenerObjetoEnPosicion(int x, int y) {
		for (Objeto objeto : objetos) {
			if (mapa[x][y] == 'O') {
				objetos.remove(objeto); // Remover el objeto del mapa
				return objeto;
			}
		}
		return null;
	}

	private void verificarEnemigosRestantes() {
		if (enemigos.isEmpty() && llave == null) {
			llave = new Llave();
			jugador.recogerLlave();
			System.out.println("\n¡Has derrotado a todos los enemigos y obtuviste la \u001B[33mllave\u001B[0m!");
		}
	}
}
