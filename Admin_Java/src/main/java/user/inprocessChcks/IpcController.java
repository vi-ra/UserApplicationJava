package user.inprocessChcks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = { "*" }, maxAge = 4800)
@RestController
@RequestMapping("/Parameters")
public class IpcController {
	
	@Autowired
	IpcService ipcService;
	
	@GetMapping("/getProductParameters")
	public String getProductParameters() {
		return ipcService.getProductParameters(1);
	}
	
	@PostMapping("/CreateProductParameters")
	public void saveTableData(@RequestBody String details) {
		String json_data = details.replace(",\"editFlg\":true", ",\"editFlg\":false");
		ipcService.saveOrUpdateTableData(1, json_data);
	}

	
}
