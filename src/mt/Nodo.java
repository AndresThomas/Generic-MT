/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author andre
 */
public class Nodo implements Serializable{

    private String etiqueta;
    private ArrayList<String> conjuntoAcciones;  
    private HashMap<String, Nodo> transicion;

    public Nodo(String etiqueta) {
        this.etiqueta = etiqueta;
        this.conjuntoAcciones = new ArrayList<>();
        this.transicion = new HashMap<>();
    }

    public String getEtiqueta() {
        return this.etiqueta;
    }
    
    public HashMap<String,Nodo> table(){
        return this.transicion;
    }
    
    public void addAccion(String accion){
        this.conjuntoAcciones.add(accion);
    }

    public void setEtiqueta(String etiqueta) {
        this.etiqueta = etiqueta;
    }

    public ArrayList<String> getConjuntoAcciones() {
        return conjuntoAcciones;
    }

    public void setConjuntoAcciones(ArrayList<String> conjuntoAcciones) {
        this.conjuntoAcciones = conjuntoAcciones;
    }

    @Override
    public String toString() {
        return etiqueta;
    }
    
    public String especificaciones(){
        return "Nodo{" + "etiqueta=" + etiqueta + ", conjuntoAcciones=" + conjuntoAcciones + ", transicion=" + transicion + '}';
    }
    
    

    public Nodo getTransicion(String key) {
        return this.transicion.get(key);
    }

    public void setTransicion(String accion, Nodo destino) {
        this.transicion.put(accion, destino);
        System.out.println("el nodo" + this.etiqueta+" ha sido unido a "+destino.getEtiqueta()+" con accion "+accion);
    }

    
}
