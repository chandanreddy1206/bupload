package upload.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.IOUtils;

import upload.test.PlantsDataVo;
import upload.test.bqloadjob;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.model.TableDataInsertAllRequest;
import com.google.api.services.bigquery.model.TableDataInsertAllRequest.Rows;
import com.google.api.services.bigquery.model.TableDataInsertAllResponse;
import com.google.api.services.bigquery.model.TableRow;
import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;
import com.google.apphosting.api.ApiProxy.OverQuotaException;

import datastore.TableStandardizationUtil;

/**
 * The Class UploadDemand process the uploaded Demand Excel Sheet and inserts
 * data into Bigquery row by row.
 * 
 * @author Google_COE
 * @version 0.1
 * 
 */
public class UploadFamily extends HttpServlet {


	/** The message. */
	private transient String message = "";
	private static int insertionCount;
	/** The parent IDs. */
	private transient Set<String> parentIds = new HashSet<String>();

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(UploadDemand.class
			.getCanonicalName());

	/** The Constant TRANSPORT. */
	private static final HttpTransport TRANSPORT = new UrlFetchTransport();

	/** The Constant JSON_FACTORY. */
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	
	private static Map<String, String> demandRequiredDates;
	/** The uploaded file name. */
	public static String uploadedFileName;

	/** The is upload successful. */
	private transient boolean isUploadSuccessful = true;

	/** The demand_ types. */
	private static Set<String> demandTypes = new HashSet<String>();
	
