package user.inprocessChcks;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface StartupRecordRepo extends JpaRepository<StartupRecordData, Integer>{

	@Query(value = "SELECT json_data FROM admin.startup_record_data where ipc_id = ? and sample_id=? and section=?", nativeQuery = true)
	String getStartupStatisticsData(Integer ipcId, Integer sampleId,String section);

}
