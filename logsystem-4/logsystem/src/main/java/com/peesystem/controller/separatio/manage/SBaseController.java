package com.peesystem.controller.separatio.manage;

import java.io.File;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public class SBaseController {
	
	
	/**
	 * 新增
	 * @param view
	 * @param session
	 * @return
	 */
	public String getPath(MultipartFile file,HttpServletRequest request){
		   try{
			     if (file!=null) {// 判断上传的文件是否为空
			            String path=null;// 文件路径
			            String type=null;// 文件类型
			            String cupath=null;
			            String fileName=file.getOriginalFilename();// 文件原名称
			            // 判断文件类型
			            type=fileName.indexOf(".")!=-1?fileName.substring(fileName.lastIndexOf(".")+1, fileName.length()):null;
			            if (type!=null) {// 判断文件类型是否为空
			            	 // 项目在容器中实际发布运行的根路径
		                    String realPath=request.getSession().getServletContext().getRealPath("/");
		                    // 自定义的文件名称
		                    String trueFileName=String.valueOf(System.currentTimeMillis())+fileName;
		                    // 设置存放图片文件的路径
		                    path=realPath+"/uploadimage/"+/*System.getProperty("file.separator")+*/trueFileName;
		                    // 转存文件到指定的路径
		                    file.transferTo(new File(path));
		                    cupath="uploadimage/"+trueFileName;
			            }else {
			                System.out.println("文件类型为空");
			                return null;
			            }
			            return  cupath;
			        }else {
			            System.out.println("没有找到相对应的文件");
			            return null;
			        }
			    }catch(Exception e){
				e.printStackTrace();
				return  null;
			   }
	    }

}
