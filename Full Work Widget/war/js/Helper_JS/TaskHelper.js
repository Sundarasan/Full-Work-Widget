var TaskHelper = {
		
		getAssignee : function() {
			
			var assignee = [];
			
			var workerDivs = $('#add .activity-worker');
			var arrayListOfWorkerId = [];
			
			for( var i = 0; i < workerDivs.length; i++ ) {
				
				var workerDivId = workerDivs[i].id;
				var workerId = workerDivId.replace( 'worker-', '' );
				arrayListOfWorkerId.push( workerId );
				
			}
			
			var assigneeId = arrayListOfWorkerId[0];		/* Till now only one assignee can be assigned */
			
			if( !arrayListOfWorkerId.length ) {				/* If no assignee selected then it will assign to themself */
				assigneeId = contactId;
			}
			
			return assigneeId;
			
		},
		
		performAutoCompleteForAssignee : function() {
			
			var personsObjectWithNameAndIdArrayList = PersonHelper.getPersonObjectWithNameAndId( persons );
			var arrayListLabelAndValuePair = [];
			
			for( var index in personsObjectWithNameAndIdArrayList ) {	
				arrayListLabelAndValuePair.push( { label : personsObjectWithNameAndIdArrayList[index].name, value : personsObjectWithNameAndIdArrayList[index].name, id : personsObjectWithNameAndIdArrayList[index].id } );
			}
			
			var onPersonSelect = function( event, ui ) {
				
				var cid = $(event.target).parents().eq(4).attr('id').replace( 'worker-list-div-', '' );
				var workersListContainer = $('#activity-widget #' + cid).find( '.workers-list' );
				console.log( workersListContainer );
				workersListContainer.append( "<div class='activity-worker label pull-left' id='worker-" + ui.item.id + "' >" + ui.item.label + "</div>" );
				$('#activity-widget').trigger('click');
				
			}
			
			$('.search-worker-textbox').autocomplete( { source : arrayListLabelAndValuePair, select : onPersonSelect } );
			
		},
		
		findNewTasksFromModel : function( newModels ) {
			
			var returnArrayListOfNewTasks = [];
			
			for( var index in newModels ) {
				
				var callBackAfterCheckingDb = function( object ) {
					
					if( object ) {
						returnArrayListOfNewTasks.push( newModels[index].attributes );
					}
					
				};
				
				IndexedDBHelper.inDbExistById( 'tasks', newModels[index].id, callBackAfterCheckingDb );
				
			}
			
		},
		
};