package upload.test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.util.IOUtils;

import com.google.api.client.googleapis.auth.clientlogin.ClientLogin.Response;
import com.google.appengine.tools.cloudstorage.GcsFileOptions;
import com.google.appengine.tools.cloudstorage.GcsFilename;
import com.google.appengine.tools.cloudstorage.GcsOutputChannel;
import com.google.appengine.tools.cloudstorage.GcsService;
import com.google.appengine.tools.cloudstorage.GcsServiceFactory;
import com.google.appengine.tools.cloudstorage.RetryParams;

@SuppressWarnings("serial")
public class GcssppengineServlet extends HttpServlet {

	private static Map<String, String> demandRequiredDates;
	private final GcsService gcsService = GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 
			
			
	{
		
		
		//new bqloadjob().bqservice("bulkdatafile.csv");

		
		List<Map<String, List<String>>> jobids= new ArrayList<>();
		FileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(
                     fileItemFactory);
        
        try 
        {
            FileItemIterator fileItemIterator = servletFileUpload.getItemIterator(req);
            while (fileItemIterator.hasNext())
            {
                  FileItemStream fileItemStream = (FileItemStream) fileItemIterator.next();
            
                  InputStream inputStream = fileItemStream.openStream();
                  InputStream stream = new ByteArrayInputStream(IOUtils.toByteArray(inputStream));
                  
                  List<List<Object>> table = ExcelParsingUtil.readExcellData(stream, 29);
                  
                  jobids=  createDemandData(table);
            
            }
        }
   
        catch( Exception e)
            {
            	
            }
        
        Integer i=1;  
    	 resp.addIntHeader("number", jobids.size());


    	 PrintWriter out= resp.getWriter();
    	 
    	 for(Map<String, List<String>> s:jobids) 
        {Iterator it= s.entrySet().iterator();
      	  
         while(it.hasNext())
         {Map.Entry pairs= (Map.Entry)it.next();
          resp.addHeader(pairs.getKey().toString(), ((List)pairs.getValue()).get(0).toString());
          out.println(pairs.getKey().toString()+"   " +((List)pairs.getValue()).get(0).toString());
         }
        	resp.sendRedirect("Result_page.html");
         
        }
	}
	
	private List<Map<String, List<String>>> createDemandData(List<List<Object>> table) 
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
		
		c.setTime(new Date());
		String insertDate = bqSdf.format(c.getTime());
		String plantCode ="";
		tblItr = table.iterator();
		List<Object> header = tblItr.next();	
		int rowNo=0;
		
		List<DemandSetUp> firstPlantSet = new ArrayList<DemandSetUp>();
		List<DemandSetUp> secondPlantSet = new ArrayList<DemandSetUp>();
		List<DemandSetUp> thirdPlantSet = new ArrayList<DemandSetUp>();
		
		
		
		Map<String, String> finalDemandRequiredDates;

		demandRequiredDates = new HashMap<String, String>();
	
		
		
		while (tblItr.hasNext())
		{
			List<Object> rowList = (ArrayList<Object>) tblItr.next();

			//checkRequired(rowList, rowNo);
			//validateDemandData(rowList, rowNo);
			//to validate Organisation, Company, Plant codes.
			List<Object> list = new ArrayList<Object>();
			list.add(0, rowList.get(0).toString());//OrganisationCode
			list.add(1, rowList.get(2).toString());//CompanyCode
			list.add(2, rowList.get(4).toString());//PlantCode
			//ValidationUtil.validateUserData(list, rowNo);
			
			DemandSetUp demandSetUp = new DemandSetUp();
			
			
			
			if(rowList.get(4)!=null && !rowList.get(4).toString().trim().isEmpty()){
				plantCode =  rowList.get(4).toString();
				
				
				demandSetUp.setPlant_Code(rowList.get(4).toString());}
			
			//PlantID: OrganizationCode+CompanyCode+PlantCode
			
			StringBuilder plantId = new StringBuilder();
			plantId.append(rowList.get(0).toString())
			.append("-")
			.append(rowList.get(2).toString())
			.append("-")
			.append(rowList.get(4).toString());
			
			demandSetUp.setPlant_Id(plantId.toString());
		
			if(rowList.get(5)!=null && !rowList.get(5).toString().trim().isEmpty()){
			demandSetUp.setPlant_Name(rowList.get(5).toString());}
			if(rowList.get(6)!=null && !rowList.get(6).toString().trim().isEmpty()){
			demandSetUp.setDemandType(rowList.get(6).toString());}
			demandSetUp.setOwnerName("Aravinda");
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
			
			demandSetUp.setOwnerId("1011");
			demandSetUp.setCountry("South Africa");
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
		//System.out.println("demandRequiredDates::::"+reqDate);
			demandRequiredDates.put(reqDate.toString(), reqDate.toString());
			}
			
			
			if(rowList.get(28)!=null && !rowList.get(28).toString().trim().isEmpty()){
			demandSetUp.setBusiness_Unit_Code(rowList.get(28).toString());}
			if(rowList.get(0)!=null && !rowList.get(0).toString().trim().isEmpty()){
			////System.out.println("OrganisationCode:"+rowList.get(0));
			demandSetUp.setOrganisationCode(rowList.get(0).toString());}
			if(rowList.get(2)!=null && !rowList.get(2).toString().trim().isEmpty()){
			////System.out.println("CompanyCode:"+rowList.get(2));
			demandSetUp.setCompanyCode(rowList.get(2).toString());}
			demandSetUp.setMode("Refresh");
			
