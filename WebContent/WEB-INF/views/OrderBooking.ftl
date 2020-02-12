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
      <!-- The Modal -->

      <div id="myBtn" class="modal" >
        <div class="modal-dialog" role="document">
          <!-- Modal content -->
          <div class="modal-content">
           <div class="modal-header">
             <h4 class="modal-title" >Error </h4>
             <span class="close">&times;</span>  
           </div>
           <div class="modal-body">
            <p>Please Enter required details.</p>
          </div>
        </div>
      </div>
    </div>






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
            <div class="main col-lg-4">
                <div id="logo" class="logo">
                    <img src="images/img/logo.jpg" alt="" style="width: 200px;height: 55px;margin-top: 20px;margin-left: -20px;">
                  </div>
              <!-- page-title start -->
              <!-- ================ -->
             
              </div>
              <!-- page-title end -->
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
      </section>
      <!-- main-container end --> 

  <!-- section start -->
  <!-- ================ -->


  <div class="section light-gray-bg">
    <div class="container" style="max-width:90%;">
          <div class="row">
             <h1 class="page-title mt-5 text-default align-text-bottom" style="margin-left: 2%;">Order Details</h1>
          </div>
      <div class="form-row">
       <table class="table table-colored">

        <thead>
          <tr>          	
            <th>Material</th>
            <th>Type</th>
            <th>Index</th>
            <th>Coating</th>
            <th>Tint</th>
            <th>Quantity (nos.)</th>
            <th>Frame Type</th>
            <th>Fitting</th>
            <th>Cust Order #</th>
          </tr>
        </thead>

        <tbody id="tableContent">
         <tr>
          <td>
            <div class="form-group">
              <select class="form-control" name="material" id="material" >
                <option></option>
                <option value="CR">CR</option>
                <option value="GLASS">Glass</option>
              </select>
            </div>
          </td>
          <td>
            <div class="form-group">
              <select class="form-control" name="type" id="type">
                <option></option>
                <option value="Single Vision" >Single Vision</option>
                <option value="Kt Bifocal" >Kt Bifocal</option>
                <option value="Progressive" >Progressive</option>
                <option value="D Bifocal" >D Bifocal</option>
                <option value="Executive" >Executive</option>
              </select>
            </div>
          </td>
          <td>
            <div class="form-group">
             <input class="form-control" type="text" name="index" id="index" value="" />
           </div>
         </td>
         <td>
          <div class="form-group">
            <select class="form-control" name="coating" id="coating">
              <option></option>
              <option name="UC">UC</option>
              <option name="HC">HC</option>
              <option name="ARC">ARC</option>
            </select>
          </div>
        </td>
        <td>
          <div class="form-group">
            <select class="form-control" name="tint" id="tint">
              <option></option>
              <option value="Clear">Clear</option>
              <option value="PG">PG</option>
              <option value="PG4">PG4</option>
              <option value="PG6">PG6</option>
              <option value="SP2">SP2</option>
              <option value="PB">PB</option>
            </select>
          </div>
        </td>
        <td>
          <div class="form-group">
   <input class="form-control" type="text" name="qtyNos" id="qtyNos" value="1"/>
         </div>
       </td>
       <td>
        <div class="form-group">
          <select class="form-control" name="frameType" id="frameType">
            <option></option>
            <option value="Full Frame">Full Frame</option>
            <option value="Supra Frame">Supra Frame</option>
            <option value="3 Piece">3 Piece</option>
          </select>
        </div>
      </td>
	   <td>
	   		<div class='form-group'>
		  		<select class='form-control' name='fitting' id='fitting'>
	         		<option></option>
	             	<option value='With Fitting'>With Fitting</option>            
					<option value='Without Fitting'>Without Fitting</option>      
				</select>        
			</div>      
		</td>
		<td>
		 	<div class="form-group">
           		<input class="form-control" type="text" name="custOrderNumber" id="custOrderNumber" value=""/>
         	</div>
		</td>
    </tr>            
    <tr  class="table-grey">
     <td>LENS SIDE</td>
     <td>SPH</td>
     <td>CYL</td>
     <td>AXIS</td>
     <td>ADD</td>
     <td>DIA</td>
     <td>SOURCING</td>
     <td></td>
     <td></td>
   </tr>
   <tr>
    <td>R</td>
