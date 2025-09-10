package hospital.logic;

import hospital.data.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Paciente extends Usuario{

    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    private LocalDate fechaNacimiento;

    private String telefono;

    public Paciente(){
        super();
        this.fechaNacimiento = LocalDate.now();
        this.telefono = "";
    }

    public Paciente(String id, String clave, String nombre, LocalDate fechaNacimiento, String telefono) {
        super(id, clave, nombre);
        this.id = id;
        this.clave = clave;
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String getTipo() {
        return "PACIENTE";
    }
}
