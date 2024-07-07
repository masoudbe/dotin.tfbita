package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.ObjectiveCategoryElementRepository;
import com.dotin.tfbita.service.ObjectiveCategoryElementService;
import com.dotin.tfbita.service.dto.ObjectiveCategoryElementDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.ObjectiveCategoryElement}.
 */
@RestController
@RequestMapping("/api/objective-category-elements")
public class ObjectiveCategoryElementResource {

    private static final Logger log = LoggerFactory.getLogger(ObjectiveCategoryElementResource.class);

    private static final String ENTITY_NAME = "objectiveCategoryElement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ObjectiveCategoryElementService objectiveCategoryElementService;

    private final ObjectiveCategoryElementRepository objectiveCategoryElementRepository;

    public ObjectiveCategoryElementResource(
        ObjectiveCategoryElementService objectiveCategoryElementService,
        ObjectiveCategoryElementRepository objectiveCategoryElementRepository
    ) {
        this.objectiveCategoryElementService = objectiveCategoryElementService;
        this.objectiveCategoryElementRepository = objectiveCategoryElementRepository;
    }

    /**
     * {@code POST  /objective-category-elements} : Create a new objectiveCategoryElement.
     *
     * @param objectiveCategoryElementDTO the objectiveCategoryElementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new objectiveCategoryElementDTO, or with status {@code 400 (Bad Request)} if the objectiveCategoryElement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ObjectiveCategoryElementDTO> createObjectiveCategoryElement(
        @RequestBody ObjectiveCategoryElementDTO objectiveCategoryElementDTO
    ) throws URISyntaxException {
        log.debug("REST request to save ObjectiveCategoryElement : {}", objectiveCategoryElementDTO);
        if (objectiveCategoryElementDTO.getId() != null) {
            throw new BadRequestAlertException("A new objectiveCategoryElement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        objectiveCategoryElementDTO = objectiveCategoryElementService.save(objectiveCategoryElementDTO);
        return ResponseEntity.created(new URI("/api/objective-category-elements/" + objectiveCategoryElementDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, objectiveCategoryElementDTO.getId().toString())
            )
            .body(objectiveCategoryElementDTO);
    }

    /**
     * {@code PUT  /objective-category-elements/:id} : Updates an existing objectiveCategoryElement.
     *
     * @param id the id of the objectiveCategoryElementDTO to save.
     * @param objectiveCategoryElementDTO the objectiveCategoryElementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objectiveCategoryElementDTO,
     * or with status {@code 400 (Bad Request)} if the objectiveCategoryElementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the objectiveCategoryElementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ObjectiveCategoryElementDTO> updateObjectiveCategoryElement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ObjectiveCategoryElementDTO objectiveCategoryElementDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ObjectiveCategoryElement : {}, {}", id, objectiveCategoryElementDTO);
        if (objectiveCategoryElementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, objectiveCategoryElementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!objectiveCategoryElementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        objectiveCategoryElementDTO = objectiveCategoryElementService.update(objectiveCategoryElementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, objectiveCategoryElementDTO.getId().toString()))
            .body(objectiveCategoryElementDTO);
    }

    /**
     * {@code PATCH  /objective-category-elements/:id} : Partial updates given fields of an existing objectiveCategoryElement, field will ignore if it is null
     *
     * @param id the id of the objectiveCategoryElementDTO to save.
     * @param objectiveCategoryElementDTO the objectiveCategoryElementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated objectiveCategoryElementDTO,
     * or with status {@code 400 (Bad Request)} if the objectiveCategoryElementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the objectiveCategoryElementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the objectiveCategoryElementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ObjectiveCategoryElementDTO> partialUpdateObjectiveCategoryElement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ObjectiveCategoryElementDTO objectiveCategoryElementDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ObjectiveCategoryElement partially : {}, {}", id, objectiveCategoryElementDTO);
        if (objectiveCategoryElementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, objectiveCategoryElementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!objectiveCategoryElementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ObjectiveCategoryElementDTO> result = objectiveCategoryElementService.partialUpdate(objectiveCategoryElementDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, objectiveCategoryElementDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /objective-category-elements} : get all the objectiveCategoryElements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of objectiveCategoryElements in body.
     */
    @GetMapping("")
    public List<ObjectiveCategoryElementDTO> getAllObjectiveCategoryElements() {
        log.debug("REST request to get all ObjectiveCategoryElements");
        return objectiveCategoryElementService.findAll();
    }

    /**
     * {@code GET  /objective-category-elements/:id} : get the "id" objectiveCategoryElement.
     *
     * @param id the id of the objectiveCategoryElementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the objectiveCategoryElementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ObjectiveCategoryElementDTO> getObjectiveCategoryElement(@PathVariable("id") Long id) {
        log.debug("REST request to get ObjectiveCategoryElement : {}", id);
        Optional<ObjectiveCategoryElementDTO> objectiveCategoryElementDTO = objectiveCategoryElementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(objectiveCategoryElementDTO);
    }

    /**
     * {@code DELETE  /objective-category-elements/:id} : delete the "id" objectiveCategoryElement.
     *
     * @param id the id of the objectiveCategoryElementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteObjectiveCategoryElement(@PathVariable("id") Long id) {
        log.debug("REST request to delete ObjectiveCategoryElement : {}", id);
        objectiveCategoryElementService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
