package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.CustomJustificationRepository;
import com.dotin.tfbita.service.CustomJustificationService;
import com.dotin.tfbita.service.dto.CustomJustificationDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.CustomJustification}.
 */
@RestController
@RequestMapping("/api/custom-justifications")
public class CustomJustificationResource {

    private final Logger log = LoggerFactory.getLogger(CustomJustificationResource.class);

    private static final String ENTITY_NAME = "customJustification";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomJustificationService customJustificationService;

    private final CustomJustificationRepository customJustificationRepository;

    public CustomJustificationResource(
        CustomJustificationService customJustificationService,
        CustomJustificationRepository customJustificationRepository
    ) {
        this.customJustificationService = customJustificationService;
        this.customJustificationRepository = customJustificationRepository;
    }

    /**
     * {@code POST  /custom-justifications} : Create a new customJustification.
     *
     * @param customJustificationDTO the customJustificationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customJustificationDTO, or with status {@code 400 (Bad Request)} if the customJustification has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CustomJustificationDTO> createCustomJustification(@RequestBody CustomJustificationDTO customJustificationDTO)
        throws URISyntaxException {
        log.debug("REST request to save CustomJustification : {}", customJustificationDTO);
        if (customJustificationDTO.getId() != null) {
            throw new BadRequestAlertException("A new customJustification cannot already have an ID", ENTITY_NAME, "idexists");
        }
        customJustificationDTO = customJustificationService.save(customJustificationDTO);
        return ResponseEntity.created(new URI("/api/custom-justifications/" + customJustificationDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, customJustificationDTO.getId().toString()))
            .body(customJustificationDTO);
    }

    /**
     * {@code PUT  /custom-justifications/:id} : Updates an existing customJustification.
     *
     * @param id the id of the customJustificationDTO to save.
     * @param customJustificationDTO the customJustificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customJustificationDTO,
     * or with status {@code 400 (Bad Request)} if the customJustificationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customJustificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomJustificationDTO> updateCustomJustification(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomJustificationDTO customJustificationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CustomJustification : {}, {}", id, customJustificationDTO);
        if (customJustificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customJustificationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customJustificationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        customJustificationDTO = customJustificationService.update(customJustificationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customJustificationDTO.getId().toString()))
            .body(customJustificationDTO);
    }

    /**
     * {@code PATCH  /custom-justifications/:id} : Partial updates given fields of an existing customJustification, field will ignore if it is null
     *
     * @param id the id of the customJustificationDTO to save.
     * @param customJustificationDTO the customJustificationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customJustificationDTO,
     * or with status {@code 400 (Bad Request)} if the customJustificationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the customJustificationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the customJustificationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CustomJustificationDTO> partialUpdateCustomJustification(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomJustificationDTO customJustificationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CustomJustification partially : {}, {}", id, customJustificationDTO);
        if (customJustificationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customJustificationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customJustificationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CustomJustificationDTO> result = customJustificationService.partialUpdate(customJustificationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customJustificationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /custom-justifications} : get all the customJustifications.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customJustifications in body.
     */
    @GetMapping("")
    public List<CustomJustificationDTO> getAllCustomJustifications(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all CustomJustifications");
        return customJustificationService.findAll();
    }

    /**
     * {@code GET  /custom-justifications/:id} : get the "id" customJustification.
     *
     * @param id the id of the customJustificationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customJustificationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomJustificationDTO> getCustomJustification(@PathVariable("id") Long id) {
        log.debug("REST request to get CustomJustification : {}", id);
        Optional<CustomJustificationDTO> customJustificationDTO = customJustificationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customJustificationDTO);
    }

    /**
     * {@code DELETE  /custom-justifications/:id} : delete the "id" customJustification.
     *
     * @param id the id of the customJustificationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomJustification(@PathVariable("id") Long id) {
        log.debug("REST request to delete CustomJustification : {}", id);
        customJustificationService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
