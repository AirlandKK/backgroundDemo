<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!-- jQuery 3 -->
<script src="assets/js/jquery-1.10.2.min.js"></script> 
				
 <script type="text/javascript">
      $(function(){
    	  var list = $('.sidebar').find('ul').find('li');
    	  for(var i = 0; i < list.length; i++) {
    		  if(window.location.pathname=="/"+$(list[i]).find("a").attr("href")){
    			  $(list[i]).addClass("active");
    			  if($(list[i]).parent("ul").length>0){
    				//  $(list[i]).parent("ul").addClass("active open");
    				  if($(list[i]).parent("ul").parent("li").length>0){
    					  $(list[i]).parent("ul").parent("li").addClass("active open");
    				  }
    			  }
    		  }
            }
      }) 

    </script>
<meta http-equiv="X-UA-Compatible" content="IE=10">	
		<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>
		<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed')}catch(e){}
					</script>

					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-success">
								<i class="icon-signal"></i>
							</button>

							<button class="btn btn-info">
								<i class="icon-pencil"></i>
							</button>

							<button class="btn btn-warning">
								<i class="icon-group"></i>
							</button>

							<button class="btn btn-danger">
								<i class="icon-cogs"></i>
							</button>
						</div>

						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->

					<ul class="nav nav-list">
					<c:if test="${exUser.userType eq 3 }">
						<li >
							<a href="web/manage/toAdminLog">
								<i class="icon-dashboard"></i>
								<span class="menu-text">日志管理</span>
							</a>
						</li>
						<li >
							<a href="web/manage/toUser">
								<i class="icon-dashboard"></i>
								<span class="menu-text">用户管理 </span>
							</a>
						</li>
						</c:if>
						<c:if test="${exUser.userType eq 2 }">
						  <li >
							<a href="web/manage/toUserInfo">
								<i class="icon-dashboard"></i>
								<span class="menu-text">基本资料 </span>
							</a>
						</li>
						<li >
							<a href="web/manage/toAllLog">
								<i class="icon-dashboard"></i>
								<span class="menu-text">全部日志 </span>
							</a>
						</li>
							<li >
							<a href="web/manage/toLog">
								<i class="icon-dashboard"></i>
								<span class="menu-text">我的日志 </span>
							</a>
						</li>
						<li >
							<a href="web/manage/toComment">
								<i class="icon-dashboard"></i>
								<span class="menu-text">我的评论 </span>
							</a>
						</li>
						<li >
							<a href="web/manage/toCollect">
								<i class="icon-dashboard"></i>
								<span class="menu-text">我的收藏 </span>
							</a>
						</li>
						</c:if>

			<!-- 			<li>
							<a href="web/manage/toData" class="dropdown-toggle">
								<i class="icon-list"></i>
								<span class="menu-text">专家管理</span>

								<b class="arrow icon-angle-down"></b>
							</a>

							<ul class="submenu">

								<li>
									<a href="web/manage/toExpert">
										<i class="icon-double-angle-right"></i>
										专家信息列表
									</a>
								</li>
					
							</ul>
						</li> -->


			
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed')}catch(e){}
					</script>
				</div>
