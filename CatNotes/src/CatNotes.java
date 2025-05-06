import java.util.*;

class Usuario {
    String correo;
    String contraseña;
    String nombre;
    String Carrera;

    horario Horario;
    Stack<String> pila;
    Queue<String> cola;
    PriorityQueue<TareaPrioridad> colaPrioridad;

    Usuario izquierda, derecha;

    public Usuario(String correo, String contraseña, String nombre, String car) {
        this.correo = correo;
        this.contraseña = contraseña;
        this.nombre = nombre;
        this.Carrera=car;

        this.Horario= new horario();
        this.pila = new Stack<>();
        this.cola = new LinkedList<>();
        this.colaPrioridad = new PriorityQueue<>();
    }

}

class TareaPrioridad implements Comparable<TareaPrioridad> {
    String tarea;
    int prioridad;

    public TareaPrioridad(String tarea, int prioridad) {
        this.tarea = tarea;
        this.prioridad = prioridad;
    }

    @Override
    public int compareTo(TareaPrioridad o) {
        return Integer.compare(this.prioridad, o.prioridad);
    }

    @Override
    public String toString() {
        return tarea + " (Prioridad " + prioridad + ")";
    }
}

class ArbolUsuarios {
    Usuario raiz;

    public boolean insertar(String correo, String contraseña, String nombre, String Carrera) {
        if (!correo.endsWith("@poligran.edu.co")) {
            return false;
        }

        Usuario nuevo = new Usuario(correo, contraseña, nombre, Carrera);
        if (raiz == null) {
            raiz = nuevo;
            return true;
        }
        return insertarRec(raiz, nuevo);
    }

    private boolean insertarRec(Usuario actual, Usuario nuevo) {
        if (nuevo.correo.compareTo(actual.correo) < 0) {
            if (actual.izquierda == null) {
                actual.izquierda = nuevo;
                return true;
            }
            return insertarRec(actual.izquierda, nuevo);
        } else if (nuevo.correo.compareTo(actual.correo) > 0) {
            if (actual.derecha == null) {
                actual.derecha = nuevo;
                return true;
            }
            return insertarRec(actual.derecha, nuevo);
        } else {
            return false;
        }
    }

    public Usuario buscar(String correo, String contraseña) {
        return buscarRec(raiz, correo, contraseña);
    }

    private Usuario buscarRec(Usuario actual, String correo, String contraseña) {
        if (actual == null) return null;
        if (correo.equals(actual.correo) && contraseña.equals(actual.contraseña)) return actual;

        if (correo.compareTo(actual.correo) < 0) {
            return buscarRec(actual.izquierda, correo, contraseña);
        } else {
            return buscarRec(actual.derecha, correo, contraseña);
        }
    }
}
// HORARIO LISTA ENLAZADA :(
class Dia {
    String nombreDia;
    String ClaseDeSiete;
    String ClaseDeochocincuenta;
    String ClaseDediezcuarenta;
    String ClaseDedoceymedia;
    String ClaseDedosyviente;
    Dia siguiente;

    public Dia(String nombreDia, String claseDeSiete, String claseDeochocincuenta,
               String claseDediezcuarenta, String claseDedoceymedia, String claseDedosyviente) {
        this.nombreDia = nombreDia;
        this.ClaseDeSiete = claseDeSiete;
        this.ClaseDeochocincuenta = claseDeochocincuenta;
        this.ClaseDediezcuarenta = claseDediezcuarenta;
        this.ClaseDedoceymedia = claseDedoceymedia;
        this.ClaseDedosyviente = claseDedosyviente;
        this.siguiente = null;
    }
}
class horario {
    Dia cabeza;

