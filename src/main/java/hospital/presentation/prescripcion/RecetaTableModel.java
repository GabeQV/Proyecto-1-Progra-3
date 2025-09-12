package hospital.presentation.prescripcion;

import hospital.logic.Receta;
import hospital.presentation.abstracts.AbstractTableModel;

import java.util.List;

public class RecetaTableModel extends AbstractTableModel<Receta> {

    public static final int ID = 0;
    public static final int INDICACIONES = 1;
    public static final int CANTIDAD = 2;
    public static final int DURACION = 3;
    public static final int MEDICAMENTO = 4;
    public static final int ESTADO = 5;

    public RecetaTableModel(int[] cols, List<Receta> rows) {
        super(cols, rows);
    }

    @Override
    protected Object getPropetyAt(Receta receta, int col) {
        return switch (col) {
            case ID -> receta.getId();
            case INDICACIONES -> receta.getIndicaciones();
            case CANTIDAD -> receta.getCantidad();
            case DURACION -> receta.getDuracion();
            case MEDICAMENTO -> receta.getMedicamento();
            case ESTADO -> receta.getEstado();
            default -> "";
        };
    }

    @Override
    protected void initColNames() {
        colNames = new String[6];
        colNames[ID] = "ID";
        colNames[INDICACIONES] = "Indicacion";
        colNames[CANTIDAD] = "Cantidad";
        colNames[DURACION] = "Duracion";
        colNames[MEDICAMENTO] = "Medicamento";
        colNames[ESTADO] = "Estado";

    }
}
