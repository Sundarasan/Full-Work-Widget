var temp = null;
var weekday = [];

$(document).ready( onDocumentReadyTemplate );

var onDocumentReadyTemplate = function() {

	weekday = ["Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"];

	$('.panel-heading').height($('.panel-title').height());

	$('.activity-accordian-collapse').on('show.bs.collapse', onActivityAccordianOpen);

	$('.activity-accordian-collapse').on('hide.bs.collapse', onActivityAccordianClose);

	var accordionIconDivDatePicker = $('.activity-date-picker');
	var accordianIconsDiv = accordionIconDivDatePicker.closest('.icons-div');
	var accordionDateInput = $('.activity-accordion-group .panel .activity-accordian-collapse .activity-date-input');
	var accordionDateIcon = $('.activity-accordion-group .panel .activity-accordian-collapse .activity-icons-div-date-icon');
	var accordionTagIcon = $('.activity-accordion-group .panel .activity-accordian-collapse .activity-tag-button');
	var addWorkerPersonIcon = $('.activity-accordion-group .panel .activity-accordian-collapse .activity-add-worker-button');
	var accordionPlayIconImg = $('.activity-accordion-group .panel .activity-play-button img');
	var accordionPauseIconImg = $('.activity-accordion-group .activity-accordian-panel .activity-pause-button img');
	var accordionStopIconImg = $('.activity-accordion-group .activity-accordian-panel .activity-stop-button img');
	var accordionTagIconImg = $('.activity-accordion-group .panel .activity-tag-button img');
	var accordionDateIconImg = accordionDateIcon.find('img');
	var addWorkerPersonIconImg = $('.activity-accordion-group .panel .activity-accordian-collapse .activity-add-worker-button img');
	var accordionDeadLineInput = $('#activity-accordion-group .activity-accordian-panel .activity-accordian-collapse .deadline-div .deadline-input');
	var addActivityDeadLineInput = $('#activity-accordion-group .add-activity-accordian-panel .activity-accordian-collapse .deadline-div .deadline-input');
	var addActivityTextArea =  $('#activity-accordion-group .add-activity-accordian-panel .activity-accordion-heading .activity-text-area');

	accordionIconDivDatePicker.attr('data-date', '2013/10/01');

	accordionDeadLineInput.attr('size', 34);
	addActivityDeadLineInput.attr('size', 34);


	accordionDateInput.bind('change', function() {
		$(this).attr('size', eval($(this).val().length + 3));
	});

	updateDate();
	updateTimeInAddActivityTimeInputBox();

	$('.activity-accordion-group .panel .activity-accordion-heading .activity-ticking-clock').height($('.activity-accordion-group .activity-accordian-panel').height());
	$('.activity-accordion-group .panel .activity-accordion-heading .activity-ticking-clock .activity-ticking-clock-input').height($('.activity-accordion-group .activity-accordian-panel').height());

	//accordionDateInput.val(eval(nowDate.getDate()) + '/' + eval(nowDate.getMonth() + 1) + '/' + eval(nowDate.getFullYear()) + ' ' + weekday[nowDate.getDay()] + ' ' + hour_24HoursTo12Hours(nowDate.getHours()) + ':' + addZeroToMinutes(nowDate.getMinutes()) + ' ' + meridian_24HoursTo12Hours(nowDate.getHours()));
	//accordionDeadLineInput.val(eval(nowDate.getDate()) + '/' + eval(nowDate.getMonth() + 1) + '/' + eval(nowDate.getFullYear()) + ' ' + weekday[nowDate.getDay()] + ' ' + hour_24HoursTo12Hours(nowDate.getHours()) + ':' + addZeroToMinutes(nowDate.getMinutes()) + ' ' + meridian_24HoursTo12Hours(nowDate.getHours()));
	/*accordionDateIcon.bind('click', function() {
		var iconsDivUl = $(this).parents().eq(1);
		var activityDateInput = iconsDivUl.find('.activity-date-input');
		nowDateTemp = new Date();
		nowDate = new Date(nowDateTemp.getFullYear(), nowDateTemp.getMonth(), nowDateTemp.getDate(), nowDateTemp.getHours(), nowDateTemp.getMinutes());
		activityDateInput.val(eval(nowDate.getDate()) + '/' + eval(nowDate.getMonth() + 1) + '/' + eval(nowDate.getFullYear()) + ' ' + weekday[nowDate.getDay()] + ' ' + hour_24HoursTo12Hours(nowDate.getHours()) + ':' + addZeroToMinutes(nowDate.getMinutes()) + ' ' + meridian_24HoursTo12Hours(nowDate.getHours()));
		$(this).hide();
		activityDateInput.slideDown();
	});*/

	addActivityDeadLineInput.datetimepicker({ dateFormat: "dd/mm/yy DD", timeFormat: "hh:mm tt", minDate: nowDate});
	accordionDeadLineInput.datetimepicker({ dateFormat: "dd/mm/yy DD", timeFormat: "hh:mm tt", minDate: nowDate});
	addActivityDeadLineInput.val( DigitHelper.convertToDigits( eval(nowDate.getDate() + 1), 2 ) + '/' + DigitHelper.convertToDigits( eval(nowDate.getMonth() + 1), 2 ) + '/' + eval(nowDate.getFullYear()) + ' ' + weekday[nowDate.getDay()] + ' ' + hour_24HoursTo12Hours(nowDate.getHours()) + ':' + addZeroToMinutes(nowDate.getMinutes()) + ' ' + meridian_24HoursTo12Hours(nowDate.getHours()));

	accordionDateIcon.bind('click', function() {
		$(this).fadeOut();
		var accordionCollapse = $(this).parents().eq(5);
		var deadLineDiv = accordionCollapse.find('.deadline-div');
		var tagIcon = accordionCollapse.find('.activity-tag-button');
		var tagsDiv = accordionCollapse.find('.tags-div');
		var workersListDiv = accordionCollapse.find('.workers-list-div');
		var addWorkerIcon = accordionCollapse.find('.activity-add-worker-button');

		deadLineDiv.slideDown("fast");	
		tagsDiv.slideUp("fast");
		workersListDiv.slideUp("fast");

		addActivityDeadLineInput.val( DigitHelper.convertToDigits( eval(nowDate.getDate() + 1), 2 ) + '/' + DigitHelper.convertToDigits( eval(nowDate.getMonth() + 1), 2 ) + '/' + eval(nowDate.getFullYear()) + ' ' + weekday[nowDate.getDay()] + ' ' + hour_24HoursTo12Hours(nowDate.getHours()) + ':' + addZeroToMinutes(nowDate.getMinutes()) + ' ' + meridian_24HoursTo12Hours(nowDate.getHours()));	
		tagIcon.fadeIn();
		addWorkerIcon.fadeIn();

	});

	accordionTagIcon.bind('click', function() {

		$(this).fadeOut();
		var accordionCollapse = $(this).parents().eq(5);
		var tagsDiv = accordionCollapse.find('.tags-div');
		var deadLineDiv = accordionCollapse.find('.deadline-div');
		var datePickerIcon = accordionCollapse.find('.activity-icons-div-date-icon');
		var workersListDiv = accordionCollapse.find('.workers-list-div');
		var addWorkerIcon = accordionCollapse.find('.activity-add-worker-button');

		tagsDiv.slideDown("fast");	
		deadLineDiv.slideUp("fast");
		workersListDiv.slideUp("fast");
		datePickerIcon.fadeIn();
		addWorkerIcon.fadeIn();

	});

	addWorkerPersonIcon.bind('click', function() {

		$(this).fadeOut();
		var accordionCollapse = $(this).parents().eq(5);
		var workersListDiv = accordionCollapse.find('.workers-list-div');
		var tagsDiv = accordionCollapse.find('.tags-div');
		var deadLineDiv = accordionCollapse.find('.deadline-div');
		var datePickerIcon = accordionCollapse.find('.activity-icons-div-date-icon');
		var tagIcon = accordionCollapse.find('.activity-tag-button');

		workersListDiv.slideDown("fast");
		tagsDiv.slideUp("fast");
		deadLineDiv.slideUp("fast");
		datePickerIcon.fadeIn();
		tagIcon.fadeIn();

	});

	//accordionDateInput.datetimepicker();

	//expandTextArea(addActivityTextArea, {"height": "70px"});

	setInterval(timerFunction, 1000);

	$('.activity-accordion-group .activity-accordian-panel .activity-accordian-collapse.in').collapse('show');

	accordionPlayIconImg.bind('mouseenter', {src:'img/play-green.png'},setImageForImg);

	accordionPlayIconImg.bind('mouseleave', {src:'img/Play1.png'}, setImageForImg);

	accordionPauseIconImg.bind('mouseenter', {src:'img/pause-red.png'}, setImageForImg);

	accordionPauseIconImg.bind('mouseleave', {src:'img/pause-grey.png'}, setImageForImg);

	accordionStopIconImg.bind('mouseenter', {src:'img/stop-red.png'}, setImageForImg);

	accordionStopIconImg.bind('mouseleave', {src:'img/stop-grey.png'}, setImageForImg);

	accordionTagIconImg.bind('mouseenter', {src:'img/tag-flipped-orange.png'}, setImageForImg);

	accordionTagIconImg.bind('mouseleave', {src:'img/TagFliped.png'}, setImageForImg);

	addWorkerPersonIconImg.bind('mouseenter', {src:'img/add_person_green.png'}, setImageForImg);

	addWorkerPersonIconImg.bind('mouseleave', {src:'img/add_person.png'}, setImageForImg);

	accordionDateIconImg.bind('mouseenter',  {src:'img/calendar-color.png'}, setImageForImg);

	accordionDateIconImg.bind('mouseleave',  {src:'img/calendar-grey.png'}, setImageForImg);

	//$('.activity-tag-button').popover();

	$('.tag-dropdown').bind('click', function(event){
		event.stopPropagation();
	});

	$('.tag-icon').bind('click', function(event){
		$(this).parent().find('.search-tag-textbox').val('');
	});

	$('.add-workers-dropdown').bind('click', function(event){
		event.stopPropagation();
	});

	$('.add-worker-plus-button').bind('click', function(event){
		$(this).parent().find('.search-worker-textbox').val('');
	});
	
};



