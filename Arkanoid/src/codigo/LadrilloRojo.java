/*
 * Autores: Sof�a Rico y Javier de la Llave
 * 
 * fantasmas que hacen la funci�n de ladrillos
 */
package codigo;

import acm.graphics.GImage;

public class LadrilloRojo extends GImage {
	public int vidas = 0;
	GImage imagen;
	
	/**
	 * 
	 * @param posX: posici�n x del fantasma
	 * @param posY: posici�n y del fantasma
	 * @param ancho: Ancho del fantasma
	 * @param alto: Alto del fantasma
	 * @param ladrillo: Direccion de la foto que va a hacer de ladrillo
	 */
	public LadrilloRojo(int posX, int posY, double ancho, double alto, String ladrillo){
		
		super(ladrillo);
		setSize(ancho, alto);
		setLocation(posX, posY);
		
	}
}
