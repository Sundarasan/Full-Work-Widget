var PersonHelper = {
	
	findPersonById : function( contactId ) {
		
		for( var index in persons ) {

			if( persons[index].id == contactId ) {
				return persons[index];
			}
			
		}
		
		return null;
		
	},
	
	findPersonNameAndPictureById : function( contactId ) {
		
		var person = this.findPersonById( contactId );
		
		var name = 'Unknown';
		
		var profilePictureUrl = 'img/default_prof_pic.png';
		
		if( person != null ) {
		
			var name = StringHelper.findIfNull( person.firstName ) + ' ' + StringHelper.findIfNull( person.middleName ) + ' ' + StringHelper.findIfNull( person.lastName );
			
			if( person.photoID ) {
				
				profilePictureUrl = person.photoID;
				
			}
		
		}
		
		return { 'name' : name, 'photoID' : profilePictureUrl };
		
	},
	
	getPersonName : function( person ) {
		
		return StringHelper.findIfNull( person.firstName ) + ' ' + StringHelper.findIfNull( person.middleName ) + ' ' + StringHelper.findIfNull( person.lastName );
		
	},
	
	getPersonObjectWithNameAndId : function( persons ) {
		
		var personsToReturn = [];
		
		for( var index in persons ) {
			
			var person = { name : this.getPersonName( persons[index] ), id : persons[index].id };
			personsToReturn.push( person );
			
		}
		
		return personsToReturn;
		
	},
	
	getArrayListOfContactId : function( arrayListOfTaskModels, callBackFunction ) {
		
		var arrayListOfContactId = [];  
		for( var index in arrayListOfTaskModels ) {
			
			if( arrayListOfContactId.indexOf( arrayListOfTaskModels[index].attributes.ownerID ) ) {
				arrayListOfContactId.push( arrayListOfTaskModels[index].attributes.ownerID );
			}
			
			if( arrayListOfContactId.indexOf( arrayListOfTaskModels[index].attributes.assigneeID ) ) {
				arrayListOfContactId.push( arrayListOfTaskModels[index].attributes.assigneeID );
			}
		}
		
		if( callBackFunction ) {
			callBackFunction( arrayListOfContactId );
		} else {
			return arrayListOfContactId;
		}
		
	},
	
	
	
}