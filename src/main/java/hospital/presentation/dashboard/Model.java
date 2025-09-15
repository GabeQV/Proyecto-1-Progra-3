package hospital.presentation.dashboard;

import hospital.logic.Receta;
import hospital.presentation.abstracts.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {

    public static final String CURRENT = "current";
    public static final String LIST = "list";

    List<Receta> list;
    Receta current;

    public Model() {
        current = new Receta();
        list = new ArrayList<>();

    }
    void setList(List<Receta> list) {
        this.list = list;
        firePropertyChange(LIST);
    }

    public void setCurrent(Receta current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public Receta getCurrent() {
        return current;
    }

    public List<Receta> getList() {
        return list;
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(LIST);
    }
}
