package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.PaymentCondition;
import com.dotin.tfbita.repository.PaymentConditionRepository;
import com.dotin.tfbita.service.PaymentConditionService;
import com.dotin.tfbita.service.dto.PaymentConditionDTO;
import com.dotin.tfbita.service.mapper.PaymentConditionMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.PaymentCondition}.
 */
@Service
@Transactional
public class PaymentConditionServiceImpl implements PaymentConditionService {

    private static final Logger log = LoggerFactory.getLogger(PaymentConditionServiceImpl.class);

    private final PaymentConditionRepository paymentConditionRepository;

    private final PaymentConditionMapper paymentConditionMapper;

    public PaymentConditionServiceImpl(
        PaymentConditionRepository paymentConditionRepository,
        PaymentConditionMapper paymentConditionMapper
    ) {
        this.paymentConditionRepository = paymentConditionRepository;
        this.paymentConditionMapper = paymentConditionMapper;
    }

    @Override
    public PaymentConditionDTO save(PaymentConditionDTO paymentConditionDTO) {
        log.debug("Request to save PaymentCondition : {}", paymentConditionDTO);
        PaymentCondition paymentCondition = paymentConditionMapper.toEntity(paymentConditionDTO);
        paymentCondition = paymentConditionRepository.save(paymentCondition);
        return paymentConditionMapper.toDto(paymentCondition);
    }

    @Override
    public PaymentConditionDTO update(PaymentConditionDTO paymentConditionDTO) {
        log.debug("Request to update PaymentCondition : {}", paymentConditionDTO);
        PaymentCondition paymentCondition = paymentConditionMapper.toEntity(paymentConditionDTO);
        paymentCondition = paymentConditionRepository.save(paymentCondition);
        return paymentConditionMapper.toDto(paymentCondition);
    }

    @Override
    public Optional<PaymentConditionDTO> partialUpdate(PaymentConditionDTO paymentConditionDTO) {
        log.debug("Request to partially update PaymentCondition : {}", paymentConditionDTO);

        return paymentConditionRepository
            .findById(paymentConditionDTO.getId())
            .map(existingPaymentCondition -> {
                paymentConditionMapper.partialUpdate(existingPaymentCondition, paymentConditionDTO);

                return existingPaymentCondition;
            })
            .map(paymentConditionRepository::save)
            .map(paymentConditionMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentConditionDTO> findAll() {
        log.debug("Request to get all PaymentConditions");
        return paymentConditionRepository
            .findAll()
            .stream()
            .map(paymentConditionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentConditionDTO> findOne(Long id) {
        log.debug("Request to get PaymentCondition : {}", id);
        return paymentConditionRepository.findById(id).map(paymentConditionMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PaymentCondition : {}", id);
        paymentConditionRepository.deleteById(id);
    }
}
