<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %> 
<%@ taglib uri="/taglibs/lacsd.tld" prefix="lacsd" %> 
<script src="/rcx/javascript/jquery-3.4.1.min.js" type="text/javascript" language="javascript" ></script>
<script src="/rcx/javascript/popper.min.js" type="text/javascript" language="javascript" ></script>
<script src="/rcx/javascript/bootstrap.min.js" type="text/javascript" language="javascript" ></script>



<html>
<head >
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css" type="text/css">
  <link rel="stylesheet" href="/rcx/css/theme.css" type="text/css">
</head>

<body>
  <nav class="navbar navbar-expand-lg navbar-dark bg-primary-tr"><a class="navbar-brand m-0 p-0" href="https://prodapps.lacsd.org/" style=""><img src="/rcx/images/lacsd-logo.png" class="d-inline-block align-top m-0 p-0 border rounded" alt="" height="60" width="240"></a>
    <div class="container"> <a class="navbar-brand ml-5 pl-5 d-flex justify-content-center flex-row align-items-center flex-grow-1" href="/rcx/jsp/RCXIndex.jsp">
        <i class="fa d-inline fa-lg"></i>
        <b class="text-white m-0 p-0" style=""> Refuse Recycle Center</b>
      </a> <button class="navbar-toggler navbar-toggler-right border-0" type="button" data-toggle="collapse" data-target="#navbar17">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbar17">
        <ul class="navbar-nav ml-auto">
          <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle text-white" href="" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> System Admin&nbsp;</a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> 
                <a class="dropdown-item" href="#">Archive Data</a> 
            	<a class="dropdown-item" href="/rcx/jsp/RCXCities.jsp">Cities</a> 
            	<a class="dropdown-item" href="./Material.action?actionName=setup">Material</a> 
            	<a class="dropdown-item" href="./Rate.action?actionName=retrieve">Rates</a> 
            	<a class="dropdown-item" href="#">Recycle Centers</a> 
            	<a class="dropdown-item" href="./Container.action?actionName=setup">Container</a>
            	<a class="dropdown-item" href="#">Site Container</a>
            	<a class="dropdown-item" href="#">Site Material</a>
           	</div>
          </li>
          <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle text-white" href="" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> Accounting&nbsp;</a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> 
            	<a class="dropdown-item" href="./Charity.action?actionName=setup">Charities</a> 
            	<a class="dropdown-item" href="#">Charity Payments</a> 
            	<a class="dropdown-item" href="#">Monthly Expenses</a> 
            	<a class="dropdown-item" href="#">Pay Charity Tickets</a> 
            	<a class="dropdown-item" href="#">Safe Adjustment</a> 
            	<a class="dropdown-item" href="#">Ticket</a> 
            	<a class="dropdown-item" href="./Vendor.action?actionName=setup">Vendor</a> 
           	</div>
          </li>
          <li class="nav-item dropdown"> <a class="nav-link dropdown-toggle text-white" href="" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Site Operations</a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink"> 
            	<a class="dropdown-item" href="/rcx/jsp/RCXNewTicket.jsp">New Ticket</a> 
            	<a class="dropdown-item" href="#">Bank Drop</a> 
            	<a class="dropdown-item" href="#">Close Safe</a> 
            	<a class="dropdown-item" href="#">Ticket Search</a>
           	</div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  
 </body>

</html>