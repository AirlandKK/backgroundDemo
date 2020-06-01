package com.peesystem.service;

import com.peesystem.entity.dto.PageDto;
import com.peesystem.entity.pojo.ExUserLog;

public interface LogService {

	PageDto<ExUserLog> getExUserLogList(ExUserLog exUserLog, PageDto<ExUserLog> cpsPageDto);

	ExUserLog findLogById(ExUserLog exUserLog);

	int deleteLog(ExUserLog exUserLog);

	int saveLog(ExUserLog exUserLog);

}
