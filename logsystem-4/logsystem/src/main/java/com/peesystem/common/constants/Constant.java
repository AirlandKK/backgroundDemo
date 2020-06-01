package com.peesystem.common.constants;

public class Constant {
	
	

	
	/**
	 * 统一返回状态码
	 * 
	 * @author
	 *
	 */
	public static enum RESULT_STATE {
		/**
		 * 200-成功
		 */
		OK("200"),
		/**
		 * 500-异常
		 */
		EXCEPTION("500"),
		/**
		 * 400-失败
		 */
		FAIL("400");
	
		private String resultState;

		RESULT_STATE(String resultState) {
			this.resultState = resultState;
		}

		public String getResultState() {
			return resultState;
		}
	}

}
