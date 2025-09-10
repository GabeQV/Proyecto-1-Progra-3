package hospital.presentation.login.cambio_clave;

import hospital.logic.Service;

public class Controller {
    private CambioClaveView view;

    public Controller(CambioClaveView view) {
        this.view = view;
        view.setController(this);
    }

    public void cambiarClave(String id, String claveActual, String nuevaClave, String confirmarClave) throws Exception{
        Service.instance().cambiarClave(id, claveActual, nuevaClave, confirmarClave);
        view.dispose();
    }
}
