package hospital.presentation.prescripcion;


import hospital.logic.Medicamento;
import hospital.logic.Receta;
import hospital.logic.Service;

public class Controller {
    PrescripcionView view;
    Model model;

    public Controller(PrescripcionView view, Model model) {
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
        Medicamento m = new Medicamento();
        m.setId(id);
        try {
            model.setCurrent(Service.instance().readMedicamento(m));
        } catch (Exception ex) {
            Medicamento empty = new Medicamento();
            empty.setId(id);
            model.setCurrent(empty);
            throw ex;
        }
    }

    public void buscarPaciente(String id) throws Exception {
        // you might want to use a Paciente class here,
        // for now this reuses Medicamento logic as placeholder
        Medicamento m = new Medicamento();
        m.setId(id);
        try {
            model.setCurrent(Service.instance().readMedicamento(m));
        } catch (Exception ex) {
            Medicamento empty = new Medicamento();
            empty.setId(id);
            model.setCurrent(empty);
            throw ex;
        }
    }

    // ➕ Add a medicamento to current prescription
    public void addMedicamento(Medicamento m) {
        model.getList().add(m);
        model.setList(model.getList()); // refresh view
    }

    // ➖ Remove medicamento by table index
    public void removeMedicamento(int index) {
        if (index >= 0 && index < model.getList().size()) {
            model.getList().remove(index);
            model.setList(model.getList());
        }
    }

    // 💾 Save prescription
    public void guardarReceta() throws Exception {
        Receta receta = new Receta();
        // here you probably need to link paciente info too
        receta.setMedicamento(model.getList().);
        Service.instance().createReceta(receta);

        // reset after save
        model.setCurrent(new Medicamento());
        model.setList(Service.instance().findAllMedicamentos());
    }

    // 🧹 Clear form
    public void clear() {
        model.setCurrent(new Medicamento());
        model.setList(Service.instance().findAllMedicamentos());
    }

    // 📄 Show details of a selected medicamento
    public Medicamento detallesMedicamento(int index) {
        if (index >= 0 && index < model.getList().size()) {
            return model.getList().get(index);
        }
        return null;
    }

}
