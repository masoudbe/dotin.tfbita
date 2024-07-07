package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.CreditTypeConditionInfoRepository;
import com.dotin.tfbita.service.CreditTypeConditionInfoService;
import com.dotin.tfbita.service.dto.CreditTypeConditionInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.CreditTypeConditionInfo}.
 */
@RestController
@RequestMapping("/api/credit-type-condition-infos")
public class CreditTypeConditionInfoResource {

    private static final Logger log = LoggerFactory.getLogger(CreditTypeConditionInfoResource.class);

    private static final String ENTITY_NAME = "creditTypeConditionInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CreditTypeConditionInfoService creditTypeConditionInfoService;

    private final CreditTypeConditionInfoRepository creditTypeConditionInfoRepository;

    public CreditTypeConditionInfoResource(
        CreditTypeConditionInfoService creditTypeConditionInfoService,
        CreditTypeConditionInfoRepository creditTypeConditionInfoRepository
    ) {
        this.creditTypeConditionInfoService = creditTypeConditionInfoService;
        this.creditTypeConditionInfoRepository = creditTypeConditionInfoRepository;
    }

    /**
     * {@code POST  /credit-type-condition-infos} : Create a new creditTypeConditionInfo.
     *
     * @param creditTypeConditionInfoDTO the creditTypeConditionInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new creditTypeConditionInfoDTO, or with status {@code 400 (Bad Request)} if the creditTypeConditionInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CreditTypeConditionInfoDTO> createCreditTypeConditionInfo(
        @RequestBody CreditTypeConditionInfoDTO creditTypeConditionInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to save CreditTypeConditionInfo : {}", creditTypeConditionInfoDTO);
        if (creditTypeConditionInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new creditTypeConditionInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        creditTypeConditionInfoDTO = creditTypeConditionInfoService.save(creditTypeConditionInfoDTO);
        return ResponseEntity.created(new URI("/api/credit-type-condition-infos/" + creditTypeConditionInfoDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, creditTypeConditionInfoDTO.getId().toString())
            )
            .body(creditTypeConditionInfoDTO);
    }

    /**
     * {@code PUT  /credit-type-condition-infos/:id} : Updates an existing creditTypeConditionInfo.
     *
     * @param id the id of the creditTypeConditionInfoDTO to save.
     * @param creditTypeConditionInfoDTO the creditTypeConditionInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditTypeConditionInfoDTO,
     * or with status {@code 400 (Bad Request)} if the creditTypeConditionInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the creditTypeConditionInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CreditTypeConditionInfoDTO> updateCreditTypeConditionInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreditTypeConditionInfoDTO creditTypeConditionInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CreditTypeConditionInfo : {}, {}", id, creditTypeConditionInfoDTO);
        if (creditTypeConditionInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, creditTypeConditionInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!creditTypeConditionInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        creditTypeConditionInfoDTO = creditTypeConditionInfoService.update(creditTypeConditionInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, creditTypeConditionInfoDTO.getId().toString()))
            .body(creditTypeConditionInfoDTO);
    }

    /**
     * {@code PATCH  /credit-type-condition-infos/:id} : Partial updates given fields of an existing creditTypeConditionInfo, field will ignore if it is null
     *
     * @param id the id of the creditTypeConditionInfoDTO to save.
     * @param creditTypeConditionInfoDTO the creditTypeConditionInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated creditTypeConditionInfoDTO,
     * or with status {@code 400 (Bad Request)} if the creditTypeConditionInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the creditTypeConditionInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the creditTypeConditionInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CreditTypeConditionInfoDTO> partialUpdateCreditTypeConditionInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CreditTypeConditionInfoDTO creditTypeConditionInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CreditTypeConditionInfo partially : {}, {}", id, creditTypeConditionInfoDTO);
        if (creditTypeConditionInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, creditTypeConditionInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!creditTypeConditionInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CreditTypeConditionInfoDTO> result = creditTypeConditionInfoService.partialUpdate(creditTypeConditionInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, creditTypeConditionInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /credit-type-condition-infos} : get all the creditTypeConditionInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of creditTypeConditionInfos in body.
     */
    @GetMapping("")
    public List<CreditTypeConditionInfoDTO> getAllCreditTypeConditionInfos() {
        log.debug("REST request to get all CreditTypeConditionInfos");
        return creditTypeConditionInfoService.findAll();
    }

    /**
     * {@code GET  /credit-type-condition-infos/:id} : get the "id" creditTypeConditionInfo.
     *
     * @param id the id of the creditTypeConditionInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the creditTypeConditionInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CreditTypeConditionInfoDTO> getCreditTypeConditionInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get CreditTypeConditionInfo : {}", id);
        Optional<CreditTypeConditionInfoDTO> creditTypeConditionInfoDTO = creditTypeConditionInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(creditTypeConditionInfoDTO);
    }

    /**
     * {@code DELETE  /credit-type-condition-infos/:id} : delete the "id" creditTypeConditionInfo.
     *
     * @param id the id of the creditTypeConditionInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCreditTypeConditionInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete CreditTypeConditionInfo : {}", id);
        creditTypeConditionInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
