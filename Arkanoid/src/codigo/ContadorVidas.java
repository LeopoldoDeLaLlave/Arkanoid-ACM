/*
 * Autores: Sofía Rico y Javier de la Llave
 * 
 * La clase ContadorVidas crea un label que contendrá
 * las vidas que tiene el jugador
 */

package codigo;

import java.awt.Color;
import acm.graphics.GLabel;




public class ContadorVidas extends GLabel {
	
	/**
	 * 
	 * @param posX: Posición x del contador
	 * @param posY: Posición y del contador
	 */
	public ContadorVidas(int posX, int posY) {
		super("");
		setFont("Times New Roman-20");
		setColor(Color.yellow);
		setLocation(posX, posY);

}
}
