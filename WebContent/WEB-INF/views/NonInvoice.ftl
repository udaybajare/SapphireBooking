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
  
</head>
    <body class=" ">
      <div class="scrollToTop circle"><i class="fa fa-angle-up"></i></div>
      <div class="page-wrapper">
        <div class="header-container">
          <div class="header-top dark">
            <div class="container">
              <div class="row">
                <div class="col-3 col-sm-6 col-lg-9">
                </div>
                <div class="col-9 col-sm-6 col-lg-3">
                </div>
              </div>
            </div>
          </div>
        </div>
        <section class="container">
          <div class="container">
            <div class="row">
              <div class="main col-md-6">
               <div id="logo" class="logo">
                <img src="images/img/logo.jpg" alt="" style="width: 200px;height: 55px;margin-top: 20px;margin-left: -20px;">
              </div>
            </div>
            <div class="main col-md-3">
              <div class="row">
                <a class="font-weight-bold text-uppercase p-1 bg-light border rounded border-info" href="home" style="margin-top: 17%;margin-left: 2%">Home</a>
                <a class="font-weight-bold text-uppercase  p-1 bg-light border rounded border-info" href="bookorder" style="margin-left: 2%;margin-top: 17%;">Book Now</a>
                <a class="font-weight-bold text-uppercase  p-1 bg-light border rounded border-info" href="listOrders" style="margin-left: 2%;margin-top: 17%;">Show Orders</a>
              </div>
            </div>
            <div class="main col-md-3">
              <form class="form-horizontal" action="logout" method="POST">
               <div class="col-lg-7 col-xl-8 mr-20">
                 <button type="submit" class="btn btn-group btn-default btn-animated mt-4">Log Out <i class="fa fa-user"></i></button>
               </div>
             </form>
           </div>
         </div>
       </div>
     </section>
     <!-- main-container end -->

     <!-- section start -->
     <!-- ================ -->
     <div class="section light-gray-bg">
      <div class="container">
       <form action="generatenon" method="POST">
         <div class="form-row">
          <h2 class="page-title text-default text-md-center mt-5" style="margin-left: 2%">Non Gst Invoice</h2>
        </div>
        <div class="form-row">
          <div class="col-md-4">
            <div class="form-group ml-3">
              <label>Organization</label>
              <select class="form-control" name="orgName" >
                <option></option>
                ${organizationOptions}
              </select>
            </div>
          </div>
          <div class="col-md-4">
            <div class="form-group ml-4">
              <label>From Date </label>
              <input type="text" name="fromDate" id="datepicker" class="form-control fromDate" style="margin-left: 2%;" placeholder = "select from date">
            </div>
          </div>
          <div class="col-md-4">
           <div class="form-group ml-4">
             <label>To Date </label>
             <input type="text" name="toDate" id="datepicker1" class="form-control toDate" style="margin-left: 2%;" placeholder = "select to date">
           </div>
         </div>
       </div>
       <div class="form-row">
         <div class="form-group col-md-4">
          <aside class="col-lg-12 ">
           <label for="inputAddress">Terms :</label>
           <button type="button" onClick="addTerm();">+</button>
           <div class="sidebar" id="terms" >
             <input type="text" name="terms" class="form-control" id="inputAddress">		
           </div>
         </aside>
       </div>	
       <div class="col-md-4">
         <div class="form-group ml-3">
           <label>Discount</label>
           <input type="text" name="discount" class="form-control">
         </div>
       </div>
       <div class="col-md-4">
         <div class="ph-20 feature-box text-left object-non-visible" data-animation-effect="fadeInDownSmall" data-effect-delay="100">
          <br>
          <button type="submit" data-toggle="collapse" class="btn btn-default">Non Gst Invoice</button>
          <button type="submit" data-toggle="collapse" class="btn btn-default" formaction="generateGST">Gst Invoice</button>
        </div>
      </div>
    </div>
  </form>
</div>
</div>
<div class="space"></div>
<div class="container">
</div>
<div class="space"></div>
<footer class="section footer">
  <div class="container">
    <div class="footer-content">
    </div>
  </div>
</footer>
<div class="space"></div>
<footer id="footer" class="clearfix ">
  <div class="subfooter">
    <div class="container">
      <div class="subfooter-inner">
        <div class="row">
          <div class="col-md-12">
          </div>
        </div>
      </div>
    </div>
  </div>
</footer>
</div>
<!-- Jquery and Bootstap core js files -->
<script src="plugins/jquery.min.js"></script>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<!-- Magnific Popup javascript -->
<script src="plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
<!-- Appear javascript -->
<script src="plugins/waypoints/jquery.waypoints.min.js"></script>
<script src="plugins/waypoints/sticky.min.js"></script>
<!-- Count To javascript -->
<script src="plugins/countTo/jquery.countTo.js"></script>
<!-- Slick carousel javascript -->
<script src="plugins/slick/slick.min.js"></script>
<!-- Initialization of Plugins -->
<script src="js/template.js"></script>
<!-- Custom Scripts -->
<script src="js/custom.js"></script>


<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
<script src="plugins/waypoints/jquery.waypoints.min.js"></script>
<script src="plugins/waypoints/sticky.min.js"></script>
<script src="plugins/countTo/jquery.countTo.js"></script>
<script src="plugins/slick/slick.min.js"></script>

<script src="js/template.js"></script>
<script src="js/custom.js"></script>

<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script src="plugins/printThis.js"></script>





<script src="template/plugins/rs-plugin-5/js/extensions/revolution.extension.slideanims.min.js"></script>
<script src="template/plugins/rs-plugin-5/js/extensions/revolution.extension.slideanims.min.js"></script>
<script src="template/plugins/rs-plugin-5/js/extensions/revolution.extension.actions.min.js"></script>
<script src="template/plugins/rs-plugin-5/js/extensions/revolution.extension.layeranimation.min.js"></script>
<script src="template/plugins/rs-plugin-5/js/extensions/revolution.extension.kenburn.min.js"></script>

<script>
  $( function() {
    $( "#datepicker" ).datepicker({
      dateFormat: 'dd/mm/yy'
    });
    $( "#datepicker1" ).datepicker({
      dateFormat: 'dd/mm/yy'
    });
  } );
</script>

<script>

  var i = 0;

  function addTerm()
  {
	  if(i<5)
	  {
	    var toAppend = "<br/><input type=\"text\" name=\"terms\" class=\"form-control\" id=\"inputAddress\">";
	
	    $('#terms').append(toAppend);
		}
		else
		{
			alert("Can not add more than 5 terms..!!");		
		}
    i++;
  }
</script>

</body>
</html>



