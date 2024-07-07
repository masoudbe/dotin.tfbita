package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.DraftStatusInfo;
import com.dotin.tfbita.repository.DraftStatusInfoRepository;
import com.dotin.tfbita.service.dto.DraftStatusInfoDTO;
import com.dotin.tfbita.service.mapper.DraftStatusInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.DraftStatusInfo}.
 */
@Service
@Transactional
public class DraftStatusInfoService {

    private final Logger log = LoggerFactory.getLogger(DraftStatusInfoService.class);

    private final DraftStatusInfoRepository draftStatusInfoRepository;

    private final DraftStatusInfoMapper draftStatusInfoMapper;

    public DraftStatusInfoService(DraftStatusInfoRepository draftStatusInfoRepository, DraftStatusInfoMapper draftStatusInfoMapper) {
        this.draftStatusInfoRepository = draftStatusInfoRepository;
        this.draftStatusInfoMapper = draftStatusInfoMapper;
    }

    /**
     * Save a draftStatusInfo.
     *
     * @param draftStatusInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftStatusInfoDTO save(DraftStatusInfoDTO draftStatusInfoDTO) {
        log.debug("Request to save DraftStatusInfo : {}", draftStatusInfoDTO);
        DraftStatusInfo draftStatusInfo = draftStatusInfoMapper.toEntity(draftStatusInfoDTO);
        draftStatusInfo = draftStatusInfoRepository.save(draftStatusInfo);
        return draftStatusInfoMapper.toDto(draftStatusInfo);
    }

    /**
     * Update a draftStatusInfo.
     *
     * @param draftStatusInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public DraftStatusInfoDTO update(DraftStatusInfoDTO draftStatusInfoDTO) {
        log.debug("Request to update DraftStatusInfo : {}", draftStatusInfoDTO);
        DraftStatusInfo draftStatusInfo = draftStatusInfoMapper.toEntity(draftStatusInfoDTO);
        draftStatusInfo = draftStatusInfoRepository.save(draftStatusInfo);
        return draftStatusInfoMapper.toDto(draftStatusInfo);
    }

    /**
     * Partially update a draftStatusInfo.
     *
     * @param draftStatusInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<DraftStatusInfoDTO> partialUpdate(DraftStatusInfoDTO draftStatusInfoDTO) {
        log.debug("Request to partially update DraftStatusInfo : {}", draftStatusInfoDTO);

        return draftStatusInfoRepository
            .findById(draftStatusInfoDTO.getId())
            .map(existingDraftStatusInfo -> {
                draftStatusInfoMapper.partialUpdate(existingDraftStatusInfo, draftStatusInfoDTO);

                return existingDraftStatusInfo;
            })
            .map(draftStatusInfoRepository::save)
            .map(draftStatusInfoMapper::toDto);
    }

    /**
     * Get all the draftStatusInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<DraftStatusInfoDTO> findAll() {
        log.debug("Request to get all DraftStatusInfos");
        return draftStatusInfoRepository
            .findAll()
            .stream()
            .map(draftStatusInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one draftStatusInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<DraftStatusInfoDTO> findOne(Long id) {
        log.debug("Request to get DraftStatusInfo : {}", id);
        return draftStatusInfoRepository.findById(id).map(draftStatusInfoMapper::toDto);
    }

    /**
     * Delete the draftStatusInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete DraftStatusInfo : {}", id);
        draftStatusInfoRepository.deleteById(id);
    }
}
