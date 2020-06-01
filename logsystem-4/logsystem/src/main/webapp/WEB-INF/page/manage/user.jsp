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
		<title>用户信息管理列表</title>


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
								<a href="#">用户信息</a>
							</li>
							<li class="active">用户信息管理</li>
						</ul><!-- .breadcrumb -->


					</div>

			<div class="page-content" id="vueBox">
			<i-form  inline>	
			
				<div class="page-header">
							<h1>
								用户信息
							</h1>
						</div>
					
				
			 <Form-item label="用户名 " :label-width="80">
                     <i-input v-model="param.userName" placeholder="请输入用户名 " style="width: 200px" />
             </Form-item> 
                    <i-button type="primary" @click="search">查询</i-button>
                    <i-button type="primary" @click="add">新增</i-button>
            </i-form>
			
			<div class="page-header">
							<h1>
								用户信息列表
							</h1>
						</div><!-- /.page-header -->
			
				<div class="tbDiv">
				  <i-table border stripe :columns="columns1" :data="data1"></i-table>
                  <Page :total="total" :current="param.page" :page-size="param.rows" @on-change="changePageNum" show-total show-elevator></Page>
                  
                 <Modal v-model="detailModel" draggable scrollable :title=title @on-ok="save"  :mask-closable="false" :loading="modalLoading" ref="modal">
                          <i-form v-if="showDialog" :model="detailform" label-position="left" :label-width="100"   :rules="ruleInline" ref="detailform"  >
                                     <Form-item label="用户名" prop="userName">
                                             <i-input v-model="detailform.userName" ></i-input>
                                     </Form-item>
                                    <Form-item label="密码"  prop="userPassword">
                                              <i-input type="password" v-model="detailform.userPassword" ></i-input>
                                    </Form-item>
                                     <Form-item label="昵称" prop="nick">
                                             <i-input v-model="detailform.nick" ></i-input>
                                     </Form-item>
                                      <Form-item label="性别" prop="sex">
                                               <i-select v-model="detailform.sex" placeholder="请选择"  clearable v-cloak>
                                                     <i-option value="男" >男</i-option>
                                                      <i-option value="女" >女</i-option>
                                                </i-select>   
                                     </Form-item>
                                      <Form-item label="邮箱" prop="mail">
                                             <i-input v-model="detailform.mail" ></i-input>
                                     </Form-item>
                                             <Form-item label="头像">
                                          <Upload
                                                 ref="upload"
                                                 type="drag"
                                                 :format="['docx','doc','txt', 'pdf']"
                                                 :max-size="5000"
                                                 :before-upload="handleUpload"
                                                 >
                                                 <div style="padding: 20px 0">
                                                      <Icon type="ios-cloud-upload" size="52" style="color: #3399ff"></Icon>
                                                          <p>点击或者拖拽到此次上传文件</p>
                                                 </div>
                                          </Upload>
                                      <div>
                                            <ul class="file-list" v-for="(list,index) in file" :key="index">
                                              <li>文件名: <span style="font-size:15px;">{{ list.name }}</span> <Icon type="ios-close" size="20" style="float:right;" @click="delFileList(index)"></Icon></li>
                                           </ul>
                                      </div>
                                   </Form-item>
                                      <Form-item label="是否封号" prop="isEnable">
                                               <i-select v-model="detailform.isEnable" placeholder="请选择"  clearable v-cloak>
                                                     <i-option value="0" >是</i-option>
                                                      <i-option value="1" >否</i-option>
                                                </i-select>   
                                     </Form-item>
                                 
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
		<script src="assets/script/business/user.js"></script>


</body>
</html>
