package ks46team02.company.dto;

public class FarmProductCategory {
    private String productCategoryCode;
    private String productName;
    private String adminId;
    private String productCategoryRegDate;

    public String getProductCategoryCode() {
        return productCategoryCode;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getProductCategoryRegDate() {
        return productCategoryRegDate;
    }

    public void setProductCategoryRegDate(String productCategoryRegDate) {
        this.productCategoryRegDate = productCategoryRegDate;
    }

    @Override
    public String toString() {
        return "FarmProductCategory{" +
                "productCategoryCode='" + productCategoryCode + '\'' +
                ", productName='" + productName + '\'' +
                ", adminId='" + adminId + '\'' +
                ", productCategoryRegDate='" + productCategoryRegDate + '\'' +
                '}';
    }
}
