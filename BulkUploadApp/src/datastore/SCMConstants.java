package datastore;


import java.util.HashMap;

// TODO: Auto-generated Javadoc
/**
 * The Interface SCMConstants.
 */
public interface SCMConstants {

	/** Kissflow config constant.URL of KISSFLOW */
	public static final String BASE_URL = "http://kf-0001783.appspot.com/api/1";

	/** Kissflow config constants.API_KEY_VALUE. */
	public static final String API_KEY_VALUE = "953ec89c-1d2d-11e4-b3b7-b1fd76db25fb";

	/** Google Drive config constants.The Constant SCOPE. */
	public static final String SCOPE = "https://www.googleapis.com/auth/bigquery";

	/** Google Drive config constants. The Constant GDRIVE_FILE_SCOPE. */
	public static final String GDRIVE_FILE_SCOPE = "https://www.googleapis.com/auth/drive.file";

	/** Google Drive config constants The Constant GDRIVE_SCOPE. */
	public static final String GDRIVE_SCOPE = "https://www.googleapis.com/auth/drive";

	/** Google Drive config constants for email service */
	public static final String SERVICE_ACCOUNT_EMAIL = "383286818063-hlnbgni1q730oscekseqoan1nui6fk6v@developer.gserviceaccount.com";

	/** Google Drive config constants for file path */
	public static final String SERVICE_ACCOUNT_PKCS12_FILE_PATH = "e2escm-gpractice-8c4b9745f862.p12";

	/** Application name */
	public static final String APP_NAME = "e2escm-gpractice";

	/** The Constant DOWNLOAD_FILE_PATH. */
	public static final String DOWNLOAD_FILE_PATH = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

	/** Twilio config constants.The Constant TWILIO_ACCOUNT_SID. */
	public static final String TWILIO_ACCOUNT_SID = "AC8d3c472977cb8cc8b1fd91d33151bb80";

	/** TWILIO Authentication token */
	public static final String TWILIO_AUTH_TOKEN = "c2d2207888838bf6015e275d0feb534d";
	public static final String TWILIO_PURCHASE_NO = "(760) 364-6373";

	/** Kissflowutil constants. The Constant HTTP_METHOD_POST. */
	public static final String HTTP_METHOD_POST = "POST";

	/** Kissflowutil constants.The Constant HTTP_METHOD_GET. */
	public static final String HTTP_METHOD_GET = "GET";

	/** The Constant API_KEY. */
	public static final String API_KEY = "api_key";

	/** The Constant EMAIL_ID. */
	public static final String EMAIL_ID = "email_id";

	/** The Constant ACTION_SUBMIT. */
	public static final String ACTION_SUBMIT = "/submit";

	/** The Constant DEMAND_OWNER_NAME. */
	public static final String DEMAND_OWNER_NAME = "Demand_Owner_Name";

	/** The Constant QUANTITY. */
	public static final String QUANTITY = "Quantity";

	/** The Constant DEMAND_OWNER_ID. */
	public static final String DEMAND_OWNER_ID = "Demand_Owner_ID";

	/** The Constant PRODUCT_NAME. */
	public static final String PRODUCT_NAME = "Product_Name";

	/** The Constant FILE_NAME. */
	public static final String FILE_NAME = "File_Name";

	/** The Constant UPLOAD_DATE. */
	public static final String UPLOAD_DATE = "Upload_Date";

	/** The Constant SEQUENCENO. */
	public static final String SEQUENCENO = "SequenceNo";

	/** The Constant ID. */
	public static final String ID = "Id";

	/** The Constant SUBJECT. */
	public static final String SUBJECT = "Subject";

	/** The Constant PROCESS_STEP. */
	public static final String PROCESS_STEP = "Process Step";

	/** The Constant PROCESS_NAME. */
	public static final String PROCESS_NAME = "processName";

	/** The Constant PARENT_ID. */
	public static final String PARENT_ID = "P0";

	/** The Constant DataStore Entity Product Data */
	public static final String DSENTITY_PRODUCT_DATA = "Product_Data";

	/** The Constant DataStore Entity Product Map. */
	public static final String DSENTITY_PRODUCT_MAP = "Product_Map";
	
	public static final String DSENTITY_ARUBA_PRODUCT_DATA = "Aruba_Product_Data";
	
	public static final String DSENTITY_ARUBA_PRODUCT_MAP = "Aruba_Product_Map";
	
	public static final String DEMAND_REQUIREDDATES_MAP = "Demand_RequiredDates_Map";
	
	public static final String DEMAND_REQUIREDDATES_DATA = "Demand_RequiredDates_Data";
	
