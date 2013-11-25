var ActivityCollectionClass = Backbone.Collection.extend({
	
	url: '/getTasksByAssigneeId' + '?' + 'apiKey=' + accountId + '&' + 'userId=' + contactId,
	
	model: ActivityModelClass,

});