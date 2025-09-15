package hospital.presentation.dashboard;

import hospital.logic.Receta;
import hospital.presentation.abstracts.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Receta> implements javax.swing.table.TableModel{

    public static final int MEDICAMENTO = 0;
    public static final int MES = 1;
    public static final int CANTIDAD = 2;

    public TableModel(int[] cols, List<Receta> rows) {
        super(cols, rows);
    }

    @Override
    protected void initColNames() {
        colNames = new String[3];
        colNames[MEDICAMENTO] = "Medicamento";
        colNames[MES] = "Fecha";
        colNames[CANTIDAD] = "Cantidad";
    }

    @Override
    protected Object getPropetyAt(Receta e, int col) {
        switch (cols[col]) {
            case MEDICAMENTO:
                return e.getMedicamento().getNombre();
            case MES:
                return e.getFecha().toString();
            case CANTIDAD:
                return e.getCantidad();
            default:
                return "";
        }
    }
}