package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.LicenceInfo;
import com.dotin.tfbita.domain.OrderRegServ;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.domain.Product;
import com.dotin.tfbita.service.dto.LicenceInfoDTO;
import com.dotin.tfbita.service.dto.OrderRegServDTO;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import com.dotin.tfbita.service.dto.ProductDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link LicenceInfo} and its DTO {@link LicenceInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface LicenceInfoMapper extends EntityMapper<LicenceInfoDTO, LicenceInfo> {
    @Mapping(target = "product", source = "product", qualifiedByName = "productId")
    @Mapping(target = "orderRegServ", source = "orderRegServ", qualifiedByName = "orderRegServId")
    @Mapping(target = "orderRegistrationInfo", source = "orderRegistrationInfo", qualifiedByName = "orderRegistrationInfoId")
    LicenceInfoDTO toDto(LicenceInfo s);

    @Named("productId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ProductDTO toDtoProductId(Product product);

    @Named("orderRegServId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegServDTO toDtoOrderRegServId(OrderRegServ orderRegServ);

    @Named("orderRegistrationInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegistrationInfoDTO toDtoOrderRegistrationInfoId(OrderRegistrationInfo orderRegistrationInfo);
}
