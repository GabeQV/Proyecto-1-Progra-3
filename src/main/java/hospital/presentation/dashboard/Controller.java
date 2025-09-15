package hospital.presentation.dashboard;

import hospital.logic.Receta;
import hospital.logic.Service;

import java.time.LocalDate;
import java.util.List;

public class Controller {
    Model model;
    DashboardView view;
    public Controller(DashboardView view, Model model) {
        this.model = model;
        this.view = view;
    }

    public List<Receta> findRecetasMultiple(LocalDate from, LocalDate to, List<String> medicamentosSeleccionados) {
        return Service.instance().findAllRecetas().stream()
                .filter(r -> r.getFecha() != null
                        && (from == null || !r.getFecha().isBefore(from))
                        && (to == null || !r.getFecha().isAfter(to))
                        && (medicamentosSeleccionados == null || medicamentosSeleccionados.isEmpty()
                        || medicamentosSeleccionados.contains(r.getMedicamento().getNombre())))
                .toList();
    }


    public List<Receta> findRecetas(LocalDate from, LocalDate to, String medicamentoNombre) {
        return Service.instance().findAllRecetas().stream()
                .filter(r -> r.getFecha() != null
                        && (from == null || !r.getFecha().isBefore(from))
                        && (to == null || !r.getFecha().isAfter(to))
                        && (medicamentoNombre == null || r.getMedicamento().getNombre().equals(medicamentoNombre)))
                .toList();
    }
    public void recetasOrdenadas(LocalDate from, LocalDate to, String medicamentoNombre) {
        model.setList(findRecetas(from, to, medicamentoNombre));
    }
}