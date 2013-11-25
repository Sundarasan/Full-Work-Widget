<!DOCTYPE html>
<html lang="en">
	<head>
    	<meta charset="utf-8">
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
   		<meta name="description" content="FULL Authentication">
    	<meta name="author" content="FULL">
    	
    	<link rel="shortcut icon" href="icon/full-logo-black-yellow.png">

    	<title>Sign in | FULL Work Widget</title>
    	
    	<!-- CSS -->
    	
    	<!-- Library CSS -->
    	<link href="css/Libraries/bootstrap.css" rel="stylesheet">

    	<!-- Template CSS -->
    	<link href="css/Template_CSS/1.css" rel="stylesheet">
    	<link href="css/Template_CSS/global.css" rel="stylesheet">

    	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    	<!--[if lt IE 9]>
      	<script src="../../assets/js/html5shiv.js"></script>
      	<script src="../../assets/js/respond.min.js"></script>
    	<![endif]-->
    	
    	<!-- Scripts -->
    	
    	<!-- Library Scripts -->
    	<script src="js/Libraries/jquery.js"></script>
    	
    	<!-- Template Scripts -->
    	
    	<!-- Helper Scripts -->
    	<script src="js/Helper_JS/IndexedDBHelper.js" type="text/javascript"></script>
    	
    	<script type="text/javascript">
    	
    		var afterOpenDb = function() {
    			IndexedDBHelper.clearObjectStore( ['tasks', 'createdTasks', 'updatedTasks', 'deletedTasks', 'account', 'persons'] );
    		}
    	
    		IndexedDBHelper.openDb( 'taskDb', 1, ['tasks', 'createdTasks', 'updatedTasks', 'deletedTasks', 'account', 'persons'], afterOpenDb );
    	
    	</script>
    	
  	</head>

  	<body> 

	  	<div class="container padding-top-10" style="margin-right: auto; margin-left: auto; padding-left: 15px; padding-right: 15px;" >
	  		<div class="jumbotron" style="border-radius: 6px;" >

		     	<!-- <div class="header">
		        	<ul class="nav nav-pills pull-right">
		          		<li class="active"><a href="#">Home</a></li>
		          		<li><a href="#">About</a></li>
		          		<li><a href="#">Contact</a></li>
		        	</ul>
		        	<h3 class="text-muted">FULL</h3>
		     	</div> -->

	     	
		      	<div class="row-fluid" align="center">
		      		<div class="span12 circle"></div>
		      	</div>
		      	<div class="row-fluid">
		      		<div class="span12">
		      			<img id="full-logo-back" src="img/full-logo-white.png">
		      		</div>
		      	</div>
		      	<div class="row-fluid">
		        	<a href="${googleOAuthUrl}"><img id="google-plus-button-img" src="img/google-plus-buttons/Red-signin_Long_base_44dp.png" style="width: 182px; height: 50px;" onmouseover="this.src='img/google-plus-buttons/Red-signin_Long_hover_44dp.png'" onmouseout="this.src='img/google-plus-buttons/Red-signin_Long_base_44dp.png'" ></a>
		    	</div>

		      	<div class="row-fluid text-size-small">
		      		<span class="color-grey">Trouble Logging in </span><a href="#">Click here</a>
		      	</div>
		    
		      	<!-- <div class="footer">
		        	<p>© Company 2013</p>
		      	</div> -->

	      	</div>

	    </div>
	    
		<script src="js/Libraries/jquery.js"></script>
	</body>
</html>