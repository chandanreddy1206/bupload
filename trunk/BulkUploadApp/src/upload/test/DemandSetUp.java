package upload.test;


import java.io.Serializable;

// TODO: Auto-generated Javadoc
/**
 * The Class DemandSetUp provides the details of Demand.
 * 
 * @author
 * @version 0.1
 */
public class DemandSetUp implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The demand id. */
	private String demandId;

	/** The owner id. */
	private String ownerId;

	/** The owner name. */
	private String ownerName;

	/** The product id. */
	private String productId;

	/** The product name. */
	private String productName;

	/** The quantity. */
	private double quantity;

	/** The country. */
	private String country;

	/** The type. */
	private String type;

	/** The demand type. */
	private String demandType;

	/** The date. */
	private String date;

	/** The insertion date. */
	private String insertionDate;

	/** The plant_ id. */
	private String plant_Id;
	
	/** The plant_ code. */
	private String plant_Code;
	
	/** The plant_ name. */
	private String plant_Name;
	
	/** The so_ amendment_ number. */
	private int so_Amendment_Number;
	
	/** The Sales_ order_ date. */
	private String Sales_Order_Date;
	
	/** The status_of_ sale_ order. */
	private String status_of_Sale_Order;
	
	/** The customer_ code. */
	private String customer_Code;
	
	/** The customer_ name. */
	private String customer_Name;
	
	/** The customer_ ship_ address1. */
	private String customer_Ship_Address1;
	
	/** The customer_ ship_ address2. */
	private String customer_Ship_Address2;
	
	/** The customer_ ship_ country code. */
	private String customer_Ship_CountryCode;
	
	/** The customer_ ship_ city_ code. */
	private String customer_Ship_City_Code;
	
	/** The customer_ zipcode. */
	private String customer_Zipcode;
	
	/** The so_ line_ number. */
	private int so_Line_Number;
	
	/** The part_ number. */
	private String part_Number;
	
	/** The part_ name. */
	private String part_Name;
	
	/** The part_ revision_ number. */
	private int part_Revision_Number;
	
	/** The uom_ code. */
	private String uom_Code;
	
	/** The price. */
	private int price;
	
	/** The currency_ code. */
	private String currency_Code;
	
	/** The required_ date. */
	private String required_Date;
	
	/** The business_ unit_ code. */
	private String business_Unit_Code;
	
	/** The parent id. */
	private String parentID;
	
	/** The mode. */
	private String mode;
	
	private String headParentId;
	
	private String sale_Order_Number;
	
	private String organisationCode;
	
	private String companyCode;

	private String modifiedTime;
	
	private int demandInfoYear;
	
	private String demandInfotimeframe;
	
	private String demandInfoDemandType;
	
	public int getDemandInfoYear() {
		return demandInfoYear;
	}

	public void setDemandInfoYear(int demandInfoYear) {
		this.demandInfoYear = demandInfoYear;
	}

	public String getDemandInfotimeframe() {
		return demandInfotimeframe;
	}

	public void setDemandInfotimeframe(String demandInfotimeframe) {
		this.demandInfotimeframe = demandInfotimeframe;
	}

	public String getDemandInfoDemandType() {
		return demandInfoDemandType;
	}

	public void setDemandInfoDemandType(String demandInfoDemandType) {
		this.demandInfoDemandType = demandInfoDemandType;
	}

	public String getModifiedTime() {
		return modifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getSale_Order_Number() {
		return sale_Order_Number;
	}

	public void setSale_Order_Number(String sale_Order_Number) {
		this.sale_Order_Number = sale_Order_Number;
	}

	
	public String getHeadParentId() {
		return headParentId;
	}

	public void setHeadParentId(String headParentId) {
		this.headParentId = headParentId;
	}

	/**
	 * Gets the plant_ id.
	 *
	 * @return the plant_ id
	 */
	public String getPlant_Id() {
		return plant_Id;
	}

	/**
	 * Sets the plant_ id.
	 *
	 * @param plant_Id the new plant_ id
	 */
	public void setPlant_Id(String plant_Id) {
		this.plant_Id = plant_Id;
	}

	/**
	 * Gets the plant_ code.
	 *
	 * @return the plant_ code
	 */
	public String getPlant_Code() {
		return plant_Code;
	}

	/**
	 * Sets the plant_ code.
	 *
	 * @param plant_Code the new plant_ code
	 */
	public void setPlant_Code(String plant_Code) {
		this.plant_Code = plant_Code;
	}

	/**
	 * Gets the plant_ name.
	 *
	 * @return the plant_ name
	 */
	public String getPlant_Name() {
		return plant_Name;
	}

	/**
	 * Sets the plant_ name.
	 *
	 * @param plant_Name the new plant_ name
	 */
	public void setPlant_Name(String plant_Name) {
		this.plant_Name = plant_Name;
	}

	
	

	public int getSo_Amendment_Number() {
		return so_Amendment_Number;
	}

	public void setSo_Amendment_Number(int so_Amendment_Number) {
		this.so_Amendment_Number = so_Amendment_Number;
	}

	/**
	 * Gets the sales_ order_ date.
	 *
	 * @return the sales_ order_ date
	 */
	public String getSales_Order_Date() {
		return Sales_Order_Date;
	}

	/**
	 * Sets the sales_ order_ date.
	 *
	 * @param sales_Order_Date the new sales_ order_ date
	 */
	public void setSales_Order_Date(String sales_Order_Date) {
		Sales_Order_Date = sales_Order_Date;
	}

	/**
	 * Gets the status_of_ sale_ order.
	 *
	 * @return the status_of_ sale_ order
	 */
	public String getStatus_of_Sale_Order() {
		return status_of_Sale_Order;
	}

	/**
	 * Sets the status_of_ sale_ order.
	 *
	 * @param status_of_Sale_Order the new status_of_ sale_ order
	 */
	public void setStatus_of_Sale_Order(String status_of_Sale_Order) {
		this.status_of_Sale_Order = status_of_Sale_Order;
	}

	/**
	 * Gets the customer_ code.
	 *
	 * @return the customer_ code
	 */
	public String getCustomer_Code() {
		return customer_Code;
	}

	/**
	 * Sets the customer_ code.
	 *
	 * @param customer_Code the new customer_ code
	 */
	public void setCustomer_Code(String customer_Code) {
		this.customer_Code = customer_Code;
	}

	/**
	 * Gets the customer_ name.
	 *
	 * @return the customer_ name
	 */
	public String getCustomer_Name() {
		return customer_Name;
	}

	/**
	 * Sets the customer_ name.
	 *
	 * @param customer_Name the new customer_ name
	 */
	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}

	/**
	 * Gets the customer_ ship_ address1.
	 *
	 * @return the customer_ ship_ address1
	 */
	public String getCustomer_Ship_Address1() {
		return customer_Ship_Address1;
	}

	/**
	 * Sets the customer_ ship_ address1.
	 *
	 * @param customer_Ship_Address1 the new customer_ ship_ address1
	 */
	public void setCustomer_Ship_Address1(String customer_Ship_Address1) {
		this.customer_Ship_Address1 = customer_Ship_Address1;
	}

	/**
	 * Gets the customer_ ship_ address2.
	 *
	 * @return the customer_ ship_ address2
	 */
	public String getCustomer_Ship_Address2() {
		return customer_Ship_Address2;
	}

	

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	/**
	 * Sets the customer_ ship_ address2.
	 *
	 * @param customer_Ship_Address2 the new customer_ ship_ address2
	 */
	public void setCustomer_Ship_Address2(String customer_Ship_Address2) {
		this.customer_Ship_Address2 = customer_Ship_Address2;
	}

	/**
	 * Gets the customer_ ship_ country code.
	 *
	 * @return the customer_ ship_ country code
	 */
	public String getCustomer_Ship_CountryCode() {
		return customer_Ship_CountryCode;
	}

	/**
	 * Sets the customer_ ship_ country code.
	 *
	 * @param customer_Ship_CountryCode the new customer_ ship_ country code
	 */
	public void setCustomer_Ship_CountryCode(String customer_Ship_CountryCode) {
		this.customer_Ship_CountryCode = customer_Ship_CountryCode;
	}

	/**
	 * Gets the customer_ ship_ city_ code.
	 *
	 * @return the customer_ ship_ city_ code
	 */
	public String getCustomer_Ship_City_Code() {
		return customer_Ship_City_Code;
	}

	/**
	 * Sets the customer_ ship_ city_ code.
	 *
	 * @param customer_Ship_City_Code the new customer_ ship_ city_ code
	 */
	public void setCustomer_Ship_City_Code(String customer_Ship_City_Code) {
		this.customer_Ship_City_Code = customer_Ship_City_Code;
	}

	/**
	 * Gets the customer_ zipcode.
	 *
	 * @return the customer_ zipcode
	 */
	public String getCustomer_Zipcode() {
		return customer_Zipcode;
	}

	/**
	 * Sets the customer_ zipcode.
	 *
	 * @param customer_Zipcode the new customer_ zipcode
	 */
	public void setCustomer_Zipcode(String customer_Zipcode) {
		this.customer_Zipcode = customer_Zipcode;
	}

	/**
	 * Gets the so_ line_ number.
	 *
	 * @return the so_ line_ number
	 */
	public int getSo_Line_Number() {
		return so_Line_Number;
	}

	/**
	 * Sets the so_ line_ number.
	 *
	 * @param so_Line_Number the new so_ line_ number
	 */
	public void setSo_Line_Number(int so_Line_Number) {
		this.so_Line_Number = so_Line_Number;
	}

	/**
	 * Gets the part_ number.
	 *
	 * @return the part_ number
	 */
	public String getPart_Number() {
		return part_Number;
	}

	/**
	 * Sets the part_ number.
	 *
	 * @param part_Number the new part_ number
	 */
	public void setPart_Number(String part_Number) {
		this.part_Number = part_Number;
	}

	/**
	 * Gets the part_ name.
	 *
	 * @return the part_ name
	 */
	public String getPart_Name() {
		return part_Name;
	}

	/**
	 * Sets the part_ name.
	 *
	 * @param part_Name the new part_ name
	 */
	public void setPart_Name(String part_Name) {
		this.part_Name = part_Name;
	}

	/**
	 * Gets the part_ revision_ number.
	 *
	 * @return the part_ revision_ number
	 */
	

	/**
	 * Gets the uom_ code.
	 *
	 * @return the uom_ code
	 */
	public String getUom_Code() {
		return uom_Code;
	}

	public int getPart_Revision_Number() {
		return part_Revision_Number;
	}

	public void setPart_Revision_Number(int part_Revision_Number) {
		this.part_Revision_Number = part_Revision_Number;
	}

	/**
	 * Sets the uom_ code.
	 *
	 * @param uom_Code the new uom_ code
	 */
	public void setUom_Code(String uom_Code) {
		this.uom_Code = uom_Code;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */

	/**
	 * Gets the currency_ code.
	 *
	 * @return the currency_ code
	 */
	public String getCurrency_Code() {
		return currency_Code;
	}

	/**
	 * Sets the currency_ code.
	 *
	 * @param currency_Code the new currency_ code
	 */
	public void setCurrency_Code(String currency_Code) {
		this.currency_Code = currency_Code;
	}

	/**
	 * Gets the required_ date.
	 *
	 * @return the required_ date
	 */
	public String getRequired_Date() {
		return required_Date;
	}

	/**
	 * Sets the required_ date.
	 *
	 * @param required_Date the new required_ date
	 */
	public void setRequired_Date(String required_Date) {
		this.required_Date = required_Date;
	}

	/**
	 * Gets the business_ unit_ code.
	 *
	 * @return the business_ unit_ code
	 */
	public String getBusiness_Unit_Code() {
		return business_Unit_Code;
	}

	/**
	 * Sets the business_ unit_ code.
	 *
	 * @param business_Unit_Code the new business_ unit_ code
	 */
	public void setBusiness_Unit_Code(String business_Unit_Code) {
		this.business_Unit_Code = business_Unit_Code;
	}

	/**
	 * Gets the parent id.
	 *
	 * @return the parent id
	 */
	public String getParentID() {
		return parentID;
	}

	/**
	 * Sets the parent id.
	 *
	 * @param parentID the new parent id
	 */
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	/**
	 * Gets the mode.
	 *
	 * @return the mode
	 */
	public String getMode() {
		return mode;
	}

	/**
	 * Sets the mode.
	 *
	 * @param mode the new mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * Gets the owner name.
	 * 
	 * @return the owner name
	 */
	public String getOwnerName() {
		return ownerName;
	}

	/**
	 * Sets the owner name.
	 * 
	 * @param ownerName
	 *            the new owner name
	 */
	public void setOwnerName(final String ownerName) {
		this.ownerName = ownerName;
	}

	/**
	 * Gets the demand type.
	 * 
	 * @return the demand type
	 */
	public String getDemandType() {
		return demandType;
	}

	/**
	 * Gets the demand id.
	 * 
	 * @return the demand id
	 */
	public String getDemandId() {
		return demandId;
	}

	/**
	 * Sets the demand id.
	 * 
	 * @param demandId
	 *            the new demand id
	 */
	public void setDemandId(final String demandId) {
		this.demandId = demandId;
	}

	/**
	 * Gets the date.
	 * 
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 * 
	 * @param date
	 *            the new date
	 */
	public void setDate(final String date) {
		this.date = date;
	}

	/**
	 * Sets the demand type.
	 * 
	 * @param demandType
	 *            the new demand type
	 */
	public void setDemandType(final String demandType) {
		this.demandType = demandType;
	}

	/**
	 * Gets the owner id.
	 * 
	 * @return the owner id
	 */
	public String getOwnerId() {
		return ownerId;
	}

	/**
	 * Sets the owner id.
	 * 
	 * @param ownerId
	 *            the new owner id
	 */
	public void setOwnerId(final String ownerId) {
		this.ownerId = ownerId;
	}

	/**
	 * Gets the product id.
	 * 
	 * @return the product id
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * Sets the product id.
	 * 
	 * @param productId
	 *            the new product id
	 */
	public void setProductId(final String productId) {
		this.productId = productId;
	}

	
	
	/**
	 * Gets the country.
	 * 
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	/**
	 * Sets the country.
	 * 
	 * @param country
	 *            the new country
	 */
	public void setCountry(final String country) {
		this.country = country;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the new type
	 */
	public void setType(final String type) {
		this.type = type;
	}

	/**
	 * Gets the product name.
	 * 
	 * @return the product name
	 */
	public String getProductName() {
		return productName;
	}

	/**
	 * Sets the product name.
	 * 
	 * @param productName
	 *            the new product name
	 */
	public void setProductName(final String productName) {
		this.productName = productName;
	}

	/**
	 * Gets the insertion date.
	 * 
	 * @return the insertion date
	 */
	public String getInsertionDate() {
		return insertionDate;
	}

	/**
	 * Sets the insertion date.
	 * 
	 * @param insertionDate
	 *            the new insertion date
	 */
	public void setInsertionDate(final String insertionDate) {
		this.insertionDate = insertionDate;
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
	
}
