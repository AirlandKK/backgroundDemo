package com.peesystem.common.utils;

import com.peesystem.constans.Constant;
import com.peesystem.entity.vo.ResultVo;

public class ResultUtil {
	
	
	
	/**
	 * 成功
	 * @return
	 */
	public static ResultVo success(Object ...object){
		if(object.length>=2){
			return new ResultVo(Constant.RESULT_STATE.OK.getResultState(), object[0],object[1]!=null?object[1].toString():null);
		}else if(object.length==1){
			return new ResultVo(Constant.RESULT_STATE.OK.getResultState(), object[0]);
		}else{
			return new ResultVo(Constant.RESULT_STATE.OK.getResultState(), null);
		}
	}
	
	/**
	 * 失败
	 * @return
	 */
	public static ResultVo fail(Object ...object){
		if(object.length>=2){
			return new ResultVo(Constant.RESULT_STATE.FAIL.getResultState(), object[0],object[1]!=null?object[1].toString():null);
		}else if(object.length==1){
			return new ResultVo(Constant.RESULT_STATE.FAIL.getResultState(), object[0]);
		}else{
			return new ResultVo(Constant.RESULT_STATE.FAIL.getResultState(), "返回的结果，传入参数错误");
		}
	}
	
	
	/**
	 * 异常
	 * @return
	 */
	public static ResultVo exception(Object ...object){
		if(object.length>=2){
			return new ResultVo(Constant.RESULT_STATE.EXCEPTION.getResultState(), object[0],object[1]!=null?object[1].toString():null);
		}else if(object.length==1){
			return new ResultVo(Constant.RESULT_STATE.EXCEPTION.getResultState(), object);
		}else{
			return new ResultVo(Constant.RESULT_STATE.FAIL.getResultState(), "返回的结果，传入参数错误");
		}
	}
	
	
	/**
	 * 未登录
	 * @return
	 */
	public static ResultVo notLogin(Object ...object){
		if(object.length>=2){
			return new ResultVo(Constant.RESULT_STATE.NOT_LOGIN.getResultState(), object[0],object[1]!=null?object[1].toString():null);
		}else if(object.length==1){
			return new ResultVo(Constant.RESULT_STATE.NOT_LOGIN.getResultState(), object);
		}else{
			return new ResultVo(Constant.RESULT_STATE.FAIL.getResultState(), "返回的结果，传入参数错误");
		}
	}
	
	/**
	 * 没权限
	 * @param object
	 * @return
	 */
	public static ResultVo notAuth(Object ...object){
		if(object.length>=2){
			return new ResultVo(Constant.RESULT_STATE.NOT_AUTH.getResultState(), object[0],object[1]!=null?object[1].toString():null);
		}else if(object.length==1){
			return new ResultVo(Constant.RESULT_STATE.NOT_AUTH.getResultState(), object);
		}else{
			return new ResultVo(Constant.RESULT_STATE.FAIL.getResultState(), "返回的结果，传入参数错误");
		}
	}
	
	
	/**
	 * 404找不到
	 */
	public static ResultVo error404(Object ...object){
		if(object.length>=2){
			return new ResultVo(Constant.RESULT_STATE.ERROR_404.getResultState(), object[0],object[1]!=null?object[1].toString():null);
		}else if(object.length==1){
			return new ResultVo(Constant.RESULT_STATE.ERROR_404.getResultState(), object);
		}else{
			return new ResultVo(Constant.RESULT_STATE.FAIL.getResultState(), "返回的结果，传入参数错误");
		}
	}
	
	
	/**
	 * 时间过长,登录掉线
	 */
	public static ResultVo longTimeNotLogin(Object ...object){
		if(object.length>=2){
			return new ResultVo(Constant.RESULT_STATE.LOGN_TIME_NOT_LOGIN.getResultState(), object[0],object[1]!=null?object[1].toString():null);
		}else if(object.length==1){
			return new ResultVo(Constant.RESULT_STATE.LOGN_TIME_NOT_LOGIN.getResultState(), object);
		}else{
			return new ResultVo(Constant.RESULT_STATE.FAIL.getResultState(), "返回的结果，传入参数错误");
		}
	}
	
	/**
	 * 被踢出来了
	 */
	public static ResultVo anotherPlaceLogin(Object ...object){
		if(object.length>=2){
			return new ResultVo(Constant.RESULT_STATE.ANTHER_PLACE_LOGIN.getResultState(), object[0],object[1]!=null?object[1].toString():null);
		}else if(object.length==1){
			return new ResultVo(Constant.RESULT_STATE.ANTHER_PLACE_LOGIN.getResultState(), object);
		}else{
			return new ResultVo(Constant.RESULT_STATE.FAIL.getResultState(), "返回的结果，传入参数错误");
		}
	}
}
