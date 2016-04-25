<html>
<head>
<title>Insert title here</title>
</head>
<body>
<table class="table table-striped">
						<thead>
							<tr>
								<th>Job Title</th>
								<th>Job Description</th>
								<th>Salary</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody>
						<#list model["applications"] as application>
							<tr>
           						<td>  ${application.job.jobTitle}<br></td>
           						<td>  ${application.job.jobDescription}<br></td>
           						<td>  ${application.job.pay}<br></td>  
           						<td>  ${application.status}<br></td>
           					</tr>
           				<#list> 
                        </tbody>
						</table>
						<a style="text-decoration:none" href="report.htm" target="_blank"><h4><span class="label label-warning">Download Report</span></h4></a>

</body>
</html>