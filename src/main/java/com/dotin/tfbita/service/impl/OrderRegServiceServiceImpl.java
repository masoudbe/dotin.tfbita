package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.OrderRegService;
import com.dotin.tfbita.repository.OrderRegServiceRepository;
import com.dotin.tfbita.service.OrderRegServiceService;
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
public class OrderRegServiceServiceImpl implements OrderRegServiceService {

    private static final Logger log = LoggerFactory.getLogger(OrderRegServiceServiceImpl.class);

    private final OrderRegServiceRepository orderRegServiceRepository;

    private final OrderRegServiceMapper orderRegServiceMapper;

    public OrderRegServiceServiceImpl(OrderRegServiceRepository orderRegServiceRepository, OrderRegServiceMapper orderRegServiceMapper) {
        this.orderRegServiceRepository = orderRegServiceRepository;
        this.orderRegServiceMapper = orderRegServiceMapper;
    }

    @Override
    public OrderRegServiceDTO save(OrderRegServiceDTO orderRegServiceDTO) {
        log.debug("Request to save OrderRegService : {}", orderRegServiceDTO);
        OrderRegService orderRegService = orderRegServiceMapper.toEntity(orderRegServiceDTO);
        orderRegService = orderRegServiceRepository.save(orderRegService);
        return orderRegServiceMapper.toDto(orderRegService);
    }

    @Override
    public OrderRegServiceDTO update(OrderRegServiceDTO orderRegServiceDTO) {
        log.debug("Request to update OrderRegService : {}", orderRegServiceDTO);
        OrderRegService orderRegService = orderRegServiceMapper.toEntity(orderRegServiceDTO);
        orderRegService = orderRegServiceRepository.save(orderRegService);
        return orderRegServiceMapper.toDto(orderRegService);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<OrderRegServiceDTO> findAll() {
        log.debug("Request to get all OrderRegServices");
        return orderRegServiceRepository
            .findAll()
            .stream()
            .map(orderRegServiceMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderRegServiceDTO> findOne(Long id) {
        log.debug("Request to get OrderRegService : {}", id);
        return orderRegServiceRepository.findById(id).map(orderRegServiceMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderRegService : {}", id);
        orderRegServiceRepository.deleteById(id);
    }
}
