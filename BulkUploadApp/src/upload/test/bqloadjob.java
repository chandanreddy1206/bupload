package upload.test;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;

import org.mortbay.jetty.Connector;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.bio.SocketConnector;
import org.mortbay.jetty.handler.ContextHandlerCollection;
























import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.model.Job;
import com.google.api.services.bigquery.model.JobConfiguration;
import com.google.api.services.bigquery.model.JobConfigurationLoad;
import com.google.api.services.bigquery.model.TableReference;
public class bqloadjob 
{
  HttpTransport httpTransport ; 
  JsonFactory jsonFactory ;
  String JobId;
  Job get_details;
  Bigquery bq;  
public Map<String,List<String>> bqservice(String filename,String plantDataSetInstance,String type) 
{ 
	get_details =new Job(); 	
try {	
	httpTransport= GoogleNetHttpTransport.newTrustedTransport();
} catch (GeneralSecurityException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
	
  jsonFactory= com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance();
  	   Job job= new Job();
	   JobConfiguration configuration= new JobConfiguration();
	   JobConfigurationLoad loader= new JobConfigurationLoad();	  
	   List<String> uris = new ArrayList<>();
	   uris.add("gs://e2escm-gpractice.appspot.com/Bulk/"+filename.trim());
	   loader.setSourceFormat("CSV");
	   loader.setSkipLeadingRows(1);	   
	   loader.setSourceUris(uris);	   
	   TableReference destinationTable = new TableReference();
	   destinationTable.setProjectId("e2escm-gpractice");	   
	   String dataSetName = plantDataSetInstance;
	   destinationTable.setDatasetId(dataSetName);	
	   if("Demand".equalsIgnoreCase(type)){		   
	   destinationTable.setTableId("DEMAND");	
	   }else if("Supply".equalsIgnoreCase(type)){
	   destinationTable.setTableId("SUPPLYBUY");	
	   }else if("BOM".equalsIgnoreCase(type)){
	   destinationTable.setTableId("BOM");   
	   }	  	
	   loader.setDestinationTable(destinationTable);	   
	   loader.setWriteDisposition("WRITE_APPEND");
	   configuration.setLoad(loader);
	   job.setConfiguration(configuration);
	   Map<String, List<String>> jobdetails= new HashMap<>();   
	   try {
		 bq= new Bigquery(httpTransport, jsonFactory, gettoken.authorize());
		 get_details=bq.jobs().insert("e2escm-gpractice", job).execute();
		 List<String> jobstatus= new ArrayList();		
		 jobstatus.add(get_details.getStatus().getState());
		try {
			 jobstatus.add(get_details.getStatus().getErrorResult().getReason().trim().toString());
		} catch (Exception e) {
			jobstatus.add("No Errors");			
		}		 
		 jobdetails.put(get_details.getJobReference().getJobId(), jobstatus);
     	 JobId = get_details.getJobReference().getJobId();	 
		 System.out.println("JobId::::::::::::::::::::::::::"+JobId);			 
		 return jobdetails;		
		
	} catch (Exception e) {
		System.out.println("Exception ::::"+e.getLocalizedMessage());
		e.printStackTrace();
	}
	   List<String> list = new ArrayList();
	   list.add("ERROR");
	   jobdetails.put("error", list);
	   return jobdetails;
	}
}

  



