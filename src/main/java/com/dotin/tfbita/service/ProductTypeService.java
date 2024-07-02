package com.dotin.tfbita.service;

import com.dotin.tfbita.domain.ProductType;
import com.dotin.tfbita.repository.ProductTypeRepository;
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
public class ProductTypeService {

    private final Logger log = LoggerFactory.getLogger(ProductTypeService.class);

    private final ProductTypeRepository productTypeRepository;

    private final ProductTypeMapper productTypeMapper;

    public ProductTypeService(ProductTypeRepository productTypeRepository, ProductTypeMapper productTypeMapper) {
        this.productTypeRepository = productTypeRepository;
        this.productTypeMapper = productTypeMapper;
    }

    /**
     * Save a productType.
     *
     * @param productTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductTypeDTO save(ProductTypeDTO productTypeDTO) {
        log.debug("Request to save ProductType : {}", productTypeDTO);
        ProductType productType = productTypeMapper.toEntity(productTypeDTO);
        productType = productTypeRepository.save(productType);
        return productTypeMapper.toDto(productType);
    }

    /**
     * Update a productType.
     *
     * @param productTypeDTO the entity to save.
     * @return the persisted entity.
     */
    public ProductTypeDTO update(ProductTypeDTO productTypeDTO) {
        log.debug("Request to update ProductType : {}", productTypeDTO);
        ProductType productType = productTypeMapper.toEntity(productTypeDTO);
        productType = productTypeRepository.save(productType);
        return productTypeMapper.toDto(productType);
    }

    /**
     * Partially update a productType.
     *
     * @param productTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
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

    /**
     * Get all the productTypes.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ProductTypeDTO> findAll() {
        log.debug("Request to get all ProductTypes");
        return productTypeRepository.findAll().stream().map(productTypeMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get all the productTypes with eager load of many-to-many relationships.
     *
     * @return the list of entities.
     */
    public Page<ProductTypeDTO> findAllWithEagerRelationships(Pageable pageable) {
        return productTypeRepository.findAllWithEagerRelationships(pageable).map(productTypeMapper::toDto);
    }

    /**
     * Get one productType by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ProductTypeDTO> findOne(Long id) {
        log.debug("Request to get ProductType : {}", id);
        return productTypeRepository.findOneWithEagerRelationships(id).map(productTypeMapper::toDto);
    }

    /**
     * Delete the productType by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ProductType : {}", id);
        productTypeRepository.deleteById(id);
    }
}
