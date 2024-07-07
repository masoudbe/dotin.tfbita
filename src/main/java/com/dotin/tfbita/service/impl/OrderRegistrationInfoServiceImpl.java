package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.OrderRegistrationInfo;
import com.dotin.tfbita.repository.OrderRegistrationInfoRepository;
import com.dotin.tfbita.service.OrderRegistrationInfoService;
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
public class OrderRegistrationInfoServiceImpl implements OrderRegistrationInfoService {

    private static final Logger log = LoggerFactory.getLogger(OrderRegistrationInfoServiceImpl.class);

    private final OrderRegistrationInfoRepository orderRegistrationInfoRepository;

    private final OrderRegistrationInfoMapper orderRegistrationInfoMapper;

    public OrderRegistrationInfoServiceImpl(
        OrderRegistrationInfoRepository orderRegistrationInfoRepository,
        OrderRegistrationInfoMapper orderRegistrationInfoMapper
    ) {
        this.orderRegistrationInfoRepository = orderRegistrationInfoRepository;
        this.orderRegistrationInfoMapper = orderRegistrationInfoMapper;
    }

    @Override
    public OrderRegistrationInfoDTO save(OrderRegistrationInfoDTO orderRegistrationInfoDTO) {
        log.debug("Request to save OrderRegistrationInfo : {}", orderRegistrationInfoDTO);
        OrderRegistrationInfo orderRegistrationInfo = orderRegistrationInfoMapper.toEntity(orderRegistrationInfoDTO);
        orderRegistrationInfo = orderRegistrationInfoRepository.save(orderRegistrationInfo);
        return orderRegistrationInfoMapper.toDto(orderRegistrationInfo);
    }

    @Override
    public OrderRegistrationInfoDTO update(OrderRegistrationInfoDTO orderRegistrationInfoDTO) {
        log.debug("Request to update OrderRegistrationInfo : {}", orderRegistrationInfoDTO);
        OrderRegistrationInfo orderRegistrationInfo = orderRegistrationInfoMapper.toEntity(orderRegistrationInfoDTO);
        orderRegistrationInfo = orderRegistrationInfoRepository.save(orderRegistrationInfo);
        return orderRegistrationInfoMapper.toDto(orderRegistrationInfo);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<OrderRegistrationInfoDTO> findAll() {
        log.debug("Request to get all OrderRegistrationInfos");
        return orderRegistrationInfoRepository
            .findAll()
            .stream()
            .map(orderRegistrationInfoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<OrderRegistrationInfoDTO> findAllWithEagerRelationships(Pageable pageable) {
        return orderRegistrationInfoRepository.findAllWithEagerRelationships(pageable).map(orderRegistrationInfoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrderRegistrationInfoDTO> findOne(Long id) {
        log.debug("Request to get OrderRegistrationInfo : {}", id);
        return orderRegistrationInfoRepository.findOneWithEagerRelationships(id).map(orderRegistrationInfoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete OrderRegistrationInfo : {}", id);
        orderRegistrationInfoRepository.deleteById(id);
    }
}
