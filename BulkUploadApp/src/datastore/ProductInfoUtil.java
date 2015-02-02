package datastore;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;



import datastore.TableStandardizationUtil;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.http.HttpTransport;
import com.google.appengine.api.NamespaceManager;
import com.google.appengine.api.datastore.Blob;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;

// TODO: Auto-generated Javadoc
/**
 * The Class ProductInfoUtil.
 * 
 * @author Gayatri Jena
 * @version <0.1>
 */
public class ProductInfoUtil {

	/** The Constant TRANSPORT. */
	private static final HttpTransport TRANSPORT = new UrlFetchTransport();
	// private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static int productUploadCountValue;
    
	private static int demandUploadCountValue;
	
	private static int supplyUploadCountValue;
	
	private static int inventoryUploadCountValue;

	private static final String COUNT = "InsertionCount";
	
	//private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();

	/** The mem cahce details. */

	/** The child parent map. */
	private static HashMap<String, Double> childParentMap;

	/** The lead time map. */
	private static LinkedHashMap<String, Integer> leadTimeMap;

	/** The product i ds. */
	private static Map<String, String> productIDs;
	
	private static Map<String, String> arubaProductNOs;
	
	private static Map<String, String> deamndRequiredDates;
	private static Map<String, String> supplyRequiredDates;
	private static Map<String, String> productRequiredDates;
	private static Map<String, String> arubaFamilyNOs;
	private static DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
	/** The t util. */
	static TableStandardizationUtil tUtil = new TableStandardizationUtil();

	public static void putAllProductRequiredDatesIntoDataStore(final Map productReqDates/*, String plantDataSetInstance*/)
			throws IOException {
		productRequiredDates = new HashMap<String, String>();
		
		//System.out.println("productRequiredDates :::::::::::"+tUtil.getProjectInstanceName());
		NamespaceManager.set(tUtil.getProjectInstanceName());
		productRequiredDates = getProductRequiredDatesFromDataStore();
		final Set<String> keys = productReqDates.keySet();
		for (String key : keys) {
			System.out.println("key::::"+productReqDates.get(key));
			productRequiredDates.put(key, (String) productReqDates.get(key));
		}
		Entity productRequiredDates = new Entity(SCMConstants.PRODUCT_REQUIREDDATES,
				tUtil.getProjectInstanceName() + SCMConstants.PRODUCT_REQUIREDDATES_MAP);
		productRequiredDates.setProperty(SCMConstants.MODE,
				tUtil.getProjectInstanceName() + SCMConstants.PRODUCT_REQUIREDDATES_MAP);
		
		productRequiredDates.setProperty(SCMConstants.PRODUCT_REQUIREDDATES_DATA, new Blob(
				ConversionUtil.convertObjectToByte(supplyRequiredDates)));
		//System.out.println("productRequiredDates @@@@@ "+productRequiredDates.getProperties());
		
		datastore.put(productRequiredDates);

	}
	
	public static Map<String, String> getProductRequiredDatesFromDataStore(/*String plantDataSetInstance*/) {
		String plantDataSetInstance = tUtil.getProjectInstanceName();
		//System.out.println("productRequiredDatesFromDataStore:::"+plantDataSetInstance);
		
		NamespaceManager.set(plantDataSetInstance);
		Entity productReqDates = new Entity(SCMConstants.PRODUCT_REQUIREDDATES,
				plantDataSetInstance+ SCMConstants.PRODUCT_REQUIREDDATES_MAP);
		//System.out.println("productReqDates*Entity***********:"+productReqDates);	
		try {
			productReqDates = datastore.get(productReqDates.getKey());
		//	System.out.println("productReqDates*key***********:"+productReqDates);	
		} catch (EntityNotFoundException e) {
			productReqDates = null;
		}
		if (productReqDates != null) {
			//System.out.println("productReqDates*if**********:"+productReqDates);	
			final Blob blob = (Blob) productReqDates
					.getProperty(SCMConstants.PRODUCT_REQUIREDDATES_DATA);
			try {
				if (ConversionUtil.convertByteToObject(blob.getBytes()) instanceof Map) {
					productRequiredDates = (Map<String, String>) ConversionUtil
							.convertByteToObject(blob.getBytes());
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Exception in productRequiredDates() "
						+ e.getMessage());
			} catch (Exception e) {
				System.out.println("Exception in productRequiredDates() "
						+ e.getMessage());
			}
		}
		//System.out.println("*****  productRequiredDates ************:"+productRequiredDates);
		return productRequiredDates;
	}
	
