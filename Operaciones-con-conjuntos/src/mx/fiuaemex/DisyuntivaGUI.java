/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mx.fiuaemex;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author eric
 */
public class DisyuntivaGUI {
    //estas dos listas  almacenan los elementos ingresados

    private ArrayList<Integer> conjuntoA; //primera lista = conjunto A
    private ArrayList<Integer> conjuntoB; //segunda lista = conjuntos B
    private final Logic logic; // para hacer los calculos y el final para que no se pueda cambiar

    //constuctor
    //que va a iniciar todos los componentes necesarios 
    public DisyuntivaGUI() {

        logic = new Logic(); //instancia la logica de calculo 

        //preparamos la lista para que se ingresen 
        conjuntoA = new ArrayList<>();
        conjuntoB = new ArrayList<>();

        //llamamos al metodo que creara la interfaz osea todo lo que va en la ventana
        crearVentana();
    }

    //método que crea la ventana con titulo tamaño para que se pueda interatuar 
    private void crearVentana() {
        //crea la ventana principal
        JFrame frame = new JFrame("Verificación de Conjuntos Disyuntos");
        frame.setSize(500, 400); //tamaño 500 * 400 pixeles 
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(null);

        // Instrucciones para que el usuario ingrese los elementos como se le piden
        JLabel instrucciones = new JLabel("Ingrese números enteros separados por comas (ej: 1,2,3,4)");
        instrucciones.setBounds(20, 10, 450, 20);
        frame.add(instrucciones);

        //le indica que es el conjunto A poara que ingrese los datos 
        JLabel labelA = new JLabel("Conjunto A:");
        labelA.setBounds(20, 40, 200, 20);
        frame.add(labelA);

        JTextField fieldA = new JTextField();
        fieldA.setBounds(20, 60, 400, 25);
        frame.add(fieldA);

        //le indica que es el conjunto B para que ingrese los datos 
        JLabel labelB = new JLabel("Conjunto B:");
        labelB.setBounds(20, 100, 200, 20);
        frame.add(labelB);

        JTextField fieldB = new JTextField();
        fieldB.setBounds(20, 120, 400, 25);
        frame.add(fieldB);

        //Boton para que ingrese los datos a la lista
        JButton btnIngresar = new JButton("Ingresar Conjuntos");
        btnIngresar.setBounds(20, 160, 180, 30);
        frame.add(btnIngresar);

        //boton para que verifique la disyuntiva entre los conjuntos
        JButton btnVerificar = new JButton("Verificar Disyuntiva");
        btnVerificar.setBounds(220, 160, 180, 30);
        frame.add(btnVerificar);

        //aqui se le mostrarán los resultados al usuario
        JTextArea resultadoArea = new JTextArea();
        resultadoArea.setBounds(20, 200, 440, 140);
        //que solo se muestre y no se modifique
        resultadoArea.setEditable(false);
        resultadoArea.setLineWrap(true);
        resultadoArea.setWrapStyleWord(true);

        //agrega el área de resultados a la ventana
        frame.add(resultadoArea);

        //este código hace que cuando el usuario presione el botón "Ingresar Conjuntos":
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            //toma el texto que escribio el usuario y lo convierte en numeros
            public void actionPerformed(ActionEvent e) {
                conjuntoA = obtenerConjunto(fieldA.getText());
                conjuntoB = obtenerConjunto(fieldB.getText());

                //muestra que se capturo correctamente   
                resultadoArea.setText("Conjuntos ingresados correctamente:\n"
                        + "Conjunto A: " + conjuntoA + "\n"
                        + "Conjunto B: " + conjuntoB);
            }
        });

        //este hace que cuando el usuario presione el boton de "Verificar Disyuntiva":
        btnVerificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //revisa que el usuario haya ingresado datos
                if (conjuntoA.isEmpty() || conjuntoB.isEmpty()) {
                    //sino es asi me aparecera unerror
                    resultadoArea.setText("error: primero debe ingresar ambos conjuntos");
                    return;
                }

                //preparamos lo resultados
                String resultado = "--- RESULTADOS ---\n\n";
                resultado += "Conjunto A: " + conjuntoA + "\n";
                resultado += "Conjunto B: " + conjuntoB + "\n\n";

                // logic.sonDisyuntos() es el método que hace todo el análisis 
                //y que está en la clase Logic
                boolean sonDisyuntos = logic.sonDisyuntos(conjuntoA, conjuntoB);

                //aqui solo preparamos el titulo de la respuesta
                resultado += "¿Los conjuntos son disyuntos?\n";
                //si son disyntivos 
                if (sonDisyuntos) {
                    resultado += "SÍ son disyuntos\n"; //imprimirá que son disyuntos 
                    resultado += "No comparten ningun elemento en comun"; //y nos dirá porque son disyuntos
                } else {
                    //sino
                    resultado += "NO son disyuntos\n"; //dirá que no son disyuntos

                    //para encontrar elementos en comun
                    //creamos una nueva lista para guardar los que se repiten
                    ArrayList<Integer> comunes = new ArrayList<>();

                    //aqui recorremos todos los elementos del conjuntoio A 
                    for (int i = 0; i < conjuntoA.size(); i++) {
                        //entonces aqui tomamos un numro del conjunto A
                        int elemento = conjuntoA.get(i);

                        //con busqueda secuencial buscamos este numero en el conjunto B 
                        for (int j = 0; j < conjuntoB.size(); j++) {
                            if (conjuntoB.get(j) == elemento) {
                                //encontramos la coincidencia  
                                comunes.add(elemento); //y guardamos el numero repetido 
                                break; //entonces ya dejamos de buscar este numero
                            }
                        }
                    }
                    //le mostramos al usuario que numeros son los que hacen que no sean disyuntos por que comparten 
                    resultado += "Elementos en común: " + comunes;
                }

                resultadoArea.setText(resultado); //le mostramos el resultado final
            }
        });

        // CENTRAR LA VENTANA
        frame.setLocationRelativeTo(null);

        frame.setVisible(true);
    }

    //este es un metodo para que conviert lo que se esta ingresando
    private ArrayList<Integer> obtenerConjunto(String texto) {
        //creamos una nueva lista
        ArrayList<Integer> conjunto = new ArrayList<>();
        //si el usuario no escribió nada, devolvemos lista vacía
        if (texto.trim().isEmpty()) {
            return conjunto;
        }

        //dividimos el texto usando las comas como separadores
        // por ejemplo "1,2,3" se convierte en "1", "2", "3" 
        String[] partes = texto.split(",");
        //despoues ya se recorre cada parte del texto
        for (String parte : partes) {
            try {
                //y quitamos los espacios en blanco que estan a un lado del numero
                String numStr = parte.trim();
                //se convierte de texto a numero
                if (!numStr.isEmpty()) {
                    conjunto.add(Integer.parseInt(numStr));
                }
            } //es para manejar errores por ejemplo si ingresaron abc
            //nos evita de que se caiga el programa
            catch (NumberFormatException ex) {

            }
        }
        //devolvemos la lista con numeros validos 
        return conjunto;

    }
}
