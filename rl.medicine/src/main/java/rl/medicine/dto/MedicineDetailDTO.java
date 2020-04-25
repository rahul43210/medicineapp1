package rl.medicine.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class MedicineDetailDTO {

	private int medicineId;
	private String medicineName;
	private Date medicineExpiryDate;
	private String medicineBox = "";
	private int leftQuantity;
	private int strip;
	private int medicineNameId;
	private int medicineBoxId;
	private int totalQuantity;
	private Date maxExpiryDate;
	private String multipleMedicineName;
	private String medicineChemicalName;
	private Set<String> medicineNames;
	private List<MedicineDetailDTO> listMedicineDetailDTO;
	private MedicineDetailDTO medicineDetailDTO;

	public MedicineDetailDTO() {
		super();
	}

	public MedicineDetailDTO(int medicineId, String medicineName, Date medicineExpiryDate, String medicineBox,
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

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public Date getMaxExpiryDate() {
		return maxExpiryDate;
	}

	public void setMaxExpiryDate(Date maxExpiryDate) {
		this.maxExpiryDate = maxExpiryDate;
	}

	public String getMultipleMedicineName() {
		return multipleMedicineName;
	}

	public void setMultipleMedicineName(String multipleMedicineName) {
		this.multipleMedicineName = multipleMedicineName;
	}

	public String getMedicineChemicalName() {
		return medicineChemicalName;
	}

	public void setMedicineChemicalName(String medicineChemicalName) {
		this.medicineChemicalName = medicineChemicalName;
	}

	public Set<String> getMedicineNames() {
		return medicineNames;
	}

	public void setMedicineNames(Set<String> medicineNames) {
		this.medicineNames = medicineNames;
	}

	public List<MedicineDetailDTO> getListMedicineDetailDTO() {
		return listMedicineDetailDTO;
	}

	public void setListMedicineDetailDTO(List<MedicineDetailDTO> listMedicineDetailDTO) {
		this.listMedicineDetailDTO = listMedicineDetailDTO;
	}

	public MedicineDetailDTO getMedicineDetailDTO() {
		return medicineDetailDTO;
	}

	public void setMedicineDetailDTO(MedicineDetailDTO medicineDetailDTO) {
		this.medicineDetailDTO = medicineDetailDTO;
	}

	@Override
	public String toString() {
		return " medicineId : "+medicineId+
				" medicineName : "+medicineName+
				" medicineExpiryDate : "+medicineExpiryDate+
				" medicineBox : "+medicineBox+
				" leftQuantity : "+leftQuantity+
				" strip : "+strip+
				" medicineNameId : "+medicineNameId+
				" medicineBoxId: "+medicineBoxId+
				" medicineChemical Name : "+medicineChemicalName+
				" medicine names : "+medicineNames+
				" Total Quantity : "+totalQuantity;	
	}


}
