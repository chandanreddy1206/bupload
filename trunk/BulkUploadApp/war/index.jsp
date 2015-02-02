<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/style.css" rel="stylesheet" type="text/css">
<title>Insert title here</title>
</head>
<body>


<%
PrintWriter outht= response.getWriter();
//outht.print("Good Got the first view");
%>

<div id="wrapper"> 
  
  <div id="header">
   
  </div>

  <div id="page_content">
  
  <div class="navigation">
 <h2> Supply Chain Management - Bulk Uploads </h2>
 
  </div>

    <div class="left_section">
    <div class="common_content">
        <h2>Demand</h2>
        <hr>
        <p>Please use this section to upload the Demand file to bigquery dataset. Also the file will be autometically sharded across various datasets. This feature simplifies analytics</p>
        <br>

<table>
 <form method="POST" action="gcssppengine?type=Demand"  enctype="multipart/form-data" >
      <tr>
        <td colspan="2" style="font-weight:bold;">
        
		<input  type="file" name="uploadField" />
		
        
        </td>        
      </tr>
      <tr>
<td>
      <input  class="custom-btn" type="submit" value="Submit">
</td>
      </tr>
 </table>
</form>



      </div>
      
      <div class="top_content border_none">
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
       <!--   <h1>Upload</h1> -->
        <div class="box">

	  <ul>
 	    <li><a href="index.jsp">Demand</a></li>
         <li><a href="supply.jsp">Supply</a></li> 
           <li><a href="productfamily.jsp">Product Family</a></li>             
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
</html>