package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import javax.swing.border.EmptyBorder;


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
        this.Carrera = car;

        this.Horario = new horario();
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

    @Override
    public String toString() {
        return "Día: " + nombreDia + "\n" +
                "  7:00 - " + ClaseDeSiete + "\n" +
                "  8:50 - " + ClaseDeochocincuenta + "\n" +
                " 10:40 - " + ClaseDediezcuarenta + "\n" +
                " 12:30 - " + ClaseDedoceymedia + "\n" +
                " 14:20 - " + ClaseDedosyviente;
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

    public boolean BuscarDia(String DiaBuscado) {
        if (cabeza == null) {
            return true;
        }
        Dia actual = cabeza;
        while (actual != null) {
            if (actual.nombreDia.equals(DiaBuscado)) {
                return false;
            }
            actual = actual.siguiente;
        }
        return true;
    }

    public String mostrarHorario() {
        StringBuilder sb = new StringBuilder();
        Dia actual = cabeza;
        while (actual != null) {
            sb.append(actual.toString()).append("\n--------------------------\n");
            actual = actual.siguiente;
        }
        return sb.toString();
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
                        JOptionPane.showMessageDialog(null, "Hora no válida.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                }
                JOptionPane.showMessageDialog(null, "Clase editada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            actual = actual.siguiente;
        }
        JOptionPane.showMessageDialog(null, "Día no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
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
                JOptionPane.showMessageDialog(null, "Día reemplazado con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            actual = actual.siguiente;
        }
        JOptionPane.showMessageDialog(null, "Día no encontrado para reemplazar.", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

public class CatNotesSwing extends JFrame {
    static ArbolUsuarios arbol = new ArbolUsuarios();
    static String[] DiasDeLaSemana = {"lunes", "martes", "miércoles", "jueves", "viernes"};
    private Usuario usuarioActual = null;
    private CardLayout cardLayout = new CardLayout();
    private JPanel mainPanel = new JPanel(cardLayout);
    private JPanel loginPanel, registerPanel, menuPanel, horarioPanel, tareasPanel, historialPanel, modificarHorarioPanel, modificarClasePanel;
    private JTextField loginCorreoField, registerCorreoField, registerNombreField, registerCarreraField;
    private JPasswordField loginPasswordField, registerPasswordField;
    private JLabel usuarioNombreLabel, usuarioCorreoLabel, usuarioCarreraLabel;
    private JTextArea horarioTextArea, tareasPrioridadTextArea, historialTextArea;
    private JList<String> tareasLista;
    private DefaultListModel<String> tareasListModel;

    public CatNotesSwing() {
        // Usuarios de prueba
        arbol.insertar("juan@poligran.edu.co", "1234", "Juan Pérez", "ING.sistemas");
        arbol.insertar("maria@poligran.edu.co", "abcd", "Maria Gómez", "Diseño de modas");
        arbol.insertar("ana@poligran.edu.co", "pass", "Ana Torres", "Contaduria");

        Usuario juan = arbol.buscar("juan@poligran.edu.co", "1234");
        if (juan != null) {
            juan.pila.push("Documento A");
            juan.pila.push("Documento B");
            juan.cola.add("Tarea 1");
            juan.cola.add("Tarea 2");
            juan.colaPrioridad.add(new TareaPrioridad("Urgente A", 1));
            juan.colaPrioridad.add(new TareaPrioridad("Normal B", 5));
            juan.colaPrioridad.add(new TareaPrioridad("Importante C", 3));
            juan.Horario.insertarDia(new Dia("lunes", "Matemáticas", "Programación", "Libre", "Física", "Inglés"));
        }

        Usuario maria = arbol.buscar("maria@poligran.edu.co", "abcd");
        if (maria != null) {
            maria.pila.push("PDF Maria");
            maria.cola.add("Informe 2023");
            maria.colaPrioridad.add(new TareaPrioridad("Entrega Final", 2));
            maria.colaPrioridad.add(new TareaPrioridad("Tarea Ocasional", 6));
        }

        Usuario ana = arbol.buscar("ana@poligran.edu.co", "pass");
        if (ana != null) {
            ana.pila.push("Apunte Ana");
            ana.pila.push("Código Fuente");
            ana.cola.add("Examen Final");
            ana.cola.add("Entrega Proyecto");
            ana.colaPrioridad.add(new TareaPrioridad("Examen Extra", 4));
            ana.colaPrioridad.add(new TareaPrioridad("Estudio Libre", 1));
        }

        setTitle("Cat Notes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        loginPanel = createLoginPanel();
        registerPanel = createRegisterPanel();
        menuPanel = createMenuPanel();
        horarioPanel = createHorarioPanel();
        tareasPanel = createTareasPanel();
        historialPanel = createHistorialPanel();
        modificarHorarioPanel = createModificarHorarioPanel();
        modificarClasePanel = createModificarClasePanel();

        mainPanel.add(loginPanel, "login");
        mainPanel.add(registerPanel, "register");
        mainPanel.add(menuPanel, "menu");
        mainPanel.add(horarioPanel, "horario");
        mainPanel.add(tareasPanel, "tareas");
        mainPanel.add(historialPanel, "historial");
        mainPanel.add(modificarHorarioPanel, "modificarHorario");
        mainPanel.add(modificarClasePanel, "modificarClase");

        add(mainPanel);
        cardLayout.show(mainPanel, "login");
        setVisible(true);
    }

    private JPanel createLoginPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JLabel correoLabel = new JLabel("Correo:");
        loginCorreoField = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña:");
        loginPasswordField = new JPasswordField();
        JButton loginButton = new JButton("Iniciar sesión");
        JButton registerButton = new JButton("Registrarse");

        inputPanel.add(correoLabel);
        inputPanel.add(loginCorreoField);
        inputPanel.add(passwordLabel);
        inputPanel.add(loginPasswordField);

        buttonPanel.add(loginButton);
        buttonPanel.add(registerButton);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = loginCorreoField.getText();
                String password = new String(loginPasswordField.getPassword());
                usuarioActual = arbol.buscar(correo, password);
                if (usuarioActual != null) {
                    usuarioNombreLabel.setText("Hola, " + usuarioActual.nombre);
                    usuarioCorreoLabel.setText("Correo: " + usuarioActual.correo);
                    usuarioCarreraLabel.setText("Carrera: " + usuarioActual.Carrera);
                    mostrarHorario();
                    mostrarTareasPrioridad();
                    mostrarHistorial();
                    cardLayout.show(mainPanel, "menu");
                } else {
                    JOptionPane.showMessageDialog(CatNotesSwing.this, "Correo o contraseña incorrectos.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "register");
            }
        });

        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }

    private JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10));
        JLabel correoLabel = new JLabel("Correo (@poligran.edu.co):");
        registerCorreoField = new JTextField();
        JLabel passwordLabel = new JLabel("Contraseña:");
        registerPasswordField = new JPasswordField();
        JLabel nombreLabel = new JLabel("Nombre:");
        registerNombreField = new JTextField();
        JLabel carreraLabel = new JLabel("Carrera:");
        registerCarreraField = new JTextField();
        JButton registerButton = new JButton("Registrar");
        JButton backButton = new JButton("Volver");

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String correo = registerCorreoField.getText();
                String password = new String(registerPasswordField.getPassword());
                String nombre = registerNombreField.getText();
                String carrera = registerCarreraField.getText();
                if (arbol.insertar(correo, password, nombre, carrera)) {
                    JOptionPane.showMessageDialog(CatNotesSwing.this, "Usuario registrado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(mainPanel, "login");
                } else {
                    JOptionPane.showMessageDialog(CatNotesSwing.this, "Error: Correo inválido o ya registrado.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "login");
            }
        });

        panel.add(correoLabel);
        panel.add(registerCorreoField);
        panel.add(passwordLabel);
        panel.add(registerPasswordField);
        panel.add(nombreLabel);
        panel.add(registerNombreField);
        panel.add(carreraLabel);
        panel.add(registerCarreraField);
        panel.add(registerButton);
        panel.add(backButton);
        return panel;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 14));
        button.setForeground(Color.WHITE);
        button.setBackground(new Color(0, 123, 255)); // Un azul moderno
        button.setFocusPainted(false);
        button.setBorder(new EmptyBorder(10, 20, 10, 20)); // Padding dentro del botón
        return button;
    }

    private JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        label.setForeground(new Color(50, 50, 50)); // Un gris oscuro
        return label;
    }

    private JPanel createMenuPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel userInfoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        usuarioNombreLabel = createStyledLabel("Hola, Usuario");
        usuarioCorreoLabel = createStyledLabel("Correo: ");
        usuarioCarreraLabel = createStyledLabel("Carrera: ");
        userInfoPanel.add(usuarioNombreLabel);
        userInfoPanel.add(usuarioCorreoLabel);
        userInfoPanel.add(usuarioCarreraLabel);
        panel.add(userInfoPanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel(new GridLayout(3, 3, 10, 10)); // Más espacio entre botones
        buttonsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton ingresarHorarioButton = createStyledButton("Ingresar Horario");
        ingresarHorarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarIngresarHorarioPanel();
            }
        });
        buttonsPanel.add(ingresarHorarioButton);

        JButton horarioButton = createStyledButton("Ver Horario");
        horarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarHorario();
                cardLayout.show(mainPanel, "horario");
            }
        });
        buttonsPanel.add(horarioButton);

        JButton ingresarTareaButton = createStyledButton("Ingresar Tarea");
        ingresarTareaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarIngresarTareaPanel();
            }
        });
        buttonsPanel.add(ingresarTareaButton);

        JButton tareasButton = createStyledButton("Ver Tareas");
        tareasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTareasPrioridad();
                cardLayout.show(mainPanel, "tareas");
            }
        });
        buttonsPanel.add(tareasButton);

        JButton checkTareaButton = createStyledButton("Check Tarea");
        checkTareaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkTarea();
            }
        });
        buttonsPanel.add(checkTareaButton);

        JButton historialButton = createStyledButton("Ver Historial");
        historialButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarHistorial();
                cardLayout.show(mainPanel, "historial");
            }
        });
        buttonsPanel.add(historialButton);

        JButton modificarHorarioButton = createStyledButton("Modificar Horario");
        modificarHorarioButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarModificarHorarioPanel();
            }
        });
        buttonsPanel.add(modificarHorarioButton);

        JButton modificarClaseButton = createStyledButton("Modificar Clase");
        modificarClaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarModificarClasePanel();
            }
        });
        buttonsPanel.add(modificarClaseButton);

        JButton logoutButton = createStyledButton("Cerrar Sesión");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usuarioActual = null;
                cardLayout.show(mainPanel, "login");
            }
        });
        buttonsPanel.add(logoutButton);

        panel.add(buttonsPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createHorarioPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        horarioTextArea = new JTextArea();
        horarioTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(horarioTextArea);
        JButton backButton = new JButton("Volver");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "menu");
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);
        return panel;
    }

    private void mostrarHorario() {
        if (usuarioActual != null) {
            horarioTextArea.setText(usuarioActual.Horario.mostrarHorario());
        } else {
            horarioTextArea.setText("No hay usuario logueado.");
        }
    }

    private JPanel createTareasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        tareasPrioridadTextArea = new JTextArea();
        tareasPrioridadTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(tareasPrioridadTextArea);
        JButton backButton = new JButton("Volver");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "menu");
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);
        return panel;
    }

    private void mostrarTareasPrioridad() {
        if (usuarioActual != null) {
            PriorityQueue<TareaPrioridad> copia = new PriorityQueue<>(usuarioActual.colaPrioridad);
            StringBuilder sb = new StringBuilder("Tareas por Prioridad:\n");
            while (!copia.isEmpty()) {
                sb.append(copia.poll()).append("\n");
            }
            tareasPrioridadTextArea.setText(sb.toString());
        } else {
            tareasPrioridadTextArea.setText("No hay usuario logueado.");
        }
    }

    private JPanel createHistorialPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        historialTextArea = new JTextArea();
        historialTextArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historialTextArea);
        JButton backButton = new JButton("Volver");

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "menu");
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(backButton, BorderLayout.SOUTH);
        return panel;
    }

    private void mostrarHistorial() {
        if (usuarioActual != null) {
            Stack<String> copia = new Stack<>();
            copia.addAll(usuarioActual.pila);
            StringBuilder sb = new StringBuilder("Historial de Tareas:\n");
            while (!copia.isEmpty()) {
                sb.append(copia.pop()).append("\n");
            }
            historialTextArea.setText(sb.toString());
        } else {
            historialTextArea.setText("No hay usuario logueado.");
        }
    }

    private void mostrarIngresarHorarioPanel() {
        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(this, "Debes iniciar sesión primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        JComboBox<String> diaComboBox = new JComboBox<>(DiasDeLaSemana);
        JTextField[] clasesFields = new JTextField[5];
        String[] horas = {"7:00", "8:50", "10:40", "12:30", "14:20"};
        for (int i = 0; i < 5; i++) {
            panel.add(new JLabel("Clase " + horas[i] + ":"));
            clasesFields[i] = new JTextField();
            panel.add(clasesFields[i]);
        }
        JButton guardarButton = new JButton("Guardar Horario");
        JButton cancelarButton = new JButton("Cancelar");

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String diaSeleccionado = (String) diaComboBox.getSelectedItem();
                if (usuarioActual.Horario.BuscarDia(diaSeleccionado.toLowerCase())) {
                    String[] clases = new String[5];
                    for (int i = 0; i < 5; i++) {
                        clases[i] = clasesFields[i].getText().isEmpty() ? "Hueco" : clasesFields[i].getText();
                    }
                    Dia nuevoDia = new Dia(diaSeleccionado.toLowerCase(), clases[0], clases[1], clases[2], clases[3], clases[4]);
                    usuarioActual.Horario.insertarDia(nuevoDia);
                    JOptionPane.showMessageDialog(CatNotesSwing.this, "Horario ingresado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    cardLayout.show(mainPanel, "menu");
                } else {
                    JOptionPane.showMessageDialog(CatNotesSwing.this, "El día ya existe en el horario.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "menu");
            }
        });

        JFrame frame = new JFrame("Ingresar Horario");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        panel.add(new JLabel("Seleccionar día:"));
        panel.add(diaComboBox);
        panel.add(guardarButton);
        panel.add(cancelarButton);
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(300, 300);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    private void mostrarIngresarTareaPanel() {
        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(this, "Debes iniciar sesión primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JTextField tareaField = new JTextField();
        SpinnerModel model = new SpinnerNumberModel(1, 1, 10, 1);
        JSpinner prioridadSpinner = new JSpinner(model);
        JButton guardarButton = new JButton("Guardar Tarea");
        JButton cancelarButton = new JButton("Cancelar");

        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tarea = tareaField.getText();
                int prioridad = (int) prioridadSpinner.getValue();
                usuarioActual.cola.add(tarea);
                usuarioActual.colaPrioridad.add(new TareaPrioridad(tarea, prioridad));
                usuarioActual.pila.push("tarea creada: " + tarea);
                JOptionPane.showMessageDialog(CatNotesSwing.this, "Tarea ingresada correctamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                cardLayout.show(mainPanel, "menu");
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "menu");
            }
        });

        panel.add(new JLabel("Nombre de la tarea:"));
        panel.add(tareaField);
        panel.add(new JLabel("Prioridad (1=Alta):"));
        panel.add(prioridadSpinner);
        panel.add(guardarButton);
        panel.add(cancelarButton);

        JFrame frame = new JFrame("Ingresar Tarea");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER);
        frame.setSize(300, 200);
        frame.setLocationRelativeTo(this);
        frame.setVisible(true);
    }

    private void checkTarea() {
        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(this, "Debes iniciar sesión primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (usuarioActual.colaPrioridad.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No hay tareas en la cola de prioridad.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        TareaPrioridad tareaHecha = usuarioActual.colaPrioridad.poll();
        usuarioActual.pila.push("Completada: " + tareaHecha.tarea);
        JOptionPane.showMessageDialog(this, "Tarea marcada como hecha: " + tareaHecha, "Tarea Completada", JOptionPane.INFORMATION_MESSAGE);
        mostrarTareasPrioridad();
        mostrarHistorial();
    }

    private JPanel createModificarHorarioPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JComboBox<String> diaComboBox = new JComboBox<>(DiasDeLaSemana);
        JPanel clasesPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        JTextField[] claseFields = new JTextField[5];
        String[] horas = {"7:00", "8:50", "10:40", "12:30", "14:20"};
        for (int i = 0; i < 5; i++) {
            clasesPanel.add(new JLabel("Clase " + horas[i] + ":"));
            claseFields[i] = new JTextField();
            clasesPanel.add(claseFields[i]);
        }
        JButton reemplazarButton = new JButton("Reemplazar Día");
        JButton cancelarButton = new JButton("Cancelar");

        reemplazarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuarioActual == null) {
                    JOptionPane.showMessageDialog(CatNotesSwing.this, "Debes iniciar sesión primero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String diaSeleccionado = (String) diaComboBox.getSelectedItem();
                String[] clases = new String[5];
                for (int i = 0; i < 5; i++) {
                    clases[i] = claseFields[i].getText().isEmpty() ? "Hueco" : claseFields[i].getText();
                }
                Dia nuevoDia = new Dia(diaSeleccionado.toLowerCase(), clases[0], clases[1], clases[2], clases[3], clases[4]);
                usuarioActual.Horario.reemplazarDia(diaSeleccionado.toLowerCase(), nuevoDia);
                mostrarHorario();
                cardLayout.show(mainPanel, "menu");
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "menu");
            }
        });

        JPanel controlsPanel = new JPanel(new FlowLayout());
        controlsPanel.add(new JLabel("Seleccionar día a modificar:"));
        controlsPanel.add(diaComboBox);
        controlsPanel.add(reemplazarButton);
        controlsPanel.add(cancelarButton);

        panel.add(controlsPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(clasesPanel), BorderLayout.CENTER);
        return panel;
    }

    private void mostrarModificarHorarioPanel() {
        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(this, "Debes iniciar sesión primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        cardLayout.show(mainPanel, "modificarHorario");
        // El panel de modificar horario se crea una sola vez en createModificarHorarioPanel()
    }

    private JPanel createModificarClasePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        JComboBox<String> diaComboBox = new JComboBox<>(DiasDeLaSemana);
        JComboBox<String> horaComboBox = new JComboBox<>(new String[]{"7:00", "8:50", "10:40", "12:30", "14:20"});
        JTextField nuevaClaseField = new JTextField();
        JButton modificarButton = new JButton("Modificar Clase");
        JButton cancelarButton = new JButton("Cancelar");

        modificarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (usuarioActual == null) {
                    JOptionPane.showMessageDialog(CatNotesSwing.this, "Debes iniciar sesión primero.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                String diaSeleccionado = (String) diaComboBox.getSelectedItem();
                String horaSeleccionada = (String) horaComboBox.getSelectedItem();
                String nuevaClase = nuevaClaseField.getText();
                usuarioActual.Horario.editarClase(diaSeleccionado.toLowerCase(), horaSeleccionada, nuevaClase);
                mostrarHorario();
                cardLayout.show(mainPanel, "menu");
            }
        });

        cancelarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "menu");
            }
        });

        panel.add(new JLabel("Seleccionar día:"));
        panel.add(diaComboBox);
        panel.add(new JLabel("Seleccionar hora:"));
        panel.add(horaComboBox);
        panel.add(new JLabel("Nueva clase:"));
        panel.add(nuevaClaseField);
        panel.add(modificarButton);
        panel.add(cancelarButton);
        return panel;
    }

    private void mostrarModificarClasePanel() {
        if (usuarioActual == null) {
            JOptionPane.showMessageDialog(this, "Debes iniciar sesión primero.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        cardLayout.show(mainPanel, "modificarClase");
        // El panel de modificar clase se crea una sola vez en createModificarClasePanel()
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new CatNotesSwing();
            }
        });
    }
}
