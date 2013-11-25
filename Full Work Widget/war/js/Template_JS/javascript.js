// This file was automatically generated from activity.soy.
// Please don't edit this file by hand.

if (typeof adaptavant == 'undefined') { var adaptavant = {}; }
if (typeof adaptavant.activity == 'undefined') { adaptavant.activity = {}; }


adaptavant.activity.activty_pane = function(opt_data, opt_ignored) {
  return adaptavant.activity.activty_pane_start(null) + adaptavant.activity.activty_pane_header(opt_data) + adaptavant.activity.activty_pane_body_start(null) + adaptavant.activity.add_activity_div(null) + adaptavant.activity.show_activities(opt_data) + adaptavant.activity.activty_pane_body_end(null) + adaptavant.activity.activty_pane_end(null);
};


adaptavant.activity.activity_pane_with_container = function(opt_data, opt_ignored) {
  return adaptavant.activity.activty_pane_container_start(null) + adaptavant.activity.activty_pane_start(null) + adaptavant.activity.activty_pane_header(opt_data) + adaptavant.activity.activty_pane_body_start(null) + adaptavant.activity.add_activity_div(null) + adaptavant.activity.show_activities(opt_data) + adaptavant.activity.activty_pane_body_end(null) + adaptavant.activity.activty_pane_end(null) + adaptavant.activity.activty_pane_container_end(null);
};


adaptavant.activity.activty_pane_container_start = function(opt_data, opt_ignored) {
  return '<div class="container padding-top-10"><div class="jumbotron" id="activity-jumbotron" style="padding: 0px 0px 0px 0px;">';
};


adaptavant.activity.activty_pane_container_end = function(opt_data, opt_ignored) {
  return '</div></div>';
};


adaptavant.activity.activty_pane_start = function(opt_data, opt_ignored) {
  return '<div class="row activity-pane-template"><div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-zero">';
};


adaptavant.activity.activty_pane_end = function(opt_data, opt_ignored) {
  return '</div></div>';
};


adaptavant.activity.activty_pane_header = function(opt_data, opt_ignored) {
  return '<div class="header"><nav class="navbar navbar-default" role="navigation" style="padding: 5px 5px 5px 5px; margin-bottom: 0px; height: 40px;"><ul class="nav navbar-nav pull-right nav-bar"><li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><img class="img-medium" src="img/signal-icon.png" /><b class="caret"></b></a><ul class="dropdown-menu"><li><a href="#">Action</a></li><li><a href="#">Another action</a></li><li><a href="#">Something else here</a></li><li><a href="#">Separated link</a></li></ul></li><li class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown"><img class="img-medium" src="' + soy.$$escapeHtml(opt_data.profile_pic_src) + '" /><b class="caret"></b></a><ul class="dropdown-menu"><li><a href="#">Action</a></li><li><a href="#">Another action</a></li><li><a href="#">Something else here</a></li><li class="divider"></li><li><a href="#">Log out</a></li></ul></li></ul></nav></div>';
};


adaptavant.activity.activty_pane_body_start = function(opt_data, opt_ignored) {
  return '<div class="main-container">';
};


adaptavant.activity.activty_pane_body_end = function(opt_data, opt_ignored) {
  return '</div>';
};


adaptavant.activity.add_activity_div = function(opt_data, opt_ignored) {
  return '<div class="row text-area-expand"><div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-zero"><div class="row" style="line-height: 25px;"><div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-zero"><textarea rows="1" cols="50" placeholder="Describe your activity..." class="activity-text-area"></textarea></div></div><div class="row icons-div"><div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-zero"><ul class="nav nav-pills pull-left icons-group"><li class="vertical-divider-right"><a href="#"><img class="img-small" src="img/TagFliped.png"/></a></li></ul><ul class="nav nav-pills pull-right icons-group"><li class="vertical-divider-left"><a href="#"><img class="img-small" src="img/datepicker_calendar_icon.gif"/></a></li><li class="vertical-divider-left"><a href="#"><img class="img-small" src="img/Play1.png"/></a></li></ul></div></div></div></div>';
};


adaptavant.activity.show_activities = function(opt_data, opt_ignored) {
  var output = '';
  var activityList43 = opt_data.activities;
  var activityListLen43 = activityList43.length;
  if (activityListLen43 > 0) {
    for (var activityIndex43 = 0; activityIndex43 < activityListLen43; activityIndex43++) {
      var activityData43 = activityList43[activityIndex43];
      output += soy.$$escapeHtml(activityData43.desc);
    }
  } else {
    output += '<div class="row" style="margin: 30px 0px 60px 0px;"><div class="col-lg-12 col-md-12 col-sm-12 col-xs-12 padding-zero" align="center"><img class="profile-pic-nav" src="img/clock.png"/><p class="color-grey" style="font-size:18px;">Start Creating your activity ....</p><p class="text-size-very-small color-grey">.... and track your activity log will appear here</p></div></div>';
  }
  return output;
};
