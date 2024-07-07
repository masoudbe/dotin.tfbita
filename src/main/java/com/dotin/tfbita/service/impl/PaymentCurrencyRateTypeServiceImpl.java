package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.PaymentCurrencyRateType;
import com.dotin.tfbita.repository.PaymentCurrencyRateTypeRepository;
import com.dotin.tfbita.service.PaymentCurrencyRateTypeService;
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
public class PaymentCurrencyRateTypeServiceImpl implements PaymentCurrencyRateTypeService {

    private static final Logger log = LoggerFactory.getLogger(PaymentCurrencyRateTypeServiceImpl.class);

    private final PaymentCurrencyRateTypeRepository paymentCurrencyRateTypeRepository;

    private final PaymentCurrencyRateTypeMapper paymentCurrencyRateTypeMapper;

    public PaymentCurrencyRateTypeServiceImpl(
        PaymentCurrencyRateTypeRepository paymentCurrencyRateTypeRepository,
        PaymentCurrencyRateTypeMapper paymentCurrencyRateTypeMapper
    ) {
        this.paymentCurrencyRateTypeRepository = paymentCurrencyRateTypeRepository;
        this.paymentCurrencyRateTypeMapper = paymentCurrencyRateTypeMapper;
    }

    @Override
    public PaymentCurrencyRateTypeDTO save(PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO) {
        log.debug("Request to save PaymentCurrencyRateType : {}", paymentCurrencyRateTypeDTO);
        PaymentCurrencyRateType paymentCurrencyRateType = paymentCurrencyRateTypeMapper.toEntity(paymentCurrencyRateTypeDTO);
        paymentCurrencyRateType = paymentCurrencyRateTypeRepository.save(paymentCurrencyRateType);
        return paymentCurrencyRateTypeMapper.toDto(paymentCurrencyRateType);
    }

    @Override
    public PaymentCurrencyRateTypeDTO update(PaymentCurrencyRateTypeDTO paymentCurrencyRateTypeDTO) {
        log.debug("Request to update PaymentCurrencyRateType : {}", paymentCurrencyRateTypeDTO);
        PaymentCurrencyRateType paymentCurrencyRateType = paymentCurrencyRateTypeMapper.toEntity(paymentCurrencyRateTypeDTO);
        paymentCurrencyRateType = paymentCurrencyRateTypeRepository.save(paymentCurrencyRateType);
        return paymentCurrencyRateTypeMapper.toDto(paymentCurrencyRateType);
    }

    @Override
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

    @Override
    @Transactional(readOnly = true)
    public List<PaymentCurrencyRateTypeDTO> findAll() {
        log.debug("Request to get all PaymentCurrencyRateTypes");
        return paymentCurrencyRateTypeRepository
            .findAll()
            .stream()
            .map(paymentCurrencyRateTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentCurrencyRateTypeDTO> findOne(Long id) {
        log.debug("Request to get PaymentCurrencyRateType : {}", id);
        return paymentCurrencyRateTypeRepository.findById(id).map(paymentCurrencyRateTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PaymentCurrencyRateType : {}", id);
        paymentCurrencyRateTypeRepository.deleteById(id);
    }
}
