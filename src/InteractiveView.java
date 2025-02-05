import javax.swing.*;
        import javax.swing.table.*;
        import java.awt.*;
        import java.awt.*;
        import java.awt.event.*;

public class InteractiveView extends JFrame {
    private Agenda agenda;
    private JTable tablaContactos;
    private DefaultTableModel modeloTabla;
    private JTextField nombreField, apellidoField, telefonoField;
    private JLabel espaciosLabel;
    private static final Color COLOR_PRIMARIO = new Color(41, 128, 185);
    private static final Color COLOR_FONDO = new Color(236, 240, 241);

    public InteractiveView() {
        // Pedir tamaño de la agenda
        String input = JOptionPane.showInputDialog(null,
                "Ingrese el tamaño de la agenda (o presione Cancelar para usar el tamaño por defecto de 10):",
                "Tamaño de Agenda",
                JOptionPane.QUESTION_MESSAGE);

        agenda = (input == null || input.isEmpty()) ? new Agenda() : new Agenda(Integer.parseInt(input));
        configurarVentana();
        crearComponentes();
        actualizarTabla();
        setVisible(true);
    }

    private void configurarVentana() {
        setTitle("Agenda Telefónica");
        setSize(1200, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(COLOR_FONDO);
    }

    private void crearComponentes() {
        // Panel principal con margen
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(COLOR_FONDO);

        // Panel para formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(COLOR_FONDO);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);

        nombreField = crearCampoTexto("Nombre:");
        apellidoField = crearCampoTexto("Apellido:");
        telefonoField = crearCampoTexto("Teléfono:");

        // Agregar campos al formPanel
        agregarCampo(formPanel, new JLabel("Nombre:"), nombreField, gbc, 0);
        agregarCampo(formPanel, new JLabel("Apellido:"), apellidoField, gbc, 1);
        agregarCampo(formPanel, new JLabel("Teléfono:"), telefonoField, gbc, 2);

        // Panel de botones
        JPanel botonesPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 0));
        botonesPanel.setBackground(COLOR_FONDO);

        JButton agregarBtn = crearBoton("Agregar", new Color(143, 220, 178));
        JButton eliminarBtn = crearBoton("Eliminar", new Color(229, 137, 127));
        JButton modificarBtn = crearBoton("Modificar", new Color(114, 179, 222));
        JButton limpiarBtn = crearBoton("Limpiar", new Color(220, 126, 89));
        JButton buscarBtn = crearBoton("Buscar", new Color(193, 142, 218));
        JButton listarBtn = crearBoton("Lista", new Color(213, 210, 124));
        JButton verificarLlenaBtn = crearBoton("Agenda Llena", new Color(129, 225, 125));
        JButton salirBtn = crearBoton("Salir", new Color(150, 100, 80));

        botonesPanel.add(agregarBtn);
        botonesPanel.add(eliminarBtn);
        botonesPanel.add(modificarBtn);
        botonesPanel.add(limpiarBtn);
        botonesPanel.add(buscarBtn);
        botonesPanel.add(listarBtn);
        botonesPanel.add(verificarLlenaBtn);
        botonesPanel.add(salirBtn);

        // Agregar panel de botones al formPanel
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(botonesPanel, gbc);

        // Panel de información
        espaciosLabel = new JLabel("Espacios libres: " + agenda.espacioLibres());
        espaciosLabel.setHorizontalAlignment(SwingConstants.CENTER);
        JPanel infoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        infoPanel.setBackground(COLOR_FONDO);
        infoPanel.add(espaciosLabel);

