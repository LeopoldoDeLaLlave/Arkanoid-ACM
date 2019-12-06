/*
 * Autor:Sofía Rico y Javier de la Llave
 * 
 * Clase que controla la bola del juego Arkanoid
 * define su comportamiento con otros objetos del
 * juego(pared, techo, ladrillo, etc).
 */

package codigo;

import java.awt.Color;

import acm.graphics.GLabel;
import acm.graphics.GObject;
import acm.graphics.GOval;
import acm.util.RandomGenerator;



public class Bola extends GOval {

	//Píxeles que se va amover la bola cada vez que se ejecute el while.
	public double vx = 1;
	public  double vy = -1;

	boolean vivo = true;

	int posXFantasma;
	int posYFantasma;

	double bonusY = 1000;
	double bonusX;

	BonusCrecer bonusCrecer = new BonusCrecer(bonusX, bonusY, 20.0, 20.0, "imagenes/fresa2.png");
	BonusEncoger bonusEncoger = new BonusEncoger(bonusX, bonusY, 20.0, 20.0, "imagenes/naranja.png");
	BonusBolas bonusBolas = new BonusBolas(bonusX, bonusY, 20.0, 20.0, "imagenes/cerezas.png");

	private RandomGenerator miRandom = new RandomGenerator();



	/**
	 * 
	 * @param ancho: ancho de la bola
	 * @param color: Color de la bola
	 * @param a: clase Arkanoid
	 */
	public Bola(double ancho, Color color, Arkanoid a){
		super(ancho, ancho);
		setFilled(true);
		setFillColor(color);
		setLocation(0, 680);

	}





	//Establece el comportamiento de la bola al encontrarse con otros
	//elemento como paredes, ladrillos, o cursores
	public void chequeaRebote(Arkanoid a){

		mueveBola();
		moverBonus();

		bonusCrecer.efectoBonus(a);
		bonusEncoger.efectoBonus(a);
		bonusBolas.efectoBonus(a);


		rebote(a);
		choqueSuelo(a);

		enviarValoresColision(a);
	}




	//Le da moviemiento a la bola
	private void mueveBola(){
		move(vx,vy);
	}


	//Controla el comportamiento de la bola al cocar con pared y techo
	private void rebote(Arkanoid a){
		//rebota contra la pared

		if ((getX() > a.getWidth()-getWidth()) ||(getX() <= 0)){
			vx = -vx;
		}

		//si toca el techo 
		if (getY() <= 30 ){
			vy = -vy;	
		}
	}



	//Controla lo que ocurre si la bola llega al suelo
	private void choqueSuelo(Arkanoid a){

		if(getY()  >= a.getHeight() - getHeight()){//Si toca suelo

			a.nBolas -= 1;



			if(a.nBolas == 0 ){//Si no quedan bolas

				a.vidas -= 1; 
				a.contadorVidas.setLabel("Vidas:" + a.vidas);//Se refleja en el marcador esa pérdida			

				//Vuelve a colocar la bola en la posicion incial
				setLocation(0, 680);
				vx=1;
				vy=-1;

				//Coloca el cursor con los valores iniciales por si había cambiado de tamaño
				a.cursor.setSize(100, 7);
				a.cursor.setFillColor(Color.cyan);

				a.nBolas =1; //Al poner otra vez la bola pasa a haber una bola

				//Elimina los bonus que caen
				bonusEncoger.setLocation(-20, -20);
				bonusBolas.setLocation(-20, -20);
				bonusCrecer.setLocation(-20, -20);

				if(a.vidas >= 1){//Si pierde el jugador, aparece directamente el game over
					pause(1000);//Da tiempo entre que desaparece la bola y vuelve					
				}

			}else{//Si quedan bolas

				//Saca la bola fuera del mapa y le quita el movimiento
				setLocation(-20,-20);
				vx=0;
				vy=0;

			}
		}
	}




