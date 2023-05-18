package user.dynamicTable;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TableDataRepo extends JpaRepository<TableData, Integer> {

	@Query(value = " SELECT col1_value, col2_value, col3_value, col4_value, col5_value, col6_value, col7_value, col8_value, col9_value, col10_value  FROM admin.table_data t WHERE t.table_id= ?1", nativeQuery = true)
	List<String[]> findTableDataById(Integer tableId);

}
