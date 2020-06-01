package com.peesystem.entity.pojo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Table(name = "ex_user_log")
public class ExUserLog implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //支持回写的uuid
    private String id;

    /**
     * 日志发表时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private String userId;
    
    /**
     * 标题
     */
    private String  title;
    /**
     * 日志内容
     */
    private byte[] content;
    
    
    /**
     * 用户名
     */
    @Transient
    private String userName;
     
    /**
     * 具体的内容
     */
    @Transient
    private String contentString;
    
    @Transient
    private String contentParam;
    
    /**
     * 昵称
     */
    @Transient
    private String nick;
   
   
    private static final long serialVersionUID = 1L;

    /**
     * @return id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * 获取日志发表时间
     *
     * @return create_time - 日志发表时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置日志发表时间
     *
     * @param createTime 日志发表时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public String getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    /**
     * 获取日志内容
     *
     * @return content - 日志内容
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * 设置日志内容
     *
     * @param content 日志内容
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getContentString(){
		
    	String contentString = "";
    	if(this.content != null && this.content.length > 0){
    		String s = new String(this.content);
    		contentString=s;
    	}
    	return contentString;
    }
	
	public void setContentString(String contentString) {
		this.contentString = contentString;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public String getContentParam() {
		return contentParam;
	}

	public void setContentParam(String contentParam) {
		this.contentParam = contentParam;
	}

	@Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", createTime=").append(createTime);
        sb.append(", userId=").append(userId);
        sb.append(", content=").append(content);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}