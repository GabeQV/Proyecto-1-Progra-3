package hospital.presentation.pacientes;

import hospital.logic.Paciente;
import hospital.presentation.abstracts.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Paciente current;
    List<Paciente> list;

    public static final String CURRENT = "current";
    public static final String LIST = "list";


    public Model() {
        current = new Paciente();
        list = new ArrayList<>();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(LIST);
    }

    public Paciente getCurrent() {return current;}

    public void setCurrent(Paciente current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public List<Paciente> getList() {
        return list;
    }

    public void setList(List<Paciente> list) {
        this.list = list;
        firePropertyChange(LIST);
    }
}
