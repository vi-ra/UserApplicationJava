package user.dynamicTable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TableDataRepo extends JpaRepository<TableData, Integer> {

	@Query(value = " SELECT json_data  FROM admin.table_data t WHERE t.table_id= ?1", nativeQuery = true)
	String findTableDataById(Integer tableId);

}
