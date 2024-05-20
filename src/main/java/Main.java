import database.DatabaseOperationService;
import file.entity.FileData;
import file.entity.FileDataRepository;

import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        DatabaseOperationService service = new DatabaseOperationService();
        service.openConnection();
        FileDataRepository repository = new FileDataRepository(service.getConnection());
        repository.insert(new FileData(
                UUID.randomUUID(),
                "qwe",
                "qwe",
//                (new Date()).,
//                new Date(),
                12
        ));
        System.out.println(repository.select());

        service.closeConnection();
    }
}
