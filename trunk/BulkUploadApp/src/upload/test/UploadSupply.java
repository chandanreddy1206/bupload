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
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

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





/*import com.app.scmProject.client.utils.DateCalculationUtil;
import com.app.scmProject.client.utils.ProductInfoUtil;
import com.app.scmProject.client.utils.ValidationUtil;
import com.app.scmProject.exceptions.CodeNotFoundException;
import com.app.scmProject.exceptions.IllegalFormatException;
import com.app.scmProject.exceptions.NegativeQuantityException;
import com.app.scmProject.server.SCMConstantsServer;
import com.app.scmProject.shared.Product;
import com.app.scmProject.shared.Supply;*/
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.model.QueryRequest;
import com.google.api.services.bigquery.model.QueryResponse;
import com.google.api.services.bigquery.model.TableDataInsertAllRequest;
import com.google.api.services.bigquery.model.TableDataInsertAllRequest.Rows;
import com.google.api.services.bigquery.model.TableDataInsertAllResponse;
import com.google.api.services.bigquery.model.TableRow;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.EntityNotFoundException;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
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

/**
 * The Class UploadSupply process the uploaded supplySetup Excel Sheet and inserts
 * data into Bigquery row by row.
 * 
 * @author Google_COE
 * @version 0.1
 * 
 */
public class UploadSupply extends HttpServlet {

	/** The Constant tUtil. */
	
	/** The parent IDs. */
	private transient Set<String> parentIds = new HashSet<String>();
	private static int insertionCount;
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The Constant LOGGER. */
	private static final Logger LOGGER = Logger.getLogger(UploadSupply.class
			.getCanonicalName());

	/** The Constant TRANSPORT. */
	private static final HttpTransport TRANSPORT = new UrlFetchTransport();

	/** The Constant JSON_FACTORY. */
	private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	/** The uploaded file name. */
	public static String uploadedFileName;
	private static Map<String, String> supplyRequiredDates;
	/** The is upload successful. */
	private boolean isUploadSuccessful = true;
	
