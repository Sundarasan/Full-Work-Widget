var DateHelper = {
		
		weekday : ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
	
		getDate : function( dateInDecimal ) {
			
			if( dateInDecimal ) {
				
				var dateObject = new Date( dateInDecimal );
				var date = DigitHelper.convertToDigits( dateObject.getDate(), 2 );
				var month = DigitHelper.convertToDigits( eval( dateObject.getMonth() + 1 ), 2 );
				var year = dateObject.getFullYear();
				var day = this.weekday[ dateObject.getDay() ];
				var hours = DigitHelper.convertToDigits( this.get12HoursFrom24Hours( dateObject.getHours() ), 2 );
				var minutes = DigitHelper.convertToDigits( dateObject.getMinutes(), 2 );
				var meridian = this.findMeridianFromHours( dateObject.getHours() );
			
				return date + '/' + month + '/' + year + ' ' + day + ' ' + hours + ':' + minutes + ' ' + meridian;
				
			} else {
				
				return 'Not set';
				
			}
			
		},
		
		getDateToSend : function( dateString ) {		/*	'dd/mm/yyyy day hh:ss a' --> 'mm/dd/yyyy hh:ss a'	*/
			
			dateString = this.inverseDateAndMonth( dateString );
			var dayRemovedDateString = dateString.replace( dateString.slice( 10, eval(dateString.indexOf('y') + 1) ), '' );
			var meridianCaseChanged = this.changeMeridianToUpperCase( dayRemovedDateString );
			return meridianCaseChanged;
			
		},
		
		inverseDateAndMonth : function( dateString ) {
			
			var date = dateString.substring( 0, 2 );
			var month = dateString.substring( 3, 5 );
			
			dateString = dateString.replace( dateString.substring( 0, 5), month + '/' + date );
			
			return dateString;
			
		},
		
		changeMeridianToUpperCase : function( dateString ) {
			
			var meridian = dateString.slice( dateString.length - 2, dateString.length );
			var restDate = dateString.slice( 0, dateString.length - 2 );
			
			if( meridian == 'am' ) {
				
				return restDate + 'AM';
				
			} else {
				
				return restDate + 'PM';
				
			}
			
		},

		findMeridianFromHours : function( hours ) {
			
			var meridian = 'am';
			
			if( hours > 12 ) {
				meridian = 'pm';
			}
			
			return meridian;
			
		},
		
		get12HoursFrom24Hours : function( hours ) {
			
			if( hours > 12 ) {
				return hours - 12;
			}
			
			return hours;
			
		},
		
};