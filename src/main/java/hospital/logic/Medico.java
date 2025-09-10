package hospital.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Medico extends Usuario {

    private String especialidad;

    public Medico(String id, String clave, String nombre, String especialidad) {
        super(id, clave, nombre);
        this.especialidad = especialidad;
    }

    public Medico(){
        super();
        this.especialidad = "";
    }


    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public String getTipo() {
        return "MEDICO";
    }


}