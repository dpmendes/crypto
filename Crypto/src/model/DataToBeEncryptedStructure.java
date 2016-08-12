package model;

public class DataToBeEncryptedStructure {
	public String plainData;
	public String header;

	public DataToBeEncryptedStructure(String plainData, String header) {
		this.plainData = plainData;
		this.header = header;
	}
	
	public boolean equals(DataToBeEncryptedStructure encryptionDataStructure) {
		boolean objectsAreEqual = true;
		
		if(!this.plainData.equals(encryptionDataStructure.plainData))
			objectsAreEqual = false;
		if(!this.header.equals(encryptionDataStructure.header))
			objectsAreEqual = false;
		
		return objectsAreEqual;
	}
	
	public String getPlainData() {
		return plainData;
	}

	public void setPlainData(String plainData) {
		this.plainData = plainData;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}
	
	public DataToBeEncryptedStructure() {
		
	}
	
}