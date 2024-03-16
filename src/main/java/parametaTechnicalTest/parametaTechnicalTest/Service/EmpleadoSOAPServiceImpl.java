package parametaTechnicalTest.parametaTechnicalTest.Service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import org.apache.cxf.annotations.Logging;
import org.springframework.stereotype.Service;
import parametaTechnicalTest.parametaTechnicalTest.Model.Empleado;
import parametaTechnicalTest.parametaTechnicalTest.Repository.EmpleadoRepository;

@Service
@Logging
public class EmpleadoSOAPServiceImpl implements EmpleadoSOAPService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoSOAPServiceImpl(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    @Override
    public void guardarEmpleado(Empleado empleado) {
        empleadoRepository.save(empleado);
    }

    @Override
    @WebMethod
    public boolean verificarDocumentoExistente(@WebParam(name = "numeroDocumento") String numeroDocumento) {
        return empleadoRepository.existsByNumeroDocumento(numeroDocumento);
    }
}


