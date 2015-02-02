package upload.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
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
import org.datanucleus.util.MultiMap;

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
import com.google.common.collect.LinkedListMultimap;

import datastore.TableStandardizationUtil;

/**
 * The Class UploadDemand process the uploaded Demand Excel Sheet and inserts
 * data into Bigquery row by row.
 * 
 * @author Google_COE
 * @version 0.1
 * 
 */
public class UploadDemand extends HttpServlet {


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
	static TableStandardizationUtil tUtil = new TableStandardizationUtil();
	/** The demand_ types. */
	private static Set<String> demandTypes = new HashSet<String>();
	
	private final GcsService gcsService = GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());

	
	/** The plant instance. */
	//private String plantDataSetInstance = null;

	public List<Map<String, List<String>>> createDemandData(List<List<Object>> table,String plantDataSetInstance,int insertionCount) 
	{
		 final String DATEFORMAT2 = "yyyy-MM-dd HH:mm:ss";
		String dDate = null;
		String dDateSOD=null;
		String dDateRD=null;
		final DateFormat to = new SimpleDateFormat(DATEFORMAT2);
		Iterator<List<Object>> tblItr = table.iterator();
		List<DemandSetUp> demandData = new ArrayList<DemandSetUp>();
		DateFormat bqSdf = new SimpleDateFormat(DATEFORMAT2);		
		Calendar c = Calendar.getInstance();
		 String key= "";
		c.setTime(new Date());
		String insertDate = bqSdf.format(c.getTime());
		String plantCode ="";		
		int rowNo=0;	
		Map<String, String> finalDemandRequiredDates;
		StringBuilder plantId = new StringBuilder();
		int index=0;
		//List<PlantsDataVo> plantsDataList = new ArrayList<PlantsDataVo>();
		demandRequiredDates = new HashMap<String, String>();		
		while (tblItr.hasNext())
		{
			List<Object> rowList = (ArrayList<Object>) tblItr.next();			
			DemandSetUp demandSetUp = new DemandSetUp();				
			if(rowList.get(4)!=null && !rowList.get(4).toString().trim().isEmpty()){
			plantCode =  rowList.get(4).toString();								
			demandSetUp.setPlant_Code(rowList.get(4).toString());}
			key= (String)rowList.get(0)+"_"+rowList.get(2)+"_"+rowList.get(4);				
			demandSetUp.setPlant_Id(key);		
			if(rowList.get(5)!=null && !rowList.get(5).toString().trim().isEmpty()){
			demandSetUp.setPlant_Name(rowList.get(5).toString());}
			if(rowList.get(6)!=null && !rowList.get(6).toString().trim().isEmpty()){
			demandSetUp.setDemandType(rowList.get(6).toString());}
			demandSetUp.setOwnerName("Tom Bayer");
			if(rowList.get(8)!=null && !rowList.get(8).toString().trim().isEmpty()){
			demandSetUp.setSale_Order_Number(rowList.get(8).toString());}
			if(rowList.get(9)!=null && !rowList.get(9).toString().trim().isEmpty()){
			demandSetUp.setSo_Amendment_Number((int)(Double.parseDouble(rowList.get(9).toString())));}
			if(rowList.get(10)!=null && !rowList.get(10).toString().trim().isEmpty()){
			Double xcelDtSOD = (Double) rowList.get(10);
			Date javaDtSOD = DateUtil.getJavaDate(xcelDtSOD);
			dDateSOD = to.format(javaDtSOD);
			demandSetUp.setSales_Order_Date(dDateSOD);}			
			if(rowList.get(11)!=null && !rowList.get(11).toString().trim().isEmpty()){
			demandSetUp.setStatus_of_Sale_Order(rowList.get(11).toString());}
			if(rowList.get(12)!=null && !rowList.get(12).toString().trim().isEmpty()){
			demandSetUp.setCustomer_Code(rowList.get(12).toString());}
			if(rowList.get(13)!=null && !rowList.get(13).toString().trim().isEmpty()){
			demandSetUp.setCustomer_Name(rowList.get(13).toString());}
			if(rowList.get(14)!=null && !rowList.get(14).toString().trim().isEmpty()){
			demandSetUp.setCustomer_Ship_Address1(rowList.get(14).toString());}
			if(rowList.get(15)!=null && !rowList.get(15).toString().trim().isEmpty()){
			demandSetUp.setCustomer_Ship_Address2(rowList.get(15).toString());}
			if(rowList.get(16)!=null && !rowList.get(16).toString().trim().isEmpty()){
			demandSetUp.setCustomer_Ship_CountryCode(rowList.get(16).toString());}
			if(rowList.get(17)!=null && !rowList.get(17).toString().trim().isEmpty()){
			demandSetUp.setCustomer_Ship_City_Code(rowList.get(17).toString());}
			if(rowList.get(18)!=null && !rowList.get(18).toString().trim().isEmpty()){
			demandSetUp.setCustomer_Zipcode(rowList.get(18).toString());}
			if(rowList.get(19)!=null && !rowList.get(19).toString().trim().isEmpty()){
			demandSetUp.setSo_Line_Number((int)(Double.parseDouble( rowList.get(19).toString())));}
			if(rowList.get(20)!=null && !rowList.get(20).toString().trim().isEmpty()){
			demandSetUp.setPart_Number(rowList.get(20).toString());}
			if(rowList.get(21)!=null && !rowList.get(21).toString().trim().isEmpty()){
			demandSetUp.setPart_Name(rowList.get(21).toString());}
			if(rowList.get(22)!=null && !rowList.get(22).toString().trim().isEmpty()){
			demandSetUp.setPart_Revision_Number((int)(Double.parseDouble( rowList.get(22).toString())));}
			if(rowList.get(23)!=null && !rowList.get(23).toString().trim().isEmpty()){
			demandSetUp.setQuantity(Double.parseDouble(rowList.get(23).toString()));}
			demandSetUp.setOwnerId("2");
			demandSetUp.setCountry("USA");
			demandSetUp.setMode("Refresh");
			if(rowList.get(24)!=null && !rowList.get(24).toString().trim().isEmpty()){
			demandSetUp.setUom_Code(rowList.get(24).toString());}
			if(rowList.get(25)!=null && !rowList.get(25).toString().trim().isEmpty()){
			demandSetUp.setPrice((int)(Double.parseDouble(rowList.get(25).toString())));}
			if(rowList.get(26)!=null && !rowList.get(26).toString().trim().isEmpty()){
			demandSetUp.setCurrency_Code(rowList.get(26).toString());}			
			if(rowList.get(27)!=null && !rowList.get(27).toString().trim().isEmpty()){
			Double xcelDtRD = (Double) rowList.get(27);
			Date javaDtRD = DateUtil.getJavaDate(xcelDtRD);
			dDateRD = to.format(javaDtRD);
			demandSetUp.setRequired_Date(dDateRD);			
			String reqDate = new SimpleDateFormat("MMM").format(javaDtRD.getTime())+"-"+
					new SimpleDateFormat("YYYY").format(javaDtRD.getTime());		
			demandRequiredDates.put(reqDate.toString(), reqDate.toString());
			}		
			
			if(rowList.get(28)!=null && !rowList.get(28).toString().trim().isEmpty()){
			demandSetUp.setBusiness_Unit_Code(rowList.get(28).toString());}
			if(rowList.get(0)!=null && !rowList.get(0).toString().trim().isEmpty()){
			demandSetUp.setOrganisationCode(rowList.get(0).toString());}
		    if(rowList.get(2)!=null && !rowList.get(2).toString().trim().isEmpty()){
			demandSetUp.setCompanyCode(rowList.get(2).toString());}
			demandSetUp.setMode("Refresh");			
			if(rowList.get(7)!=null && !rowList.get(7).toString().trim().isEmpty()){
				demandSetUp.setOwnerName(rowList.get(7).toString());
			} else {
				demandSetUp.setOwnerName("Tom Bayer");
			}
			if(rowList.get(20)!=null && !rowList.get(20).toString().trim().isEmpty()){
			String tempPartID = rowList.get(20).toString();		
			}			
			demandSetUp.setInsertionDate(insertDate);
			demandSetUp.setModifiedTime(String.valueOf(System.currentTimeMillis()));					
			demandData.add(demandSetUp);		
			rowNo++;		
		}			
		String headers="PlantCode,PlantName,DemandType,DemandOwner,SaleOrderNumber,SOAmendmentNumber,SalesOrderDate,StatusofSaleOrder,CustomerCode,CustomerName,CustomerShiptoAddressLine1,CustomerShiptoAddressLine2,CustomerShiptoCountryCode,CustomerShiptoCityCode,CustomerZipcode,SOLineNumber,PartNumber,PartName,PartRevisionNumber,Quantity,UOMCode,Price,CurrencyCode,RequiredDate,BusinessUnitCode,PlantID,Mode,ParentID,Date,InsertionDate,HeadParentID,OrganisationCode,CompanyCode,ModifiedTime,InsertionCount\n";
		StringBuilder strbuilder= new StringBuilder();				
		try{
		insertionCount = datastore.ProductInfoUtil.getDemandUploadCountFromDataStore(plantDataSetInstance);
		for(int i=0;i<table.size();i++)
		{
		List<DemandSetUp> currentplant= new ArrayList<>();
		currentplant=demandData;		
		for (Iterator iterator = currentplant.iterator(); iterator.hasNext();) 
		{ 			
			DemandSetUp demandSetUp = (DemandSetUp) iterator.next();				
			strbuilder.append(demandSetUp.getPlant_Code()+",");		
			strbuilder.append(demandSetUp.getPlant_Name()+",");			
			strbuilder.append(demandSetUp.getDemandType()+",");			
			strbuilder.append("Tom Bayer"+",");		 /// Demand Owner			
			strbuilder.append(demandSetUp.getSale_Order_Number()+",");			
			strbuilder.append(((Integer)demandSetUp.getSo_Amendment_Number())+",");			
			strbuilder.append(new Date().toString()+",");   // Sales order date			
			strbuilder.append(demandSetUp.getStatus_of_Sale_Order()+",");			
			strbuilder.append(demandSetUp.getCustomer_Code()+",");		
			strbuilder.append(demandSetUp.getCustomer_Name()+",");		
			if(demandSetUp.getCustomer_Ship_Address1()!=null && !demandSetUp.getCustomer_Ship_Address1().isEmpty())
			strbuilder.append(demandSetUp.getCustomer_Ship_Address1().replace(",", " ") +",");	
			else
			strbuilder.append(demandSetUp.getCustomer_Ship_Address1()+",");	
			if(demandSetUp.getCustomer_Ship_Address2()!=null && !demandSetUp.getCustomer_Ship_Address2().isEmpty())
			strbuilder.append(demandSetUp.getCustomer_Ship_Address2().replace(",", " ")+",");			
			else				
		    strbuilder.append(demandSetUp.getCustomer_Ship_Address2()+",");					
			strbuilder.append(demandSetUp.getCustomer_Ship_CountryCode()+",");			
			strbuilder.append(demandSetUp.getCustomer_Ship_City_Code()+",");			
			strbuilder.append(demandSetUp.getCustomer_Zipcode()+",");			
			strbuilder.append(((Integer)demandSetUp.getSo_Line_Number())+",");			
			strbuilder.append(demandSetUp.getPart_Number()+",");			
			strbuilder.append(demandSetUp.getPart_Name()+",");			
			strbuilder.append(((Integer)demandSetUp.getPart_Revision_Number())+",");
			strbuilder.append(((Double)demandSetUp.getQuantity()).intValue()+",");
			strbuilder.append(demandSetUp.getUom_Code()+",");			
			strbuilder.append(((Integer)demandSetUp.getPrice())+",");				
			strbuilder.append(demandSetUp.getCurrency_Code()+",");			
			strbuilder.append(demandSetUp.getRequired_Date()+",");
			strbuilder.append(demandSetUp.getBusiness_Unit_Code()+",");			
			strbuilder.append(demandSetUp.getPlant_Id()+",");			
			strbuilder.append(demandSetUp.getMode()+",");			
			strbuilder.append(demandSetUp.getParentID()+",");			
			strbuilder.append(new Date().toString()+","); // get date
			strbuilder.append(demandSetUp.getInsertionDate()+","); // insertion date
			strbuilder.append("HeadParent"+",");
			strbuilder.append(demandSetUp.getOrganisationCode()+",");		
			strbuilder.append(demandSetUp.getCompanyCode()+",");		
			strbuilder.append(demandSetUp.getModifiedTime()+","); // insertion date				
			strbuilder.append((insertionCount+1)+" \n"); // insertion date					
		}	 	
		if(strbuilder.toString()!=null && !strbuilder.toString().isEmpty()){		
		byte[] channel= (headers+ strbuilder.toString()).getBytes();		
		ByteBuffer buf = ByteBuffer.wrap(channel);
		GcsFilename file= new GcsFilename("e2escm-gpractice.appspot.com", "Bulk/Demand_"+(String)plantDataSetInstance.toString()+".csv");
		GcsFileOptions.Builder builder= new GcsFileOptions.Builder();
		GcsFileOptions fileoptions=builder.mimeType("application/vnd.ms-excel").build();
		GcsOutputChannel outputChannel;
		try {
			outputChannel = gcsService.createOrReplace(file, fileoptions);
			outputChannel.write(buf);
			outputChannel.close();			
		} catch (Exception e1) 
		{
			System.out.println("msg-->"+e1.getMessage());
			e1.printStackTrace();
		}
		}else{
			System.out.println("No data found");
		}
		
	currentplant.clear();
	strbuilder.setLength(0);	
	   }	
		}catch(Exception e){
			System.out.println("Exception-------------"+e.getMessage());
		}
	//Iterator<PlantsDataVo> plantlistIt = plantsDataList.iterator();
	int i = 0;	
	List<Map<String,List<String>>> jobids= new ArrayList<>();
	 try{
		Map<String,List<String>> temp= new HashMap<>();
		temp=new bqloadjob().bqservice("Demand_"+((String)plantDataSetInstance).toString()+".csv",plantDataSetInstance);
		jobids.add(temp);
		 try {		
			datastore.ProductInfoUtil.putAllRequiredDatesIntoDataStore(demandRequiredDates);
		 } catch (IOException e) {
			   // TODO Auto-generated catch block
			   e.printStackTrace();
		}
	 }catch(Exception e ){
			System.out.println("Exception :::::::::::"+e.getMessage());
	 }		 
	
		return jobids;	
	}	
}
