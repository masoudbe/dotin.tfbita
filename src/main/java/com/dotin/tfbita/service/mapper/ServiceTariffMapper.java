package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.ServiceTariff;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.ServiceTariffDTO;
import java.util.Set;
import java.util.stream.Collectors;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ServiceTariff} and its DTO {@link ServiceTariffDTO}.
 */
@Mapper(componentModel = "spring")
public interface ServiceTariffMapper extends EntityMapper<ServiceTariffDTO, ServiceTariff> {
    @Mapping(target = "drafts", source = "drafts", qualifiedByName = "draftIdSet")
    ServiceTariffDTO toDto(ServiceTariff s);

    @Mapping(target = "removeDraft", ignore = true)
    ServiceTariff toEntity(ServiceTariffDTO serviceTariffDTO);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);

    @Named("draftIdSet")
    default Set<DraftDTO> toDtoDraftIdSet(Set<Draft> draft) {
        return draft.stream().map(this::toDtoDraftId).collect(Collectors.toSet());
    }
}
