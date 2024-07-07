package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.ProductType;
import com.dotin.tfbita.repository.ProductTypeRepository;
import com.dotin.tfbita.service.ProductTypeService;
import com.dotin.tfbita.service.dto.ProductTypeDTO;
import com.dotin.tfbita.service.mapper.ProductTypeMapper;
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
 * Service Implementation for managing {@link com.dotin.tfbita.domain.ProductType}.
 */
@Service
@Transactional
public class ProductTypeServiceImpl implements ProductTypeService {

    private static final Logger log = LoggerFactory.getLogger(ProductTypeServiceImpl.class);

    private final ProductTypeRepository productTypeRepository;

    private final ProductTypeMapper productTypeMapper;

    public ProductTypeServiceImpl(ProductTypeRepository productTypeRepository, ProductTypeMapper productTypeMapper) {
        this.productTypeRepository = productTypeRepository;
        this.productTypeMapper = productTypeMapper;
    }

    @Override
    public ProductTypeDTO save(ProductTypeDTO productTypeDTO) {
        log.debug("Request to save ProductType : {}", productTypeDTO);
        ProductType productType = productTypeMapper.toEntity(productTypeDTO);
        productType = productTypeRepository.save(productType);
        return productTypeMapper.toDto(productType);
    }

    @Override
    public ProductTypeDTO update(ProductTypeDTO productTypeDTO) {
        log.debug("Request to update ProductType : {}", productTypeDTO);
        ProductType productType = productTypeMapper.toEntity(productTypeDTO);
        productType = productTypeRepository.save(productType);
        return productTypeMapper.toDto(productType);
    }

    @Override
    public Optional<ProductTypeDTO> partialUpdate(ProductTypeDTO productTypeDTO) {
        log.debug("Request to partially update ProductType : {}", productTypeDTO);

        return productTypeRepository
            .findById(productTypeDTO.getId())
            .map(existingProductType -> {
                productTypeMapper.partialUpdate(existingProductType, productTypeDTO);

                return existingProductType;
            })
            .map(productTypeRepository::save)
            .map(productTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProductTypeDTO> findAll() {
        log.debug("Request to get all ProductTypes");
        return productTypeRepository.findAll().stream().map(productTypeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    public Page<ProductTypeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return productTypeRepository.findAllWithEagerRelationships(pageable).map(productTypeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductTypeDTO> findOne(Long id) {
        log.debug("Request to get ProductType : {}", id);
        return productTypeRepository.findOneWithEagerRelationships(id).map(productTypeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ProductType : {}", id);
        productTypeRepository.deleteById(id);
    }
}