    public horario() {
        this.cabeza = null;
    }
    public void insertarDia(Dia nuevoDia) {
        if (cabeza == null) {
            cabeza = nuevoDia;
            return;
        }
        Dia actual = cabeza;
        while (actual.siguiente != null) {
            actual = actual.siguiente;
        }
        actual.siguiente = nuevoDia;

    }
    public boolean BuscarDia(String DiaBuscado){
        if (cabeza==null){
            return true;
        }
        Dia actual=cabeza;
        while (actual!=null){
            if(actual.nombreDia.equals(DiaBuscado)) {
                return false;
            }
            actual=actual.siguiente;
        }
        return true;
    }
    public void mostrarHorario() {
        Dia actual = cabeza;
        while (actual != null) {
            System.out.println("Día: " + actual.nombreDia);
            System.out.println("  7:00 - " + actual.ClaseDeSiete);
            System.out.println("  8:50 - " + actual.ClaseDeochocincuenta);
            System.out.println(" 10:40 - " + actual.ClaseDediezcuarenta);
            System.out.println(" 12:30 - " + actual.ClaseDedoceymedia);
            System.out.println(" 14:20 - " + actual.ClaseDedosyviente);
            System.out.println("--------------------------");
            actual = actual.siguiente;
        }
    }

    public void editarClase(String diaBuscado, String hora, String nuevaClase) {
        Dia actual = cabeza;
        while (actual != null) {
            if (actual.nombreDia.equalsIgnoreCase(diaBuscado)) {
                switch (hora) {
                    case "7:00":
                        actual.ClaseDeSiete = nuevaClase;
                        break;
                    case "8:50":
                        actual.ClaseDeochocincuenta = nuevaClase;
                        break;
                    case "10:40":
                        actual.ClaseDediezcuarenta = nuevaClase;
                        break;
                    case "12:30":
                        actual.ClaseDedoceymedia = nuevaClase;
                        break;
                    case "14:20":
                        actual.ClaseDedosyviente = nuevaClase;
                        break;
                    default:
                        System.out.println("Hora no válida.");
                        return;
                }
                System.out.println("Clase editada con éxito.");
                return;
            }
            actual = actual.siguiente;
        }
        System.out.println("Día no encontrado.");
    }

    public void reemplazarDia(String diaBuscado, Dia nuevoDia) {
        Dia actual = cabeza;
        while (actual != null) {
            if (actual.nombreDia.equalsIgnoreCase(diaBuscado)) {
                actual.nombreDia = nuevoDia.nombreDia;
                actual.ClaseDeSiete = nuevoDia.ClaseDeSiete;
                actual.ClaseDeochocincuenta = nuevoDia.ClaseDeochocincuenta;
                actual.ClaseDediezcuarenta = nuevoDia.ClaseDediezcuarenta;
                actual.ClaseDedoceymedia = nuevoDia.ClaseDedoceymedia;
                actual.ClaseDedosyviente = nuevoDia.ClaseDedosyviente;
                System.out.println("Día reemplazado con éxito.");
                return;
            }
            actual = actual.siguiente;
        }
        System.out.println("Día no encontrado para reemplazar.");
    }
}

public class CatNotes {
    static ArbolUsuarios arbol = new ArbolUsuarios();
    static Scanner sc = new Scanner(System.in);
    static String[] DiasDeLaSemana = {"lunes", "marte", "miercoles", "jueves", "viernes"};

