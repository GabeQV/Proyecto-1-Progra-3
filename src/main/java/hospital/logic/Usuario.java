package hospital.logic;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Medico.class, Paciente.class, Farmaceuta.class})
public abstract class Usuario {
    @XmlElement
    @XmlID
    protected String id;
    @XmlElement
    protected String clave;
    @XmlElement
    protected String nombre;

    protected Usuario(){}

    public Usuario(String id, String clave, String nombre) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public abstract String getTipo();
}
