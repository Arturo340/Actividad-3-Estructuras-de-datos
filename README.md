# Actividad-3-Estructuras-de-datos
En neatbeans crea un proyecto java
Agrega una clase main llamada MenuRecursividad el package se debe de llamar menurecursividad por ejemplo seria asi package menurecursividad;
Ejecuta Run el boton con la flechita verde lo tienes que oprimir
Al iniciar veras un menu:
1 Fibonacci
2 Subset Sum
3 Sudoku
4 Salir
Escribe el numero de la opcion y presiona Enter
Sigue las instrucciones especificas de cada opcion
¿Que hace cada opcion?
1) Fibonacci recursivo
   Pide n ≥ 0
   Calcula F(n) con recursion:
   Casos base: F(0)=0, F(1)=1
   Paso recursivo: F(n)=F(n-1)+F(n-2)
   Muestra el resultado
2) Suma de subconjuntos (Subset Sum)
   Pides una lista de enteros (por ejemplo: 3 34 4 12 5 2) y un objetivo
   Usa backtraking: En cada posicion decide incluir o excluir el numero actual
   Si llega a suma 0 imprime un subconjunto que cumple si no indica que no existe
3) Resolver Sudoku (9x9)
   Puedes escoger dos opciones:
   1 usar tablero ejemplo (ceros = celdas vacias) o 2 capturar 9 filas de 9 numeros (0-9)
   Aplica backtraking:
   Busca una celda vacia
   Prueba numeros 1-9 que sean validos segun esSeguro() (no repetido en fila, columna y subcuadro 3x3)
   Avanza si es valido si mas adelante falla retrocede y prueba otro numero
   Imprime el tablero resuelto o informa si no tiene solucion

   Fibonacci: solo acepta enteros no negativos
   Subset Sum: parsea enteros desde texto y valida el objetivo
   Sudoku: verifica 9 numeros por fila (0-9) y respeta reglas del juego
