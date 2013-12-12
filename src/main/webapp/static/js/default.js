$(document).ready(function() {

	//Default Action for Tours Sections
	 $(".findDonorTabsCnt").css({ display:"none"}); //Hide all content
	 $(".findDonorTabs ul li:first").addClass("selected").css({ display:"block"}); 	//Activate first tab
	 $(".findDonorTabsCnt:first").css({ display:"block"}); //Show first tab content
 //On Click Event
	 $(".findDonorTabs ul li").click(function() {
	 $(".findDonorTabs ul li").removeClass("selected"); //Remove any "active" class
	 $(this).addClass("selected"); //Add "active" class to selected tab
	 $(".findDonorTabsCnt").css({ display:"none"}); //Hide all tab content
	 var activeTab = $(this).find("a").attr("href"); //Find the rel attribute value to identify the active tab + content
	 $(activeTab).fadeIn();
	 //$(activeTab).css({ display:"block"}); //Fade in the active content
	 return false;
 });

	



});


$(function(){
	
	initDrop();
	
	
})

//popups init
function initDrop(){
	var activeClass = 'open-drop';
	$('.top-menu').each(function() {
		
		var menu = $(this);
		var items = menu.find('> li');
		items.each(function() {
			
			var item = $(this);
			var opener = item.find('a.btn-open');
			
			var drop = item.find('div.drop');
			
			if(drop.length) {
				
				opener.click(function(e) {
					
					items.not(item).removeClass(activeClass);
					item.toggleClass(activeClass);
					e.preventDefault();
				})
			}
		})
		$('body').click(function(e) {
			
			if(!$(e.target).parents('.top-menu > li').length) {
				items.removeClass(activeClass);
			}
		})
	});
};


$(document).ready(function() {
	$(".datatable tr").mouseover(function() {$(this).addClass("trHovrClr");}).mouseout(function() {$(this).removeClass("trHovrClr");})
});