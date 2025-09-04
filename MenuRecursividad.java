/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package menurecursividad;
import java.util.*; // Importa utilidades como Scanner, List y Arrays
/**
 *
 * @author Arturo González
 */
public class MenuRecursividad { // Clase principal que contiene el menu

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) { // Punto de entrada del programa
        // TODO code application logic here
        Scanner sc = new Scanner(System.in); // Lector de entradas de consola
        while (true){ // Bucle para mostrar el menu hasta que el usuario salga
            System.out.println("\n===== Menu: Recursividad y Backtracking =====");// Titulo del menu
            System.out.println("1) Fibonacci recursivo"); // Opcion 1
            System.out.println("2) Suma de subconjuntos (Subset Sum)"); // Opcion 2
            System.out.println("3) Resolver Sudoku (backtracking)"); // Opcion 3
            System.out.println("4) Salir"); // Opcion salir
            System.out.println("Elige una opcion: "); // Solicita opcion al usuario
            
            String entrada = sc.nextLine().trim(); // Lee la opcion como texto para evitar fallas por letras 
            if (entrada.equals("1")){ // Si elige Fibonacci
                ejecutarFibonacci(sc); // Llama al caso de uso Fibonacci
            } else if (entrada.equals("2")){ // Si elige Subset Sum
                ejecutarSubsetSum(sc); // Llama al caso de uso Subset Sum
            } else if (entrada.equals("3")){ // Si elige Sudoku
                ejecutarSudoku(sc); // Llama al caso de uso Sudoku
            } else if (entrada.equals("4")){ // Si elige salir
                System.out.println("Gracias Programa finalizado.");// Mensjae de despedida
                break; // Rompe el bucle y termina
            } else { // Si la opcion no es valida
                System.out.println("Opcion no valida. Intenta de nuevo."); // Retroalimentacion
            }
            
        }
        sc.close(); // Cierra el lector al terminar
    }
    

    // 1) Fibonacci Recursivo
    private static void ejecutarFibonacci(Scanner sc){ // Caso de uso para pedir n y calcular Fibonacci
        System.out.println("\n--- Fibonacci recursivo ---");// Titulo de seccion
        System.out.print("Ingresa n (entero >= 0): ");// Pide el indice n
        String txt = sc.nextLine().trim();// Lee texto
        if (!esEnteroNoNegativo(txt)){ // Valida que sea entero no negativo
            System.out.println("Entrada invalida. Debe ser un entero >= 0.");// Mensaje de error
            return; // Regresa al menu
        }
        int n = Integer.parseInt(txt); // Convierte la entrada a entero
        long resultado = fibonacci(n); // Calcula fibonacci(n) de forma recursiva
        System.out.println("F(" + n + ") = " + resultado);// Muestar el resultado

    }

    private static long fibonacci(int n){ // Funcion recursiva de Fibonacci
        // Casos base: F(0)=0 y F(1)=1
        // Define condiciones de parada
        if (n == 0) // Retorna 0 para n=0
        return 0;
        if (n == 1) // Retorna 1 para n=1
        return 1;
        // Caso recursivo: F(n)=F(n-1)+F(n-2)
        // Descompone el problema en subproblemas
        return fibonacci(n - 1) + fibonacci(n - 2); // Llamadas recursivas
 
    }

    private static boolean esEnteroNoNegativo(String s){ // Valida que el texto describe un entero >=0
        if (s.isEmpty()) // Rechaza vacio
        return false;
        for (int i = 0; i < s.length(); i++){ // Recorre cada caracter
            if (!Character.isDigit(s.charAt(i))) // Exige digitos
            return false;
        }
        return true; // Es valido
    }
    // 2) Suma de subconjuntos
    private static void ejecutarSubsetSum(Scanner sc){ // Caso de uso Subset Sum
        System.out.println("\n--- Suma de subconjuntos (Subset Sum) ---"); // Titulo de seccion
        System.out.println("Ingresa la lista de enteros separada por espacios (ej. 3 34 4 12 5 2):"); // Instrucciones de entrada
        String linea = obtenerLineaNoVacia(sc); // Lee una linea con datos
         int[] numeros = parsearEnteros(linea); // Convierte a arreglo de enteros (ignora tokens no numericos)
        if (numeros.length == 0){ // Si no hay numeros validos
            System.out.println("No se detectaron enteros validos.");// Aviso
            return; // Regresa al menu
        }
        System.out.print("Ingresa el valor objetivo (entero): ");// Pide objetivo de suma
        String objetivoTxt = obtenerLineaNoVacia(sc); // Lee objetivo
        Integer objetivo = parsearEntero(objetivoTxt); // Convierte a entero (o null si invalido)
        if (objetivo == null){ // Si no es entero
            System.out.println("Objetivo invalido."); // Aviso
            return; //Regresa al menu
        }

        List<Integer> subconjunto = new ArrayList<>();// Guardara un subconjunto si existe
        boolean existe = subsetSumRec(numeros, 0, objetivo, subconjunto);// Llama al backtracking recursivo
    
        if (existe){ // Si se encontro solucion
            System.out.println("Existe un subconjunto que suma " + objetivo + ": " + subconjunto); // Muestra subconjunto
        } else { // Si no se encontro
            System.out.println("No existe un subconjunto que sume " + objetivo + ".");// Mensaje negativo

        }
    }

    private static boolean subsetSumRec(int[] nums, int i, int objetivo, List<Integer> actual){ // Backtracking recursivo
        // Caso base 1: Objetivo alcanzado
        // Exito si la suma es exacta
        if (objetivo == 0) // Se logro la suma
        return true; 
        // Caso base 2: Sin mas elementos
        // Fin sin exito si no quedan numeros
        if (i == nums.length) // No hay mas opciones
        return false;

        // Opcion 1: incluir nums[i] si ayuda decide tomar  el elemento actual
        if (objetivo - nums[i] >= Integer.MIN_VALUE){ // Chequeo trivial para claridad (evitar desbordes extremos)
            actual.add(nums[i]); // Agrega el elemento a la solucion parcial
            if (subsetSumRec(nums, i + 1, objetivo - nums[i], actual)) // Avanza con elemento incluido
            return true;
            actual.remove(actual.size() - 1); // Retrocede (backtrack) si no funciono
        }
        // Opcion 2: excluir nums[i] Prueba sin tomar el elemento actual
        return subsetSumRec(nums, i + 1, objetivo, actual);// Avanza sin el elemento
    }

    private static String obtenerLineaNoVacia(Scanner sc){ // Garantiza leer una linea no vacia
        String l; // Variable para la linea
        do{ // Repite hasta que haya contenido
            l = sc.nextLine().trim(); // Lee y recorta
        } while (l.isEmpty()); // Sigue si estaba vacia
        return l; // Regresa linea valida
    }

    private static int[] parsearEnteros(String linea){ // Convierte una linea a arreglo de enteros
        String[] partes = linea.split("\\s+"); // Separa por espacios
        List<Integer> lista = new ArrayList<>(); // Contenedor temporal
        for (String p : partes){ // Recorre tokens
            Integer v = parsearEntero(p);// Intenta convertir a entero
            if (v != null) lista.add(v);// Agrega si es valido
        }
        int[] arr = new int[lista.size()];// Crea arreglo del tamaño correcto
        for (int i = 0; i < lista.size(); i++) arr[i] = lista.get(i); // Copia a arreglo primitivo
        return arr; // Retorna arreglo
    }

    private static Integer parsearEntero(String s){// Intenta parsear entero (null si falla)
        try{ // Bloque de prueba
            return Integer.parseInt(s); // Convierte a entero
        } catch (Exception e){ // Si no es convertible
            return null; // Retorna null
        }
    }
    
    // 3) Sudoku (Backtracking)
    private static void ejecutarSudoku(Scanner sc){ // Caso de uso Sudoku
        System.out.println("\n--- Resolver Sudoku (9x9) ---"); // Titulo de seccion
        System.out.println("Elige: 1) usar tablero de ejemplo 2) capturar tablero manualmente"); // Instrucciones
        String op = sc.nextLine().trim(); // Lee eleccion

        int[][] tablero; // Matriz del sudoku
        if (op.equals("2")){ // Si usuario quiere capturar
            tablero = leerTableroSudoku(sc); // Lee 9 filas de 9 enteros (0=vacio)
        } else { // Por defecto usa ejemplo
            tablero = tableroEjemplo(); // Carga un tablero clasico parcialmente vacio
            System.out.println("Usando tablero de ejemplo (0 significa celda vacia)."); // Avisa al usuario
        }
    
        System.out.println("\nTablero inicial"); // Encabezado
        imprimirTablero(tablero); // Muestra el tablero antes de resolver

        if (resolverSudoku(tablero)){ // Llama al backtracking para resolver
            System.out.println("\nSolucion encontrada:"); // Encabezado de solucion
            imprimirTablero(tablero);// Muestra el tablero resuelto
        } else { // Si no hay solucion
            System.out.println("\nNo existe solucion para este tablero.");// Aviso
        }
    }

        private static int[][] leerTableroSudoku(Scanner sc){ // Permite capturar un sudoku 9x9
            int[][] t = new int[9][9]; // Crea matriz 9x9
            System.out.println("Introduce 9 filas con 9 numeros (0-9) separados por espacios, usa 0 para vacio:"); // Instruciones
            for (int f = 0; f < 9; f++){// Itera filas
            String linea; // Linea actual
            while (true){ // Repite hasta capturar 9 valores validos
                System.out.print("Fila " + (f + 1) + ": "); // Pide fila f
                linea = sc.nextLine().trim(); // Lee linea
                String[] partes = linea.split("\\s+"); // Separa por espacios
                 if (partes.length == 9){ // Verifica cantidad correcta
                    boolean ok = true; // Indicador de validacion
                    for (int c = 0; c < 9; c++){// Recorre columnas
                        Integer v = parsearEntero(partes[c]); // Convierte a entero
                        if (v == null || v < 0 || v > 9){ // Verifica rango 0-9
                        ok = false;
                        break;
                        }
                        t[f][c] = v; // Asigna valor a la celda                  
                    }
                    if (ok)// Sale del while si la fila fue valida
                    break;
                }
                System.out.println("Entrada invalida. Deben ser 9 enteros entre 0 y 9.");// Error y reintento
            }
        }
        return t; // Retorna el tablero capturado
    }

    private static int[][] tableroEjemplo(){ // Retorna un tablero ejemplo estandar
        return new int[][]{ // Matriz literal
            {5,3,0, 0,7,0, 0,0,0},// Fila 1 (0=vacio)
            {6,0,0, 1,9,5, 0,0,0},// Fila 2
            {0,9,8, 0,0,0, 0,6,0},// Fila 3
            {8,0,0, 0,6,0, 0,0,3},// Fila 4
            {4,0,0, 8,0,3, 0,0,1},// Fila 5
            {7,0,0, 0,2,0, 0,0,6},// Fila 6
            {0,6,0, 0,0,0, 2,8,0},// Fila 7
            {0,0,0, 4,1,9, 0,0,5},// Fila 8
            {0,0,0, 0,8,0, 0,7,9}// Fila 9

        };
    }
    private static void imprimirTablero(int[][] t){// Imprime el tablero con separadores
        for (int f = 0; f < 9; f++){ // Recorre filas
            if (f % 3 == 0) 
            System.out.println("+-------+-------+-------+"); // Linea gruesa cada 3 filas
            for (int c = 0; c < 9; c++){ // Recorre columnas
                if (c % 3 == 0)System.out.print("| "); // Separador de subcuadricula
                System.out.print((t[f][c] == 0 ? "." : t[f][c]) + " "); // Muestra punto si vacio, numero si lleno
            }
            System.out.println("|");// Cierra la fila
        }
        System.out.println("+-------+-------+-------+");// Linea final
    }

    private static boolean resolverSudoku(int[][] t){ // Algoritmo de backtracking para sudoku
        int fila = -1, col = -1; // Variables para ubicar un vacio
        boolean hayVacio = false;// Indicador para saber si quedan celdas vacias
        for (int f = 0; f < 9 && !hayVacio; f++){ // Busca una celda vacia
            for (int c = 0; c < 9 && !hayVacio; c++){// Recorre columnas
                if (t[f][c] == 0){ // Guarda posicion del primer vacio
                   fila = f; 
                   col = c;
                   hayVacio = true;
                }
            }
        }
        if (!hayVacio) // Si no hay vacios el tablero esta resuelto
        return true;
    
        for (int num = 1; num <= 9; num++){ // Prueba numeros del 1 al 9
            if (esSeguro(t, fila, col, num)){ // Verifica si el numero es valido en la posicion
                t[fila][col] = num; // Coloca el numero
                if (resolverSudoku(t)) //Avanza recursivamente si resuelve retorna exito
                return true;
                t[fila][col] = 0; // Si no funciono deshace el movimiento (backtracking)
            }

        }
        return false; // Si ningun numero funciono retrocede en la recursion
    }

    private static boolean esSeguro(int[][] t, int fila, int col, int num){ // Verifica el sudoku
        for (int c = 0; c < 9; c++){ // Revisa fila
            if (t[fila][c] == num) 
            return false;
        }
    
        for (int f = 0; f < 9; f++){
            if (t[f][col] == num)
            return false; // Revisa columna
        }

        int fInicio = (fila / 3) * 3; // Fila inicial del bloque 3x3
        int cInicio = (col / 3) * 3; // Columna inicial del bloque 3x3
        for (int f = fInicio; f < fInicio + 3; f++){ //Recorre subcuadricula
            for (int c = cInicio; c < cInicio + 3; c++){ // Recorre subcuadricula
                if (t[f][c] == num) // Si el numero ya esta en el bloque no es seguro
                return false;
            }
        }
        return true;// Si paso las tres pruebas es secugo colocar el numero

    }
}
  