package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.TransportationType;
import com.dotin.tfbita.service.dto.TransportationTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TransportationType} and its DTO {@link TransportationTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface TransportationTypeMapper extends EntityMapper<TransportationTypeDTO, TransportationType> {}
