/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mt;

import java.io.Serializable;
import java.util.ArrayList;
/**
 *
 * @author andre
 */
class Grafo implements Serializable {

    private Nodo estadoActual;
    private Nodo eFinal;
    private ArrayList<Nodo> nodos;
    
    Grafo(){
        nodos = new ArrayList<>();
    }
    
    public boolean init(Nodo estado,Nodo eFinal){
        if (estado != null){
            estadoActual = estado;
            this.eFinal = eFinal;
            return true;
        }
        return false;
    }

    public ArrayList<Nodo> getNodos() {
        return nodos;
    }

    public void setEstadoActual(Nodo estadoActual) {
        this.estadoActual = estadoActual;
    }

    public void setNodos(ArrayList<Nodo> nodos) {
        this.nodos = nodos;
    }

    public Nodo getEstadoActual() {
        return estadoActual;
    }
    
    public void addNode(Nodo node){
        nodos.add(node);
    }

    public String[] lectura(String leido) {
        String resultado[] = new String[2];
        String clave ="";
        System.out.println(" leyendo awantaaaaaaaaaaaaa ->" + leido);
        System.out.println("estado:"+estadoActual.getEtiqueta());
        for (String accion : estadoActual.getConjuntoAcciones()) {
            clave = accion;
            String[] split = clave.split(",");
            
            System.out.println("caracter:"+leido+":"+split[0]);
            if (split[0].compareTo(leido) == 0) {
                resultado[0] = split[1];
                resultado[1] = split[2];
                System.out.println(clave + " clave");
                System.out.println(estadoActual.getEtiqueta() + " estado actual antes");
                estadoActual = estadoActual.getTransicion(clave);
                System.out.println(estadoActual.getEtiqueta() + " estado actual luego");
            }
        }
        System.out.println("r "+resultado[0]+": r"+resultado[1]);
        return resultado;
    }

    public Nodo geteFinal() {
        return eFinal;
    }
    
    public void verNodos(){
        for (Nodo nodo : nodos) {
            System.out.print(nodo.getEtiqueta()+",");
        }
        System.out.println("");
    }

}

