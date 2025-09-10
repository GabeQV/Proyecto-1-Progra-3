package hospital.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;

@XmlAccessorType(XmlAccessType.FIELD)
public class Medicamento {
    @XmlElement
    @XmlID
    private String id;
    @XmlElement
    private String nombre;
    @XmlElement
    private String presentacion;

    public Medicamento() {
    }

    public Medicamento(String id, String nombre, String presentacion) {
        this.id = id;
        this.nombre = nombre;
        this.presentacion = presentacion;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPresentacion() {
        return this.presentacion;
    }

    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
