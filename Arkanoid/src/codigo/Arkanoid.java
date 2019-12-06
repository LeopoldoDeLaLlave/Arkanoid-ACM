/*
 * Autores:Sofía Rico y Javier de la Llave
 * 
 * Clase principal del Arkanoid
 */
package codigo;


import java.awt.Color;
import java.awt.event.MouseEvent;

import acm.graphics.GImage;
import acm.graphics.GLabel;
import acm.graphics.GOval;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;




public class Arkanoid extends GraphicsProgram{


	Bola bola1 = new Bola(10, Color.yellow, this);
	Bola bola2 = new Bola(10, Color.yellow, this);
	Bola bola3 = new Bola(10, Color.yellow, this);

	Cursor cursor = new Cursor(700, 100, 7, Color.cyan);

	int nLadrillos;//Va a contar cuantos ladrillos hay en cada momento en el mundo

	int mundo = 1;//Nos indica en qué mundo estamos. Empieza por el mundo 1

	int nBolas = 1; //Nos indica cuántas bolas hay en el mundo.

	int vidas;//Vidas que tiene el jugador, al llegar a 0 acaba el juego

	int puntos; //Puntos del jugador

	boolean ganado = false; //Nos indica si se ha pasado el juego


	GImage fondo;

	Contador contador = new Contador(540, 20); //Etiqueta con los puntos del jugador
	ContadorVidas contadorVidas=new ContadorVidas(30,20); //Etiqueta con las vidas del jugador

	static int ANCHO_LADRILLO = 35;
	static int ALTO_LADRILLO = 35;



	public void init(){

		this.setSize(700, 820);
		fondo = new GImage("imagenes/negro.jpg");
		fondo.setSize(700,820);
		add(fondo);
		addMouseListeners();

	}



	public void run(){

		while(!ganado){//El juego se ejecuta hasta que se gana

			crearMundo1();

			partida();

			if(mundo == 1 && vidas != 0){//Si se ha pasado el mundo 1 iniciamos el mundo2
				crearMundo2();
			}

			partida();
			fondoFinal();
		}
	}




	//Mientras esté dentro de la pantlla, el cursor sigue al ratón.
	public void mouseMoved(MouseEvent evento){

		if(evento.getX() + cursor.getWidth() <= this.getWidth()){
			cursor.setLocation(evento.getX(), cursor.getY());
		}
	}




	//Creamos el primer mundo.
	private void crearMundo1(){

		waitForClick();//Hay que pulsar para que aparezca el primer mundo

		//Ponemos el fondo
		fondo = new GImage("imagenes/fondoPac22.jpg");
		fondo.setSize(650,810);

		puntos = 0;
		vidas = 3;

		add(fondo);

		iniciarMundo();

		int numLadrillos = 14;
		int desplazamientoInicial = (getWidth() - numLadrillos * ANCHO_LADRILLO)/2;

		//Creamos el bloque de ladrillos superior, el cual tendrá fantasmas rojos con 3 vidas
		for (int i = 0; i < 3; i++) { 
			for (int j = 0; j < numLadrillos; j++) {

				int posX = desplazamientoInicial + ANCHO_LADRILLO*j;
				int posY = ALTO_LADRILLO*i + ALTO_LADRILLO + 70;

				LadrilloRojo miLadrillo = new LadrilloRojo(
						posX,
						posY,
						ANCHO_LADRILLO,
						ALTO_LADRILLO,
						"imagenes/rojo.png");

				add(miLadrillo);
				miLadrillo.vidas = 3;

				nLadrillos++;
			}
		}


		//Creamos el bloque de ladrillos central, el cual tendrá fantasmas verdes con 2 vidas
		for (int n = 0; n < 3; n++) {
			for (int j = 0; j < numLadrillos; j++) {

				int posX = desplazamientoInicial + ANCHO_LADRILLO*j;
				int posY = ALTO_LADRILLO*n + ALTO_LADRILLO + 200;

				LadrilloRojo miLadrillo = new LadrilloRojo(
						posX,
						posY,
						ANCHO_LADRILLO,
						ALTO_LADRILLO,
						"imagenes/verde.png");

				add(miLadrillo);
				miLadrillo.vidas = 2;

				nLadrillos++;
			}
		}


		//Creamos el bloque de ladrillos inferior, el cual tendrá fantasmas azules con 1 vida
		for (int m = 0; m < 3; m++) {
			for (int j = 0; j < numLadrillos; j++) {

				int posX = desplazamientoInicial + ANCHO_LADRILLO*j;
				int posY = ALTO_LADRILLO*m + ALTO_LADRILLO +350;

				LadrilloRojo miLadrillo = new LadrilloRojo(
						posX,
						posY,
						ANCHO_LADRILLO,
						ALTO_LADRILLO,
						"imagenes/azul3.png");

				add(miLadrillo);
				miLadrillo.vidas = 1;

				nLadrillos++;
			}
		}

	}


