package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.ObjectiveCategory;
import com.dotin.tfbita.domain.ObjectiveCategoryElement;
import com.dotin.tfbita.service.dto.ObjectiveCategoryDTO;
import com.dotin.tfbita.service.dto.ObjectiveCategoryElementDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ObjectiveCategoryElement} and its DTO {@link ObjectiveCategoryElementDTO}.
 */
@Mapper(componentModel = "spring")
public interface ObjectiveCategoryElementMapper extends EntityMapper<ObjectiveCategoryElementDTO, ObjectiveCategoryElement> {
    @Mapping(target = "objectiveCategory", source = "objectiveCategory", qualifiedByName = "objectiveCategoryId")
    ObjectiveCategoryElementDTO toDto(ObjectiveCategoryElement s);

    @Named("objectiveCategoryId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ObjectiveCategoryDTO toDtoObjectiveCategoryId(ObjectiveCategory objectiveCategory);
}
