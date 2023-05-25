package user.dynamicTable;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "column_details", catalog = "admin")
public class TableColumnDetails {
	private TableColumnsId id;
	private String columnName;
	private String dataType;
	private Integer lowerLimit;
	private Integer upperLimit;
	private String uom;
	private boolean activeFlag;

	public TableColumnDetails() {
	}

	public TableColumnDetails(TableColumnsId id, String columnName, String dataType,
			Integer lowerLimit, Integer upperLimit, String uom,boolean activeFlag) {
		this.id = id;
		this.columnName = columnName;
		this.dataType = dataType;
		this.lowerLimit = lowerLimit;
		this.upperLimit = upperLimit;
		this.uom = uom;
		this.activeFlag = activeFlag;
	}

	public TableColumnDetails(TableColumnsId tableColumnsId) {
		// TODO Auto-generated constructor stub
	}

	@EmbeddedId
	@AttributeOverrides({ @AttributeOverride(name = "tableId", column = @Column(name = "table_id", nullable = false)),
			@AttributeOverride(name = "serialNo", column = @Column(name = "serial_no", nullable = false)) })
	public TableColumnsId getId() {
		return this.id;
	}

	public void setId(TableColumnsId id) {
		this.id = id;
	}

	@Column(name = "column_name")
	public String getColumnName() {
		return this.columnName;
	}

	public void setColumnName(String columnName) {
		this.columnName = columnName;
	}

	@Column(name = "data_type")
	public String getDataType() {
		return this.dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	@Column(name = "lower_limit")
	public Integer getLowerLimit() {
		return this.lowerLimit;
	}

	public void setLowerLimit(Integer lowerLimit) {
		this.lowerLimit = lowerLimit;
	}

	@Column(name = "upper_limit")
	public Integer getUpperLimit() {
		return this.upperLimit;
	}

	public void setUpperLimit(Integer upperLimit) {
		this.upperLimit = upperLimit;
	}

	@Column(name = "uom")
	public String getUom() {
		return this.uom;
	}

	public void setUom(String uom) {
		this.uom = uom;
	}
	
	@Column(name = "active_flag")
	public boolean isActiveFlag() {
		return activeFlag;
	}

	public void setActiveFlag(boolean activeFlag) {
		this.activeFlag = activeFlag;
	}
	
	

}
