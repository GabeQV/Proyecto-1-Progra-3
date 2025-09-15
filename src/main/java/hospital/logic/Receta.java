package hospital.logic;

import hospital.data.LocalDateAdapter;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;
import jakarta.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import java.time.LocalDate;

@XmlAccessorType(XmlAccessType.FIELD)
public class Receta {
    @XmlID
    @XmlElement
    private String id;
    @XmlElement
    private String indicaciones;
    @XmlElement
    private String cantidad;
    @XmlElement
    private String duracion;
    @XmlElement
    private String estado;
    @XmlIDREF
    @XmlElement
    private Usuario usuario;
    @XmlIDREF
    @XmlElement
    private Paciente paciente;
    @XmlIDREF
    @XmlElement
    private Medicamento medicamento;
    @XmlElement
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    LocalDate fecha;

    public Receta() {
    }

    public Receta(String id, String indicaciones, String cantidad, String duracion, String estado, Medicamento medicamento, LocalDate fecha) {
        this.id = id;
        this.indicaciones = indicaciones;
        this.cantidad = cantidad;
        this.duracion = duracion;
        this.medicamento = medicamento;
        this.estado = estado;
        this.fecha = fecha;
    }

    public Receta(String id, String indicaciones, String cantidad, String duracion,String estado, Medicamento medicamento) {
        this(id, indicaciones, cantidad, duracion, estado, medicamento, null);
    }

    public String getCantidad() {
        return this.cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getIndicaciones() {
        return this.indicaciones;
    }

    public void setIndicaciones(String indicaciones) {
        this.indicaciones = indicaciones;
    }

    public String getDuracion() {
        return this.duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public Medicamento getMedicamento() {
        return this.medicamento;
    }

    public void setMedicamento(Medicamento medicamento) {
        this.medicamento = medicamento;
    }

    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {return this.estado;}
    public void setEstado(String estado) {this.estado = estado;}

    public Usuario getUsuario() { return usuario; }
    public void setUsuario(Usuario usuario) { this.usuario = usuario; }

    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

}