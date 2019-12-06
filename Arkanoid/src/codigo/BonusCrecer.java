/*
 * Autores: Sof�a Rico y Javier de la Llave
 * 
 * Al romper un ladrillo puede empezar a caer un bonus que si lo
 * recoge el cursor lo ensanchar� 20 p�xeles respecto al tama�o inicial
 * y lo pondr� de color rojo.
 */
package codigo;

import java.awt.Color;

import acm.graphics.GImage;
import acm.graphics.GObject;


public class BonusCrecer extends GImage {

	/**
	 * @param posX: Posici�n x de la que parte el bonus
	 * @param posY: Posici�n y de la que parte el bonus
	 * @param ancho: Ancho del bonus.
	 * @param alto: Alto del bonus.
	 * @param bonus: Direccion de la imagen que har� de bonus.
	 */
	public BonusCrecer(double posX, double posY, double ancho, double alto, String bonus) {
		super(bonus);
		setSize(ancho, alto);
		setLocation(posX, posY);

	}


	/*
	 * Chequea si bonus y cursor han chocado y en caso afirmativo lleva a cabo el efecto del bonus
	 */
	public void efectoBonus(Arkanoid a ){
		//move(0,0.65);

		//Chequeo si la bola ha chocado con el cursor
		//Le mand� varios puntos de la bola para un mayor precisi�n

		if(chequeaColision(getX()-1, getY()  + getHeight()+1, a)){
			if(chequeaColision(getX()+getWidth()+1, getY()  + getHeight()+1, a)){
				if(chequeaColision(getX()+getWidth()/2, getY()  + getHeight()+1, a)){
					if(chequeaColision(getX()+getWidth()/4, getY()  + getHeight()+1, a)){
						if(chequeaColision(getX()+getWidth()*(3/4), getY()  + getHeight()+1, a)){
							if(chequeaColision(getX()-1, getY()-1, a)){
								if(chequeaColision(getX()+getWidth()+1, getY()-1, a)){
									if(chequeaColision(getX()+getWidth()/2, getY()-1, a)){
										if(chequeaColision(getX()+getWidth()/4, getY()-1, a)){
											if(chequeaColision(getX()+getWidth()*(3/4), getY()-1, a)){
												if(chequeaColision(getX()-1, getY()+getHeight()/2, a)){
													if(chequeaColision(getX()-1, getHeight()/4, a)){
														if(chequeaColision(getX()+getWidth(), getHeight()*(3/4), a)){
															if(chequeaColision(getX()+getWidth(), getY()+getHeight()/2, a)){
																if(chequeaColision(getX()+getWidth()+1, getHeight()/4, a)){
																	if(chequeaColision(getX()+getWidth()+1, getHeight()*(3/4), a)){

																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
	}



	/*
	 * Este m�todo comprueba si el bonus ha chocado con el cursor
	 * y lleva a cabo el efecto
	 */
	private boolean chequeaColision(double posX, double posY, Arkanoid arkanoid){

		boolean noHaChocado = true;

		GObject aux = arkanoid.getElementAt(posX,posY);

		if(aux instanceof Cursor){//Si el bonus se choca con el cursor


			((Cursor) aux).setSize(120, aux.getHeight());
			((Cursor) aux).setFillColor(Color.lightGray);
			setLocation(1000,0);
			arkanoid.puntos += 5;//Coger un bonus da 5 puntos.		

			arkanoid.contador.setLabel("Puntuacion: " + arkanoid.puntos);//camb�amos el valor de la etiqueta con los puntos

			noHaChocado = false;
		}
		return noHaChocado;
	}

}


