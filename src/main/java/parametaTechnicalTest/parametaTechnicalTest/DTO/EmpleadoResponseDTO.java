package parametaTechnicalTest.parametaTechnicalTest.DTO;

import parametaTechnicalTest.parametaTechnicalTest.Model.Empleado;

import java.time.Period;

public class EmpleadoResponseDTO {

    private Empleado empleado;
    private String tiempoVinculacion;
    private String edadActual;

    public EmpleadoResponseDTO(Empleado empleado, String tiempoVinculacion, String edadActual) {
        this.empleado = empleado;
        this.tiempoVinculacion = tiempoVinculacion;
        this.edadActual = edadActual;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getEdadActual() {
        return edadActual;
    }

    public void setEdadActual(String edadActual) {
        this.edadActual = edadActual;
    }

    public String getTiempoVinculacion() {
        return tiempoVinculacion;
    }

    public void setTiempoVinculacion(String tiempoVinculacion) {
        this.tiempoVinculacion = tiempoVinculacion;
    }
}

