package juego;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelSnake extends JPanel {

    Color colorsnake = Color.PINK;
    Color colorcomida = Color.WHITE;
    int tamax, tam, cant;
    List<int[]> snake = new ArrayList<>();
    int[] comida = new int[2];
    String direccion = "de";
    String direccionproxima = "de";
    Thread hilo;
    Caminante camino;

    //constructores
    public PanelSnake(int tamax, int cant) {
        this.tamax = tamax;
        this.cant = cant;
        this.tam = tamax / cant;

        int[] a = {cant / 2 - 1, cant / 2 - 1};
        int[] b = {cant / 2, cant / 2 - 1};
        snake.add(a);
        snake.add(b);
        generarcomida();

        camino = new Caminante(this);
        hilo = new Thread(camino);
        hilo.start();
    }

    //color de la serpiente
    @Override
    public void paint(Graphics pintor) {
        super.paint(pintor);
        pintor.setColor(colorsnake);

        for (int[] par : snake) {
            pintor.fillRect(par[0] * tam, par[1] * tam, tam - 1, tam - 1);
        }
        //color de la comida
        pintor.setColor(colorcomida);
        pintor.fillRect(comida[0] * tam, comida[1] * tam, tam - 1, tam - 1);
    }

    //Funcion avance con los 4 casos posibles
    public void avanzar() {
        igualdir();
        int[] ultimo = snake.get(snake.size() - 1);
        int agregarEnX = 0;
        int agregarEnY = 0;
        switch (direccion) {
            case "de":
                agregarEnX = 1;
                break;
            case "iz":
                agregarEnX = -1;
                break;
            case "ar":
                agregarEnY = 1;
                break;
            case "ab":
                agregarEnY = -1;
                break;
        }
        //Cuando avances al ultimo tramo regresa al inicio
        int[] nuevo = {Math.floorMod(ultimo[0] + agregarEnX, cant),
            Math.floorMod(ultimo[1] + agregarEnY, cant)};

        //Avanzar y comer la comida casos posibles:
        boolean existe = false;
        for (int i = 0; i < snake.size(); i++) {
            if (nuevo[0] == snake.get(i)[0] && nuevo[1] == snake.get(i)[1]) {
                existe = true;
                break;
            }
        }
        if (existe) {
            JOptionPane.showMessageDialog(this, "Perdiste");
        } else {
            if (nuevo[0] == comida[0] && nuevo[1] == comida[1]) {
                snake.add(nuevo);
                //Luego que comio se genera otra comida
                generarcomida();
            } else {
                snake.add(nuevo);
                snake.remove(0);
            }
        }
    }

    //Funcion para general la comida
    public void generarcomida() {
        boolean existe = false;
        //coornedas aleatorias que no pertenescan al cuerpo de la serpiente
        int a = (int) (Math.random() * cant);
        int b = (int) (Math.random() * cant);
        for (int[] par : snake) {
            if (par[0] == a && par[1] == b) {
                existe = true;
                generarcomida();
                break;
            }
        }
        if (!existe) {
            this.comida[0] = a;
            this.comida[1] = b;
        }
    }

    //funcion que permita cambiar de direccion a la serpiente
    public void cambiodireccion(String dir) {
        if ((this.direccion.equals("de") || this.direccion.equals("iz")) && (dir.equals("ar") || dir.equals("ab"))) {
            this.direccionproxima = dir;
        }
        if ((this.direccion.equals("ar") || this.direccion.equals("ab")) && (dir.equals("iz") || dir.equals("de"))) {
            this.direccionproxima = dir;
        }
    }

    //Igual direcciones
    public void igualdir() {
        this.direccion = this.direccionproxima;
    }
}
