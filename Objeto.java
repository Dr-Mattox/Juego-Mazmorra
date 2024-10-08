package juegoMazmorra;

public class Mercader {

	public void interactuarConJugador(Jugador jugador) {
		if (jugador.cantidadDePocionesDeFuerza() >= 2) { // primero comprueba que se tengan las suficientes pociones
			System.out.println(
					"Tienes suficientes pociones de fuerza. ¿Deseas intercambiarlas por una poción de regeneración? (s/n)");
			char decision = new java.util.Scanner(System.in).next().charAt(0);
			if (decision == 's' || decision == 'S') {
				jugador.intercambiarPociones(); // Método para realizar el intercambio
				System.out.println(
						"\u001B[35mHas intercambiado 2 pociones de fuerza por 1 poción de regeneración\u001B[0m.");
			} else {
				System.out.println("Decidiste no intercambiar las pociones.");
			}
		} else {
			System.out.println("No tienes suficientes pociones de fuerza para realizar un intercambio."); // si no tiene
																											// dos o mas
																											// no se
																											// puede
																											// intercambiar
		}
	}
}
