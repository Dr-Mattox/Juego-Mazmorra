package juegoMazmorra;

public class MenuPrincipal {

	
    public void mostrarMenu() {
        while (true) {
			System.out.println("\n\u001B[33m--- Menú Principal ---");
            System.out.println("1. Iniciar Nuevo Juego");
            System.out.println("2. Ver Instrucciones");
            System.out.println("3. Salir del Juego");
            System.out.print("Selecciona una opción:\u001B[0m ");

            int opcion = new java.util.Scanner(System.in).nextInt();

            switch (opcion) {
                case 1:
                    iniciarNuevoJuego();
                    break;
                case 2:
                    mostrarInstrucciones();
                    break;
                case 3:
                    salirDelJuego();
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    private void iniciarNuevoJuego() {
        System.out.println("\nIniciando nuevo juego...");
        AventuraEnLaMazmorra juego = new AventuraEnLaMazmorra();
        juego.jugar(10, 10, null); // Iniciar el juego con mapa y un nuevo jugador
    }

    // Este método puede ser llamado cuando se avanza de nivel
    public void iniciarJuegoConJugadorExistente(Jugador jugador, int filas, int columnas) {
        System.out.println("\nIniciando siguiente nivel...");
        AventuraEnLaMazmorra juego = new AventuraEnLaMazmorra();
        juego.jugar(filas, columnas, jugador); // Mantener salud e inventario del jugador
    }

    private void mostrarInstrucciones() {
        System.out.println("\n--- Instrucciones del Juego ---");
        System.out.println("Te mueves usando las teclas W (arriba), A (izquierda), S (abajo) y D (derecha).");
        System.out.println("Presiona E para abrir el inventario.");
        System.out.println("Tu objetivo es derrotar a todos los enemigos, recoger la llave y salir por la mazmorra.");
        System.out.println("Si tienes muchas pociones de fuerza las puedes intercambiar con el Mercader.");
        System.out.println("Presiona cualquier tecla para volver al menú.");
        new java.util.Scanner(System.in).next(); // Pausar hasta que el usuario presione una tecla
    }

    private void salirDelJuego() {
        System.out.println("\nGracias por jugar. ¡Hasta la próxima!");
        System.exit(0); // Cerrar el programa
    }
}
