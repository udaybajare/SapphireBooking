<!DOCTYPE html>
<html dir="ltr" lang="zxx">

  <head>
    <meta charset="utf-8">
    <title>The Project | Forms</title>
    <meta name="description" content="The Project a Bootstrap-based, Responsive HTML5 Template">
    <meta name="author" content="author">

    <!-- Mobile Meta -->
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <!-- Favicon -->
    <link rel="shortcut icon" href="images/favicon.ico">

    <!-- Web Fonts -->
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,300i,400,400i,500,500i,700,700i" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Raleway:300,400,700" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Pacifico" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=PT+Serif" rel="stylesheet">

    <!-- Bootstrap core CSS -->
    <link href="bootstrap/css/bootstrap.css" rel="stylesheet">

    <!-- Font Awesome CSS -->
    <link href="fonts/font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Plugins -->
    <link href="plugins/magnific-popup/magnific-popup.css" rel="stylesheet">
    <link href="css/animations.css" rel="stylesheet">
    <link href="plugins/slick/slick.css" rel="stylesheet">
    
    <!-- The Project's core CSS file -->
    <!-- Use css/rtl_style.css for RTL version -->
    <link href="css/style.css" rel="stylesheet" >
    <!-- The Project's Typography CSS file, includes used fonts -->
    <!-- Used font for body: Roboto -->
    <!-- Used font for headings: Raleway -->
    <!-- Use css/rtl_typography-default.css for RTL version -->
    <link href="css/typography-default.css" rel="stylesheet" >
    <!-- Color Scheme (In order to change the color scheme, replace the blue.css with the color scheme that you prefer) -->
    <link href="css/skins/light_blue.css" rel="stylesheet">

    <!-- Custom css -->
    <link href="css/custom.css" rel="stylesheet">
    
  </head>

  <!-- body classes:  -->
  <!-- "boxed": boxed layout mode e.g. <body class="boxed"> -->
  <!-- "pattern-1 ... pattern-9": background patterns for boxed layout mode e.g. <body class="boxed pattern-1"> -->
  <!-- "transparent-header": makes the header transparent and pulls the banner to top -->
  <!-- "gradient-background-header": applies gradient background to header -->
  <!-- "page-loader-1 ... page-loader-6": add a page loader to the page (more info @components-page-loaders.html) -->
  <body class=" ">

    <!-- scrollToTop -->
    <!-- ================ -->
    <div class="scrollToTop circle"><i class="fa fa-angle-up"></i></div>

    <!-- page wrapper start -->
    <!-- ================ -->
    <div class="page-wrapper">
      <!-- header-container start -->
      <div class="header-container">
        <!-- header-top start -->
        <!-- classes:  -->
        <!-- "dark": dark version of header top e.g. class="header-top dark" -->
        <!-- "colored": colored version of header top e.g. class="header-top colored" -->
        <!-- ================ -->
        <div class="header-top dark">
          <div class="container">
            <div class="row">
              <div class="col-3 col-sm-6 col-lg-9">
                <!-- header-top-first start -->
                <!-- ================ -->
                
                <!-- header-top-first end -->
              </div>
              <div class="col-9 col-sm-6 col-lg-3">

                <!-- header-top-second start -->
                <!-- ================ -->
                
                <!-- header-top-second end -->
              </div>
            </div>
          </div>
        </div>
        <!-- header-top end -->

        <!-- header start -->
        <!-- classes:  -->
        <!-- "fixed": enables fixed navigation mode (sticky menu) e.g. class="header fixed clearfix" -->
        <!-- "fixed-desktop": enables fixed navigation only for desktop devices e.g. class="header fixed fixed-desktop clearfix" -->
        <!-- "fixed-all": enables fixed navigation only for all devices desktop and mobile e.g. class="header fixed fixed-desktop clearfix" -->
        <!-- "dark": dark version of header e.g. class="header dark clearfix" -->
        <!-- "centered": mandatory class for the centered logo layout -->
        <!-- ================ -->
        <!-- header end -->
      </div>
      <!-- header-container end -->
      <!-- breadcrumb start -->
      <!-- ================ -->
   
      <!-- breadcrumb end -->

      <!-- main-container start -->
      <!-- ================ -->
      <section class="container">

        <div class="container">
          <div class="row">

            <!-- main start -->
            <!-- ================ -->
            <div class="main col-md-6">
               <div id="logo" class="logo">
                    <img src="images/img/logo.jpg" alt="" style="width: 200px;height: 55px;margin-top: 20px;margin-left: -20px;">
                  </div>
              <!-- page-title start -->
              <!-- ================ -->
              
            </div>
            <div class="main col-md-3">
              <div class="row">
               
            <a class="font-weight-bold text-uppercase p-1 bg-light border rounded border-info" href="home" style="margin-top: 17%;margin-left: 2%">Home</a>
          
          
            <a class="font-weight-bold text-uppercase  p-1 bg-light border rounded border-info" href="bookorder" style="margin-left: 2%;margin-top: 17%;">Book Now</a>
          
          
            <a class="font-weight-bold text-uppercase  p-1 bg-light border rounded border-info" href="listOrders" style="margin-left: 2%;margin-top: 17%;">Show Orders</a>
          
            </div>
            </div>
            
            <div class="main col-md-3">
              <!-- page-title end -->
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
          <form action="addOrganization" method="POST">
            <div class="form-row">
              <h2 class="page-title text-default text-md-center mt-5" style="margin-left: 2%">ADD ORGANISATION</h2>
            </div>
            <div class="form-row">
           
              <div class="col-md-2">
                <div class="form-group ml-3">
                  <label >Organization Name</label>
                  <input type="text" name="orgName" class="form-control " >
                </div>
              </div>
              <div class="col-md-3">
                <div class="form-group ">
                  <label >Organization Contact</label>
                  <input type="text" name="orgContact" class="form-control " >
                </div>
              </div>
              <div class="col-md-3">
                <div class="form-group ">
                  <label >Organization Address</label>
                  <input type="text" name="orgAddress" class="form-control " >
                </div>
              </div>
              <div class="col-md-3">
                <div class="form-group ">
                  <label >Customer Number</label>
                  <input type="text" name="custNumber" class="form-control" value="${nextCustNumber}">
                </div>
              </div>			  
            </div>	
			<div class="form-row">
			<div class="col-md-3 ">
			<div class="ph-20 feature-box text-left object-non-visible" data-animation-effect="fadeInDownSmall" data-effect-delay="100">
				<br>
				<button type="submit" data-toggle="collapse" class="btn btn-default" >Add Organization</button>
			</div>	
			</div>
			</div>
			</form>
          
        </div>
      </div>
	  
	  
			  
      <!-- section end -->
      <div class="space"></div>
      <!-- section start -->
      <!-- ================ -->

      <!-- section end -->
      <div class="container">
        
      </div>
      <!-- section start -->
      <!-- ================ -->
      
      <!-- section end -->
      <div class="space"></div>
      <!-- section start -->
      <!-- ================ -->
      <footer class="section footer">
        <div class="container">
          <div class="footer-content">
          </div>
        </div>
      </footer>
      <!-- section end -->
      <div class="space"></div>

      <!-- footer top start -->
      <!-- ================ -->
      
      <!-- footer top end -->
      <!-- footer start (Add "dark" class to #footer in order to enable dark footer) -->
      <!-- ================ -->
      <footer id="footer" class="clearfix ">

        <!-- .footer start -->
        <!-- ================ -->
        <!-- .footer end -->

        <!-- .subfooter start -->
        <!-- ================ -->
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
        <!-- .subfooter end -->

      </footer>
      <!-- footer end -->
    </div>
    <!-- page-wrapper end -->

    <!-- JavaScript files placed at the end of the document so the pages load faster -->
    <!-- ================================================== -->
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

  <script src="template/plugins/rs-plugin-5/js/extensions/revolution.extension.slideanims.min.js"></script>
<script src="template/plugins/rs-plugin-5/js/extensions/revolution.extension.slideanims.min.js"></script>
<script src="template/plugins/rs-plugin-5/js/extensions/revolution.extension.actions.min.js"></script>
<script src="template/plugins/rs-plugin-5/js/extensions/revolution.extension.layeranimation.min.js"></script>
<script src="template/plugins/rs-plugin-5/js/extensions/revolution.extension.kenburn.min.js"></script>

</body>
</html>
