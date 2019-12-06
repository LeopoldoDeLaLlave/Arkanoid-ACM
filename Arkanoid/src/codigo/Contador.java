/*
 * Autores: Sofía Rico y Javier de la Llave
 * 
 * La clase Contador crea un label que contendrá
 * la puntuación que lleva el jugador, se obtendrá 
 *  puntos si se golpea un ladrillo o se coge un 
 *  bonus y 10 si rompe un ladrillo
 */

package codigo;

import java.awt.Color;
import acm.graphics.GLabel;


public class Contador extends GLabel  {


	/**
	 * 
	 * @param posX posición x del contador
	 * @param posY posición y del contador
	 */
	public Contador(int posX, int posY) {
		super("");
		setFont("Times New Roman-20");
		setColor(Color.yellow);
		setLocation(posX, posY);
	}


	
	
}
