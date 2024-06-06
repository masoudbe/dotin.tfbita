package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.repository.CategoryElementRepository;
import com.dotin.tfbita.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dotin.tfbita.domain.CategoryElement}.
 */
@RestController
@RequestMapping("/api/category-elements")
@Transactional
public class CategoryElementResource {

    private final Logger log = LoggerFactory.getLogger(CategoryElementResource.class);

    private static final String ENTITY_NAME = "categoryElement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryElementRepository categoryElementRepository;

    public CategoryElementResource(CategoryElementRepository categoryElementRepository) {
        this.categoryElementRepository = categoryElementRepository;
    }

    /**
     * {@code POST  /category-elements} : Create a new categoryElement.
     *
     * @param categoryElement the categoryElement to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryElement, or with status {@code 400 (Bad Request)} if the categoryElement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CategoryElement> createCategoryElement(@RequestBody CategoryElement categoryElement) throws URISyntaxException {
        log.debug("REST request to save CategoryElement : {}", categoryElement);
        if (categoryElement.getId() != null) {
            throw new BadRequestAlertException("A new categoryElement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        categoryElement = categoryElementRepository.save(categoryElement);
        return ResponseEntity.created(new URI("/api/category-elements/" + categoryElement.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, categoryElement.getId().toString()))
            .body(categoryElement);
    }

    /**
     * {@code PUT  /category-elements/:id} : Updates an existing categoryElement.
     *
     * @param id the id of the categoryElement to save.
     * @param categoryElement the categoryElement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryElement,
     * or with status {@code 400 (Bad Request)} if the categoryElement is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoryElement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryElement> updateCategoryElement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CategoryElement categoryElement
    ) throws URISyntaxException {
        log.debug("REST request to update CategoryElement : {}, {}", id, categoryElement);
        if (categoryElement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryElement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryElementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        categoryElement = categoryElementRepository.save(categoryElement);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryElement.getId().toString()))
            .body(categoryElement);
    }

    /**
     * {@code PATCH  /category-elements/:id} : Partial updates given fields of an existing categoryElement, field will ignore if it is null
     *
     * @param id the id of the categoryElement to save.
     * @param categoryElement the categoryElement to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryElement,
     * or with status {@code 400 (Bad Request)} if the categoryElement is not valid,
     * or with status {@code 404 (Not Found)} if the categoryElement is not found,
     * or with status {@code 500 (Internal Server Error)} if the categoryElement couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CategoryElement> partialUpdateCategoryElement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CategoryElement categoryElement
    ) throws URISyntaxException {
        log.debug("REST request to partial update CategoryElement partially : {}, {}", id, categoryElement);
        if (categoryElement.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryElement.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryElementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CategoryElement> result = categoryElementRepository
            .findById(categoryElement.getId())
            .map(existingCategoryElement -> {
                if (categoryElement.getValue() != null) {
                    existingCategoryElement.setValue(categoryElement.getValue());
                }
                if (categoryElement.getCategoryName() != null) {
                    existingCategoryElement.setCategoryName(categoryElement.getCategoryName());
                }
                if (categoryElement.getCode() != null) {
                    existingCategoryElement.setCode(categoryElement.getCode());
                }

                return existingCategoryElement;
            })
            .map(categoryElementRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryElement.getId().toString())
        );
    }

    /**
     * {@code GET  /category-elements} : get all the categoryElements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoryElements in body.
     */
    @GetMapping("")
    public List<CategoryElement> getAllCategoryElements() {
        log.debug("REST request to get all CategoryElements");
        return categoryElementRepository.findAll();
    }

    /**
     * {@code GET  /category-elements/:id} : get the "id" categoryElement.
     *
     * @param id the id of the categoryElement to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryElement, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryElement> getCategoryElement(@PathVariable("id") Long id) {
        log.debug("REST request to get CategoryElement : {}", id);
        Optional<CategoryElement> categoryElement = categoryElementRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(categoryElement);
    }

    /**
     * {@code DELETE  /category-elements/:id} : delete the "id" categoryElement.
     *
     * @param id the id of the categoryElement to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryElement(@PathVariable("id") Long id) {
        log.debug("REST request to delete CategoryElement : {}", id);
        categoryElementRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
