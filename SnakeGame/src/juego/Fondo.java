package juego;

import com.sun.prism.paint.Paint;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;
//Importamos la clase Jpanel para crear una clase hija de FONDO 
public class Fondo extends JPanel{
    Color colofondo=Color.LIGHT_GRAY;
    int tamax,tam,cant;
    
    //constructor
    public Fondo(int tamax,int cant){
        this.tamax=tamax;
        this.cant=cant;
        this.tam=tamax/cant;
    }
    //Esta funcion va a ir cambiando el color del fondo
    @Override
    public void paint(Graphics pintor){
        super.paint(pintor);
        pintor.setColor(colofondo);
        for (int i = 0; i < cant; i++) {
            for (int j = 0; j < cant; j++) {
                pintor.fillRect(i*tam,j*tam,tam-1,tam-1);
            }           
        }
    }
}
