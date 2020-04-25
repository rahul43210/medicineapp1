package rl.medicine.model;

import java.util.Date;

public class MedicineDetailModel {

	private int medicineId;
	private String medicineName;
	private Date medicineExpiryDate;
	private String medicineBox;
	private int leftQuantity;
	private int strip;
	private int medicineNameId;
	private int medicineBoxId;
	
	public MedicineDetailModel() {
		super();
	}

	public MedicineDetailModel(int medicineId, String medicineName, Date medicineExpiryDate, String medicineBox,
			int leftQuantity, int strip, int medicineNameId, int medicineBoxId) {
		super();
		this.medicineId = medicineId;
		this.medicineName = medicineName;
		this.medicineExpiryDate = medicineExpiryDate;
		this.medicineBox = medicineBox;
		this.leftQuantity = leftQuantity;
		this.strip = strip;
		this.medicineNameId = medicineNameId;
		this.medicineBoxId = medicineBoxId;
	}

	public int getMedicineId() {
		return medicineId;
	}

	public void setMedicineId(int medicineId) {
		this.medicineId = medicineId;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public Date getMedicineExpiryDate() {
		return medicineExpiryDate;
	}

	public void setMedicineExpiryDate(Date medicineExpiryDate) {
		this.medicineExpiryDate = medicineExpiryDate;
	}

	public String getMedicineBox() {
		return medicineBox;
	}

	public void setMedicineBox(String medicineBox) {
		this.medicineBox = medicineBox;
	}

	public int getLeftQuantity() {
		return leftQuantity;
	}

	public void setLeftQuantity(int leftQuantity) {
		this.leftQuantity = leftQuantity;
	}

	public int getStrip() {
		return strip;
	}

	public void setStrip(int strip) {
		this.strip = strip;
	}

	public int getMedicineNameId() {
		return medicineNameId;
	}

	public void setMedicineNameId(int medicineNameId) {
		this.medicineNameId = medicineNameId;
	}

	public int getMedicineBoxId() {
		return medicineBoxId;
	}

	public void setMedicineBoxId(int medicineBoxId) {
		this.medicineBoxId = medicineBoxId;
	}

	@Override
	public String toString() {
	return "Medicine Id : "+medicineId+" Medicine Name : "+medicineName+" Medicine Expiry Date "+medicineExpiryDate+" Medicine Box "+medicineBox
	 +" Left Quantity : "+leftQuantity+" Strip : "+strip+" Medicine Name Id "+medicineNameId+" Medicine Box Id "+ medicineBoxId;
	}
	
}
