package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.DraftAccountInfo;
import com.dotin.tfbita.service.dto.DraftAccountInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftAccountInfo} and its DTO {@link DraftAccountInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftAccountInfoMapper extends EntityMapper<DraftAccountInfoDTO, DraftAccountInfo> {}
