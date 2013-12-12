<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript">
	$(document).ready(function() {
		$("#featured").tabs({
			fx : {
				opacity : "toggle"
			}
		}).tabs("rotate", 3500, true);
		$("#featured").hover(function() {
			$("#featured").tabs("rotate", 0, true);
		}, function() {
			$("#featured").tabs("rotate", 3500, true);
		});
	});
</script>
<link href="${webRoot}/media/css/slider_style.css" rel="stylesheet"
	type="text/css" media="all" />
<%@ taglib prefix="s" uri="/struts-tags"%>
<div class="flW">
	<div class="pageTitle">Welcome to donateblood.com!</div>
	

		<div id="featured">
			<ul class="ui-tabs-nav">
				<li class="ui-tabs-nav-item ui-tabs-selected" id="nav-fragment-1"><a
					href="#fragment-1"><img
						src="${webRoot}/media/images/slider/image1-small.jpg" alt="" /></a></li>
				<li class="ui-tabs-nav-item" id="nav-fragment-2"><a
					href="#fragment-2"><img
						src="${webRoot}/media/images/slider/image2-small.jpg" alt="" /></a></li>
				<li class="ui-tabs-nav-item" id="nav-fragment-3"><a
					href="#fragment-3"><img
						src="${webRoot}/media/images/slider/image3-small.jpg" alt="" /></a></li>
				<li class="ui-tabs-nav-item" id="nav-fragment-4"><a
					href="#fragment-4"><img
						src="${webRoot}/media/images/slider/image4-small.jpg" alt="" /></a></li>
                        <li class="ui-tabs-nav-item" id="nav-fragment-5"><a
					href="#fragment-5"><img
						src="${webRoot}/media/images/slider/image2-small.jpg" alt="" /></a></li>
			</ul>

			<!-- First Content -->
			<div id="fragment-1" class="ui-tabs-panel" style="">
				<img src="${webRoot}/media/images/slider/image1.jpg" alt="" />
				<div class="info">
					<h2>
						<a href="#">15+ Excellent High Speed Photographs</a>
					</h2>
					<p>
						Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla
						tincidunt condimentum lacus. Pellentesque ut diam....<a href="#">read
							more</a>
					</p>
				</div>
			</div>

			<!-- Second Content -->
			<div id="fragment-2" class="ui-tabs-panel ui-tabs-hide" style="">
				<img src="${webRoot}/media/images/slider/image2.jpg" alt="" />
				<div class="info">
					<h2>
						<a href="#">20 Beautiful Long Exposure Photographs</a>
					</h2>
					<p>
						Vestibulum leo quam, accumsan nec porttitor a, euismod ac tortor.
						Sed ipsum lorem, sagittis non egestas id, suscipit....<a href="#">read
							more</a>
					</p>
				</div>
			</div>

			<!-- Third Content -->
			<div id="fragment-3" class="ui-tabs-panel ui-tabs-hide" style="">
				<img src="${webRoot}/media/images/slider/image3.jpg" alt="" />
				<div class="info">
					<h2>
						<a href="#">35 Amazing Logo Designs</a>
					</h2>
					<p>
						liquam erat volutpat. Proin id volutpat nisi. Nulla facilisi.
						Curabitur facilisis sollicitudin ornare....<a href="#">read
							more</a>
					</p>
				</div>
			</div>

			<!-- Fourth Content -->
			<div id="fragment-4" class="ui-tabs-panel ui-tabs-hide" style="">
				<img src="${webRoot}/media/images/slider/image4.jpg" alt="" />
				<div class="info">
					<h2>
						<a href="#">Create a Vintage Photograph in Photoshop</a>
					</h2>
					<p>
						Quisque sed orci ut lacus viverra interdum ornare sed est. Donec
						porta, erat eu pretium luctus, leo augue sodales....<a href="#">read
							more</a>
					</p>
				</div>
			</div>
            
            <div id="fragment-5" class="ui-tabs-panel ui-tabs-hide" style="">
				<img src="${webRoot}/media/images/slider/image2.jpg" alt="" />
				<div class="info">
					<h2>
						<a href="#">Create a Vintage Photograph in Photoshop</a>
					</h2>
					<p>
						Quisque sed orci ut lacus viverra interdum ornare sed est. Donec
						porta, erat eu pretium luctus, leo augue sodales....<a href="#">read
							more</a>
					</p>
				</div>
			</div>

		</div>
		<br/><br/>
        <div>
		<p><strong>SaveALifeToday</strong> is a non-commercial, non-profit organization that assists you in searching for a voluntary blood donor in your area.</p>
		 <p>Thanks to good hearts and will of many people from across the country who agree to be voluntary donors we could create this platform and bring together those in need of help and those who want to come to the rescue.</p>
		</div>

	</div>
