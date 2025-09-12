package hospital.presentation.prescripcion;

import hospital.logic.Medicamento;
import hospital.presentation.abstracts.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Medicamento> {

    public static final int ID = 0;
    public static final int NOMBRE = 1;
    public static final int PRESENTACION = 2;

    public TableModel(int[] cols, List<Medicamento> rows) {
        super(cols, rows);
    }

    @Override
    protected Object getPropetyAt(Medicamento medicamento, int col) {
        switch (col) {
            case ID:
                return medicamento.getId();
            case NOMBRE:
                return medicamento.getNombre();
            case PRESENTACION:
                return medicamento.getPresentacion();
            default:
                return "";
        }
    }

    @Override
    protected void initColNames() {
        colNames = new String[3];
        colNames[ID] = "ID";
        colNames[NOMBRE] = "Nombre";
        colNames[PRESENTACION] = "Presentación";
    }
}
