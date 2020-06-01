package com.peesystem.service;

import com.peesystem.entity.dto.PageDto;
import com.peesystem.entity.pojo.ExUserCollect;

public interface CollectService {

	PageDto<ExUserCollect> getCollectList(ExUserCollect exUserCollect, PageDto<ExUserCollect> cpsPageDto);

	int deleteCollect(ExUserCollect exUserCollect);

	int saveCollect(ExUserCollect exUserCollect);


}