	public static void putAllRequiredDatesIntoDataStore(final Map demandReqDates/*, String plantDataSetInstance*/)
			throws IOException {
		deamndRequiredDates = new HashMap<String, String>();
		
		//System.out.println("putAllRequiredDatesIntoDataStore :::::::::::"+tUtil.getProjectInstanceName());
		NamespaceManager.set(tUtil.getProjectInstanceName());
		deamndRequiredDates = getDemandRequiredDatesFromDataStore();
		final Set<String> keys = demandReqDates.keySet();
		for (String key : keys) {
			System.out.println("key::::"+demandReqDates.get(key));
			deamndRequiredDates.put(key, (String) demandReqDates.get(key));
		}
		Entity demadRequiredDates = new Entity(SCMConstants.DEMAND_REQUIREDDATES,
				tUtil.getProjectInstanceName() + SCMConstants.DEMAND_REQUIREDDATES_MAP);
		demadRequiredDates.setProperty(SCMConstants.MODE,
				tUtil.getProjectInstanceName() + SCMConstants.DEMAND_REQUIREDDATES_MAP);
		demadRequiredDates.setProperty(SCMConstants.DEMAND_REQUIREDDATES_DATA, new Blob(
				ConversionUtil.convertObjectToByte(deamndRequiredDates)));
		//System.out.println("demadRequiredDates @@@@@ "+demadRequiredDates.getProperties());
		
		datastore.put(demadRequiredDates);

	}
	
	public static Map<String, String> getDemandRequiredDatesFromDataStore(/*String plantDataSetInstance*/) {
		String plantDataSetInstance = tUtil.getProjectInstanceName();
		//System.out.println("DemandRequiredDatesFromDataStore:::"+plantDataSetInstance);
		
		NamespaceManager.set(plantDataSetInstance);
		Entity demanddReqDates = new Entity(SCMConstants.DEMAND_REQUIREDDATES,
				plantDataSetInstance+ SCMConstants.DEMAND_REQUIREDDATES_MAP);
		//System.out.println("demanddReqDates*Entity**********:"+demanddReqDates);	
		try {
			demanddReqDates = datastore.get(demanddReqDates.getKey());
			//System.out.println("demanddReqDates**key***********:"+demanddReqDates);	
		} catch (EntityNotFoundException e) {
			demanddReqDates = null;
		}
		if (demanddReqDates != null) {
			//System.out.println("demanddReqDates**if**********:"+demanddReqDates);	
			final Blob blob = (Blob) demanddReqDates
					.getProperty(SCMConstants.DEMAND_REQUIREDDATES_DATA);
			try {
				if (ConversionUtil.convertByteToObject(blob.getBytes()) instanceof Map) {
					deamndRequiredDates = (Map<String, String>) ConversionUtil
							.convertByteToObject(blob.getBytes());
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Exception in getDemandRequiredDatesFromDataStore() "
						+ e.getMessage());
			} catch (Exception e) {
				System.out.println("Exception in getDemandRequiredDatesFromDataStore() "
						+ e.getMessage());
			}
		}
		//System.out.println("*****  deamndRequiredDates ************:"+deamndRequiredDates);
		return deamndRequiredDates;
	}

	
	public static void putAllSupplyRequiredDatesIntoDataStore(final Map supplyReqDates/*, String plantDataSetInstance*/)
			throws IOException {
		supplyRequiredDates = new HashMap<String, String>();
		
		//System.out.println("putAllRequiredDatesIntoDataStore :::::::::::"+tUtil.getProjectInstanceName());
		NamespaceManager.set(tUtil.getProjectInstanceName());
		supplyRequiredDates = getSupplyRequiredDatesFromDataStore();
		final Set<String> keys = supplyReqDates.keySet();
		for (String key : keys) {
			System.out.println("key::::"+supplyReqDates.get(key));
			supplyRequiredDates.put(key, (String) supplyReqDates.get(key));
		}
		Entity supplyRequiredDates = new Entity(SCMConstants.SUPPLY_REQUIREDDATES,
				tUtil.getProjectInstanceName() + SCMConstants.SUPPLY_REQUIREDDATES_MAP);
		supplyRequiredDates.setProperty(SCMConstants.MODE,
				tUtil.getProjectInstanceName() + SCMConstants.SUPPLY_REQUIREDDATES_MAP);
		
		supplyRequiredDates.setProperty(SCMConstants.SUPPLY_REQUIREDDATES_DATA, new Blob(
				ConversionUtil.convertObjectToByte(supplyRequiredDates)));
		//System.out.println("supplyDates @@@@@ "+supplyRequiredDates.getProperties());
		
		datastore.put(supplyRequiredDates);

	}
	
