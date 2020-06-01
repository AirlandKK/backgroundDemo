package com.peesystem.service;

import java.util.List;

import com.peesystem.entity.dto.PageDto;
import com.peesystem.entity.pojo.ExUserLogComment;

public interface CommentService {

	PageDto<ExUserLogComment> getCommentList(ExUserLogComment exUserLogComment, PageDto<ExUserLogComment> cpsPageDto);

	int saveComment(ExUserLogComment exUserLogComment);

	int deleteComment(ExUserLogComment exUserLogComment);

	List<ExUserLogComment> getAllCommentByLogId(ExUserLogComment exUserLogComment);

}
