package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.DraftRequestType;
import com.dotin.tfbita.service.dto.DraftRequestTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftRequestType} and its DTO {@link DraftRequestTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftRequestTypeMapper extends EntityMapper<DraftRequestTypeDTO, DraftRequestType> {}
