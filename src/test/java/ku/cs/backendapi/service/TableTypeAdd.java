package ku.cs.backendapi.service;

import ku.cs.backendapi.entity.TableType;
import ku.cs.backendapi.repository.TableTypeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@SpringBootTest
public class TableTypeAdd {
    @Autowired
    private TableTypeRepository tableTypeRepository;

    @Test
    public void addTableType() {
        TableType tableType = new TableType();
        tableType.setIdTableType(UUID.randomUUID());
        tableType.setSeatNumber(8);
        tableTypeRepository.save(tableType);
    }
}
