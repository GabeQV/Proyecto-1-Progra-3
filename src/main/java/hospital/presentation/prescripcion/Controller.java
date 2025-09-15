package hospital.presentation.prescripcion;

import hospital.logic.Paciente;
import hospital.logic.Receta;
import hospital.logic.Service;

public class Controller {
    PrescripcionView view;
    Model model;

    public Controller(PrescripcionView view, Model model) {
        this.view = view;
        this.model = model;
        //view.setController(this);
        //view.setModel(model);
        model.setList(Service.instance().findAllRecetas());
    }


    public void agregarReceta(Receta r) {
        model.addReceta(r);
        model.setCurrentReceta(new Receta());
    }


    public void borrarReceta(int index) {
        model.removeReceta(index);
    }

    public void guardarRecetas(String text) throws Exception {
        for (Receta r : model.getList()) {
            Service.instance().createReceta(r);
        }
        model.setList(new java.util.ArrayList<>());
    }

    public void clear() {
        model.setCurrentReceta(new Receta());
        model.setCurrentPaciente(new Paciente());
        model.setList(new java.util.ArrayList<>());
    }

    public boolean buscarPaciente(String pacienteId) {
        if (pacienteId == null) { return false; }
        for (Receta r : model.getList()) {
           if (r.getId().equals(pacienteId)) {return true;}
        }
        return false;
    }

    public void descartarMedicamento()  {
        Receta receta = model.getCurrentReceta();
        if(receta != null && receta.getMedicamento() != null) {
            model.getCurrentReceta().setMedicamento(null);
            model.setCurrentReceta(model.getCurrentReceta());
            model.getCurrentReceta().setCantidad("");
            model.getCurrentReceta().setDuracion("");
            model.getCurrentReceta().setIndicaciones("");
        }
    }

    public void guardarRecetaActual(Receta r) throws Exception {

        if (r.getId() == null || r.getId().isEmpty()) {
            r.setId(generarIdReceta());
        }
        Service.instance().createReceta(r);
        model.setCurrentReceta(new Receta());
    }

    public Receta mostrarDetalles(int index) {
        return model.getList().get(index);
    }

    private String generarIdReceta() {
        java.util.Random rand = new java.util.Random();
        String nuevoId;
        do {
            int idNum = rand.nextInt(1000); // 0 - 999
            nuevoId = String.format("%03d", idNum); // 3 dígitos
        } while (Service.instance().findRecetaById(nuevoId));
        return nuevoId;
    }
}
