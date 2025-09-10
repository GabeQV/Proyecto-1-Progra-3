package hospital.logic;

public class Sesion {
    private static Sesion theInstance;
    private Usuario usuarioActual;

    public static Sesion instance() {
        if (theInstance == null) theInstance = new Sesion();
        return theInstance;
    }

    private Sesion() {}

    public void setUsuarioActual(Usuario user) {
        this.usuarioActual = user;
    }

    public Usuario getUsuarioActual() {
        return usuarioActual;
    }

    public void abrirVentanaPrincipal() {

    }

    public void cerrarSesion() {
        this.usuarioActual = null;
    }
}
