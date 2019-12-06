/*
 * Autores: Sof�a Rico y Javier de la Llave
 * 
 * La clase ContadorVidas crea un label que contendr�
 * las vidas que tiene el jugador
 */

package codigo;

import java.awt.Color;
import acm.graphics.GLabel;




public class ContadorVidas extends GLabel {
	
	/**
	 * 
	 * @param posX: Posici�n x del contador
	 * @param posY: Posici�n y del contador
	 */
	public ContadorVidas(int posX, int posY) {
		super("");
		setFont("Times New Roman-20");
		setColor(Color.yellow);
		setLocation(posX, posY);

}
}
