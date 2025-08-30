/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.fiuaemex;

import java.util.ArrayList;

/**
 *
 * @author eric
 */
public class Logic {
    //metodo que determina si dos conjuntos son disyuntos osea (que no comparten elementos)

    public boolean sonDisyuntos(ArrayList<Integer> conjuntoA, ArrayList<Integer> conjuntoB) {
        // Búsqueda secuencial para verificar disyuntiva
        for (int i = 0; i < conjuntoA.size(); i++) {
            int elementoA = conjuntoA.get(i); //tomamos un elemento del conjunto A 

            //para cada elemento de A, buscamos en TODOS los elementos de B(búsqueda secuencial)
            for (int j = 0; j < conjuntoB.size(); j++) {
                // comparamos si un elemento de A esxiste en B
                if (conjuntoB.get(j) == elementoA) {
                    return false; // Encontró un elemento común y nos dirta que no son disyuntos
                }
            }
        }
        //si llega hasta aqui nos quiere decir que ningun elemento de A está en B 
        return true; //no encontró elementos comunes: por lo tanto sí es disyuntos
    }
}
