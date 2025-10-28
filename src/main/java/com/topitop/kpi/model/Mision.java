package com.topitop.kpi.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull; // Importar

import java.time.LocalDateTime;

@Entity
@Table(name = "misiones") // Coincide con tu tabla SQL
public class Mision {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_mision")
    private Integer idMision;

    @NotBlank
    @Column(name = "nombre_mision", nullable = false, length = 100)
    private String nombreMision;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @Column(name = "objetivo", length = 255)
    private String objetivo;

    @Column(name = "fecha_inicio")
    private LocalDateTime fechaInicio;

    @Column(name = "fecha_fin")
    private LocalDateTime fechaFin;

    @Column(name = "estado", length = 50)
    private String estado;

    @Column(name = "puntaje_mision")
    private Integer puntajeMision;

    // --- Relaciones ---

    // Quién creó la misión
    @NotNull // Una misión siempre debe tener un supervisor creador
    @ManyToOne(fetch = FetchType.LAZY) // Usamos LAZY para eficiencia
    @JoinColumn(name = "id_supervisor_FK", nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) // Solo para recibir el ID al crear/actualizar
    private Supervisor supervisor;

    // A quién se asignó la misión (puede ser null)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_empleado_FK", nullable = true) // nullable=true porque la FK permite NULLs
    private Empleado empleadoAsignado;

    // --- Constructores ---
    public Mision() {
        // Estado por defecto al crear una misión
        this.estado = "Pendiente";
    }

    // --- Getters y Setters ---
    // (Genera todos los getters y setters para los campos)

    public Integer getIdMision() { return idMision; }
    public void setIdMision(Integer idMision) { this.idMision = idMision; }

    public String getNombreMision() { return nombreMision; }
    public void setNombreMision(String nombreMision) { this.nombreMision = nombreMision; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public LocalDateTime getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDateTime fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDateTime getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDateTime fechaFin) { this.fechaFin = fechaFin; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Integer getPuntajeMision() { return puntajeMision; }
    public void setPuntajeMision(Integer puntajeMision) { this.puntajeMision = puntajeMision; }

    public Supervisor getSupervisor() { return supervisor; }
    public void setSupervisor(Supervisor supervisor) { this.supervisor = supervisor; }

    public Empleado getEmpleadoAsignado() { return empleadoAsignado; }
    public void setEmpleadoAsignado(Empleado empleadoAsignado) { this.empleadoAsignado = empleadoAsignado; }
}