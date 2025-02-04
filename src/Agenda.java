public class Agenda {
    private Contacto[] contactos;
    private int cantidadDeContactos;

    public Agenda(int tamano) {
        this.contactos = new Contacto[tamano];
        this.cantidadDeContactos = 0;
    }

    public Agenda() {
        this(10);
    }

    // Metodo para buscar contacto
    public void buscaContacto (String nombre, String apellido){
        for (int i = 0; i < cantidadDeContactos; i ++) {
            if (contactos[i].getNombre().toLowerCase().equals(nombre.toLowerCase()) &&
                    contactos[i].getApellido().toLowerCase().equals(apellido.toLowerCase())) {
                System.out.println("Contacto encontrado:");
                System.out.println("Nombre: " + contactos[i].getNombre());
                System.out.println("Apellido: " + contactos[i].getApellido());
                System.out.println("Teléfono: " + contactos[i].getTelefono());
                    return;
            }
        }
        System.out.println("Contacto no encontrado.");
    }


    // Metodo para eliminar un contacto
    public void eliminarContacto (Contacto contacto){
        for (int i = 0; i < cantidadDeContactos; i ++) {
            if (contactos[i].getNombre().toLowerCase().equals(contacto.getNombre().toLowerCase()) &&
                    contactos[i].getApellido().toLowerCase().equals(contacto.getApellido().toLowerCase())) {
                // Movemos todos los contactos siguientes una posición hacia atrás
                for (int indiceSiguiente = i; indiceSiguiente < cantidadDeContactos - 1; indiceSiguiente++) {
                        contactos[indiceSiguiente] = contactos[indiceSiguiente + 1];
                }
                // Reducimos el contador de contactos
                contactos[cantidadDeContactos - 1] = null;
                cantidadDeContactos--;
                System.out.println("Contacto eliminado exitosamente.");
                return; // Salimos del método después de eliminar
            }
        }
        // Si no encontramos el contacto, mostramos un mensaje
        System.out.println("Contacto no encontrado, no se puede eliminar.");
    }

    // Método para modificar el teléfono de un contacto
    public void modificarTelefono (String nombre, String apellido, String nuevoTelefono){
        // Recorremos todo el arreglo de contactos
        for (int i = 0; i < cantidadDeContactos; i++) {
            if (contactos[i].getNombre().toLowerCase().equals(nombre.toLowerCase()) &&
                    contactos[i].getApellido().toLowerCase().equals(apellido.toLowerCase())) {
                // Modificamos el teléfono del contacto
                contactos[i].setTelefono(nuevoTelefono);
                System.out.println("Teléfono modificado exitosamente.");
                return; // Salimos del método después de modificar
            }
        }
        // Si no encontramos el contacto, mostramos un mensaje
        System.out.println("Contacto no encontrado, no se puede modificar.");
    }

    // Método para verificar si hay contactos
    public boolean tieneContactos () {
        return cantidadDeContactos > 0;
    }
}