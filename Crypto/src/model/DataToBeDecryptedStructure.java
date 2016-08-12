package model;

public class DataToBeDecryptedStructure {
	public String encryptedData;
	public String header;

	public DataToBeDecryptedStructure(String encryptedData, String header) {
		this.encryptedData = encryptedData;
		this.header = header;
	}
	
	public boolean equals(DataToBeDecryptedStructure dtbds) {
		boolean objectsAreEqual = true;
		
		if(!this.encryptedData.equals(dtbds.encryptedData))
			objectsAreEqual = false;
		if(!this.header.equals(dtbds.header))
			objectsAreEqual = false;
		
		return objectsAreEqual;
	}
	
	public String getEncryptedData() {
		return encryptedData;
	}

	public void setEncryptedData(String encryptedData) {
		this.encryptedData = encryptedData;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	public DataToBeDecryptedStructure() {
		
	}
	
}