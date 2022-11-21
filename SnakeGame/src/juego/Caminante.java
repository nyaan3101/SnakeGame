package juego;

import java.util.logging.Level;
import java.util.logging.Logger;
//Esta clase es para hacer que la sepiente se mantenga en movimiento a la velocidad que queremos que vaya
public class Caminante implements Runnable {
//Implemento de sus metodos abstractos
    PanelSnake panel;
    boolean estado= true;
    public Caminante(PanelSnake panel){
        this.panel=panel;
    }
    @Override
    public void run() {
        while(estado){
            panel.avanzar();
            panel.repaint();//Mientras esta avanzando la serpiente va pintado el fondo para que se vualize 
            try {
                Thread.sleep(100); //Aqui se puede cambiar la velocidad de la serpiente
            } catch (InterruptedException ex) {
                Logger.getLogger(Caminante.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    //funcion de parar de avanzar
    public void para(){
        this.estado=false;
    }
}
