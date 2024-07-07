package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.AdvisorDefinition;
import com.dotin.tfbita.domain.AuditCompanyInfo;
import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.domain.CurrencyExchangeInfo;
import com.dotin.tfbita.domain.Custom;
import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftAccountInfo;
import com.dotin.tfbita.domain.DraftDocumentTransactionContainer;
import com.dotin.tfbita.domain.DraftStatusInfo;
import com.dotin.tfbita.domain.DraftType;
import com.dotin.tfbita.domain.InsuranceCompanyInfo;
import com.dotin.tfbita.domain.LongValue;
import com.dotin.tfbita.domain.Product;
import com.dotin.tfbita.domain.ServiceTariff;
import com.dotin.tfbita.domain.StringValue;
import com.dotin.tfbita.domain.SuggestedSanctionInfo;
import com.dotin.tfbita.domain.TransportationType;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDTO;
import com.dotin.tfbita.service.dto.AuditCompanyInfoDTO;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.dto.CurrencyExchangeInfoDTO;
import com.dotin.tfbita.service.dto.CustomDTO;
import com.dotin.tfbita.service.dto.DraftAccountInfoDTO;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftDocumentTransactionContainerDTO;
import com.dotin.tfbita.service.dto.DraftStatusInfoDTO;
import com.dotin.tfbita.service.dto.DraftTypeDTO;
import com.dotin.tfbita.service.dto.InsuranceCompanyInfoDTO;
import com.dotin.tfbita.service.dto.LongValueDTO;
import com.dotin.tfbita.service.dto.ProductDTO;
import com.dotin.tfbita.service.dto.ServiceTariffDTO;
import com.dotin.tfbita.service.dto.StringValueDTO;
import com.dotin.tfbita.service.dto.SuggestedSanctionInfoDTO;
import com.dotin.tfbita.service.dto.TransportationTypeDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Draft} and its DTO {@link DraftDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftMapper extends EntityMapper<DraftDTO, Draft> {
    @Mapping(target = "chargedExchangeBroker", source = "chargedExchangeBroker", qualifiedByName = "categoryElementId")
    @Mapping(target = "insuranceLetterType", source = "insuranceLetterType", qualifiedByName = "categoryElementId")
    @Mapping(target = "advisorDepositType", source = "advisorDepositType", qualifiedByName = "categoryElementId")
    @Mapping(target = "interfaceAdvisorDepositType", source = "interfaceAdvisorDepositType", qualifiedByName = "categoryElementId")
    @Mapping(target = "coveringAdvisorDepositType", source = "coveringAdvisorDepositType", qualifiedByName = "categoryElementId")
    @Mapping(target = "impartType", source = "impartType", qualifiedByName = "categoryElementId")
    @Mapping(target = "dealType", source = "dealType", qualifiedByName = "categoryElementId")
    @Mapping(target = "transportVehicleType", source = "transportVehicleType", qualifiedByName = "categoryElementId")
    @Mapping(target = "freightLetterType", source = "freightLetterType", qualifiedByName = "categoryElementId")
    @Mapping(target = "actionCode", source = "actionCode", qualifiedByName = "categoryElementId")
    @Mapping(target = "ownershipCode", source = "ownershipCode", qualifiedByName = "categoryElementId")
    @Mapping(target = "currencyContainerPlace", source = "currencyContainerPlace", qualifiedByName = "categoryElementId")
    @Mapping(target = "paymentType", source = "paymentType", qualifiedByName = "categoryElementId")
    @Mapping(target = "draftSource", source = "draftSource", qualifiedByName = "categoryElementId")
    @Mapping(target = "loadSwitchPlace", source = "loadSwitchPlace", qualifiedByName = "customId")
    @Mapping(target = "draftType", source = "draftType", qualifiedByName = "draftTypeId")
    @Mapping(target = "statusInfo", source = "statusInfo", qualifiedByName = "draftStatusInfoId")
    @Mapping(target = "insuranceCompanyInfo", source = "insuranceCompanyInfo", qualifiedByName = "insuranceCompanyInfoId")
    @Mapping(target = "advisingBank", source = "advisingBank", qualifiedByName = "advisorDefinitionId")
    @Mapping(target = "interfaceAdvisingBank", source = "interfaceAdvisingBank", qualifiedByName = "advisorDefinitionId")
    @Mapping(target = "coveringBank", source = "coveringBank", qualifiedByName = "advisorDefinitionId")
    @Mapping(target = "auditCompanyInfo", source = "auditCompanyInfo", qualifiedByName = "auditCompanyInfoId")
    @Mapping(target = "transportType", source = "transportType", qualifiedByName = "transportationTypeId")
    @Mapping(target = "currencyExchangeInfo", source = "currencyExchangeInfo", qualifiedByName = "currencyExchangeInfoId")
    @Mapping(target = "accountInfo", source = "accountInfo", qualifiedByName = "draftAccountInfoId")
    @Mapping(target = "destinationCustomCompanies", source = "destinationCustomCompanies", qualifiedByName = "customId")
    @Mapping(target = "sourceCustomCompanies", source = "sourceCustomCompanies", qualifiedByName = "customId")
    @Mapping(target = "services", source = "services", qualifiedByName = "serviceTariffIdSet")
    @Mapping(target = "products", source = "products", qualifiedByName = "productIdSet")
    @Mapping(target = "sanctionSerials", source = "sanctionSerials", qualifiedByName = "stringValueIdSet")
    @Mapping(target = "customerNumbers", source = "customerNumbers", qualifiedByName = "longValueIdSet")
    @Mapping(target = "suggestedSanctions", source = "suggestedSanctions", qualifiedByName = "suggestedSanctionInfoIdSet")
    @Mapping(
        target = "documentTransactionContainers",
        source = "documentTransactionContainers",
        qualifiedByName = "draftDocumentTransactionContainerIdSet"
    )
    DraftDTO toDto(Draft s);

    @Mapping(target = "removeServices", ignore = true)
    @Mapping(target = "removeProducts", ignore = true)
    @Mapping(target = "removeSanctionSerials", ignore = true)
    @Mapping(target = "removeCustomerNumbers", ignore = true)
    @Mapping(target = "removeSuggestedSanctions", ignore = true)
    @Mapping(target = "removeDocumentTransactionContainer", ignore = true)
    Draft toEntity(DraftDTO draftDTO);

    @Named("categoryElementId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryElementDTO toDtoCategoryElementId(CategoryElement categoryElement);

    @Named("customId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CustomDTO toDtoCustomId(Custom custom);

    @Named("draftTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftTypeDTO toDtoDraftTypeId(DraftType draftType);

    @Named("draftStatusInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftStatusInfoDTO toDtoDraftStatusInfoId(DraftStatusInfo draftStatusInfo);

    @Named("insuranceCompanyInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    InsuranceCompanyInfoDTO toDtoInsuranceCompanyInfoId(InsuranceCompanyInfo insuranceCompanyInfo);

    @Named("advisorDefinitionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AdvisorDefinitionDTO toDtoAdvisorDefinitionId(AdvisorDefinition advisorDefinition);

    @Named("auditCompanyInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AuditCompanyInfoDTO toDtoAuditCompanyInfoId(AuditCompanyInfo auditCompanyInfo);

    @Named("transportationTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    TransportationTypeDTO toDtoTransportationTypeId(TransportationType transportationType);

    @Named("currencyExchangeInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CurrencyExchangeInfoDTO toDtoCurrencyExchangeInfoId(CurrencyExchangeInfo currencyExchangeInfo);

    @Named("draftAccountInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftAccountInfoDTO toDtoDraftAccountInfoId(DraftAccountInfo draftAccountInfo);

    @Named("serviceTariffId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ServiceTariffDTO toDtoServiceTariffId(ServiceTariff serviceTariff);

    @Named("serviceTariffIdSet")
    default Set<ServiceTariffDTO> toDtoServiceTariffIdSet(Set<ServiceTariff> serviceTariff) {
        return serviceTariff.stream().map(this::toDtoServiceTariffId).collect(Collectors.toSet());
    }

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("productIdSet")
    default Set<ProductDTO> toDtoProductIdSet(Set<Product> product) {
        return product.stream().map(this::toDtoProductId).collect(Collectors.toSet());
    }

    @Named("stringValueId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    StringValueDTO toDtoStringValueId(StringValue stringValue);

    @Named("stringValueIdSet")
    default Set<StringValueDTO> toDtoStringValueIdSet(Set<StringValue> stringValue) {
        return stringValue.stream().map(this::toDtoStringValueId).collect(Collectors.toSet());
    }

    @Named("longValueId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    LongValueDTO toDtoLongValueId(LongValue longValue);

    @Named("longValueIdSet")
    default Set<LongValueDTO> toDtoLongValueIdSet(Set<LongValue> longValue) {
        return longValue.stream().map(this::toDtoLongValueId).collect(Collectors.toSet());
    }

    @Named("suggestedSanctionInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    SuggestedSanctionInfoDTO toDtoSuggestedSanctionInfoId(SuggestedSanctionInfo suggestedSanctionInfo);

    @Named("suggestedSanctionInfoIdSet")
    default Set<SuggestedSanctionInfoDTO> toDtoSuggestedSanctionInfoIdSet(Set<SuggestedSanctionInfo> suggestedSanctionInfo) {
        return suggestedSanctionInfo.stream().map(this::toDtoSuggestedSanctionInfoId).collect(Collectors.toSet());
    }

    @Named("draftDocumentTransactionContainerId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDocumentTransactionContainerDTO toDtoDraftDocumentTransactionContainerId(
        DraftDocumentTransactionContainer draftDocumentTransactionContainer
    );

    @Named("draftDocumentTransactionContainerIdSet")
    default Set<DraftDocumentTransactionContainerDTO> toDtoDraftDocumentTransactionContainerIdSet(
        Set<DraftDocumentTransactionContainer> draftDocumentTransactionContainer
    ) {
        return draftDocumentTransactionContainer.stream().map(this::toDtoDraftDocumentTransactionContainerId).collect(Collectors.toSet());
    }
}
