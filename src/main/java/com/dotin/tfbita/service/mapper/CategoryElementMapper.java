package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.AdvisorDefinition;
import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftReceipt;
import com.dotin.tfbita.domain.DraftStatusInfo;
import com.dotin.tfbita.domain.DraftType;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.domain.PurchaseFromOtherResources;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDTO;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftReceiptDTO;
import com.dotin.tfbita.service.dto.DraftStatusInfoDTO;
import com.dotin.tfbita.service.dto.DraftTypeDTO;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import com.dotin.tfbita.service.dto.PurchaseFromOtherResourcesDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoryElement} and its DTO {@link CategoryElementDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryElementMapper extends EntityMapper<CategoryElementDTO, CategoryElement> {
    @Mapping(target = "orderRegType", source = "orderRegType", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "requestType", source = "requestType", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "importType", source = "importType", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "operationTyp", source = "operationTyp", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "currencyProvisionType", source = "currencyProvisionType", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "paymentTool", source = "paymentTool", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "activityType", source = "activityType", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "ownerType", source = "ownerType", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "status", source = "status", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "externalCustomerType", source = "externalCustomerType", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "transportType", source = "transportType", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "currencySupplier", source = "currencySupplier", qualifiedByName = "purchaseFromOtherResourcesId")
    @Mapping(target = "statusPurchase", source = "statusPurchase", qualifiedByName = "purchaseFromOtherResourcesId")
    @Mapping(target = "transportVehicleType", source = "transportVehicleType", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "freightLetterType", source = "freightLetterType", qualifiedByName = "draftId")
    @Mapping(target = "actionCode", source = "actionCode", qualifiedByName = "draftId")
    @Mapping(target = "ownershipCode", source = "ownershipCode", qualifiedByName = "draftId")
    @Mapping(target = "currencyContainerPlace", source = "currencyContainerPlace", qualifiedByName = "draftId")
    @Mapping(target = "draftSource", source = "draftSource", qualifiedByName = "draftId")
    @Mapping(target = "chargedExchangeBroker", source = "chargedExchangeBroker", qualifiedByName = "draftId")
    @Mapping(target = "impartType", source = "impartType", qualifiedByName = "draftId")
    @Mapping(target = "insuranceLetterType", source = "insuranceLetterType", qualifiedByName = "draftId")
    @Mapping(target = "advisorDepositType", source = "advisorDepositType", qualifiedByName = "draftId")
    @Mapping(target = "interfaceAdvisorDepositType", source = "interfaceAdvisorDepositType", qualifiedByName = "draftId")
    @Mapping(target = "paymentType", source = "paymentType", qualifiedByName = "draftId")
    @Mapping(target = "dealType", source = "dealType", qualifiedByName = "draftId")
    @Mapping(target = "coveringAdvisorDepositType", source = "coveringAdvisorDepositType", qualifiedByName = "draftId")
    @Mapping(target = "depositType", source = "depositType", qualifiedByName = "advisorDefinitionId")
    @Mapping(target = "type", source = "type", qualifiedByName = "draftTypeId")
    @Mapping(target = "secondaryType", source = "secondaryType", qualifiedByName = "draftTypeId")
    @Mapping(target = "division", source = "division", qualifiedByName = "draftTypeId")
    @Mapping(target = "productDimension", source = "productDimension", qualifiedByName = "draftReceiptId")
    @Mapping(target = "stateOfDocuments", source = "stateOfDocuments", qualifiedByName = "draftReceiptId")
    @Mapping(target = "currencyProvisionFileType", source = "currencyProvisionFileType", qualifiedByName = "draftReceiptId")
    @Mapping(target = "statusDraft", source = "statusDraft", qualifiedByName = "draftStatusInfoId")
    CategoryElementDTO toDto(CategoryElement s);

    @Named("orderRegistrationInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegistrationInfoDTO toDtoOrderRegistrationInfoId(OrderRegistrationInfo orderRegistrationInfo);

    @Named("purchaseFromOtherResourcesId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    PurchaseFromOtherResourcesDTO toDtoPurchaseFromOtherResourcesId(PurchaseFromOtherResources purchaseFromOtherResources);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);

    @Named("advisorDefinitionId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    AdvisorDefinitionDTO toDtoAdvisorDefinitionId(AdvisorDefinition advisorDefinition);

    @Named("draftTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftTypeDTO toDtoDraftTypeId(DraftType draftType);

    @Named("draftReceiptId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftReceiptDTO toDtoDraftReceiptId(DraftReceipt draftReceipt);

    @Named("draftStatusInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftStatusInfoDTO toDtoDraftStatusInfoId(DraftStatusInfo draftStatusInfo);
}
