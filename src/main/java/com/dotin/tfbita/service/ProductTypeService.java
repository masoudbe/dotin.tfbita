package com.dotin.tfbita.service;

import com.dotin.tfbita.service.dto.ProductTypeDTO;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Service Interface for managing {@link com.dotin.tfbita.domain.ProductType}.
 */
public interface ProductTypeService {
    /**
     * Save a productType.
     *
     * @param productTypeDTO the entity to save.
     * @return the persisted entity.
     */
    ProductTypeDTO save(ProductTypeDTO productTypeDTO);

    /**
     * Updates a productType.
     *
     * @param productTypeDTO the entity to update.
     * @return the persisted entity.
     */
    ProductTypeDTO update(ProductTypeDTO productTypeDTO);

    /**
     * Partially updates a productType.
     *
     * @param productTypeDTO the entity to update partially.
     * @return the persisted entity.
     */
    Optional<ProductTypeDTO> partialUpdate(ProductTypeDTO productTypeDTO);

    /**
     * Get all the productTypes.
     *
     * @return the list of entities.
     */
    List<ProductTypeDTO> findAll();

    /**
     * Get all the productTypes with eager load of many-to-many relationships.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<ProductTypeDTO> findAllWithEagerRelationships(Pageable pageable);

    /**
     * Get the "id" productType.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<ProductTypeDTO> findOne(Long id);

    /**
     * Delete the "id" productType.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