	public static final String DSENTITY_ARUBA_FAMILY_DATA = "Aruba_Family_Data";
	
	public static final String DSENTITY_ARUBA_FAMILY_MAP = "Aruba_Family_Map";
	
	/** The Constant DataStore Entity Part Data */
	public static final String DSENTITY_PART_DATA = "Part_Data";
	
	public static final String DATASTORE_REFRESHDATA = "RefreshData";
	
	public static final String DATASTORE_INCREMENTALDATA = "IncrementalData";

	/** The Constant DataStore Entity Part Map. */
	public static final String DSENTITY_PART_MAP = "Part_Map";

	/** The Constant DATASTORE_CACHEDATA. */
	public static final String DATASTORE_CACHEDATA = "CacheData";
	
	public static final String DEMAND_REQUIREDDATES = "DemandRequiredDates";
	
	
	public static final String PHYSICAL_DETAILS = "Physical Details";

	/** The Constant MODE. */
	public static final String MODE = "Mode";

	/** The Constant DATEFORMAT YYYYMMDD. */
	public static final String DATEFORMATYYYYMMDD = "yyyy-MM-dd";

	/** The Constant QUARTERS. */
	public static final int[] QUARTERS = { 1, 1, 1, 2, 2, 2, 3, 3, 3, 4, 4, 4 };

	/** Map with month number as key and month name as value. */
	public static final HashMap<String, String> MONTHCHARHASHMAP = new HashMap<String, String>() {

		{

			put("1", MONTH_JAN);

			put("2", MONTH_FEB);

			put("3", MONTH_MAR);

			put("4", MONTH_APR);

			put("5", MONTH_MAY);

			put("6", MONTH_JUN);

			put("7", MONTH_JUL);

			put("8", MONTH_AUG);

			put("9", MONTH_SEP);

			put("10", MONTH_OCT);

			put("11", MONTH_NOV);

			put("12", MONTH_DEC);

		}

	};

	/** The Constant SELECTION_TYPE_WEEK. */
	public static final String SELECTION_TYPE_WEEK = "Weekly";

	/** The Constant SELECTION_TYPE_QUARTER. */
	public static final String SELECTION_TYPE_QUARTER = "Quarterly";

	/** The Constant SELECTION_TYPE_MONTH. */
	public static final String SELECTION_TYPE_MONTH = "Monthly";

	/** Map with month name as key and month number as value. */
	public static final HashMap<String, String> MONTHNUMHASHMAP = new HashMap<String, String>() {
		{
			put(MONTH_JAN, "01");
			put(MONTH_FEB, "02");
			put(MONTH_MAR, "03");
			put(MONTH_APR, "04");
			put(MONTH_MAY, "05");
			put(MONTH_JUN, "06");
			put(MONTH_JUL, "07");
			put(MONTH_AUG, "08");
			put(MONTH_SEP, "09");
			put(MONTH_OCT, "10");
			put(MONTH_NOV, "11");
			put(MONTH_DEC, "12");
		}
	};

	/** Map containing demand supply types with their priority */
	public static final HashMap<String, Integer> DEMANDSUPPLYTYPESHASHMAP = new HashMap<String, Integer>() {
		{
			// set demand supply types according to priority
			put("on-hand", 2);
			put("available as parts", 3);
			put("supply", 4);
		}
	};

	public static final String JAN = "Jan";
	public static final String FEB = "Feb";
	public static final String MAR = "Mar";
	public static final String APR = "Apr";
	public static final String MAY = "May";
	public static final String JUN = "Jun";
	public static final String JUL = "Jul";
	public static final String AUG = "Aug";
	public static final String SEP = "Sep";
	public static final String OCT = "Oct";
	public static final String NOV = "Nov";
	public static final String DEC = "Dec";

	/** The Constant NET_FILTERED_SUPPLIES. */
	public static final String NET_FILTERED_SUPPLIES = "NetFilteredSupplies";

	/** The Constant MONTHLY. */
	public static final String MONTHLY = "Monthly";

	/** The Constant CURRENT_SUPPLY. */
	public static final String CURRENT_SUPPLY = "CurrentSupply";

	/** The Constant CURRENT_DEMAND_AND_SUPPLY. */
	public static final String CURRENT_DEMAND_AND_SUPPLY = "CurrentDemandAndSupply";

	/** The Constant WHAT_IF. */
	public static final String WHAT_IF = "WhatIf";

	/** The Constant PRODUCT. */
	public static final String PRODUCT = "Product";

	/** The Constant PRODUCT_ID. */
	public static final String PRODUCT_ID = "ProductId";

