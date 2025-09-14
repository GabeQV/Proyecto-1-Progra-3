package hospital.logic;

import jakarta.xml.bind.annotation.*;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlSeeAlso({Medico.class, Farmaceuta.class})
public abstract class Usuario {

    @XmlID
    protected String id;
    protected String clave;
    protected String nombre;

    protected Usuario(){
        this.id = "";
        this.clave = "";
        this.nombre = "";
    }

    public Usuario(String id, String clave, String nombre) {
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {this.id = id;}

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {this.nombre = nombre;}

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public abstract String getTipo();
}
