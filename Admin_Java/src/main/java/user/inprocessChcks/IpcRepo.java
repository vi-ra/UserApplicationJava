package user.inprocessChcks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IpcRepo extends JpaRepository<MrProductParameters, Integer>{

	@Query(value = "SELECT json_data FROM admin.ipc__mr_product_parameters where mr_id = ?" ,nativeQuery = true)
	String findJsonById(int i);
}
