package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.DraftFactor;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.DraftFactorDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftFactor} and its DTO {@link DraftFactorDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftFactorMapper extends EntityMapper<DraftFactorDTO, DraftFactor> {
    @Mapping(target = "draftFactors", source = "draftFactors", qualifiedByName = "draftId")
    DraftFactorDTO toDto(DraftFactor s);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);
}
