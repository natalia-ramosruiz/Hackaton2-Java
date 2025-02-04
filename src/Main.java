import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Agenda agenda;
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        inicializarAgenda();
        int opcion;
        do {
            mostrarMenu();
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir el salto de línea
            ejecutarOpcion(opcion);
        } while (opcion != 8);
        scanner.close();
    }

    private static void inicializarAgenda() {
        System.out.print("Ingrese el tamaño de la agenda (o presione Enter para usar el tamaño por defecto de 10): ");
        String input = scanner.nextLine();
        agenda = input.isEmpty() ? new Agenda() : new Agenda(Integer.parseInt(input));
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Agenda Telefónica ---");
        System.out.println("1. Añadir Contacto");
        System.out.println("2. Buscar Contacto");
        System.out.println("3. Listar Contactos");
        System.out.println("4. Eliminar Contacto");
        System.out.println("5. Modificar Teléfono");
        System.out.println("6. Verificar si la agenda está llena");
        System.out.println("7. Verificar espacios libres");
        System.out.println("8. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> añadirContacto();
            case 2 -> buscarContacto();
            case 3 -> agenda.listarContactos();
            case 4 -> eliminarContacto();
            case 5 -> modificarTelefono();
            case 6 -> verificarAgendaLlena();
            case 7 -> verificarEspaciosLibres();
            case 8 -> System.out.println("Saliendo...");
            default ->
                    System.out.println("Opción no válida. Intente de nuevo.");
        }
    }

    private static void añadirContacto() {
        Contacto nuevoContacto = solicitarDatosContacto(true);
        if (nuevoContacto != null) {
            agenda.anadirContacto(nuevoContacto);
        }
    }

    private static void buscarContacto() {
        if (!agenda.tieneContactos()) {
            System.out.println("La agenda está vacía.");
            return;
        }
        Contacto c = solicitarDatosContacto(false);
        if (c != null) {
            agenda.buscaContacto(c.getNombre(), c.getApellido());
        }
    }

    private static void eliminarContacto() {
        if (!agenda.tieneContactos()) {
            System.out.println("La agenda está vacía.");
            return;
        }
        Contacto c = solicitarDatosContacto(false);
        if (c != null) {
            agenda.eliminarContacto(c);
        }
    }

    private static void modificarTelefono() {
        if (!agenda.tieneContactos()) {
            System.out.println("La agenda está vacía.");
            return;
        }
        Contacto c = solicitarDatosContacto(false);
        if (c != null) {
            System.out.print("Ingrese el nuevo teléfono: ");
            String nuevoTelefono = scanner.nextLine();
            agenda.modificarTelefono(c.getNombre(), c.getApellido(), nuevoTelefono);
        }
    }

    private static void verificarAgendaLlena() {
        System.out.println(agenda.agendaLlena() ? "La agenda está llena." : "Aún puedes agregar más contactos.");
    }

    private static void verificarEspaciosLibres() {
        System.out.println("Espacios libres en la agenda: " + agenda.espacioLibres());
    }

    private static Contacto solicitarDatosContacto(boolean incluirTelefono) {
        try {
            System.out.print("Ingrese el nombre: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el apellido: ");
            String apellido = scanner.nextLine();
            if (nombre.isEmpty() || apellido.isEmpty()) {
                System.out.println("El nombre y apellido no pueden estar vacíos.");
                return null;
            }
            String telefono = incluirTelefono ? pedirTelefono() : "";
            return new Contacto(nombre, apellido, telefono);
        } catch (InputMismatchException e) {
            System.out.println("Error los datos ingresados no son validos ");
        }
        return null;
    }

    private static String pedirTelefono() {
        System.out.print("Ingrese el teléfono: ");
        return scanner.nextLine();
    }
}
