package com.topitop.kpi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
@Table(name="supervisor")
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supervisor")
    private Integer idSupervisor;

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

    // --- Relaciones ---
    @OneToMany(mappedBy = "supervisor", cascade = CascadeType.ALL)
    private List<Empleado> empleados;

    /*
    @OneToMany(mappedBy = "supervisor", cascade = CascadeType.ALL)
    private List<Mision> misiones;

    @ManyToMany
    @JoinTable(
            name = "supervisor_rol",
            joinColumns = @JoinColumn(name = "id_supervisor"),
            inverseJoinColumns = @JoinColumn(name = "id_rol")
    )
    private List<Rol> roles;

    @OneToMany(mappedBy = "supervisor", cascade = CascadeType.ALL)
    private List<Reporte> reportes;

     */

    // --- Constructores ---
    public Supervisor() {
    }

    public Supervisor(Integer idSupervisor, String nombre, String apellido, String correoEmpresarial) {
        this.idSupervisor = idSupervisor;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correoEmpresarial = correoEmpresarial;
    }

    // --- Getters y Setters ---

    public Integer getIdSupervisor() {
        return idSupervisor;
    }

    public void setIdSupervisor(Integer idSupervisor) {
        this.idSupervisor = idSupervisor;
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

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    /*

    public List<Mision> getMisiones() {
        return misiones;
    }

    public void setMisiones(List<Mision> misiones) {
        this.misiones = misiones;
    }

    public List<Rol> getRoles() {
        return roles;
    }

    public void setRoles(List<Rol> roles) {
        this.roles = roles;
    }

    public List<Reporte> getReportes() {
        return reportes;
    }

    public void setReportes(List<Reporte> reportes) {
        this.reportes = reportes;
    }

     */
}
