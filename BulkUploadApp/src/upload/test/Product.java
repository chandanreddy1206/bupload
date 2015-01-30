package upload.test;

import java.io.Serializable;

/**
 * The Class Product provides the information of Product Details.
 * 
 * @author
 * @version 0.1
 */
public class Product implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The Plant code. */
	private String PlantCode;
	
	/** The Plant name. */
	private String PlantName;
	
	/** The Level. */
	private int Level;
	
	/** The Product code. */
	private String ProductCode;
	
	/** The Product name. */
	private String ProductName;
	
	/** The Product revision number. */
	private int ProductRevisionNumber;
	
	/** The Part number. */
	private String PartNumber;
	
	/** The Part name. */
	private String PartName;
	
	/** The Parent id. */
	private String ParentID;
	
	/** The Part revision number. */
	private int PartRevisionNumber;
	
	/** The BOM status. */
	private String BOMStatus;
	
	/** The Comments. */
	private String Comments;
	
	/** The UOM code. */
	private String UOMCode;
	
	/** The Part usage quantity. */
	private int PartUsageQuantity;
	
	/** The Supply type. */
	private String SupplyType;
	
	/** The Reference designators. */
	private String ReferenceDesignators;
	
	/** The Product image. */
	private String ProductImage;
	
	/** The Price. */
	private int Price;
	
	/** The Currency code. */
	private String CurrencyCode;
	
	/** The Lead time. */
	private int LeadTime;
	
	/** The UOM lead time. */
	private String UOMLeadTime;
	
	/** The ProductFamily1. */
	private String ProductFamily1;
	
	/** The ProductFamily2. */
	private String ProductFamily2;
	
	/** TheProductFamily3. */
	private String ProductFamily3;
	
	/** The ModifiedTime. */
	private String ModifiedTime;
	
	private String organisationCode;
	
	private String companyCode;
	
	private String organisationName;
	
	private String companyName;
	
	private String family;
	
	private String model;
	
	private String supplier;

	/** The key. */
	private String key;

	/** The no of components. */
	private int noOfComponents;

	/** The leaf node. */
	private boolean leafNode;

	
	private String headParentID;
	


	/**
	 * Gets the plant code.
	 *
	 * @return the plant code
	 */
	public String getPlantCode() {
		return PlantCode;
	}

	/**
	 * Sets the plant code.
	 *
	 * @param plantCode the new plant code
	 */
	public void setPlantCode(String plantCode) {
		PlantCode = plantCode;
	}

	/**
	 * Gets the plant name.
	 *
	 * @return the plant name
	 */
	public String getPlantName() {
		return PlantName;
	}

	/**
	 * Sets the plant name.
	 *
	 * @param plantName the new plant name
	 */
	public void setPlantName(String plantName) {
		PlantName = plantName;
	}

	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return Level;
	}

	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(int level) {
		Level = level;
	}

	/**
	 * Gets the product code.
	 *
	 * @return the product code
	 */
	public String getProductCode() {
		return ProductCode;
	}

	/**
	 * Sets the product code.
	 *
	 * @param productCode the new product code
	 */
	public void setProductCode(String productCode) {
		ProductCode = productCode;
	}

	/**
	 * Gets the product name.
	 *
	 * @return the product name
	 */
	public String getProductName() {
		return ProductName;
	}

	/**
	 * Sets the product name.
	 *
	 * @param productName the new product name
	 */
	public void setProductName(String productName) {
		ProductName = productName;
	}

	/**
	 * Gets the product revision number.
	 *
	 * @return the product revision number
	 */
	public int getProductRevisionNumber() {
		return ProductRevisionNumber;
	}

	/**
	 * Sets the product revision number.
	 *
	 * @param productRevisionNumber the new product revision number
	 */
	public void setProductRevisionNumber(int productRevisionNumber) {
		ProductRevisionNumber = productRevisionNumber;
	}

	/**
	 * Gets the part number.
	 *
	 * @return the part number
	 */
	public String getPartNumber() {
		return PartNumber;
	}

	/**
	 * Sets the part number.
	 *
	 * @param partNumber the new part number
	 */
	public void setPartNumber(String partNumber) {
		PartNumber = partNumber;
	}

	/**
	 * Gets the part name.
	 *
	 * @return the part name
	 */
	public String getPartName() {
		return PartName;
	}

	/**
	 * Sets the part name.
	 *
	 * @param partName the new part name
	 */
	public void setPartName(String partName) {
		PartName = partName;
	}

	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	public String getParentID() {
		return ParentID;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentID the new parent id
	 */
	public void setParentID(String parentID) {
		ParentID = parentID;
	}

	/**
	 * Gets the part revision number.
	 *
	 * @return the part revision number
	 */
	public int getPartRevisionNumber() {
		return PartRevisionNumber;
	}

	/**
	 * Sets the part revision number.
	 *
	 * @param partRevisionNumber the new part revision number
	 */
	public void setPartRevisionNumber(int partRevisionNumber) {
		PartRevisionNumber = partRevisionNumber;
	}

	/**
	 * Gets the BOM status.
	 *
	 * @return the BOM status
	 */
	public String getBOMStatus() {
		return BOMStatus;
	}

	/**
	 * Sets the BOM status.
	 *
	 * @param bOMStatus the new BOM status
	 */
	public void setBOMStatus(String bOMStatus) {
		BOMStatus = bOMStatus;
	}

	/**
	 * Gets the comments.
	 *
	 * @return the comments
	 */
	public String getComments() {
		return Comments;
	}

	/**
	 * Sets the comments.
	 *
	 * @param comments the new comments
	 */
	public void setComments(String comments) {
		Comments = comments;
	}

	/**
	 * Gets the UOM code.
	 *
	 * @return the UOM code
	 */
	public String getUOMCode() {
		return UOMCode;
	}

	/**
	 * Sets the UOM code.
	 *
	 * @param uOMCode the new UOM code
	 */
	public void setUOMCode(String uOMCode) {
		UOMCode = uOMCode;
	}

	/**
	 * Gets the part usage quantity.
	 *
	 * @return the part usage quantity
	 */
	public int getPartUsageQuantity() {
		return PartUsageQuantity;
	}

	/**
	 * Sets the part usage quantity.
	 *
	 * @param partUsageQuantity the new part usage quantity
	 */
	public void setPartUsageQuantity(int partUsageQuantity) {
		PartUsageQuantity = partUsageQuantity;
	}

	/**
	 * Gets the supply type.
	 *
	 * @return the supply type
	 */
	public String getSupplyType() {
		return SupplyType;
	}

	/**
	 * Sets the supply type.
	 *
	 * @param supplyType the new supply type
	 */
	public void setSupplyType(String supplyType) {
		SupplyType = supplyType;
	}

	/**
	 * Gets the reference designators.
	 *
	 * @return the reference designators
	 */
	public String getReferenceDesignators() {
		return ReferenceDesignators;
	}

	/**
	 * Sets the reference designators.
	 *
	 * @param referenceDesignators the new reference designators
	 */
	public void setReferenceDesignators(String referenceDesignators) {
		ReferenceDesignators = referenceDesignators;
	}

	/**
	 * Gets the product image.
	 *
	 * @return the product image
	 */
	public String getProductImage() {
		return ProductImage;
	}

	/**
	 * Sets the product image.
	 *
	 * @param productImage the new product image
	 */
	public void setProductImage(String productImage) {
		ProductImage = productImage;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public int getPrice() {
		return Price;
	}


	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(int price) {
		Price = price;
	}


	/**
	 * Gets the currency code.
	 *
	 * @return the currency code
	 */
	public String getCurrencyCode() {
		return CurrencyCode;
	}

	/**
	 * Sets the currency code.
	 *
	 * @param currencyCode the new currency code
	 */
	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}

	/**
	 * Gets the lead time.
	 *
	 * @return the lead time
	 */
	public int getLeadTime() {
		return LeadTime;
	}

	/**
	 * Sets the lead time.
	 *
	 * @param leadTime the new lead time
	 */
	public void setLeadTime(int leadTime) {
		LeadTime = leadTime;
	}

	/**
	 * Gets the UOM lead time.
	 *
	 * @return the UOM lead time
	 */
	public String getUOMLeadTime() {
		return UOMLeadTime;
	}

	/**
	 * Sets the UOM lead time.
	 *
	 * @param uOMLeadTime the new UOM lead time
	 */
	public void setUOMLeadTime(String uOMLeadTime) {
		UOMLeadTime = uOMLeadTime;
	}

	/**
	 * Gets the key.
	 *
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the key.
	 *
	 * @param key the new key
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * Gets the no of components.
	 *
	 * @return the no of components
	 */
	public int getNoOfComponents() {
		return noOfComponents;
	}

	/**
	 * Sets the no of components.
	 *
	 * @param noOfComponents the new no of components
	 */
	public void setNoOfComponents(int noOfComponents) {
		this.noOfComponents = noOfComponents;
	}

	/**
	 * Checks if is leaf node.
	 *
	 * @return true, if is leaf node
	 */
	public boolean isLeafNode() {
		return leafNode;
	}

	/**
	 * Sets the leaf node.
	 *
	 * @param leafNode the new leaf node
	 */
	public void setLeafNode(boolean leafNode) {
		this.leafNode = leafNode;
	}

	public String getProductFamily1() {
		return ProductFamily1;
	}

	public void setProductFamily1(String productFamily1) {
		ProductFamily1 = productFamily1;
	}

	public String getProductFamily2() {
		return ProductFamily2;
	}

	public void setProductFamily2(String productFamily2) {
		ProductFamily2 = productFamily2;
	}

	public String getProductFamily3() {
		return ProductFamily3;
	}

	public void setProductFamily3(String productFamily3) {
		ProductFamily3 = productFamily3;
	}

	public String getModifiedTime() {
		return ModifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		ModifiedTime = modifiedTime;
	}

	/**
	 * Gets the serialversionuid.
	 *
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public String getOrganisationCode() {
		return organisationCode;
	}

	public void setOrganisationCode(String organisationCode) {
		this.organisationCode = organisationCode;
	}

	public String getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(String companyCode) {
		this.companyCode = companyCode;
	}

	
	public String getHeadParentID() {
		return headParentID;
	}

	public void setHeadParentID(String headParentID) {
		this.headParentID = headParentID;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	
		StringBuilder prod=new StringBuilder();
		prod.append("Product [ProductCode=").append(ProductCode).append(", ProductName=").append(ProductName)
		.append(", ParentID=").append( ParentID).append(", LeadTime=").append( LeadTime).append("]");
		return prod.toString();
	

	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	
	public String getFamily() {
		return family;
	}

	public void setFamily(String family) {
		this.family = family;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}
}
