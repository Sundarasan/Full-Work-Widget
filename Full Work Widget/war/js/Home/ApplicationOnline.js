var ApplicationOnline = {
		
		applicationBackOnline : function() {
			
			isOnline = true;
			console.log( 'isOnline = ' + isOnline );
			
		},
		
		/* Check for pending updatedTask and update */
		checkUpdatedTasksDbAndUpdate : function() {
			
			var afterGettingUpdatedTaskObjects = function( arrayListOfUpdatedTasks ) {
				for( var index in arrayListOfUpdatedTasks ) {
					var model =  new ActivityModelClass( arrayListOfUpdatedTasks[index] );
					model.save();
					console.log( 'Model Saved!' );
					IndexedDBHelper.deleteObjectById( 'updatedTasks', arrayListOfUpdatedTasks[index].id );
				}
			};
			
			IndexedDBHelper.getAllObjectsFromDb( 'updatedTasks', null, afterGettingUpdatedTaskObjects );
			
		},
		
		
		/* Check for pending createdTask and create */
		checkCreatedTasksDbAndCreate : function( collection ) {
			
			var afterGettingCreatedTaskObjects = function( arrayListOfCreatedTasks ) {
				var modelIdArray = [];
				for( var index in arrayListOfCreatedTasks ) {
					modelIdArray.push( arrayListOfCreatedTasks[index].id );
				}
				
				for( var index in arrayListOfCreatedTasks ) {
					var modelId = arrayListOfCreatedTasks[index].id;
					arrayListOfCreatedTasks[index].id = null;
					var model =  new ActivityModelClass( arrayListOfCreatedTasks[index] );
					model.save( null, { success : function( response ) {
							var modelIdTemp = modelIdArray.pop();
							$('#collapse-' + modelIdTemp).attr( 'id', 'collapse-' + response.id );
							$('#activity-widget #' + modelIdTemp + ' .accordion-toggle').attr( 'href', '#collapse-' + response.id );
							$('#activity-widget #' + modelIdTemp + ' .accordion-toggle' + ' .old-activity-text-area').attr( 'id', 'comments-' + response.id );
							$('#activity-widget #' + modelIdTemp + ' .accordion-toggle' + ' .activity-created-on').html( DateHelper.getDate( response.attributes.createdDate ) );
							collection.add( response );
						}
					} );
					console.log( 'Model Saved!' );
					IndexedDBHelper.deleteObjectById( 'createdTasks', modelId );
				}
			};
			
			IndexedDBHelper.getAllObjectsFromDb( 'createdTasks', null, afterGettingCreatedTaskObjects );
			
		},
		
};