			if(rowList.get(7)!=null && !rowList.get(7).toString().trim().isEmpty()){
				demandSetUp.setOwnerName(rowList.get(7).toString());
			} else {
				demandSetUp.setOwnerName("Aravinda");
			}
			if(rowList.get(20)!=null && !rowList.get(20).toString().trim().isEmpty()){
			String tempPartID = rowList.get(20).toString();
			//////System.out.println("parentChildMap ######## :"+parentChildMap +" ##### tempPartID :"+tempPartID);
			/*while (parentChildMap.containsKey(tempPartID)) {
				if (!parentChildMap.get(tempPartID).equals("P0")) {
					tempPartID = parentChildMap.get(tempPartID);
				} else {
					parentIds.add(tempPartID);
					break;
				}
			}*/
			//parentIds.add(tempPartID);
	
			}
			//////System.out.println("parentIds %%%%%%%%%%%%%%%% :"+parentIds);
			// inserting the insertion date
			demandSetUp.setInsertionDate(insertDate);
			demandSetUp.setModifiedTime(String.valueOf(System.currentTimeMillis()));
			
			if(plantCode.equalsIgnoreCase("SUNN"))
			{
				firstPlantSet.add(demandSetUp);
	
				
				
			}else if(plantCode.equalsIgnoreCase("ARSG"))
			{
				secondPlantSet.add(demandSetUp);
			}else if(plantCode.equalsIgnoreCase("AREU"))
			{
				thirdPlantSet.add(demandSetUp);
			}else
			{
				System.out.println("SOME OTHER PLANT CODE : "+plantCode);
			}
			demandData.add(demandSetUp);
			
