package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.DraftAccountInfo;
import com.dotin.tfbita.repository.DraftAccountInfoRepository;
import com.dotin.tfbita.service.dto.DraftAccountInfoDTO;
import com.dotin.tfbita.service.mapper.DraftAccountInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftAccountInfo}.
 */
@Service
@Transactional
public class DraftAccountInfoService {

    private final Logger log = LoggerFactory.getLogger(DraftAccountInfoService.class);

    private final DraftAccountInfoRepository draftAccountInfoRepository;

    private final DraftAccountInfoMapper draftAccountInfoMapper;

    public DraftAccountInfoService(DraftAccountInfoRepository draftAccountInfoRepository, DraftAccountInfoMapper draftAccountInfoMapper) {
        this.draftAccountInfoRepository = draftAccountInfoRepository;
        this.draftAccountInfoMapper = draftAccountInfoMapper;
    }

    /**
     * Save a draftAccountInfo.
     *
     * @param draftAccountInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftAccountInfoDTO save(DraftAccountInfoDTO draftAccountInfoDTO) {
        log.debug("Request to save DraftAccountInfo : {}", draftAccountInfoDTO);
        DraftAccountInfo draftAccountInfo = draftAccountInfoMapper.toEntity(draftAccountInfoDTO);
        draftAccountInfo = draftAccountInfoRepository.save(draftAccountInfo);
        return draftAccountInfoMapper.toDto(draftAccountInfo);
    }

    /**
     * Update a draftAccountInfo.
     *
     * @param draftAccountInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftAccountInfoDTO update(DraftAccountInfoDTO draftAccountInfoDTO) {
        log.debug("Request to update DraftAccountInfo : {}", draftAccountInfoDTO);
        DraftAccountInfo draftAccountInfo = draftAccountInfoMapper.toEntity(draftAccountInfoDTO);
        draftAccountInfo = draftAccountInfoRepository.save(draftAccountInfo);
        return draftAccountInfoMapper.toDto(draftAccountInfo);
    }

    /**
     * Partially update a draftAccountInfo.
     *
     * @param draftAccountInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DraftAccountInfoDTO> partialUpdate(DraftAccountInfoDTO draftAccountInfoDTO) {
        log.debug("Request to partially update DraftAccountInfo : {}", draftAccountInfoDTO);

        return draftAccountInfoRepository
            .findById(draftAccountInfoDTO.getId())
            .map(existingDraftAccountInfo -> {
                draftAccountInfoMapper.partialUpdate(existingDraftAccountInfo, draftAccountInfoDTO);

                return existingDraftAccountInfo;
            })
            .map(draftAccountInfoRepository::save)
            .map(draftAccountInfoMapper::toDto);
    }

    /**
     * Get all the draftAccountInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DraftAccountInfoDTO> findAll() {
        log.debug("Request to get all DraftAccountInfos");
        return draftAccountInfoRepository
            .findAll()
            .stream()
            .map(draftAccountInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one draftAccountInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DraftAccountInfoDTO> findOne(Long id) {
        log.debug("Request to get DraftAccountInfo : {}", id);
        return draftAccountInfoRepository.findById(id).map(draftAccountInfoMapper::toDto);
    }

    /**
     * Delete the draftAccountInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DraftAccountInfo : {}", id);
        draftAccountInfoRepository.deleteById(id);
    }
}
