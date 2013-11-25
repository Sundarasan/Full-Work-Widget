var ModelHelper = {
	
		findModelByCId : function( collection, cId ) {
			
			var models = collection.models;
			
			for( var index in models ) {
				
				if ( models[index].cid == cId ) {
					return models[index];
				}
				
			}
			
			return null;
			
		},
		
		populateJSONtoModel : function( jsonObject, model ) {
			
			for( var key in jsonObject ) {
				console.log( 'Key : ' + key + '  Value : ' + eg[key] );
				
			}
			
		},
		
		findModelById : function( collection, id ) {
			
			var models = collection.models;
			
			for( var index in models ) {
				
				console.log(  );
				if ( models[index].id == id ) {
					return models[index];
				}
				
			}
			
			return null;
			
		},
		
		
};