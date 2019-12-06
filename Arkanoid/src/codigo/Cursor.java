/*
 * Autores Sof�a Rico y Javier de la Llave
 * 
 * La claseo Cursor define el cursor en el el que rebotar� la bola
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
	 * @param posY: Posici�n Y del cursor, la X siempre ser� 0(Aparecer� a la izquierda de la pantalla)
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
