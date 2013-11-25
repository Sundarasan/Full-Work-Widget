var DAO = { taskDb : {} };
var dbOpenSuccess = false;

var IndexedDBHelper = {
		
		/* Opening a Database */
		openDb : function( databaseName, version, objectStoreNames, callBackFunction ) {
				
				console.log( '\n--> openDb()' );
				if( ! version )
					version = 1;

				var request = indexedDB.open( databaseName, version );

				request.onupgradeneeded = function( event ) {

					console.log( '--> upgradeneeded()' );

					var db = event.target.result;
					
					for( var index in objectStoreNames ) {
						
						if( db.objectStoreNames.contains( objectStoreNames[index] ) ) {
					     	db.deleteObjectStore( objectStoreNames[index] );
					    }
						
						var store = db.createObjectStore( objectStoreNames[index] , {keyPath: "id"} );
						store.createIndex( 'createdDateIndex', 'createdDate', { unique : false } );
					    store.createIndex( 'dueDateIndex', 'dueDate', { unique : false } );
					    
					}

				};

				request.onsuccess = function( event ) {

					DAO.taskDb.db = event.target.result;
					console.log('\nDB successfully created! :)');

					if( DAO.taskDb.afterDateBaseCreated )
						DAO.taskDb.afterDateBaseCreated();
					
					if( callBackFunction )
						callBackFunction();
					
					dbOpenSuccess = true;

				};

				request.onerror = this.commonError;
			
		},
		
		/* Adding task to DB */
		storeObjectToDb : function( objectStoreName, object, callBack ) {					/* task = {'type':'call','id':'1c3e3d0d-c9ce-434f-ad79-db584f25f329','timeZone':'US/Pacific','status':'pending','mailSubject':null,'mailContent':null,'taskContent':null,'utcoffset':-28800000,'timeZoneID':'PST','notes':'Sample NOTES','assigneeID':'940eb647-bcf6-43e7-973b-e5c181d4cc4c','linkedSubscribers':null,'dueDate':'02/10/2013 12:00 AM','comments':'Sample Comments','linkedCustomFields':null,'linkedTags':null,'rating':null,'ownerID':'940eb647-bcf6-43e7-973b-e5c181d4cc4c','createdDate':1383563825751,'linkedTasks':null,'linkedDocuments':null,'accountID':'b1119fc8-e4c4-4f33-904f-a17788aa02a2','linkedContacts':null,'linkedDeals':null,'linkedAccounts':null}  */

			console.log( '\n--> storeObjectToDb()' );
			var transaction = DAO.taskDb.db.transaction( [objectStoreName], 'readwrite' );
			var store = transaction.objectStore( objectStoreName );
			var request = store.put( object );

			request.onsuccess = function() {
				console.log( 'Object added succesfully! :)' );

				if( callBack )
					callBack( true );
				
			};

			request.onerror = this.commonError;
			
		},
		
		/* Querying Database for Task */
		getAllObjectsFromDb : function( objectStoreName, option, callBack ) {				/* option =	{ order : <'asc'/'des'>, lowerBoundValue : 'AAA', upperBoundValue : 'ZZZ' }	*/
			
			console.log( '\n--> getObjectFromDb()' );
			
			var direction = 'next';			/* Ascending Order By Default */
			var lowerBound = 0;
			var upperBound = 'z';
			var indexName = null;
			
			var returnArrayListOfObjects = [];

			if( option ) {

				if( option.order ) {
					if( option.order == 'des' ) {
						direction = 'prev';
					} else if( option.order == 'asc' ) {
						direction = 'next';
					} else {
						console.log( 'Given Order option is not valid! So taking the default order option (asc).\nValid sorting option : asc / des' );
					}
				}

				if( option.lowerBoundValue ) {
					lowerBound = option.lowerBoundValue;
				}

				if( option.upperBoundValue ) {
					upperBound = option.upperBoundValue;
				}
				
				if( option.indexName ) {
					indexName = option.indexName;
				}

			}

			var transaction = DAO.taskDb.db.transaction( [ objectStoreName ], 'readwrite' );
			var store = transaction.objectStore( objectStoreName );
			var keyRange = IDBKeyRange.bound( lowerBound, upperBound);
			
			var cursorRequest = null;

			if( indexName ) {
				var index = store.index( indexName );
				cursorRequest = index.openCursor( keyRange, direction );
			} else {
				cursorRequest = store.openCursor( keyRange, direction );
			}

		  	cursorRequest.onsuccess = function( event ) {
			    var result = event.target.result;
			    if( !result ) {
			    	callBack( returnArrayListOfObjects );
			  	} else {
			  		returnArrayListOfObjects.push( result.value );
				    result.continue();
				}

		  	};

		  cursorRequest.onerror = this.commonError;
		  
		},
		
		getObjectById : function( objectStoreName, id, callBack ) {

			console.log( '\n--> getObjectById()' );
			
			that = this;
			
			var transaction = DAO.taskDb.db.transaction( [objectStoreName], 'readwrite' );
			var store = transaction.objectStore( objectStoreName );

			var request = store.get( id );

			request.onsuccess = function( event ) {

				var result = event.target.result;
				console.log( '\Got Successfully!' );

				if( callBack ) {
					callBack( result );
				}
			};

			request.onerror = this.commonError;
			
		},
		
		/* Delelte Task */
		deleteObjectById : function( objectStoreName, id, callBack ) {

			console.log( '\n--> deleteObjectById()' );

			var transaction = DAO.taskDb.db.transaction( [objectStoreName], 'readwrite' );
			var store = transaction.objectStore( objectStoreName );

			var request = store.delete( id );

			request.onsuccess = function() {
				console.log( '\nDeleted Successfully!' );

				if( callBack )
					callBack();
				
			};

			request.onerror = this.commonError;

		},
		
		clearObjectStore : function( objectStoreNames, callBack ) {
			
			var transaction = DAO.taskDb.db.transaction( objectStoreNames, 'readwrite' );
			
			for( var index in objectStoreNames ) {
				var store = transaction.objectStore( objectStoreNames[index] );
				store.clear();
			}
			
			console.log( '\nObjectStore Cleared Successfully!' );
			
			if( callBack )
				callBack();
			
		},
		
		commonError : function( event ) {
			console.log( 'Error on IndexedDB : ' + event.value );
		},
		
		storeModelsInDB : function( objectStoreName, models, callBack ) {
			
			for( var index in models ) {
				var task = models[index].attributes;
				this.storeObjectToDb( objectStoreName, task );
			}
			
			if( callBack ) {
				callBack( true );
				console.log( 'Called' );
			}
			
		},
		
		storeArrayOfObjects : function( objectStoreName, arrayListOfObjects ) {
			
			for( var index in arrayListOfObjects ) {
				this.storeObjectToDb( objectStoreName, arrayListOfObjects[index] );
			}
			
		},
		
		inDbExistById : function( objectStoreName, id, callBack ) {
			
			var transaction = DAO.taskDb.db.transaction( [objectStoreName], 'readwrite' );
			var store = transaction.objectStore( objectStoreName );
			var request = store.get( id );
			
			request.onsuccess = function( event ) {
				if( event.target.result == undefined ) {
					callBack( false );
				} else {
					callBack( event.target.result );
				}
			};
			
			request.onerror = this.commonError;
			
		},
		
};