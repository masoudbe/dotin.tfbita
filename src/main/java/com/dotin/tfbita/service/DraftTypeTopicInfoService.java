package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.DraftTypeTopicInfo;
import com.dotin.tfbita.repository.DraftTypeTopicInfoRepository;
import com.dotin.tfbita.service.dto.DraftTypeTopicInfoDTO;
import com.dotin.tfbita.service.mapper.DraftTypeTopicInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftTypeTopicInfo}.
 */
@Service
@Transactional
public class DraftTypeTopicInfoService {

    private final Logger log = LoggerFactory.getLogger(DraftTypeTopicInfoService.class);

    private final DraftTypeTopicInfoRepository draftTypeTopicInfoRepository;

    private final DraftTypeTopicInfoMapper draftTypeTopicInfoMapper;

    public DraftTypeTopicInfoService(
        DraftTypeTopicInfoRepository draftTypeTopicInfoRepository,
        DraftTypeTopicInfoMapper draftTypeTopicInfoMapper
    ) {
        this.draftTypeTopicInfoRepository = draftTypeTopicInfoRepository;
        this.draftTypeTopicInfoMapper = draftTypeTopicInfoMapper;
    }

    /**
     * Save a draftTypeTopicInfo.
     *
     * @param draftTypeTopicInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftTypeTopicInfoDTO save(DraftTypeTopicInfoDTO draftTypeTopicInfoDTO) {
        log.debug("Request to save DraftTypeTopicInfo : {}", draftTypeTopicInfoDTO);
        DraftTypeTopicInfo draftTypeTopicInfo = draftTypeTopicInfoMapper.toEntity(draftTypeTopicInfoDTO);
        draftTypeTopicInfo = draftTypeTopicInfoRepository.save(draftTypeTopicInfo);
        return draftTypeTopicInfoMapper.toDto(draftTypeTopicInfo);
    }

    /**
     * Update a draftTypeTopicInfo.
     *
     * @param draftTypeTopicInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftTypeTopicInfoDTO update(DraftTypeTopicInfoDTO draftTypeTopicInfoDTO) {
        log.debug("Request to update DraftTypeTopicInfo : {}", draftTypeTopicInfoDTO);
        DraftTypeTopicInfo draftTypeTopicInfo = draftTypeTopicInfoMapper.toEntity(draftTypeTopicInfoDTO);
        draftTypeTopicInfo = draftTypeTopicInfoRepository.save(draftTypeTopicInfo);
        return draftTypeTopicInfoMapper.toDto(draftTypeTopicInfo);
    }

    /**
     * Partially update a draftTypeTopicInfo.
     *
     * @param draftTypeTopicInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DraftTypeTopicInfoDTO> partialUpdate(DraftTypeTopicInfoDTO draftTypeTopicInfoDTO) {
        log.debug("Request to partially update DraftTypeTopicInfo : {}", draftTypeTopicInfoDTO);

        return draftTypeTopicInfoRepository
            .findById(draftTypeTopicInfoDTO.getId())
            .map(existingDraftTypeTopicInfo -> {
                draftTypeTopicInfoMapper.partialUpdate(existingDraftTypeTopicInfo, draftTypeTopicInfoDTO);

                return existingDraftTypeTopicInfo;
            })
            .map(draftTypeTopicInfoRepository::save)
            .map(draftTypeTopicInfoMapper::toDto);
    }

    /**
     * Get all the draftTypeTopicInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DraftTypeTopicInfoDTO> findAll() {
        log.debug("Request to get all DraftTypeTopicInfos");
        return draftTypeTopicInfoRepository
            .findAll()
            .stream()
            .map(draftTypeTopicInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one draftTypeTopicInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DraftTypeTopicInfoDTO> findOne(Long id) {
        log.debug("Request to get DraftTypeTopicInfo : {}", id);
        return draftTypeTopicInfoRepository.findById(id).map(draftTypeTopicInfoMapper::toDto);
    }

    /**
     * Delete the draftTypeTopicInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DraftTypeTopicInfo : {}", id);
        draftTypeTopicInfoRepository.deleteById(id);
    }
}