			rowNo++;
			
		}

		String headers="PlantCode,PlantName,DemandType,DemandOwner,SaleOrderNumber,SOAmendmentNumber,SalesOrderDate,StatusofSaleOrder,CustomerCode,CustomerName,CustomerShiptoAddressLine1,CustomerShiptoAddressLine2,CustomerShiptoCountryCode,CustomerShiptoCityCode,CustomerZipcode,SOLineNumber,PartNumber,PartName,PartRevisionNumber,Quantity,UOMCode,Price,CurrencyCode,RequiredDate,BusinessUnitCode,PlantID,Mode,ParentID,Date,InsertionDate,HeadParentID,OrganisationCode,CompanyCode,ModifiedTime\n";
		StringBuilder strbuilder= new StringBuilder();
		
		
		List<PlantsDataVo> plantsDataList = new ArrayList<PlantsDataVo>();
	for(int i=0;i<3;i++)
	{
		List<DemandSetUp> currentplant= new ArrayList<>();
		if(i==0)
		{
						
			
			currentplant=firstPlantSet;
			
			DemandSetUp currentPlantDS = currentplant.get(0);
			PlantsDataVo plantBean = new PlantsDataVo();
			plantBean.setPlantCode(currentPlantDS.getPlant_Code());
			plantBean.setOrgCode(currentPlantDS.getOrganisationCode());
			plantBean.setComCode(currentPlantDS.getCompanyCode());
			plantBean.setIndexNumber(0);
			plantsDataList.add(plantBean);
		
		}
		else if(i==1)
		{
			currentplant=secondPlantSet;
			DemandSetUp currentPlantDS = currentplant.get(0);
			PlantsDataVo plantBean = new PlantsDataVo();
			plantBean.setPlantCode(currentPlantDS.getPlant_Code());
			plantBean.setOrgCode(currentPlantDS.getOrganisationCode());
			plantBean.setComCode(currentPlantDS.getCompanyCode());
			plantBean.setIndexNumber(1);
			plantsDataList.add(plantBean);
		}
		else
		{
			currentplant=thirdPlantSet;
			DemandSetUp currentPlantDS = currentplant.get(0);
			PlantsDataVo plantBean = new PlantsDataVo();
			plantBean.setPlantCode(currentPlantDS.getPlant_Code());
			plantBean.setOrgCode(currentPlantDS.getOrganisationCode());
			plantBean.setComCode(currentPlantDS.getCompanyCode());
			plantBean.setIndexNumber(2);
			plantsDataList.add(plantBean);
		}
		
		for (Iterator iterator = currentplant.iterator(); iterator.hasNext();) 
		{
			DemandSetUp demandSetUp = (DemandSetUp) iterator.next();
			
			//System.out.println("hELLO HELLO HELLO"+ demandSetUp.getCountry());
			strbuilder.append(demandSetUp.getPlant_Code()+",");
			//System.out.println("1");
			strbuilder.append(demandSetUp.getPlant_Name()+",");
			//System.out.println("2");
			strbuilder.append(demandSetUp.getDemandType()+",");
			//System.out.println("2.5");
			strbuilder.append("Aravinda,");    /// Demand Owner
			//System.out.println("2.75");
			strbuilder.append(demandSetUp.getSale_Order_Number()+",");
			//System.out.println("2.85");
			strbuilder.append(((Integer)demandSetUp.getSo_Amendment_Number())+",");
			//System.out.println("2.95");
			strbuilder.append(new Date().toString()+",");   // Sales order date
			//System.out.println("3");
			strbuilder.append(demandSetUp.getStatus_of_Sale_Order()+",");
			//System.out.println("4");
			strbuilder.append(demandSetUp.getCustomer_Code()+",");
			//System.out.println("5");
			strbuilder.append(demandSetUp.getCustomer_Name()+",");
			//System.out.println("6");
			strbuilder.append(demandSetUp.getCustomer_Ship_Address1()+",");
			//System.out.println("7");
			strbuilder.append(demandSetUp.getCustomer_Ship_Address2()+",");
			//System.out.println("8");
			strbuilder.append(demandSetUp.getCustomer_Ship_CountryCode()+",");
			//System.out.println("9");
			strbuilder.append(demandSetUp.getCustomer_Ship_City_Code()+",");
			//System.out.println("10");
			strbuilder.append(demandSetUp.getCustomer_Zipcode()+",");
			//System.out.println("11");
			strbuilder.append(((Integer)demandSetUp.getSo_Line_Number())+",");
			//System.out.println("12");
			strbuilder.append(demandSetUp.getPart_Number()+",");
			//System.out.println("13");
			strbuilder.append(demandSetUp.getPart_Name()+",");
			//System.out.println("14");
			strbuilder.append(((Integer)demandSetUp.getPart_Revision_Number())+",");
			//System.out.println("15");
			strbuilder.append(((Double)demandSetUp.getQuantity()).intValue()+",");
			//System.out.println("16");
			strbuilder.append(demandSetUp.getUom_Code()+",");
			//System.out.println("17");
			strbuilder.append(((Integer)demandSetUp.getPrice())+",");
			//System.out.println("18");
			strbuilder.append(demandSetUp.getCurrency_Code()+",");
			//System.out.println("19");
			strbuilder.append(demandSetUp.getRequired_Date()+",");             
			//System.out.println("20");
			strbuilder.append(demandSetUp.getBusiness_Unit_Code()+",");
			//System.out.println("21");
			strbuilder.append(demandSetUp.getPlant_Id()+",");
			//System.out.println("22");
			strbuilder.append(demandSetUp.getMode()+",");
			//System.out.println("23");
			strbuilder.append(demandSetUp.getParentID()+",");
			//System.out.println("24");
			strbuilder.append(new Date().toString()+","); // get date
			strbuilder.append(demandSetUp.getInsertionDate()+","); // insertion date
			//System.out.println("25");
			strbuilder.append("HeadParent"+",");
			//System.out.println("26");
			strbuilder.append(demandSetUp.getOrganisationCode()+",");
			//System.out.println("27");
			strbuilder.append(demandSetUp.getCompanyCode()+",");
			//System.out.println("28");
			strbuilder.append(demandSetUp.getModifiedTime()+" \n"); // insertion date
			//System.out.println("29");
			
			
		}
		
		//System.out.println("after the loop");
		
		
		byte[] channel= (headers+ strbuilder.toString()).getBytes();
		
		ByteBuffer buf = ByteBuffer.wrap(channel);
		GcsFilename file= new GcsFilename("e2escm-gpractice.appspot.com", "ScmProject/bulkdatafile"+((Integer)i).toString()+".csv");
		
		GcsFileOptions.Builder builder= new GcsFileOptions.Builder();
		GcsFileOptions fileoptions=builder.mimeType("application/vnd.ms-excel").build();
		GcsOutputChannel outputChannel;
		try {
			outputChannel = gcsService.createOrReplace(file, fileoptions);
			outputChannel.write(buf);
			outputChannel.close();
		
			System.out.println("Start the upload JOBBBBB");
			//new bqloadjob().bqservice("bulkdatafile.csv");
			System.out.println("Done");
		} catch (Exception e1) 
		{
			// TODO Auto-generated catch block
			System.out.println("Problem");
			System.out.println(e1.getMessage());
			e1.printStackTrace();
		}
		
		//check if the write is successful
	

	currentplant.clear();
	strbuilder.setLength(0);
	
	}
	System.out.println("approaching");
	
