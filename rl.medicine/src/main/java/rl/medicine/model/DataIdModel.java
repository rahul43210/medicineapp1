package rl.medicine.model;

public class DataIdModel {
	private int id;
	private String data;
	private String dataSubName;
	
	public DataIdModel() {}
	
	public DataIdModel(final int id, final String data) {
		this.id = id;
		this.data = data;
	}

	public DataIdModel(int id, String data, String dataSubName) {
		super();
		this.id = id;
		this.data = data;
		this.dataSubName = dataSubName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDataSubName() {
		return dataSubName;
	}

	public void setDataSubName(String dataSubName) {
		this.dataSubName = dataSubName;
	}
	
	
}
