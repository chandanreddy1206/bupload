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

	/** The mem cahce details. */

	/** The child parent map. */
	private static HashMap<String, Double> childParentMap;

	/** The lead time map. */
	private static LinkedHashMap<String, Integer> leadTimeMap;

	/** The product i ds. */
	private static Map<String, String> productIDs;
	
	private static Map<String, String> arubaProductNOs;
	
	private static Map<String, String> deamndRequiredDates;
	
	private static Map<String, String> arubaFamilyNOs;

	/** The t util. */
	static TableStandardizationUtil tUtil = new TableStandardizationUtil();

	/**
	 * Function returns list of products with all related parents.
	 * 
	 * @return HashMap<String, Double> containing related Parent product with
	 *         quantity
	 */
/*	public static void getRelatedParentInfo() {
		System.out
				.println("ParentProductInfoUtil: getRelatedParentInfo***************");
		// QueryResponse prodResponse = new QueryResponse();
		childParentMap = new HashMap<String, Double>();
		leadTimeMap = new LinkedHashMap<String, Integer>();

		final List<Product> productrows = getProductDataFromDataStore();

		if (productrows != null) {

			for (final Product productDatarows1 : productrows) {
				//System.out.println("Inside getRelatedParentInfo"+productDatarows1.getProductCode()+"Lead time"+productDatarows1.getLeadTime());
				childParentMap.put(productDatarows1.getProductCode() + ":"
						+ productDatarows1.getParentID(),
						(double)productDatarows1.getPartUsageQuantity());
				leadTimeMap.put(productDatarows1.getProductCode(),
						productDatarows1.getLeadTime());

			}
		}
	}

	*//**
	 * Gets the tree structure of given product with all the product details.
	 * 
	 * @param productID
	 *            the product id
	 * @return the product tree
	 *//*
	public static ArrayListTree getProductTree(final String productID) {
		final ArrayListTree aListTree = new ArrayListTree();
		final ArrayList<ComponentObject> allP = new ArrayList<ComponentObject>();
		// final ArrayList<Integer> aryLeadTime = new ArrayList<>();
		final LinkedHashMap<String, Integer> ltMap = new LinkedHashMap<String, Integer>();
		final LinkedHashMap<String, Double> productMap = getSingleProductMap(
				productID, ltMap);
		//System.out.println("productMap"+productMap);
		ComponentObject V = null;
		String[] parentProdId;
		//System.out.println("leadTimeMap"+leadTimeMap);
		for (final Map.Entry<String, Double> entryProd : productMap.entrySet()) {
			parentProdId = entryProd.getKey().toString().split(":");
			V = new ComponentObject();
			V.setId(parentProdId[0]);
			V.setQuantity(entryProd.getValue());
			if (leadTimeMap != null && !leadTimeMap.isEmpty()) {
				System.out.println("leadtimemap" + leadTimeMap.get(parentProdId[0]));
				V.setLeadTime(leadTimeMap.get(parentProdId[0]));
			}
			V.setParentId(parentProdId[1]);
			allP.add(V);
		}
		ComponentObject rootParent = new ComponentObject();

		final Iterator<ComponentObject> objectIterator = allP.iterator();
		ComponentObject parentObject;
		Iterator<ComponentObject> childObjectIterator;
		ComponentObject childObject;
		while (objectIterator.hasNext()) {
			parentObject = objectIterator.next();
			if (parentObject.getParentId().equalsIgnoreCase(
					SCMConstants.PARENT_ID)) {

				rootParent = parentObject;
				aListTree.add(rootParent);
				childObjectIterator = allP.iterator();
				while (childObjectIterator.hasNext()) {
					childObject = (ComponentObject) childObjectIterator.next();
					if (childObject.getParentId().equalsIgnoreCase(
							parentObject.getId())) {
						try {
							aListTree.add(rootParent, childObject);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						getChildren(aListTree, allP, childObject);
					}
				}
			}
			 * else if (parentObject.getParentId().equalsIgnoreCase(productID))
			 * {
			 * 
			 * }
			 
		}
		int maxLeadtime = 0;

		try {
			int j = 0;
			double pQuant = 0;
			int pLeadTime = 0;
			ComponentObject d;
			while (j < aListTree.children(rootParent).size()) {

				d = (ComponentObject) aListTree.children(rootParent).get(j);
				pQuant = d.getQuantity() * rootParent.getQuantity();
				pLeadTime = d.getLeadTime() + rootParent.getLeadTime();
				d.setRelLeadTime(pLeadTime);
				d.setRelQuantity(pQuant);
				if (maxLeadtime == 0) {
					maxLeadtime = pLeadTime;
				} else {
					maxLeadtime = maxLeadtime > d.getLeadTime() ? maxLeadtime
							: d.getLeadTime();
				}

				maxLeadtime = computedTree(aListTree, d, pQuant, pLeadTime,
						maxLeadtime, false, null, null, null);
				j++;
			}
		} catch (Exception e) {
			System.out.println("getProductTree :: Exception : " + e.toString());
			e.printStackTrace();
		}
		return aListTree;
	}

	*//**
	 * adds all the children info to <code>aListTree</code>
	 * 
	 * @param aListTree
	 *            all the child info will be added to this
	 * 
	 * @param allP
	 *            List component objects
	 * 
	 * @param childObject
	 *            component object containing child info
	 * 
	 * @return the children
	 *//*
	public static void getChildren(final ArrayListTree aListTree,
			final ArrayList<ComponentObject> allP,
			final ComponentObject childObject) {
		final Iterator<ComponentObject> childObjectIterator = allP.iterator();
		ComponentObject subChildObject;
		while (childObjectIterator.hasNext()) {
			subChildObject = childObjectIterator.next();
			if (subChildObject.getParentId().equalsIgnoreCase(
					childObject.getId())) {
				try {
					aListTree.add(childObject, subChildObject);
				} catch (shared.NodeNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				//getChildren(aListTree, allP, subChildObject);
			}
		}
	}

	*//**
	 * puts all the product info into <code>aListTree</code>
	 * 
	 * @param aListTree
	 *            all the product info will be added to this
	 * 
	 * @param inChild
	 *            gets the child info
	 * 
	 * @param qnt
	 *            quantity of the component
	 * 
	 * @param leadtime
	 *            lead of the component
	 * 
	 * @param maxLeadtime
	 *            the highest of all the components lead time in the product
	 * 
	 * @param var
	 *            boolean true is leafnode false has children
	 * 
	 * @param ComputeDate
	 *            can be null
	 * 
	 * @param demandDate
	 *            can be null
	 * 
	 * @param strWeek
	 *            can be null
	 * 
	 * @return the lead time for the whole product
	 *//*
	public static int computedTree(final ArrayListTree aListTree,
			final ComponentObject inChild, final double qnt,
			final int leadtime, int maxLeadtime, final boolean var,
			final Calendar ComputeDate, final Calendar demandDate,
			final String strWeek) {

		int j = 0;
		double pQuant = 0;
		int pLeadTime = 0;
		ComponentObject childObj = null;
		try {
			while (aListTree.children(inChild) != null
					&& j < aListTree.children(inChild).size()) {
				childObj = (ComponentObject) aListTree.children(inChild).get(j);
				if (var == false) {

					childObj.setRelLeadTime(inChild.getRelLeadTime()
							+ childObj.getLeadTime());
					childObj.setRelQuantity(inChild.getRelQuantity()
							* childObj.getQuantity());
					maxLeadtime = maxLeadtime > childObj.getLeadTime() ? maxLeadtime
							: childObj.getLeadTime();
				}
				computedTree(aListTree, childObj, pQuant, pLeadTime,
						maxLeadtime, var, ComputeDate, demandDate, strWeek);
				j++;
			}
		} catch (shared.NodeNotFoundException e) {
			e.printStackTrace();
		}
		return maxLeadtime;
	}

	*//**
	 * puts the data into product map with key in <code>productID</code>
	 * :ParentID format and value is lead time map
	 * 
	 * @param productID
	 *            Product ID
	 * 
	 * @param ltMap
	 *            LeadTime Map
	 * 
	 * @return the single product map
	 *//*
	private static LinkedHashMap<String, Double> getSingleProductMap(
			final String productID, LinkedHashMap<String, Integer> ltMap) {
		final LinkedHashMap<String, Double> productMap = new LinkedHashMap<String, Double>();
		if (ltMap == null) {
			ltMap = new LinkedHashMap<String, Integer>();
		}
		//System.out.println("childParentMap"+childParentMap);
//		if (childParentMap == null || childParentMap.isEmpty()) {
			ProductInfoUtil.getRelatedParentInfo();
//		}
		productMap.put(productID +":"+productID , 1.0);
		String[] childParent;
		for (final Map.Entry<String, Double> prodMap : childParentMap
				.entrySet()) {
			childParent = prodMap.getKey().split(":");
			if (childParent[1].equalsIgnoreCase(productID)) {
				productMap.put(prodMap.getKey(), prodMap.getValue());
				ltMap.put(productID, leadTimeMap.get(childParent[0]));
				ltMap.put(productID, leadTimeMap.get(childParent[1]));
				putChild(childParent[0], productMap, ltMap);
			}

		}
		return productMap;
	}

	*//**
	 * Code snippet for retrieving the demand for parent products
	 * 
	 * @param productID
	 *            the product id
	 * @param productMap
	 *            the product map
	 * @param ltMap
	 *            the leadTime map
	 *//*
	private static void putChild(final String productID,
			final LinkedHashMap<String, Double> productMap,
			final LinkedHashMap<String, Integer> ltMap) {
		String[] childParent;
		for (final Map.Entry<String, Double> prodMap : childParentMap
				.entrySet()) {
			childParent = prodMap.getKey().split(":");
			if (childParent[1].equalsIgnoreCase(productID)) {
				productMap.put(prodMap.getKey(), prodMap.getValue());
				ltMap.put(productID, leadTimeMap.get(childParent[0]));
				ltMap.put(productID, leadTimeMap.get(childParent[1]));
				//putChild(childParent[0], productMap, ltMap);
			}

		}
	}

	*//**
	 * Gets the all parent info.
	 * 
	 * @param productID
	 *            the product id
	 * @return the all parent info
	 *//*
	public static Map<String, Double> getAllParentInfo(final String productID) {
		// Code snippet for retrieving all the related parent Info
		final Map<String, Double> parents = new HashMap<String, Double>();
		String[] childParent;
		for (final Map.Entry<String, Double> prodMap : childParentMap
				.entrySet()) {
			childParent = prodMap.getKey().split(":");
			if (childParent[0].equalsIgnoreCase(productID)) {
				if (!childParent[1].equalsIgnoreCase(SCMConstants.PARENT_ID)) {
					parents.put(childParent[1], prodMap.getValue());
				}
				getParents(childParent[1], prodMap.getValue(), parents);
			}
		}
		return parents;
	}

	*//**
	 * Gets the parents.
	 * 
	 * @param parent
	 *            the parent
	 * @param qnty
	 *            the qnty
	 * @param parents
	 *            the parents
	 * @return the parents
	 *//*
	private static void getParents(String parent, final Double qnty,
			final Map<String, Double> parents) {
		String[] childParent;
		double d;
		for (Map.Entry<String, Double> prodMap : childParentMap.entrySet()) {
			childParent = prodMap.getKey().split(":");
			if (childParent[0].equalsIgnoreCase(parent)) {
				d = qnty * prodMap.getValue();
				if (!childParent[1].equalsIgnoreCase(SCMConstants.PARENT_ID)) {
					parents.put(childParent[1], d);
				}
				getParents(childParent[1], d, parents);
			}
		}
	}

	*//**
	 * Gets the product name for the given product ID
	 * 
	 * @param productID
	 *            the product id
	 * @return the product name
	 *//*
	public static String getProductName(String productID) {
		String productName = "NA";
		try {
			System.out.println("productID IN getProductName : "+productID);
			System.out.println("List of product ids : "+productIDs );
//			System.out.println("productIDs.isEmpty() : "+productIDs.isEmpty());
//			System.out.println("isProductPresent(productID) : "+isProductPresent(productID));
			if (productIDs==null||productIDs.isEmpty()) {
				System.out.println("iNSIDE productIDs.isEmpty() ");
				getProductIDsFromDataStore();
			}			
//				System.out.println("AFTER CHECK FOR EMPTY");
				if (isProductPresent(productID)) {
//					System.out.println("INSIDE isProductPresent(productID) : ");
					productName = productIDs.get(productID);
				//	System.out.print("productName" + productName);
				}
			
		} catch (IllegalArgumentException | NullPointerException e) {
			e.printStackTrace();
		}
		//System.out.println("FINALLY PRODUCT NAME : "+productName);
		
		
		return productName;
	}

	*//**
	 * Put all product ids into data store.
	 * 
	 * @param h
	 *            map containing product id as key and product name as value for
	 *            the newly added products
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 *//*
	public static void putAllProductIDsIntoDataStore(final Map h,String plantDataSetInstance)
			throws IOException {
		productIDs = new HashMap<String, String>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		System.out.println("PlantDataSet :::::::::::"+tUtil.getPlantDataInstanceName());
		tUtil.setPlantDataInstanceName(plantDataSetInstance);
		NamespaceManager.set(tUtil.getPlantDataInstanceName());
		productIDs = getProductIDsFromDataStore();
		final Set<String> s = h.keySet();
		for (String m : s) {
			System.out.println("Family::::"+h.get(m));
			productIDs.put(m, (String) h.get(m));
		}
		Entity ProductData = new Entity(SCMConstants.DATASTORE_CACHEDATA,
				plantDataSetInstance
						+ SCMConstants.DSENTITY_PRODUCT_MAP);
		ProductData.setProperty(SCMConstants.MODE,
				plantDataSetInstance
						+ SCMConstants.DSENTITY_PRODUCT_MAP);
		ProductData.setProperty(SCMConstants.DSENTITY_PRODUCT_DATA, new Blob(
				ConversionUtil.convertObjectToByte(productIDs)));
//System.out.println("ProductData @@@@@ "+ProductData.getProperties());
		datastore.put(ProductData);

	}
	
	public static void putAllArubaProductNOsIntoDataStore(final Map prodData, String plantDataSetInstance)
			throws IOException {
		arubaProductNOs = new HashMap<String, String>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		System.out.println("putAllArubaProductNOsIntoDataStore::PlantDataSet :::::::::::"+plantDataSetInstance);
		NamespaceManager.set(plantDataSetInstance);
		arubaProductNOs = getArubaProductNOsFromDataStore(plantDataSetInstance);
		final Set<String> keys = prodData.keySet();
		for (String key : keys) {
			//System.out.println("ArubaProductNO::::"+prodData.get(key));
			arubaProductNOs.put(key, (String) prodData.get(key));
		}
		Entity ProductData = new Entity(SCMConstants.DATASTORE_CACHEDATA,
				plantDataSetInstance + SCMConstants.DSENTITY_ARUBA_PRODUCT_MAP);
		ProductData.setProperty(SCMConstants.MODE,
				plantDataSetInstance + SCMConstants.DSENTITY_ARUBA_PRODUCT_MAP);
		ProductData.setProperty(SCMConstants.DSENTITY_ARUBA_PRODUCT_DATA, new Blob(
				ConversionUtil.convertObjectToByte(arubaProductNOs)));
		//System.out.println("ProductData @@@@@ "+ProductData.getProperties());
		
		datastore.put(ProductData);

	}
	

	*//**
	 * Checks if product is present.
	 * 
	 * @param productID
	 *            the product id
	 * @return true, if product is present
	 *//*
	public static boolean isProductPresent(String productID) {
		try {
			System.out.println("PRODUCT ID : "+productID);
			Set<String> proIDs = new HashSet<String>();
			getProductIDsFromDataStore();
			proIDs = productIDs.keySet();
			//System.out.println("isProductPresent : Set product ID : " + proIDs);

			if (!proIDs.contains(productID)) {
				return false;
			} else {
				return true;
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("isProductPresent 1: " + e.toString());
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("isProductPresent 2: " + e.toString());
			return false;
		}

	}

	public static boolean isArubaProductPresent(String productNO, String plantDataSetInstance) {
		try {
			System.out.println("PRODUCT NO : "+productNO);
			Set<String> proIDs = new HashSet<String>();
			arubaProductNOs = getArubaProductNOsFromDataStore(plantDataSetInstance);
			proIDs = arubaProductNOs.keySet();
			System.out.println("Plant ::"+plantDataSetInstance+"\n:proIDs::"+proIDs);
			if (!proIDs.contains(productNO)) {
				return false;
			} else {
				System.out.println("product already exist...:"+productNO);
				return true;
			}

		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			System.out.println("isProductPresent 1: " + e.toString());
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("isProductPresent 2: " + e.toString());
			return false;
		}

	}
	
	
	*//**
	 * Put product BOM data into datastore.
	 * 
	 * @param list
	 *            the new product data
	 * @throws IllegalArgumentException
	 *             the illegal argument exception
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 *//*
	public static void putProductDataIntoDatastore(final List<Product> list , String plantDataSetInstance)
			throws IllegalArgumentException, IOException {
		System.out.println("putProductDataIntoDatastore:::plantDataSetInstance:"+plantDataSetInstance);
		//System.out.println("putProductDataIntoDatastore*************************"+list);
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		List<Product> allProducts = new ArrayList<Product>();
		Product product = new Product();
		allProducts = getProductDataFromDataStore(plantDataSetInstance);
		if (allProducts == null || allProducts.isEmpty()) {
			System.out.println("all products is null");
			allProducts = new ArrayList<Product>();
		}
		final Iterator<Product> itr = list.iterator();
		while (itr.hasNext()) {
			product = (Product) itr.next();
			allProducts.add(product);
		}
		//System.out.println("allProducts.size() %%%%%%%%%%%%%%%%%::"+allProducts.size());
		//System.out.println("allProducts %%%%%%%%%%%%%%%%%:::"+allProducts);
		//System.out.println("*** plant instance ***"+plantDataSetInstance);
		NamespaceManager.set(plantDataSetInstance);
		Entity productData = new Entity(SCMConstants.DATASTORE_CACHEDATA,
					plantDataSetInstance + SCMConstants.DSENTITY_PRODUCT_DATA);
		productData.setProperty(SCMConstants.MODE,
					plantDataSetInstance + SCMConstants.DSENTITY_PRODUCT_DATA);
		
		productData.setProperty(SCMConstants.DSENTITY_PRODUCT_DATA, new Blob(
				ConversionUtil.convertObjectToByte(allProducts)));
		//System.out.println("productData:"+productData.getProperties());
		//System.out.println("productData:"+productData.getProperty("Product_Data"));
		datastore.put(productData);
try {
	System.out.println("after datastore put:"+datastore.get(productData.getKey()));
} catch (EntityNotFoundException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
	}

	*//**
	 * Gets the complete product BOM data from data store.
	 * 
	 * @return the product BOM data from data store
	 *//*
	public static List<Product> getProductDataFromDataStore() {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		System.out.println("tUtil.getPlantDataInstanceName()"+tUtil.getPlantDataInstanceName());
		NamespaceManager.set(tUtil.getPlantDataInstanceName());
		Entity productData = new Entity(SCMConstants.DATASTORE_CACHEDATA,
				tUtil.getPlantDataInstanceName()
						+ SCMConstants.DSENTITY_PRODUCT_DATA);

		List<Product> allproducts = null;
		try {
			productData = datastore.get(productData.getKey());

		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			productData = null;
		}
		//System.out.println(":::: productData ::::" + productData);
		if (productData != null) {
			final Blob blob = (Blob) productData.getProperty(SCMConstants.DSENTITY_PRODUCT_DATA);
			//System.out.println("blob::::" + blob.getBytes());
			try {
				//System.out.println("List BLOB ##########"
						+ (List) ConversionUtil.convertByteToObject(blob.getBytes()));
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				//System.out.println("Inside try%%%%%%%");
				final List productsList = (List) ConversionUtil.convertByteToObject(blob.getBytes());
				//System.out.println("List BLOB ##########"+productsList);
				allproducts = (List<Product>) productsList;
			} catch (IllegalArgumentException e) {
				System.out
						.println("Exception in getProductDataFromDataStore() "
								+ e.getMessage());
			} catch (IOException e) {
				System.out
						.println("Exception in getProductDataFromDataStore() "
								+ e.getMessage());
			}
		} else {
			//System.out.println("inside else");
			allproducts = new ArrayList();
		}
		//System.out.println("getProductDataFromDataStore allproducts" + allproducts);
		return allproducts;
	}

	*//**
	 * Gets the product ids from data store.
	 * 
	 * @return the product ids from data store
	 *//*
	public static Map<String, String> getProductIDsFromDataStore() {
		System.out.println("inside ids getProductIDsFromDataStore"+tUtil.getPlantDataInstanceName());
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		NamespaceManager.set(tUtil.getPlantDataInstanceName());
		Entity productData = new Entity(SCMConstants.DATASTORE_CACHEDATA,
				tUtil.getPlantDataInstanceName()
						+ SCMConstants.DSENTITY_PRODUCT_MAP);
		try {
			productData = datastore.get(productData.getKey());
		} catch (EntityNotFoundException e) {
			productData = null;
		}
		if (productData != null) {
			final Blob blob = (Blob) productData
					.getProperty(SCMConstants.DSENTITY_PRODUCT_DATA);
			try {
				if (ConversionUtil.convertByteToObject(blob.getBytes()) instanceof Map) {
					productIDs = (Map<String, String>) ConversionUtil
							.convertByteToObject(blob.getBytes());
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Exception in getProductIDsFromDataStore() "
						+ e.getMessage());
			} catch (Exception e) {
				System.out.println("Exception in getProductIDsFromDataStore() "
						+ e.getMessage());
			}
		}
		//System.out.println("*****  productIDs ************"+productIDs);
		return productIDs;
	}
	
	public static Map<String, String> getArubaProductNOsFromDataStore(String plantDataSetInstance) {
		System.out.println("inside ids getArubaProductNOsFromDataStore::::"+plantDataSetInstance);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		NamespaceManager.set(plantDataSetInstance);
		Entity productData = new Entity(SCMConstants.DATASTORE_CACHEDATA,
				plantDataSetInstance+ SCMConstants.DSENTITY_ARUBA_PRODUCT_MAP);
		try {
			productData = datastore.get(productData.getKey());
		} catch (EntityNotFoundException e) {
			productData = null;
		}
		if (productData != null) {
			final Blob blob = (Blob) productData
					.getProperty(SCMConstants.DSENTITY_ARUBA_PRODUCT_DATA);
			try {
				if (ConversionUtil.convertByteToObject(blob.getBytes()) instanceof Map) {
					arubaProductNOs = (Map<String, String>) ConversionUtil
							.convertByteToObject(blob.getBytes());
				}
			} catch (IllegalArgumentException e) {
				System.out.println("Exception in getProductIDsFromDataStore() "
						+ e.getMessage());
			} catch (Exception e) {
				System.out.println("Exception in getProductIDsFromDataStore() "
						+ e.getMessage());
			}
		}
		//System.out.println("*****  arubaProductNOs ************"+arubaProductNOs);
		return arubaProductNOs;
	}
	
	*//**
	 * Gets the complete product BOM data from data store.
	 * 
	 * @return the product BOM data from data store
	 *//*
	public static List<Product> getProductDataFromDataStore(String plantDataSetInstance) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		System.out.println("getProductDataFromDataStore(String plantDataSetInstance)::::"+plantDataSetInstance);
		NamespaceManager.set(plantDataSetInstance);
		Entity productData = new Entity(SCMConstants.DATASTORE_CACHEDATA,
				plantDataSetInstance + SCMConstants.DSENTITY_PRODUCT_DATA);

		List<Product> allproducts = null;
		try {
			productData = datastore.get(productData.getKey());

		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			productData = null;
		}
		//System.out.println(":::: productData ::::" + productData);
		if (productData != null) {
			final Blob blob = (Blob) productData.getProperty(SCMConstants.DSENTITY_PRODUCT_DATA);
			//System.out.println("blob::::" + blob.getBytes());
			try {
				System.out.println("List BLOB ##########"
						+ (List) ConversionUtil.convertByteToObject(blob.getBytes()));
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			try {
				System.out.println("Inside try%%%%%%%");
				final List productsList = (List) ConversionUtil.convertByteToObject(blob.getBytes());
				//System.out.println("List BLOB ##########"+productsList);
				allproducts = (List<Product>) productsList;
			} catch (IllegalArgumentException e) {
				System.out
						.println("Exception in getProductDataFromDataStore() "
								+ e.getMessage());
			} catch (IOException e) {
				System.out
						.println("Exception in getProductDataFromDataStore() "
								+ e.getMessage());
			}
		} else {
			//System.out.println("inside else");
			allproducts = new ArrayList();
		}
		//System.out.println("getProductDataFromDataStore allproducts" + allproducts);
		return allproducts;
	}*/
	
	public static void putAllRequiredDatesIntoDataStore(final Map demandReqDates/*, String plantDataSetInstance*/)
			throws IOException {
		deamndRequiredDates = new HashMap<String, String>();
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		System.out.println("PlantDataSet::putAllRequiredDatesIntoDataStore :::::::::::"+tUtil.getProjectInstanceName());
		NamespaceManager.set(tUtil.getProjectInstanceName());
		deamndRequiredDates = getDemandRequiredDatesFromDataStore();
		final Set<String> keys = demandReqDates.keySet();
		for (String key : keys) {
			System.out.println("demandReqDates::::"+demandReqDates.get(key));
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
		System.out.println("inside ids getDemandRequiredDatesFromDataStore:::"+plantDataSetInstance);
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		NamespaceManager.set(plantDataSetInstance);
		Entity demanddReqDates = new Entity(SCMConstants.DEMAND_REQUIREDDATES,
				plantDataSetInstance+ SCMConstants.DEMAND_REQUIREDDATES_MAP);
		try {
			demanddReqDates = datastore.get(demanddReqDates.getKey());
		} catch (EntityNotFoundException e) {
			demanddReqDates = null;
		}
		if (demanddReqDates != null) {
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
		System.out.println("*****  deamndRequiredDates ************:"+deamndRequiredDates);
		return deamndRequiredDates;
	}
	
/*	public static void putAllArubaFamilyNOsIntoDataStore(final List<Product> list)
			throws IOException {
		System.out.println("putAllArubaFamilyNOsIntoDataStore::"
				+ tUtil.getProjectInstanceName());
		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
		List<Product> allProducts = new ArrayList<Product>();
		Product product = new Product();
		allProducts = getArubaFamilyNOsFromDataStore();
		if (allProducts == null || allProducts.isEmpty()) {
			System.out.println("all products is null");
			allProducts = new ArrayList<Product>();
		}
		final Iterator<Product> itr = list.iterator();
		while (itr.hasNext()) {
			product = (Product) itr.next();
			allProducts.add(product);
		}
		NamespaceManager.set(tUtil.getProjectInstanceName());
		Entity productData = new Entity(SCMConstants.DATASTORE_CACHEDATA,
				tUtil.getProjectInstanceName() + SCMConstants.DSENTITY_ARUBA_FAMILY_DATA);
		productData.setProperty(SCMConstants.MODE,
				tUtil.getProjectInstanceName() + SCMConstants.DSENTITY_ARUBA_FAMILY_DATA);

		productData.setProperty(SCMConstants.DSENTITY_ARUBA_FAMILY_DATA, new Blob(
				ConversionUtil.convertObjectToByte(allProducts)));
		System.out.println("FamilyData:" + productData.getProperties());
		datastore.put(productData);
		try {
			System.out.println("after datastore put:"
					+ datastore.get(productData.getKey()));
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public static List<Product> getArubaFamilyNOsFromDataStore() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		NamespaceManager.set(tUtil.getProjectInstanceName());
		Entity productData = new Entity(SCMConstants.DATASTORE_CACHEDATA,
				tUtil.getProjectInstanceName() + SCMConstants.DSENTITY_ARUBA_FAMILY_DATA);

		List<Product> allFamilyNOs = null;
		try {
			productData = datastore.get(productData.getKey());
		} catch (EntityNotFoundException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
			productData = null;
		}
		//System.out.println(":::: Family ::::" + productData);
		if (productData != null) {
			final Blob blob = (Blob) productData.getProperty(SCMConstants.DSENTITY_ARUBA_FAMILY_DATA);
			try {
				System.out.println("Inside try%%%%%%%");
				final List productsList = (List) ConversionUtil.convertByteToObject(blob.getBytes());
				allFamilyNOs = (List<Product>) productsList;
			} catch (IllegalArgumentException e) {
				System.out
						.println("Exception in getArubaFamilyNOsFromDataStore() "
								+ e.getMessage());
			} catch (IOException e) {
				System.out
						.println("Exception in getArubaFamilyNOsFromDataStore() "
								+ e.getMessage());
			}
		} else {
			//System.out.println("inside else");
			allFamilyNOs = new ArrayList();
		}
		//System.out.println("getProductDataFromDataStore allFamilyNOs" + allFamilyNOs);
		return allFamilyNOs;
	}*/
}
