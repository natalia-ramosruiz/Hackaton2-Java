import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Contacto[]listaContactos = new Contacto[3];


        System.out.print("Ingrese el tamaño de la agenda (o presione Enter para usar el tamaño por defecto de 10): ");
        String input = scanner.nextLine();
        Agenda agenda;
        int opcion;
        if (input.isEmpty()) {
            agenda = new Agenda();
        } else {
            agenda = new Agenda(Integer.parseInt(input));
        }

        do {
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
            opcion = scanner.nextInt();
            scanner.nextLine();

            switch (opcion) {
                /*
                case 1:
                    System.out.print("Ingrese el nombre: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese el apellido: ");
                    String apellido = scanner.nextLine();
                    System.out.print("Ingrese el teléfono: ");
                    String telefono = scanner.nextLine();
                    Contacto nuevoContacto = new Contacto(nombre, apellido, telefono);
                    agenda.añadirContacto(nuevoContacto);
                    break;
                case 2:
                    if (!agenda.tieneContactos()) {
                        System.out.println("La agenda está vacía.");
                        break;
                    }
                    System.out.print("Ingrese el nombre: ");
                    String nombreBuscar = scanner.nextLine();
                    System.out.print("Ingrese el apellido: ");
                    String apellidoBuscar = scanner.nextLine();
                    agenda.buscaContacto(nombreBuscar, apellidoBuscar);
                    break;

                 */
                case 3:
                    agenda.listarContactos();
                    break;
                    /*
                case 4:
                    if (!agenda.tieneContactos()) {
                        System.out.println("La agenda está vacía.");
                        break;
                    }
                    System.out.print("Ingrese el nombre: ");
                    String nombreEliminar = scanner.nextLine();
                    System.out.print("Ingrese el apellido: ");
                    String apellidoEliminar = scanner.nextLine();
                    Contacto contactoEliminar = new Contacto(nombreEliminar, apellidoEliminar, "");
                    agenda.eliminarContacto(contactoEliminar);
                    break;


                case 5:
                    if (!agenda.tieneContactos()) {
                        System.out.println("La agenda está vacía.");
                        break;
                    }
                    System.out.print("Ingrese el nombre: ");
                    String nombreModificar = scanner.nextLine();
                    System.out.print("Ingrese el apellido: ");
                    String apellidoModificar = scanner.nextLine();
                    System.out.print("Ingrese el nuevo teléfono: ");
                    String nuevoTelefono = scanner.nextLine();
                    agenda.modificarTelefono(nombreModificar, apellidoModificar, nuevoTelefono);
                    break;
                case 6:
                    if (agenda.agendaLlena()) {
                        System.out.println("La agenda está llena.");
                    } else {
                        System.out.println("La agenda no está llena.");
                    }
                    break;
                case 7:
                    System.out.println("Espacios libres en la agenda: " + agenda.espacioLibres());
                    break;

                     */
                case 8:
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while(opcion != 8);
    }
}