package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.DraftTypeTopicInfo;
import com.dotin.tfbita.service.dto.DraftTypeTopicInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftTypeTopicInfo} and its DTO {@link DraftTypeTopicInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftTypeTopicInfoMapper extends EntityMapper<DraftTypeTopicInfoDTO, DraftTypeTopicInfo> {}
