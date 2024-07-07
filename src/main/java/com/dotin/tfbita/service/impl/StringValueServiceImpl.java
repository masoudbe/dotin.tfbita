package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.StringValue;
import com.dotin.tfbita.repository.StringValueRepository;
import com.dotin.tfbita.service.StringValueService;
import com.dotin.tfbita.service.dto.StringValueDTO;
import com.dotin.tfbita.service.mapper.StringValueMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.StringValue}.
 */
@Service
@Transactional
public class StringValueServiceImpl implements StringValueService {

    private static final Logger log = LoggerFactory.getLogger(StringValueServiceImpl.class);

    private final StringValueRepository stringValueRepository;

    private final StringValueMapper stringValueMapper;

    public StringValueServiceImpl(StringValueRepository stringValueRepository, StringValueMapper stringValueMapper) {
        this.stringValueRepository = stringValueRepository;
        this.stringValueMapper = stringValueMapper;
    }

    @Override
    public StringValueDTO save(StringValueDTO stringValueDTO) {
        log.debug("Request to save StringValue : {}", stringValueDTO);
        StringValue stringValue = stringValueMapper.toEntity(stringValueDTO);
        stringValue = stringValueRepository.save(stringValue);
        return stringValueMapper.toDto(stringValue);
    }

    @Override
    public StringValueDTO update(StringValueDTO stringValueDTO) {
        log.debug("Request to update StringValue : {}", stringValueDTO);
        StringValue stringValue = stringValueMapper.toEntity(stringValueDTO);
        stringValue = stringValueRepository.save(stringValue);
        return stringValueMapper.toDto(stringValue);
    }

    @Override
    public Optional<StringValueDTO> partialUpdate(StringValueDTO stringValueDTO) {
        log.debug("Request to partially update StringValue : {}", stringValueDTO);

        return stringValueRepository
            .findById(stringValueDTO.getId())
            .map(existingStringValue -> {
                stringValueMapper.partialUpdate(existingStringValue, stringValueDTO);

                return existingStringValue;
            })
            .map(stringValueRepository::save)
            .map(stringValueMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StringValueDTO> findAll() {
        log.debug("Request to get all StringValues");
        return stringValueRepository.findAll().stream().map(stringValueMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StringValueDTO> findOne(Long id) {
        log.debug("Request to get StringValue : {}", id);
        return stringValueRepository.findById(id).map(stringValueMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete StringValue : {}", id);
        stringValueRepository.deleteById(id);
    }
}
