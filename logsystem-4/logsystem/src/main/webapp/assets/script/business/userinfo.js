 var vm;
      
      $(function(){
    	  initVue();
         // vm.getJsonList();
          vm.getUserInfo();
   	 
      })
      
      
      function initVue(){
    	  
    		vm = new Vue({
    	        el: '#vueBox',
    	        data:function(){
    	        	
    	             return {
    	            	  modal:false,
    	              param: {
    	            	  id:"",
    	            	  pageNum: 1,
    	            	  pageSize: 10
    	                },
    	                title:'',
    	                modalLoading: true,
    	                detailModel:false,
    	                showDialog:false,
    	                total:0,
    	                file:[],
    	                detailform:{
    	                },
    	              
    	                columns1: [
               {
					type : 'index',
					title : '序号',
					align : 'center'
				},
    	                    { title: '用户名',  key: 'userName' },
    	                    { title: '昵称',  key: 'nick' },
    	                    { title: '性别',  key: 'sex' },
    	                    { title: '邮箱',  key: 'mail' },
    	                    { title: '头像',  key: 'photoUrl',
    				           	render: function(h, params) {
    							    return h('div', [
    							      h('img', {
    							      domProps: {
    							        'src': params.row.photoUrl
    							      },
    							      style: {
    							        display: 'block',
    							        width: '30px',
    							        height: '30px',
    							        borderRadius: '3px',
    							      },
    							       })
    							     ])
    							    } },
    	                    { title: '封号状态',  key: 'isEnable' 
    	                    	,render: function (h, params) {
	                    		if(this.row.isEnable=='0'){
	                    			return  h('span', '已封号');
	                    		}else if(this.row.isEnable=='1'){
	                    			return  h('span', '正常使用');
	                    		}
	                    	}},
    	                    { title: '创建时间',  key: 'createTime'},
     	                    {
    	                        title: '操作',
    	                        key: 'action',
    	                        width: 310,
    	                        align: 'center',
    	                        render: function(h, params){
    	                            return h('div', [
    	                                h('Button', {
    	                                    props: {
    	                                        type: 'primary',
    	                                        size: 'small'
    	                                    },
    	                                    style: {
    	                                        marginRight: '5px',
    	                                    },
    	                                    on: {
    	                                        click:function(){
    	                                        	vm.update(params.row);
    	                                        }
    	                                    }
    	                                }, '修改'),
    	                                h('Button', {
    	                                    props: {
    	                                        type: 'primary',
    	                                        size: 'small'
    	                                    },
    	                                    style: {
    	                                        marginRight: '5px',
    	                                    },
    	                                    on: {
    	                                        click:function(){
    	                                        	vm.deleteRow(params.row.userId);
    	                                        }
    	                                    }
    	                                }, '删除'),
    	                                h('Button', {
    	                                    props: {
    	                                        type: 'primary',
    	                                        size: 'small'
    	                                    },
    	                                    style: {
    	                                        marginRight: '5px',
    	                                    },
    	                                    on: {
    	                                        click:function(){
    	                                        	vm.blackUser(params.row.userId);
    	                                        }
    	                                    }
    	                                }, '封号')
    	               
    	                            ]);
    	                        }
    	                    }
    	                    
    	                ],
    	               data1:[],
    	               ruleInline: {
    	            	   userName: [
   	                        { required: true, message: '不能为空', trigger: 'blur' }
   	                    ],
   	                   userPassword: [
   	                        { required: true, message: '不能为空', trigger: 'blur' },
   	                     ],
   	                  nick: [
     	        	                        { required: true, message: '不能为空', trigger: 'blur' },
     	        	                     ],
     	        	                    sex: [
     	           	                        { required: true, message: '不能为空', trigger: 'blur' },
     	           	                     ],
     	           	                 mail: [
     	           	   	                        { required: true, message: '不能为空', trigger: 'blur' },
     	           	   	                     ],
     	           	   	              isEnable: [
     	           	    	                        { required: true, message: '不能为空', trigger: 'blur' },
     	           	    	                     ],
   	                }
    	                
    	            }
    	        },
    	        created:function(){
    	        	//this.getUserInfo();
    	        },
    	        methods:{
    	        	addModel:function(){
    	        		this.modal=true;
    	        	},
    	            changePageNum: function (pageNum) {
    	              this.param.pageNum = pageNum;
    	              this.getJsonList();
    	            },
    	            handleUpload:function(file) {
    	                let that = this;
    	                if(that.file.length >= 1){
    	                  this.$Message.info("最多只能上传1个文件");
    	                }else{
    	                  that.file.push(file);
    	                }
    	                return false;
    	              },
    	         delFileList:function(index){
    	                  let that = this;
    	                  that.file.splice(index, 1);
    	                },
    	        search:function(){
          	        	   this.param.pageNum = 1;
        	        	   this.getJsonList();
        	       },
        	       blackUser:function(id){
        	          	$.ajax({
	    	                    type:"post",
	    	                    url:"web/manage/blackUser",
	    	                    data:{"userId":id},
	    	                    dataType:'json',
	    	                    async:false,
	    	                    success : function(data) {  
	    	                    	if(data.resultCode=='200'){
	    	                    		layer.msg('操作成功!');
 	    	                   		   vm.getJsonList();
	    	                   	}else{
	    	                   		
	    	                   		 layer.msg('操作失败！');
	    	                   	}
	    	                     
	    	                    },
	    	                   error :function() {
	    	                	   layer.msg('操作失败！');
	    	                    }
	    	              	 }); 
        	    	   
        	       },
        	       getUserInfo:function(){
        	    	   var that=this;
        	          	$.ajax({
	    	                    type:"get",
	    	                    url:"web/manage/getUserInfo",
	    	                    dataType:'json',
	    	                    async:false,
	    	                    success : function(data) {  
	    	                    	if(data.resultCode=='200'){
	    	                    		that.detailform=data.data;
	    	                   	}else{
	    	                   		
	    	                   		 layer.msg('刪除失败！');
	    	                   	}
	    	                     
	    	                    },
	    	                   error :function() {
	    	                	   layer.msg('刪除失败！');
	    	                    }
	    	              	 });
        	       },
        	   	 deleteRow:function(id){
    	       	          	 this.$Modal.confirm({
    	     	                    title: '提示框',
    	     	                    content: '<p>确定删除吗？</p>',
    	     	                    onOk:function(){
    	     	                    	$.ajax({
    	     	    	                    type:"post",
    	     	    	                    url:"web/manage/deleteUser",
    	     	    	                    data:{"userId":id},
    	     	    	                    dataType:'json',
    	     	    	                    async:false,
    	     	    	                    success : function(data) {  
    	     	    	                    	if(data.resultCode=='200'){
    	     	    	                    		layer.msg('刪除成功!');
	    	     	    	                   		vm.getJsonList();
    	     	    	                   	}else{
    	     	    	                   		
    	     	    	                   		 layer.msg('刪除失败！');
    	     	    	                   	}
    	     	    	                     
    	     	    	                    },
    	     	    	                   error :function() {
    	     	    	                	   layer.msg('刪除失败！');
    	     	    	                    }
    	     	    	              	 });
    	     	                    	
    	     	                    }});
    	     	     },
    	           getJsonList:function(){
    	        	   var load=this.$Loading;
    	        	   load.start();
    	        	   var index = layer.load(0, {shade: false}); 
    	        	   var Data = this.param;
    	            	$.ajax({
    	                  type:"post",
    	                  url:"web/manage/getExUserList",
    	                  data:Data,
    	                  dataType:'json',
    	                  async:false,
    	                  success : function(data) {  
    	                   vm.data1 = data.data.result;
    	                   vm.total = data.data.total;
    	                   load.finish();
    	                   layer.close(index);
    	                  },
    	                 error :function() {
    	                	 load.error();
    	                	 alert("后台报错");
    	                  }
    	            	 });
    	            },
    	            save:function(){
    	            
    	            	 var  that=this;
 	    	     		this.$refs['detailform'].validate(function(valid) {
 	                        if (valid) {
 	                       
 	                        	 //创建 formData 对象
 	               	              let formData = new FormData();
 	              	              formData.append('userName', that.detailform.userName);
 	              	              formData.append('userPassword', that.detailform.userPassword);
 	              	              formData.append('nick', that.detailform.nick);
 	              	           formData.append('sex', that.detailform.sex);
 	              	        formData.append('mail', that.detailform.mail);
 	              	          formData.append('isEnable', that.detailform.isEnable);
 	              	              if( that.detailform.userId!=undefined){
 	              	            	 formData.append('userId', that.detailform.userId);
 	              	              }
 	              	     		 if(that.file.length > 0){
 	              	              //多个文件上传
 	              	              for(var i=0; i< that.file.length; i++){ 
 	              	               formData.append("workFile",that.file[i]);  // 文件对象  
 	              	              } 
 	              	             }
    	       	       	$.ajax({
	    	                    type:"post",
	    	                    url:"web/manage/saveUser",
	    	                    data:formData,
	    	                    dataType:'json',
	    	                    async:false,
	            				 cache: false, 
	            				 contentType: false, //不设置内容类型
	            				 processData: false, //不处理数据
	    	                    success : function(data) {  
	    	                    	if(data.resultCode=='200'){
	    	                    			layer.msg('操作成功!');
     	    	                   		    vm.getJsonList();
     	    	                   		     that.handleReset();
     	    	                   		    that.getUserInfo();
	    	                   	}else{
	    	                   		 layer.msg('操作失败！');
	    	                   	}
	    	                     
	    	                    },
	    	                   error :function() {
	    	                	   layer.msg('操作失败！');
	    	                    }
	    	              	 });
 	                       }else{
	                        }
 	    	     		});
    	     	     },
    	     	     add:function(){
    	     	    	this.title="新增";
    	     	    	this.detailModel=true;
    	     	    	this.showDialog=true;
    	     	     },
    	     	    handleReset:function () {
    	     	    	this.$refs.detailform.resetFields();
    	     	      },
    	     	    update:function(row){
    	     	    	this.title="修改";
    	     	    	this.detailform.userId=row.userId;
    	     	    	this.detailform.userName=row.userName;
    	     	    	this.detailform.userPassword=row.userPassword;
    	     	    	this.detailform.nick=row.nick;
    	     	    	this.detailform.sex=row.sex;
    	     	    	this.detailform.mail=row.mail;
    	     	    	this.detailform.isEnable=row.isEnable;
    	     	    	this.detailModel=true;
    	     	    	this.showDialog=true;
    	     	    }
    	
    	     
    	   
    	        }
    	    });
    	}

    
      

      

      




      
        	 
