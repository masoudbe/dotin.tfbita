package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.AdvisorDefinitionDepositRepository;
import com.dotin.tfbita.service.AdvisorDefinitionDepositService;
import com.dotin.tfbita.service.dto.AdvisorDefinitionDepositDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.AdvisorDefinitionDeposit}.
 */
@RestController
@RequestMapping("/api/advisor-definition-deposits")
public class AdvisorDefinitionDepositResource {

    private static final Logger log = LoggerFactory.getLogger(AdvisorDefinitionDepositResource.class);

    private static final String ENTITY_NAME = "advisorDefinitionDeposit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AdvisorDefinitionDepositService advisorDefinitionDepositService;

    private final AdvisorDefinitionDepositRepository advisorDefinitionDepositRepository;

    public AdvisorDefinitionDepositResource(
        AdvisorDefinitionDepositService advisorDefinitionDepositService,
        AdvisorDefinitionDepositRepository advisorDefinitionDepositRepository
    ) {
        this.advisorDefinitionDepositService = advisorDefinitionDepositService;
        this.advisorDefinitionDepositRepository = advisorDefinitionDepositRepository;
    }

    /**
     * {@code POST  /advisor-definition-deposits} : Create a new advisorDefinitionDeposit.
     *
     * @param advisorDefinitionDepositDTO the advisorDefinitionDepositDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new advisorDefinitionDepositDTO, or with status {@code 400 (Bad Request)} if the advisorDefinitionDeposit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AdvisorDefinitionDepositDTO> createAdvisorDefinitionDeposit(
        @RequestBody AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO
    ) throws URISyntaxException {
        log.debug("REST request to save AdvisorDefinitionDeposit : {}", advisorDefinitionDepositDTO);
        if (advisorDefinitionDepositDTO.getId() != null) {
            throw new BadRequestAlertException("A new advisorDefinitionDeposit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        advisorDefinitionDepositDTO = advisorDefinitionDepositService.save(advisorDefinitionDepositDTO);
        return ResponseEntity.created(new URI("/api/advisor-definition-deposits/" + advisorDefinitionDepositDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, advisorDefinitionDepositDTO.getId().toString())
            )
            .body(advisorDefinitionDepositDTO);
    }

    /**
     * {@code PUT  /advisor-definition-deposits/:id} : Updates an existing advisorDefinitionDeposit.
     *
     * @param id the id of the advisorDefinitionDepositDTO to save.
     * @param advisorDefinitionDepositDTO the advisorDefinitionDepositDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated advisorDefinitionDepositDTO,
     * or with status {@code 400 (Bad Request)} if the advisorDefinitionDepositDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the advisorDefinitionDepositDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AdvisorDefinitionDepositDTO> updateAdvisorDefinitionDeposit(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AdvisorDefinitionDeposit : {}, {}", id, advisorDefinitionDepositDTO);
        if (advisorDefinitionDepositDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, advisorDefinitionDepositDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!advisorDefinitionDepositRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        advisorDefinitionDepositDTO = advisorDefinitionDepositService.update(advisorDefinitionDepositDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, advisorDefinitionDepositDTO.getId().toString()))
            .body(advisorDefinitionDepositDTO);
    }

    /**
     * {@code PATCH  /advisor-definition-deposits/:id} : Partial updates given fields of an existing advisorDefinitionDeposit, field will ignore if it is null
     *
     * @param id the id of the advisorDefinitionDepositDTO to save.
     * @param advisorDefinitionDepositDTO the advisorDefinitionDepositDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated advisorDefinitionDepositDTO,
     * or with status {@code 400 (Bad Request)} if the advisorDefinitionDepositDTO is not valid,
     * or with status {@code 404 (Not Found)} if the advisorDefinitionDepositDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the advisorDefinitionDepositDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AdvisorDefinitionDepositDTO> partialUpdateAdvisorDefinitionDeposit(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AdvisorDefinitionDepositDTO advisorDefinitionDepositDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AdvisorDefinitionDeposit partially : {}, {}", id, advisorDefinitionDepositDTO);
        if (advisorDefinitionDepositDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, advisorDefinitionDepositDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!advisorDefinitionDepositRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AdvisorDefinitionDepositDTO> result = advisorDefinitionDepositService.partialUpdate(advisorDefinitionDepositDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, advisorDefinitionDepositDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /advisor-definition-deposits} : get all the advisorDefinitionDeposits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of advisorDefinitionDeposits in body.
     */
    @GetMapping("")
    public List<AdvisorDefinitionDepositDTO> getAllAdvisorDefinitionDeposits() {
        log.debug("REST request to get all AdvisorDefinitionDeposits");
        return advisorDefinitionDepositService.findAll();
    }

    /**
     * {@code GET  /advisor-definition-deposits/:id} : get the "id" advisorDefinitionDeposit.
     *
     * @param id the id of the advisorDefinitionDepositDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the advisorDefinitionDepositDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AdvisorDefinitionDepositDTO> getAdvisorDefinitionDeposit(@PathVariable("id") Long id) {
        log.debug("REST request to get AdvisorDefinitionDeposit : {}", id);
        Optional<AdvisorDefinitionDepositDTO> advisorDefinitionDepositDTO = advisorDefinitionDepositService.findOne(id);
        return ResponseUtil.wrapOrNotFound(advisorDefinitionDepositDTO);
    }

    /**
     * {@code DELETE  /advisor-definition-deposits/:id} : delete the "id" advisorDefinitionDeposit.
     *
     * @param id the id of the advisorDefinitionDepositDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdvisorDefinitionDeposit(@PathVariable("id") Long id) {
        log.debug("REST request to delete AdvisorDefinitionDeposit : {}", id);
        advisorDefinitionDepositService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
