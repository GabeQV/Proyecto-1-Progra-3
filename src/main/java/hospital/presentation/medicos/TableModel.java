package hospital.presentation.medicos;

import javax.swing.*;

import hospital.logic.Medico;
import hospital.presentation.abstracts.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Medico> implements javax.swing.table.TableModel{

    public static final int ID = 0;
    public static final int NOMBRE = 1;
    public static final int ESPECIALIDAD = 2;

    public TableModel(int[] cols, List<Medico> rows) {
        super(cols, rows);
    }

    @Override
    protected void initColNames() {
        colNames = new String[3];
        colNames[ID] = "Id";
        colNames[NOMBRE] = "Nombre";
        colNames[ESPECIALIDAD] = "Especialidad";
    }

    @Override
    protected Object getPropetyAt(Medico e, int col) {
        switch (cols[col]) {
            case ID:
                return e.getId();
            case NOMBRE:
                return e.getNombre();
            case ESPECIALIDAD:
                return e.getEspecialidad();
            default:
                return "";
        }
    }

}