/* Helper Functions */

function setImageForImg(event) {
	$(this).attr('src', event.data.src);
}

function hour_24HoursTo12Hours(hour) {

	if(hour > 12) {
		hour = hour - 12;
	}

	if(hour < 10) {
		hour = '0' + hour;
	}

	return hour;
}

function meridian_24HoursTo12Hours(hour) {
	var meridain = 'am';
	if(hour > 12) {
		meridain = 'pm';
	}
	return meridain;
}

function addZeroToMinutes(minutes) {
	if(minutes < 10) {
		minutes = '0' + minutes;
	}
	return minutes;
}

function onActivityAccordianOpen() {


	var activityAccordian = $(this).parent();
	var activityTextArea = activityAccordian.find('.activity-text-area');
	var activityAccordianCollapse = activityAccordian.find('.activity-accordian-collapse');
	var inAccordionDataToggle = activityTextArea.parent();
	var activityTickingClock = activityAccordian.find('.panel-heading .accordion-toggle .activity-ticking-clock');
	var allAccordianToggle = $('.activity-accordion-group .panel .panel-heading .accordion-toggle');
	var activityAccordionProfPic = activityAccordian.find('.panel-heading .accordion-toggle .activity-prof-pic');
	var activityInfoDiv = activityAccordian.find('.activity-accordion-heading .info-div');
	var activityDeadLineDiv = activityAccordianCollapse.find('.deadline-div');
	var activityTagDiv = activityAccordianCollapse.find('.tags-div');
	var activityDatePickerIcon = activityAccordianCollapse.find('.activity-icons-div-date-icon');
	var activityTagIcon = activityAccordianCollapse.find('.activity-tag-button');
	var activityAddWorkerButton = activityAccordianCollapse.find('.activity-add-worker-button');
	var activityWorkerListDiv = activityAccordianCollapse.find('.workers-list-div');

	var animationTextArea = {"height": "70px"};

	var expandToHeight = 70;

	activityTextArea.attr('spellcheck', 'true');

	if(!activityAccordian.hasClass('add-activity-accordian-panel')) {	
		activityTextArea.css("width","100%");
		expandToHeight = 85;
	}

	expandTextArea(activityTextArea, animationTextArea);
	animateAccordianHeadingSize( activityTextArea, expandToHeight);

	activityTextArea.css('cursor', 'text');

	addClassToElementsOtherThan(allAccordianToggle, inAccordionDataToggle, 'collapsed');

	findUnopenedAccordianAndGiveGradientEffect();

	activityTickingClock.hide();

	activityAccordionProfPic.hide();

	activityInfoDiv.slideDown("fast");

	inAccordionDataToggle.attr('data-toggle', '');

	activityDeadLineDiv.hide();
	activityTagDiv.hide();
	activityDatePickerIcon.show();
	activityTagIcon.show();
	activityAddWorkerButton.show();
	activityWorkerListDiv.hide();
}

