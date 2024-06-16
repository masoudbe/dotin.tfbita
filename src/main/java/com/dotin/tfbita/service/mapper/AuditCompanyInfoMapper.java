package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.AuditCompanyInfo;
import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.service.dto.AuditCompanyInfoDTO;
import com.dotin.tfbita.service.dto.DraftDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AuditCompanyInfo} and its DTO {@link AuditCompanyInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface AuditCompanyInfoMapper extends EntityMapper<AuditCompanyInfoDTO, AuditCompanyInfo> {
    @Mapping(target = "auditCompanyInfo", source = "auditCompanyInfo", qualifiedByName = "draftId")
    AuditCompanyInfoDTO toDto(AuditCompanyInfo s);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);
}
