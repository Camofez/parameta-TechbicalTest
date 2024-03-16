package parametaTechnicalTest.parametaTechnicalTest.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import parametaTechnicalTest.parametaTechnicalTest.DTO.EmpleadoResponseDTO;
import parametaTechnicalTest.parametaTechnicalTest.Model.Empleado;
import parametaTechnicalTest.parametaTechnicalTest.Service.EmpleadoSOAPService;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@RestController
public class EmpleadoRestController {

    private final EmpleadoSOAPService empleadoSOAPService;

    @Autowired
    public EmpleadoRestController(EmpleadoSOAPService empleadoSOAPService) {
        this.empleadoSOAPService = empleadoSOAPService;
    }

    @PostMapping("/crear-empleado")
    public ResponseEntity<?> crearEmpleado(@RequestBody Empleado empleado) {
        // Realizar validaciones de datos
        if (empleado == null) {
            return ResponseEntity.badRequest().body("El empleado es nulo");
        }
        if (empleado.getNombres() == null || empleado.getNombres().isEmpty()) {
            return ResponseEntity.badRequest().body("Los nombres del empleado no pueden estar vacíos");
        }
        if (empleado.getApellidos() == null || empleado.getApellidos().isEmpty()) {
            return ResponseEntity.badRequest().body("Los apellidos del empleado no pueden estar vacíos");
        }
        if (empleado.getFechaNacimiento() == null || empleado.getFechaVinculacion() == null) {
            return ResponseEntity.badRequest().body("Las fechas de nacimiento y vinculación deben ser proporcionadas");
        }

        // Verificar que la fecha de nacimiento sea anterior a la fecha actual
        Date fechaActual = new Date();
        if (empleado.getFechaNacimiento().after(fechaActual)) {
            return ResponseEntity.badRequest().body("La fecha de nacimiento no puede ser posterior a la fecha actual");
        }

        // Verificar que la fecha de vinculación sea anterior o igual a la fecha actual
        if (empleado.getFechaVinculacion().after(fechaActual)) {
            return ResponseEntity.badRequest().body("La fecha de vinculación no puede ser posterior a la fecha actual");
        }

        // Calcular edad actual del empleado
        LocalDate fechaActualLocal = LocalDate.now();
        LocalDate fechaNacimiento = empleado.getFechaNacimiento().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Period edad = Period.between(fechaNacimiento, fechaActualLocal);
        if(edad.getYears()<18){
            return ResponseEntity.badRequest().body("El empleado debe ser mayor de edad");
        }


        if (empleadoSOAPService.verificarDocumentoExistente(empleado.getNumeroDocumento())) {
            return ResponseEntity.badRequest().body("El número de documento ya está en uso");
        }

        // Llama al servicio SOAP para guardar al empleado en la base de datos
        empleadoSOAPService.guardarEmpleado(empleado);

        // Calcular tiempo de vinculación a la compañía

        LocalDate fechaVinculacion = empleado.getFechaVinculacion().toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();

        Period periodoVinculacion = Period.between(fechaVinculacion, fechaActualLocal);

        String periodoVinculacionDTO=periodoVinculacion.getYears()+"Y"+"-"+periodoVinculacion.getMonths()+"M"+
                "-"+ periodoVinculacion.getDays()+"D";
        String edadDTO=edad.getYears()+"Y"+"-"+ edad.getMonths()+"M"+"-"+ edad.getDays()+"D";

        // Agregar la información adicional al objeto empleado
        EmpleadoResponseDTO responseDTO = new EmpleadoResponseDTO(empleado, periodoVinculacionDTO, edadDTO);

        // Devolver el DTO de respuesta en la respuesta JSON
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
}