/*	for(int i=0;i<3;i++)
	{
		new bqloadjob().bqservice("bulkdatafile"+((Integer)i).toString()+".csv");
	}
	*/
	
	System.out.println("approaching the one");
	Iterator<PlantsDataVo> plantlistIt = plantsDataList.iterator();
	int i = 0;
	System.out.println("approaching the one with data"+plantsDataList.size());
	
	List<Map<String,List<String>>> jobids= new ArrayList<>();
	while(plantlistIt.hasNext())
		{
		System.out.println("Getting in the JOB");
		Map<String,List<String>> temp= new HashMap<>();
			temp=new bqloadjob().bqservice("bulkdatafile"+((Integer)i).toString()+".csv",plantlistIt.next());
			i++;
			jobids.add(temp);
		}
	System.out.println("demandRequiredDates::"+demandRequiredDates);
	try {
		System.out.println("demandRequiredDates::"+demandRequiredDates);
		datastore.ProductInfoUtil.putAllRequiredDatesIntoDataStore(demandRequiredDates);
		
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	return jobids;
   
    
    
		
	}
	
	


	
}

        
        
	
		
		
		//Form a csv String

		/*String to_write= "Number1,Number2,Number3,Number4,Number5,Number6,Number7,Number8,Number9,number10,number11\n";
		StringBuilder strbuilder= new StringBuilder();
		
		for(Integer i=1; i<100000;i++)
		{
			for(Integer j=0; j<11;j++)
			{
				
				if(j.equals(10))
				{
					strbuilder.append(j.toString()+"\n");
					
				}
				else{
					strbuilder.append(j.toString()+",");
				}
				
			}
		}
		
		
	 
		
		
		
		//Convert to byte, which is converted to byte buffer
		byte[] channel= (to_write+ strbuilder.toString()).getBytes();
		ByteBuffer buf = ByteBuffer.wrap(channel);

		
		//Write the buffer to a file in cloud storage
		GcsFilename file= new GcsFilename("kshbucketone", "data.csv");
		GcsFileOptions.Builder builder= new GcsFileOptions.Builder();
		GcsFileOptions fileoptions=builder.mimeType("text/csv").build();
		GcsOutputChannel outputChannel =
			    gcsService.createOrReplace(file, fileoptions);
		
		//check if the write is successful
		try{
		outputChannel.write(buf);
		outputChannel.close();
		
		
		// Call the JOB function
		////System.out.println("written successfully");
		
		}
		catch(Exception e)
		{
			
		}
*/		
		
        
//	}
//}
