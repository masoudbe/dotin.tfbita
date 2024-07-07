package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.CategoryElementRepository;
import com.dotin.tfbita.service.CategoryElementService;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
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
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dotin.tfbita.domain.CategoryElement}.
 */
@RestController
@RequestMapping("/api/category-elements")
public class CategoryElementResource {

    private static final Logger log = LoggerFactory.getLogger(CategoryElementResource.class);

    private static final String ENTITY_NAME = "categoryElement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CategoryElementService categoryElementService;

    private final CategoryElementRepository categoryElementRepository;

    public CategoryElementResource(CategoryElementService categoryElementService, CategoryElementRepository categoryElementRepository) {
        this.categoryElementService = categoryElementService;
        this.categoryElementRepository = categoryElementRepository;
    }

    /**
     * {@code POST  /category-elements} : Create a new categoryElement.
     *
     * @param categoryElementDTO the categoryElementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new categoryElementDTO, or with status {@code 400 (Bad Request)} if the categoryElement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CategoryElementDTO> createCategoryElement(@RequestBody CategoryElementDTO categoryElementDTO)
        throws URISyntaxException {
        log.debug("REST request to save CategoryElement : {}", categoryElementDTO);
        if (categoryElementDTO.getId() != null) {
            throw new BadRequestAlertException("A new categoryElement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        categoryElementDTO = categoryElementService.save(categoryElementDTO);
        return ResponseEntity.created(new URI("/api/category-elements/" + categoryElementDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, categoryElementDTO.getId().toString()))
            .body(categoryElementDTO);
    }

    /**
     * {@code PUT  /category-elements/:id} : Updates an existing categoryElement.
     *
     * @param id the id of the categoryElementDTO to save.
     * @param categoryElementDTO the categoryElementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryElementDTO,
     * or with status {@code 400 (Bad Request)} if the categoryElementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the categoryElementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CategoryElementDTO> updateCategoryElement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CategoryElementDTO categoryElementDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CategoryElement : {}, {}", id, categoryElementDTO);
        if (categoryElementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryElementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryElementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        categoryElementDTO = categoryElementService.update(categoryElementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryElementDTO.getId().toString()))
            .body(categoryElementDTO);
    }

    /**
     * {@code PATCH  /category-elements/:id} : Partial updates given fields of an existing categoryElement, field will ignore if it is null
     *
     * @param id the id of the categoryElementDTO to save.
     * @param categoryElementDTO the categoryElementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated categoryElementDTO,
     * or with status {@code 400 (Bad Request)} if the categoryElementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the categoryElementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the categoryElementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CategoryElementDTO> partialUpdateCategoryElement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CategoryElementDTO categoryElementDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CategoryElement partially : {}, {}", id, categoryElementDTO);
        if (categoryElementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, categoryElementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!categoryElementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CategoryElementDTO> result = categoryElementService.partialUpdate(categoryElementDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, categoryElementDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /category-elements} : get all the categoryElements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of categoryElements in body.
     */
    @GetMapping("")
    public List<CategoryElementDTO> getAllCategoryElements() {
        log.debug("REST request to get all CategoryElements");
        return categoryElementService.findAll();
    }

    /**
     * {@code GET  /category-elements/:id} : get the "id" categoryElement.
     *
     * @param id the id of the categoryElementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the categoryElementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CategoryElementDTO> getCategoryElement(@PathVariable("id") Long id) {
        log.debug("REST request to get CategoryElement : {}", id);
        Optional<CategoryElementDTO> categoryElementDTO = categoryElementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(categoryElementDTO);
    }

    /**
     * {@code DELETE  /category-elements/:id} : delete the "id" categoryElement.
     *
     * @param id the id of the categoryElementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategoryElement(@PathVariable("id") Long id) {
        log.debug("REST request to delete CategoryElement : {}", id);
        categoryElementService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
