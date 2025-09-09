package hospital.data;

import hospital.logic.*;

import java.time.LocalDate;
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

        medicos.add(new Medico("111","111","Juan Perez","Odontologia"));
        pacientes.add(new Paciente("123","123","Carlos Gutierrez", LocalDate.of(1998,9,16),"89677869"));

    }

    public List<Medico> getMedicos() {return medicos;}
    public List<Paciente> getPacientes() {return pacientes;}
    public List<Farmaceuta> getFarmaceutas() { return farmaceutas; }
    public List<Medicamento> getMedicamentos() { return medicamentos; }
    public List<Receta> getRecetas() { return recetas; }

}
