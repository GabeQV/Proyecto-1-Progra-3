package hospital.presentation.prescripcion;

import hospital.logic.Paciente;
import hospital.logic.Receta;
import hospital.presentation.abstracts.AbstractModel;

import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Receta currentReceta;
    Paciente currentPaciente;
    List<Receta> list;

    public static final String CURRENT_RECETA = "currentreceta";
    public static final String CURRENT_PACIENTE = "currentpaciente";
    public static final String LIST = "list";

    public Model() {
        currentReceta = new Receta();
        currentPaciente = new Paciente();
        list = new ArrayList<>();
    }

    public Receta getCurrentReceta() { return currentReceta; }

    public void setCurrentReceta(Receta current) {
        this.currentReceta = current;
        firePropertyChange(CURRENT_RECETA);
    }

    public List<Receta> getList() { return list; }

    public void setList(List<Receta> list) {
        this.list = list;
        firePropertyChange(LIST);
    }

    public void addReceta(Receta r) {
        list.add(r);
        firePropertyChange(LIST);
    }

    public void removeReceta(int index) {
        if (index >= 0 && index < list.size()) {
            list.remove(index);
            firePropertyChange(LIST);
        }
    }

    public Paciente getCurrentPaciente() {
        return currentPaciente;
    }

    public void setCurrentPaciente(Paciente currentPaciente) {
        this.currentPaciente = currentPaciente;
        firePropertyChange(CURRENT_PACIENTE);
    }

    public List<Receta> getRecetaActualComoLista() {
        if (currentReceta != null) {
            java.util.List<Receta> lista = new java.util.ArrayList<>();
            lista.add(currentReceta);
            return lista;
        }
        return new java.util.ArrayList<>();
    }
}
