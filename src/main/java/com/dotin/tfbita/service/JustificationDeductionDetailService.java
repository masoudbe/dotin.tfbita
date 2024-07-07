package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.JustificationDeductionDetail;
import com.dotin.tfbita.repository.JustificationDeductionDetailRepository;
import com.dotin.tfbita.service.dto.JustificationDeductionDetailDTO;
import com.dotin.tfbita.service.mapper.JustificationDeductionDetailMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.JustificationDeductionDetail}.
 */
@Service
@Transactional
public class JustificationDeductionDetailService {

    private final Logger log = LoggerFactory.getLogger(JustificationDeductionDetailService.class);

    private final JustificationDeductionDetailRepository justificationDeductionDetailRepository;

    private final JustificationDeductionDetailMapper justificationDeductionDetailMapper;

    public JustificationDeductionDetailService(
        JustificationDeductionDetailRepository justificationDeductionDetailRepository,
        JustificationDeductionDetailMapper justificationDeductionDetailMapper
    ) {
        this.justificationDeductionDetailRepository = justificationDeductionDetailRepository;
        this.justificationDeductionDetailMapper = justificationDeductionDetailMapper;
    }

    /**
     * Save a justificationDeductionDetail.
     *
     * @param justificationDeductionDetailDTO the entity to save.
     * @return the persisted entity.
     */
    public JustificationDeductionDetailDTO save(JustificationDeductionDetailDTO justificationDeductionDetailDTO) {
        log.debug("Request to save JustificationDeductionDetail : {}", justificationDeductionDetailDTO);
        JustificationDeductionDetail justificationDeductionDetail = justificationDeductionDetailMapper.toEntity(
            justificationDeductionDetailDTO
        );
        justificationDeductionDetail = justificationDeductionDetailRepository.save(justificationDeductionDetail);
        return justificationDeductionDetailMapper.toDto(justificationDeductionDetail);
    }

    /**
     * Update a justificationDeductionDetail.
     *
     * @param justificationDeductionDetailDTO the entity to save.
     * @return the persisted entity.
     */
    public JustificationDeductionDetailDTO update(JustificationDeductionDetailDTO justificationDeductionDetailDTO) {
        log.debug("Request to update JustificationDeductionDetail : {}", justificationDeductionDetailDTO);
        JustificationDeductionDetail justificationDeductionDetail = justificationDeductionDetailMapper.toEntity(
            justificationDeductionDetailDTO
        );
        justificationDeductionDetail = justificationDeductionDetailRepository.save(justificationDeductionDetail);
        return justificationDeductionDetailMapper.toDto(justificationDeductionDetail);
    }

    /**
     * Partially update a justificationDeductionDetail.
     *
     * @param justificationDeductionDetailDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<JustificationDeductionDetailDTO> partialUpdate(JustificationDeductionDetailDTO justificationDeductionDetailDTO) {
        log.debug("Request to partially update JustificationDeductionDetail : {}", justificationDeductionDetailDTO);

        return justificationDeductionDetailRepository
            .findById(justificationDeductionDetailDTO.getId())
            .map(existingJustificationDeductionDetail -> {
                justificationDeductionDetailMapper.partialUpdate(existingJustificationDeductionDetail, justificationDeductionDetailDTO);

                return existingJustificationDeductionDetail;
            })
            .map(justificationDeductionDetailRepository::save)
            .map(justificationDeductionDetailMapper::toDto);
    }

    /**
     * Get all the justificationDeductionDetails.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<JustificationDeductionDetailDTO> findAll() {
        log.debug("Request to get all JustificationDeductionDetails");
        return justificationDeductionDetailRepository
            .findAll()
            .stream()
            .map(justificationDeductionDetailMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one justificationDeductionDetail by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<JustificationDeductionDetailDTO> findOne(Long id) {
        log.debug("Request to get JustificationDeductionDetail : {}", id);
        return justificationDeductionDetailRepository.findById(id).map(justificationDeductionDetailMapper::toDto);
    }

    /**
     * Delete the justificationDeductionDetail by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete JustificationDeductionDetail : {}", id);
        justificationDeductionDetailRepository.deleteById(id);
    }
}
