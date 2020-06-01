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
            
                    <i-button type="primary" @click="search">查询</i-button>
                    <i-button type="primary" @click="add">新增</i-button>
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
                 </div>
				</div><!-- /.page-content -->
				</div><!-- /.main-content -->



             <%-- <jsp:include page="/WEB-INF/page/manage/common/Left.jsp"></jsp:include> --%>
			</div><!-- /.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
		
	<div class="modal fade" id="addDiv"  tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog" width="700px">
		<div class="modal-content"  style="width:750px;">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h3 class="modal-title" id="myModalLabel">
				  <span  id="logtitle"></span>日志
				</h3>
			</div>
			<div class="modal-body">
	         <form role="form" id="from1">
			 <div class="input-group" style="margin-top: 5px">
             <span class="input-group-addon" id="basic-addon2"><span class="glyphicon glyphicon-modal-window"></span></span>
             <input type="text" class="form-control" placeholder="请输入日志标题" aria-describedby="basic-addon2" name="title" id="title"/>
             </div>
             <div class="input-group" style="margin-top: 5px">
             <span class="input-group-addon " id="basic-addon1" ><span class="glyphicon  glyphicon-modal-window"></span></span>
             <div id="editor">
                                                              
              </div>
             </div>
              <input type="hidden" name="id" id="logid"/>
			</form>
			</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" data-dismiss="modal">关闭
				</button>
				<button type="button" class="btn btn-primary" onclick="save()">
					保存
				</button>
			</div>
		</div>
	</div>
   </div>
   
   
        <script src="assets/js/jquery-1.10.2.min.js"></script>
       <script type="text/javascript" src="assets/js/layer/layer.js"></script>
        <script src="assets/js/vue.2.5.17/vue.min.js" type="text/javascript"></script>
        <script src="assets/js/iview/iview.min.js" type="text/javascript"></script>
        <script src="assets/js/ace-extra.min.js"></script>
		<script src="assets/js/bootstrap.min.js"></script>
		<script src="assets/js/date-time/bootstrap-datepicker.min.js"></script>
		<script src="assets/js/ace-elements.min.js"></script>
		<script src="assets/js/ace.min.js"></script>
		<script src="assets/script/business/log.js"></script>

         <script type="text/javascript" src="assets/js/wangEditor-3.1.1/release/wangEditor.min.js"></script>
         <script type="text/javascript">
        var E = window.wangEditor
        var editor = new E('#editor')
        editor.customConfig.uploadImgServer = 'web/manage/uploadFile'
        // 将图片大小限制为 3M
        editor.customConfig.uploadImgMaxSize = 3 * 1024 * 1024
       // 限制一次最多上传 5 张图片
        editor.customConfig.uploadImgMaxLength = 5
        	editor.customConfig.showLinkImg = false;
        editor.customConfig.withCredentials = true;
        editor.customConfig.uploadImgParamsWithUrl = true;
        editor.customConfig.uploadFileName = 'workFile';

        editor.customConfig.uploadImgHooks = {

         // 如果服务器端返回的不是 {errno:0, data: [...]} 这种格式，可使用该配置
         // （但是，服务器端返回的必须是一个 JSON 格式字符串！！！否则会报错）
         customInsert: function (insertImg, result, editor) {
             console.log(result);
             if(result.resultCode == 200){
                     let imgUrl = result.data;
        		     insertImg(imgUrl)
             }
         }
        };

        //用户操作（鼠标点击、键盘打字等）导致的内容变化之后，会自动触发此函数并获取富文本中的所有内容
       /*   editor.customConfig.onchange = (html) => {
         this.form.editorContent = html
        }   */
        editor.customConfig.zIndex = 1;    //防止富文本编辑器被别的内容所覆盖     
        editor.create();
        
        
        function save(){
        	var title=$("#title").val();
        	var id=$("#logid").val();
        	var content=editor.txt.html();
        	$.ajax({
                type:"post",
                url:"web/manage/saveLog",
                data:{"content":content,"title":title,"id":id},
                dataType:'json',
                async:false,
                success : function(data) {  
                	if(data.resultCode=='200'){
                	        	 $('#addDiv').modal('hide');
	                   		    vm.getJsonList();
	                   		    layer.msg("操作成功")
               	}else{
               	     layer.msg("操作成功")
               	}
                 
                },
               error :function() {
            	   layer.msg("操作成功")
                }
          	 });
    	  
    	  
      }

        function upateLog(row){
        	 $("#logtitle").text("修改");
	    	 $("#logid").val(row.id);
	    	 $("#title").val(row.title);
	    	 editor.txt.html(row.contentString);
	    	 $('#addDiv').modal('show');
        	
        	
        	
        }

    </script>
</body>
</html>
