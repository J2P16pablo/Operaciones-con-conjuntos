package mvc_produto_simetrico;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class vista_producto_simetrico {
    private Scanner scanner = new Scanner(System.in);

  public Set<Integer> pedirConjunto(String nombreConjunto) {
        Set<Integer> conjunto = new HashSet<>();
        System.out.println("Introduzca los elementos del " + nombreConjunto + " separados por espacios:");

  String linea = scanner.nextLine();
        String[] elementos = linea.trim().split("\\s+");

  for (String elemento : elementos) {
            try {
                conjunto.add(Integer.parseInt(elemento));
            } catch (NumberFormatException e) {
                System.out.println("Elemento invalido ignorado: " + elemento);
            }
        }

  return conjunto;
    }

  public void mostrarConjuntos(Set<Integer> conjuntoA, Set<Integer> conjuntoB) {
        System.out.println("\n--- Conjuntos Ingresados ---");
        System.out.println("Conjunto A: " + conjuntoA);
        System.out.println("Conjunto B: " + conjuntoB);
    }

  public void mostrarResultado(Set<Integer> resultado, String operacion) {
        System.out.println("\nResultado de " + operacion + ": \n" + resultado);
    }

  public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}
