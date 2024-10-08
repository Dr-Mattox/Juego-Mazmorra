package juegoMazmorra;

public abstract class Enemigo { // es una clase abstracta porque hay dos enemigos, y tienen los mismos metodos

	// Atributos
	protected int salud;
	protected int daño;

	// Constructor
	public Enemigo(int salud, int daño) {
		this.salud = salud;
		this.daño = daño;
	}

	public int getSalud() { // Getter
		return salud;
	}

	public int getDaño() { // Getter
		return daño;
	}

	public void recibirDaño(int daño) {
		this.salud -= daño;
	}

	public boolean estaMuerto() {
		return this.salud <= 0;
	}
}
