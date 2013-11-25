var AJAXHelper = {
	
		makeAjaxCall : function( url, methodType, requestData, onSuccess, onError ) {
			
			$.ajax( {
				beforeSend : function(request) {
					request.setRequestHeader("Content-Type", "application/json");
					request.setRequestHeader("Accept", "application/json");
				},
				type : methodType,
				async : true,
				datatype : 'json',
				data : requestData,
				contentType : 'application/json; charset=utf-8',
				url : url,
				success : onSuccess,
				error : onError,
			
			} );
			
		},
		
};