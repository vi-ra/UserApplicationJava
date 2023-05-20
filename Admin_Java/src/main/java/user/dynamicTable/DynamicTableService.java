package user.dynamicTable;

import java.util.List;

public interface DynamicTableService {
	
	public void createTableDetails(TableDetails details);
	public TableDetails getTableDetailsById(Integer id);
	public List<TableDetails> getAllTables();
	public void createColumnDetails(List<TableColumnDetails> details);
	public List<TableColumnDetails> getColumnDetails(Integer id);
	public List<String[]> getTableData(Integer tableId);
	public Integer getMaxTableNo();
	public void addDefaultRow(TableData tableData);



}
