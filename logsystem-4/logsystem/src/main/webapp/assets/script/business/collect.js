 var vm;
      
      $(function(){
    	  initVue();
          vm.getJsonList();
          
   	 
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
    	                accountModel:false,
    	                showDialog:false,
    	                total:0,
    	                accountform:{},
    	                detailform:{
    	                },
    	              
    	                columns1: [
               {
					type : 'index',
					title : '序号',
					align : 'center'
				},
    	                    { title: '日志标题',  key: 'title' },
    	                    { title: '收藏时间',  key: 'createTime'},
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
    	                                        	vm.viewDetail(params.row.logId);
    	                                        }
    	                                    }
    	                                }, '查看内容')
    	                         
    	               
    	                            ]);
    	                        }
    	                    }
    	                    
    	                ],
    	               data1:[],
    	               ruleInline: {
    	            	   expertName: [
   	                        { required: true, message: '不能为空', trigger: 'blur' }
   	                    ],
   	                 deptName: [
   	                        { required: true, message: '不能为空', trigger: 'blur' },
   	                     ],
   	                  doctorTitleName:[
   	    	                        { required: true, message: '不能为空', trigger: 'blur' },
   	    	              ],
   	    	           createTime: [
   	    	    	               { required: true, message: '不能为空', trigger: 'blur' },
   	    	    	       ]
   	    	    	   
   	                },
   	             accountInline:{
   	            	       userName: [
	       	                        { required: true, message: '不能为空', trigger: 'blur' }
	       	                    ],
	       	                 userPassword: [
	       	                        { required: true, message: '不能为空', trigger: 'blur' },
	       	                     ]
	       	                 
	       	    	    	   
	       	                }
    	                
    	            }
    	        },
    	        created:function(){
    	        	
    	        },
    	        methods:{
    	        	addModel:function(){
    	        		this.modal=true;
    	        	},
    	            changePageNum: function (pageNum) {
    	              this.param.pageNum = pageNum;
    	              this.getJsonList();
    	            },
    	     	     viewDetail:function(id){
         	    	    var that=this;
         	    	 	$.ajax({
 	    	                    type:"post",
 	    	                    url:"web/manage/findLogById",
 	    	                    data:{"id":id},
 	    	                    dataType:'json',
 	    	                    async:false,
 	    	                    success : function(data) {  
 	    	                    	if(data.resultCode=='200'){
 	    	                    		$("#contentString").html(data.data);
 	    	                    		that.detailModel=true;
 	    	                    		that.showDialog=true;
 	    	                   	}
 	    	                     
 	    	                    },
 	    	                   error :function() {
 	    	                	   vm.error('操作失败！');
 	    	                    }
 	    	              	 });
         	    	 
         	     },
    	        search:function(){
          	        	   this.param.pageNum = 1;
        	        	   this.getJsonList();
        	       },
        	     disAccount:function(id){
        	    	 this.accountModel=true;
        	    	 this.accountform.id=id
        	    	   
        	       },
        	   	 deleteRow:function(id){
    	       	          	 this.$Modal.confirm({
    	     	                    title: '提示框',
    	     	                    content: '<p>确定删除吗？</p>',
    	     	                    onOk:function(){
    	     	                    	$.ajax({
    	     	    	                    type:"post",
    	     	    	                    url:"web/manage/deleteExExpertInfo",
    	     	    	                    data:{"id":id},
    	     	    	                    dataType:'json',
    	     	    	                    async:false,
    	     	    	                    success : function(data) {  
    	     	    	                    	if(data.resultCode=='200'){
    	     	    	                    		vm.success('刪除成功!');
	    	     	    	                   		vm.getJsonList();
    	     	    	                   	}else{
    	     	    	                   		
    	     	    	                   		 vm.warning('刪除失败！');
    	     	    	                   	}
    	     	    	                     
    	     	    	                    },
    	     	    	                   error :function() {
    	     	    	                	   vm.error('刪除失败！');
    	     	    	                    }
    	     	    	              	 });
    	     	                    	
    	     	                    }});
    	     	     },
    	           getJsonList:function(){
    	               this.$Loading.start();
    	        	 /*  var load=this.$Loading;
    	        	   load.start();
    	        	   var index = layer.load(0, {shade: false}); */
    	        	   var Data = this.param;
    	            	$.ajax({
    	                  type:"post",
    	                  url:"web/manage/getCollectList",
    	                  data:Data,
    	                  dataType:'json',
    	                  async:false,
    	                  success : function(data) {  
    	                   vm.data1 = data.data.result;
    	                   vm.total = data.data.total;
    	                  /* load.finish();
    	                   layer.close(index);*/
    	                   vm.$Loading.finish();
    	                  },
    	                 error :function() {
    	                	 this.$Loading.error();
    	                  }
    	            	 });
    	            },
    	            save:function(row){
    	            	 var  that=this;
 	    	     		this.$refs['detailform'].validate(function(valid) {
 	                        if (valid) {
    	       	       	$.ajax({
	    	                    type:"post",
	    	                    url:"web/manage/saveOrUpdateExExpertInfo",
	    	                    data:that.detailform,
	    	                    dataType:'json',
	    	                    async:false,
	    	                    success : function(data) {  
	    	                    	if(data.resultCode=='200'){
     	    	                   		    vm.getJsonList();
     	    	                   		    that.detailModel=false;
     	    	                   		     that.showDialog=false;
     	    	                   		     that.handleReset("detailform");
     	    	                   		     that.success('操作成功!');
	    	                   	}else{
	    	                   		 that.warning('操作失败！');
	    	                   	}
	    	                     
	    	                    },
	    	                   error :function() {
	    	                	   this.error('操作失败！');
	    	                    }
	    	              	 });
 	                       }else{
	                        	that.$refs.modal.visible = true;
	                        	 that.modalLoading = false;
	                        	 that.detailModel=true;
	                        }
 	    	     		});
    	     	     },
    	     	    success:function(content) {
    	                 this.$Message.success(content);
    	             },
    	             warning:function (content) {
    	                 this.$Message.warning(content);
    	             },
    	             error:function (content) {
    	                 this.$Message.error(content);
    	             },
    	     	    saveAccount:function(row){
    	            	 var  that=this;
 	    	     		this.$refs['accountform'].validate(function(valid) {
 	                        if (valid) {
    	       	       	$.ajax({
	    	                    type:"post",
	    	                    url:"web/manage/disAccount",
	    	                    data:that.accountform,
	    	                    dataType:'json',
	    	                    async:false,
	    	                    success : function(data) {  
	    	                    	if(data.resultCode=='200'){
	    	                    		if(data.data==-1){
	    	                    			that.warning('不能重复添加!');
    	    	                   		     that.accountModel=false;
    	    	                   		     that.handleReset("accountform");
	    	                    		}else{
	    	                    			that.success('操作成功!');
    	    	                   		     that.accountModel=false;
    	    	                   		     that.handleReset("accountform");
	    	                    			
	    	                    		}
	    	                    			
	    	                   	}else{
	    	                   		 that.warning('操作失败！');
	    	                   	}
	    	                     
	    	                    },
	    	                   error :function() {
	    	                	   this.error('操作失败！');
	    	                    }
	    	              	 });
 	                       }else{
	                        	that.$refs.accountModel.visible = true;
	                        	 that.modalLoading = false;
	                        	 that.accountModel=true;
	                        }
 	    	     		});
    	     	     },
    	     	     add:function(){
    	     	    	this.title="新增";
    	     	    	this.detailModel=true;
    	     	    	this.showDialog=true;
    	     	     },
    	     	    handleReset:function (name) {
    	     	    	this.$refs[name].resetFields();
    	     	      },
    	     	    update:function(row){
    	     	    	this.title="修改";
    	     	    	this.detailform.id=row.id;
    	     	    	this.detailform.expertName=row.expertName;
    	     	    	this.detailform.deptName=row.deptName;
    	     	    	this.detailform.doctorTitleName=row.doctorTitleName;
    	     	    	this.detailModel=true;
    	     	    	this.showDialog=true;
    	     	    }
    	
    	     
    	   
    	        }
    	    });
    	}

    
      

      

      




      
        	 
