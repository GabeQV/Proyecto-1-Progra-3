package hospital.presentation.catalogo;

import hospital.logic.Medicamento;
import hospital.logic.Receta;
import hospital.logic.Service;
import hospital.presentation.catalogo.CatologoView;
import hospital.presentation.catalogo.Model;

public class Controller {
    CatologoView view;
    hospital.presentation.catalogo.Model model;

    public Controller(CatologoView view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.setList(Service.instance().findAllMedicamentos());
    }

    public void create(Medicamento e) throws Exception {
        Service.instance().createMedicamento(e);
        model.setCurrent(new Medicamento());
        model.setList(Service.instance().findAllMedicamentos());
    }

    public void read(String id) throws Exception {
        Medicamento r = new Medicamento();
        r.setId(id);
        try {
            model.setCurrent(Service.instance().readMedicamento(r));
        } catch (Exception ex) {
            Medicamento empty = new Medicamento();
            empty.setId(id);
            model.setCurrent(empty);
            throw ex;
        }
    }

    public void delete(String id) throws Exception {
        Medicamento med = new Medicamento();
        med.setId(id);
        Service.instance().deleteMedicamento(med);
        model.setList(Service.instance().findAllMedicamentos());
        model.setCurrent(new Medicamento());
    }

    public void update(Medicamento e) throws Exception {
        Service.instance().updateMedicamento(e);
        model.setCurrent(new Medicamento());
        model.setList(Service.instance().findAllMedicamentos());
    }

    public java.util.List<Medicamento> buscarPorNombre(String nombre) throws Exception {
        return Service.instance().findMedicamentosByNombre(nombre);
    }

    public java.util.List<Medicamento> buscarPorCodigo(String codigo) throws Exception {
        return Service.instance().findMedicamentosByCodigo(codigo);
    }

    public void clear() {
        model.setCurrent(new Medicamento());
    }

}
