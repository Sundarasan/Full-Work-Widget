var ActivityModelClass = Backbone.Model.extend({
	
	url: '/task' + '?' + 'apiKey=' + accountId + '&' + 'userId=' + contactId,
	
	initialize: function() {
		
		 this.on("change:notes", function(model) {
			 
			 console.log('NOTES CHANGED');
			 
         });
		
	},
	
	defaults: {
		
		accountID: "",
		assigneeID: "",
		comments: "",
		createdDate: null,
		dueDate: null,
		id:  null,
		linkedAccounts: [],
		linkedContacts: [],
		linkedCustomFields: [],
		linkedDeals: [],
		linkedDocuments: [],
		linkedSubscribers: [],
		linkedTags: [],
		linkedTasks: [],
		mailContent: "",
		mailSubject: "",
		notes: "",
		ownerID: "",
		rating: "",
		status: "",
		taskContent: "",
		timeZone: "",
		timeZoneID: "",
		type: "",
		utcoffset: "",
		
    },

});