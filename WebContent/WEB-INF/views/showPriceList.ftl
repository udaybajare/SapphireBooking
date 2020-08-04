<!DOCTYPE html>
<html dir="ltr" lang="zxx">

<head>
  <meta charset="utf-8">
  <title>Sapphire Lenses</title>
  <meta name="description" content="The Project a Bootstrap-based, Responsive HTML5 Template">
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
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  
</head>

<body class=""> 

  <div class="header-container">
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
          <div class="main col-md-4">
            <div class="row">
              <a class="font-weight-bold text-uppercase p-1 bg-light border rounded border-info" href="home" style="margin-top: 13%;margin-left: 40%;">Home</a>          
              <a class="font-weight-bold text-uppercase p-1 bg-light border rounded border-info" href="bookorder" style="margin-left: 5%;margin-top: 13%;">Book Now</a>
            </div>
          </div>
          <div class="main col-md-2">
           <form class="form-horizontal mt-4 " action="logout" method="POST">  
             <button type="submit" class="btn btn-group btn-default btn-animated mt-4" >Log Out <i class="fa fa-user"></i></button>
           </form>	
         </div>

       </div>
     </div>       
   </header>
 </div>
 <section class="main-container">
  <div class="row ml-5">
    <div class="col-md-12">
      <ul class="nav nav-tabs style-1" role="tablist">
        <li class="nav-item">
          <a class="nav-link active" href="#htab1" role="tab" data-toggle="tab"><i class="fa fa-home pr-2"></i>Factory Order - Glass Price</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#htab2" role="tab" data-toggle="tab"><i class="fa fa-user pr-2"></i>Factory Order - CR Price</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#htab3" role="tab" data-toggle="tab"><i class="fa fa-envelope pr-2"></i>Ready Stock - Glass Price</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="#htab4" role="tab" data-toggle="tab"><i class="fa fa-envelope pr-2"></i>Ready Stock - CR Price</a>
        </li>
      </ul>
      <div class="tab-content">
        <div class="tab-pane fade show active" id="htab1" role="tabpanel">                  
          <div class="row">
            <div class="col-md-12">
              <table class="table table-striped table-colored table-responsive">
               <thead>
                <tr>
                  <th>CYL/SPH</th>
                  <th>PB_KT</th>
                  <th>PB_SV</th>
                  <th>PG4_KT</th>
                  <th>PG4_SV</th>
                  <th>PG6_KT</th>
                  <th>PG6_SV</th>
                  <th>SP2_KT</th>
                  <th>SP2_SV</th>
                  <th>WT_KT</th>
                  <th>WT_SV</th>
                  <th>PG_DB</th>
                  <th>PG_DB_ARC</th>
                  <th>PG_PR</th>
                  <th>PG_PR_ARC</th>
                  <th>WT_DB</th>
                  <th>WT_DB_ARC</th>
                  <th>WT_PR</th>
                  <th>WT_PR_ARC</th>
                </tr>
              </thead>
              <form id="updateGlassPriceList" action="updateGlassPriceList" method="POST">
                <tbody>
                 ${glassPriceDetails}
               </tbody>
             </table>  
             <input id="updateGlassPriceBtn" type="submit" class="btn btn-primary mt-5" value="Update">
           </form>
         </div>
       </div>                  
     </div>
     <div class="tab-pane fade" id="htab2" role="tabpanel">
      <div class="row">
        <div class="col-md-12">
          <table class="table table-striped table-colored">
            <thead>
              <tr>
                <th>#</th>
                <th>PRODUCT</th>
                <th>TYPE</th>
                <th>INDEXVAL</th>
                <th>RANGE START</th>
                <th>RANGE END</th>
                <th>UC-SRP</th>
                <th>HC-SRP</th>
                <th>ARC-SRP</th>
              </tr>
            </thead>
            <form id="updateCRPriceList" action="updateCRPriceList" method="POST">
              <tbody>
                ${crPriceDetails}
              </tbody>
            </table>
            <input id="updateCRPriceBtn" type="submit" class="btn btn-primary mt-5" value="Update">
          </form>
        </div>
      </div>
    </div>
    <div class="tab-pane fade" id="htab3" role="tabpanel">
      <div class="row">
        <div class="col-md-12">
          <table class="table table-striped table-colored">
          	<thead>
          		<tr>
          		<th>Sph</th>
          		<th>Cyl</th>
          		<th>W_SV_NN</th>
          		<th>W_SV_PP</th>
          		<th>W_SV_PN</th>
          		<th>PG_SV_NN</th>
          		<th>PG_SV_PP</th>
          		<th>PG_SV_PN</th>
          		<th>W_KT_P</th>
          		<th>W_KT_N</th>
          		<th>PG_KT_P</th>
          		<th>PG_KT_N</th>
          		<th>W_KT</th>
          		<th>PG_KT</th>
          		</tr>
          	</thead>
          	<form id="updateGlasspriceReadyStockList" action="updateGlasspriceReadyStockList" method="POST">
          	<tbody>
          	${glassPriceReadyStockDetails}
          	</tbody>
          </table>
           <input id="updateGlassPriceReadyStockBtn" type="submit" class="btn btn-primary mt-5" value="Update">
           </form>
        </div>
      </div>
    </div>
    <div class="tab-pane fade" id="htab4" role="tabpanel">
      <div class="row">
        <div class="col-md-12">
          <table class="table table-striped table-colored">
            <thead>
              <tr>
                <th>#</th>
                <th>TYPE</th>
                <th>TINT</th>
                <th>INDEX</th>
                <th>COATING</th>
                <th>DIA</th>
                <th>SPH</th>
                <th>CYL</th>
                <th>AXIS</th>
                <th>UC</th>
                <th>HC</th>
                <th>ARC</th>
              </tr>
            </thead>
            <form id="updateCRPriceReadyStockList">
            <tbody>
              ${crPriceReadyStockDetails}
            </tbody>            
            </form>
          </table>
          <input id="updateCRPriceReadyStockBtn" type="submit" class="btn btn-primary mt-5" value="Update">
        </div>
      </div>
    </div>
  </div>
