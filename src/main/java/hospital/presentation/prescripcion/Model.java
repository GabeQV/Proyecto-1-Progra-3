package hospital.presentation.prescripcion;

import hospital.logic.Receta;
import hospital.presentation.abstracts.AbstractModel;

import javax.swing.table.TableModel;
import java.util.ArrayList;
import java.util.List;

public class Model extends AbstractModel {
    Receta current;
    List<Receta> list;
    RecetaTableModel tableModel;

    public static final String CURRENT = "current";
    public static final String LIST = "list";
    public static final String TABLE = "table";

    public Model() {
        current = new Receta();
        list = new ArrayList<>();
    }

    public Receta getCurrent() { return current; }

    public void setCurrent(Receta current) {
        this.current = current;
        firePropertyChange(CURRENT);
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

    public TableModel getTableModel() {
        return tableModel;
    }
}
