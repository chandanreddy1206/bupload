package upload.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.omg.CORBA.Environment;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

public class gettoken {
	
	
	
	static HttpTransport httpTransport ; 
	  static JsonFactory jsonFactory ;
	  static JsonFactory JSON_FACTORY ;
	
	public static AppIdentityCredential authorize() throws Exception {
		
		  List<String> list= new ArrayList<>();
		  list.add("https://www.googleapis.com/auth/bigquery");
		  
		  list.add("https://www.googleapis.com/auth/devstorage.full_control");

		//String filename= "client_secret.json";
		String workingdirectory=System.getProperty("user.dir");
		  
		 httpTransport= GoogleNetHttpTransport.newTrustedTransport();

		JSON_FACTORY= com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance();
		  // load client secrets
		 // GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
		   //   new InputStreamReader(new FileInputStream(new File(workingdirectory+"/"+filename))));

//		  
//		  GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
//		      httpTransport, JSON_FACTORY, clientSecrets,
//		      (list)).setAccessType("offline").build();
//		  
		  
//		  GoogleCredential credential = new GoogleCredential.Builder()
//		    .setTransport(httpTransport)
//		    .setJsonFactory(JSON_FACTORY)
//		    .setServiceAccountId("95887887700-s8mnqp50s5bpmtr9c9o7tl3v0o5taouq@developer.gserviceaccount.com")
//		    .setServiceAccountPrivateKeyFromP12File(new File(workingdirectory+"/"+"key.p12"))
//		    .setServiceAccountScopes(list)
//		    
//		    .build();
		
		AppIdentityCredential creds = new AppIdentityCredential(list);
		  
		  
		  
		  return creds;
		 
		  // authorize
		 // return new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");
		}
	
	
	public static Credential getRefreshToken(Credential cr)
	{
		Credential reftoken= cr.setRefreshToken(cr.getRefreshToken());
		return reftoken;
	}
	
	

}
