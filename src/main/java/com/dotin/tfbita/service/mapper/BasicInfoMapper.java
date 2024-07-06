package com.dotin.tfbita.service.mapper;

import com.dotin.tfbita.domain.BasicInfo;
import com.dotin.tfbita.service.dto.BasicInfoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link BasicInfo} and its DTO {@link BasicInfoDTO}.
 */
@Mapper(componentModel = "spring")
public interface BasicInfoMapper extends EntityMapper<BasicInfoDTO, BasicInfo> {}
