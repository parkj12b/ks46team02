package ks46team02.common.dto;

public class FileRelation {
	private String fileAssociateKey;
	private String tableName;
	private String tablePrimaryKey;
	public String getFileAssociateKey() {
		return fileAssociateKey;
	}
	public void setFileAssociateKey(String fileAssociateKey) {
		this.fileAssociateKey = fileAssociateKey;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getTablePrimaryKey() {
		return tablePrimaryKey;
	}
	public void setTablePrimaryKey(String tablePrimaryKey) {
		this.tablePrimaryKey = tablePrimaryKey;
	}
	@Override
	public String toString() {
		return "FileRelation [fileAssociateKey=" + fileAssociateKey + ", tableName=" + tableName + ", tablePrimaryKey="
				+ tablePrimaryKey + "]";
	}
	
	
}
