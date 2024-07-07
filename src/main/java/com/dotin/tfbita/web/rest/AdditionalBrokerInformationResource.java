package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.AdditionalBrokerInformationRepository;
import com.dotin.tfbita.service.AdditionalBrokerInformationService;
import com.dotin.tfbita.service.dto.AdditionalBrokerInformationDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.AdditionalBrokerInformation}.
 */
@RestController
@RequestMapping("/api/additional-broker-informations")
public class AdditionalBrokerInformationResource {

    private static final Logger log = LoggerFactory.getLogger(AdditionalBrokerInformationResource.class);

    private static final String ENTITY_NAME = "additionalBrokerInformation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdditionalBrokerInformationService additionalBrokerInformationService;

    private final AdditionalBrokerInformationRepository additionalBrokerInformationRepository;

    public AdditionalBrokerInformationResource(
        AdditionalBrokerInformationService additionalBrokerInformationService,
        AdditionalBrokerInformationRepository additionalBrokerInformationRepository
    ) {
        this.additionalBrokerInformationService = additionalBrokerInformationService;
        this.additionalBrokerInformationRepository = additionalBrokerInformationRepository;
    }

    /**
     * {@code POST  /additional-broker-informations} : Create a new additionalBrokerInformation.
     *
     * @param additionalBrokerInformationDTO the additionalBrokerInformationDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new additionalBrokerInformationDTO, or with status {@code 400 (Bad Request)} if the additionalBrokerInformation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AdditionalBrokerInformationDTO> createAdditionalBrokerInformation(
        @RequestBody AdditionalBrokerInformationDTO additionalBrokerInformationDTO
    ) throws URISyntaxException {
        log.debug("REST request to save AdditionalBrokerInformation : {}", additionalBrokerInformationDTO);
        if (additionalBrokerInformationDTO.getId() != null) {
            throw new BadRequestAlertException("A new additionalBrokerInformation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        additionalBrokerInformationDTO = additionalBrokerInformationService.save(additionalBrokerInformationDTO);
        return ResponseEntity.created(new URI("/api/additional-broker-informations/" + additionalBrokerInformationDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, additionalBrokerInformationDTO.getId().toString())
            )
            .body(additionalBrokerInformationDTO);
    }

    /**
     * {@code PUT  /additional-broker-informations/:id} : Updates an existing additionalBrokerInformation.
     *
     * @param id the id of the additionalBrokerInformationDTO to save.
     * @param additionalBrokerInformationDTO the additionalBrokerInformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated additionalBrokerInformationDTO,
     * or with status {@code 400 (Bad Request)} if the additionalBrokerInformationDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the additionalBrokerInformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AdditionalBrokerInformationDTO> updateAdditionalBrokerInformation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AdditionalBrokerInformationDTO additionalBrokerInformationDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AdditionalBrokerInformation : {}, {}", id, additionalBrokerInformationDTO);
        if (additionalBrokerInformationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, additionalBrokerInformationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!additionalBrokerInformationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        additionalBrokerInformationDTO = additionalBrokerInformationService.update(additionalBrokerInformationDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, additionalBrokerInformationDTO.getId().toString())
            )
            .body(additionalBrokerInformationDTO);
    }

    /**
     * {@code PATCH  /additional-broker-informations/:id} : Partial updates given fields of an existing additionalBrokerInformation, field will ignore if it is null
     *
     * @param id the id of the additionalBrokerInformationDTO to save.
     * @param additionalBrokerInformationDTO the additionalBrokerInformationDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated additionalBrokerInformationDTO,
     * or with status {@code 400 (Bad Request)} if the additionalBrokerInformationDTO is not valid,
     * or with status {@code 404 (Not Found)} if the additionalBrokerInformationDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the additionalBrokerInformationDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AdditionalBrokerInformationDTO> partialUpdateAdditionalBrokerInformation(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AdditionalBrokerInformationDTO additionalBrokerInformationDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdditionalBrokerInformation partially : {}, {}", id, additionalBrokerInformationDTO);
        if (additionalBrokerInformationDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, additionalBrokerInformationDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!additionalBrokerInformationRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdditionalBrokerInformationDTO> result = additionalBrokerInformationService.partialUpdate(additionalBrokerInformationDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, additionalBrokerInformationDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /additional-broker-informations} : get all the additionalBrokerInformations.
     *
     * @param filter the filter of the request.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of additionalBrokerInformations in body.
     */
    @GetMapping("")
    public List<AdditionalBrokerInformationDTO> getAllAdditionalBrokerInformations(
        @RequestParam(name = "filter", required = false) String filter
    ) {
        if ("advisordefinition-is-null".equals(filter)) {
            log.debug("REST request to get all AdditionalBrokerInformations where advisorDefinition is null");
            return additionalBrokerInformationService.findAllWhereAdvisorDefinitionIsNull();
        }
        log.debug("REST request to get all AdditionalBrokerInformations");
        return additionalBrokerInformationService.findAll();
    }

    /**
     * {@code GET  /additional-broker-informations/:id} : get the "id" additionalBrokerInformation.
     *
     * @param id the id of the additionalBrokerInformationDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the additionalBrokerInformationDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AdditionalBrokerInformationDTO> getAdditionalBrokerInformation(@PathVariable("id") Long id) {
        log.debug("REST request to get AdditionalBrokerInformation : {}", id);
        Optional<AdditionalBrokerInformationDTO> additionalBrokerInformationDTO = additionalBrokerInformationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(additionalBrokerInformationDTO);
    }

    /**
     * {@code DELETE  /additional-broker-informations/:id} : delete the "id" additionalBrokerInformation.
     *
     * @param id the id of the additionalBrokerInformationDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdditionalBrokerInformation(@PathVariable("id") Long id) {
        log.debug("REST request to delete AdditionalBrokerInformation : {}", id);
        additionalBrokerInformationService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
