package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Category;
import com.dotin.tfbita.service.dto.CategoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Category} and its DTO {@link CategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {}
