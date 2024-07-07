package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Custom;
import com.dotin.tfbita.service.dto.CustomDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Custom} and its DTO {@link CustomDTO}.
 */
@Mapper(componentModel = "spring")
public interface CustomMapper extends EntityMapper<CustomDTO, Custom> {}
