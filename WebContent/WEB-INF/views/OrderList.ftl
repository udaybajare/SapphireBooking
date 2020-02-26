<!DOCTYPE html>
<html dir="ltr" lang="zxx">

  <head>
    <meta charset="utf-8">
    <title>The Project | Tables</title>
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
              <div class="col-md-4">
           
               

                  <div id="logo" class="logo">
                    <img src="images/img/logo.jpg" alt="" style="width: 100px;height: 55px;margin-top: 10px;margin-left: -20px;">
                  </div>
                </div>
              
                <div class="col-md-6">
                  <div class="row">
                    <a class="font-weight-bold text-uppercase p-1 bg-light border rounded border-default" href="home" style="margin-top: 3%;margin-left: 50%;">Home</a>
          
          
                   <a class="font-weight-bold text-uppercase p-1 bg-light border rounded border-default" href="bookorder" style="margin-left: 5%;margin-top: 3%;">Book Now</a>
                   </div>
                </div>
                <div class="col-md-2">
                	<form class="form-horizontal mt-4 " action="logout" method="POST">  
                   		<button type="submit" class="btn btn-group btn-default btn-animated" style="margin-top: 8%;margin-left: 10%;">Log Out <i class="fa fa-user"></i></button>
                   	</form>	
                </div>

                </div>
              </div>
                  
               
             
        </header>
      </div>
      
      <section class="main-container">

        <div class="container">
              <h2 class="mt-5">Search Orders</h2>
              <form class="form-inline">
                <label class="sr-only" for="inlineFormInput">Search By:</label>
                <select class="form-control" id="criteriaSection" onchange="showValSec($('#criteriaSection').val());">
    				<option></option>
					<option value="orderId">Order ID</option>
					<option value="organizationName">Organization</option>
					<option value="userName">Booked By</option>
					<option value="status">Order Status</option>
					
				</select>
                <label class="sr-only" for="inlineFormInputGroup">Value</label>
                <div class="input-group mb-2 mr-sm-2 mb-sm-0">
                  <input type="text" id="orderId" class="form-control" style="display:none;margin-left: 2%;">
				  <input type="text" id="userName" class="form-control" style="display:none;margin-left: 2%;">
				  <select class="form-control" id="organizationName" style="display:none;margin-left: 2%;">
					${organizationOptions}
				  </select>
				  <select class="form-control" id="status" style="display:none;margin-left: 3%;">
				  <option value="Pending">Pending</option>
				  <option value="accepted">Accepted</option>
				  <option value="processing" >Processing</option>
				  <option value="readyToDeliver">Ready To Deliver</option>
				  <option value="delivered" >Delivered</option>
				  </select>
				  <input type="text" id="datepicker" class="form-control fromDate" style="margin-left: 2%;" placeholder = "select from date">
				  <input type="text" id="datepicker1" class="form-control toDate" style="margin-left: 2%;" placeholder = "select to date">
                </div>

                <button type="button" class="btn btn-default" onClick="searchOrder()">Submit</button>
                ${generateButton}
              </form>
        <div id="orders">
     	${orderList}
     	</div>
        </div>

      </section> 
        <div class="subfooter">

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

    // <script src="plugins/jquery.min.js"></script>
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
  function showValSec(actual)
  {
	var inputNames = ["orderId", "userName", "organizationName", "status", "fromDate", "toDate"];
	
	$(inputNames).each(function( index, element ) {
		$('#'+element).hide();
	});
	
	console.log("Showing only : "+actual);
	
	if(actual=="orderDate")
	{
		$('.fromDate').show();
		$('.toDate').show();
	}
	else
	{
		$('#'+actual).show();
	}
  }
</script>
<script>

function printItem(itemId)
{

	$('#'+itemId).css("display","block");
	console.log(itemId);
	
	$('#'+itemId).printThis();
	
	setTimeout(function(){
  		$('#'+itemId).css("display","none");
	}, 5000);
	
}
</script>
<script>
  function searchOrder(){			  
  var selector = $('#criteriaSection').val();
  
  var selectorVal = "";
  
  if(selector=="orderDate")
  {
  	selectorVal = $('.fromDate').val()+"-"+$('.toDate').val();
  }
  else
  {
  	selectorVal = $('#'+selector).val();
  }
  
  
  var fromDateVal =  $('.fromDate').val();
  var toDateVal = $('.toDate').val();
  
  var ajaxReq = $.ajax({
	  url : 'listOrdersHTML',
	  type : 'POST',
	  data : {'criteria' : selector, 'criteriaValue' : selectorVal, 'fromDate':fromDateVal , 'toDate':toDateVal},
	  success: function(data) 
		{
		console.log(" Received data from BE");
				$('#orders').html(data);							
	    }
	});
  }
</script>


<script>
  function generateReport(){
  	
  
  var selector = $('#criteriaSection').val();
  
  var selectorVal = "";
  
  if(selector=="orderDate")
  {
  	selectorVal = $('.fromDate').val()+"-"+$('.toDate').val();
  }
  else
  {
  	selectorVal = $('#'+selector).val();
  }
  
  var newForm = jQuery('<form>', {
        'action': 'generateReport',
        'target': '_top',
        'method': 'POST'
    }).append(jQuery('<input>', {
        'name': 'criteria',
        'value': selector,
        'type': 'hidden'
    }).append(jQuery('<input>', {
        'name': 'criteriaValue',
        'value': selectorVal,
        'type': 'hidden'
    })));
    
    $(document.body).append(newForm);
    
    newForm.submit();
    
    
   
  }
</script>

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
$('.updateOrder').on('click',function updateTotal()
{

	var totalAmount = $(this.form.elements)[0].value;
	var comment = $(this.form.elements)[1].value;
	var orderId = $(this.form.elements)[2].value;
	var sourcing = $(this.offsetParent.children)[0].children[0].value;
	
	var para = $(this.offsetParent.children);
	
	var lSourcing = '';
	var rSourcing = '';
	
	for(var j = 1; j< $(this.offsetParent.children).length; j++)
	{
		if(para[j].tagName==='P')
		{
			if(para[j].children[0] !== undefined && para[j].children[0].name==='sourcing')
			{
				sourcing = sourcing + "," +para[j].children[0].value;
			}
		}
		else if(para[j].tagName==='TABLE')		
		{
			var trs = para[j].rows;

							$.each(trs, function(a)
							{
								var cells = $(this).children('td');
									$.each(cells, function(b){
										var ab = $(this).children()[0];
											if(ab!==undefined)
											{
												if(ab.name==='rSourcing')
												{
													rSourcing = rSourcing + ab.value+',';
												}
												else if(ab.name==='lSourcing')
												{
													lSourcing = lSourcing + ab.value+',';
												}
											}
											
											
									});		
									
							});					
		}
		
	}
	

	var ajaxReq = $.ajax({
	  url : 'updateTotal',
	  type : 'POST',
	  data : {'totalAmount' : totalAmount, 'comment' : comment, 'orderId': orderId , 'rSourcing': rSourcing, 'lSourcing': lSourcing},
	  success: function(data) 
		{
			console.log(" Received data from BE");
			console.log(data);							
	    }
	});

});
</script>
</body>
</html>
