import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    public void listarContactos() {
        if (cantidadDeContactos == 0) {
            System.out.println("La agenda está vacía.");
            return;
        }

        ordenarContactos();
        System.out.println(Arrays.toString(contactos));

    }

    public List<Contacto> ordenarContactos() {
        List<Contacto> listaOrdenada = new ArrayList<>();

        for (int i = 0; i < cantidadDeContactos; i++) {
            Contacto contacto = contactos[i];
            int posicion = 0;

            // Encontrar la posición correcta en la lista ordenada
            for (Contacto c : listaOrdenada) {
                if (contacto.getNombre().compareTo(c.getNombre()) < 0 ||
                        (contacto.getNombre().compareTo(c.getNombre()) == 0 &&
                                contacto.getApellido().compareTo(c.getApellido()) < 0)) {
                    break;
                }
                posicion++;
            }

            // Insertar el contacto en la posición encontrada
            listaOrdenada.add(posicion, contacto);
        }

        return listaOrdenada;
    }


    public boolean tieneContactos() {
        return cantidadDeContactos > 0;
    }


}


