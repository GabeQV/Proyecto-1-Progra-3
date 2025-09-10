package hospital.logic;

import hospital.data.Data;
import hospital.data.XmlPersister;

import java.util.List;

public class Service {
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) theInstance = new Service();
        return theInstance;
    }

    private Data data;

    private Service(){
        try{
            data= XmlPersister.instance().load();
        }
        catch(Exception e){
            data =  new Data();
        }
    }

    public void stop(){
        try {
            XmlPersister.instance().store(data);
        } catch (Exception e) {
            System.out.println("Error al guardar los datos " + e.getMessage());
        }
    }


    // =============== MEDICOS ===============
    public void createMedico(Medico e) throws Exception {
        Medico result = data.getMedicos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            data.getMedicos().add(e);
        } else {
            throw new Exception("Medico ya existe");
        }
    }

    public Medico readMedico(Medico e) throws Exception {
        Medico result = data.getMedicos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Medico no existe");
        }
    }
    public List<Medico> findAllMedicos() {
        return data.getMedicos();
    }


    // =============== FARMACEUTAS ===============
    public void createFarmaceuta(Farmaceuta e) throws Exception {
        Farmaceuta result = data.getFarmaceutas().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            data.getFarmaceutas().add(e);
        } else {
            throw new Exception("Farmaceuta ya existe");
        }
    }

    public Farmaceuta readFarmaceuta(Farmaceuta e) throws Exception {
        Farmaceuta result = data.getFarmaceutas().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Farmaceuta no existe");
        }
    }
    public List<Farmaceuta> findAllFarmaceutas() {
        return data.getFarmaceutas();
    }


    // =============== PACIENTES ===============
    public void createPaciente(Paciente e) throws Exception {
        Paciente result = data.getPacientes().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            data.getPacientes().add(e);
        } else {
            throw new Exception("Paciente ya existe");
        }
    }

    public Paciente readPaciente(Paciente e) throws Exception {
        Paciente result = data.getPacientes().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Paciente no existe");
        }
    }
    public List<Paciente> findAllPacientes() {
        return data.getPacientes();
    }


    // =============== RECETAS ===============
    public void createReceta(Receta e) throws Exception {
        Receta result = data.getRecetas().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            data.getRecetas().add(e);
        } else {
            throw new Exception("Receta ya existe");
        }
    }

    public Receta readReceta(Receta e) throws Exception {
        Receta result = data.getRecetas().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Receta no existe");
        }
    }
    public List<Receta> findAllRecetas() {
        return data.getRecetas();
    }

    // =============== MEDICAMENTOS ===============

    public void createMedicamento(Medicamento e) throws Exception {
        Medicamento result = data.getMedicamentos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result == null) {
            data.getMedicamentos().add(e);
        } else {
            throw new Exception("Medicamento ya existe");
        }
    }

    public Medicamento readMedicamento(Medicamento e) throws Exception {
        Medicamento result = data.getMedicamentos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            return result;
        } else {
            throw new Exception("Medicamento no existe");
        }
    }
    public List<Medicamento> findAllMedicamentos() {
        return data.getMedicamentos();
    }


}
