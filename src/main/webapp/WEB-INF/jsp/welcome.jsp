<!DOCTYPE html>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html lang="en">
<head>

<link rel="stylesheet" type="text/css"
	href="webjars/bootstrap/3.3.7/css/bootstrap.min.css" />

<!-- 
	<spring:url value="/css/main.css" var="springCss" />
	<link href="${springCss}" rel="stylesheet" />
	 -->
<c:url value="/css/main.css" var="jstlCss" />
<link href="${jstlCss}" rel="stylesheet" />

</head>
<body>
	
	<h1>PaddyPower BetFair Report Code Challenge by <span>Vijayakumar K</span></h1>
	
	
	<div><p>&nbsp;</p></div>
	
	<form id="updateForm" name="updateForm" enctype="multipart/form-data">
			<INPUT TYPE="radio" NAME="radios" onclick="displayFileButton()" id="radiofile" VALUE="file" CHECKED>
             CSV File<div id="error"><h4>${Error}</h4></div>
            <BR>
            <INPUT TYPE="radio" NAME="radios" onclick="displayTextbox()" id="radioJson" VALUE="json">
             HTTP Service(Json)<div id="jsonWarning"><h4 >This Option currently not available. coming soon..</h4></div>
   			<div id="filediv"><h3>Please choose a file to upload </h3><input type="file" id="file" name="file" /><br/></div>
   			<div id="textdiv"><h3>Please type the URL </h3><textarea rows="1" cols="30"  id="textjson" name="json"></textarea><br/></div>
   			<div id="submitButtons">
    			<input type="button" id="btnView" onclick="viewReport()" value="ViewReport" />
    			<input type="button" id="btnXml" onclick="downloadXMLReport()"  value="DownloadXMLReport" />
    		</div>
	</form>

	<div class="container">

		
		<div>
			<table class="responstable">
				<tr>					
					<td width="10%" align="left"><b>Selection Name</b></td>
					<td width="5%"  align="left"><b>Currency</b></td>
					<td width="5%"  align="left"><b>No of Bets</b></td>
					<td width="10%" align="left"><b>Total Stakes</b></td>
					<td width="10%" align="left"><b>Total Price</b></td>
				</tr>
			<c:forEach items="${betList}" var="bet">
				<tr>
					<td width="10%" align="left"><c:out value="${bet.selectionName}" /></td>
					<td width="5%" align="left"><c:out value="${bet.currency}" /></td>
					<td width="5%" align="left"><c:out value="${bet.noOfBets}" /></td>
					<td width="10%" align="left"><c:if test = "${bet.currency=='EUR'}">&#8364;</c:if><c:if test = "${bet.currency=='GBP'}">£</c:if><c:out value="${bet.totalStakes}" /></td>
					<td width="10%" align="left"><c:if test = "${bet.currency=='EUR'}">&#8364;</c:if><c:if test = "${bet.currency=='GBP'}">£</c:if><c:out value="${bet.totalPayout}" /></td>
				</tr>
			</c:forEach>
			</table>
		</div>

	</div>
	<!-- /.container -->

	<script type="text/javascript"
		src="webjars/bootstrap/3.3.7/js/bootstrap.min.js"></script>

</body>
<script>
displayFileButton();
function displayTextbox(){
	document.getElementById('textdiv').style.display = 'block';
	document.getElementById('filediv').style.display = 'none';
	document.getElementById('jsonWarning').style.display = 'block';
	document.getElementById('submitButtons').style.display = 'none';
}

function displayFileButton(){
	document.getElementById('filediv').style.display = 'block';
	document.getElementById('textdiv').style.display = 'none';
	document.getElementById('jsonWarning').style.display = 'none';
	document.getElementById('submitButtons').style.display = 'block';
}

function viewReport(){
	document.getElementById("updateForm").method="POST";
	document.getElementById("updateForm").action="uploadCSV";
    document.getElementById("updateForm").submit();
    //document.getElementById('error').style.display = 'none';
}

function downloadXMLReport(){
	document.getElementById("updateForm").method="POST";
	document.getElementById("updateForm").action="xmlreport";
    document.getElementById("updateForm").submit();
    //document.getElementById('error').style.display = 'none';
}

</script>
</html>
