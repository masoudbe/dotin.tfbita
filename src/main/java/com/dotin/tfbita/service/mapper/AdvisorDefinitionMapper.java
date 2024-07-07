package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.AdditionalBrokerInformation;
import com.dotin.tfbita.domain.AdvisorDefinition;
import com.dotin.tfbita.domain.AdvisorDefinitionDeposit;
import com.dotin.tfbita.domain.SwiftBic;
import com.dotin.tfbita.domain.TransferMethodManagement;
import com.dotin.tfbita.service.dto.AdditionalBrokerInformationDTO;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDTO;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDepositDTO;
import com.dotin.tfbita.service.dto.SwiftBicDTO;
import com.dotin.tfbita.service.dto.TransferMethodManagementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AdvisorDefinition} and its DTO {@link AdvisorDefinitionDTO}.
 */
@Mapper(componentModel = "spring")
public interface AdvisorDefinitionMapper extends EntityMapper<AdvisorDefinitionDTO, AdvisorDefinition> {
    @Mapping(
        target = "additionalBrokerInformation",
        source = "additionalBrokerInformation",
        qualifiedByName = "additionalBrokerInformationId"
    )
    @Mapping(target = "defaultVostroDeposit", source = "defaultVostroDeposit", qualifiedByName = "advisorDefinitionDepositId")
    @Mapping(target = "defaultNostroDeposit", source = "defaultNostroDeposit", qualifiedByName = "advisorDefinitionDepositId")
    @Mapping(target = "receiveMethod", source = "receiveMethod", qualifiedByName = "transferMethodManagementId")
    @Mapping(target = "payMethod", source = "payMethod", qualifiedByName = "transferMethodManagementId")
    @Mapping(target = "swiftBic", source = "swiftBic", qualifiedByName = "swiftBicId")
    AdvisorDefinitionDTO toDto(AdvisorDefinition s);

    @Named("additionalBrokerInformationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AdditionalBrokerInformationDTO toDtoAdditionalBrokerInformationId(AdditionalBrokerInformation additionalBrokerInformation);

    @Named("advisorDefinitionDepositId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AdvisorDefinitionDepositDTO toDtoAdvisorDefinitionDepositId(AdvisorDefinitionDeposit advisorDefinitionDeposit);

    @Named("transferMethodManagementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TransferMethodManagementDTO toDtoTransferMethodManagementId(TransferMethodManagement transferMethodManagement);

    @Named("swiftBicId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SwiftBicDTO toDtoSwiftBicId(SwiftBic swiftBic);
}
