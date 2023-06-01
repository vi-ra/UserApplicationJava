package user.dynamicTable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

@CrossOrigin(origins = { "*" }, maxAge = 4800)
@RestController
@RequestMapping("/table")
public class DynamicTableController {

	@Autowired
	DynamicTableService tableService;

	@PostMapping("/CreateNewTable")
	public void createNewTable(@RequestBody TableColumnDetailsBean details) {
		TableDetails tableDetails = details.getTableDetails();
		List<TableColumnDetails> colDetails = details.getColDetails();
		int newTableId = tableService.getMaxTableNo() + 1;
		if (tableDetails != null) {
			tableDetails.setTableId(newTableId);
			tableService.SaveTableDetails(tableDetails);
		}
		AtomicInteger i = new AtomicInteger(1);

		JSONObject obj = new JSONObject();
		JSONArray jsonArray = new JSONArray();

		for (TableColumnDetails col : colDetails) {
			obj.put(col.getColumnName(), "");
			col.setId(new TableColumnsId(newTableId, i.getAndIncrement()));
		}
		
		jsonArray.add(obj);


		int noOfCols = colDetails.size();
		if (noOfCols > 0) {
			tableService.createColumnDetails(colDetails);
			System.out.print(obj);
			TableDataId id = new TableDataId(newTableId);
			tableService.addDefaultRow(new TableData(id, jsonArray.toJSONString()));
		}
	}

	@PostMapping("/UpdateExistingTable")
	public void updateNewTable(@RequestBody TableColumnDetailsBean details) {
		TableDetails tableDetails = details.getTableDetails();
		List<TableColumnDetails> colDetails = details.getColDetails();

		tableService.SaveTableDetails(tableDetails);
		tableService.createColumnDetails(colDetails);
	}

	@GetMapping("/getAllTableData")
	public List<TableColumnDetailsBean> getTableAndColumnDetailsById() {
		List<TableDetails> tableDetails = tableService.getAllTables();
		List<TableColumnDetailsBean> allTables = new ArrayList<>();
		for (TableDetails table : tableDetails) {
			Integer tableId = table.getTableId();
			List<TableColumnDetails> columnDetails = tableService.getColumnDetails(tableId);
			try {
				allTables.add(new TableColumnDetailsBean(tableId, table, columnDetails, tableService.getTableData(tableId)));
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return allTables;
	}

	@PostMapping("/saveRowData")
	public void createNewTable(@RequestBody Object details) {
		LinkedHashMap<Object, Object> ob = (LinkedHashMap<Object, Object>) details;
		Object object = ob.get("json_data");
		System.out.println(object);
//		TableData data = new TableData(new TableDataId(1, 1), details);
//		tableService.addDefaultRow(details);
	}
	
	@PostMapping("/saveTableData")
	public void saveTableData(@RequestBody Object details) {
		LinkedHashMap<Object, Object> ob = (LinkedHashMap<Object, Object>) details;
		String json_data = ob.get("json_data").toString().replace(",\"editflag\":true", "");
		int id = Integer.parseInt(ob.get("table_id").toString());
		tableService.saveOrUpdateTableData(id, json_data);
	}

}
