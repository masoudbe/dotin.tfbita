package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.Draft;
import com.dotin.tfbita.domain.InsuranceCompanyInfo;
import com.dotin.tfbita.service.dto.DraftDTO;
import com.dotin.tfbita.service.dto.InsuranceCompanyInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link InsuranceCompanyInfo} and its DTO {@link InsuranceCompanyInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface InsuranceCompanyInfoMapper extends EntityMapper<InsuranceCompanyInfoDTO, InsuranceCompanyInfo> {
    @Mapping(target = "insuranceCompanyInfo", source = "insuranceCompanyInfo", qualifiedByName = "draftId")
    InsuranceCompanyInfoDTO toDto(InsuranceCompanyInfo s);

    @Named("draftId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    DraftDTO toDtoDraftId(Draft draft);
}
