package hospital.presentation.historico_recetas;

import hospital.logic.Receta;
import hospital.presentation.abstracts.AbstractTableModel;

import java.util.List;

public class TableModel extends AbstractTableModel<Receta> implements javax.swing.table.TableModel{

    public static final int ID = 0;
    public static final int MEDICAMENTO = 1;
    public static final int PRESENTACION = 2;
    public static final int CANTIDAD = 3;
    public static final int INDICACIONES = 4;
    public static final int DURACION = 5;
    public static final int ESTADO = 6;

    public TableModel(int[] cols, List<Receta> rows) {
        super(cols, rows);
    }

    @Override
    protected void initColNames() {
        colNames = new String[7];
        colNames[ID] = "ID";
        colNames[MEDICAMENTO] = "Medicamento";
        colNames[PRESENTACION] = "Presentación";
        colNames[CANTIDAD] = "Cantidad";
        colNames[INDICACIONES] = "Indicaciones";
        colNames[DURACION] = "Duración";
        colNames[ESTADO] = "Estado";
    }

    @Override
    protected Object getPropetyAt(Receta e, int col) {
        switch (cols[col]) {
            case ID:
                return e.getId();
            case MEDICAMENTO:
                return e.getMedicamento().getNombre();
            case PRESENTACION:
                return e.getMedicamento().getPresentacion();
            case CANTIDAD:
                return e.getCantidad();
            case INDICACIONES:
                return e.getIndicaciones();
            case DURACION:
                return e.getDuracion();
            case ESTADO:
                return e.getEstado();
            default:
                return "";
        }
    }

}