package user.inprocessChcks;

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
@Table(name = "ipc__mr_product_parameters" , catalog = "admin")
public class MrProductParameters {
	
	@Id
	@Column(name = "mr_id")
	private Integer mrId;
	
	private String jsonData;
	
	
	

	public MrProductParameters(Integer mrId, String jsonData) {
		super();
		this.mrId = mrId;
		this.jsonData = jsonData;
	}
	

	public MrProductParameters() {
		super();
	}


	public Integer getMrId() {
		return mrId;
	}

	public void setMrId(Integer mrId) {
		this.mrId = mrId;
	}

	@Column(name = "json_Data")
	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}
	
	
	
	

}
