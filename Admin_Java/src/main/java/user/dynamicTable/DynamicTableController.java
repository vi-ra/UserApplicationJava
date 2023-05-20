package user.dynamicTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
		for (TableColumnDetails col : colDetails) {
			col.setId(new TableColumnsId(newTableId, i.getAndIncrement()));
		}

		if (colDetails.size() > 0) {
			tableService.createColumnDetails(colDetails);
			tableService.addDefaultRow(new TableData(new TableDataId(colDetails.get(0).getId().getTableId(),1),"","","","","","","","","","" ));
		}
	}


	@PostMapping("/UpdateExistingTable")
	public void updateNewTable(@RequestBody TableColumnDetailsBean details) {
		TableDetails tableDetails = details.getTableDetails();
		List<TableColumnDetails> colDetails = details.getColDetails();
		AtomicInteger i = new  AtomicInteger(1)
		
		
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
			allTables
					.add(new TableColumnDetailsBean(tableId, table, columnDetails, tableService.getTableData(tableId)));
		}
		return allTables;
	}
}
