package upload.test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.services.bigquery.Bigquery;
import com.google.api.services.bigquery.model.Job;

import java.util.*;



@Api(name="statusapi",version="v1")

public class getstatusEP
{
AppIdentityCredential credential;
Bigquery bq;
HttpTransport httpTransport ; 
JsonFactory jsonFactory ;
String jobid;
String jobstatus;
String errorstatus;
String project_name="e2escm-gpractice";

	@ApiMethod(name="status")
	public statusEntity status(@Named("jobid") String jobid,@Named("Number") Integer number)
	{List<String> scopes= new ArrayList<>();
	statusEntity bean= new statusEntity();
		scopes.add("https://www.googleapis.com/auth/bigquery");
		scopes.add("https://www.googleapis.com/auth/cloud-platform");
		credential= new AppIdentityCredential(scopes);
		this.jobid=jobid;
		try {
			httpTransport= GoogleNetHttpTransport.newTrustedTransport();
			jsonFactory= com.google.api.client.json.jackson2.JacksonFactory.getDefaultInstance();
			 bq= new Bigquery(httpTransport, jsonFactory, credential);

			Job job= new Job();
			job=bq.jobs().get(project_name, jobid).execute();
			
			jobstatus=job.getStatus().getState();
			errorstatus="No Error yet";
			
			if(jobstatus.equalsIgnoreCase("done"))
			{
				try{
				errorstatus=job.getStatus().getErrorResult().getMessage();
				}
				catch(Exception np)
				{
					
				 errorstatus="NO ERROR, Job completed sucessfully";
					
				}
				
			}

				bean.setError(errorstatus);
				bean.setStatus(jobstatus);
				bean.setJob(jobid);
				bean.setNumber(number);
				return bean;
		} catch (GeneralSecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bean.setStatus("No such job");
		bean.setError("No such job");
		bean.setNumber(number);
		bean.setJob(jobid);

		return bean;

		
		
		
	}

}
