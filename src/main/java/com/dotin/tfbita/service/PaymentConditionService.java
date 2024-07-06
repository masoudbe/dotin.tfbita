package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.PaymentCondition;
import com.dotin.tfbita.repository.PaymentConditionRepository;
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
public class PaymentConditionService {

    private final Logger log = LoggerFactory.getLogger(PaymentConditionService.class);

    private final PaymentConditionRepository paymentConditionRepository;

    private final PaymentConditionMapper paymentConditionMapper;

    public PaymentConditionService(PaymentConditionRepository paymentConditionRepository, PaymentConditionMapper paymentConditionMapper) {
        this.paymentConditionRepository = paymentConditionRepository;
        this.paymentConditionMapper = paymentConditionMapper;
    }

    /**
     * Save a paymentCondition.
     *
     * @param paymentConditionDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentConditionDTO save(PaymentConditionDTO paymentConditionDTO) {
        log.debug("Request to save PaymentCondition : {}", paymentConditionDTO);
        PaymentCondition paymentCondition = paymentConditionMapper.toEntity(paymentConditionDTO);
        paymentCondition = paymentConditionRepository.save(paymentCondition);
        return paymentConditionMapper.toDto(paymentCondition);
    }

    /**
     * Update a paymentCondition.
     *
     * @param paymentConditionDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentConditionDTO update(PaymentConditionDTO paymentConditionDTO) {
        log.debug("Request to update PaymentCondition : {}", paymentConditionDTO);
        PaymentCondition paymentCondition = paymentConditionMapper.toEntity(paymentConditionDTO);
        paymentCondition = paymentConditionRepository.save(paymentCondition);
        return paymentConditionMapper.toDto(paymentCondition);
    }

    /**
     * Partially update a paymentCondition.
     *
     * @param paymentConditionDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the paymentConditions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PaymentConditionDTO> findAll() {
        log.debug("Request to get all PaymentConditions");
        return paymentConditionRepository
            .findAll()
            .stream()
            .map(paymentConditionMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one paymentCondition by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PaymentConditionDTO> findOne(Long id) {
        log.debug("Request to get PaymentCondition : {}", id);
        return paymentConditionRepository.findById(id).map(paymentConditionMapper::toDto);
    }

    /**
     * Delete the paymentCondition by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PaymentCondition : {}", id);
        paymentConditionRepository.deleteById(id);
    }
}
