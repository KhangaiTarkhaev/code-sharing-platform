package platform.model;


import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import platform.entities.CodeInfo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CodeRepository extends CrudRepository<CodeInfo, UUID> , PagingAndSortingRepository<CodeInfo, UUID> {

}
