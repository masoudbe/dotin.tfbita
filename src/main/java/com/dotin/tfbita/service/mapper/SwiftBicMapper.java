package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.SwiftBic;
import com.dotin.tfbita.service.dto.SwiftBicDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link SwiftBic} and its DTO {@link SwiftBicDTO}.
 */
@Mapper(componentModel = "spring")
public interface SwiftBicMapper extends EntityMapper<SwiftBicDTO, SwiftBic> {}
