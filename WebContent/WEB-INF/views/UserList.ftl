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
      <header class="header fixed fixed-desktop clearfix">
        <div class="container">
          <div class="row">
            <div class="main col-lg-4">
              <div id="logo" class="logo">
                <img src="images/img/logo.jpg" alt="" style="width: 200px;height: 55px;margin-top: 20px;margin-left: -20px;">
              </div>
            </div>
            <div class="main col-lg-2">
            </div>
            <div class="main col-lg-4">
              <div class="row">
                <a class="font-weight-bold text-uppercase p-1 bg-light border rounded border-info" href="home" style="margin-top: 13%;margin-left: 40%;">Home</a>
                <a class="font-weight-bold text-uppercase p-1 bg-light border rounded border-info" href="listOrders" style="margin-left: 5%;margin-top: 13%;">Show Orders</a>
              </div>
            </div>
            <div class="main col-lg-2">
             <form class="form-horizontal mt-4 " action="logout" method="POST">       
               <button type="submit" class="btn btn-group btn-default btn-animated mt-4">Log Out <i class="fa fa-user"></i></button>
             </form>
           </div>
         </div>
       </div>
     </header>
   </div>
   <section class="main-container">
    <div class="container">
      <h2 class="mt-5">Pending Registration Requests</h2>
      <div id="orders">
      </div>
      <table class="table table-colored">
       <thead>
         <tr>
           <th>Searial No</th>
           <th>Org Name</th>
           <th>City</th>
           <th>Name</th> 
           <th></th>
           <th></th>   	 
         </tr>
       </thead>
       <tbody>
         <div id="list">
          ${userList}
          <div>
          </tbody>
        </table>
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
  </footer>
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
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="plugins/printThis.js"></script>
<script>
	function userList(){
   var ajaxREq = $.ajax({
     url : 'listUser',
     type : 'GET',
     success : function(data) 
     {
      $('#list').html(data);
    }
  });
 }
</script>
</body>
</html>
