package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.DraftCertificateType;
import com.dotin.tfbita.domain.DraftRequestType;
import com.dotin.tfbita.service.dto.DraftCertificateTypeDTO;
import com.dotin.tfbita.service.dto.DraftRequestTypeDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link DraftCertificateType} and its DTO {@link DraftCertificateTypeDTO}.
 */
@Mapper(componentModel = "spring")
public interface DraftCertificateTypeMapper extends EntityMapper<DraftCertificateTypeDTO, DraftCertificateType> {
    @Mapping(target = "draftRequestType", source = "draftRequestType", qualifiedByName = "draftRequestTypeId")
    DraftCertificateTypeDTO toDto(DraftCertificateType s);

    @Named("draftRequestTypeId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftRequestTypeDTO toDtoDraftRequestTypeId(DraftRequestType draftRequestType);
}
