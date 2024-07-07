package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.PaymentCurrencyRateType;
import com.dotin.tfbita.repository.PaymentCurrencyRateTypeRepository;
import com.dotin.tfbita.service.dto.PaymentCurrencyRateTypeDTO;
import com.dotin.tfbita.service.mapper.PaymentCurrencyRateTypeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.PaymentCurrencyRateType}.
 */
@Service
@Transactional
public class PaymentCurrencyRateTypeService {

    private final Logger log = LoggerFactory.getLogger(PaymentCurrencyRateTypeService.class);

    private final PaymentCurrencyRateTypeRepository paymentCurrencyRateTypeRepository;

    private final PaymentCurrencyRateTypeMapper paymentCurrencyRateTypeMapper;

    public PaymentCurrencyRateTypeService(
        PaymentCurrencyRateTypeRepository paymentCurrencyRateTypeRepository,
        PaymentCurrencyRateTypeMapper paymentCurrencyRateTypeMapper
    ) {
        this.paymentCurrencyRateTypeRepository = paymentCurrencyRateTypeRepository;
        this.paymentCurrencyRateTypeMapper = paymentCurrencyRateTypeMapper;
    }

    /**
     * Save a paymentCurrencyRateType.
     *
     * @param paymentCurrencyRateTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentCurrencyRateTypeDTO save(PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO) {
        log.debug("Request to save PaymentCurrencyRateType : {}", paymentCurrencyRateTypeDTO);
        PaymentCurrencyRateType paymentCurrencyRateType = paymentCurrencyRateTypeMapper.toEntity(paymentCurrencyRateTypeDTO);
        paymentCurrencyRateType = paymentCurrencyRateTypeRepository.save(paymentCurrencyRateType);
        return paymentCurrencyRateTypeMapper.toDto(paymentCurrencyRateType);
    }

    /**
     * Update a paymentCurrencyRateType.
     *
     * @param paymentCurrencyRateTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public PaymentCurrencyRateTypeDTO update(PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO) {
        log.debug("Request to update PaymentCurrencyRateType : {}", paymentCurrencyRateTypeDTO);
        PaymentCurrencyRateType paymentCurrencyRateType = paymentCurrencyRateTypeMapper.toEntity(paymentCurrencyRateTypeDTO);
        paymentCurrencyRateType = paymentCurrencyRateTypeRepository.save(paymentCurrencyRateType);
        return paymentCurrencyRateTypeMapper.toDto(paymentCurrencyRateType);
    }

    /**
     * Partially update a paymentCurrencyRateType.
     *
     * @param paymentCurrencyRateTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<PaymentCurrencyRateTypeDTO> partialUpdate(PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO) {
        log.debug("Request to partially update PaymentCurrencyRateType : {}", paymentCurrencyRateTypeDTO);

        return paymentCurrencyRateTypeRepository
            .findById(paymentCurrencyRateTypeDTO.getId())
            .map(existingPaymentCurrencyRateType -> {
                paymentCurrencyRateTypeMapper.partialUpdate(existingPaymentCurrencyRateType, paymentCurrencyRateTypeDTO);

                return existingPaymentCurrencyRateType;
            })
            .map(paymentCurrencyRateTypeRepository::save)
            .map(paymentCurrencyRateTypeMapper::toDto);
    }

    /**
     * Get all the paymentCurrencyRateTypes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<PaymentCurrencyRateTypeDTO> findAll() {
        log.debug("Request to get all PaymentCurrencyRateTypes");
        return paymentCurrencyRateTypeRepository
            .findAll()
            .stream()
            .map(paymentCurrencyRateTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one paymentCurrencyRateType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<PaymentCurrencyRateTypeDTO> findOne(Long id) {
        log.debug("Request to get PaymentCurrencyRateType : {}", id);
        return paymentCurrencyRateTypeRepository.findById(id).map(paymentCurrencyRateTypeMapper::toDto);
    }

    /**
     * Delete the paymentCurrencyRateType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete PaymentCurrencyRateType : {}", id);
        paymentCurrencyRateTypeRepository.deleteById(id);
    }
}
