package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.PaymentItemType;
import com.dotin.tfbita.repository.PaymentItemTypeRepository;
import com.dotin.tfbita.service.PaymentItemTypeService;
import com.dotin.tfbita.service.dto.PaymentItemTypeDTO;
import com.dotin.tfbita.service.mapper.PaymentItemTypeMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.PaymentItemType}.
 */
@Service
@Transactional
public class PaymentItemTypeServiceImpl implements PaymentItemTypeService {

    private static final Logger log = LoggerFactory.getLogger(PaymentItemTypeServiceImpl.class);

    private final PaymentItemTypeRepository paymentItemTypeRepository;

    private final PaymentItemTypeMapper paymentItemTypeMapper;

    public PaymentItemTypeServiceImpl(PaymentItemTypeRepository paymentItemTypeRepository, PaymentItemTypeMapper paymentItemTypeMapper) {
        this.paymentItemTypeRepository = paymentItemTypeRepository;
        this.paymentItemTypeMapper = paymentItemTypeMapper;
    }

    @Override
    public PaymentItemTypeDTO save(PaymentItemTypeDTO paymentItemTypeDTO) {
        log.debug("Request to save PaymentItemType : {}", paymentItemTypeDTO);
        PaymentItemType paymentItemType = paymentItemTypeMapper.toEntity(paymentItemTypeDTO);
        paymentItemType = paymentItemTypeRepository.save(paymentItemType);
        return paymentItemTypeMapper.toDto(paymentItemType);
    }

    @Override
    public PaymentItemTypeDTO update(PaymentItemTypeDTO paymentItemTypeDTO) {
        log.debug("Request to update PaymentItemType : {}", paymentItemTypeDTO);
        PaymentItemType paymentItemType = paymentItemTypeMapper.toEntity(paymentItemTypeDTO);
        paymentItemType = paymentItemTypeRepository.save(paymentItemType);
        return paymentItemTypeMapper.toDto(paymentItemType);
    }

    @Override
    public Optional<PaymentItemTypeDTO> partialUpdate(PaymentItemTypeDTO paymentItemTypeDTO) {
        log.debug("Request to partially update PaymentItemType : {}", paymentItemTypeDTO);

        return paymentItemTypeRepository
            .findById(paymentItemTypeDTO.getId())
            .map(existingPaymentItemType -> {
                paymentItemTypeMapper.partialUpdate(existingPaymentItemType, paymentItemTypeDTO);

                return existingPaymentItemType;
            })
            .map(paymentItemTypeRepository::save)
            .map(paymentItemTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<PaymentItemTypeDTO> findAll() {
        log.debug("Request to get all PaymentItemTypes");
        return paymentItemTypeRepository
            .findAll()
            .stream()
            .map(paymentItemTypeMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PaymentItemTypeDTO> findOne(Long id) {
        log.debug("Request to get PaymentItemType : {}", id);
        return paymentItemTypeRepository.findById(id).map(paymentItemTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PaymentItemType : {}", id);
        paymentItemTypeRepository.deleteById(id);
    }
}
