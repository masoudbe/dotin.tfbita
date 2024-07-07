package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.InsuranceCompanyInfo;
import com.dotin.tfbita.service.dto.InsuranceCompanyInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceCompanyInfo} and its DTO {@link InsuranceCompanyInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface InsuranceCompanyInfoMapper extends EntityMapper<InsuranceCompanyInfoDTO, InsuranceCompanyInfo> {}
