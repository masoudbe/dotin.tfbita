package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.repository.OrderRegistrationInfoRepository;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
import com.dotin.tfbita.service.mapper.OrderRegistrationInfoMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.OrderRegistrationInfo}.
 */
@Service
@Transactional
public class OrderRegistrationInfoService {

    private final Logger log = LoggerFactory.getLogger(OrderRegistrationInfoService.class);

    private final OrderRegistrationInfoRepository orderRegistrationInfoRepository;

    private final OrderRegistrationInfoMapper orderRegistrationInfoMapper;

    public OrderRegistrationInfoService(
        OrderRegistrationInfoRepository orderRegistrationInfoRepository,
        OrderRegistrationInfoMapper orderRegistrationInfoMapper
    ) {
        this.orderRegistrationInfoRepository = orderRegistrationInfoRepository;
        this.orderRegistrationInfoMapper = orderRegistrationInfoMapper;
    }

    /**
     * Save a orderRegistrationInfo.
     *
     * @param orderRegistrationInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderRegistrationInfoDTO save(OrderRegistrationInfoDTO orderRegistrationInfoDTO) {
        log.debug("Request to save OrderRegistrationInfo : {}", orderRegistrationInfoDTO);
        OrderRegistrationInfo orderRegistrationInfo = orderRegistrationInfoMapper.toEntity(orderRegistrationInfoDTO);
        orderRegistrationInfo = orderRegistrationInfoRepository.save(orderRegistrationInfo);
        return orderRegistrationInfoMapper.toDto(orderRegistrationInfo);
    }

    /**
     * Update a orderRegistrationInfo.
     *
     * @param orderRegistrationInfoDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderRegistrationInfoDTO update(OrderRegistrationInfoDTO orderRegistrationInfoDTO) {
        log.debug("Request to update OrderRegistrationInfo : {}", orderRegistrationInfoDTO);
        OrderRegistrationInfo orderRegistrationInfo = orderRegistrationInfoMapper.toEntity(orderRegistrationInfoDTO);
        orderRegistrationInfo = orderRegistrationInfoRepository.save(orderRegistrationInfo);
        return orderRegistrationInfoMapper.toDto(orderRegistrationInfo);
    }

    /**
     * Partially update a orderRegistrationInfo.
     *
     * @param orderRegistrationInfoDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OrderRegistrationInfoDTO> partialUpdate(OrderRegistrationInfoDTO orderRegistrationInfoDTO) {
        log.debug("Request to partially update OrderRegistrationInfo : {}", orderRegistrationInfoDTO);

        return orderRegistrationInfoRepository
            .findById(orderRegistrationInfoDTO.getId())
            .map(existingOrderRegistrationInfo -> {
                orderRegistrationInfoMapper.partialUpdate(existingOrderRegistrationInfo, orderRegistrationInfoDTO);

                return existingOrderRegistrationInfo;
            })
            .map(orderRegistrationInfoRepository::save)
            .map(orderRegistrationInfoMapper::toDto);
    }

    /**
     * Get all the orderRegistrationInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OrderRegistrationInfoDTO> findAll() {
        log.debug("Request to get all OrderRegistrationInfos");
        return orderRegistrationInfoRepository
            .findAll()
            .stream()
            .map(orderRegistrationInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the orderRegistrationInfos with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<OrderRegistrationInfoDTO> findAllWithEagerRelationships(Pageable pageable) {
        return orderRegistrationInfoRepository.findAllWithEagerRelationships(pageable).map(orderRegistrationInfoMapper::toDto);
    }

    /**
     * Get one orderRegistrationInfo by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderRegistrationInfoDTO> findOne(Long id) {
        log.debug("Request to get OrderRegistrationInfo : {}", id);
        return orderRegistrationInfoRepository.findOneWithEagerRelationships(id).map(orderRegistrationInfoMapper::toDto);
    }

    /**
     * Delete the orderRegistrationInfo by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrderRegistrationInfo : {}", id);
        orderRegistrationInfoRepository.deleteById(id);
    }
}
