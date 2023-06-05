package user.inprocessChcks;

public interface IpcService {

	void saveOrUpdateTableData(int id, String json_data);

	String getProductParameters(int i);

	void CreateOrUpdateStartupData(StartupRecordData strtRecData);

	String getStartupStatistics(Integer ipcId, Integer sampleId,String section);
}
