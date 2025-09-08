package hospital.logic;

public class Farmaceuta extends Usuario {
    public Farmaceuta(String id, String clave, String nombre) {
        super(id, clave, nombre);
    }

    @Override
    public String getTipo() {
        return "FARMACEUTA";
    }

}
