package user.inprocessChcks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.dynamicTable.TableData;
import user.dynamicTable.TableDataId;

@Service
public class IpcServiceImpl implements IpcService {

	@Autowired
	IpcRepo ipcRepo;
	
	@Autowired
	StartupRecordRepo startupRecordRepo;

	@Override
	public void saveOrUpdateTableData(int id, String json_data) {
		MrProductParameters mrProductParameters = new MrProductParameters(1, json_data);
		ipcRepo.save(mrProductParameters);		
		
	}

	@Override
	public String getProductParameters(int i) {
		return ipcRepo.findJsonById(i);	
	}

	@Override
	public void CreateOrUpdateStartupData(StartupRecordData data) {
		startupRecordRepo.save(data);		
	}

	@Override
	public String getStartupStatistics(Integer ipcId, Integer sampleId,String section) {
		return startupRecordRepo.getStartupStatisticsData(ipcId, sampleId,section);
	}

}
