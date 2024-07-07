package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Category;
import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.service.dto.CategoryDTO;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link CategoryElement} and its DTO {@link CategoryElementDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryElementMapper extends EntityMapper<CategoryElementDTO, CategoryElement> {
    @Mapping(target = "category", source = "category", qualifiedByName = "categoryId")
    CategoryElementDTO toDto(CategoryElement s);

    @Named("categoryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    CategoryDTO toDtoCategoryId(Category category);
}
