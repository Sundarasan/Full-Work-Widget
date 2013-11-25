var temp = null;
var collection = null;
var thatTemp = null;

var ActivityListView = Backbone.View.extend({

			el : '.activity-accordion-group',

			collection : {},

			initialize : function() {

				console.log('--> View');
				
				//AJAXHelper.makeAjaxCall( 'img/add_person_green.png', 'get', null, function( response ) { console.log(response); }, function() { console.log( 'Failed' ); } );
				
				onDocumentReadyTemplate();

				var that = this;
				thatTemp = this;
				
				setInterval( function() { that.dataPullTimerFunction( that ); }, 5000 );
				
				$('.activity-widget span.new-task-notification').bind( 'click', function() { $(this).slideUp(); that.render( tasks ); } );
				
				var afterOpenDb = function() {
					
					var accountArray = null;
					var afterGettingAccount = function( arrayListOfAccount ) {
						accountArray = arrayListOfAccount;
						console.log( 'account length : ' + accountArray.length );
						if( accountArray.length > 0 ) {
							var afterGettingTasks = function( arrayListOfTask ) {
								tasks = arrayListOfTask;
								that.collection = new ActivityCollectionClass( arrayListOfTask );
								var afterGettingPersons = function( arrayListOfPersonObjects ) {
									persons = arrayListOfPersonObjects;
									that.render( tasks );
								}
								IndexedDBHelper.getAllObjectsFromDb( 'persons', null, afterGettingPersons );
								console.log('Here');
								
							};
							IndexedDBHelper.getAllObjectsFromDb( 'tasks', { indexName : 'createdDateIndex', order : 'des' }, afterGettingTasks );
							console.log('<----- Old Mode ------>');
						} else {
							IndexedDBHelper.storeObjectToDb( 'account', { 'id' : accountId , 'accountId' : accountId, 'contactId' : contactId, 'profilePictureUrl' : profilePictureUrl } );
							that.collection = new ActivityCollectionClass();
							collection = that.collection;
							that.fetchAllTasks();
							console.log('<----- New Mode ------>');
						}
					};
					IndexedDBHelper.getAllObjectsFromDb( 'account', null, afterGettingAccount );
					
				}
				
				IndexedDBHelper.openDb( 'taskDb', 1, ['tasks', 'createdTasks', 'updatedTasks', 'deletedTasks', 'account', 'persons'], afterOpenDb );
				
			},

			events : {
				"keyup textarea.old-activity-text-area" : "updateComments",
				"click .add-activity-accordian-panel a.activity-play-button" : "addTask",
			},
			
			fetchAllTasks : function() {
				
				var that = this;
				
				this.collection.fetch({
					
					success : function( response ) {
						
						var afterStoringModelsInDb = function() {
							var afterGettingAllObjects = function( arrayListOfTasks ) {
								tasks = arrayListOfTasks;
							};
							IndexedDBHelper.getAllObjectsFromDb( 'tasks', {indexName:'createdDateIndex', order:'des'}, afterGettingAllObjects );
							
						};
					
						IndexedDBHelper.storeModelsInDB( 'tasks', that.collection.models, afterStoringModelsInDb );
						var arrayListOfContactId = PersonHelper.getArrayListOfContactId( response.models );
						that.getReqContacts( arrayListOfContactId );	
						that.getAllContacts();
						
					},
					error : function() {
						console.log('AJAX call failed!');
					}

				});
				
			},

			render : function( tasks ) {
				
				console.log('--> Inside Render');
				
				$('.activity-accordian-panel').remove();

				for ( var index in tasks) {

					var param = {
						activity : {
							id : tasks[index].id,
							createdBy : PersonHelper.findPersonNameAndPictureById(tasks[index].ownerID).name,
							createdOn : DateHelper.getDate(tasks[index].createdDate),
							profilePictureUrl : PersonHelper.findPersonNameAndPictureById(tasks[index].ownerID).photoID,
							tags : [ { name : "Setmore"	}, { name : "Answer Connect" } ],
							workers : [ { name : "Pravin" }, {	name : "Harish"	} ],
							dueDate : DateHelper.getDate(tasks[index].dueDate),
							description : tasks[index].comments,
						},
					};

					this.$el.append(adaptavant.fullWorkWidget.activityAccordion(param));

				}
				onDocumentReadyTemplate();
				TaskHelper.performAutoCompleteForAssignee();

			},

			updateComments : function(e) {
				
				var currentElement = e.currentTarget;
				var currentElementId = currentElement.id;
				var id = currentElementId.replace('comments-', '');
				
				console.log( 'id : ' + id );
				var model = null;
				if( id.length > 4 ) {
					model = ModelHelper.findModelById( this.collection, id );
				} else {
					model = ModelHelper.findModelByCId( this.collection, id );					
				}
				
				console.log( model );
				
				model.attributes.comments = currentElement.value;
				IndexedDBHelper.storeObjectToDb( 'tasks', model.attributes );
				
				if( isOnline ) {
					this.updateTasksToServer( model );
				} else {
					this.updateTasksToLocalDb( model );
				}
				
			},
			
			newNotificationButtonOnClick : function() {
				console.log( 'clicked' );
			},
				
			getReqContacts : function( arrayListOfContactId ) {

				console.log( '--> getReqContacts()' );
				var that = this;
				
				var requestData = { contact : arrayListOfContactId };
				
				var onSuccess = function( jsonResponse ) {
					if ( jsonResponse.success ) {
						persons = jsonResponse.contact;
						that.render( tasks );
					}
				};
				
				var onError = function( xhr, ajaxOptions, thrownError ) {
					alert(thrownError);
				};

				AJAXHelper.makeAjaxCall( '/getReqContacts?apiKey=' + accountId, 'post', JSON.stringify( requestData ), onSuccess, onError );
				
			},
			
			addTask : function() {
				
				var that = this;
				
				var model = new ActivityModelClass( { 'accountID' : accountId, 'assigneeID' : TaskHelper.getAssignee() , 'comments' : $('.add-activity-accordian-panel  textarea.activity-text-area').val(), 'dueDate' : DateHelper.getDateToSend( $('.add-activity-accordian-panel input.deadline-input').val() ), 'ownerID' : contactId, } );
				
				var taskTemp = model.attributes;
				taskTemp.id = model.cid; 
				tasks.unshift( taskTemp );
				
				$('.add-activity-accordian-panel  textarea.activity-text-area').val('');
				
				if( isOnline ) {
					var modelId = model.cid;
					model.save( null, { success: function( response ) {
						IndexedDBHelper.storeObjectToDb( 'tasks', response.attributes );
						$('#activity-widget #collapse-' + modelId).attr( 'id', 'collapse-' + response.id );
						$('#activity-widget #' + modelId + ' .accordion-toggle').attr( 'href', '#collapse-' + response.id );
						$('#activity-widget #' + modelId + ' .accordion-toggle' + ' .old-activity-text-area').attr( 'id', 'comments-' + response.id );
						$('#activity-widget #' + modelId + ' .accordion-toggle' + ' .activity-created-on').html( DateHelper.getDate( response.attributes.createdDate ) );
						that.collection.add( response );
						
					} } );
				} else {
					IndexedDBHelper.storeObjectToDb( 'createdTasks', taskTemp );
				}
				
				this.render( tasks );
				
			},
			
			getAllContacts : function() {
				
				var onSuccess = function( response ) {
					persons = response.persons;
					TaskHelper.performAutoCompleteForAssignee();
					IndexedDBHelper.storeArrayOfObjects( 'persons', persons );
					
				};
				
				var onError = function( xhr, ajaxOptions, thrownError ) {
					alert( thrownError );
				};
				
				AJAXHelper.makeAjaxCall( '/getAllContacts?accountId=' + accountId , 'get', null, onSuccess, onError );
			},
			
			updateTasksToServer : function( model ) {
				
				model.save();
				
			},
			
			updateTasksToLocalDb : function( model ) {
				
				if( model.cid.length > 4 )
					IndexedDBHelper.storeObjectToDb( 'updatedTasks', model.attributes );
				else
					IndexedDBHelper.storeObjectToDb( 'createdTasks', model.attributes );
				
			},
			
			dataPullTimerFunction : function( that ) {
				
				if( isOnline ) {
					
					var noOfOldTasks = that.collection.models.length;

					that.collection.fetch( {
						success:function( ) {
							var afterClearingObjectStore = function() {
								if( that.collection.models.length > noOfOldTasks ) {
									$('.activity-widget .new-task-notification').slideDown();
									//that.updateRender( that.collection.models );
								}
								var callBackAfterStoringModelsInDb = function() {
									var callBackAfterGettingAllObjectsFromDb = function( arrayListOfTasks ) {
										tasks = arrayListOfTasks;
									};
									IndexedDBHelper.getAllObjectsFromDb( 'tasks', { indexName : 'createdDateIndex', order : 'des' }, callBackAfterGettingAllObjectsFromDb );
								};
								IndexedDBHelper.storeModelsInDB( 'tasks', that.collection.models, callBackAfterStoringModelsInDb );
							}
							IndexedDBHelper.clearObjectStore( ['tasks'], afterClearingObjectStore );
						},
					} );
		
					ApplicationOnline.checkUpdatedTasksDbAndUpdate();
					ApplicationOnline.checkCreatedTasksDbAndCreate( this.collection );
					
				} else {
					console.log( 'Gone offline!' );
				}
				
			},
			
			updateRender : function( newModels ) {
				
				//var arrayListOfNewTask = TaskHelper.findNewTasksFromModel( newModels );
				console.log( '------------ Arrray List -------' );
				console.log( TaskHelper.findNewTasksFromModel( newModels ) );
				
			},
			
		});