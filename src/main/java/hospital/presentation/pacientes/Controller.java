package hospital.presentation.pacientes;

import hospital.logic.Paciente;
import hospital.logic.Service;


public class Controller {
    PacientesView view;
    Model model;

    public Controller(PacientesView view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.setList(Service.instance().findAllPacientes());
    }

    public void create(Paciente e) throws  Exception{
        try {
            Service.instance().createPaciente(e);
        } catch (Exception ex) {
            if ("Paciente ya existe".equals(ex.getMessage())) {
                Service.instance().updatePaciente(e);
            } else {
                throw ex;
            }
        }
        model.setCurrent(new Paciente());
        model.setList(Service.instance().findAllPacientes());
    }

    public void read(String id) throws Exception {
        Paciente e = new Paciente();
        e.setId(id);
        if(Service.instance().readPaciente(e)!=null){
            try {
                model.setCurrent(Service.instance().readPaciente(e));
            } catch (Exception ex) {
                Paciente b = new Paciente();
                b.setId(id);
                model.setCurrent(b);
                throw ex;
            }
        }

    }

    public void clear() {
        model.setCurrent(new Paciente());
    }

    public void delete(Paciente e) throws Exception{
        Service.instance().deletePaciente(e);
        model.setCurrent(new Paciente());
        model.setList(Service.instance().findAllPacientes());
    }
}
