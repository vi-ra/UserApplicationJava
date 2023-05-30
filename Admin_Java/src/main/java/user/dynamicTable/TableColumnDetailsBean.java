package user.dynamicTable;

import java.util.List;

import org.json.simple.JSONArray;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TableColumnDetailsBean {

	private TableDetails tableDetails;
	private List<TableColumnDetails> colDetails;
	private Object[] tableData;

	public TableColumnDetailsBean(Integer tableId, TableDetails tableDetails, List<TableColumnDetails> colDetails,
			String tableData) throws JsonMappingException, JsonProcessingException {
		super();

		this.tableDetails = tableDetails;
		this.colDetails = colDetails;
		
		// Convert the JSON array string to a JSON array
		Object[] jsonArray = new ObjectMapper().readValue(tableData, Object[].class);
		this.tableData = jsonArray;
	}

}
