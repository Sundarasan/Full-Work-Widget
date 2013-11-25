var fullWorkWidgetRouterClass = Backbone.Router.extend({
	routes: {
		'': 'home',
	}
});

var fullWorkWidgetRouter = new fullWorkWidgetRouterClass();
var activityListView = new ActivityListView();

fullWorkWidgetRouter.on('route:home',
		function() {
			console.log('--> /home');
		}
);

Backbone.history.start();