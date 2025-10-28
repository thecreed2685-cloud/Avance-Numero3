package com.topitop.kpi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "empleado")
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_empleado")
    private Integer idEmpleado;

    @NotBlank
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @NotBlank
    @Column(name = "apellido", nullable = false, length = 50)
    private String apellido;

    @NotBlank
    @Email
    @Column(name = "correo_empresarial", nullable = false, unique = true, length = 100)
    private String correoEmpresarial;

    @Column(name = "puesto", length = 50)
    private String puesto;

    // Relación con Supervisor
    @ManyToOne
    @JoinColumn(name = "id_supervisor", nullable = false)
    @JsonIgnore
    private Supervisor supervisor;

    // --- Constructores ---
    public Empleado() {
    }

    public Empleado(Integer idEmpleado, String nombre, String apellido, String correoEmpresarial, String puesto, Supervisor supervisor) {
        this.idEmpleado = idEmpleado;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoEmpresarial = correoEmpresarial;
        this.puesto = puesto;
        this.supervisor = supervisor;
    }

    // --- Getters y Setters ---
    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreoEmpresarial() {
        return correoEmpresarial;
    }

    public void setCorreoEmpresarial(String correoEmpresarial) {
        this.correoEmpresarial = correoEmpresarial;
    }

    public String getPuesto() {
        return puesto;
    }

    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public Supervisor getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Supervisor supervisor) {
        this.supervisor = supervisor;
    }
}
