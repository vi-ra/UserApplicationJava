package user.inprocessChcks;

public interface IpcService {

	void saveOrUpdateTableData(int id, String json_data);

	String getProductParameters(int i);
}
