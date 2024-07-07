package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.AuditCompanyInfo;
import com.dotin.tfbita.service.dto.AuditCompanyInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link AuditCompanyInfo} and its DTO {@link AuditCompanyInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface AuditCompanyInfoMapper extends EntityMapper<AuditCompanyInfoDTO, AuditCompanyInfo> {}
