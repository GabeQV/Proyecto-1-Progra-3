package hospital.presentation.despacho;

import hospital.logic.Receta;
import hospital.logic.Service;
import hospital.presentation.despacho.DespachoView;
import hospital.presentation.despacho.Model;

public class Controller {
    DespachoView view;
    hospital.presentation.despacho.Model model;

    public Controller(DespachoView view, Model model) {
        this.view = view;
        this.model = model;
        //view.setController(this);
        //view.setModel(model);
        model.setList(Service.instance().findAllRecetas());
    }

    public void create(Receta e) throws  Exception{
        Service.instance().createReceta(e);
        model.setCurrent(new Receta());
        model.setList(Service.instance().findAllRecetas());
    }

    public void read(String id) throws Exception {
        Receta e = new Receta();
        e.setId(id);
        try {
            model.setCurrent(Service.instance().readReceta(e));
        } catch (Exception ex) {
            Receta b = new Receta();
            b.setId(id);
            model.setCurrent(b);
            throw ex;
        }
    }

    public void clear() {
        model.setCurrent(new Receta());
    }
}
