<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() 
	                   + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html>
	<head>
	   <base href="<%=basePath%>"/>
		<meta charset="utf-8" />
		<title>日志列表</title>


		<meta name="viewport" content="width=device-width, initial-scale=1.0" />

		<link href="assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="assets/css/font-awesome.min.css" />
        <link rel="stylesheet" href="assets/js/iview/iview.css">
		<link rel="stylesheet" href="assets/css/ace.min.css" />
		<link rel="stylesheet" href="assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="assets/css/ace-skins.min.css" />

	</head>

	<body>
	
       <jsp:include page="/WEB-INF/page/manage/common/Header.jsp"></jsp:include>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed')}catch(e){}
			</script>

			<div class="main-container-inner">
			

			 <jsp:include page="/WEB-INF/page/manage/common/LeftMenu.jsp"></jsp:include>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="#">主页</a>
							</li>

							<li>
								<a href="#">日志信息</a>
							</li>
							<li class="active">日志信息管理</li>
						</ul><!-- .breadcrumb -->


					</div>

			<div class="page-content" id="vueBox">
			<i-form  inline>	
			
				<div class="page-header">
							<h1>
								日志信息
							</h1>
						</div>
					
				
			 <Form-item label="昵称 " :label-width="80">
                     <i-input v-model="param.nick" placeholder="请输入昵称" style="width: 200px" />
             </Form-item> 
             <Form-item label="关键词 " :label-width="80">
                     <i-input v-model="param.contentParam" placeholder="请输入关键词" style="width: 200px" />
             </Form-item> 
                    <i-button type="primary" @click="search">查询</i-button>
            </i-form>
			
			<div class="page-header">
							<h1>
								日志信息列表
							</h1>
						</div><!-- /.page-header -->
			
				<div class="tbDiv">
				  <i-table border stripe :columns="columns1" :data="data1"></i-table>
                  <Page :total="total" :current="param.page" :page-size="param.rows" @on-change="changePageNum" show-total show-elevator></Page>
                  
                 <Modal v-model="detailModel" draggable scrollable title="查看详情" >
                         <span  id="contentString"></span>
                 </Modal>
                 
                      <Modal v-model="commentModel" draggable scrollable title="发表评论" @on-ok="save"  :mask-closable="false" :loading="modalLoading" ref="modal">
                          <i-form  :model="detailform" label-position="left" :label-width="100"   :rules="ruleInline" ref="detailform"  >
                                     <hr/>
                                     <div style="text-align: center;color: black;font-size: 15px;font-weight: bold;">评论列表</div>
                                     <div style="margin-bottom:20px;">
                                     <span style="margin-bottom:10px;" v-for="(item,index) in commentList" :key="index" >{{item.createTime}}&nbsp;&nbsp;&nbsp;&nbsp;<font color="red">{{item.userName}}</font>用户&nbsp;&nbsp;:&nbsp;&nbsp;<font color="red">{{item.content}}<br/></font><hr/></span>
                                     </div>
                                     <hr/>
                                     <div style="text-align: center;color: black;font-size: 15px;font-weight: bold;">评论区</div>
                                     <div style="margin-top: 33px;">
                                     <Form-item label="评论内容" prop="content">
                                             <i-input type="textarea" :rows="4" v-model="detailform.content" ></i-input>
                                     </Form-item>
                                     </div>
                          </i-form>
                 </Modal>
                 
                 
                 </div>
				</div><!-- /.page-content -->
				</div><!-- /.main-content -->

             <%-- <jsp:include page="/WEB-INF/page/manage/common/Left.jsp"></jsp:include> --%>
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
        <script src="assets/js/jquery-1.10.2.min.js"></script>
       <script type="text/javascript" src="assets/js/layer/layer.js"></script>
        <script src="assets/js/vue.2.5.17/vue.min.js" type="text/javascript"></script>
        <script src="assets/js/iview/iview.min.js" type="text/javascript"></script>
        <script src="assets/js/ace-extra.min.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
		<script src="assets/script/business/allLog.js"></script>


</body>
</html>
