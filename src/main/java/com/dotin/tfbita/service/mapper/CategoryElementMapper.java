package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Category;
import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.service.dto.CategoryDTO;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoryElement} and its DTO {@link CategoryElementDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryElementMapper extends EntityMapper<CategoryElementDTO, CategoryElement> {
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryId")
    @Mapping(target = "orderRegistrationInfos", source = "orderRegistrationInfos", qualifiedByName = "orderRegistrationInfoIdSet")
    CategoryElementDTO toDto(CategoryElement s);

    @Mapping(target = "orderRegistrationInfos", ignore = true)
    @Mapping(target = "removeOrderRegistrationInfo", ignore = true)
    CategoryElement toEntity(CategoryElementDTO categoryElementDTO);

    @Named("categoryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryDTO toDtoCategoryId(Category category);

    @Named("orderRegistrationInfoId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    OrderRegistrationInfoDTO toDtoOrderRegistrationInfoId(OrderRegistrationInfo orderRegistrationInfo);

    @Named("orderRegistrationInfoIdSet")
    default Set<OrderRegistrationInfoDTO> toDtoOrderRegistrationInfoIdSet(Set<OrderRegistrationInfo> orderRegistrationInfo) {
        return orderRegistrationInfo.stream().map(this::toDtoOrderRegistrationInfoId).collect(Collectors.toSet());
    }
}