	//Realizamos la distribución de ladrillos del mundo 2
	private void crearMundo2(){

		removeAll();//Eliminamos el mundo anteriror
		mundo = 2;//Indicamos que es el segundo mundo

		//Ponemos el fondo
		fondo = new GImage("imagenes/fondopac22.jpg");
		fondo.setSize(700,820);
		add(fondo);

		//Colocamos la bola en su posición inicial
		bola1.setLocation(0, 680);
		bola1.vx =1;
		bola1.vy =-1;		

		vidas++; //Regalamos una vida por haber pasado el mundo



		iniciarMundo();

		int numLadrillos = 12;
		int nColumnas = 12;
		int desplazamientoInicial = (getWidth() - numLadrillos * ANCHO_LADRILLO)/2;

		for (int i = 0; i < nColumnas; i++) {


			for (int j = 0; j < numLadrillos; j++) {

				if(i==0||i==nColumnas-1|| j == 0 || j == numLadrillos-1){//Hacemos el cuadro externo
					int posX = desplazamientoInicial + ANCHO_LADRILLO*j;
					int posY = ALTO_LADRILLO*i + ALTO_LADRILLO + 90;

					LadrilloRojo miLadrillo = new LadrilloRojo(
							posX,
							posY,
							ANCHO_LADRILLO,
							ALTO_LADRILLO,
							"imagenes/azul3.png");

					add(miLadrillo);
					miLadrillo.vidas = 1;

					nLadrillos++;
				}else if((i==2 && j !=1 && j != numLadrillos-2)||(i==nColumnas-3 && j !=1 && j != numLadrillos-2)
						|| (j == 2 && i !=1 && i != nColumnas-2) || (j == numLadrillos-3 && i !=1 && i != nColumnas-2)){//Hacemos el segundo cuadro desde fuera
					int posX = desplazamientoInicial + ANCHO_LADRILLO*j;
					int posY = ALTO_LADRILLO*i + ALTO_LADRILLO + 90;

					LadrilloRojo miLadrillo = new LadrilloRojo(
							posX,
							posY,
							ANCHO_LADRILLO,
							ALTO_LADRILLO,
							"imagenes/verde.png");

					add(miLadrillo);
					miLadrillo.vidas = 2;

					nLadrillos++;
				} else if((i==4 &&  j!=3 && j != numLadrillos-4 && j !=1 && j != numLadrillos-2)||(i==nColumnas-5 && j !=3 && j != numLadrillos-4 && j !=1 && j != numLadrillos-2)
						|| (j == 4 && i !=3 && i != nColumnas-4 && i !=1 && i != nColumnas-2) || (j == numLadrillos-5 && i !=3 && i != nColumnas-4 && i !=1 && i != nColumnas-2)){//Hacemos el tercer cuadro desde fuera
					int posX = desplazamientoInicial + ANCHO_LADRILLO*j;
					int posY = ALTO_LADRILLO*i + ALTO_LADRILLO + 90;

					LadrilloRojo miLadrillo = new LadrilloRojo(
							posX,
							posY,
							ANCHO_LADRILLO,
							ALTO_LADRILLO,
							"imagenes/rojo.png");

					add(miLadrillo);
					miLadrillo.vidas = 3;

					nLadrillos++;
				} 
			}
		}
	}