function onActivityAccordianClose() {

	var activityAccordian = $(this).parent();
	var activityTextArea = activityAccordian.find('.activity-text-area');
	var activityAccordianCollapse = activityAccordian.find('.activity-accordian-collapse');
	var activityTickingClock = activityAccordian.find('.panel-heading .accordion-toggle .activity-ticking-clock');
	var activityAccordionProfPic = activityAccordian.find('.panel-heading .accordion-toggle .activity-prof-pic');
	var activityInfoDiv = activityAccordian.find('.activity-accordion-heading .info-div');

	var animationTextArea = {"height": "28px"};

	activityTextArea.attr('spellcheck', 'false');

	if(!activityAccordian.hasClass('add-activity-accordian-panel')) {	
		activityTextArea.css("width","72%");
	}

	activityTickingClock.fadeIn();

	shrinkTextArea(activityTextArea, animationTextArea);
	animateAccordianHeadingSize( activityTextArea, 28);
	activityTextArea.css('cursor', 'pointer');
	activityTextArea.css('cursor', 'hand');

	activityInfoDiv.hide();
	activityAccordionProfPic.fadeIn();

}

function expandTextArea(textArea, animation) {

	textArea.animate(animation, "fast", function() {
		textArea.css('overflow', 'auto');
	});
	textArea.attr('rows','4');
	textArea.attr('cols','50');

}

