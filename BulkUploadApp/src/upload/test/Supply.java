package upload.test;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * The Class Supply provides the details of supplier.
 * 
 * @author
 * @version 0.1
 */
public class Supply implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The supply id. *//*
	private String supplyID;

	*//** The supplier id. *//*
	private String supplierID;

	*//** The supplier name. *//*
	private String supplierName;

	*//** The part id. *//*
	private String partID;

	*//** The part name. *//*
	private String partName;

	*//** The date. *//*
	private String date;

	*//** The quantity. *//*
	private long quantity;

	*//** The supply type. *//*
	private String supplyType;

	*//** The insert date. *//*
	private String insertDate;

	*//** The country. *//*
	private String country;*/
	
	private String OrganisationCode;
	private String OrganisationName;
	private String PlantCode;
	private String PlantName;
	private String SupplyType;
	private String SalesOrderNumber;
	private int SOAmendmentNumber;
	private String SalesOrderDate;
	private String StatusofSaleOrder;
	private int SOLineNumber;
	private String PurchaseOrderNumber;
	private int POAmendmentNumber;
	private String PurchaseOrderDate;
	private String StatusofPurchaseOrder;
	private String TradingPartnerCode;
	private String TradingPartnerName;
	private String TypeofTradingPartner;
	private String ShifFromAddressLine1;
	private String ShipFromAddressLine2;
	private String ShipFromCountryCode;
	private String ShipFromCityCode;
	private String TPZipcode;
	private String POLineNumber;
	private String PartNumber;
	private String PartName;
	private int PartRevisionNumber;
	private double Quantity;
	private String UOMCode;
	private int Price;
	private String CurrencyCode;
	private String POScheduleDate;
	private String SupplyDate;
	private String BusinessUnitCode;
	private String PlantID;
	private String HeadParentId;
	private String ParentId;
	private String InsertDate;
	private String Mode;
	private String CompanyCode;
	private String ModifiedTime;
	
	
	//private String UOMCode;
	//private String POScheduleDate;
	private String modeOfShipment;
	public String getModeOfShipment() {
		return modeOfShipment;
	}

	public void setModeOfShipment(String modeOfShipment) {
		this.modeOfShipment = modeOfShipment;
	}

	public String getShipDate() {
		return shipDate;
	}

	public void setShipDate(String shipDate) {
		this.shipDate = shipDate;
	}

	public int getLeadTime() {
		return leadTime;
	}

	public void setLeadTime(int leadTime) {
		this.leadTime = leadTime;
	}

	public String getUOMForLeadtime() {
		return UOMForLeadtime;
	}

	public void setUOMForLeadtime(String uOMForLeadtime) {
		UOMForLeadtime = uOMForLeadtime;
	}

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getShipfromAddressLine1() {
		return shipfromAddressLine1;
	}

	public void setShipfromAddressLine1(String shipfromAddressLine1) {
		this.shipfromAddressLine1 = shipfromAddressLine1;
	}

	public String getShipfromAddressLine2() {
		return shipfromAddressLine2;
	}

	public void setShipfromAddressLine2(String shipfromAddressLine2) {
		this.shipfromAddressLine2 = shipfromAddressLine2;
	}

	public String getShipfromCountryCode() {
		return shipfromCountryCode;
	}

	public void setShipfromCountryCode(String shipfromCountryCode) {
		this.shipfromCountryCode = shipfromCountryCode;
	}

	public String getShipfromCityCode() {
		return shipfromCityCode;
	}

	public void setShipfromCityCode(String shipfromCityCode) {
		this.shipfromCityCode = shipfromCityCode;
	}

	public int getpOLineNumber() {
		return pOLineNumber;
	}

	public void setpOLineNumber(int pOLineNumber) {
		this.pOLineNumber = pOLineNumber;
	}

	private String shipDate;
	private int leadTime;
	private String UOMForLeadtime;
	private String parentID;
	private String companyName;
	
	private String shipfromAddressLine1;
	private String shipfromAddressLine2;
	private String shipfromCountryCode;
	private String shipfromCityCode;	
	private int pOLineNumber;
	

	public String getOrganisationCode() {
		return OrganisationCode;
	}

	public void setOrganisationCode(String organisationCode) {
		OrganisationCode = organisationCode;
	}

	public String getOrganisationName() {
		return OrganisationName;
	}

	public void setOrganisationName(String organisationName) {
		OrganisationName = organisationName;
	}

	public String getPlantCode() {
		return PlantCode;
	}

	public void setPlantCode(String plantCode) {
		PlantCode = plantCode;
	}

	public String getPlantName() {
		return PlantName;
	}

	public void setPlantName(String plantName) {
		PlantName = plantName;
	}

	public String getSalesOrderNumber() {
		return SalesOrderNumber;
	}

	public void setSalesOrderNumber(String salesOrderNumber) {
		SalesOrderNumber = salesOrderNumber;
	}

	public int getSOAmendmentNumber() {
		return SOAmendmentNumber;
	}

	public void setSOAmendmentNumber(int sOAmendmentNumber) {
		SOAmendmentNumber = sOAmendmentNumber;
	}

	public String getSalesOrderDate() {
		return SalesOrderDate;
	}

	public void setSalesOrderDate(String salesOrderDate) {
		SalesOrderDate = salesOrderDate;
	}

	public String getStatusofSaleOrder() {
		return StatusofSaleOrder;
	}

	public void setStatusofSaleOrder(String statusofSaleOrder) {
		StatusofSaleOrder = statusofSaleOrder;
	}

	public int getSOLineNumber() {
		return SOLineNumber;
	}

	public void setSOLineNumber(int sOLineNumber) {
		SOLineNumber = sOLineNumber;
	}

	public String getPurchaseOrderNumber() {
		return PurchaseOrderNumber;
	}

	public void setPurchaseOrderNumber(String purchaseOrderNumber) {
		PurchaseOrderNumber = purchaseOrderNumber;
	}

	public int getPOAmendmentNumber() {
		return POAmendmentNumber;
	}

	public void setPOAmendmentNumber(int pOAmendmentNumber) {
		POAmendmentNumber = pOAmendmentNumber;
	}

	public String getPurchaseOrderDate() {
		return PurchaseOrderDate;
	}

	public void setPurchaseOrderDate(String purchaseOrderDate) {
		PurchaseOrderDate = purchaseOrderDate;
	}

	public String getStatusofPurchaseOrder() {
		return StatusofPurchaseOrder;
	}

	public void setStatusofPurchaseOrder(String statusofPurchaseOrder) {
		StatusofPurchaseOrder = statusofPurchaseOrder;
	}

	public String getTradingPartnerCode() {
		return TradingPartnerCode;
	}

	public void setTradingPartnerCode(String tradingPartnerCode) {
		TradingPartnerCode = tradingPartnerCode;
	}

	public String getTradingPartnerName() {
		return TradingPartnerName;
	}

	public void setTradingPartnerName(String tradingPartnerName) {
		TradingPartnerName = tradingPartnerName;
	}

	public String getTypeofTradingPartner() {
		return TypeofTradingPartner;
	}

	public void setTypeofTradingPartner(String typeofTradingPartner) {
		TypeofTradingPartner = typeofTradingPartner;
	}

	public String getShifFromAddressLine1() {
		return ShifFromAddressLine1;
	}

	public void setShifFromAddressLine1(String shifFromAddressLine1) {
		ShifFromAddressLine1 = shifFromAddressLine1;
	}

	public String getShipFromAddressLine2() {
		return ShipFromAddressLine2;
	}

	public void setShipFromAddressLine2(String shipFromAddressLine2) {
		ShipFromAddressLine2 = shipFromAddressLine2;
	}

	public String getShipFromCountryCode() {
		return ShipFromCountryCode;
	}

	public void setShipFromCountryCode(String shipFromCountryCode) {
		ShipFromCountryCode = shipFromCountryCode;
	}

	public String getShipFromCityCode() {
		return ShipFromCityCode;
	}

	public void setShipFromCityCode(String shipFromCityCode) {
		ShipFromCityCode = shipFromCityCode;
	}

	public String getTPZipcode() {
		return TPZipcode;
	}

	public void setTPZipcode(String tPZipcode) {
		TPZipcode = tPZipcode;
	}

	public String getPOLineNumber() {
		return POLineNumber;
	}

	public void setPOLineNumber(String pOLineNumber) {
		POLineNumber = pOLineNumber;
	}

	public String getPartNumber() {
		return PartNumber;
	}

	public void setPartNumber(String partNumber) {
		PartNumber = partNumber;
	}

	public int getPartRevisionNumber() {
		return PartRevisionNumber;
	}

	public void setPartRevisionNumber(int partRevisionNumber) {
		PartRevisionNumber = partRevisionNumber;
	}

	public String getUOMCode() {
		return UOMCode;
	}

	public void setUOMCode(String uOMCode) {
		UOMCode = uOMCode;
	}

	

	public int getPrice() {
		return Price;
	}

	public void setPrice(int price) {
		Price = price;
	}

	public String getCurrencyCode() {
		return CurrencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		CurrencyCode = currencyCode;
	}

	public String getPOScheduleDate() {
		return POScheduleDate;
	}

	public void setPOScheduleDate(String pOScheduleDate) {
		POScheduleDate = pOScheduleDate;
	}

	public String getSupplyDate() {
		return SupplyDate;
	}

	public void setSupplyDate(String supplyDate) {
		SupplyDate = supplyDate;
	}
	public String getBusinessUnitCode() {
		return BusinessUnitCode;
	}

	public void setBusinessUnitCode(String businessUnitCode) {
		BusinessUnitCode = businessUnitCode;
	}

	public String getPlantID() {
		return PlantID;
	}

	public void setPlantID(String plantID) {
		PlantID = plantID;
	}

	public String getHeadParentId() {
		return HeadParentId;
	}

	public void setHeadParentId(String headParentId) {
		HeadParentId = headParentId;
	}

	public String getParentId() {
		return ParentId;
	}

	public void setParentId(String parentId) {
		ParentId = parentId;
	}

	public String getMode() {
		return Mode;
	}

	public void setQuantity(double quantity) {
		Quantity = quantity;
	}

	public void setMode(String mode) {
		Mode = mode;
	}

	public String getCompanyCode() {
		return CompanyCode;
	}

	public void setCompanyCode(String companyCode) {
		CompanyCode = companyCode;
	}

	public String getModifiedTime() {
		return ModifiedTime;
	}

	public void setModifiedTime(String modifiedTime) {
		ModifiedTime = modifiedTime;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
/*
	*//**
	 * Gets the insert date.
	 * 
	 * @return the insert date
	 *//*
	public String getInsertDate() {
		return insertDate;
	}

	*//**
	 * Sets the insert date.
	 * 
	 * @param insertDate
	 *            the new insert date
	 *//*
	public void setInsertDate(final String insertDate) {
		this.insertDate = insertDate;
	}

	*//**
	 * Gets the supply id.
	 * 
	 * @return the supply id
	 *//*
	public String getSupplyID() {
		return supplyID;
	}

	*//**
	 * Sets the supply id.
	 * 
	 * @param supplyID
	 *            the new supply id
	 *//*
	public void setSupplyID(final String supplyID) {
		this.supplyID = supplyID;
	}

	*//**
	 * Gets the supplier id.
	 * 
	 * @return the supplier id
	 *//*
	public String getSupplierID() {
		return supplierID;
	}

	*//**
	 * Sets the supplier id.
	 * 
	 * @param supplierID
	 *            the new supplier id
	 *//*
	public void setSupplierID(final String supplierID) {
		this.supplierID = supplierID;
	}

	*//**
	 * Gets the part id.
	 * 
	 * @return the part id
	 *//*
	public String getPartID() {
		return partID;
	}

	*//**
	 * Sets the part id.
	 * 
	 * @param partID
	 *            the new part id
	 *//*
	public void setPartID(final String partID) {
		this.partID = partID;
	}

	*//**
	 * Gets the date.
	 * 
	 * @return the date
	 *//*
	public String getDate() {
		return this.date;
	}

	*//**
	 * Sets the date.
	 * 
	 * @param date
	 *            the new date
	 *//*
	public void setDate(final String date) {
		this.date = date;
	}

	*//**
	 * Gets the quantity.
	 * 
	 * @return the quantity
	 *//*
	public long getQuantity() {
		return quantity;
	}

	*//**
	 * Sets the quantity.
	 * 
	 * @param quantity
	 *            the new quantity
	 *//*
	public void setQuantity(final long quantity) {
		this.quantity = quantity;
	}

	*//**
	 * Gets the supply type.
	 * 
	 * @return the supply type
	 *//*
	public String getSupplyType() {
		return supplyType;
	}

	*//**
	 * Sets the supply type.
	 * 
	 * @param supplyType
	 *            the new supply type
	 *//*
	public void setSupplyType(final String supplyType) {
		this.supplyType = supplyType;
	}

	*//**
	 * Gets the part name.
	 * 
	 * @return the part name
	 *//*
	public String getPartName() {
		return partName;
	}

	*//**
	 * Sets the part name.
	 * 
	 * @param partName
	 *            the new part name
	 *//*
	public void setPartName(final String partName) {
		this.partName = partName;
	}

	*//**
	 * Gets the supplier name.
	 * 
	 * @return the supplier name
	 *//*
	public String getSupplierName() {
		return supplierName;
	}

	*//**
	 * Sets the supplier name.
	 * 
	 * @param supplierName
	 *            the new supplier name
	 *//*
	public void setSupplierName(final String supplierName) {
		this.supplierName = supplierName;
	}

	*//**
	 * Gets the country.
	 * 
	 * @return the country
	 *//*
	public String getCountry() {
		return country;
	}

	*//**
	 * Sets the country.
	 * 
	 * @param country
	 *            the new country
	 *//*
	public void setCountry(final String country) {
		this.country = country;
	}*/
	
	public Map<String, String> getExcelColumns() {
		Map<String, String> excelColumns = new LinkedHashMap<>();
	/*	excelColumns.put("OrganisationCode", "String");
		excelColumns.put("OrganisationName", "String");
		excelColumns.put("PlantCode", "String");
		excelColumns.put("PlantName", "String");
		excelColumns.put("SupplyType", "String");
		excelColumns.put("SalesOrderNumber", "String");
		excelColumns.put("SOAmendmentNumber", "Integer");
		excelColumns.put("SalesOrderDate", "TimeStamp");
		excelColumns.put("StatusofSaleOrder", "String");
		excelColumns.put("SOLineNumber", "String");
		excelColumns.put("PurchaseOrderNumber", "String");
		excelColumns.put("POAmendmentNumber", "Integer");
		excelColumns.put("PurchaseOrderDate", "TimeStamp");
		excelColumns.put("StatusofPurchaseOrder", "String");
		excelColumns.put("TradingPartnerCode", "String");
		excelColumns.put("TradingPartnerName", "String");
		excelColumns.put("TypeofTradingPartner", "String");
		excelColumns.put("ShifFromAddressLine1", "String");
		excelColumns.put("ShipfromAddressLine2", "String");
		excelColumns.put("ShipfromCountryCode", "String");
		excelColumns.put("ShipfromCityCode", "String");
		excelColumns.put("TPZipcode", "String");
		excelColumns.put("POLineNumber", "String");
		excelColumns.put("PartNumber", "String");
		excelColumns.put("PartName", "String");
		excelColumns.put("PartRevisionNumber", "Integer");
		excelColumns.put("Quantity", "Integer");
		excelColumns.put("UOMCode", "String");
		excelColumns.put("Price", "Float");
		excelColumns.put("CurrencyCode", "String");
		excelColumns.put("POScheduleDate", "TimeStamp");
		excelColumns.put("SupplyDate", "TimeStamp");
		excelColumns.put("BusinessUnitCode", "String");
		excelColumns.put("CompanyCode", "String");*/
		// excelColumns.put("InsertDate","String");
		// excelColumns.put("InsertDate","TimeStamp");
		// excelColumns.put("Mode","String");
	
		
		
		excelColumns.put("Organisation Code","String");
		excelColumns.put("Organisation Name","String");
		excelColumns.put("Company Code","String");
		excelColumns.put("Company Name","String");
		excelColumns.put("Plant Code","String");
		excelColumns.put("Plant Name","String");
		excelColumns.put("Supply Type","String");
		excelColumns.put("Sales Order Number","String");
		excelColumns.put("SO Amendment Number","Integer");
		excelColumns.put("Sales Order Date","TimeStamp");
		excelColumns.put("Status of Sale Order","String");
		excelColumns.put("SO Line Number","Integer");
		excelColumns.put("Purchase Order Number","String");
		excelColumns.put("PO Amendment Number","Integer");
		excelColumns.put("Purchase Order Date","TimeStamp");
		excelColumns.put("Status of Purchase Order","String");
		excelColumns.put("Trading Partner Code","String");
		excelColumns.put("Trading Partner Name","String");
		excelColumns.put("Type of Trading Partner","String");
		excelColumns.put("Ship from Address Line 1","String");
		excelColumns.put("Ship from Address Line 2","String");
		excelColumns.put("Ship from Country Code","String");
		excelColumns.put("Ship from City Code","String");
		excelColumns.put("TP Zipcode","String");
		excelColumns.put("PO Line Number","Integer");
		excelColumns.put("Part Number/ Product Code","String");
		excelColumns.put("Part/ Product Name","String");
		excelColumns.put("Part/ Product Revision Number","Integer");
		excelColumns.put("Quantity","Integer");
		excelColumns.put("UOM Code","String");
		excelColumns.put("Price","Integer");
		excelColumns.put("Currency Code","String");
		excelColumns.put("PO Schedule Date","TimeStamp");
		excelColumns.put("Supply Date","TimeStamp");
		excelColumns.put("Business Unit Code","String");
		excelColumns.put("ModeOfShipment","String");
		excelColumns.put("ShipDate","TimeStamp");
		excelColumns.put("LeadTime","Integer");
		excelColumns.put("UOMForLeadtime","String");
		return excelColumns;


		
	}

	public String getSupplyType() {
		return SupplyType;
	}

	public void setSupplyType(String supplyType) {
		SupplyType = supplyType;
	}

	public String getPartName() {
		return PartName;
	}

	public void setPartName(String partName) {
		PartName = partName;
	}

	public String getInsertDate() {
		return InsertDate;
	}

	public void setInsertDate(String insertDate) {
		InsertDate = insertDate;
	}

	public double getQuantity() {
		return Quantity;
	}
	
	
	
}