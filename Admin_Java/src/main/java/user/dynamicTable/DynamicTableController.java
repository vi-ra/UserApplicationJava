package user.dynamicTable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

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
@RequestMapping("/table")
public class DynamicTableController {

	@Autowired
	DynamicTableService tableService;

	@PostMapping("/CreateNewTable")
	public void createNewTable(@RequestBody TableColumnDetailsBean details) {
		TableDetails tableDetails = details.getTableDetails();
		List<TableColumnDetails> colDetails = details.getColDetails();
		int newTableId = tableService.getMaxTableNo() ==null ? 1:tableService.getMaxTableNo()+1;
		if (tableDetails != null) {
			tableDetails.setTableId(newTableId);
			tableService.createTableDetails(tableDetails);
		}
		AtomicInteger i = new AtomicInteger(1);
		for (TableColumnDetails col : colDetails) {
			col.setTableId(newTableId);
			col.setSerialNo(i.getAndIncrement());
		}
		
		if(colDetails.size()>0) {
			tableService.createColumnDetails(colDetails);
		}
	}

	@PostMapping("/CreateTableDetails")
	public void createTableDetails(@RequestBody TableDetails details) {
		tableService.createTableDetails(details);
	}

	@GetMapping("/GetTableDetailsById/{id}")
	public TableDetails getTableDetailsById(@PathVariable("id") Integer id) {
		return tableService.getTableDetailsById(id);

	}

	@PostMapping("/CreateColumnDetails")
	public void createColumnDetails(@RequestBody List<TableColumnDetails> details) {
		tableService.createColumnDetails(details);

	}

	@GetMapping("/GetAllColumnDetails/{id}")
	public List<TableColumnDetails> getAllColumnDetails(@PathVariable("id") Integer id) {
		return tableService.getColumnDetails(id);

	}

	@GetMapping("/getTableAndColumnDetailsById/{id}")
	public TableColumnDetailsBean getTableAndColumnDetailsById(@PathVariable("id") Integer id) {
		return null;
		/*
		 * TableDetails tableDetails = tableService.getTableDetailsById(id);
		 * List<TableColumnDetails> columnDetails = tableService.getColumnDetails(id);
		 * List<TableData> data = tableService.getTableData(id); TableData tableData =
		 * data.isPresent() ? data.get() : new TableData(); TableColumnDetailsBean
		 * tableColumnDetailsBean = new TableColumnDetailsBean(tableDetails,
		 * columnDetails, tableData); return tableColumnDetailsBean;
		 */}

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