function shrinkTextArea(textArea, animation) {

	textArea.css('height', '28px');
	textArea.css('overflow', 'hidden');

	/*textArea.animate(animation, 150, function() {
		textArea.css('overflow', 'hidden');
	});*/
	textArea.attr('rows','1');
	textArea.attr('cols','50');
	textArea.scrollTop(0);

}

function animateAccordianHeadingSize(textArea, size) {

	var activityTextArea = textArea;
	var activityAccordian = textArea.parents().eq(3);
	var activityAccordionHeading = activityAccordian.find('.panel-heading');

	activityAccordionHeading.animate({"height": size + "px"}, "fast");

}

function findUnopenedAccordianAndGiveGradientEffect() {

	var allAccordionTextArea = $('.activity-accordion-group .activity-accordian-panel .panel-heading .accordion-toggle .activity-text-area');
	var collapsedAccordionTextArea = $('.activity-accordion-group .activity-accordian-panel .panel-heading .accordion-toggle.collapsed .activity-text-area');
	var collapsedAccordionDataToggle = collapsedAccordionTextArea.parent();
	var allAccordionPanel =  allAccordionTextArea.closest('.activity-accordian-panel');
	var collapsedAccordionPanel = collapsedAccordionTextArea.closest('.activity-accordian-panel');
	var addActivityAccordionDataToggle = $('.activity-accordion-group .add-activity-accordian-panel .panel-heading .accordion-toggle');

	allAccordionTextArea.css('background-image', 'none');
	collapsedAccordionTextArea.css('background-image', 'linear-gradient(to bottom, #F3F2F2, #CECECE)');

	allAccordionTextArea.mouseenter(function() {
		$(this).css('background-image', 'none');		
	});
	allAccordionTextArea.mouseleave(function() {
		$(this).css('background-image', 'none');		
	});

	collapsedAccordionTextArea.mouseenter(function() {
		$(this).css('background-image', 'linear-gradient(to bottom, #CECECE, #F3F2F2)');		
	});
	collapsedAccordionTextArea.mouseleave(function() {
		$(this).css('background-image', 'linear-gradient(to bottom, #F3F2F2, #CECECE)');		
	});

	collapsedAccordionDataToggle.attr('data-toggle','collapse');
	addActivityAccordionDataToggle.attr('data-toggle','collapse');
}

