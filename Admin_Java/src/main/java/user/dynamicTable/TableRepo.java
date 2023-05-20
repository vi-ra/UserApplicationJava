package user.dynamicTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TableRepo extends JpaRepository<TableDetails, Integer>{

	@Query(value = "SELECT max(table_id) as max_table_no FROM admin.table_details", nativeQuery = true)
	Integer getMaxTableNo(); 

}


