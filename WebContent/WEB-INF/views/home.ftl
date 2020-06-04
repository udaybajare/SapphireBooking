<!DOCTYPE html>
<html dir="ltr" lang="zxx">

<head>
	<meta charset="utf-8">
	<title>Sapphire Lenses</title>
	<meta name="description" content="">
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


<body class=" ">


	<div class="scrollToTop circle"><i class="fa fa-angle-up"></i></div>


	<div class="page-wrapper">
		<div class="header-container">
			<header class="header fixed fixed-desktop clearfix">
				<div class="container">
					<div class="row">
						<div class="col-md-4 hidden-md-down">
							<div class="header-first clearfix">

								<div id="logo" class="logo">
									<img src="images/img/logo.jpg" alt="" style="width: 200px;height: 55px;margin-top: 20px;margin-left: -20px;">
								</div>
							</div>                
						</div>
						<div class="col-lg-4 ml-auto">

							<div class="header-second clearfix">
								<form class="form-horizontal" action="logout" method="POST">
									<div class="col-auto ml-auto hidden-md-down">

										<button type="submit" class="btn btn-group btn-default btn-animated" style="margin-left: 50%;">Log Out <i class="fa fa-user"></i></button>

									</div>
								</form>

							</div>

						</div>
					</div>
				</div>
			</div>
		</header>
	</div>



	<!-- section start -->
	<!-- ================ -->


	<div class="section light-gray-bg">
		<div class="container" style="max-width: 90%;margin-top: 0%;">
			<div class="form-row">
				<div class="col-md-12">
					<h1 class="text-default text-center">
						Welcome to Sapphire Lenses
					</h1>
				</div>
			</div>
			<div class="form-row firstRow" style="margin-left: 5%">
				${addOrgOption}
				<div class="col-md-3">
					<div class="form-group has-feedback">
						<form id="registration" action="registrationForm" method="GET">
							<img src="images/img/registration1.png" alt="" width="200" height="200" onClick="$('#registration').submit();">
							<button type="button" class="btn btn-sm btn-default" onClick="$('#registration').submit();" style="margin-top: 13%;margin-left: 12%">View/Update User Details</button>
						</form>
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group has-feedback">
						<form id="bookorder" action="bookorder" method="GET">
							<img src="images/img/Order1.png" alt="" width="200" height="200" onClick="$('#bookorder').submit();">
							<button type="button" class="btn btn-sm btn-default" onClick="$('#bookorder').submit();" style="margin-top: 7%;margin-left: 14%">Order Now</button>
						</form>
					</div>
				</div>
				<div class="col-md-3"> 
					<div class="form-group has-feedback">
						<form id="listOrders" action="listOrders" method="GET">
							<img src="images/img/showOrder5.png" alt="" width="200" height="200" onClick="$('#listOrders').submit();">
							<button type="button" class="btn btn-sm btn-default" onClick="$('#listOrders').submit();" style="margin-top: 3%;margin-left: 14%">Show Orders</button>
						</form>
					</div>
				</div>
			</div>
			<div class="form-row" style="margin-left: 5%">
				<div class="col-md-3">
					<div class="form-group has-feedback">
						${invoiceButton}
					</div>
				</div>
				<div class="col-md-3">
					<div class="form-group has-feedback">
						${userListButton}
					</div>
				</div>
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
								<p class="text-center">Powered by Social Angels Digital Solution Pvt Ltd.</p>
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

	<script>
		$( document ).ready(function(){
			if($('.firstRow').find('div.col-md-3').length===3)
			{

				var item = $('.firstRow').find('div.col-md-3')[0];
				item.setAttribute('class','col-md-4');
				
				item = $('.firstRow').find('div.col-md-3')[0];
				item.setAttribute('class','col-md-4');
				
				item = $('.firstRow').find('div.col-md-3')[0];
				item.setAttribute('class','col-md-4');
				
			}

			
		});
	</script>
</body>
</html>
