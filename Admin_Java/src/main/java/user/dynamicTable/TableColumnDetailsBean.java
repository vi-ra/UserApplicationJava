package user.dynamicTable;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TableColumnDetailsBean {

	private TableDetails tableDetails;
	private List<TableColumnDetails> colDetails;
	private List<String[]> tableData;

	public TableColumnDetailsBean(Integer tableId , TableDetails tableDetails,
			List<TableColumnDetails> colDetails, List<String[]> tableData) {
		super();
		this.tableDetails = tableDetails;
		this.colDetails = colDetails;
		this.tableData = tableData;
	}

}
