package ku.cs.backendapi.repository;

import ku.cs.backendapi.entity.TableType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TableTypeRepository extends JpaRepository<TableType, UUID> {
}
