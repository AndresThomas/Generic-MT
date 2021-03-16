/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.control.Alert;
import javax.swing.JOptionPane;

/**
 *
 * @author andre
 */
public class Maquina implements Serializable {

    private String[] cinta;
    private String resultado;
    private Grafo grafo;
    private ArrayList<String> sigma;
    private boolean flag;

    Maquina() {
        grafo = new Grafo();
        sigma = new ArrayList<>();
        flag = false;
        System.out.println("Maquina creada");
    }

    public void setCinta(String text2) {
        String text = text2 + "B";
        cinta = new String[text.length()];
        for (int i = 0; i < text.length(); i++) {
            cinta[i] = text.charAt(i) + "";
        }
    }

    public boolean validator() {
        boolean status = false;
        if (!sigma.isEmpty() && !grafo.getNodos().isEmpty()) {
            status = true;
        }
        return status;
    }

    public String[] getCinta() {
        return cinta;
    }

    public Grafo getGrafo() {
        return grafo;
    }

    public ArrayList<String> getSigma() {
        return sigma;
    }

    public boolean isFlag() {
        return flag;
    }

    public void addletter(String letter) {
        sigma.add(letter);
    }

    public void proceso() {
        /*
        leer la cinta caracter a caracter
        segun cinta[cabezal] verificar si el caracter leido es
        0 o 1 y mover el cabezal segun lo indicado
         */
        int cabezal = 0;
        String resultado[] = new String[2];
        for (int iterator = 0; cabezal < cinta.length; iterator++) {
            //x++;
            if (sigma.contains(cinta[cabezal])) {
                resultado = grafo.lectura(cinta[cabezal]); // se lee el espacio de la cinta y se retorna el valor a escribir
                cinta[cabezal] = resultado[0];
                //System.out.println(resultado[0]+ " <- resultado[0]");
                //System.out.println(resultado[1]+ " <- resultado[1]");
                try {
                    if (resultado[1].compareTo("I") == 0) {
                        System.out.println("mover a la izquierda");
                        cabezal--;
                    } else {
                        cabezal++;
                        System.out.println("mover derecha");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Alert message = new Alert(Alert.AlertType.INFORMATION);
                    message.setHeaderText("Error: "+ e);
                    message.setContentText("Ha ocurrido un error al leer"+ cinta[cabezal]);
                    message.setTitle("Resultado");
                    message.showAndWait();
                    cabezal = cinta.length;
                    iterator = 0;
                    flag = true;
                }
            } else {
                cabezal = cinta.length;
                JOptionPane.showMessageDialog(null, "Error en la entrada, la maquina se ha detenido");
                break;
            }
            if (grafo.getEstadoActual().getEtiqueta().compareTo("q7") == 0) {
                cabezal = cinta.length;
            }

            /*if (x == 15) {
                cabezal = cinta.length;
            }*/
        }

    }

    public String imprimirCinta() {
        String word = "B";
        if (!flag) {
            word = "";
            for (int i = 0; i < cinta.length; i++) {
                word += cinta[i];
            }
        }
        flag = false;
        return word;
    }

    public String getResultado() {
        resultado = "Cadena no valida";
        ArrayList<String> cinta2 = new ArrayList<>();
        for (int i = 0; i < cinta.length; i++) {
            cinta2.add(cinta[i]);
        }
        System.out.println(grafo.getEstadoActual().getEtiqueta()+":"+grafo.geteFinal().getEtiqueta()+"********************");
        if (grafo.getEstadoActual().getEtiqueta().compareTo(grafo.geteFinal().getEtiqueta()) == 0) {
            resultado = "Cadena valida";
        }
        return resultado;
    }

}
