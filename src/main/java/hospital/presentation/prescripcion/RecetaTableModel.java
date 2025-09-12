package hospital.presentation.prescripcion;

import hospital.logic.Receta;
import hospital.presentation.abstracts.AbstractTableModel;

import java.util.List;

public class RecetaTableModel extends AbstractTableModel<Receta> {

    public static final int ID = 0;

    public RecetaTableModel(int[] cols, List<Receta> rows) {
        super(cols, rows);
    }

    @Override
    protected Object getPropetyAt(Receta receta, int col) {
        switch (col) {
            case ID:
                return receta.getId();
            default:
                return "";
        }
    }

    @Override
    protected void initColNames() {
        colNames = new String[1];
        colNames[ID] = "ID";
    }
}