    public static void main(String[] args) {
        // Usuarios de prueba
        arbol.insertar("juan@poligran.edu.co", "1234", "Juan Pérez", "ING.sistemas");
        arbol.insertar("maria@poligran.edu.co", "abcd", "Maria Gómez", "Diseño de modas");
        arbol.insertar("ana@poligran.edu.co", "pass", "Ana Torres", "Contaduria");

        Usuario juan = arbol.buscar("juan@poligran.edu.co", "1234");
        juan.pila.push("Documento A");
        juan.pila.push("Documento B");
        juan.cola.add("Tarea 1");
        juan.cola.add("Tarea 2");
        juan.colaPrioridad.add(new TareaPrioridad("Urgente A", 1));
        juan.colaPrioridad.add(new TareaPrioridad("Normal B", 5));
        juan.colaPrioridad.add(new TareaPrioridad("Importante C", 3));
        juan.Horario.insertarDia(new Dia("lunes", "Matemáticas", "Programación", "Libre", "Física", "Inglés"));


        Usuario maria = arbol.buscar("maria@poligran.edu.co", "abcd");
        maria.pila.push("PDF Maria");
        maria.cola.add("Informe 2023");
        maria.colaPrioridad.add(new TareaPrioridad("Entrega Final", 2));
        maria.colaPrioridad.add(new TareaPrioridad("Tarea Ocasional", 6));

        Usuario ana = arbol.buscar("ana@poligran.edu.co", "pass");
        ana.pila.push("Apunte Ana");
        ana.pila.push("Código Fuente");
        ana.cola.add("Examen Final");
        ana.cola.add("Entrega Proyecto");
        ana.colaPrioridad.add(new TareaPrioridad("Examen Extra", 4));
        ana.colaPrioridad.add(new TareaPrioridad("Estudio Libre", 1));
        // MENU LOGIN
        menulogin();

    }

    static void menulogin(){
        int op;
        do {
            System.out.println("\n CAT NOTES ");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar sesión");
            System.out.println("3. Salir");
            System.out.print("Opción: ");
            op = sc.nextInt();
            sc.nextLine();
            switch (op) {
                case 1:
                    registrarUsuario();
                    break;
                case 2:
                    iniciarSesion();
                    break;
                case 3:
                    System.out.println("Gracias por usar Cat Notes, Vuelva pronto");
                    break;
            }
        } while (op != 3);
    }

    static void registrarUsuario() {
        System.out.print("Correo (debe ser @poligran.edu.co): ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Carrera: ");
        String Carrera = sc.nextLine();
        if (arbol.insertar(correo, pass, nombre, Carrera)) {
            System.out.println("Usuario registrado exitosamente.");
        } else {
            System.out.println("Error: Correo inválido o ya registrado.");
        }
    }

    static void iniciarSesion() {
        System.out.print("Correo: ");
        String correo = sc.nextLine();
        System.out.print("Contraseña: ");
        String pass = sc.nextLine();
        Usuario u = arbol.buscar(correo, pass);
        if (u != null) {
            int mov;
            do {
                System.out.println("Hola, " + u.nombre);
                System.out.println("Correo: " + u.correo);
                System.out.println("Carrera: " + u.Carrera);
                System.out.println("\nSelecione una opción:");
                System.out.println("1. Ingresar Horario");
                System.out.println("2. Ver horario");
                System.out.println("3. Ingresar Tareas");
                System.out.println("4. Ver tareas por Prioridad");
                System.out.println("5. Check Tarea (marcar como hecha la tarea más urgente)");
                System.out.println("6. Ver historial de tareas");
                System.out.println("7. Modificar horario");
                System.out.println("8. Salir");
                mov = sc.nextInt();
                sc.nextLine();

                switch (mov) {
                    case 1 -> ingresarHorario(u);
                    case 2 -> u.Horario.mostrarHorario();
                    case 3 -> ingresarTarea(u);
                    case 4 -> verTareasPrioridad(u);
                    case 5 -> checkTarea(u);
                    case 6 -> verHistorial(u);
                    case 7 -> modificarHorario(u);
                    case 8 -> modificarClaseDia(u);
                    case 9 -> System.out.println("Cerrando sesión...");
                    default -> System.out.println("Opción inválida");
                }
            } while (mov != 8);
        } else {
            System.out.println("Correo o contraseña incorrectos.");
        }
    }

