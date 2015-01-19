package datastore;


import java.util.LinkedHashSet;

/**
 * The Class TableStandardizationUtil.
 */
public class TableStandardizationUtil {

	/** The Project instance name. */
	//private String projectInstanceName = "E2EProductTest";
	  private String projectInstanceName = "ARUBA_MASTER_UATDATASET";
	  
	  
		/*   Set bulkUploadURL according to the dataset as below : 
      ARUBA_MASTER_UATDATASET = http://arubauatbulkuploads-dot-e2escm-gpractice.appspot.com/ -> arubauat  
      ARUBA_MASTER_VENKAT = http://arubatestbulkuploads-dot-e2escm-gpractice.appspot.com/ -> arubatest
      ARUBA_MASTER_ITEST = bulkuploads             -> <anydev>
*/
	  
	  private String bulkUploadURL = "arubauatbulkuploads";
	
	
	private static String plantDataInstanceName = null;
	private String activePlantCode = null;

	private String supplyBuy = "SUPPLYBUY";

	/** The Project id. */
	private String projectId = "383286818063";

	/** The Product table name. */
	private String productTableName = "BOM";

	/** The Demand table name. */
	private String demandTableName = "DEMAND";

	/** The Derived demand table name. */
	private String derivedDemandTableName = "DerivedDemandData";

	/** The Supply table name. */
	private String supplyTableName = "SUPPLY";

	/** The Derived supply table name. */
	private String derivedSupplyTableName = "DerivedSupply";

	/** The Demand owner table name. */
	private String demandOwnerTableName = "Demand_Owner";

	/** The Upload details. */
	private String uploadDetails = "UPLOADDETAILS";

	/** The Supplier table name. */
	private String supplierTableName = "Supplier";

	/** The current demand view. */
	private String currentDemandView = "CURRENT_DEMAND";
	
	private String currentDemandInfoView = "CURRENT_DEMAND_INFO";
	private String currentSupplyInfoView = "CURRENT_SUPPLY_INFO";

	public String getCurrentSupplyInfoView() {
		return currentSupplyInfoView;
	}

	public void setCurrentSupplyInfoView(String currentSupplyInfoView) {
		this.currentSupplyInfoView = currentSupplyInfoView;
	}

	/** The current supply view. */
	private String currentSupplyView = "CURRENT_SUPPLY";

	/** The current derived supply view. */
	private String currentDerivedSupplyView = "CURRENT_DerivedSupply";

	/** The Refresh table. */
	private String refreshTable = "REFRESH_DATA";

	/** The Tableau report table. */
	private String tableauReportTable = "TABLEAU_DER_SUPPLY";

	/** The Mode. */
	private String mode = "REFRESH";

	/** The Mode. */
	private String 	user = "USER";
	

	// Admin Upload
	private String vmiTableName = "VMI";

	private String userTableName = "USER";

	private String partTableName = "PART";

	private String physicalTableName = "PHYSICAL";

	private String virtualTableName = "VIRTUAL";

	private String buyerTableName = "BUYER";
	
	private String tradingPartnerTableName = "TRADINGPARTNER";
	
	private String inventoryTableName = "INVENTORY";
	
	private String wareHouseTableName = "WAREHOUSE";
	
	private String supplyMakeTableName = "SUPPLYMAKE";
	
	/** Number of columns in an excel that are uploaded for Demand */
    private int demandColCount = 29;

    /** Number of columns in an excel that are uploaded for Supply */
    private int supplyColCount = 39;

    /** Number of columns in an excel that are uploaded for Product */
    private int productColCount = 27;

	private int vmiColCount = 23;

	private int userColCount = 20;

	private int partColCount = 28;

	private int physicalColCount = 33;

	private int virtualColCount = 10;

	private int buyerColCount = 12;
	
	private int tradingPartnerColCount = 23;
	
	private int inventoryColCount = 19;
	
	private int wareHouseColCount = 18;
	
	private int supplyMakeColCount = 43;
	

	/*
	 * //for REFRESH mode (version 24) private String GOOGLE_CLIENT_ID=
	 * "105212968912-ln6up4pm0tuld1991fj0nh3m1f0mlcq8.apps.googleusercontent.com"
	 * ; private String DEVELOPER_KEY="AIzaSyCT2RN9-fxIqWVzSsadkXbhLTD5drRprPc";
	 */

	/** request type for Supply */
	private int supply = 1;

	/** request type for Demand */
	private int demand = 2;

	/** request for Demand and Supply */
	private int demandSupply = 3;

	/** The time_out. */
	static public Long timeOut = 30000L;

	/*
	 * //for REFRESH mode (version 64) private String GOOGLE_CLIENT_ID=
	 * "105212968912-ak81m00vge51il80fh54ri52ghc454br.apps.googleusercontent.com"
	 * ; private String DEVELOPER_KEY="AIzaSyCT2RN9-fxIqWVzSsadkXbhLTD5drRprPc";
	 */