	public static Map<String, String> getSupplyRequiredDatesFromDataStore(/*String plantDataSetInstance*/) {
		String plantDataSetInstance = tUtil.getProjectInstanceName();
		//System.out.println("supplyRequiredDatesFromDataStore:::"+plantDataSetInstance);
		
		NamespaceManager.set(plantDataSetInstance);
		Entity supplyReqDates = new Entity(SCMConstants.SUPPLY_REQUIREDDATES,
				plantDataSetInstance+ SCMConstants.SUPPLY_REQUIREDDATES_MAP);
		//System.out.println("supplyReqDates*Entity***********:"+supplyReqDates);	
		try {
			supplyReqDates = datastore.get(supplyReqDates.getKey());
			//System.out.println("supplyReqDates*key***********:"+supplyReqDates);	
		} catch (EntityNotFoundException e) {
			supplyReqDates = null;
		}
		if (supplyReqDates != null) {
			//System.out.println("supplyReqDates*if**********:"+supplyReqDates);	
			final Blob blob = (Blob) supplyReqDates
					.getProperty(SCMConstants.SUPPLY_REQUIREDDATES_DATA);
			try {
				if (ConversionUtil.convertByteToObject(blob.getBytes()) instanceof Map) {
					supplyRequiredDates = (Map<String, String>) ConversionUtil
							.convertByteToObject(blob.getBytes());
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Exception in getSupplyRequiredDatesFromDataStore() "
						+ e.getMessage());
			} catch (Exception e) {
				System.out.println("Exception in getSupplyRequiredDatesFromDataStore() "
						+ e.getMessage());
			}
		}
		System.out.println("*****  supplyReqDates ************:"+supplyRequiredDates);
		return supplyRequiredDates;
	}
	public static void putProductUploadCountIntoDataStore(int insertionCount,
			String plantDataSetInstance) throws IOException {
		
		NamespaceManager.set(plantDataSetInstance);
		Entity productUploadCount = new Entity(
				SCMConstants.PRODUCT_UPLOAD_COUNT, SCMConstants.PRODUCT_UPLOAD_COUNT_DATA);
		//int val = getProductUploadCountFromDataStore(plantDataSetInstance);
		productUploadCount.setProperty(COUNT, insertionCount + 1);
		datastore.put(productUploadCount);
	}

