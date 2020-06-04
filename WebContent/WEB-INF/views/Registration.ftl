<!DOCTYPE html>
<html dir="ltr" lang="zxx">

<head>
  <meta charset="utf-8">
  <title>Sapphire Lenses</title>
  <meta name="description" content="">
  <meta name="author" content="author">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <link rel="shortcut icon" href="images/favicon.ico">
  <link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,700i" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,700" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
  <link href="https://fonts.googleapis.com/css?family=PT+Serif" rel="stylesheet">
  <link href="bootstrap/css/bootstrap.css" rel="stylesheet">
  <link href="fonts/font-awesome/css/font-awesome.css" rel="stylesheet">
  <link href="plugins/magnific-popup/magnific-popup.css" rel="stylesheet">
  <link href="css/animations.css" rel="stylesheet">
  <link href="plugins/slick/slick.css" rel="stylesheet">
  <link href="css/style.css" rel="stylesheet" >
  <link href="css/typography-default.css" rel="stylesheet" >
  <link href="css/skins/light_blue.css" rel="stylesheet">
  <link href="css/custom.css" rel="stylesheet">
</head>
<body class=" ">
  <div class="scrollToTop circle"><i class="fa fa-angle-up"></i></div>
  <div class="page-wrapper">
    <div class="header-container">
      <div class="header-top ">
        <div class="container">
          <div class="row">
            <div class="col-3 col-sm-6 col-lg-9">
            </div>
            <div class="col-9 col-sm-6 col-lg-3">
              <div id="header-top-second"  class="clearfix">
              </div>
            </div>
          </div>
        </div>
      </div>

      <header class="header fixed fixed-desktop clearfix">
        <div class="container">
          <div class="row">
            <div class="main col-md-4">
              <div id="logo" class="logo">
                <img src="images/img/logo.jpg" alt="" style="width: 200px;height: 55px;margin-top: 10px;margin-left: -20px;">
              </div>
            </div>
            <div class="main col-lg-2">
            </div>
            <div class="main col-md-4 headerNav">
              <div class="row">
                <a class="font-weight-bold text-uppercase p-1 bg-light border rounded border-info" href="home" style="margin-top: 13%;margin-left: 40%;">Home</a>          
                <a class="font-weight-bold text-uppercase p-1 bg-light border rounded border-info" href="bookorder" style="margin-left: 5%;margin-top: 13%;">Book Now</a>
              </div>
            </div>
            <div class="main col-md-2 headerNav">
             <form class="form-horizontal mt-4 " action="logout" method="POST">  
               <button type="submit" class="btn btn-group btn-default btn-animated mt-4" >Log Out <i class="fa fa-user"></i></button>
             </form>  
           </div>
         </div>
       </div>
     </header>
     <section class="main-container">
      <div class="">
       <div class="container">
         <div class="form-row">
          <div class="col-md-4">
          </div>
          <div class="col-md-4">
          </div>
        </div>
        <form action="register" method="POST">
          <div class="form-row">
            <div class="col-md-12">
              ${errorMessage}
            </div>
          </div>
          <div class="form-row">
            <div class="col-md-6">
             <div class="form-group has-feedback">
               <label>Full Name</label>
               <input type="text" name="customerName" class="form-control" value='${customerName}'>
             </div>
           </div>
           <div class="col-md-6">
             <div class="form-group has-feedback">
               <label>Contact Number</label>
               <input type="text" name="contactNumber" class="form-control" value='${contactNumber}'>
             </div>
           </div> 
         </div>
         <div class="form-row">
           <div class="col-md-6">
            <div class="form-group has-feedback">
             <label>Username</label> 
             <input type="text" name="userName" class="form-control" value='${userName}'>   
           </div>   
         </div>
         <div class="col-md-6">
           <div class="form-group has-feedback">        
             <label>Password</label>        
             <input type="password" name="password" class="form-control" value='${password}'>
           </div>
         </div>
         <div class="col-md-6">
           <div class="form-group has-feedback">
             <label>GST number</label>
             <input type="text" name="gstNo" class="form-control" value='${gstNo}'>
           </div>
         </div>
         <div class="col-md-6">
          <div class="form-group has-feedback">
            <label>Email</label>
            <input type="text" name="email" class="form-control" value='${email}'>
          </div>
        </div>
        <div class="col-md-6">
          <div class="form-group has-feedback">
            <label>Address Line 1</label>
            <input type="text" name="addressLine1" class="form-control" value='${addressLine1}'>
          </div>
        </div>
        <div class="col-md-6">
          <div class="form-group has-feedback">
            <label>Address Line 2</label>
            <input type="text" name="addressLine2" class="form-control" value='${addressLine2}'> 
          </div> 
        </div>
        <div class="col-md-6">
          <div class="form-group has-feedback">
            <label>Address Line 3</label>
            <input type="text" name="addressLine3" class="form-control" value='${addressLine3}'>
          </div>    
        </div>
        <div class="col-md-6">
          <div class="form-group has-feedback"> 
            <label>Postal Code</label>
            <input type="text" name="postalCode" class="form-control" value='${postalCode}'>
          </div>
        </div>
        <div class="col-md-6">
         <div class="form-group has-feedback">
           <label>Organization</label>
           <select class="form-control" name="orgName" > 
            <option></option>       
            ${organizationOptions}	
          </select>    
        </div>
      </div> 
    </div>
    ${registerUserDetails}
    ${updateUserDetails}
  </form>
</div>
</div>
</div>
</section>
<div class="sp"> <div class="subfooter">
  <div class="container">
    <div class="subfooter-inner">
      <div class="row">
        <div class="col-md-12">
          <p class="text-center">Powered By Social Angels Digital Solution Pvt Ltd.</p>
        </div>
      </div>
    </div>
  </div>
</div>
</div>
<script src="plugins/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
<script src="plugins/waypoints/jquery.waypoints.min.js"></script>
<script src="plugins/waypoints/sticky.min.js"></script>
<script src="plugins/countTo/jquery.countTo.js"></script>
<script src="plugins/slick/slick.min.js"></script>
<script src="js/template.js"></script>
<script src="js/custom.js"></script>
<script>
$(document).ready(function(){
	if($('#registerBtn')[0] !== undefined)
	{
		$('.headerNav').remove();
	}
});
</script>

</body>
</html>
