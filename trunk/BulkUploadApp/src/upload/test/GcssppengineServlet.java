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
import java.util.logging.Level;

import javax.servlet.ServletException;
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

import datastore.TableStandardizationUtil;

@SuppressWarnings("serial")
public class GcssppengineServlet extends HttpServlet {
	
	private static Map<String, String> demandRequiredDates;
	private static String plantDataSetInstance = null;
	private static int insertionCount;
	static TableStandardizationUtil tUtil = new TableStandardizationUtil();
	private final GcsService gcsService = GcsServiceFactory.createGcsService(RetryParams.getDefaultInstance());
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws IOException 	{
		List<Map<String, List<String>>> jobids= new ArrayList<>();
		FileItemFactory fileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload(
                     fileItemFactory);
        UploadDemand demand=new UploadDemand();
        UploadSupply supply=new UploadSupply();
        UploadFamily bom=new UploadFamily();
        boolean isUploadSuccessful = true;
        
        try 
        {
            FileItemIterator fileItemIterator = servletFileUpload.getItemIterator(req);
            while (fileItemIterator.hasNext())
            {
                  FileItemStream fileItemStream = (FileItemStream) fileItemIterator.next();
            
                  InputStream inputStream = fileItemStream.openStream();
                  InputStream stream = new ByteArrayInputStream(IOUtils.toByteArray(inputStream));
                  List<List<Object>> table = ExcelParsingUtil.readExcellData(stream,("Demand".equals(req.getParameter("type")))?29:("Supply".equals(req.getParameter("type")))?39:27);
                   if (table == null) {
						isUploadSuccessful = false;
					} else {
						     List<Product> list;
							 Map<String,List<List<Object>>> PlantID=new HashMap<String,List<List<Object>>>();
                             for(List<Object> rowData:table){
                                  String key= (String) rowData.get(0)+"_"+rowData.get(2)+"_"+rowData.get(4);
                                  List<Object> row=rowData;
                                  List<List<Object>> rowLists=new ArrayList<List<Object>>();
                                  if(PlantID.get(key)!=null){
                                       rowLists=PlantID.get(key);
                                  }
                                  rowLists.add(row);
                                  PlantID.put(key, rowLists);                                 
                            }
							for (Map.Entry<String, List<List<Object>>> tableData : PlantID.entrySet()) {								
								 if (tableData.getKey().equals("Organisation Code_Company Code_Plant Code")) {
									 System.out.println("Header Data");
								 }else {
								      plantDataSetInstance = "E2ESCM_" + tableData.getKey();	
								      if("Demand".equals(req.getParameter("type"))){								    	 
						                  jobids=  demand.createDemandData(tableData.getValue(),plantDataSetInstance,insertionCount);                  
						              }else if("Supply".equals(req.getParameter("type"))){						                                
						                  jobids=  supply.createSupplyData(tableData.getValue());   
						              }else if("BOM".equals(req.getParameter("type"))){ 
						                  jobids=  bom.createProductData(tableData.getValue());   
						          }
							    }
						     }
							for (Map.Entry<String, List<List<Object>>> tableData : PlantID.entrySet()) {								
								 if (tableData.getKey().equals("Organisation Code_Company Code_Plant Code")) {
									 System.out.println("Header Data");
								 }else {									 
									 plantDataSetInstance = "E2ESCM_" + tableData.getKey();
									 insertionCount = datastore.ProductInfoUtil.getDemandUploadCountFromDataStore(plantDataSetInstance);
									 datastore.ProductInfoUtil.putDemandUploadCountIntoDataStore(insertionCount,plantDataSetInstance);
									 
							    }
						     }							
					}                 
            } 
        }   
        catch( Exception e){    
        	e.printStackTrace();
        }           
        Integer i=1;  
   	 req.setAttribute("number", ((Integer)jobids.size() ).toString());
   	 System.out.println(((Integer)jobids.size() ).toString());
   	 resp.addHeader("number", ((Integer)jobids.size() ).toString());
   	 
  	 PrintWriter out= resp.getWriter();    	
   	 try {
   		 Integer jobcount=0;
   	 for(Map<String, List<String>> s:jobids) 
   	 {
   		 System.out.println("keySet-->"+s.keySet().toArray()[0].toString());   		 
   		 req.setAttribute("Job"+jobcount.toString(), s.keySet().toArray()[0].toString());
   		 jobcount++;   		
   	 } 
		req.getRequestDispatcher("Result_page.jsp").forward(req, resp);					
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}

