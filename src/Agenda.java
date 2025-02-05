public class Agenda {
    private Contacto[] contactos;
    private int tamano;
    private int cantidadDeContactos;

    public Agenda() {
        this(10);
    }

    public Agenda(int tamano) {
        this.tamano = tamano;
        this.contactos = new Contacto[tamano];
        this.cantidadDeContactos = 0;
    }

    public void anadirContacto(Contacto c) {
        //* verifica si la agenda esta llena
        if (agendaLlena()) {
            System.out.println("La agenda está llena");
            return;
        }
        //* verifica que el contacto ya existe
        if (existeContacto(c)) {
            System.out.println("El usuario ya esta en la agenda");
            return;
        }
        contactos[cantidadDeContactos] = c;
        cantidadDeContactos++;
        System.out.println("Contacto agregado: " + c.getNombre() + " " + c.getApellido()); //
    }

    //* Verifica si existe el contacto
    public boolean existeContacto(Contacto c) {
        for (int i = 0; i < cantidadDeContactos; i++) {
            if (contactos[i].equals(c)) {
                return true;
            }
        }
        return false;
    }

    //* Metodo para listar contactos
    public void listarContactos() {
        if (cantidadDeContactos == 0) {
            System.out.println("La agenda está vacía.");
            return;
        }
        ordenarContactos();
        for (int i = 0; i < cantidadDeContactos; i++) {
            System.out.println(contactos[i]);
        }
    }

    //* Metodo para ordenar contactos
    public void ordenarContactos() {
        for (int i = 0; i < cantidadDeContactos - 1; i++) {
            for (int j = i + 1; j < cantidadDeContactos; j++) {
                if (contactos[i].getNombre().compareToIgnoreCase(contactos[j].getNombre()) > 0) {
                    Contacto temporal = contactos[i];
                    contactos[i] = contactos[j];
                    contactos[j] = temporal;
                }
            }
        }
    }

    //* Metodo para buscar contacto
    public void buscaContacto(String nombre, String apellido) {
        for (int i = 0; i < cantidadDeContactos; i++) {
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

    //* Metodo para eliminar un contacto
    public void eliminarContacto(Contacto contacto) {
        for (int i = 0; i < cantidadDeContactos; i++) {
            if (contactos[i].getNombre().toLowerCase().equals(contacto.getNombre().toLowerCase()) &&
                    contactos[i].getApellido().toLowerCase().equals(contacto.getApellido().toLowerCase())) {
                for (int indiceSiguiente = i; indiceSiguiente < cantidadDeContactos - 1; indiceSiguiente++) {
                    contactos[indiceSiguiente] = contactos[indiceSiguiente + 1];
                }
                contactos[cantidadDeContactos - 1] = null;
                cantidadDeContactos--;
                System.out.println("Contacto eliminado exitosamente.");
                return;
            }
        }
        System.out.println("Contacto no encontrado, no se puede eliminar.");
    }

    //* Metodo para modificar el teléfono de un contacto
    public void modificarTelefono(String nombre, String apellido, String nuevoTelefono) {

        for (int i = 0; i < cantidadDeContactos; i++) {
            if (contactos[i].getNombre().toLowerCase().equals(nombre.toLowerCase()) &&
                    contactos[i].getApellido().toLowerCase().equals(apellido.toLowerCase())) {
                contactos[i].setTelefono(nuevoTelefono);
                System.out.println("Teléfono modificado exitosamente.");
                return;
            }
        }
        System.out.println("Contacto no encontrado, no se puede modificar.");
    }

    //* verfica si la agenda esta llena
    public boolean agendaLlena() {
        return cantidadDeContactos >= tamano;
    }

    //* Verifica si hay espacios libres en la agenda
    public int espacioLibres() {
        return tamano - cantidadDeContactos;
    }

    //* Método para verificar si hay contactos
    public boolean tieneContactos() {
        return cantidadDeContactos > 0;
    }

    public Contacto[] getContactos() {
        return contactos;
    }

    public void setContactos(Contacto[] contactos) {
        this.contactos = contactos;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public int getCantidadDeContactos() {
        return cantidadDeContactos;
    }

    public void setCantidadDeContactos(int cantidadDeContactos) {
        this.cantidadDeContactos = cantidadDeContactos;
    }


}

