/*
 * Autores: Sofía Rico y Javier de la Llave
 * 
 * Al romper un ladrillo puede empezar a caer un bonus que si lo
 * recoge el cursor lo encogerá 20 píxeles respecto al tamaño inicial
 * y lo pondrá de color verde.
 */
package codigo;



import acm.graphics.GImage;
import acm.graphics.GObject;


public class BonusBolas extends GImage {


	/**
	 * 
	 * @param posX: Posición x de la que parte el bonus
	 * @param posY: Posición y de la que parte el bonus
	 * @param ancho: Ancho del bonus.
	 * @param alto: Alto del bonus.
	 * @param bonus: Direccion de la imagen que hará de bonus.
	 */
	public BonusBolas(double posX, double posY, double ancho, double alto, String bonus) {
		super(bonus);
		setSize(ancho, alto);
		setLocation(posX, posY);

	}

	/*
	 * Chequea si bonus y cursor han chocado y en caso afirmativo lleva a cabo el efecto del bonus
	 */
	public void efectoBonus(Arkanoid a ){

		//Chequeo si la bola ha chocado con el cursor
		//Le mandó varios puntos de la bola para un mayor precisión

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
	 * Este método comprueba si el bonus ha chocado con el cursor
	 * y llava a cabo el efecto del bonus
	 */
	private boolean chequeaColision(double posX, double posY, Arkanoid arkanoid){
		boolean noHaChocado = true;

		GObject aux = arkanoid.getElementAt(posX,posY);




		if(aux instanceof Cursor){//Si el bonus se choca con el cursor


			setLocation(1000,0);
			arkanoid.puntos += 5;//Coger un bonus da 5 puntos.
			arkanoid.contador.setLabel("Puntuacion: " + arkanoid.puntos);//cambíamos el valor de la etiqueta con los puntos

			if(arkanoid.nBolas == 1 && arkanoid.bola2.vx == 0 && arkanoid.bola3.vx == 0 ){//Solo se ejecutará si hay solo una bola y esa bola es bola1
				arkanoid.bola2.setLocation(10,40);
				arkanoid.bola2.vx = 1;
				arkanoid.bola2.vy = 1;

				arkanoid.bola3.setLocation(arkanoid.getWidth()-20,40);
				arkanoid.bola3.vx = -1;
				arkanoid.bola3.vy = 1;

				arkanoid.nBolas = 3;

			}else if(arkanoid.nBolas == 1 && arkanoid.bola1.vx ==0 && arkanoid.bola3.vx == 0 ){//Solo se ejecutará si hay solo una bola y esa bola es bola2
				arkanoid.bola1.setLocation(10,40);
				arkanoid.bola1.vx = 1;
				arkanoid.bola1.vy = 1;

				arkanoid.bola3.setLocation(arkanoid.getWidth()-20,40);
				arkanoid.bola3.vx = -1;
				arkanoid.bola3.vy = 1;

				arkanoid.nBolas = 3;

			}else if(arkanoid.nBolas == 1 && arkanoid.bola2.vx ==0 && arkanoid.bola1.vx == 0 ){//Solo se ejecutará si hay solo una bola y esa bola es bola3
				arkanoid.bola2.setLocation(10,40);
				arkanoid.bola2.vx = 1;
				arkanoid.bola2.vy = 1;

				arkanoid.bola1.setLocation(arkanoid.getWidth()-20,40);
				arkanoid.bola1.vx = -1;
				arkanoid.bola1.vy = 1;

				arkanoid.nBolas = 3;

			}else if(arkanoid.nBolas == 2 && arkanoid.bola1.vx ==0 ){//Solo se ejecutará si hay dos bolas y no es la 1
				arkanoid.bola1.setLocation(10,40);
				arkanoid.bola1.vx = 1;
				arkanoid.bola1.vy = 1;

				arkanoid.nBolas = 3;

			}else if(arkanoid.nBolas == 2 && arkanoid.bola2.vx ==0 ){//Solo se ejecutará si hay dos bolas y no es la 2
				arkanoid.bola2.setLocation(10,40);
				arkanoid.bola2.vx = 1;
				arkanoid.bola2.vy = 1;

				arkanoid.nBolas = 3;

			}else if(arkanoid.nBolas == 2 && arkanoid.bola3.vx ==0 ){//Solo se ejecutará si hay dos bolas y no es la 3
				arkanoid.bola3.setLocation(10,40);
				arkanoid.bola3.vx = 1;
				arkanoid.bola3.vy = 1;

				arkanoid.nBolas = 3;

			}

			noHaChocado = false;
		}
		return noHaChocado;
	}
}


