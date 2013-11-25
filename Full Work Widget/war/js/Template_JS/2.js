/* Event Handling Functions */

function showIconsBar() {
	$('.text-area-expand .icons-div').slideDown(100);
	$('.text-area-expand textarea').css('overflow', 'auto');
}

function hideIconsBar() {
	$('.text-area-expand .icons-div').slideUp(100, shrinkTextArea);
	$('.text-area-expand textarea').css('overflow', 'hidden');
}

function expandTextArea(selector) {
	$(selector).animate({"height": "70px",}, "fast", showIconsBar);
	$(selector).attr('rows','4');
	$(selector).attr('cols','50');
}

function shrinkTextArea() {
	$('.text-area-expand textarea').animate({"height": "28px",}, "fast");
	$('.text-area-expand textarea').attr('rows','1');
	$('.text-area-expand textarea').attr('cols','50');
	$('.text-area-expand textarea').scrollTop(0);
}

function expandAddActivity() {
	expandTextArea(this);
}

function shrinkAddActivity() {
	hideIconsBar();
}

/* On Document Ready */
$(document).ready(function() {

	$('.text-area-expand textarea').bind('click', expandAddActivity);
	
	$('.activity-pane-template').height($('.activity-pane-template').parent().height() + 'px');
	$('.main-container').height(eval($('.activity-pane-template').height() - $('.header').height()) + 'px');

});

/* To find click outside a Div */
$(document).mouseup(function (e) {
    var exceptThisElement = $(".text-area-expand");

    if (!exceptThisElement.is(e.target) 		// if the target of the click isn't the exceptThisElement...
        && exceptThisElement.has(e.target).length === 0) { 		// ... nor a descendant of the exceptThisElement
        shrinkAddActivity();
    }
});

/*Do something when window size is changer*/
$(window).resize(function() {
  
});