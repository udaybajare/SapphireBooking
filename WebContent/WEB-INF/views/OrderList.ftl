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
  <link href="css/jquery-ui.css" rel="stylesheet">
  
</head>

<body class=" ">

  <div class="scrollToTop circle"><i class="fa fa-angle-up"></i></div>

  <div class="page-wrapper">
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
          <option value="orderDate">Date Range</option>
          
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

          <option value="Complete" >Completed</option>
          <option value="Rejected" >Rejected</option>
        </select>
        <input type="text" id="datepicker" class="form-control fromDate" style="margin-left: 2%;" placeholder = "select from date">
        <input type="text" id="datepicker1" class="form-control toDate" style="margin-left: 2%;" placeholder = "select to date">
      </div>

      <button type="button" class="btn btn-default" onClick="searchOrder()" style="margin-left: 2%;">Submit</button>
      ${generateButton}
      <input type="button" class="btn btn-default" onClick="addToPrintAreaAndPrint()" style="margin-left: 2%;" value="Print Selected"/>
    </form>
    <div class="alert alert-success" id="success-alert" style="display: none;">
      <button type="button" class="close" data-dismiss="alert">x</button>
      <strong>Added</strong> to Print List.
    </div>
    <div class="alert alert-success" id="removed-alert" style="display: none;">
      <button type="button" class="close" data-dismiss="alert">x</button>
      <strong>Removed</strong> from Print List.
    </div>    
    <div id="cominePrint" style="display:none;">
    </div>	
    <div id="orders">
      ${orderList}
    </div>
  </div>
</script>
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

<script src="plugins/jquery-3.3.1.min.js"></script>
<script src="plugins/jquery-ui.min.js"></script>
<script src="bootstrap/js/bootstrap.bundle.min.js"></script>
<script src="plugins/magnific-popup/jquery.magnific-popup.min.js"></script>
<script src="plugins/waypoints/jquery.waypoints.min.js"></script>
<script src="plugins/waypoints/sticky.min.js"></script>
<script src="plugins/countTo/jquery.countTo.js"></script>
<script src="plugins/slick/slick.min.js"></script>

<script src="js/template.js"></script>
<script src="js/custom.js"></script>

<script src="plugins/printThis.js"></script>

<script>
  function showAdded()
  {

    $("#success-alert").fadeTo(2000, 500).slideUp(500, function() {
      $("#success-alert").slideUp(500);
    });


  }
  
  function showRemoved()
  {  

    $("#removed-alert").fadeTo(2000, 500).slideUp(500, function() {
      $("#removed-alert").slideUp(500);
    });

  } 
</script>
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
     
     selector ="";
     selectorVal = "";
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
      
      var dataArray = data.split("%%%");
      
      var modalContent = "";
      var entryContent = "";
      
      for(var i=0;i < dataArray.length; i++)
      {
      	if(i%2===0)
      	{
      		entryContent = entryContent + dataArray[i]; 
      	}
      	else
      	{
      		modalContent = modalContent + dataArray[i]; 
      	}
      }
      
      $('#orders').html(entryContent);
      $('body').append(modalContent);

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
   var items = $(this.form).closest('.modal-body').find('input[name="itemPrice"]');
   
   var itemVals = "";
   for(var i=0;i<items.length;i++)
   {
   	itemVals = itemVals + items[i].value+",";
   }
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
 data : {'totalAmount' : totalAmount, 'comment' : comment, 'orderId': orderId , 'rSourcing': rSourcing, 'lSourcing': lSourcing,'updatedItemPrice':itemVals},
 success: function(data) 
 {
   console.log(" Received data from BE");
   console.log(data);							
 }
});

});
</script>
<script>

  function updatePrice(thisObj) 
  {		
   var that = thisObj.closest('.modal-body');	
   var items = that.find('input[name="itemPrice"]');
   var total = 0;
   var k = 0;
   for(var i=0; i<items.length; i++)
   {
     total = parseFloat(total) + parseFloat(items[i].value);
     
     try
     {
       thisObj.closest('div').find("span[class^=orderId]")[k].innerHTML = 'Rate:'+items[i].value;
       thisObj.closest('div').find("span[class^=orderId]")[++k].innerHTML = 'Rate:'+items[i].value;
     }
     catch(er)
     {
     	console.log(er);
     }
     k++;
   }
   
   var itemPrints = thisObj.closest('div').find("span[class^=orderId]");
   
   for(var j=0;j<itemPrints.length;j++)
   {
     console.log(itemPrints[j].innerHTML);
   }
   
   var totalAmountEle = that.find('form').find('input[name="totalAmount"]')[0];
   totalAmountEle.value = total;
   
   console.log(totalAmountEle);
 }

</script>
<script>
  var itemIdToPrint = [];
  
  function addItemToPrint(itemIdToSelect)
  {
    itemIdToPrint[itemIdToPrint.length] = itemIdToSelect;

    alert("Added to Print List..!!");
    console.log(itemIdToPrint);
  }

  function removeFromPrintList(itemIdToRemove)
  {
    itemIdToPrint = $.grep(itemIdToPrint, function(value) {
      return value != itemIdToRemove;
    });

   
    alert("Removed from Print List..!!");
    console.log(itemIdToPrint);
  }

  function addToPrintAreaAndPrint()
  {
    for(var k=0;k<itemIdToPrint.length;k++)
    {
      $('#cominePrint').append($('#'+itemIdToPrint[k]).html());  
    }
    
    printItem('cominePrint');

    itemIdToPrint = [];
  }

</script>
</body>
</html>
