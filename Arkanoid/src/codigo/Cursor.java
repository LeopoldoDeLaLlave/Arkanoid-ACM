/*
 * Autores Sofía Rico y Javier de la Llave
 * 
 * La claseo Cursor define el cursor en el el que rebotará la bola
 */

package codigo;
import java.awt.Color;

/*
 * Autor: JAvier de la Llave
 */
import acm.graphics.GRect;

public class Cursor extends GRect {

	
	/**
	 * 
	 * @param posY: Posición Y del cursor, la X siempre será 0(Aparecerá a la izquierda de la pantalla)
	 * @param ancho: ancho del cursor
	 * @param alto: alto del cursor
	 * @param color: color del cursor
	 */
	public Cursor(int posY, double ancho, double alto, Color color){
		
		super(ancho, alto); 
		
		setFilled(true);
		setFillColor(color);
		setLocation(0, posY);
		
	}
	
	
	
	
}
