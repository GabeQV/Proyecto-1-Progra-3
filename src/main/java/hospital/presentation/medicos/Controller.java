package hospital.presentation.medicos;
import hospital.logic.Medico;
import hospital.logic.Service;
import hospital.presentation.medicos.MedicosView;

public class Controller {
    MedicosView view;
    Model model;

    public Controller(MedicosView view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.setList(Service.instance().findAllMedicos());
    }

    public void create(Medico e) throws  Exception{
        Service.instance().createMedico(e);
        model.setCurrent(new Medico());
        model.setList(Service.instance().findAllMedicos());
    }

    public void read(String id) throws Exception {
        Medico e = new Medico();
        e.setId(id);
        if(Service.instance().readMedico(e)!=null){
            try {
                model.setCurrent(Service.instance().readMedico(e));
            } catch (Exception ex) {
                Medico b = new Medico();
                b.setId(id);
                model.setCurrent(b);
                throw ex;
            }
        }

    }

    public void clear() {
        model.setCurrent(new Medico());
    }

    public void delete(Medico e) throws Exception{
        Service.instance().deleteMedico(e);
        model.setCurrent(new Medico());
        model.setList(Service.instance().findAllMedicos());
    }
}
