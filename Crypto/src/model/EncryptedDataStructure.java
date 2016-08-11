package model;

public class EncryptedDataStructure {
	public String encryptedData;
	public String header;
	
	public EncryptedDataStructure(String encryptedData, String header) {
		this.encryptedData = encryptedData;
		this.header = header;
	}
	
	public boolean equals(EncryptedDataStructure encryptionDataStructure) {
		boolean objectsAreEqual = true;
		
		if(!this.encryptedData.equals(encryptionDataStructure.encryptedData))
			objectsAreEqual = false;
		if(!this.header.equals(encryptionDataStructure.header))
			objectsAreEqual = false;
		
		return objectsAreEqual;
	}
}