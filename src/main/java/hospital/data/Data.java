package hospital.data;

import hospital.logic.*;
import jakarta.xml.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {
    @XmlElementWrapper(name = "medicos")
    @XmlElement(name = "medico")
    private List<Medico> medicos;

    @XmlElementWrapper(name = "pacientes")
    @XmlElement(name = "paciente")
    private List<Paciente> pacientes;

    @XmlElementWrapper(name = "farmaceutas")
    @XmlElement(name = "farmaceuta")
    private List<Farmaceuta> farmaceutas;

    @XmlElementWrapper(name = "medicamentos")
    @XmlElement(name = "medicamento")
    private List<Medicamento> medicamentos;

    @XmlElementWrapper(name = "recetas")
    @XmlElement(name = "receta")
    private List<Receta> recetas;

    public Data() {
        medicos = new ArrayList<>();
        pacientes = new ArrayList<>();
        farmaceutas = new ArrayList<>();
        medicamentos = new ArrayList<>();
        recetas = new ArrayList<>();

        // Ejemplo original
        Medicamento Acetaminofen = new Medicamento("111","Acetaminofen","150 mg");


        // Ejemplo adicional 2
        Receta r3 = new Receta("125","Noche","7","7 dias","Entregado", Acetaminofen, LocalDate.of(2025, 3, 20));
        r3.setPaciente(p2);
        recetas.add(r3);

        // Ejemplo adicional 3
        Receta r4 = new Receta("126","Mañana","3","10 dias","Lista", Ibuprofeno, LocalDate.of(2025, 4, 5));
        r4.setPaciente(p);
        recetas.add(r4);

        // Ejemplo adicional 4
        Receta r5 = new Receta("127","Mañana y noche","2","3 dias","En proceso", Acetaminofen, LocalDate.of(2025, 5, 12));
        r5.setPaciente(p);
        recetas.add(r5);
    }

    public List<Medico> getMedicos() {return medicos;}
    public List<Paciente> getPacientes() {return pacientes;}
    public List<Farmaceuta> getFarmaceutas() { return farmaceutas; }
    public List<Medicamento> getMedicamentos() { return medicamentos; }
    public List<Receta> getRecetas() { return recetas; }

}