<td><input class="form-control" type='text' id='rSph' name='rSph' value=' ' /></td>
<td><input class="form-control" type='text' id='rCyl' name='rCyl' value=' ' /></td>
<td><input class="form-control" type='text' id='rAxis' name='rAxis' value=' ' /></td>
<td><input class="form-control" type='text' id='rAdd' name='rAdd' value=' ' /></td>
<td><input class="form-control" type='text' id='rDia' name='rDia' value=' ' /></td>
<td>
	<div class='form-group'>
		<select class='form-control' id='rSourcingSelect' >
			<option></option>
			<option value='Factory Order'>Factory Order</option>            
			<option value='Ready Stock'>Ready Stock</option>          
	</select>        
	</div>      
</td>
  <td> </td>
  <td> </td>
  </tr>
  <tr>
    <td>L</td>
<td><input class="form-control" type='text'  id='lSph' name='lSph' value=' ' /></td>
<td><input class="form-control"  type='text'  id='lCyl' name='lCyl' value=' ' /></td>
<td><input class="form-control" type='text'  id='lAxis' name='lAxis' value=' ' /></td>
<td><input class="form-control" type='text'  id='lAdd' name='lAdd' value=' ' /></td>
<td><input class="form-control" type='text'  id='lDia' name='lDia' value=' ' /></td>
<td>
	<div class='form-group'>
		<select class='form-control' id='lSourcingSelect'>
			<option></option>
			<option value='Factory Order'>Factory Order</option>            
			<option value='Ready Stock'>Ready Stock</option>          
	</select>        
	</div>      
</td>
  <td> </td>
  <td> </td>
  </tr>     
</tbody>
</table>
</div> 
<div class="form-row">
  <div class="col-md-3 ">
   <div class="ph-20 feature-box text-left object-non-visible" data-animation-effect="fadeInDownSmall" data-effect-delay="100">
     <br>
     <button type="button" data-toggle="collapse" id ="myerrorModel" class="btn btn-default" onClick="appendInventory();">Add Item</button>
     
   </div>	
 </div>
</div>
<form id="confirmOrder" action="confirmOrder" method="POST">
  <div class="form-row">
    <div class="col-md-2">
      <div class="form-group">
        <label>Organization</label>
        <select class="form-control" name="orgName">
          <option></option>
          ${organizationOptions}
        </select>
      </div>
    </div>
    <div class="col-md-2">
      <div class="form-group">
        <label>User Name</label>
        <input type="text" name="userName" class="form-control">
      </div>
    </div>
    <div class="col-md-2">
      <div class="form-group ">
        <label >Mobile No</label>
        <input type="text" name="mobileNo" class="form-control">
      </div>
    </div>			  
  </div>	
  <div class="form-row">
   <div class="col-md-12 ">
     <div class="table-responsive">                

      <table id="tableContentMain" class="table inventoryDetails" style="display:none;">
        <thead>
          <tr>
			<th></th>
            <th>Material</th>
            <th>Type</th>
            <th>Index</th>
            <th>Coating</th>
            <th>Tint</th>
            <th>Quantity (nos.)</th>
            <th>Frame Type</th>
            <th>Fitting</th>
            <th>Cust Order #</th>
          </tr>
        </thead>
        <tbody id="tableContentDetails">

        </tbody>
      </table>
    </div>	
  </div>
</div>
</form>

<div class="form-row">
  <div class="col-md-3 ">
			<div class="ph-20 feature-box text-left object-non-visible" data-animation-effect="fadeInDownSmall" data-effect-delay="100">
     <button type="button" id ="reviewAndConfirmButton" class="btn btn-default" data-toggle="modal" data-target="#myModal" onClick="getAndUpdatePrise();" disabled>Review And Confirm Order</button>
     <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
       <div class="modal-dialog" role="document">
         <div class="modal-content">
           <div class="modal-header">
             <h4 class="modal-title" id="myModalLabel">Prise Details as follows : </h4>
             <button type="button" class="close" data-dismiss="modal" aria-label="Close">
               <span aria-hidden="true">&times;</span>
               <span class="sr-only">Close</span>
             </button>
           </div>
           <div class="modal-body">
            <table class="table table-striped">
              <thead>  
               <tr>    
                 <th>Item #</th>
                 <th>Prise</th>  
               </tr>
             </thead>
             <tbody id="priceTableRow">  
               <tr>    
                <td>Total Prise :</td>
                <td id="totalPrice" ></td>
              </tr>
            </tbody>
          </table>
          <div class="separator clearfix"></div>
        </div>
        <div class="modal-footer">
         <button id="confirmButton" type="button" class="btn btn-sm btn-default" onClick="$('#confirmOrder').submit();">Confirm</button>
       </div>
     </div>
   </div>
 </div>