	//Este metodo hace que la bola vaya más rápido según avanza la partida.
	private void velocidad(){

		if(mundo == 1){

			if(nLadrillos < 12){
				pause(2);
			}else if(nLadrillos>= 12 && nLadrillos < 30){
				pause(2.5);
			}else if(nLadrillos>= 30 && nLadrillos < 55){
				pause(3);
			}else if(nLadrillos>= 55 && nLadrillos < 80){
				pause(3.5);
			}else if(nLadrillos>= 80 && nLadrillos < 100){
				pause(4);
			}else{
				pause(5);
			}
		}else{
			if(nLadrillos < 12){
				pause(1.5);
			}else if(nLadrillos>= 12 && nLadrillos < 20){
				pause(2);
			}else if(nLadrillos>= 20 && nLadrillos < 30){
				pause(2.5);
			}else if(nLadrillos>= 30 && nLadrillos < 40){
				pause(3);
			}else if(nLadrillos>= 40 && nLadrillos < 50){
				pause(3.5);
			}else{
				pause(4);
			}
		}
	}


	//Coloca el marcador, el contador, la bola y el cursor
	private void iniciarMundo(){



		nBolas = 1;//Se empieza con una bola

		//Colocamos un margen donde poner los marcadores
		GRect margen =  new GRect(700, 30);
		margen.setFilled(true);
		margen.setFillColor(Color.darkGray);
		add(margen);


		//Coloca el contador de puntos
		add(contador);
		contador.setLabel("Puntuacion: " + puntos);

		//Coloca el contador de vidas
		add(contadorVidas);
		contadorVidas.setLabel("Vidas:"+vidas);

		//Coloca el cursor con los valores iniciales por si había cambiado de tamaño
		cursor.setSize(100, 7);
		cursor.setFillColor(Color.cyan);
		add(cursor);

		add(bola1);
		add(bola2);
		add(bola3);

		//ponemos visible y con movimiento solo la bola 1.
		bola2.setLocation(-20,-20);
		bola3.setLocation(-20,-20);
		bola1.setLocation(0, 680);

		bola2.vx = 0;
		bola2.vy = 0;

		bola3.vx = 0;
		bola3.vy = 0;

		bola1.vx = 1;
		bola1.vy = -1;
	}



	//El juego mientras esté en un mundo
	private void partida(){

		if(vidas >=1){//Para que , si se queda sin vidas, aparezaca el game over directamente
			waitForClick();
		}
		while(vidas > 0 && nLadrillos > 0){//Mientras queden vidas y ladrillos en el mundo 

			if(nBolas>0 && nLadrillos >0){//Mientras no se caíga la última bola y haya ladrillos

				velocidad();

				bola1.chequeaRebote(this);
				bola2.chequeaRebote(this);
				bola3.chequeaRebote(this);					
			}

		}

	}



	//Pone la foto una vez que termina la partida
	private void fondoFinal(){

		removeAll();//Borramos todo para poner el fondo correspondiente.

		if(vidas==0){//Si ha perdido

			fondo = new GImage("imagenes/go3.jpg");
			fondo.setSize(700,820);
			add(fondo);
			pause(5000);
			nLadrillos = 0;
			removeAll();// borra todo
			init();

		}else{//Si ha ganado

			ganado = true;//Así sale del bucle y se termina el juego
			fondo = new GImage("imagenes/youwin.jpg");
			fondo.setSize(700,820);
			add(fondo);

			GLabel puntosFin = new GLabel("Puntos: " + puntos);
			add(puntosFin, 238, 720);
			puntosFin.setFont("Times New Roman-40");
			puntosFin.setColor(Color.pink);


		}
	}

}
















