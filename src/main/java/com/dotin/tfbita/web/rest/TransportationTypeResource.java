package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.TransportationTypeRepository;
import com.dotin.tfbita.service.TransportationTypeService;
import com.dotin.tfbita.service.dto.TransportationTypeDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.TransportationType}.
 */
@RestController
@RequestMapping("/api/transportation-types")
public class TransportationTypeResource {

    private static final Logger log = LoggerFactory.getLogger(TransportationTypeResource.class);

    private static final String ENTITY_NAME = "transportationType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransportationTypeService transportationTypeService;

    private final TransportationTypeRepository transportationTypeRepository;

    public TransportationTypeResource(
        TransportationTypeService transportationTypeService,
        TransportationTypeRepository transportationTypeRepository
    ) {
        this.transportationTypeService = transportationTypeService;
        this.transportationTypeRepository = transportationTypeRepository;
    }

    /**
     * {@code POST  /transportation-types} : Create a new transportationType.
     *
     * @param transportationTypeDTO the transportationTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transportationTypeDTO, or with status {@code 400 (Bad Request)} if the transportationType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TransportationTypeDTO> createTransportationType(@RequestBody TransportationTypeDTO transportationTypeDTO)
        throws URISyntaxException {
        log.debug("REST request to save TransportationType : {}", transportationTypeDTO);
        if (transportationTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new transportationType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        transportationTypeDTO = transportationTypeService.save(transportationTypeDTO);
        return ResponseEntity.created(new URI("/api/transportation-types/" + transportationTypeDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, transportationTypeDTO.getId().toString()))
            .body(transportationTypeDTO);
    }

    /**
     * {@code PUT  /transportation-types/:id} : Updates an existing transportationType.
     *
     * @param id the id of the transportationTypeDTO to save.
     * @param transportationTypeDTO the transportationTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transportationTypeDTO,
     * or with status {@code 400 (Bad Request)} if the transportationTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transportationTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TransportationTypeDTO> updateTransportationType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TransportationTypeDTO transportationTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TransportationType : {}, {}", id, transportationTypeDTO);
        if (transportationTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transportationTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transportationTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        transportationTypeDTO = transportationTypeService.update(transportationTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transportationTypeDTO.getId().toString()))
            .body(transportationTypeDTO);
    }

    /**
     * {@code PATCH  /transportation-types/:id} : Partial updates given fields of an existing transportationType, field will ignore if it is null
     *
     * @param id the id of the transportationTypeDTO to save.
     * @param transportationTypeDTO the transportationTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transportationTypeDTO,
     * or with status {@code 400 (Bad Request)} if the transportationTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the transportationTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the transportationTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TransportationTypeDTO> partialUpdateTransportationType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TransportationTypeDTO transportationTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TransportationType partially : {}, {}", id, transportationTypeDTO);
        if (transportationTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transportationTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transportationTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TransportationTypeDTO> result = transportationTypeService.partialUpdate(transportationTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transportationTypeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /transportation-types} : get all the transportationTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transportationTypes in body.
     */
    @GetMapping("")
    public List<TransportationTypeDTO> getAllTransportationTypes() {
        log.debug("REST request to get all TransportationTypes");
        return transportationTypeService.findAll();
    }

    /**
     * {@code GET  /transportation-types/:id} : get the "id" transportationType.
     *
     * @param id the id of the transportationTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transportationTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TransportationTypeDTO> getTransportationType(@PathVariable("id") Long id) {
        log.debug("REST request to get TransportationType : {}", id);
        Optional<TransportationTypeDTO> transportationTypeDTO = transportationTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transportationTypeDTO);
    }

    /**
     * {@code DELETE  /transportation-types/:id} : delete the "id" transportationType.
     *
     * @param id the id of the transportationTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransportationType(@PathVariable("id") Long id) {
        log.debug("REST request to delete TransportationType : {}", id);
        transportationTypeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
