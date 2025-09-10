package hospital.logic;

public class Admin extends Usuario{
    public Admin(){
        super("ADM","ADM","Administrador");
    }

    @Override
    public String getTipo() {
        return "ADMINISTRADOR";
    }
}
