package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.DraftTypeAccountInfo;
import com.dotin.tfbita.service.dto.DraftTypeAccountInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftTypeAccountInfo} and its DTO {@link DraftTypeAccountInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftTypeAccountInfoMapper extends EntityMapper<DraftTypeAccountInfoDTO, DraftTypeAccountInfo> {}