        // Tabla de contactos
        modeloTabla = new DefaultTableModel(
                new Object[]{"Nombre", "Apellido", "Teléfono"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        tablaContactos = new JTable(modeloTabla);
        tablaContactos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaContactos.getTableHeader().setBackground(COLOR_PRIMARIO);
        tablaContactos.getTableHeader().setForeground(Color.BLACK);
        tablaContactos.setRowHeight(25);
        tablaContactos.setShowGrid(true);
        tablaContactos.setGridColor(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(tablaContactos);
        scrollPane.getViewport().setBackground(Color.WHITE);

        // Agregar todo al panel principal
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(COLOR_FONDO);
        topPanel.add(formPanel, BorderLayout.CENTER);
        topPanel.add(infoPanel, BorderLayout.SOUTH);

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        // Eventos
        agregarBtn.addActionListener(e -> agregarContacto());
        eliminarBtn.addActionListener(e -> eliminarContacto());
        modificarBtn.addActionListener(e -> modificarContacto());
        limpiarBtn.addActionListener(e -> limpiarCampos());
        buscarBtn.addActionListener(e -> buscarContacto());
        listarBtn.addActionListener(e -> listarContactos());
        verificarLlenaBtn.addActionListener(e -> verificarAgendaLlena());
        salirBtn.addActionListener(e -> salirAplicacion());

        tablaContactos.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting() && tablaContactos.getSelectedRow() != -1) {
                int row = tablaContactos.getSelectedRow();
                nombreField.setText((String) tablaContactos.getValueAt(row, 0));
                apellidoField.setText((String) tablaContactos.getValueAt(row, 1));
                telefonoField.setText((String) tablaContactos.getValueAt(row, 2));
            }
        });
    }

    private JTextField crearCampoTexto(String placeholder) {
        JTextField field = new JTextField(20);
        field.setBorder(BorderFactory.createCompoundBorder(
                field.getBorder(),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        return field;
    }

    private void agregarCampo(JPanel panel, JLabel label, JTextField field, GridBagConstraints gbc, int y) {
        gbc.gridy = y;
        gbc.gridx = 0;
        gbc.gridwidth = 1;
        panel.add(label, gbc);
        gbc.gridx = 1;
        panel.add(field, gbc);
    }

    private JButton crearBoton(String texto, Color color) {
        JButton boton = new JButton(texto);
        boton.setBackground(color);
        boton.setForeground(Color.BLACK);
        boton.setFocusPainted(false);
        boton.setBorderPainted(false);
        boton.setPreferredSize(new Dimension(100, 35));
        return boton;
    }

    private void agregarContacto() {
        if (validarCampos()) {
            Contacto nuevoContacto = new Contacto(
                    nombreField.getText().trim(),
                    apellidoField.getText().trim(),
                    telefonoField.getText().trim()
            );

            if (agenda.agendaLlena()) {
                mostrarMensaje("La agenda está llena", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (agenda.existeContacto(nuevoContacto)) {
                mostrarMensaje("El contacto ya existe en la agenda", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            agenda.anadirContacto(nuevoContacto);
            actualizarTabla();
            limpiarCampos();
            actualizarEspaciosLibres();
        }
    }

    private void eliminarContacto() {
        int filaSeleccionada = tablaContactos.getSelectedRow();
        if (filaSeleccionada == -1) {
            mostrarMensaje("Por favor, seleccione un contacto para eliminar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String nombre = (String) tablaContactos.getValueAt(filaSeleccionada, 0);
        String apellido = (String) tablaContactos.getValueAt(filaSeleccionada, 1);

        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea eliminar el contacto " + nombre + " " + apellido + "?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            Contacto contacto = new Contacto(nombre, apellido, "");
            agenda.eliminarContacto(contacto);
            actualizarTabla();
            limpiarCampos();
            actualizarEspaciosLibres();
        }
    }

    private void modificarContacto() {
        int filaSeleccionada = tablaContactos.getSelectedRow();
        if (filaSeleccionada == -1) {
            mostrarMensaje("Por favor, seleccione un contacto para modificar", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (validarCampos()) {
            String nombreOriginal = (String) tablaContactos.getValueAt(filaSeleccionada, 0);
            String apellidoOriginal = (String) tablaContactos.getValueAt(filaSeleccionada, 1);
            String nuevoTelefono = telefonoField.getText().trim();

            agenda.modificarTelefono(nombreOriginal, apellidoOriginal, nuevoTelefono);
            actualizarTabla();
            limpiarCampos();
        }
    }

    private void limpiarCampos() {
        SwingUtilities.invokeLater(() -> {
            nombreField.setText("");
            apellidoField.setText("");
            telefonoField.setText("");
            tablaContactos.clearSelection();
            nombreField.requestFocus();
        });
    }

    private void buscarContacto() {
        if (!agenda.tieneContactos()) {
            mostrarMensaje("La agenda está vacía.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty()) {
            mostrarMensaje("Por favor, ingrese nombre y apellido para buscar.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean encontrado = false;
        Contacto[] contactos = agenda.getContactos();
        for (int i = 0; i < agenda.getCantidadDeContactos(); i++) {
            if (contactos[i].getNombre().equalsIgnoreCase(nombre) &&
                    contactos[i].getApellido().equalsIgnoreCase(apellido)) {
                mostrarMensaje(
                        "Contacto encontrado:\n" +
                                "Nombre: " + contactos[i].getNombre() + "\n" +
                                "Apellido: " + contactos[i].getApellido() + "\n" +
                                "Teléfono: " + contactos[i].getTelefono(),
                        "Contacto Encontrado",
                        JOptionPane.INFORMATION_MESSAGE
                );
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            mostrarMensaje("Contacto no encontrado.", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void listarContactos() {
        if (!agenda.tieneContactos()) {
            mostrarMensaje("La agenda está vacía.", "Información", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Crear una nueva ventana para mostrar la lista
        JDialog listaDialog = new JDialog(this, "Lista", true);
        listaDialog.setSize(400, 300);
        listaDialog.setLocationRelativeTo(this);

        // Crear una tabla para mostrar los contactos ordenados
        DefaultTableModel modeloListado = new DefaultTableModel(
                new Object[]{"Nombre", "Apellido", "Teléfono"}, 0
        );
        JTable tablaListado = new JTable(modeloListado);

        // Ordenar contactos (usando tu método existente)
        agenda.ordenarContactos();

        // Llenar la tabla con los contactos ordenados
        Contacto[] contactos = agenda.getContactos();
        for (int i = 0; i < agenda.getCantidadDeContactos(); i++) {
            modeloListado.addRow(new Object[]{
                    contactos[i].getNombre(),
                    contactos[i].getApellido(),
                    contactos[i].getTelefono()
            });
        }

        listaDialog.add(new JScrollPane(tablaListado));
        listaDialog.setVisible(true);
    }

    private void verificarAgendaLlena() {
        String mensaje = agenda.agendaLlena() ?
                "La agenda está llena." :
                "Aún hay espacio en la agenda.\nEspacios libres: " + agenda.espacioLibres();
        mostrarMensaje(mensaje, "Estado de la Agenda", JOptionPane.INFORMATION_MESSAGE);
    }

    private void salirAplicacion(){
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Está seguro de que desea salir?",
                "Confirmar Salida",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION){
            mostrarMensaje("Saliendo...", "Adiós", JOptionPane.INFORMATION_MESSAGE); dispose();
            System.exit(0);
        }
    }

    private void actualizarTabla() {
        modeloTabla.setRowCount(0);
        Contacto[] contactos = agenda.getContactos();
        for (int i = 0; i < agenda.getCantidadDeContactos(); i++) {
            if (contactos[i] != null) {
                modeloTabla.addRow(new Object[]{
                        contactos[i].getNombre(),
                        contactos[i].getApellido(),
                        contactos[i].getTelefono()
                });
            }
        }
    }

    private void actualizarEspaciosLibres() {
        espaciosLabel.setText("Espacios libres: " + agenda.espacioLibres());
    }

    private boolean validarCampos() {
        String nombre = nombreField.getText().trim();
        String apellido = apellidoField.getText().trim();
        String telefono = telefonoField.getText().trim();

        if (nombre.isEmpty() || apellido.isEmpty() || telefono.isEmpty()) {
            mostrarMensaje("Por favor, complete todos los campos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return true;
    }

    private void mostrarMensaje(String mensaje, String titulo, int tipo) {
        JOptionPane.showMessageDialog(this, mensaje, titulo, tipo);
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> new InteractiveView());
    }
}