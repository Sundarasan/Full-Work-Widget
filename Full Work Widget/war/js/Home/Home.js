var onDocumentReadyHome = function() {
	
	console.log( '\n--> onDocumentReadyHome()' );
	
	// FIREFOX
    $(window).bind("online", ApplicationOnline.applicationBackOnline); 
    $(window).bind("offline", ApplicationOffline.applicationOffline);

   /* //IE
    window.onload = function() {
        document.body.ononline = ApplicationOnline.applicationBackOnline;
        document.body.onoffline = ApplicationOffline.applicationOffline;
    } */
}

$(document).ready( onDocumentReadyHome );