	// for REFRESH mode (version 39a)
	/** The google_client_id.to get Object from GDrive */
	private String googleClientId = "383286818063-4f0n7j82aq4b14ev58d7h888v3epu2ua.apps.googleusercontent.com";

	/** The developer_key.to get Object from GDrive */
	private String developerKey = "AIzaSyCUqniAgJzC7t3PybGiVpxWJt6OB5Qh_xw";

	/*
	 * //for REFRESH mode (version 65) private String GOOGLE_CLIENT_ID=
	 * "105212968912-tmcisjtqj9lthmu2mmaf1lfttooijtpd.apps.googleusercontent.com"
	 * ; private String DEVELOPER_KEY="AIzaSyCT2RN9-fxIqWVzSsadkXbhLTD5drRprPc";
	 */

	
	//validation switch for upload tables:
	
	private Boolean validateVMITable = true;
	
	private Boolean validateUserTable = true;
	
	private Boolean validatePartTable = true;
	
	private Boolean validatePhysicalTable = true;
	
	private Boolean validateVirtualTable = true;
	
	private Boolean validateBuyerTable = true;
	
	private Boolean validateTradingPartnerTable = true;
	
	private Boolean validateInventoryTable = true;
	
	private Boolean validateWareHouseTable = true;
	
	private Boolean validateSupplyMakeTable = true;
	
	
	public Boolean getValidatePartTable() {
		return validatePartTable;
	}

	public void setValidatePartTable(Boolean validatePartTable) {
		this.validatePartTable = validatePartTable;
	}
	
	public Boolean getValidateVMITable() {
		return validateVMITable;
	}

	public void setValidateVMITable(Boolean validateVMITable) {
		this.validateVMITable = validateVMITable;
	}
	
	public Boolean getValidateUserTable() {
		return validateUserTable;
	}

	public void setValidateUserTable(Boolean validateUserTable) {
		this.validateUserTable = validateUserTable;
	}
	
	public Boolean getValidatePhysicalTable() {
		return validatePhysicalTable;
	}

	public void setValidatePhysicalTable(Boolean validatePhysicalTable) {
		this.validatePhysicalTable = validatePhysicalTable;
	}

	public Boolean getValidateVirtualTable() {
		return validateVirtualTable;
	}

	public void setValidateVirtualTable(Boolean validateVirtualTable) {
		this.validateVirtualTable = validateVirtualTable;
	}

	public Boolean getValidateBuyerTable() {
		return validateBuyerTable;
	}

	public void setValidateBuyerTable(Boolean validateBuyerTable) {
		this.validateBuyerTable = validateBuyerTable;
	}

	public Boolean getValidateTradingPartnerTable() {
		return validateTradingPartnerTable;
	}

