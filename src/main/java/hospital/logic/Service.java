package hospital.logic;

import hospital.data.Data;
import hospital.data.XmlPersister;

import java.time.LocalDate;
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

    public void deleteMedico(Medico e) throws Exception {
        Medico result = data.getMedicos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.getMedicos().remove(result);
        } else {
            throw new Exception("Medico no existe");
        }
    }

    public void updateMedico(Medico e) throws Exception {
        Medico found = data.getMedicos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (found == null) throw new Exception("Medico no existe");
        found.setNombre(e.getNombre());
        found.setEspecialidad(e.getEspecialidad());
        stop();
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

    public void deleteFarmaceuta(Farmaceuta e) throws Exception {
        Farmaceuta result = data.getFarmaceutas().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.getFarmaceutas().remove(result);
        } else {
            throw new Exception("Farmaceuta no existe");
        }
    }

    public void updateFarmaceuta(Farmaceuta e) throws Exception {
        Farmaceuta found = data.getFarmaceutas().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (found == null) throw new Exception("Farmaceuta no existe");
        found.setNombre(e.getNombre());
        stop();
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

    public void deletePaciente(Paciente e) throws Exception {
        Paciente result = data.getPacientes().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.getPacientes().remove(result);
        } else {
            throw new Exception("Paciente no existe");
        }
    }

    public void updatePaciente(Paciente e) throws Exception {
        Paciente found = data.getPacientes().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (found == null) throw new Exception("Paciente no existe");
        found.setNombre(e.getNombre());
        found.setTelefono(e.getTelefono());
        found.setFechaNacimiento(e.getFechaNacimiento());
        stop();
    }

    public List<Paciente> findAllPacientes() {
        return data.getPacientes();
    }


    // =============== RECETAS ===============
    public void createReceta(Receta e) throws Exception {

        Receta result = data.getRecetas().stream()
                .filter(i -> i.getId() != null && i.getId().equals(e.getId()))
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

    public void deleteRecetas(Receta e) throws Exception {
        Receta result = data.getRecetas().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.getRecetas().remove(result);
        } else {
            throw new Exception("Receta no existe");
        }
    }

    public List<Receta> findAllRecetas() {
        return data.getRecetas();
    }

    public List<Receta> findRecetasByPacienteId(String pacienteId) {
        return data.getRecetas().stream()
                .filter(r -> r.getPaciente() != null && pacienteId.equals(r.getPaciente().getId()))
                .toList();
    }

    public void updateReceta(Receta r) throws Exception {
        Receta found = data.getRecetas().stream()
                .filter(i -> i.getId().equals(r.getId()))
                .findFirst()
                .orElse(null);
        if (found == null) throw new Exception("Receta no existe");
        found.setEstado(r.getEstado());
        stop();
    }

    public boolean findRecetaById(String id) {
        return data.getRecetas().stream()
                .anyMatch(r -> r.getId() != null && r.getId().equals(id));
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

    public void deleteMedicamento(Medicamento e) throws Exception {
        Medicamento result = data.getMedicamentos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (result != null) {
            data.getMedicamentos().remove(result);
        } else {
            throw new Exception("Medicamento no existe");
        }
    }

    public void updateMedicamento(Medicamento e) throws Exception {
        Medicamento found = data.getMedicamentos().stream()
                .filter(i -> i.getId().equals(e.getId()))
                .findFirst()
                .orElse(null);
        if (found == null) throw new Exception("Medicamento no existe");
        found.setNombre(e.getNombre());
        found.setPresentacion(e.getPresentacion());
        stop();
    }

    public List<Medicamento> findMedicamentosByNombre(String nombre) {
        String filtro = nombre.trim().toLowerCase();
        return data.getMedicamentos().stream()
                .filter(m -> m.getNombre() != null && m.getNombre().toLowerCase().contains(filtro))
                .toList();
    }

    public List<Medicamento> findMedicamentosByCodigo(String codigo) {
        String filtro = codigo.trim().toLowerCase();
        return data.getMedicamentos().stream()
                .filter(m -> m.getId() != null && m.getId().toLowerCase().contains(filtro))
                .toList();
    }

    public List<Medicamento> findAllMedicamentos() {
        return data.getMedicamentos();
    }


    // =============== AUTENTICACIÓN Y CONTRASEÑA ===============
    public Usuario login(String id, String clave) throws Exception {

        if ("ADM".equals(id) && "ADM".equals(clave)) {
            return new Admin();
        }

        Usuario user = null;

        user = data.getMedicos().stream()
                .filter(u -> u.getId().equals(id) && u.getClave().equals(clave))
                .findFirst()
                .orElse(null);

        if (user == null) {
            user = data.getFarmaceutas().stream()
                    .filter(u -> u.getId().equals(id) && u.getClave().equals(clave))
                    .findFirst()
                    .orElse(null);
        }

        if (user == null) throw new Exception("Usuario o clave incorrecta");

        return user;
    }

    public void cambiarClave(String id, String claveActual, String nuevaClave, String nuevaClaveConfirm) throws Exception {

        if ("ADM".equals(id)) {
            throw new Exception("No es posible cambiar la clave del administrador especial.");
        }

        Usuario user = null;

        user = data.getMedicos().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (user == null) user = data.getFarmaceutas().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);

        if (user == null) throw new Exception("Usuario no encontrado");

        if (!user.getClave().equals(claveActual)) throw new Exception("La clave actual no es correcta");

        if (!nuevaClave.equals(nuevaClaveConfirm)) throw new Exception("La nueva clave no coincide");

        user.setClave(nuevaClave);
        stop();
    }

}