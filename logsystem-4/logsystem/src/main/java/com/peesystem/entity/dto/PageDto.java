package com.peesystem.entity.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 分页参数
 * @author 
 *
 */
public class PageDto<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final Integer default_page_size = 10;
	
	/**
	 * 页码，从1�?�?
	 */
	private Integer pageNum = 1;
	
	/**
	 * 每页条数
	 */
	private Integer pageSize = 10;
	
	/**
	 * 排序方式，例如：�? �?量（或价格�?�评论数等）排序
	 * 枚举值，由具体业务决�?
	 * 后台使用时，必须对其校验，避免sql注入
	 */
	private String sort;
	
	/**
	 * 总页�?
	 */
	private Long totalPages;
	
	/**
	 * 总数
	 */
	private Long total;
	
	/**
	 * 分页结果
	 */
	private List<T> result;

	public PageDto() {
		
	}
	
	public PageDto(List<T> result, Long total) {
		super();
		this.total = total;
		this.result = result;
	}





	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public List<T> getResult() {
		return result;
	}

	public void setResult(List<T> result) {
		this.result = result;
	}

	public Long getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(Long totalPages) {
		this.totalPages = totalPages;
	}

	

	
	
	
}