</div>
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
  var i = 0;
  function appendInventory() 
  {

    var material = $('#material').children("option:selected").val();
    var type = $('#type').children("option:selected").val();
    var index = $('#index').val();
    var coating = $('#coating').children("option:selected").val();
    var tint = $('#tint').children("option:selected").val();
    var qtyNos = $('#qtyNos').val();
    var frameType = $('#frameType').children("option:selected").val();
    var fitting = $('#fitting').children("option:selected").val(); 
    var custOrderNumber = $('#custOrderNumber').val();

    var rSph       =     $('#rSph').val();
    var rCyl       =     $('#rCyl').val();
    var rAxis      =     $('#rAxis').val();
    var rAdd       =     $('#rAdd').val();
    var rDia       =     $('#rDia').val();
    var lSph       =     $('#lSph').val();
    var lCyl       =     $('#lCyl').val();
    var lAxis      =     $('#lAxis').val();
    var lAdd       =     $('#lAdd').val();
    var lDia	   =     $('#lDia').val();

	var rFactory = '';
	var rReady = '';
	var lFactory = '';
	var lReady = '';
	
	if($('#lSourcingSelect').children("option:selected").val() === 'Factory Order')
	{
		lFactory = 'selected';
	}
	else if($('#lSourcingSelect').children("option:selected").val() === 'Ready Stock')
	{
		lReady = 'selected';
	}
	
	if($('#rSourcingSelect').children("option:selected").val() === 'Factory Order')
	{
		rFactory = 'selected';
	}
	else if($('#rSourcingSelect').children("option:selected").val() === 'Ready Stock')
	{
		rReady = 'selected';
	}
	
	
    var rowId = "'"+'row'+i+"'";
    if( (material !== "" && type !== "" && index !== "" && tint !== "" && qtyNos !== "" && frameType !== "") && ( rSph !== "" || rCyl !=="" || lSph !== "" || lCyl !== ""))
    {
      var	template = "<tr class="+rowId+">"
      + "    <td> <input type='button' value='x' onClick=removeRow(" + rowId +");></td>" 
      + "    <td> <input type='hidden' name='material' value='"+material+"'></input>"+material+"</td>"
      + "    <td> <input type='hidden' name='type' value='"+type+"'></input>"+type+"</td>"
      + "    <td> <input type='hidden' name='index' value='"+index+"'></input>"+index+"</td>"
      + "    <td> <input type='hidden' name='coating' value='"+coating+"'></input>"+coating+"</td>"
      + "    <td> <input type='hidden' name='tint' value='"+tint+"'></input>"+tint+"</td>"
      + "    <td> <input type='hidden' name='qtyNos' value='"+qtyNos+"'></input>"+qtyNos+"</td>"
      + "    <td> <input type='hidden' name='frameType' value='"+frameType+"'></input>"+frameType+"</td>"
      + "    <td> <input type='hidden' name='fitting' value='"+fitting+"'></input>"+fitting+"</td>"
      + "    <td> <input type='hidden' name='custOrderNumber' value='"+custOrderNumber+"'></input>"+custOrderNumber+"</td>"
      + "    </tr>"
      + "<tr class="+rowId+">"
      + "			<td>LENS SIDE</td>"
      + "			<td>SPH</td>"
      + "			<td>CYL</td>"
      + "			<td>AXIS</td>"
      + "			<td>ADD</td>"
      + "			<td>DIA</td>"
      + "			<td>Sourcing</td>"
      + "		  </tr>"
      + "<tr class="+rowId+">"
      + "		<td>R</td>"
      + "	   <td> <input class='form-control' type='text' name='rSph'  value='"+rSph+"' /></td>"
      + "	   <td> <input class='form-control' type='text' name='rCyl'  value='"+rCyl+"' /></td>"
      + "	   <td> <input class='form-control' type='text' name='rAxis' value='"+rAxis+"' /></td>"
      + "	   <td> <input class='form-control' type='text' name='rAdd'  value='"+rAdd+"' /></td>"
      + "	   <td> <input class='form-control' type='text' name='rDia'  value='"+rDia+"' /></td>"
      + "	   <td>	<div class='form-group'>"
      + "		  	<select class='form-control' name='rSourcing' id='sourcing'>"
      + "           	<option></option>"
      + "               <option value='Factory Order' "+rFactory+">Factory Order</option>"            
      + "				<option value='Ready Stock' "+rReady+">Ready Stock</option>"          
      + "			</select>"        
      + "			</div>"      
      + "		</td>"
      + "		<td></td>"
      + "		<td></td>"
      + "		<td></td>"
      + "		  </tr>"
      + "<tr class="+rowId+">"
      + "		<td>L</td>"	
      + "	   <td> <input class='form-control' type='text' name='lSph'  value='"+lSph+"' /></td>"
      + "	   <td> <input class='form-control' type='text' name='lCyl'  value='"+lCyl+"' /></td>"
      + "	   <td> <input class='form-control' type='text' name='lAxis' value='"+lAxis+"' /></td>"
      + "	   <td> <input class='form-control' type='text' name='lAdd'  value='"+lAdd+"' /></td>"
      + "	   <td> <input class='form-control' type='text' name='lDia'  value='"+lDia+"' /></td>"
      + "	   <td>	<div class='form-group'>"
      + "		  	<select class='form-control' name='lSourcing' id='sourcing'>"
      + "           	<option></option>"
      + "               <option value='Factory Order' "+lFactory+" >Factory Order</option>"            
      + "				<option value='Ready Stock' "+lReady+">Ready Stock</option>"          
      + "			</select>"        
      + "			</div>"      
      + "		</td>"
      + "		<td></td>"
      + "		<td></td>"
      + "		<td></td>"
      + "		  </tr>"
      ;


      $('.inventoryDetails').css("display","block");          	
      $('#tableContentDetails').append(template);

      i++;

    //Enable Review and Confirm Order button
    $('#reviewAndConfirmButton').removeAttr('disabled');
  }
  else
  {
 // alert('inside else.......')
  
  
            var title = "Error";
            var body = "Please enter required details.";
 
            $("#myBtn.modal-title").html(title);
            $("#myBtn .modal-body").html(body);
            $("#myBtn").modal("show");
   
   //$(function () {
     //   $("#myerrorModel").click(function () {         
            
       // });
   // });
  
   //$("#myErrorModal").show();

   
  }
}   


 function removeRow( thisObj )
 {
   $('.'+thisObj).remove();
 }

</script>
<script>

  function getAndUpdatePrise()
  {

   $('#confirmButton').show();

   jQuery.ajax({
    url: 'getItemWisePrice',
    method: 'POST',
    data: $('#confirmOrder').clone(true,true).serialize()
  }).done(function (response) 
  {
   var priseArray = response.split(',');
   var total = 0;
   $('#priceTableRow').html("<tr> <td>Total Prise :</td> <td id='totalPrice'></td> </tr>");
   $.each( priseArray, function( key, value) 
   {
	if(value==='0')
	{
	 	total = 'Your Order will be accepted. Please contact Sapphire Lens for the final price.';
	}else if(value != '')
    {
	     var itemNo = parseInt(key)+ 1;
	     $('#priceTableRow').append('<tr><td>Item '+itemNo+'</td><td>'+value+'</td></tr>');
	     console.log('total inside is : ' + total);
	     total += parseInt(value);
    }		

 });

   console.log('total is : ' + total);
   $('#totalPrice').html(total);

 }).fail(function ()
 {
   console.log('Arrrrrrr');
 });


}
</script>
<script>
  $( document ).ready(function(){
    setTimeout(function(){$('#customMessage').hide();}, 5000);
  });
</script>
</body>
</html>
