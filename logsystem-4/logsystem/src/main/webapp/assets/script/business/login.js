


/**
 * 登录
 */
function login(){

    $.ajax({
        type: "post",
        url: "web/visitor/userLogin",
        data: $("#loginform").serialize(),
        dataType: "json",
        success: function(data){
                 if(data.flag){
                	 $("#hip").empty();
                	 if(data.user.userType=='3'){
                		 window.location.href="web/manage/toAdminLog";
                	 }else{
                		 window.location.href="web/manage/toUserInfo";
                	 }
                    
                     
                     toProjectResult
                 }else{
                	 $("#hip").empty();
                	 $("#hip").html(data.resultMsg);
                 }

         }

    });

}




function reigster(){
	var userName=$("#userName").val();
	var userPassword=$("#userPassword").val();
	var userAgainPassword=$("#userAgainPassword").val();
    if(userName==''){
    	alert("用户名不能为空")
    	return ;
    }
    if(userPassword==''){
    	alert("密码不能为空")
    	return ;
    }
    if(userAgainPassword==''){
    	alert("确认密码不能为空")
    	return ;
    }
    if(userAgainPassword!=userPassword){
    	alert("两次密码不一致!")
    	return ;
    }
    $.ajax({
        type: "post",
        url: "web/visitor/saveAccount",
        data: $("#reigsterForm").serialize(),
        dataType: "json",
        success: function(data){
                 if(data.flag){
                	 $("#registehip").empty();
                     window.location.href="web/visitor/toLogin";
                 }else{
                	 $("#registehip").empty();
                	 $("#registehip").html(data.resultMsg);
                 }

         }

    });

}