function addClassToElementsOtherThan( allElementsJqueryObject, elementJqeryObject, className) {

	if(elementJqeryObject == undefined || elementJqeryObject == null) {
		elementJqeryObject = "";
	}

	for(index in allElementsJqueryObject) {
		if(index == 'length') {
			break;
		}
		if(allElementsJqueryObject[index] != elementJqeryObject[0]) {
			allElementsJqueryObject[index].className = allElementsJqueryObject[index].className + " " + className;
		}
	}

}

function updateDate() {
	nowDateTemp = new Date();
	nowDate = new Date(nowDateTemp.getFullYear(), nowDateTemp.getMonth(), nowDateTemp.getDate(), nowDateTemp.getHours(), nowDateTemp.getMinutes(), nowDateTemp.getSeconds());
}

function updateTimeInAddActivityTimeInputBox() {

	var accordionDateInput = $('.activity-accordion-group .add-activity-accordian-panel .activity-accordian-collapse .activity-date-input');
	accordionDateInput.datetimepicker({ dateFormat: "dd/mm/yy DD", timeFormat: "hh:mm tt", minDate: nowDate});

}

function timeKeeper() {

	var deadLineDateTime = new Date(2013, 11, 29, 16, 57, 0, 0); // create a date of Jun 15/2011, 8:32:00am

	//console.log(deadLineDateTime);


	$('#activity-accordion-group .activity-accordian-panel .activity-ticking-clock-input').val(differenceDates(deadLineDateTime, nowDate));

}

function timerFunction() {
	updateDate();
	updateTimeInAddActivityTimeInputBox();
	timeKeeper();
}

function differenceDates(biggerDate, smallerDate) {

	var yearsDifference, monthsDifference, daysDifference, hoursDifference, minutesDifference, secondsDifference;

	if( biggerDate.getFullYear() > smallerDate.getFullYear() ) {
		yearsDifference = biggerDate.getFullYear() - smallerDate.getFullYear();
		return yearsDifference + ' ' + findPlural( yearsDifference, 'Year');
	}
	if( biggerDate.getMonth() > smallerDate.getMonth() ) {
		monthsDifference = biggerDate.getMonth() - smallerDate.getMonth();
		return monthsDifference + ' ' + findPlural( monthsDifference, 'Month');
	}
	if( biggerDate.getDay() > smallerDate.getDay() ) {
		daysDifference = biggerDate.getDay() - smallerDate.getDay();
		return daysDifference + ' ' + findPlural( daysDifference, 'Day');
	}
	if( biggerDate.getHours() > smallerDate.getHours() ) {
		hoursDifference = biggerDate.getHours() - smallerDate.getHours();
		return hoursDifference + ' ' + findPlural( hoursDifference, 'Hour');
	}
	if( biggerDate.getMinutes() > smallerDate.getMinutes() ) {
		minutesDifference = biggerDate.getMinutes() - smallerDate.getMinutes();
		if(minutesDifference != 1)
			return eval(minutesDifference-1) + ' ' + findPlural( eval(minutesDifference-1), 'Minute');
	}
	if( minutesDifference == 1 && eval(biggerDate.getSeconds() + 60) > eval(smallerDate.getSeconds()) )  {
		secondsDifference = eval(biggerDate.getSeconds() + 60) - eval(smallerDate.getSeconds());
		return secondsDifference + ' ' + findPlural( secondsDifference, 'Second');
	}
	return 'OUT';

	function findPlural(number, unitString) {
		if(number == 1) {
			return unitString;
		} else {
			return unitString + 's';
		}
	}
}