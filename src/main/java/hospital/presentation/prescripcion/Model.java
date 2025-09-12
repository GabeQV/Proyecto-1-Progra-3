package hospital.presentation.prescripcion;

import hospital.logic.Medicamento;
import hospital.logic.Receta;
import hospital.presentation.abstracts.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Medicamento current;
    List<Medicamento> list;

    public static final String CURRENT = "current";
    public static final String LIST = "list";

    public Model() {
        current = new Medicamento();
        list = new ArrayList<Medicamento>();
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);
        firePropertyChange(CURRENT);
        firePropertyChange(LIST);
    }

    public Medicamento getCurrent() {return current;}

    public void setCurrent(Medicamento current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public List<Medicamento> getList() {return list;}

    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener) {
        super.removePropertyChangeListener(listener);
    }

    @Override
    protected void firePropertyChange(String propertyName) {
        super.firePropertyChange(propertyName);
    }

    public void setList(List<Medicamento> allMedicamentos) {
        this.list = allMedicamentos;
        firePropertyChange(LIST);
    }


}
