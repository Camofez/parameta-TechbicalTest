package parametaTechnicalTest.parametaTechnicalTest.Service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import parametaTechnicalTest.parametaTechnicalTest.Model.Empleado;

@WebService
public interface EmpleadoSOAPService {

    @WebMethod
    void guardarEmpleado(@WebParam(name = "empleado") Empleado empleado);
    @WebMethod
    boolean verificarDocumentoExistente(@WebParam(name = "numeroDocumento") String numeroDocumento);

}