</div>
</div>
</section> 
<div class="subfooter" style="margin-top: 17%;">
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


<script src="plugins/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
<script src="plugins/waypoints/jquery.waypoints.min.js"></script>
<script src="plugins/waypoints/sticky.min.js"></script>
<script src="plugins/countTo/jquery.countTo.js"></script>
<script src="plugins/slick/slick.min.js"></script>

<script src="js/template.js"></script>
<script src="js/custom.js"></script>


<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script type="text/javascript">

  $('#updateGlassPriceBtn').on('click', function(e){

   e.preventDefault(); 

   var ajaxReq = $.ajax({
     url : 'updateGlassPriceList',
     type : 'POST',
     data : $('#updateGlassPriceList').serialize(),
   // data : $('#updateGlassPriceList').clone(true,true).serialize(),
   success: function(data) 
   {
    alert("Price updated Successfully..!!");
    console.log(" Received data from BE");
    console.log(data);             
  }
});

 });

</script>

<script type="text/javascript">

  $('#updateCRPriceBtn').on('click', function(e){

   e.preventDefault(); 

   var ajaxReq = $.ajax({
     url : 'updateCRPriceList',
     type : 'POST',
     data : $('#updateCRPriceList').serialize(),
   // data : $('#updateGlassPriceList').clone(true,true).serialize(),
   success: function(data) 
   {
    alert("Price updated Successfully..!!");
    console.log(" Received data from BE");
    console.log(data);             
  }
});

 });

</script>

<script type="text/javascript">

  $('#updateGlassPriceReadyStockBtn').on('click', function(e){

   e.preventDefault(); 

   var ajaxReq = $.ajax({
     url : 'updateGlasspriceReadyStockList',
     type : 'POST',
     data : $('#updateGlasspriceReadyStockList').serialize(),
      
     success: function(data) 
   {
    alert("Price updated Successfully..!!");
    console.log(" Received data from BE");
    console.log(data);             
  }
});

 });

</script>

<script type="text/javascript">

  $('#updateCRPriceReadyStockBtn').on('click', function(e){

   e.preventDefault(); 

   var ajaxReq = $.ajax({
     url : 'updateCRPriceReadyStockList',
     type : 'POST',
     data : $('#updateCRPriceReadyStockList').serialize(),
   // data : $('#updateGlassPriceList').clone(true,true).serialize(),
   success: function(data) 
   {
    alert("Price updated Successfully..!!");
    console.log(" Received data from BE");
    console.log(data);             
  }
});

 });

</script>

</body>
</html>