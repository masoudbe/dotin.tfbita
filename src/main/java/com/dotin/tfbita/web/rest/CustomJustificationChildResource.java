package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.CustomJustificationChildRepository;
import com.dotin.tfbita.service.CustomJustificationChildService;
import com.dotin.tfbita.service.dto.CustomJustificationChildDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.CustomJustificationChild}.
 */
@RestController
@RequestMapping("/api/custom-justification-children")
public class CustomJustificationChildResource {

    private static final Logger log = LoggerFactory.getLogger(CustomJustificationChildResource.class);

    private static final String ENTITY_NAME = "customJustificationChild";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomJustificationChildService customJustificationChildService;

    private final CustomJustificationChildRepository customJustificationChildRepository;

    public CustomJustificationChildResource(
        CustomJustificationChildService customJustificationChildService,
        CustomJustificationChildRepository customJustificationChildRepository
    ) {
        this.customJustificationChildService = customJustificationChildService;
        this.customJustificationChildRepository = customJustificationChildRepository;
    }

    /**
     * {@code POST  /custom-justification-children} : Create a new customJustificationChild.
     *
     * @param customJustificationChildDTO the customJustificationChildDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customJustificationChildDTO, or with status {@code 400 (Bad Request)} if the customJustificationChild has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CustomJustificationChildDTO> createCustomJustificationChild(
        @RequestBody CustomJustificationChildDTO customJustificationChildDTO
    ) throws URISyntaxException {
        log.debug("REST request to save CustomJustificationChild : {}", customJustificationChildDTO);
        if (customJustificationChildDTO.getId() != null) {
            throw new BadRequestAlertException("A new customJustificationChild cannot already have an ID", ENTITY_NAME, "idexists");
        }
        customJustificationChildDTO = customJustificationChildService.save(customJustificationChildDTO);
        return ResponseEntity.created(new URI("/api/custom-justification-children/" + customJustificationChildDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, customJustificationChildDTO.getId().toString())
            )
            .body(customJustificationChildDTO);
    }

    /**
     * {@code PUT  /custom-justification-children/:id} : Updates an existing customJustificationChild.
     *
     * @param id the id of the customJustificationChildDTO to save.
     * @param customJustificationChildDTO the customJustificationChildDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customJustificationChildDTO,
     * or with status {@code 400 (Bad Request)} if the customJustificationChildDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customJustificationChildDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomJustificationChildDTO> updateCustomJustificationChild(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomJustificationChildDTO customJustificationChildDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CustomJustificationChild : {}, {}", id, customJustificationChildDTO);
        if (customJustificationChildDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customJustificationChildDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customJustificationChildRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        customJustificationChildDTO = customJustificationChildService.update(customJustificationChildDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customJustificationChildDTO.getId().toString()))
            .body(customJustificationChildDTO);
    }

    /**
     * {@code PATCH  /custom-justification-children/:id} : Partial updates given fields of an existing customJustificationChild, field will ignore if it is null
     *
     * @param id the id of the customJustificationChildDTO to save.
     * @param customJustificationChildDTO the customJustificationChildDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customJustificationChildDTO,
     * or with status {@code 400 (Bad Request)} if the customJustificationChildDTO is not valid,
     * or with status {@code 404 (Not Found)} if the customJustificationChildDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the customJustificationChildDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CustomJustificationChildDTO> partialUpdateCustomJustificationChild(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomJustificationChildDTO customJustificationChildDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CustomJustificationChild partially : {}, {}", id, customJustificationChildDTO);
        if (customJustificationChildDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customJustificationChildDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customJustificationChildRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CustomJustificationChildDTO> result = customJustificationChildService.partialUpdate(customJustificationChildDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customJustificationChildDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /custom-justification-children} : get all the customJustificationChildren.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customJustificationChildren in body.
     */
    @GetMapping("")
    public List<CustomJustificationChildDTO> getAllCustomJustificationChildren() {
        log.debug("REST request to get all CustomJustificationChildren");
        return customJustificationChildService.findAll();
    }

    /**
     * {@code GET  /custom-justification-children/:id} : get the "id" customJustificationChild.
     *
     * @param id the id of the customJustificationChildDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customJustificationChildDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomJustificationChildDTO> getCustomJustificationChild(@PathVariable("id") Long id) {
        log.debug("REST request to get CustomJustificationChild : {}", id);
        Optional<CustomJustificationChildDTO> customJustificationChildDTO = customJustificationChildService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customJustificationChildDTO);
    }

    /**
     * {@code DELETE  /custom-justification-children/:id} : delete the "id" customJustificationChild.
     *
     * @param id the id of the customJustificationChildDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomJustificationChild(@PathVariable("id") Long id) {
        log.debug("REST request to delete CustomJustificationChild : {}", id);
        customJustificationChildService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
