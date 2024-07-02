package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.LicenceInfo;
import com.dotin.tfbita.domain.OrderRegServ;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.domain.Product;
import com.dotin.tfbita.domain.ServiceTariff;
import com.dotin.tfbita.service.dto.LicenceInfoDTO;
import com.dotin.tfbita.service.dto.OrderRegServDTO;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import com.dotin.tfbita.service.dto.ProductDTO;
import com.dotin.tfbita.service.dto.ServiceTariffDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LicenceInfo} and its DTO {@link LicenceInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface LicenceInfoMapper extends EntityMapper<LicenceInfoDTO, LicenceInfo> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productId")
    @Mapping(target = "service", source = "service", qualifiedByName = "serviceTariffId")
    @Mapping(target = "orderRegistrationInfo", source = "orderRegistrationInfo", qualifiedByName = "orderRegistrationInfoId")
    @Mapping(target = "orderRegServ", source = "orderRegServ", qualifiedByName = "orderRegServId")
    LicenceInfoDTO toDto(LicenceInfo s);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("serviceTariffId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ServiceTariffDTO toDtoServiceTariffId(ServiceTariff serviceTariff);

    @Named("orderRegistrationInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegistrationInfoDTO toDtoOrderRegistrationInfoId(OrderRegistrationInfo orderRegistrationInfo);

    @Named("orderRegServId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegServDTO toDtoOrderRegServId(OrderRegServ orderRegServ);
}
