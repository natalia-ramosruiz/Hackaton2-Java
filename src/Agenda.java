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
}
