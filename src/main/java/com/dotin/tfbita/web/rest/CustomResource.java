package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.CustomRepository;
import com.dotin.tfbita.service.CustomService;
import com.dotin.tfbita.service.dto.CustomDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.Custom}.
 */
@RestController
@RequestMapping("/api/customs")
public class CustomResource {

    private final Logger log = LoggerFactory.getLogger(CustomResource.class);

    private static final String ENTITY_NAME = "custom";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomService customService;

    private final CustomRepository customRepository;

    public CustomResource(CustomService customService, CustomRepository customRepository) {
        this.customService = customService;
        this.customRepository = customRepository;
    }

    /**
     * {@code POST  /customs} : Create a new custom.
     *
     * @param customDTO the customDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customDTO, or with status {@code 400 (Bad Request)} if the custom has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CustomDTO> createCustom(@RequestBody CustomDTO customDTO) throws URISyntaxException {
        log.debug("REST request to save Custom : {}", customDTO);
        if (customDTO.getId() != null) {
            throw new BadRequestAlertException("A new custom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        customDTO = customService.save(customDTO);
        return ResponseEntity.created(new URI("/api/customs/" + customDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, customDTO.getId().toString()))
            .body(customDTO);
    }

    /**
     * {@code PUT  /customs/:id} : Updates an existing custom.
     *
     * @param id the id of the customDTO to save.
     * @param customDTO the customDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customDTO,
     * or with status {@code 400 (Bad Request)} if the customDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CustomDTO> updateCustom(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomDTO customDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Custom : {}, {}", id, customDTO);
        if (customDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        customDTO = customService.update(customDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customDTO.getId().toString()))
            .body(customDTO);
    }

    /**
     * {@code PATCH  /customs/:id} : Partial updates given fields of an existing custom, field will ignore if it is null
     *
     * @param id the id of the customDTO to save.
     * @param customDTO the customDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customDTO,
     * or with status {@code 400 (Bad Request)} if the customDTO is not valid,
     * or with status {@code 404 (Not Found)} if the customDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the customDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CustomDTO> partialUpdateCustom(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CustomDTO customDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Custom partially : {}, {}", id, customDTO);
        if (customDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CustomDTO> result = customService.partialUpdate(customDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, customDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /customs} : get all the customs.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customs in body.
     */
    @GetMapping("")
    public List<CustomDTO> getAllCustoms(@RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload) {
        log.debug("REST request to get all Customs");
        return customService.findAll();
    }

    /**
     * {@code GET  /customs/:id} : get the "id" custom.
     *
     * @param id the id of the customDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomDTO> getCustom(@PathVariable("id") Long id) {
        log.debug("REST request to get Custom : {}", id);
        Optional<CustomDTO> customDTO = customService.findOne(id);
        return ResponseUtil.wrapOrNotFound(customDTO);
    }

    /**
     * {@code DELETE  /customs/:id} : delete the "id" custom.
     *
     * @param id the id of the customDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustom(@PathVariable("id") Long id) {
        log.debug("REST request to delete Custom : {}", id);
        customService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
