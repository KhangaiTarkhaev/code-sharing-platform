package platform.model;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import platform.entities.CodeInfo;

import java.util.List;
import java.util.Optional;

public interface CodeRepository extends CrudRepository<CodeInfo, Long> , PagingAndSortingRepository<CodeInfo, Long> {
}