	/** The message. */
	private String message = "";

	
	/** The plant instance. */
	private String plantDataSetInstance = null;
	private final GcsService gcsService = GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest
	 * , javax.servlet.http.HttpServletResponse)
	 */
	public List<Map<String, List<String>>> createSupplyData(List<List<Object>> table) 
	{
		List<Supply> firstPlantSet = new ArrayList<Supply>();
		List<Supply> secondPlantSet = new ArrayList<Supply>();
		List<Supply> thirdPlantSet = new ArrayList<Supply>();
		
		supplyRequiredDates = new HashMap<String, String>();
		
	
		String organisationCode = null;
		String companyCode = null;
		String plantCode = null;

		List<Supply> supplyData = new ArrayList<Supply>();
		final Supply supply = new Supply();		
		Iterator<List<Object>> tblItr = table.iterator();
		tblItr = table.iterator();		
		int rowNo = 1;
		List<Rows> BQrowList = new ArrayList<Rows>();
		List<TableRow> supplydata = new ArrayList<TableRow>();
		TableDataInsertAllRequest.Rows rows = null;		
		Map<String, String> parentChildMap = new HashMap<String, String>();
		String dDateSOD=null;		
		final String DATEFORMAT2 = "yyyy-MM-dd HH:mm:ss";
		String dDate = null;		
		String dDateRD=null;
		String key=null;
		final DateFormat to = new SimpleDateFormat(DATEFORMAT2);		
		int j=0;
		while (tblItr.hasNext()) {
			List<Object> rowList = (ArrayList<Object>) tblItr.next();			
			TableRow row = new TableRow();
			int i1 =0;			
			int leadTime = 0;
			Object shipDate = null;
			String supplyType = null;
			Supply supplySetup = new Supply();
			String SuppTypMandatory = null;			
                        if(j>0){	
						//StringBuilder plantId = new StringBuilder();
						/*plantId.append(rowList.get(0).toString())
						.append("-")
						.append(rowList.get(2).toString())
						.append("-")
						.append(rowList.get(4).toString());		*/				
                        key= (String)rowList.get(0)+"_"+rowList.get(2)+"_"+rowList.get(4);
                		//System.out.println("key--->"+key);	
						if(rowList.get(0)!=null && !rowList.get(0).toString().trim().isEmpty())
						supplySetup.setOrganisationCode(rowList.get(0).toString());//1
						
						if(rowList.get(1)!=null && !rowList.get(1).toString().trim().isEmpty())
						supplySetup.setOrganisationName(rowList.get(1).toString());//2
						
						if(rowList.get(2)!=null && !rowList.get(2).toString().trim().isEmpty())
						supplySetup.setCompanyCode(rowList.get(2).toString());//3
						
						if(rowList.get(3)!=null && !rowList.get(3).toString().trim().isEmpty())
						supplySetup.setCompanyName(rowList.get(3).toString());//4
						
						if(rowList.get(4)!=null && !rowList.get(4).toString().trim().isEmpty()){
						supplySetup.setPlantCode(rowList.get(4).toString());//5						
						plantCode =  rowList.get(4).toString();
                        }
						if(rowList.get(5)!=null && !rowList.get(5).toString().trim().isEmpty())
						supplySetup.setPlantName(rowList.get(5).toString());//6
						
						if(rowList.get(6)!=null && !rowList.get(6).toString().trim().isEmpty())
						supplySetup.setSupplyType(rowList.get(6).toString());//7
						
						if(rowList.get(7)!=null && !rowList.get(7).toString().trim().isEmpty())
						supplySetup.setSalesOrderNumber(rowList.get(7).toString());//8
						
						if(rowList.get(8)!=null && !rowList.get(8).toString().trim().isEmpty())
						supplySetup.setSOAmendmentNumber((int)(Double.parseDouble(rowList.get(8).toString())));//9
						
						if(rowList.get(9)!=null && !rowList.get(9).toString().trim().isEmpty()){
							Double xcelDtSOD = (Double) rowList.get(9);
							Date javaDtSOD = DateUtil.getJavaDate(xcelDtSOD);
							dDateSOD = to.format(javaDtSOD);
							supplySetup.setSalesOrderDate(dDateSOD);	
						    supplySetup.setSalesOrderDate(rowList.get(9).toString());//10
						}
					
						if(rowList.get(10)!=null && !rowList.get(10).toString().trim().isEmpty())
						supplySetup.setStatusofSaleOrder(rowList.get(10).toString());//11
						
						if(rowList.get(11)!=null && !rowList.get(11).toString().trim().isEmpty())
						supplySetup.setSOLineNumber((int)(Double.parseDouble(rowList.get(11).toString())));//12
					
						if(rowList.get(12)!=null && !rowList.get(12).toString().trim().isEmpty())
						supplySetup.setPurchaseOrderNumber(rowList.get(12).toString());//13
						
						if(rowList.get(13)!=null && !rowList.get(13).toString().trim().isEmpty())
						supplySetup.setPOAmendmentNumber((int)(Double.parseDouble(rowList.get(13).toString())));//14
						
						if(rowList.get(14)!=null && !rowList.get(14).toString().trim().isEmpty()){
						Double xcelDtSOD = (Double) rowList.get(14);
						Date javaDtSOD = DateUtil.getJavaDate(xcelDtSOD);
						dDateSOD = to.format(javaDtSOD);
						supplySetup.setPurchaseOrderDate(dDateSOD);						
					    }						
						if(rowList.get(15)!=null && !rowList.get(15).toString().trim().isEmpty())
						supplySetup.setStatusofPurchaseOrder(rowList.get(15).toString());//16
						
						if(rowList.get(16)!=null && !rowList.get(16).toString().trim().isEmpty())
						supplySetup.setTradingPartnerCode(rowList.get(16).toString());//17
						
						if(rowList.get(17)!=null && !rowList.get(17).toString().trim().isEmpty())
						supplySetup.setTradingPartnerName(rowList.get(17).toString());//17
						
						if(rowList.get(18)!=null && !rowList.get(18).toString().trim().isEmpty())
						supplySetup.setTypeofTradingPartner(rowList.get(18).toString());//18
						
						if(rowList.get(19)!=null && !rowList.get(19).toString().trim().isEmpty())
						supplySetup.setShipfromAddressLine1(rowList.get(19).toString());//19
						
						if(rowList.get(20)!=null && !rowList.get(20).toString().trim().isEmpty())
						supplySetup.setShipfromAddressLine2(rowList.get(20).toString());//20
						
						if(rowList.get(21)!=null && !rowList.get(21).toString().trim().isEmpty())
						supplySetup.setShipfromCountryCode(rowList.get(21).toString());//21
						
						if(rowList.get(22)!=null && !rowList.get(22).toString().trim().isEmpty())
						supplySetup.setShipfromCityCode(rowList.get(22).toString());//22
						
						if(rowList.get(23)!=null && !rowList.get(23).toString().trim().isEmpty())
						supplySetup.setTPZipcode(rowList.get(23).toString());		//23
						
						if(rowList.get(24)!=null && !rowList.get(24).toString().trim().isEmpty())
						supplySetup.setpOLineNumber((int)(Double.parseDouble(rowList.get(24).toString())));//24
						
						if(rowList.get(25)!=null && !rowList.get(25).toString().trim().isEmpty())
						supplySetup.setPartNumber(rowList.get(25).toString());//25
						
						if(rowList.get(26)!=null && !rowList.get(26).toString().trim().isEmpty())
						supplySetup.setPartName(rowList.get(26).toString());//26
							
						if(rowList.get(27)!=null && !rowList.get(27).toString().trim().isEmpty())
						supplySetup.setPartRevisionNumber((int)(Double.parseDouble(rowList.get(27).toString())));	//27	
					
						if(rowList.get(28)!=null && !rowList.get(28).toString().trim().isEmpty())
						supplySetup.setQuantity(Double.parseDouble(rowList.get(28).toString()));//28
						
						if(rowList.get(29)!=null && !rowList.get(29).toString().trim().isEmpty())
						supplySetup.setUOMCode(rowList.get(29).toString());//29
						
						if(rowList.get(30)!=null && !rowList.get(30).toString().trim().isEmpty()){
						supplySetup.setPrice((int)(Double.parseDouble(rowList.get(30).toString())));//30
						}
						
						if(rowList.get(31)!=null && !rowList.get(31).toString().trim().isEmpty())
						supplySetup.setCurrencyCode(rowList.get(31).toString());//31
							
						if(rowList.get(32)!=null && !rowList.get(32).toString().trim().isEmpty()){						
							Double xcelDtSOD = (Double) rowList.get(32);
							Date javaDtSOD = DateUtil.getJavaDate(xcelDtSOD);
							dDateSOD = to.format(javaDtSOD);
							supplySetup.setPOScheduleDate(dDateSOD);
						}							
					
						if(rowList.get(33)!=null && !rowList.get(33).toString().trim().isEmpty()){
							
							Double xcelDtRD = (Double) rowList.get(33);
							Date javaDtRD = DateUtil.getJavaDate(xcelDtRD);
							dDateRD = to.format(javaDtRD);
							supplySetup.setSupplyDate(dDateRD);							
							String reqDate = new SimpleDateFormat("MMM").format(javaDtRD.getTime())+"-"+
									new SimpleDateFormat("YYYY").format(javaDtRD.getTime());
						
							supplyRequiredDates.put(reqDate.toString(), reqDate.toString());
						}						
						if(rowList.get(34)!=null && !rowList.get(34).toString().trim().isEmpty())
						supplySetup.setBusinessUnitCode(rowList.get(34).toString());//34
						
						if(rowList.get(35)!=null && !rowList.get(35).toString().trim().isEmpty())
						supplySetup.setModeOfShipment(rowList.get(35).toString());//35
							
						if(rowList.get(36)!=null && !rowList.get(36).toString().trim().isEmpty()){
						
						Double xcelDtSOD = (Double) rowList.get(36);
						Date javaDtSOD = DateUtil.getJavaDate(xcelDtSOD);
						dDateSOD = to.format(javaDtSOD);
						supplySetup.setShipDate(dDateSOD);
						}
						
						if(rowList.get(37)!=null && !rowList.get(37).toString().trim().isEmpty())							
						supplySetup.setLeadTime((int)(Double.parseDouble(rowList.get(37).toString())));//37						
							
						if(rowList.get(38)!=null && !rowList.get(38).toString().trim().isEmpty()){							
						supplySetup.setUOMForLeadtime(rowList.get(38).toString());	//38						
						}
						supplySetup.setPlantID(key);											
						supplySetup.setHeadParentId("NA");//40		
						supplySetup.setParentID("NA");//41								
						supplySetup.setMode(rowList.get(38).toString());//42	
						DateFormat bqSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						Calendar c = Calendar.getInstance();
						c.setTime(new Date());
						String insertDate = bqSdf.format(c.getTime());
						supplySetup.setInsertDate(insertDate);																			
						supplySetup.setModifiedTime(insertDate); //44								
						
						if(plantCode.equalsIgnoreCase("SUNN"))
						{
							firstPlantSet.add(supplySetup);			
							
						}else if(plantCode.equalsIgnoreCase("ARSG"))
						{
							secondPlantSet.add(supplySetup);
						}else if(plantCode.equalsIgnoreCase("AREU"))
						{
							thirdPlantSet.add(supplySetup);
						}else
						{
							System.out.println("SOME OTHER PLANT CODE : "+plantCode);
						}					
						supplyData.add(supplySetup);					
						rowNo++;
						
		}else{
			j++;
		}
		}
		String headers="OrganisationCode, OrganisationName, CompanyCode, CompanyName, PlantCode, PlantName, SupplyType, SalesOrderNumber, SOAmendmentNumber, SalesOrderDate, StatusofSaleOrder, SOLineNumber, PurchaseOrderNumber, POAmendmentNumber, PurchaseOrderDate, StatusofPurchaseOrder, TradingPartnerCode, TradingPartnerName, TypeofTradingPartner, ShifFromAddressLine1, ShipFromAddressLine2, ShipFromCountryCode, ShipFromCityCode, TPZipcode, POLineNumber, PartNumber, PartName, PartRevisionNumber, Quantity, UOMCode, Price, CurrencyCode, POScheduleDate, SupplyDate, BusinessUnitCode, ModeOfShipment, ShipDate, LeadTime, UOMForLeadtime, PlantID, HeadParentId, ParentId, InsertDate, Mode, ModifiedTime,InsertionCount\n";
		StringBuilder strbuilder= new StringBuilder();
		
		
		List<PlantsDataVo> plantsDataList = new ArrayList<PlantsDataVo>();
		
		try{
	         for(int i=0;i<3;i++)
	        {	        	 
		    List<Supply> currentplant= new ArrayList<>();
		    if(i==0){		
			
			currentplant=firstPlantSet;			
			Supply currentPlantDS = currentplant.get(0);	
			PlantsDataVo plantBean = new PlantsDataVo();
			plantBean.setPlantCode(currentPlantDS.getPlantCode());			
			plantBean.setOrgCode(currentPlantDS.getOrganisationCode());
			plantBean.setComCode(currentPlantDS.getCompanyCode());
			plantBean.setIndexNumber(0);
			plantBean.setType("Supply");
			plantDataSetInstance = "E2ESCM_"+plantBean.getOrgCode()+"_"+plantBean.getComCode()+"_"+plantBean.getPlantCode();
			plantsDataList.add(plantBean);
			}else if(i==1)
		    {
			currentplant=secondPlantSet;
			Supply currentPlantDS = currentplant.get(0);	
			PlantsDataVo plantBean = new PlantsDataVo();
			plantBean.setPlantCode(currentPlantDS.getPlantCode());			
			plantBean.setOrgCode(currentPlantDS.getOrganisationCode());
			plantBean.setComCode(currentPlantDS.getCompanyCode());
			plantBean.setIndexNumber(1);
			plantBean.setType("Supply");
			plantDataSetInstance = "E2ESCM_"+plantBean.getOrgCode()+"_"+plantBean.getComCode()+"_"+plantBean.getPlantCode();
			plantsDataList.add(plantBean);
		    }
		    else
		    {
			currentplant=thirdPlantSet;
			Supply currentPlantDS = currentplant.get(0);
			PlantsDataVo plantBean = new PlantsDataVo();
			plantBean.setPlantCode(currentPlantDS.getPlantCode());			
			plantBean.setOrgCode(currentPlantDS.getOrganisationCode());
			plantBean.setComCode(currentPlantDS.getCompanyCode());
			plantBean.setIndexNumber(2);
			plantBean.setType("Supply");
			plantDataSetInstance = "E2ESCM_"+plantBean.getOrgCode()+"_"+plantBean.getComCode()+"_"+plantBean.getPlantCode();
			plantsDataList.add(plantBean);
		    }		  
		   for (Iterator iterator = currentplant.iterator(); iterator.hasNext();) 
		   {
			Supply supplySetUp = (Supply) iterator.next();			
			strbuilder.append(supplySetUp.getOrganisationCode()+",");//1			
			strbuilder.append(supplySetUp.getOrganisationName()+",");//2		
			strbuilder.append(supplySetUp.getCompanyCode()+",");//3			
			strbuilder.append(supplySetUp.getCompanyName()+",");//4		
			strbuilder.append(supplySetUp.getPlantCode()+",");//5		
			strbuilder.append(supplySetUp.getPlantName()+",");//6		
			strbuilder.append(supplySetUp.getSupplyType()+",");//7			
			strbuilder.append(supplySetUp.getSalesOrderNumber()+",");//8			
			strbuilder.append(((Integer)supplySetUp.getSOAmendmentNumber())+",");//9			
			strbuilder.append(supplySetUp.getSalesOrderDate()+",");//10			
			strbuilder.append(supplySetUp.getStatusofSaleOrder()+",");//11			
			strbuilder.append((Integer)supplySetUp.getSOLineNumber()+",");//12			
			strbuilder.append(supplySetUp.getPurchaseOrderNumber()+",");//13			
			strbuilder.append(((Integer)supplySetUp.getPOAmendmentNumber())+",");//14			
			strbuilder.append(supplySetUp.getPurchaseOrderDate()+",");//15			
			strbuilder.append(supplySetUp.getStatusofPurchaseOrder()+",");//16			
			strbuilder.append(supplySetUp.getTradingPartnerCode()+",");//17			
			strbuilder.append(supplySetUp.getTradingPartnerName()+",");//18			
			strbuilder.append(supplySetUp.getTypeofTradingPartner()+",");//19	
			if(supplySetUp.getShipfromAddressLine1()!=null && !supplySetUp.getShipfromAddressLine1().isEmpty())
			strbuilder.append(supplySetUp.getShipfromAddressLine1().replace(",", " ") +",");	
			else
			strbuilder.append(supplySetUp.getShipfromAddressLine1()+",");	
			if(supplySetUp.getShipfromAddressLine2()!=null && !supplySetUp.getShipfromAddressLine2().isEmpty())
			strbuilder.append(supplySetUp.getShipfromAddressLine2().replace(",", " ") +",");		
			else				
			strbuilder.append(supplySetUp.getShipfromAddressLine2()+",");
			strbuilder.append(supplySetUp.getShipfromCountryCode()+",");//22			
			strbuilder.append(supplySetUp.getShipfromCityCode()+",");//23			
			strbuilder.append(supplySetUp.getTPZipcode()+",");		//24			
			strbuilder.append((Integer)supplySetUp.getpOLineNumber()+",");//25			
			strbuilder.append(supplySetUp.getPartNumber()+",");//26			
			strbuilder.append(supplySetUp.getPartName()+",");//27			
			strbuilder.append(((Integer)supplySetUp.getPartRevisionNumber())+",");	//28					
			strbuilder.append(((Double)supplySetUp.getQuantity()).intValue()+",");//29				
			strbuilder.append(supplySetUp.getUOMCode()+",");//30			
			strbuilder.append(((Integer)supplySetUp.getPrice())+",");//31			
			strbuilder.append(supplySetUp.getCurrencyCode()+",");//32			
			strbuilder.append(supplySetUp.getPOScheduleDate()+",");//33	
			strbuilder.append(supplySetUp.getSupplyDate()+",");	//34				
			strbuilder.append(supplySetUp.getBusinessUnitCode()+",");//35				
			strbuilder.append(supplySetUp.getModeOfShipment()+",");//36			
			strbuilder.append(supplySetUp.getShipDate()+",");//37			
			strbuilder.append((Integer)supplySetUp.getLeadTime()+",");//38			
			strbuilder.append(supplySetUp.getUOMForLeadtime()+",");	//39				
			strbuilder.append(supplySetUp.getPlantID()+",");//40			
			strbuilder.append(supplySetUp.getHeadParentId()+",");//41			
			strbuilder.append(supplySetUp.getParentID()+",");//42			
			strbuilder.append(supplySetUp.getInsertDate()+",");//43			
			strbuilder.append(supplySetUp.getMode()+",");//44			
			strbuilder.append(supplySetUp.getModifiedTime()+","); //45			
			insertionCount = datastore.ProductInfoUtil.getSupplyUploadCountFromDataStore(plantDataSetInstance);
			System.out.println("insertionCount------->"+insertionCount);
			strbuilder.append((insertionCount+1)+" \n");				
			
		}
		try {		
				datastore.ProductInfoUtil.putSupplyUploadCountIntoDataStore(insertionCount,plantDataSetInstance);
		} catch (IOException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		byte[] channel= (headers+ strbuilder.toString()).getBytes();
		
		ByteBuffer buf = ByteBuffer.wrap(channel);
		GcsFilename file= new GcsFilename("e2escm-gpractice.appspot.com", "Bulk/supply"+((Integer)i).toString()+".csv");		
		
		GcsFileOptions.Builder builder= new GcsFileOptions.Builder();
		GcsFileOptions fileoptions=builder.mimeType("application/vnd.ms-excel").build();
		GcsOutputChannel outputChannel;
		try {
			outputChannel = gcsService.createOrReplace(file, fileoptions);
			outputChannel.write(buf);
			outputChannel.close();		
			System.out.println("Start the upload JOBBBBB");			
			System.out.println("Done");
		} catch (Exception e1) 
		{
			// TODO Auto-generated catch block			
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		
	currentplant.clear();
	strbuilder.setLength(0);
	
	}
		}
	catch(Exception e){
		System.out.println("Exception-------------"+e.getMessage());
	}

	Iterator<PlantsDataVo> plantlistIt = plantsDataList.iterator();
	int i = 0;	
	List<Map<String,List<String>>> jobids= new ArrayList<>();
	try{
	while(plantlistIt.hasNext())
		{		
		    Map<String,List<String>> temp= new HashMap<>();
			temp=new bqloadjob().bqservice("supply"+((Integer)i).toString()+".csv",plantlistIt.next());
			i++;
			jobids.add(temp);
		}	
	try {		
		datastore.ProductInfoUtil.putAllSupplyRequiredDatesIntoDataStore(supplyRequiredDates);		
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