	public static int getProductUploadCountFromDataStore(String plantDataSetInstance) {
		
		//DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		NamespaceManager.set(plantDataSetInstance);
		Entity productUploadCount = new Entity(
				SCMConstants.PRODUCT_UPLOAD_COUNT, SCMConstants.PRODUCT_UPLOAD_COUNT_DATA);
		try {
			productUploadCount = datastore.get(productUploadCount.getKey());
		} catch (EntityNotFoundException e) {
			productUploadCount = null;
		}
		if (productUploadCount != null) {
			try {
				Long longvalue = (Long) productUploadCount.getProperty(COUNT);
				productUploadCountValue = longvalue.intValue();
			} catch (Exception e) {
				System.out
						.println("Exception in getProductUploadCountFromDataStore() "
								+ e.getMessage());
			}
		} else {
			productUploadCountValue = 0;
		}
		//System.out.println("ProductUploadCountValue :::"+ productUploadCountValue);
		return productUploadCountValue;
	}
	public static void putDemandUploadCountIntoDataStore(int insertionCount,
			String plantDataSetInstance) throws IOException {
		
		NamespaceManager.set(plantDataSetInstance);		
		Entity demandUploadCount = new Entity(
				SCMConstants.DEMAND_UPLOAD_COUNT, SCMConstants.DEMAND_UPLOAD_COUNT_DATA);
		//int val = getDemandUploadCountFromDataStore(plantDataSetInstance);
		demandUploadCount.setProperty(COUNT, insertionCount + 1);
		//System.out.println("demandUploadCount---->"+demandUploadCount);		
		datastore.put(demandUploadCount);
	}
	
	public static int getDemandUploadCountFromDataStore(String plantDataSetInstance) {
		
		//System.out.println("plantDataSetInstance-getDemandUploadCountFromDataStore->"+plantDataSetInstance);
		NamespaceManager.set(plantDataSetInstance);		
		Entity demandUploadCount = new Entity(
				SCMConstants.DEMAND_UPLOAD_COUNT, SCMConstants.DEMAND_UPLOAD_COUNT_DATA);
		try {
			demandUploadCount = datastore.get(demandUploadCount.getKey());
		} catch (EntityNotFoundException e) {
			demandUploadCount = null;
		}
		if (demandUploadCount != null) {
			try {
				Long longvalue = (Long) demandUploadCount.getProperty(COUNT);
				demandUploadCountValue = longvalue.intValue();
			} catch (Exception e) {
				/*System.out
						.println("Exception in getDemandloadCountFromDataStore() "
								+ e.getMessage());*/
			}
		} else {
			demandUploadCountValue = 0;
		}
		//System.out.println("demandUploadCountValue :::"+ demandUploadCountValue);
		return demandUploadCountValue;
	}
	public static void putSupplyUploadCountIntoDataStore(int insertionCount,
			String plantDataSetInstance) throws IOException {
		
		NamespaceManager.set(plantDataSetInstance);
		Entity supplyUploadCount = new Entity(
				SCMConstants.SUPPLY_UPLOAD_COUNT, SCMConstants.SUPPLY_UPLOAD_COUNT_DATA);
		//int val = getSupplyUploadCountFromDataStore(plantDataSetInstance);
		supplyUploadCount.setProperty(COUNT, insertionCount + 1);
		datastore.put(supplyUploadCount);
	}
	
	public static int getSupplyUploadCountFromDataStore(String plantDataSetInstance) {
		
		NamespaceManager.set(plantDataSetInstance);
		Entity supplyUploadCount = new Entity(
				SCMConstants.SUPPLY_UPLOAD_COUNT, SCMConstants.SUPPLY_UPLOAD_COUNT_DATA);
		try {
			supplyUploadCount = datastore.get(supplyUploadCount.getKey());
		} catch (EntityNotFoundException e) {
			supplyUploadCount = null;
		}
		if (supplyUploadCount != null) {
			try {
				Long longvalue = (Long) supplyUploadCount.getProperty(COUNT);
				supplyUploadCountValue = longvalue.intValue();
			} catch (Exception e) {
				System.out
						.println("Exception in getDemandloadCountFromDataStore() "
								+ e.getMessage());
			}
		} else {
			supplyUploadCountValue = 0;
		}
		//System.out.println("supplyUploadCountValue :::"+ supplyUploadCountValue);
		return supplyUploadCountValue;
	}
	
}
