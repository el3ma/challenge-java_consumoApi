import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner teclado = new Scanner(System.in);
        ConsultaAPI consulta = new ConsultaAPI();

        System.out.println("******************************************");
        System.out.println("Bienvenido al Conversor de Monedas!");
        System.out.println("******************************************");
        System.out.println("\nPor favor, elige una opción de conversión:");
        System.out.println("1. USD a ARS");
        System.out.println("2. ARS a USD");
        System.out.println("3. USD a BRL");
        System.out.println("4. BRL a USD");
        System.out.println("5. USD a COP");
        System.out.println("6. COP a USD");
        System.out.println("7. Salir"); // Opción para salir
        System.out.println("******************************************");


        int opcionSeleccionada = 0;
        String monedaOrigen = "";
        String monedaDestino = "";
        double montoIngresado = 0;

        // Bucle para permitir múltiples conversiones hasta que el usuario elija salir
        while (opcionSeleccionada != 7) {
            try {
                System.out.print("Introduce tu opción: ");
                opcionSeleccionada = teclado.nextInt();

                if (opcionSeleccionada == 7) {
                    System.out.println("Saliendo del conversor. ¡Hasta luego!");
                    break; // Salir del bucle si la opción es 7
                }

                // Validar que la opción esté dentro del rango esperado (1-6)
                if (opcionSeleccionada < 1 || opcionSeleccionada > 6) {
                    System.out.println("Opción no válida. Por favor, elige una opción entre 1 y 6.");
                    continue; // Volver al inicio del bucle para mostrar el menú de nuevo
                }

                // --- Usar switch para mapear la opción a monedas ---
                switch (opcionSeleccionada) {
                    case 1:
                        monedaOrigen = "USD";
                        monedaDestino = "ARS";
                        break;
                    case 2:
                        monedaOrigen = "ARS";
                        monedaDestino = "USD";
                        break;
                    case 3:
                        monedaOrigen = "USD";
                        monedaDestino = "BRL";
                        break;
                    case 4:
                        monedaOrigen = "BRL";
                        monedaDestino = "USD";
                        break;
                    case 5:
                        monedaOrigen = "USD";
                        monedaDestino = "COP";
                        break;
                    case 6:
                        monedaOrigen = "COP";
                        monedaDestino = "USD";
                        break;
                }

                System.out.print("Introduce el monto a convertir: ");
                montoIngresado = teclado.nextDouble(); // Leer el monto

                // --- Llamar a la API con las monedas y el monto ---

                ObjetosConversion resultado = consulta.consultarMonto(monedaOrigen, monedaDestino, montoIngresado);

                // --- Mostrar el resultado ---
                if (resultado != null && "success".equals(resultado.result())) { // Verificar si la llamada fue exitosa
                    System.out.println("\n--- Resultado de la Conversión ---");
                    System.out.println("El valor " + montoIngresado + " [" + resultado.base_code() + "] " +
                            "corresponde al valor final de: " + resultado.conversion_result() + " [" + resultado.target_code() + "]");
                    System.out.println("---------------------------------");
                } else {
                    System.out.println("Error al obtener el resultado de la conversión.");

                }


            } catch (InputMismatchException e) {
                // Manejar error si el usuario no introduce un número
                System.out.println("Entrada no válida. Por favor, introduce un número.");
                teclado.next(); // Limpiar el buffer del scanner para evitar un bucle infinito
            } catch (IOException e) {
                System.out.println("Error de I/O al conectar con la API: " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("La conexión con la API fue interrumpida: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ocurrió un error inesperado: " + e.getMessage());
                e.printStackTrace(); // Imprimir la pila de errores para depuración
            }
            System.out.println("\n******************************************");
            System.out.println("Elige otra opción o 7 para salir:");
            System.out.println("1. USD a ARS | 2. ARS a USD | 3. USD a BRL | 4. BRL a USD | 5. USD a COP | 6. COP a USD | 7. Salir");
            System.out.println("******************************************");
        }

        teclado.close();
    }
}

