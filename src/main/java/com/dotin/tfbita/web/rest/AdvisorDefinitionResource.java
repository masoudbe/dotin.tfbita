package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.AdvisorDefinitionRepository;
import com.dotin.tfbita.service.AdvisorDefinitionService;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.AdvisorDefinition}.
 */
@RestController
@RequestMapping("/api/advisor-definitions")
public class AdvisorDefinitionResource {

    private static final Logger log = LoggerFactory.getLogger(AdvisorDefinitionResource.class);

    private static final String ENTITY_NAME = "advisorDefinition";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdvisorDefinitionService advisorDefinitionService;

    private final AdvisorDefinitionRepository advisorDefinitionRepository;

    public AdvisorDefinitionResource(
        AdvisorDefinitionService advisorDefinitionService,
        AdvisorDefinitionRepository advisorDefinitionRepository
    ) {
        this.advisorDefinitionService = advisorDefinitionService;
        this.advisorDefinitionRepository = advisorDefinitionRepository;
    }

    /**
     * {@code POST  /advisor-definitions} : Create a new advisorDefinition.
     *
     * @param advisorDefinitionDTO the advisorDefinitionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new advisorDefinitionDTO, or with status {@code 400 (Bad Request)} if the advisorDefinition has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AdvisorDefinitionDTO> createAdvisorDefinition(@RequestBody AdvisorDefinitionDTO advisorDefinitionDTO)
        throws URISyntaxException {
        log.debug("REST request to save AdvisorDefinition : {}", advisorDefinitionDTO);
        if (advisorDefinitionDTO.getId() != null) {
            throw new BadRequestAlertException("A new advisorDefinition cannot already have an ID", ENTITY_NAME, "idexists");
        }
        advisorDefinitionDTO = advisorDefinitionService.save(advisorDefinitionDTO);
        return ResponseEntity.created(new URI("/api/advisor-definitions/" + advisorDefinitionDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, advisorDefinitionDTO.getId().toString()))
            .body(advisorDefinitionDTO);
    }

    /**
     * {@code PUT  /advisor-definitions/:id} : Updates an existing advisorDefinition.
     *
     * @param id the id of the advisorDefinitionDTO to save.
     * @param advisorDefinitionDTO the advisorDefinitionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated advisorDefinitionDTO,
     * or with status {@code 400 (Bad Request)} if the advisorDefinitionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the advisorDefinitionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AdvisorDefinitionDTO> updateAdvisorDefinition(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AdvisorDefinitionDTO advisorDefinitionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AdvisorDefinition : {}, {}", id, advisorDefinitionDTO);
        if (advisorDefinitionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, advisorDefinitionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!advisorDefinitionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        advisorDefinitionDTO = advisorDefinitionService.update(advisorDefinitionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, advisorDefinitionDTO.getId().toString()))
            .body(advisorDefinitionDTO);
    }

    /**
     * {@code PATCH  /advisor-definitions/:id} : Partial updates given fields of an existing advisorDefinition, field will ignore if it is null
     *
     * @param id the id of the advisorDefinitionDTO to save.
     * @param advisorDefinitionDTO the advisorDefinitionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated advisorDefinitionDTO,
     * or with status {@code 400 (Bad Request)} if the advisorDefinitionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the advisorDefinitionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the advisorDefinitionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AdvisorDefinitionDTO> partialUpdateAdvisorDefinition(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AdvisorDefinitionDTO advisorDefinitionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdvisorDefinition partially : {}, {}", id, advisorDefinitionDTO);
        if (advisorDefinitionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, advisorDefinitionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!advisorDefinitionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdvisorDefinitionDTO> result = advisorDefinitionService.partialUpdate(advisorDefinitionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, advisorDefinitionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /advisor-definitions} : get all the advisorDefinitions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of advisorDefinitions in body.
     */
    @GetMapping("")
    public List<AdvisorDefinitionDTO> getAllAdvisorDefinitions() {
        log.debug("REST request to get all AdvisorDefinitions");
        return advisorDefinitionService.findAll();
    }

    /**
     * {@code GET  /advisor-definitions/:id} : get the "id" advisorDefinition.
     *
     * @param id the id of the advisorDefinitionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the advisorDefinitionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AdvisorDefinitionDTO> getAdvisorDefinition(@PathVariable("id") Long id) {
        log.debug("REST request to get AdvisorDefinition : {}", id);
        Optional<AdvisorDefinitionDTO> advisorDefinitionDTO = advisorDefinitionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(advisorDefinitionDTO);
    }

    /**
     * {@code DELETE  /advisor-definitions/:id} : delete the "id" advisorDefinition.
     *
     * @param id the id of the advisorDefinitionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvisorDefinition(@PathVariable("id") Long id) {
        log.debug("REST request to delete AdvisorDefinition : {}", id);
        advisorDefinitionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
