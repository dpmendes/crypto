package model;

public class EncryptionDataStructure {
	public String encryptedData;
	public String header;
	
	public EncryptionDataStructure(String encryptedData, String header) {
		this.encryptedData = encryptedData;
		this.header = header;
	}
	
	public boolean equals(EncryptionDataStructure encryptionDataStructure) {
		boolean objectsAreEqual = true;
		
		if(!this.encryptedData.equals(encryptionDataStructure.encryptedData))
			objectsAreEqual = false;
		if(!this.header.equals(encryptionDataStructure.header))
			objectsAreEqual = false;
		
		return objectsAreEqual;
	}
}