	public void setValidateTradingPartnerTable(Boolean validateTradingPartnerTable) {
		this.validateTradingPartnerTable = validateTradingPartnerTable;
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
	 * Gets the google client id.
	 * 
	 * @return the google client id
	 */
	public String getGOOGLECLIENTID() {
		return googleClientId;
	}

	/**
	 * Sets the google client id.
	 * 
	 * @param googleClientId
	 *            the new google client id
	 */
	public void setGOOGLECLIENTID(String googleClientId) {
		this.googleClientId = googleClientId;
	}

	/**
	 * Gets the developer key.
	 * 
	 * @return the developer key
	 */
	public String getDEVELOPERKEY() {
		return developerKey;
	}

	/**
	 * Sets the developer key.
	 * 
	 * @param developerKey
	 *            the new developer key
	 */
	public void setDEVELOPERKEY(String developerKey) {
		this.developerKey = developerKey;
	}

	public String getSupplyBuy() {
		return supplyBuy;
	}

	public void setSupplyBuy(String supplyBuy) {
		this.supplyBuy = supplyBuy;
	}
	
	/**
	 * Sets the mode.
	 * 
	 * @param mode
	 *            the new mode
	 */
	public void setMode(String mode) {
		this.mode = mode;
	}

	/**
	 * Gets the project instance name.
	 * 
	 * @return the project instance name
	 */
	public String getProjectInstanceName() {
		return projectInstanceName;
	}

	/**
	 * Gets the project id.
	 * 
	 * @return the project id
	 */
	public String getProjectId() {
		return projectId;
	}

	/**
	 * Gets the product table name.
	 * 
	 * @return the product table name
	 */
	public String getProductTableName() {
		return productTableName;
	}

	/**
	 * Gets the demand table name.
	 * 
	 * @return the demand table name
	 */
	public String getDemandTableName() {
		return demandTableName;
	}

	/**
	 * Gets the supply table name.
	 * 
	 * @return the supply table name
	 */
	public String getSupplyTableName() {
		return supplyTableName;
	}

	/**
	 * Gets the demand owne tabler name.
	 * 
	 * @return the demand owne tabler name
	 */
	public String getDemandOwneTablerName() {
		return demandOwnerTableName;
	}

	/**
	 * Gets the upload details.
	 * 
	 * @return the upload details
	 */
	public String getUploadDetails() {
		return uploadDetails;
	}

	/**
	 * Gets the supplier table name.
	 * 
	 * @return the supplier table name
	 */
	public String getSupplierTableName() {
		return supplierTableName;
	}

	/**
	 * Gets the derived demand table name.
	 * 
	 * @return the derived demand table name
	 */
	public String getDerivedDemandTableName() {
		return derivedDemandTableName;
	}

	/**
	 * Gets the refresh table.
	 * 
	 * @return the refresh table
	 */
	public String getRefreshTable() {
		return refreshTable;
	}

	/**
	 * Sets the refresh table.
	 * 
	 * @param refreshTable
	 *            the new refresh table
	 */
	public void setRefreshTable(String refreshTable) {
		this.refreshTable = refreshTable;
	}

	/**
	 * Gets the current demand view.
	 * 
	 * @return the current demand view
	 */
	public String getCurrentDemandView() {
		return currentDemandView;
	}

	/**
	 * Gets the current supply view.
	 * 
	 * @return the current supply view
	 */
	public String getCurrentSupplyView() {
		return currentSupplyView;
	}

	/**
	 * Gets the current derived supply view.
	 * 
	 * @return the current derived supply view
	 */
	public String getCurrentDerivedSupplyView() {
		return currentDerivedSupplyView;
	}

	/**
	 * Gets the derived supply table name.
	 * 
	 * @return the derived supply table name
	 */
	public String getDerivedSupplyTableName() {
		return derivedSupplyTableName;
	}

	/**
	 * Gets the supply.
	 * 
	 * @return the supply
	 */
	public int getSUPPLY() {
		return supply;
	}

	/**
	 * Sets the supply.
	 * 
	 * @param sUPPLY
	 *            the new supply
	 */
	public void setSUPPLY(int sUPPLY) {
		supply = sUPPLY;
	}

	/**
	 * Gets the demand.
	 * 
	 * @return the demand
	 */
	public int getDEMAND() {
		return demand;
	}

	/**
	 * Sets the demand.
	 * 
	 * @param dEMAND
	 *            the new demand
	 */
	public void setDEMAND(int dEMAND) {
		demand = dEMAND;
	}

	/**
	 * Gets the demand supply.
	 * 
	 * @return the demand supply
	 */
	public int getDEMANDSUPPLY() {
		return demandSupply;
	}

	/**
	 * Sets the demand supply.
	 * 
	 * @param demandSupply
	 *            the new demand supply
	 */
	public void setDEMANDSUPPLY(int demandSupply) {
		this.demandSupply = demandSupply;
	}

	/**
	 * Gets the demand_row count.
	 * 
	 * @return the demand_row count
	 */
	public int getDemandColCount() {
		return demandColCount;
	}

	/**
	 * Sets the demand_row count.
	 * 
	 * @param demandRowCount
	 *            the new demand_row count
	 */
	public void setDemandColCount(int demandColCount) {
		demandColCount = demandColCount;
	}

	/**
	 * Gets the supply_row count.
	 * 
	 * @return the supply_row count
	 */
	public int getSupplyrowCount() {
		return supplyColCount;
	}

	/**
	 * Sets the supply_row count.
	 * 
	 * @param supplyRowCount
	 *            the new supply_row count
	 */
	public void setSupplyrowCount(int supplyRowCount) {
		supplyColCount = supplyRowCount;
	}

	/**
	 * Gets the product_row count.
	 * 
	 * @return the product_row count
	 */
	public int getProductrowCount() {
		return productColCount;
	}

	/**
	 * Sets the product_row count.
	 * 
	 * @param productRowCount
	 *            the new product_row count
	 */
	public void setProductrowCount(int productRowCount) {
		productColCount = productRowCount;
	}

	/**
	 * Gets the tableau report table.
	 * 
	 * @return the tableau report table
	 */
	public String getTableauReportTable() {
		return tableauReportTable;
	}

	/**
	 * Sets the tableau report table.
	 * 
	 * @param tableauReportTable
	 *            the new tableau report table
	 */
	public void setTableauReportTable(String tableauReportTable) {
		this.tableauReportTable = tableauReportTable;
	}

	// Admin Upload

	public String getVmiTableName() {
		return vmiTableName;
	}

	public String getUserTableName() {
		return userTableName;
	}

	public String getPartTableName() {
		return partTableName;
	}

	public String getPhysicalTableName() {
		return physicalTableName;
	}

	public String getVirtualTableName() {
		return virtualTableName;
	}

	public String getBuyerTableName() {
		return buyerTableName;
	}

	public String getTradingPartnerTableName() {
		return tradingPartnerTableName;
	}
	
	public String getInventoryTableName() {
		return inventoryTableName;
	}
	
	public String getWareHouseTableName() {
		return wareHouseTableName;
	}
	
	public String getSupplyMakeTableName() {
		return supplyMakeTableName;
	}
	
	public int getVMIrowCount() {
		return vmiColCount;
	}

	public void setVMIrowCount(int vmiRowCount) {
		vmiColCount = vmiRowCount;

	}

	public int getUserColCount() {
		return userColCount;
	}

	public void setUserColCount(int userColCount) {
		this.userColCount = userColCount;
	}
	
	public int getPartRowCount() {
		return partColCount;
	}

	public void setPartRowCount(int partColCount) {
		this.partColCount = partColCount;
	}

	public int getPhysicalrowCount() {
		return physicalColCount;
	}

	public void setPhysicalrowCount(int physicalRowCount) {
		physicalColCount = physicalRowCount;
	}

	public int getVirtualrowCount() {
		return virtualColCount;
	}

	public void setVirtualrowCount(int virtualRowCount) {
		virtualColCount = virtualRowCount;
	}

	public int getBuyerRowCount() {
		return buyerColCount;
	}

	public void setBuyerRowCount(int buyerColCount) {
		this.buyerColCount = buyerColCount;
	}
	
	public int getTradingPartnerRowCount() {
		return tradingPartnerColCount;
	}

	public void setTradingPartnerRowCount(int tradingPartnerColCount) {
		this.tradingPartnerColCount = tradingPartnerColCount;
	}
	
	public int getInventoryRowCount() {
		return inventoryColCount;
	}

	public void setInventoryRowCount(int inventoryColCount) {
		this.inventoryColCount = inventoryColCount;
	}
	
	public int getWareHouseRowCount() {
		return wareHouseColCount;
	}

	public void setWareHouseRowCount(int wareHouseColCount) {
		this.wareHouseColCount = wareHouseColCount;
	}
	
	public int getSupplyMakeRowCount() {
		return supplyMakeColCount;
	}

	public void setSupplyMakeRowCount(int supplyMakeColCount) {
		this.supplyMakeColCount = supplyMakeColCount;
	}
	
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	//end Admin
	
	/*public HashSet getValidDemandTypes() {
		HashSet validDemandTypes = new HashSet<String>();
		validDemandTypes.add("Forecast");
		validDemandTypes.add("Independent Demand");
		validDemandTypes.add("Safety Stock");
		validDemandTypes.add("Order Backlog");
		return validDemandTypes;
	}*/
	
	public LinkedHashSet getValidDemandTypes() {
		LinkedHashSet validDemandTypes = new LinkedHashSet<String>();
		/*validDemandTypes.add("Order Backlog");
		validDemandTypes.add("Independent Demand");
		validDemandTypes.add("Order Type");
		validDemandTypes.add("Safety Stock");
		validDemandTypes.add("Forecast");*/
		validDemandTypes.add("Bookings");
		validDemandTypes.add("BACKLOG");
		validDemandTypes.add("FORECAST");
		return validDemandTypes;
	}
	
	
    public String getCurrentDemandInfoView() {
        return currentDemandInfoView;
    }

    public void setCurrentDemandInfoView(String currentDemandInfoView) {
        this.currentDemandInfoView = currentDemandInfoView;
    }
    
	public static String getPlantDataInstanceName() {
		return plantDataInstanceName;
	}

	public static void setPlantDataInstanceName(String plantDataInstanceName) {
		TableStandardizationUtil.plantDataInstanceName = plantDataInstanceName;
	}
	
	public Boolean getValidateInventoryTable() {
		return validateInventoryTable;
	}

	public void setValidateInventoryTable(Boolean validateInventoryTable) {
		this.validateInventoryTable = validateInventoryTable;
	}

	public Boolean getValidateWareHouseTable() {
		return validateWareHouseTable;
	}

	public void setValidateWareHouseTable(Boolean validateWareHouseTable) {
		this.validateWareHouseTable = validateWareHouseTable;
	}

	public Boolean getValidateSupplyMakeTable() {
		return validateSupplyMakeTable;
	}

	public void setValidateSupplyMakeTable(Boolean validateSupplyMakeTable) {
		this.validateSupplyMakeTable = validateSupplyMakeTable;
	}
		
	public String getActivePlantCode() {
		return activePlantCode;
	}

	public void setActivePlantCode(String activePlantCode) {
		this.activePlantCode = activePlantCode;
	}
	
	public String getBulkUploadURL() {
		return bulkUploadURL;
	}

	public void setBulkUploadURL(String bulkUploadURL) {
		this.bulkUploadURL = bulkUploadURL;
	}
}
