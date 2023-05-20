package user.dynamicTable;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable

public class TableColumnsId implements Serializable{

	private int tableId;
	private int serialNo;

	public TableColumnsId() {
	}

	public TableColumnsId(int tableId, int serialNo) {
		this.tableId = tableId;
		this.serialNo = serialNo;
	}

	@Column(name = "table_id", nullable = false)
	public int getTableId() {
		return this.tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	@Column(name = "serial_no", nullable = false)
	public int getSerialNo() {
		return this.serialNo;
	}

	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof TableColumnsId))
			return false;
		TableColumnsId castOther = (TableColumnsId) other;

		return (this.getTableId() == castOther.getTableId()) && (this.getSerialNo() == castOther.getSerialNo());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getTableId();
		result = 37 * result + this.getSerialNo();
		return result;
	}

}
