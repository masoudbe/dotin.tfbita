package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.LongValueRepository;
import com.dotin.tfbita.service.LongValueService;
import com.dotin.tfbita.service.dto.LongValueDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.LongValue}.
 */
@RestController
@RequestMapping("/api/long-values")
public class LongValueResource {

    private final Logger log = LoggerFactory.getLogger(LongValueResource.class);

    private static final String ENTITY_NAME = "longValue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LongValueService longValueService;

    private final LongValueRepository longValueRepository;

    public LongValueResource(LongValueService longValueService, LongValueRepository longValueRepository) {
        this.longValueService = longValueService;
        this.longValueRepository = longValueRepository;
    }

    /**
     * {@code POST  /long-values} : Create a new longValue.
     *
     * @param longValueDTO the longValueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new longValueDTO, or with status {@code 400 (Bad Request)} if the longValue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LongValueDTO> createLongValue(@RequestBody LongValueDTO longValueDTO) throws URISyntaxException {
        log.debug("REST request to save LongValue : {}", longValueDTO);
        if (longValueDTO.getId() != null) {
            throw new BadRequestAlertException("A new longValue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        longValueDTO = longValueService.save(longValueDTO);
        return ResponseEntity.created(new URI("/api/long-values/" + longValueDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, longValueDTO.getId().toString()))
            .body(longValueDTO);
    }

    /**
     * {@code PUT  /long-values/:id} : Updates an existing longValue.
     *
     * @param id the id of the longValueDTO to save.
     * @param longValueDTO the longValueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated longValueDTO,
     * or with status {@code 400 (Bad Request)} if the longValueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the longValueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LongValueDTO> updateLongValue(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LongValueDTO longValueDTO
    ) throws URISyntaxException {
        log.debug("REST request to update LongValue : {}, {}", id, longValueDTO);
        if (longValueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, longValueDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!longValueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        longValueDTO = longValueService.update(longValueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, longValueDTO.getId().toString()))
            .body(longValueDTO);
    }

    /**
     * {@code PATCH  /long-values/:id} : Partial updates given fields of an existing longValue, field will ignore if it is null
     *
     * @param id the id of the longValueDTO to save.
     * @param longValueDTO the longValueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated longValueDTO,
     * or with status {@code 400 (Bad Request)} if the longValueDTO is not valid,
     * or with status {@code 404 (Not Found)} if the longValueDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the longValueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LongValueDTO> partialUpdateLongValue(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LongValueDTO longValueDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update LongValue partially : {}, {}", id, longValueDTO);
        if (longValueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, longValueDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!longValueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LongValueDTO> result = longValueService.partialUpdate(longValueDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, longValueDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /long-values} : get all the longValues.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of longValues in body.
     */
    @GetMapping("")
    public List<LongValueDTO> getAllLongValues() {
        log.debug("REST request to get all LongValues");
        return longValueService.findAll();
    }

    /**
     * {@code GET  /long-values/:id} : get the "id" longValue.
     *
     * @param id the id of the longValueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the longValueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LongValueDTO> getLongValue(@PathVariable("id") Long id) {
        log.debug("REST request to get LongValue : {}", id);
        Optional<LongValueDTO> longValueDTO = longValueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(longValueDTO);
    }

    /**
     * {@code DELETE  /long-values/:id} : delete the "id" longValue.
     *
     * @param id the id of the longValueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLongValue(@PathVariable("id") Long id) {
        log.debug("REST request to delete LongValue : {}", id);
        longValueService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
