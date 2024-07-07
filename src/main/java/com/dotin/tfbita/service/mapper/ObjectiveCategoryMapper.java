package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.ObjectiveCategory;
import com.dotin.tfbita.service.dto.ObjectiveCategoryDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ObjectiveCategory} and its DTO {@link ObjectiveCategoryDTO}.
 */
@Mapper(componentModel = "spring")
public interface ObjectiveCategoryMapper extends EntityMapper<ObjectiveCategoryDTO, ObjectiveCategory> {}