    static void ingresarHorario(Usuario u) {
        System.out.println("Selecciona un día (donde 1 es Lunes y 5 viernes): ");
        int diaIndex = sc.nextInt() - 1;
        sc.nextLine();
        if (diaIndex < 0 || diaIndex >= DiasDeLaSemana.length) {
            System.out.println("Día inválido");
            return;
        }
        String dia = DiasDeLaSemana[diaIndex];

        if (u.Horario.BuscarDia(dia)){
            System.out.println("Ingresando clases para " + dia);
            String[] clases = new String[5];
            String[] horas = {"7:00", "8:50", "10:40", "12:30", "14:20"};
            for (int i = 0; i < 5; i++) {
                System.out.print("¿Tienes clase a las " + horas[i] + "? (si/no): ");
                String tieneClase = sc.nextLine();
                if (tieneClase.equalsIgnoreCase("si")) {
                    System.out.print("Nombre de la clase: ");
                    clases[i] = sc.nextLine();
                } else {
                    clases[i] = "Hueco";
                }
            }
            Dia nuevoDia = new Dia(dia, clases[0], clases[1], clases[2], clases[3], clases[4]);
            u.Horario.insertarDia(nuevoDia);
            System.out.println("Horario ingresado exitosamente.");
            return;
        }
        System.out.println("El Dia a ingresar ya esta Dentro del horario, elija la opción de 'modificar horario' si desea hacer algun cambio");
    }

    static void ingresarTarea(Usuario u) {
        System.out.print("Nombre de la tarea: ");
        String tarea = sc.nextLine();
        System.out.print("Prioridad (1=Alta, mayor número=menos urgente): ");
        int prioridad = sc.nextInt();
        sc.nextLine();
        u.cola.add(tarea);
        u.colaPrioridad.add(new TareaPrioridad(tarea, prioridad));
        u.pila.push("tarea creada: " + tarea);
        System.out.println("Tarea ingresada correctamente.");
    }

    static void verTareasPrioridad(Usuario u) {
        if (u.colaPrioridad.isEmpty()) {
            System.out.println("No hay tareas en la cola de prioridad.");
            return;
        }
        PriorityQueue<TareaPrioridad> copia = new PriorityQueue<>(u.colaPrioridad);
        while (!copia.isEmpty()) {
            System.out.println(copia.poll());
        }
    }

    static void checkTarea(Usuario u) {
        if (u.colaPrioridad.isEmpty()) {
            System.out.println("No hay tareas en la cola de prioridad.");
            return;
        }
        TareaPrioridad tareaHecha = u.colaPrioridad.poll();
        u.pila.push("Completada: " + tareaHecha.tarea);
        System.out.println("Tarea marcada como hecha: " + tareaHecha);
    }

    static void verHistorial(Usuario u) {
        if (u.pila.isEmpty()) {
            System.out.println("No hay tareas en el historial.");
            return;
        }
        System.out.println("Historial de tareas:");
        Stack<String> copia = new Stack<>();
        copia.addAll(u.pila);
        while (!copia.isEmpty()) {
            System.out.println(copia.pop());
        }
    }

    static void modificarHorario(Usuario u) {
        System.out.print("Día a modificar: ");
        String dia = sc.nextLine().toLowerCase();
        String[] clases = new String[5];
        String[] horas = {"7:00", "8:50", "10:40", "12:30", "14:20"};
        for (int i = 0; i < 5; i++) {
            System.out.print("¿Tienes clase a las " + horas[i] + "? (si/no): ");
            String tieneClase = sc.nextLine();
            if (tieneClase.equalsIgnoreCase("si")) {
                System.out.print("Nombre de la clase: ");
                clases[i] = sc.nextLine();
            } else {
                clases[i] = "Hueco";
            }
        }
        Dia nuevoDia = new Dia(dia, clases[0], clases[1], clases[2], clases[3], clases[4]);
        u.Horario.reemplazarDia(dia, nuevoDia);
    }

    static void modificarClaseDia(Usuario u){
        System.out.println("Ingrese el dia que desea modificar: ");
        String diaBuscado = sc.nextLine().toLowerCase();
        System.out.println("Ingrese la hora que desea modificar: ");
        String hora = sc.nextLine();
        System.out.println("Ingrese la nueva clase: ");
        String nuevaClase = sc.nextLine();
        u.Horario.editarClase(diaBuscado, hora, nuevaClase);
    }

}
