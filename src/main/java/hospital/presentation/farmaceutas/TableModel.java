package hospital.presentation.farmaceutas;

import hospital.logic.Farmaceuta;
import hospital.presentation.abstracts.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Farmaceuta> implements javax.swing.table.TableModel{

    public static final int ID = 0;
    public static final int NOMBRE = 1;

    public TableModel(int[] cols, List<Farmaceuta> rows) {
        super(cols, rows);
    }

    @Override
    protected void initColNames() {
        colNames = new String[2];
        colNames[ID] = "Id";
        colNames[NOMBRE] = "Nombre";
    }

    @Override
    protected Object getPropetyAt(Farmaceuta e, int col) {
        switch (cols[col]) {
            case ID:
                return e.getId();
            case NOMBRE:
                return e.getNombre();
            default:
                return "";
        }
    }

}

