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
{HttpTransport httpTransport ; 
  JsonFactory jsonFactory ;
  String JobId;

  Job get_details;
  Bigquery bq;
  
  public Map<String,List<String>> bqservice(String filename,PlantsDataVo plantsBean) 
  { get_details =new Job(); 	
	  try {
		  System.out.println("1");
	httpTransport= GoogleNetHttpTransport.newTrustedTransport();
} catch (GeneralSecurityException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
	  System.out.println("2");
  jsonFactory= com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance();
  System.out.println("3");
	   Job job= new Job();
	   JobConfiguration configuration= new JobConfiguration();

	   JobConfigurationLoad loader= new JobConfigurationLoad();
	  
	   List<String> uris = new ArrayList<>();
	   uris.add("gs://e2escm-gpractice.appspot.com/ScmProject/"+filename.trim());
	   
	   loader.setSourceFormat("CSV");
	   loader.setSkipLeadingRows(1);
	   
	   loader.setSourceUris(uris);
	   
	   TableReference destinationTable = new TableReference();
	   destinationTable.setProjectId("e2escm-gpractice");
	   
	   String dataSetName = "E2ESCM_"+plantsBean.getOrgCode()+"_"+plantsBean.getComCode()+"_"+plantsBean.getPlantCode();
	   System.out.println("DatasetId : "+dataSetName);
	   destinationTable.setDatasetId(dataSetName);
	   System.out.println("Done");
	   	 destinationTable.setTableId("DEMAND");
	 
	   loader.setDestinationTable(destinationTable);
	   

	   loader.setWriteDisposition("WRITE_APPEND");
	   configuration.setLoad(loader);
	   
	  
	   job.setConfiguration(configuration);
	   
	   
	 System.out.println("check 4");
	   Map<String, List<String>> jobdetails= new HashMap<>();
	   
	   
	   try {
		 bq= new Bigquery(httpTransport, jsonFactory, gettoken.authorize());
		
		 System.out.println("check 5");

		
		get_details=bq.jobs().insert("e2escm-gpractice", job).execute();
		 System.out.println("check 6");

		 List<String> jobstatus= new ArrayList();
		 jobstatus.add(get_details.getStatus().getState());
		 
		 try {
			 jobstatus.add(get_details.getStatus().getErrorResult().getReason().trim().toString());

			 
		} catch (Exception e) {
			jobstatus.add("No Errors");
		}
		 
			jobdetails.put(get_details.getJobReference().getJobId(), jobstatus);

		 
		 JobId = get_details.getJobReference().getJobId();
		 System.out.println("**************This is the JOB ID**********************");

		 System.out.println("**************This is the JOB ID**********************");

		 System.out.println("**************This is the JOB ID**********************");

		 System.out.println("**************This is the JOB ID**********************");

		 System.out.println("**************This is the JOB ID**********************");

		 System.out.println("**************This is the JOB ID**********************");
		 
		 System.out.println(JobId);
		 System.out.println("**************This is the JOB ID**********************");

		 System.out.println("**************This is the JOB ID**********************");

		 System.out.println("**************This is the JOB ID**********************");

		 
		 System.out.println("**************This is the JOB ID**********************");

		 System.out.println("**************This is the JOB ID**********************");

		 System.out.println("check 7");
		 return jobdetails;

		/*
		 for(int i=0;i<100;i++)
		 {
				 System.out.println("check 8");

					get_details.getStatus();
					 System.out.println("check 9");

					System.out.println(get_details.getStatus() +"   "+ "in try");
					 System.out.println("check 10");

					Job poller = new Job();
					 
						 
						 String project ="able-memento-790";
						 System.out.println("check 11");

						 JobId= get_details.getId();
						 System.out.println("check 12");

						 String[] temp = JobId.split(":");
						 System.out.println(temp[0]+"  "+temp[1]);
						 JobId=temp[1];
						 
						try {
							poller=  bq.jobs().get(project.toString().trim(),JobId).execute();
						} catch (Exception e) {
							//poll again
							
						}
						get_details=poller;
						
					
					if(get_details.getStatus().getState().toString().equalsIgnoreCase("Done"))
					{
						System.out.println(get_details.getStatistics().getStartTime() +"  in try");
						System.out.println(get_details.getStatistics().getEndTime()+ "   in try");
						
						System.exit(1);
						
					}
			
					Thread.sleep(2000);
		 }
		
		
		
		*/
		
		
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   List<String> list = new ArrayList();
	   list.add("ERROR");
	   jobdetails.put("error", list);
	return jobdetails;
	   
	   
	   
	   
	   
	   
	   
	   
  
	   /*
	   
	   
      Server server = new Server();
      ContextHandlerCollection contexts = new ContextHandlerCollection();
      server.setHandler(contexts);
      SocketConnector connector = new SocketConnector();

      connector.setHost("localhost");
      connector.setPort(29807);
      server.setConnectors(new Connector[]{connector});
      

       org.mortbay.log.Logger logy= org.mortbay.log.Log.getLog();
       
      logy.info("point1", "hello", "hello	"); 
      
  
      
      
      try {
		server.start();
		 server.join();
	} catch (Exception e1) {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
     
  
	     
	      
	  List<String> list= new ArrayList<>();
	  
	  list.add("https://www.googleapis.com/auth/bigquery");
	  
	  try {
		  httpTransport= GoogleNetHttpTransport.newTrustedTransport();

		GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
				    .setJsonFactory(jsonFactory)
				    .setServiceAccountId("95887887700-s8mnqp50s5bpmtr9c9o7tl3v0o5taouq@developer.gserviceaccount.com")
				    .setServiceAccountScopes(list)
				    .setServiceAccountPrivateKeyFromP12File(new File("key.p12"))
				    .build();
		
			String token= credential.getAccessToken();
			server.stop();
			System.out.println(token);
	  		} 
	  			catch (Exception e)
	  				{
	  				try {
						server.stop();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	  				// TODO Auto-generated catch block
	  					e.printStackTrace();
	  				}
	  
	  }
*/  }
}

  



