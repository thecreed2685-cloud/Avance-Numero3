package com.topitop.kpi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="supervisor")
public class Supervisor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_supervisor")
    private Integer idSupervisor;

    @NotBlank
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @NotBlank
    @Column(name = "apellido", nullable = false, length = 100)
    private String apellido;

    @NotBlank
    @Email
    @Column(name = "correo_empresarial", nullable = false, unique = true, length = 255)
    private String correoEmpresarial;

    @NotBlank
    @Size(min = 8, max = 8, message = "El DNI debe tener 8 dígitos")
    @Column(name = "dni", nullable = false, unique = true, length = 8)
    private String dni;

    @Column(name = "telefono", nullable = true, length = 20)
    private String telefono;

    @Column(name = "foto_url", length = 512)
    private String fotoUrl;

    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;

    @Column(name = "fecha_ingreso")
    private LocalDateTime fechaIngreso;

    @Column(name = "puesto", length = 50)
    private String puesto;

    @Column(name = "tiempo_trabajo", length = 50)
    private String tiempoTrabajo;

    @Column(name = "es_contrato", length = 50)
    private String esContrato;

    @Column(name = "estado", length = 50)
    private String estado;

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

    public String getDni() {
        return dni;
    }
    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }
    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public String getPuesto() {
        return puesto;
    }
    public void setPuesto(String puesto) {
        this.puesto = puesto;
    }

    public String getTiempoTrabajo() {
        return tiempoTrabajo;
    }
    public void setTiempoTrabajo(String tiempoTrabajo) {
        this.tiempoTrabajo = tiempoTrabajo;
    }

    public String getEsContrato() {
        return esContrato;
    }
    public void setEsContrato(String esContrato) {
        this.esContrato = esContrato;
    }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public List<Empleado> getEmpleados() { return empleados; }
    public void setEmpleados(List<Empleado> empleados) { this.empleados = empleados; }

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
