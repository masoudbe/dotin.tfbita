package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.SwiftBic;
import com.dotin.tfbita.repository.SwiftBicRepository;
import com.dotin.tfbita.service.dto.SwiftBicDTO;
import com.dotin.tfbita.service.mapper.SwiftBicMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.SwiftBic}.
 */
@Service
@Transactional
public class SwiftBicService {

    private final Logger log = LoggerFactory.getLogger(SwiftBicService.class);

    private final SwiftBicRepository swiftBicRepository;

    private final SwiftBicMapper swiftBicMapper;

    public SwiftBicService(SwiftBicRepository swiftBicRepository, SwiftBicMapper swiftBicMapper) {
        this.swiftBicRepository = swiftBicRepository;
        this.swiftBicMapper = swiftBicMapper;
    }

    /**
     * Save a swiftBic.
     *
     * @param swiftBicDTO the entity to save.
     * @return the persisted entity.
     */
    public SwiftBicDTO save(SwiftBicDTO swiftBicDTO) {
        log.debug("Request to save SwiftBic : {}", swiftBicDTO);
        SwiftBic swiftBic = swiftBicMapper.toEntity(swiftBicDTO);
        swiftBic = swiftBicRepository.save(swiftBic);
        return swiftBicMapper.toDto(swiftBic);
    }

    /**
     * Update a swiftBic.
     *
     * @param swiftBicDTO the entity to save.
     * @return the persisted entity.
     */
    public SwiftBicDTO update(SwiftBicDTO swiftBicDTO) {
        log.debug("Request to update SwiftBic : {}", swiftBicDTO);
        SwiftBic swiftBic = swiftBicMapper.toEntity(swiftBicDTO);
        swiftBic = swiftBicRepository.save(swiftBic);
        return swiftBicMapper.toDto(swiftBic);
    }

    /**
     * Partially update a swiftBic.
     *
     * @param swiftBicDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<SwiftBicDTO> partialUpdate(SwiftBicDTO swiftBicDTO) {
        log.debug("Request to partially update SwiftBic : {}", swiftBicDTO);

        return swiftBicRepository
            .findById(swiftBicDTO.getId())
            .map(existingSwiftBic -> {
                swiftBicMapper.partialUpdate(existingSwiftBic, swiftBicDTO);

                return existingSwiftBic;
            })
            .map(swiftBicRepository::save)
            .map(swiftBicMapper::toDto);
    }

    /**
     * Get all the swiftBics.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SwiftBicDTO> findAll() {
        log.debug("Request to get all SwiftBics");
        return swiftBicRepository.findAll().stream().map(swiftBicMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one swiftBic by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SwiftBicDTO> findOne(Long id) {
        log.debug("Request to get SwiftBic : {}", id);
        return swiftBicRepository.findById(id).map(swiftBicMapper::toDto);
    }

    /**
     * Delete the swiftBic by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SwiftBic : {}", id);
        swiftBicRepository.deleteById(id);
    }
}
