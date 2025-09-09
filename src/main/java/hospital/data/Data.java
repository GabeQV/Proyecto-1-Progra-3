package hospital.data;

import hospital.logic.*;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Medico> medicos;
    private List<Paciente> pacientes;
    private List<Farmaceuta> farmaceutas;
    private List<Medicamento> medicamentos;
    private List<Receta> recetas;

    public Data() {
        medicos = new ArrayList<>();
        pacientes = new ArrayList<>();
        farmaceutas = new ArrayList<>();
        medicamentos = new ArrayList<>();
        recetas = new ArrayList<>();
    }

    public List<Medico> getMedicos() {return medicos;}
    public List<Paciente> getPacientes() {return pacientes;}
    public List<Farmaceuta> getFarmaceutas() { return farmaceutas; }
    public List<Medicamento> getMedicamentos() { return medicamentos; }
    public List<Receta> getRecetas() { return recetas; }

}
