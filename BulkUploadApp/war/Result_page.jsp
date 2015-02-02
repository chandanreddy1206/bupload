<%@page import="java.util.ArrayList"%>
<%@page import="java.util.*"%>
<%@page import="java.lang.reflect.Array"%>
<%@ page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <script src="http://code.jquery.com/jquery-1.11.0.min.js">      
</script>
         
         
         
         
     <script type="text/javascript">
     
     var completed= new Array();
     function getMessages()
           {
    	//////alert(completed.length.toString())
    	 //alert(1);
             var no_of_jobs=$('#numberofjobs').val();
            

            for(i=0;i<no_of_jobs;i++)
            		{var flag=0;
            	var jobid=$('#interestParamCount'+i).val().toString();
            		
            		 
            		for (var count=0; count<completed.length; count++)
            			{
            			if(completed[count]==jobid.toString())
            				{
            				flag=1;
            				break;
            				}
            			}
            		
            		if(flag==0)
            		{ //alert("inside");
            			$.ajax({
            				  url:'https://arubatestbulkuploads-dot-e2escm-gpractice.appspot.com/_ah/api/statusapi/v1/status/'+$('#interestParamCount'+i).val()+'/'+i,
            		 		 type: 'POST',
            		 		 dataType: 'json',
            		 		 
            		 		  success: function(data) 
            		 		  {var temp =data["number"]
            					//called when successful
            					var jobstatus=(data["status"]).toString();
            					//alert("***"+jobstatus+"***");
            					if(jobstatus=="DONE")
            					{var loopflag=0;
            						for (var count=0; count<completed.length; count++)
                        			{
                        			if(completed[count]==data["job"].toString())
                        				{
                        				loopflag=1;
                        				break;
                        				}
                        			}
            						if(loopflag==0)
            						{
            						////alert("in the block");
            						completed.push(data["job"].toString());
            						}
            						
            					}
            					
            				  $('#jobstatus'+temp).val(data["status"] );
            				  $('#joberror'+temp).val(data["error"] );
            		 		 }

            			});
            		}
            		else
            			{
            			//alert("this job is done")
            			}
            		}
            //alert(2);
               }
     //alert(3);
        </script>
        
         
         
<title>Insert title here</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
</head>
<body>
 <div id="wrapper"> 

  <div id="header">
   
  </div> 

  <div id="page_content">
  
  <div class="navigation">
 <h2> Supply Chain Management </h2>
 
  </div>

    <div class="left_section">
    <div class="common_content">
        
        <hr>
	
       
<h3>  Data upload in progress. Changes will be reflected in few minutes</h3>


<p> number of jobs currently in progress are
<%
List<String> jobs_toquery= new ArrayList();
Integer no_of_jobs=0;
no_of_jobs=Integer.parseInt(request.getAttribute("number").toString());
/* response.getWriter().print(request.getAttribute("number")); */
%>


</p>

<p>
and the corresponding jobs are
<%
for(Integer i=0;i<no_of_jobs;i++)
{
	jobs_toquery.add(request.getAttribute("Job"+i).toString());
	/* response.getWriter().print(request.getAttribute("Job"+i)); */
	
}

%>
</p>
<br>
<p>
Now its the time to get the status
</p>
<input type='hidden' value=<%= jobs_toquery.size()%> id="numberofjobs"/>



<% 
for(int i=0; i<jobs_toquery.size();i++)
{	String jobid= jobs_toquery.get(i);
%>
<input type="text" name="fname<%= i%>" value="none" id="jobstatus<%= i%>" readonly >
<input type="text" name="lname<%= i%>" value="none" id="joberror<%= i%>" readonly ><br>
<% 
}
%> 
<% 
for(int i=0; i<jobs_toquery.size();i++)
{	String jobid= jobs_toquery.get(i);
%>
<input type='hidden' value=<%= jobid%> id="interestParamCount<%= i%>"/>
<% 
}
%>

 <script>
 window.setInterval(getMessages, 2000);
 </script> 
  

      </div>
      
      <div cla ss="top_content border_none">
        <div class="column_one">
          <br>          
          <p>
	</p></div>
        <div class="column_two border_left">
         

        </div>
      </div>
    </div>
    
    <div class="right_side_bar"> 
      
      <div class="col_1">
     
        <div class="box">

	 <ul>
           
            
          </ul>
         
        </div>
      </div>
      
      <div class="col_1">
        
        <div class="box">
         </div>
      </div>
      
    </div>
    
    <div class="clear"></div>

  <!--start footer from here-->
  <div id="footer"></a><br>
  
  <!--DO NOT remove footer link-->
  <!--Template designed by--><a href="http://www.htmltemplates.net"><img src="images/footer.gif" class="copyright" alt="http://www.htmltemplates.net"></a></div>
  
  <!--/. end footer from here-->
  </div>

</div>



</body>

</body>
</html>