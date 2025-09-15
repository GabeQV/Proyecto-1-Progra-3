package hospital.presentation.farmaceutas;

import hospital.logic.Farmaceuta;
import hospital.logic.Service;
import hospital.presentation.farmaceutas.FarmaceutasView;
import hospital.presentation.farmaceutas.Model;

public class Controller {
    FarmaceutasView view;
    Model model;

    public Controller(FarmaceutasView view, Model model) {
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.setList(Service.instance().findAllFarmaceutas());
    }

    public void create(Farmaceuta e) throws  Exception{
        try {
            Service.instance().createFarmaceuta(e);
        } catch (Exception ex) {
            if ("Farmaceuta ya existe".equals(ex.getMessage())) {
                Service.instance().updateFarmaceuta(e);
            } else {
                throw ex;
            }
        }
        model.setCurrent(new Farmaceuta());
        model.setList(Service.instance().findAllFarmaceutas());
    }

    public void read(String id) throws Exception {
        Farmaceuta e = new Farmaceuta();
        e.setId(id);
        if(Service.instance().readFarmaceuta(e)!=null){
            try {
                model.setCurrent(Service.instance().readFarmaceuta(e));
            } catch (Exception ex) {
                Farmaceuta b = new Farmaceuta();
                b.setId(id);
                model.setCurrent(b);
                throw ex;
            }
        }

    }

    public void clear() {
        model.setCurrent(new Farmaceuta());
    }

    public void delete(Farmaceuta e) throws Exception{
        Service.instance().deleteFarmaceuta(e);
        model.setCurrent(new Farmaceuta());
        model.setList(Service.instance().findAllFarmaceutas());
    }
}
