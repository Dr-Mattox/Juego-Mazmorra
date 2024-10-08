package juegoMazmorra;

import java.util.ArrayList;

public class Jugador {

	// Atributos
	private int salud;
	private int daño;
	private boolean tieneLlave;
	private ArrayList<Objeto> inventario; // al importar la libreria de Arraylist se puede hacer uso de las listas como
											// tipos de objeto
	private boolean fuerzaAumentada;

	// Constructor
	public Jugador() {
		this.salud = 100;
		this.daño = 15; // Daño base
		this.inventario = new ArrayList<>();
		this.tieneLlave = false;
		this.fuerzaAumentada = false;
	}

	public int getSalud() { // Getter
		return salud;
	}

	public int getDaño() { // Getter
		return daño;
	}

	public boolean estaVivo() {
		return salud > 0; // Verifica si el jugador sigue vivo
	}

	public void recibirDaño(int daño) {
		this.salud -= daño;
	}

	public void recogerObjeto(Objeto objeto) {
		inventario.add(objeto);
	}

	public void usarPocionRecuperacion() {
		this.salud = Math.min(this.salud + 30, 100); // Se suma asi para que no se exceda de 100 puntos de vida
		System.out.println("Usaste una poción de recuperación. Salud: " + this.salud);
	}

	public void usarPocionFuerza() {
		if (!fuerzaAumentada) { // solo se puede usar una pocion de fuerza, si no seria muy facil matar a todos
								// los enemigos
			this.daño += 15;
			fuerzaAumentada = true;
			System.out.println("Usaste una poción de fuerza. Daño: " + this.daño);
		} else {
			System.out.println("Ya has usado una poción de fuerza.");
		}
	}

	public boolean tieneLlave() { // regresa el atributo
		return tieneLlave;
	}

	public void recogerLlave() { // tarnsoforma el atributo de tienellave a true
		this.tieneLlave = true;
	}

	public ArrayList<Objeto> getInventario() {
		return inventario;
	}

	public void usarObjeto(int indice) { // se le da el numero que aparece en la lista de objetos
		if (indice >= 0 && indice < inventario.size()) {
			Objeto objeto = inventario.remove(indice);
			if (objeto instanceof PocionRecuperacion) { // checa que tipo de pocion es para aplicar los efectos
														// correspondientes
				usarPocionRecuperacion();
			} else if (objeto instanceof PocionFuerza) {
				usarPocionFuerza();
			}
		} else {
			System.out.println("Objeto no válido.");
		}
	}

	// Lógica para eliminar la llave
	public void eliminarLlave() {
		this.tieneLlave = false;
	}

	// Lógica para contar las pociones de fuerza en el inventario
	public int cantidadDePocionesDeFuerza() {
		int cantidad = 0;
		for (Objeto objeto : inventario) {
			if (objeto instanceof PocionFuerza) {
				cantidad++;
			}
		}
		return cantidad;
	}

	// Método para intercambiar pociones de fuerza por una poción de recuperación
	public void intercambiarPociones() {
		int pocionesEliminadas = 0;
		for (int i = 0; i < inventario.size(); i++) {
			if (inventario.get(i) instanceof PocionFuerza) {
				inventario.remove(i);
				i--; // Ajustar índice al eliminar
				pocionesEliminadas++;
				if (pocionesEliminadas == 2) { // comprueba que si se eliminanron 2 pociones de fuerza
					break;
				}
			}
		}
		inventario.add(new PocionRecuperacion()); // despues del bucle te agrega una de recuperacion
	}
}
