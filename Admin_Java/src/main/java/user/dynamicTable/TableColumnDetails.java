package user.dynamicTable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "column_details" , catalog = "admin")
public class TableColumnDetails {
	
	@Id
	@Column(name = "table_id")
	private Integer tableId;
	
	@Column(name = "serial_no")
	private Integer serialNo;
	
	@Column(name = "column_name")
	private String columnName;

	@Column(name = "data_type")
	private String dataType;
	
	@Column(name = "lower_limit")
	private Integer lowerLimit;
	
	@Column(name = "upper_limit")
	private Integer upperLimit;
	
	@Column(name = "uom")
	private String uom;

}
