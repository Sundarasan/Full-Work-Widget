var DigitHelper = {
		
		convertToDigits : function( number, noOfDigits ) {
			
			var minimumCutOff = '';
			
			for( var i = 0; i < eval(noOfDigits - 1); i++ ) {
				
				minimumCutOff = minimumCutOff  + '9';
				
				minimumCutOff = eval( minimumCutOff );
				
				if( number <= minimumCutOff ) {
					number = '0' + number; 
				}
				
			}
			
			return number;
			
		}
		
}