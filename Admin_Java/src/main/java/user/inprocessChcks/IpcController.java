package user.inprocessChcks;

import java.util.LinkedHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 4800)
@RestController
@RequestMapping("/StartupRecord")
public class IpcController {

	@Autowired
	IpcService ipcService;


	@PostMapping("/CreateProductParameters")
	public void saveTableData(@RequestBody String details) {
		String json_data = details;
		ipcService.saveOrUpdateTableData(1, json_data);
	}

	@GetMapping("/getProductParameters/{id}")
	public String getProductParameters(@PathVariable(value = "id") Integer id) {
		return ipcService.getProductParameters(id);
	}

	@PostMapping("/CreateStartupData")
	public void CreateStartupData(@RequestBody Object data) {
		LinkedHashMap<Object, Object> ob = (LinkedHashMap<Object, Object>) data;
		String sectionName = ob.get("section_name").toString();
		Integer ipcId = Integer.valueOf(ob.get("ipc_id").toString());
		Integer sampleId = Integer.valueOf(ob.get("sample_id").toString());
		String json_data = ob.get("json_data").toString();
		StartupRecordData strtRecData = new StartupRecordData(new StartupRecordDataId(ipcId, sampleId, sectionName), json_data);
		ipcService.CreateOrUpdateStartupData(strtRecData);
	}
	
	@GetMapping("/getStartupStatistics/{ipcId}/{sampleId}/{section}")
	public String getStartupStatistics(@PathVariable(value = "ipcId") Integer ipcId,@PathVariable(value = "sampleId") Integer sampleId,
			@PathVariable(value = "section") String section) {
		return ipcService.getStartupStatistics(ipcId,sampleId,section);
	}

}
