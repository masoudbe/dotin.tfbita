package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.OrderRegServ;
import com.dotin.tfbita.repository.OrderRegServRepository;
import com.dotin.tfbita.service.dto.OrderRegServDTO;
import com.dotin.tfbita.service.mapper.OrderRegServMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.OrderRegServ}.
 */
@Service
@Transactional
public class OrderRegServService {

    private final Logger log = LoggerFactory.getLogger(OrderRegServService.class);

    private final OrderRegServRepository orderRegServRepository;

    private final OrderRegServMapper orderRegServMapper;

    public OrderRegServService(OrderRegServRepository orderRegServRepository, OrderRegServMapper orderRegServMapper) {
        this.orderRegServRepository = orderRegServRepository;
        this.orderRegServMapper = orderRegServMapper;
    }

    /**
     * Save a orderRegServ.
     *
     * @param orderRegServDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderRegServDTO save(OrderRegServDTO orderRegServDTO) {
        log.debug("Request to save OrderRegServ : {}", orderRegServDTO);
        OrderRegServ orderRegServ = orderRegServMapper.toEntity(orderRegServDTO);
        orderRegServ = orderRegServRepository.save(orderRegServ);
        return orderRegServMapper.toDto(orderRegServ);
    }

    /**
     * Update a orderRegServ.
     *
     * @param orderRegServDTO the entity to save.
     * @return the persisted entity.
     */
    public OrderRegServDTO update(OrderRegServDTO orderRegServDTO) {
        log.debug("Request to update OrderRegServ : {}", orderRegServDTO);
        OrderRegServ orderRegServ = orderRegServMapper.toEntity(orderRegServDTO);
        orderRegServ = orderRegServRepository.save(orderRegServ);
        return orderRegServMapper.toDto(orderRegServ);
    }

    /**
     * Partially update a orderRegServ.
     *
     * @param orderRegServDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<OrderRegServDTO> partialUpdate(OrderRegServDTO orderRegServDTO) {
        log.debug("Request to partially update OrderRegServ : {}", orderRegServDTO);

        return orderRegServRepository
            .findById(orderRegServDTO.getId())
            .map(existingOrderRegServ -> {
                orderRegServMapper.partialUpdate(existingOrderRegServ, orderRegServDTO);

                return existingOrderRegServ;
            })
            .map(orderRegServRepository::save)
            .map(orderRegServMapper::toDto);
    }

    /**
     * Get all the orderRegServs.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<OrderRegServDTO> findAll() {
        log.debug("Request to get all OrderRegServs");
        return orderRegServRepository.findAll().stream().map(orderRegServMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one orderRegServ by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<OrderRegServDTO> findOne(Long id) {
        log.debug("Request to get OrderRegServ : {}", id);
        return orderRegServRepository.findById(id).map(orderRegServMapper::toDto);
    }

    /**
     * Delete the orderRegServ by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete OrderRegServ : {}", id);
        orderRegServRepository.deleteById(id);
    }
}
