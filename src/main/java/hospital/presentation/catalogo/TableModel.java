package hospital.presentation.catalogo;

import hospital.logic.Medicamento;
import hospital.presentation.abstracts.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Medicamento> implements javax.swing.table.TableModel{

    public static final int ID = 0;
    public static final int NOMBRE = 1;
    public static final int PRESENTACION = 2;


    public TableModel(int[] cols, List<Medicamento> rows) {
        super(cols, rows);
    }

    @Override
    protected void initColNames() {
        colNames = new String[3];
        colNames[ID] = "ID";
        colNames[NOMBRE] = "Nombre";
        colNames[PRESENTACION] = "Presentación";

    }

    @Override
    protected Object getPropetyAt(Medicamento e, int col) {
        switch (cols[col]) {
            case ID:
                return e.getId();
            case NOMBRE:
                return e.getNombre();
            case PRESENTACION:
                return e.getPresentacion();
            default:
                return "";
        }
    }

}