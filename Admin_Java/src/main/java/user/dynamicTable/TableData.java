package user.dynamicTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "TableData" , catalog = "admin")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableData {

	@Id
	@Column(name = "table_id")
	private Integer tableId;
	
	@Column(name = "row_No")
	private Integer rowNo;

	@Column(name = "col1_Value")
	private String col1Value;

	@Column(name = "col2_Value")
	private String col2Value;
	
	@Column(name = "col3_Value")
	private String col3Value;
	
	@Column(name = "col4_Value")
	private String col4Value;
	
	@Column(name = "col5_Value")
	private String col5Value;
	
	@Column(name = "col6_Value")
	private String col6Value;
	
	@Column(name = "col7_Value")
	private String col7Value;
	
	@Column(name = "col8_Value")
	private String col8Value;
	
	@Column(name = "col9_Value")
	private String col9Value;
	
	@Column(name = "col10_Value")
	private String col10Value;

}
