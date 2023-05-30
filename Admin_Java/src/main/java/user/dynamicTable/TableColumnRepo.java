package user.dynamicTable;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TableColumnRepo extends JpaRepository<TableColumnDetails, Integer>{

	
	@Query(value =  "SELECT * FROM admin.column_details t WHERE t.table_id= ?1 ",nativeQuery = true)
	List<Object[]> getColumnDetails(Integer id);

	@Query(value =  "delete FROM admin.column_details where table_id= ?1",nativeQuery = true)
	void deleteAllById(Integer tableId); 

}