	/** The Constant TIME_FRAME. */
	public static final String TIME_FRAME = "Time Frame";

	/** The Constant NA. */
	public static final String NA = "NA";

	/** The Constant PRODUCT_DATA. */
	public static final String PRODUCT_DATA = "Product_Data";

	/** The Constant PRODUCT_MAP. */
	public static final String PRODUCT_MAP = "Product_Map";

	/** The Constant CACHE_DATA. */
	public static final String CACHE_DATA = "CacheData";

	/** The Constant TOTAL. */
	public static final String TOTAL = "Total";

	/** The Constant NET. */
	public static final String NET = "Net";

	/** The Constant CUMM. */
	public static final String CUMM = "cumm";

	/** The Constant DEMAND_TYPE. */
	public static final String DEMAND_TYPE = "Demand Type";

	/** The Constant STYLE. */
	public static final String STYLE = "style";

	/** The Constant TEXT_ALIGN_AND_COLOR. */
	public static final String TEXT_ALIGN_AND_COLOR = "text-align:left;color:blue";

	/** The Constant FORECAST. */
	public static final String FORECAST = "Forecast";

	/** The Constant YEAR. */
	public static final String YEAR = "Year";

	/** The Constant ON_HAND. */
	public static final String ON_HAND = "On-Hand";

	/** The Constant ON_ORDER. */
	public static final String ON_ORDER = "On-Order";

	/** The Constant TOTAL_DEMAND. */
	public static final String TOTAL_DEMAND = "Total Demand";

	/** The Constant MODE_OF. */
	public static final String MODE_OF = "MODE_OF";

	/** The Constant RES_DEMAND. */
	public static final String RES_DEMAND = "RES_DEMAND";

	/** The Constant VIEW_NAME. */
	public static final String VIEW_NAME = "viewName";

	/** The Constant WEEK. */
	public static final String WEEK = "Week";

	/** The Constant AVAILABLE_AS_PARTS. */
	public static final String AVAILABLE_AS_PARTS = "Available as Parts";

	// added by vinay
	/** The Constant BODY. */
	public static final String BODY = "body";

	/** The Constant ALL. */
	public static final String ALL = "All";

	/** The Constant OFF. */
	public static final String OFF = "Off";
	// public static final String QUARTERLY = "Quarterly";
	/** The Constant NAVIGATION_BUTTON. */
	public static final String NAVIGATION_BUTTON = "navigationButton";

	/** The Constant CURRENT. */
	public static final String CURRENT = "Current";

	/** The Constant S0RT. */
	public static final String S0RT = "sort";

	/** The Constant DISABLE. */
	public static final String DISABLE = "disable";

	/** The Constant BLANK. */
	public static final String BLANK = "_blank";

	/** The Constant CENTER. */
	public static final String CENTER = "center";

	/** The Constant PRODNAMES. */
	public static final String PRODNAMES = "prodNames";

	/** The Constant GETPARENTPRODUCTDETAILSSERVICE. */
	public static final String GETPARENTPRODUCTDETAILSSERVICE = "getParentProductDetailsService";

	/** The Constant GWT_MENUITEM. */
	public static final String GWT_MENUITEM = "gwt-MenuItem";

	/** The Constant ADMIN. */
	public static final String ADMIN = "admin";

	/** The Constant PROCUREMENT_MANAGER. */
	public static final String PROCUREMENT_MANAGER = "procurement_manager";

	/** The Constant PRODUCTION_MANAGER. */
	public static final String PRODUCTION_MANAGER = "production_manager";

	/** The Constant SELECTED_BUTTON. */
	public static final String SELECTED_BUTTON = "selectedButton";

	/** The Constant LANDINGPAGCOLLABORATIONALIGNMENT. */
	public static final String LANDINGPAGCOLLABORATIONALIGNMENT = "LandingPagCollabarationAlignment";

	/** The Constant VALUE. */
	public static final String VALUE = "value";

	/** The Constant OLD_VALUE. */
	public static final String OLD_VALUE = "oldvalue";

	/** The Constant ON. */
	public static final String ON = "On";

	/** The Constant DEMAND_SUPPLY. */
	public static final String DEMAND_SUPPLY = "DemandSupply";

	// add by Arjun
	/** The Constant CURR_JAN. */
	public static final String CURR_JAN = "currJan";

	/** The Constant CURR_FEB. */
	public static final String CURR_FEB = "currFeb";

	/** The Constant CURR_MAR. */
	public static final String CURR_MAR = "currMar";

	/** The Constant CURR_APR. */
	public static final String CURR_APR = "currApr";

	/** The Constant CURR_MAY. */
	public static final String CURR_MAY = "currMay";

