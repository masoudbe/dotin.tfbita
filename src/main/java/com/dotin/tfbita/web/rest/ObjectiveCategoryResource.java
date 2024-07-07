package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.ObjectiveCategoryRepository;
import com.dotin.tfbita.service.ObjectiveCategoryService;
import com.dotin.tfbita.service.dto.ObjectiveCategoryDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.ObjectiveCategory}.
 */
@RestController
@RequestMapping("/api/objective-categories")
public class ObjectiveCategoryResource {

    private final Logger log = LoggerFactory.getLogger(ObjectiveCategoryResource.class);

    private static final String ENTITY_NAME = "objectiveCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ObjectiveCategoryService objectiveCategoryService;

    private final ObjectiveCategoryRepository objectiveCategoryRepository;

    public ObjectiveCategoryResource(
        ObjectiveCategoryService objectiveCategoryService,
        ObjectiveCategoryRepository objectiveCategoryRepository
    ) {
        this.objectiveCategoryService = objectiveCategoryService;
        this.objectiveCategoryRepository = objectiveCategoryRepository;
    }

    /**
     * {@code POST  /objective-categories} : Create a new objectiveCategory.
     *
     * @param objectiveCategoryDTO the objectiveCategoryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new objectiveCategoryDTO, or with status {@code 400 (Bad Request)} if the objectiveCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ObjectiveCategoryDTO> createObjectiveCategory(@RequestBody ObjectiveCategoryDTO objectiveCategoryDTO)
        throws URISyntaxException {
        log.debug("REST request to save ObjectiveCategory : {}", objectiveCategoryDTO);
        if (objectiveCategoryDTO.getId() != null) {
            throw new BadRequestAlertException("A new objectiveCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        objectiveCategoryDTO = objectiveCategoryService.save(objectiveCategoryDTO);
        return ResponseEntity.created(new URI("/api/objective-categories/" + objectiveCategoryDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, objectiveCategoryDTO.getId().toString()))
            .body(objectiveCategoryDTO);
    }

    /**
     * {@code PUT  /objective-categories/:id} : Updates an existing objectiveCategory.
     *
     * @param id the id of the objectiveCategoryDTO to save.
     * @param objectiveCategoryDTO the objectiveCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objectiveCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the objectiveCategoryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the objectiveCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ObjectiveCategoryDTO> updateObjectiveCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ObjectiveCategoryDTO objectiveCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ObjectiveCategory : {}, {}", id, objectiveCategoryDTO);
        if (objectiveCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, objectiveCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!objectiveCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        objectiveCategoryDTO = objectiveCategoryService.update(objectiveCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, objectiveCategoryDTO.getId().toString()))
            .body(objectiveCategoryDTO);
    }

    /**
     * {@code PATCH  /objective-categories/:id} : Partial updates given fields of an existing objectiveCategory, field will ignore if it is null
     *
     * @param id the id of the objectiveCategoryDTO to save.
     * @param objectiveCategoryDTO the objectiveCategoryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objectiveCategoryDTO,
     * or with status {@code 400 (Bad Request)} if the objectiveCategoryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the objectiveCategoryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the objectiveCategoryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ObjectiveCategoryDTO> partialUpdateObjectiveCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ObjectiveCategoryDTO objectiveCategoryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ObjectiveCategory partially : {}, {}", id, objectiveCategoryDTO);
        if (objectiveCategoryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, objectiveCategoryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!objectiveCategoryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ObjectiveCategoryDTO> result = objectiveCategoryService.partialUpdate(objectiveCategoryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, objectiveCategoryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /objective-categories} : get all the objectiveCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of objectiveCategories in body.
     */
    @GetMapping("")
    public List<ObjectiveCategoryDTO> getAllObjectiveCategories() {
        log.debug("REST request to get all ObjectiveCategories");
        return objectiveCategoryService.findAll();
    }

    /**
     * {@code GET  /objective-categories/:id} : get the "id" objectiveCategory.
     *
     * @param id the id of the objectiveCategoryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the objectiveCategoryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ObjectiveCategoryDTO> getObjectiveCategory(@PathVariable("id") Long id) {
        log.debug("REST request to get ObjectiveCategory : {}", id);
        Optional<ObjectiveCategoryDTO> objectiveCategoryDTO = objectiveCategoryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(objectiveCategoryDTO);
    }

    /**
     * {@code DELETE  /objective-categories/:id} : delete the "id" objectiveCategory.
     *
     * @param id the id of the objectiveCategoryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObjectiveCategory(@PathVariable("id") Long id) {
        log.debug("REST request to delete ObjectiveCategory : {}", id);
        objectiveCategoryService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
