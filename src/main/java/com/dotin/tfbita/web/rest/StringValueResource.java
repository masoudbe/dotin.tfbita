package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.StringValueRepository;
import com.dotin.tfbita.service.StringValueService;
import com.dotin.tfbita.service.dto.StringValueDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.StringValue}.
 */
@RestController
@RequestMapping("/api/string-values")
public class StringValueResource {

    private static final Logger log = LoggerFactory.getLogger(StringValueResource.class);

    private static final String ENTITY_NAME = "stringValue";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final StringValueService stringValueService;

    private final StringValueRepository stringValueRepository;

    public StringValueResource(StringValueService stringValueService, StringValueRepository stringValueRepository) {
        this.stringValueService = stringValueService;
        this.stringValueRepository = stringValueRepository;
    }

    /**
     * {@code POST  /string-values} : Create a new stringValue.
     *
     * @param stringValueDTO the stringValueDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new stringValueDTO, or with status {@code 400 (Bad Request)} if the stringValue has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<StringValueDTO> createStringValue(@RequestBody StringValueDTO stringValueDTO) throws URISyntaxException {
        log.debug("REST request to save StringValue : {}", stringValueDTO);
        if (stringValueDTO.getId() != null) {
            throw new BadRequestAlertException("A new stringValue cannot already have an ID", ENTITY_NAME, "idexists");
        }
        stringValueDTO = stringValueService.save(stringValueDTO);
        return ResponseEntity.created(new URI("/api/string-values/" + stringValueDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, stringValueDTO.getId().toString()))
            .body(stringValueDTO);
    }

    /**
     * {@code PUT  /string-values/:id} : Updates an existing stringValue.
     *
     * @param id the id of the stringValueDTO to save.
     * @param stringValueDTO the stringValueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stringValueDTO,
     * or with status {@code 400 (Bad Request)} if the stringValueDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the stringValueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<StringValueDTO> updateStringValue(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StringValueDTO stringValueDTO
    ) throws URISyntaxException {
        log.debug("REST request to update StringValue : {}, {}", id, stringValueDTO);
        if (stringValueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stringValueDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stringValueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        stringValueDTO = stringValueService.update(stringValueDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, stringValueDTO.getId().toString()))
            .body(stringValueDTO);
    }

    /**
     * {@code PATCH  /string-values/:id} : Partial updates given fields of an existing stringValue, field will ignore if it is null
     *
     * @param id the id of the stringValueDTO to save.
     * @param stringValueDTO the stringValueDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated stringValueDTO,
     * or with status {@code 400 (Bad Request)} if the stringValueDTO is not valid,
     * or with status {@code 404 (Not Found)} if the stringValueDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the stringValueDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<StringValueDTO> partialUpdateStringValue(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody StringValueDTO stringValueDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update StringValue partially : {}, {}", id, stringValueDTO);
        if (stringValueDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, stringValueDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!stringValueRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<StringValueDTO> result = stringValueService.partialUpdate(stringValueDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, stringValueDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /string-values} : get all the stringValues.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of stringValues in body.
     */
    @GetMapping("")
    public List<StringValueDTO> getAllStringValues() {
        log.debug("REST request to get all StringValues");
        return stringValueService.findAll();
    }

    /**
     * {@code GET  /string-values/:id} : get the "id" stringValue.
     *
     * @param id the id of the stringValueDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the stringValueDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<StringValueDTO> getStringValue(@PathVariable("id") Long id) {
        log.debug("REST request to get StringValue : {}", id);
        Optional<StringValueDTO> stringValueDTO = stringValueService.findOne(id);
        return ResponseUtil.wrapOrNotFound(stringValueDTO);
    }

    /**
     * {@code DELETE  /string-values/:id} : delete the "id" stringValue.
     *
     * @param id the id of the stringValueDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStringValue(@PathVariable("id") Long id) {
        log.debug("REST request to delete StringValue : {}", id);
        stringValueService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
