package parametaTechnicalTest.parametaTechnicalTest.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import parametaTechnicalTest.parametaTechnicalTest.Model.Empleado;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    boolean existsByNumeroDocumento(String numeroDocumento);
}