	private final GcsService gcsService = GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());

	private static Map<String, String> productRequiredDates;	
	/** The plant instance. */
	private String plantDataSetInstance = null;
	
	
	public List<Map<String, List<String>>> createProductData(List<List<Object>> table,String plantDataSetInstance)  
	{
		final String DATEFORMAT2 = "yyyy-MM-dd HH:mm:ss";
		String dDate = null;			
		String dDateRD=null;
		String plantName=null;
		final DateFormat to = new SimpleDateFormat(DATEFORMAT2);
		final Iterator<List<Object>> tblItr = table.iterator();
		final List<Product> productSetup = new ArrayList<Product>();	
		int rowNo = 1;
		int j=0;String plantCode = null;		
		productRequiredDates = new HashMap<String, String>();
		while (tblItr.hasNext()) {
			List<Object> rowList = (ArrayList<Object>) tblItr.next();
		  	Product product = new Product();
		  	String productFamily=null;String parentID=null;	
			    productFamily = rowList.get(23).toString();//Family			
			    parentID = rowList.get(6).toString();							
				if(rowList.get(0) != null && !rowList.get(0).toString().trim().isEmpty()){
				product.setOrganisationCode(rowList.get(0).toString());}
				if(rowList.get(1) != null && !rowList.get(1).toString().trim().isEmpty()){
				product.setOrganisationName(rowList.get(1).toString());}				
				if(rowList.get(2) != null && !rowList.get(2).toString().trim().isEmpty()){
				product.setCompanyCode(rowList.get(2).toString());}
				if(rowList.get(3) != null && !rowList.get(3).toString().trim().isEmpty()){
				product.setCompanyName(rowList.get(3).toString());}			
				if(rowList.get(4) != null && !rowList.get(4).toString().trim().isEmpty()){
				plantCode =  rowList.get(4).toString();
				product.setPlantCode(rowList.get(4).toString());			
				}	
		
				if(rowList.get(5) != null && !rowList.get(5).toString().trim().isEmpty()){
				product.setPlantName(rowList.get(5).toString());				
				}				
				if(rowList.get(6) != null && !rowList.get(6).toString().trim().isEmpty()){
				product.setProductCode(rowList.get(6).toString());				
				plantName=product.getProductCode();				
				productRequiredDates.put(plantName, plantName);				
				}				
				if(rowList.get(7) != null && !rowList.get(7).toString().trim().isEmpty()){
				product.setProductName(rowList.get(7).toString());}				
				if(rowList.get(8) != null && !rowList.get(8).toString().trim().isEmpty()){
				product.setProductRevisionNumber((int) (Double.parseDouble(rowList.get(8).toString())));}
				if(rowList.get(9) != null && !rowList.get(9).toString().trim().isEmpty()){
				product.setPartNumber(rowList.get(9).toString());}
				if(rowList.get(10) != null && !rowList.get(10).toString().trim().isEmpty()){
				product.setPartName(rowList.get(10).toString());}				
				if(rowList.get(11) != null && !rowList.get(11).toString().trim().isEmpty()){
				product.setPartRevisionNumber((int) (Double.parseDouble(rowList.get(11).toString())));}
				if(rowList.get(12) != null && !rowList.get(12).toString().trim().isEmpty()){
				product.setBOMStatus(rowList.get(12).toString());}
				if(rowList.get(13) != null && !rowList.get(13).toString().trim().isEmpty()){
				product.setComments(rowList.get(13).toString());}
				if(rowList.get(14) != null && !rowList.get(14).toString().trim().isEmpty()){
				product.setUOMCode(rowList.get(14).toString());}				;
				if(rowList.get(15) != null && !rowList.get(15).toString().trim().isEmpty()){
				final int qty = (int) (Double.parseDouble(rowList.get(15).toString()));
				product.setPartUsageQuantity(qty);}
				if(rowList.get(16) != null && !rowList.get(16).toString().trim().isEmpty()){
				product.setSupplyType(rowList.get(16).toString());}
				if(rowList.get(17) != null && !rowList.get(17).toString().trim().isEmpty()){
				product.setReferenceDesignators(rowList.get(17).toString());}
				if(rowList.get(18) != null && !rowList.get(18).toString().trim().isEmpty()){
				product.setProductImage(rowList.get(18).toString());}
				if(rowList.get(19) != null && !rowList.get(19).toString().trim().isEmpty()){
				product.setPrice((int) (Double.parseDouble(rowList.get(19).toString())));}
				if(rowList.get(20) != null && !rowList.get(20).toString().trim().isEmpty()){
				product.setCurrencyCode(rowList.get(20).toString());}				
				if(rowList.get(21) != null && !rowList.get(21).toString().trim().isEmpty()){
				product.setLeadTime((int) (Double.parseDouble(rowList.get(21).toString())));}
				if(rowList.get(22) != null && !rowList.get(22).toString().trim().isEmpty()){
				product.setUOMLeadTime(rowList.get(22).toString());}
				if(rowList.get(23) != null && !rowList.get(23).toString().trim().isEmpty()){
				product.setFamily(rowList.get(23).toString());}				
				if(rowList.get(24) != null && !rowList.get(24).toString().trim().isEmpty()){
				product.setModel(rowList.get(24).toString());}
				if(rowList.get(25) != null && !rowList.get(25).toString().trim().isEmpty()){
				product.setProductFamily3(rowList.get(25).toString());}
				if(rowList.get(26) != null && !rowList.get(26).toString().trim().isEmpty()){
				product.setSupplier(rowList.get(26).toString());}				
				DateFormat bqSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				Calendar c = Calendar.getInstance();
				c.setTime(new Date());				
				String insertDate = bqSdf.format(c.getTime());
				product.setModifiedTime(insertDate);				
				product.setLevel(0);
				product.setParentID(productFamily);									
                productSetup.add(product);			
			    rowNo++;
		}
		String headers="OrganisationCode, OrganisationName, CompanyCode, CompanyName, PlantCode, PlantName, Level, ProductCode, ProductName, ProductRevisionNumber, PartNumber, PartName, PartRevisionNumber, StatusoftheBOM, Comments, UOMCode, PartUsageQuantity, SupplyType, ReferenceDesignators, ProductImage, Price, CurrencyCode, LeadTime, UOMLeadTime, Family, Model, ProductFamily3, Supplier, ParentID, ModifiedTime,InsertionCount\n";
		StringBuilder strbuilder= new StringBuilder();
		try{
		   insertionCount = datastore.ProductInfoUtil.getProductUploadCountFromDataStore(plantDataSetInstance);
	       for(int i=0;i<table.size();i++)
	       {	        	
		   List<Product> currentplant= new ArrayList<>();		
		   currentplant=productSetup;
		   for (Iterator iterator = currentplant.iterator(); iterator.hasNext();) 
		   {
		    Product productSetUp = (Product) iterator.next();		   
			strbuilder.append(productSetUp.getOrganisationCode()+",");//1				
			strbuilder.append(productSetUp.getOrganisationName()+",");//2			
			strbuilder.append(productSetUp.getCompanyCode()+",");//3			
			strbuilder.append(productSetUp.getCompanyName()+",");//4			
			strbuilder.append(productSetUp.getPlantCode()+",");//5			
			strbuilder.append(productSetUp.getPlantName()+",");//6				
			strbuilder.append((Integer)productSetUp.getLevel()+",");//7					
			strbuilder.append(productSetUp.getProductCode()+",");//8			
			strbuilder.append(productSetUp.getProductName()+",");//9				
			strbuilder.append((Integer)productSetUp.getProductRevisionNumber()+",");//10				
			strbuilder.append(productSetUp.getPartNumber()+",");//11				
			strbuilder.append(productSetUp.getPartName()+",");//12				
			strbuilder.append((Integer)productSetUp.getPartRevisionNumber()+",");//13				
			strbuilder.append(productSetUp.getBOMStatus()+",");//14		
			if(productSetUp.getComments()!=null && !productSetUp.getComments().isEmpty())
			strbuilder.append(productSetUp.getComments().replace(",", " ") +",");//15	
			else				
			strbuilder.append(productSetUp.getComments()+",");//15	
			strbuilder.append(productSetUp.getUOMCode()+",");//16		+
			strbuilder.append((Integer)productSetUp.getPartUsageQuantity()+",");//17				
			strbuilder.append(productSetUp.getSupplyType()+",");//18				
			strbuilder.append(productSetUp.getReferenceDesignators()+",");//19			
			strbuilder.append(productSetUp.getProductImage()+",");//20				
			strbuilder.append((Integer)productSetUp.getPrice()+",");//21				
			strbuilder.append(productSetUp.getCurrencyCode()+",");//22				
			strbuilder.append((Integer)productSetUp.getLeadTime()+",");//23			
			strbuilder.append(productSetUp.getUOMLeadTime()+",");		//24				
			strbuilder.append(productSetUp.getFamily()+",");//25				
			strbuilder.append(productSetUp.getModel()+",");//26					
			strbuilder.append(productSetUp.getProductFamily3() +",");//27			
			strbuilder.append(productSetUp.getSupplier()+",");	//28			
			strbuilder.append(productSetUp.getParentID()+",");//29				
			strbuilder.append(productSetUp.getModifiedTime()+","); //45					
			strbuilder.append((insertionCount+1)+" \n");				
		}	
		if(strbuilder.toString()!=null && !strbuilder.toString().isEmpty()){	
		byte[] channel= (headers+ strbuilder.toString()).getBytes();		
		ByteBuffer buf = ByteBuffer.wrap(channel);	
		GcsFilename file= new GcsFilename("e2escm-gpractice.appspot.com", "Bulk/Productfamily_"+((String)plantDataSetInstance).toString()+".csv");		
		GcsFileOptions.Builder builder= new GcsFileOptions.Builder();
		GcsFileOptions fileoptions=builder.mimeType("application/vnd.ms-excel").build();
		GcsOutputChannel outputChannel;
		try {
			outputChannel = gcsService.createOrReplace(file, fileoptions);
			outputChannel.write(buf);
			outputChannel.close();				
		} catch (Exception e1) 
		{			
			System.out.println("Exception-->"+e1.getMessage());
			e1.printStackTrace();
		}
		}
		else{
			System.out.println("No data found");
		}
	currentplant.clear();
	strbuilder.setLength(0);	
	}
	}
	catch(Exception e){
		System.out.println("Exception-------"+e.getMessage());
	}
	List<Map<String,List<String>>> jobids= new ArrayList<>();
	try{
	    Map<String,List<String>> temp= new HashMap<>();
		temp=new bqloadjob().bqservice("Productfamily_"+((String)plantDataSetInstance).toString()+".csv",plantDataSetInstance,"BOM");
		jobids.add(temp);	
	try {			
		datastore.ProductInfoUtil.putAllProductRequiredDatesIntoDataStore(productRequiredDates);		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();	
		System.out.println("Exception :::::::"+e.getMessage());
	}	
	}catch(Exception e ){
		System.out.println("Exception ::::::::"+e.getMessage());
	}	
		return jobids;	
	}
	


}
