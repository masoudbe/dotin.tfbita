package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.OrderRegService;
import com.dotin.tfbita.repository.OrderRegServiceRepository;
import com.dotin.tfbita.service.dto.OrderRegServiceDTO;
import com.dotin.tfbita.service.mapper.OrderRegServiceMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.OrderRegService}.
 */
@Service
@Transactional
public class OrderRegServiceService {

    private final Logger log = LoggerFactory.getLogger(OrderRegServiceService.class);

    private final OrderRegServiceRepository orderRegServiceRepository;

    private final OrderRegServiceMapper orderRegServiceMapper;

    public OrderRegServiceService(OrderRegServiceRepository orderRegServiceRepository, OrderRegServiceMapper orderRegServiceMapper) {
        this.orderRegServiceRepository = orderRegServiceRepository;
        this.orderRegServiceMapper = orderRegServiceMapper;
    }

    /**
     * Save a orderRegService.
     *
     * @param orderRegServiceDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderRegServiceDTO save(OrderRegServiceDTO orderRegServiceDTO) {
        log.debug("Request to save OrderRegService : {}", orderRegServiceDTO);
        OrderRegService orderRegService = orderRegServiceMapper.toEntity(orderRegServiceDTO);
        orderRegService = orderRegServiceRepository.save(orderRegService);
        return orderRegServiceMapper.toDto(orderRegService);
    }

    /**
     * Update a orderRegService.
     *
     * @param orderRegServiceDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderRegServiceDTO update(OrderRegServiceDTO orderRegServiceDTO) {
        log.debug("Request to update OrderRegService : {}", orderRegServiceDTO);
        OrderRegService orderRegService = orderRegServiceMapper.toEntity(orderRegServiceDTO);
        orderRegService = orderRegServiceRepository.save(orderRegService);
        return orderRegServiceMapper.toDto(orderRegService);
    }

    /**
     * Partially update a orderRegService.
     *
     * @param orderRegServiceDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OrderRegServiceDTO> partialUpdate(OrderRegServiceDTO orderRegServiceDTO) {
        log.debug("Request to partially update OrderRegService : {}", orderRegServiceDTO);

        return orderRegServiceRepository
            .findById(orderRegServiceDTO.getId())
            .map(existingOrderRegService -> {
                orderRegServiceMapper.partialUpdate(existingOrderRegService, orderRegServiceDTO);

                return existingOrderRegService;
            })
            .map(orderRegServiceRepository::save)
            .map(orderRegServiceMapper::toDto);
    }

    /**
     * Get all the orderRegServices.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OrderRegServiceDTO> findAll() {
        log.debug("Request to get all OrderRegServices");
        return orderRegServiceRepository
            .findAll()
            .stream()
            .map(orderRegServiceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one orderRegService by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderRegServiceDTO> findOne(Long id) {
        log.debug("Request to get OrderRegService : {}", id);
        return orderRegServiceRepository.findById(id).map(orderRegServiceMapper::toDto);
    }

    /**
     * Delete the orderRegService by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrderRegService : {}", id);
        orderRegServiceRepository.deleteById(id);
    }
}
