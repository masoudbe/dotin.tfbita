package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.AdditionalBrokerInformation;
import com.dotin.tfbita.service.dto.AdditionalBrokerInformationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdditionalBrokerInformation} and its DTO {@link AdditionalBrokerInformationDTO}.
 */
@Mapper(componentModel = "spring")
public interface AdditionalBrokerInformationMapper extends EntityMapper<AdditionalBrokerInformationDTO, AdditionalBrokerInformation> {}