	/** The Constant CURR_JUN. */
	public static final String CURR_JUN = "currJun";

	/** The Constant CURR_JUL. */
	public static final String CURR_JUL = "currJul";

	/** The Constant CURR_AUG. */
	public static final String CURR_AUG = "currAug";

	/** The Constant CURR_SEP. */
	public static final String CURR_SEP = "currSep";

	/** The Constant CURR_OCT. */
	public static final String CURR_OCT = "currOct";

	/** The Constant CURR_NOV. */
	public static final String CURR_NOV = "currNov";

	/** The Constant CURR_DEC. */
	public static final String CURR_DEC = "currDec";

	/** The Constant NEXT_JAN. */
	public static final String NEXT_JAN = "nextJan";

	/** The Constant NEXT_FEB. */
	public static final String NEXT_FEB = "nextFeb";

	/** The Constant NEXT_MAR. */
	public static final String NEXT_MAR = "nextMar";

	/** The Constant DEMAND. */
	public static final String DEMAND = "Demand";

	/** The Constant SUPPLY. */
	public static final String SUPPLY = "Supply";

	/** The Constant SUPPLY_TYPE1. */
	public static final String SUPPLY_TYPE1 = "SupplyType";

	/** The Constant SUPPLY_TYPE2. */
	public static final String SUPPLY_TYPE2 = "Supply Type";

	/** The Constant DERIVED. */
	public static final String DERIVED = "Derived";
	// public static final String SELECTION_TYPE_WEEK="Weekly";
	// public static final String SELECTION_TYPE_MONTH="Monthly";
	// public static final String SELECTION_TYPE_QUARTER="Quarterly";
	/** The Constant MONTH_JAN. */
	public static final String MONTH_JAN = "Jan";

	/** The Constant MONTH_FEB. */
	public static final String MONTH_FEB = "Feb";

	/** The Constant MONTH_MAR. */
	public static final String MONTH_MAR = "Mar";

	/** The Constant MONTH_APR. */
	public static final String MONTH_APR = "Apr";

	/** The Constant MONTH_MAY. */
	public static final String MONTH_MAY = "May";

	/** The Constant MONTH_JUN. */
	public static final String MONTH_JUN = "Jun";

	/** The Constant MONTH_JUL. */
	public static final String MONTH_JUL = "Jul";

	/** The Constant MONTH_AUG. */
	public static final String MONTH_AUG = "Aug";

	/** The Constant MONTH_SEP. */
	public static final String MONTH_SEP = "Sep";

	/** The Constant MONTH_OCT. */
	public static final String MONTH_OCT = "Oct";

	/** The Constant MONTH_NOV. */
	public static final String MONTH_NOV = "Nov";

	/** The Constant MONTH_DEC. */
	public static final String MONTH_DEC = "Dec";

	/** The Constant YEAR_2005. */
	public static final String YEAR_2005 = "2005";

	/** The Constant YEAR_2006. */
	public static final String YEAR_2006 = "2006";

	/** The Constant YEAR_2007. */
	public static final String YEAR_2007 = "2007";

	/** The Constant YEAR_2008. */
	public static final String YEAR_2008 = "2008";

	/** The Constant YEAR_2009. */
	public static final String YEAR_2009 = "2009";

	/** The Constant YEAR_2010. */
	public static final String YEAR_2010 = "2010";

	/** The Constant YEAR_2011. */
	public static final String YEAR_2011 = "2011";

	/** The Constant YEAR_2012. */
	public static final String YEAR_2012 = "2012";

	/** The Constant YEAR_2013. */
	public static final String YEAR_2013 = "2013";

	/** The Constant YEAR_2014. */
	public static final String YEAR_2014 = "2014";

	/** The Constant YEAR_2015. */
	public static final String YEAR_2015 = "2015";

	/** The Constant YEAR_2016. */
	public static final String YEAR_2016 = "2016";

	/** The Constant YEAR_2017. */
	public static final String YEAR_2017 = "2017";

	/** The Constant YEAR_2018. */
	public static final String YEAR_2018 = "2018";

	/** The Constant YEAR_2019. */
	public static final String YEAR_2019 = "2019";

	/** The Constant QUARTER_1. */
	public static final String QUARTER_1 = "Q1";

	/** The Constant QUARTER_2. */
	public static final String QUARTER_2 = "Q2";

	/** The Constant QUARTER_3. */
	public static final String QUARTER_3 = "Q3";

	/** The Constant QUARTER_4. */
	public static final String QUARTER_4 = "Q4";

	public static final String DATERANGE="Date Limit Selection";

	public static final String YEARSRANGE = "Years";

}