	//Manda diferentes puntos de la bola a chequea colisión
	private void enviarValoresColision(Arkanoid a){

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
	 * Este método establece cómo se comportará la bola en el momento en el que entre en contacto
	 * con los ladrillos o con el cursor.
	 */	
	/**
	 * 
	 * @param posX; Nos da el valor x del punto de la bola que ha chocado con el cursor o el ladrillo
	 * @param posY: Nos da el valor x del punto de la bola que ha chocado con el cursor o el ladrillo
	 * @param arkanoid: Nos envía la clase Arcanoid
	 * @return
	 */
	private boolean chequeaColision(double posX, double posY, Arkanoid arkanoid){

		boolean noHaChocado = true;

		GObject aux = arkanoid.getElementAt(posX,posY);


		if(aux instanceof Cursor){//Si la bola se choca con un cursor

			reboteCursor(arkanoid, aux);
			noHaChocado = false;
		}


		if(aux instanceof LadrilloRojo){//Si la bola se choca con un ladrillo

			noHaChocado = false;

			sorteoBonus(aux, arkanoid);

			reboteLadrillo(arkanoid, aux, posY);




		}

		return noHaChocado;

	}






	//Controla el comprtamiento de la bola al chocar con el cursor
	/**
	 * 
	 * @param arkanoid Envía la clase Arkanoid
	 * @param aux envía el objeto con el que ha chocado la bola
	 */
	private void reboteCursor(Arkanoid arkanoid, GObject aux){
		
		if(getX()+getWidth() <= aux.getX() || getX() >= aux.getX() + aux.getWidth()){//Si choca con el lateral del cursor 
			vx *= -1;
			
		}else if((getX()+getWidth() >= aux.getX() && getX() <= aux.getX() + aux.getWidth())
				&& getY()+ getHeight() <= aux.getY() ){//Si choca con la parte superior cursor
			vy *= -1;


			//En la siguiente serie de condiciones, establecemos cómo rebotará la bola
			//en función de la parte del cursor que golpeé, introduciendo unos parametros
			//entre los que saldrá, de forma aleatoria, la dirección del rebote.
			if(getX()+getWidth() <= aux.getX()+aux.getWidth()/8){//Si choca con el primer octavo del cursor por la izquierda

				vx=miRandom.nextDouble(-1.5,-1);

			}else if(getX()+getWidth() <= aux.getX()+aux.getWidth()/4){//Si choca con el sengundo octavo del cursor por la izquierda

				vx=miRandom.nextDouble(-1.25,-0.75);

			} else if(getX()+getWidth() <= aux.getX()+aux.getWidth()*(3/8)){//Si choca con el tercero octavo del cursor por la izquierda


				vx=miRandom.nextDouble(-1,-0.5);

			}else if(getX()+getWidth() <= aux.getX()+aux.getWidth()/2){//Si choca con el primer cuarto del cursor por la izquierda

				vx=miRandom.nextDouble(-0.75,-0.20);

			}else if(getX()+getWidth() <= aux.getX()+aux.getWidth()*(5/8)){//Si choca con el quinto octavo del cursor por la izquierda

				vx=miRandom.nextDouble(0.20,0.75);

			} else if(getX()+getWidth() <= aux.getX()+aux.getWidth()*(3/4)){//Si choca con el sexto octavo del cursor por la izquierda

				vx=miRandom.nextDouble(0.5,1);

			} else if(getX()+getWidth() <= aux.getX()+aux.getWidth()*(7/8)){//Si choca con el séptimo octavo del cursor por la izquierda

				vx=miRandom.nextDouble(0.75,1.25);

			}else{//Si choca con el último octavo del cursor por la izquierda


				vx=miRandom.nextDouble(1,1.5);
			}
		}

	}






	//Controla el comportamiento de la bola al chocar con el ladrillo y el comportamiento del ladrillo
	/**
	 * 
	 * @param arkanoid envía la clase Arkanoid
	 * @param aux	envía el objeto on el que ha chocado la bola
	 * @param posY envía la posicion de las ie en las que ha chocado la bola.
	 */
	private void reboteLadrillo(Arkanoid arkanoid, GObject aux, double posY){


		if( ((posY > aux.getY()+ aux.getHeight() -2) && (posY < aux.getY() + aux.getHeight() + 1) )  || 
				((posY > aux.getY()-1) && (posY < aux.getY()  + 2) ) ){//Si choca con la parte superior o inferior del ladrillo
			vy *= -1;


		} else {//Si choca con el lateral del ladrillo 
			vx *= -1;

		}

		arkanoid.puntos += 5;//Si rebota con un ladrillo sumamos 5 puntos

		((LadrilloRojo) aux).vidas -= 1;//Se le quita una vida al ladrillo

		if(((LadrilloRojo) aux).vidas == 0 ){

			arkanoid.puntos += 5;//Si rompe un ladrillo sumamos 10 puntos			

			arkanoid.remove(aux);

			arkanoid.nLadrillos -= 1;

		}


		arkanoid.contador.setLabel("Puntuacion: " + arkanoid.puntos);//cambíamos el valor de la etiqueta
	}





	/*
	 * Este método es llamado tras chocar con un ladrillo y de forma
	 * aleatoria decide si aparece un bonus y qué tipo de bonus
	 */
	/**
	 * 
	 * @param aux recibe el las coordenas del ladrillo para que el bonus caíga desde ahí
	 * @param a  recibe la clase Arkanoid
	 */
	public void sorteoBonus(GObject aux, Arkanoid a){

		/*
		 * Sorteamos un numero entre 30 y solo el 3,2 y el 4 tienen bonus
		 */
		int random = miRandom.nextInt(1,30);


		if(random == 3){//Bonus encoger cursor

			//Le damos las cordenadas del ladrillo al bonus
			bonusY = aux.getY();
			bonusX = aux.getX();
			bonusEncoger.setLocation(bonusX, bonusY);

			a.add(bonusEncoger);


		}else if(random == 2){//Bonus agrandar cursor

			//Le damos las cordenadas del ladrillo al bonus
			bonusY = aux.getY();
			bonusX = aux.getX();
			bonusCrecer.setLocation(bonusX, bonusY);

			a.add(bonusCrecer);


		}else if(random ==4){//Bonus aparecen dos bolas más.

			//Le damos las cordenadas del ladrillo al bonus
			bonusY = aux.getY();
			bonusX = aux.getX();
			bonusBolas.setLocation(bonusX, bonusY);

			a.add(bonusBolas);
		}
	}






	//Hace que el bonus caíga
	private void  moverBonus(){
		bonusCrecer.move(0.0,0.65);
		bonusEncoger.move(0.0,0.65);
		bonusBolas.move(0.0,0.65);
	}

}




















