package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.AuditCompanyInfoRepository;
import com.dotin.tfbita.service.AuditCompanyInfoService;
import com.dotin.tfbita.service.dto.AuditCompanyInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.AuditCompanyInfo}.
 */
@RestController
@RequestMapping("/api/audit-company-infos")
public class AuditCompanyInfoResource {

    private static final Logger log = LoggerFactory.getLogger(AuditCompanyInfoResource.class);

    private static final String ENTITY_NAME = "auditCompanyInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AuditCompanyInfoService auditCompanyInfoService;

    private final AuditCompanyInfoRepository auditCompanyInfoRepository;

    public AuditCompanyInfoResource(
        AuditCompanyInfoService auditCompanyInfoService,
        AuditCompanyInfoRepository auditCompanyInfoRepository
    ) {
        this.auditCompanyInfoService = auditCompanyInfoService;
        this.auditCompanyInfoRepository = auditCompanyInfoRepository;
    }

    /**
     * {@code POST  /audit-company-infos} : Create a new auditCompanyInfo.
     *
     * @param auditCompanyInfoDTO the auditCompanyInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new auditCompanyInfoDTO, or with status {@code 400 (Bad Request)} if the auditCompanyInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AuditCompanyInfoDTO> createAuditCompanyInfo(@RequestBody AuditCompanyInfoDTO auditCompanyInfoDTO)
        throws URISyntaxException {
        log.debug("REST request to save AuditCompanyInfo : {}", auditCompanyInfoDTO);
        if (auditCompanyInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new auditCompanyInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        auditCompanyInfoDTO = auditCompanyInfoService.save(auditCompanyInfoDTO);
        return ResponseEntity.created(new URI("/api/audit-company-infos/" + auditCompanyInfoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, auditCompanyInfoDTO.getId().toString()))
            .body(auditCompanyInfoDTO);
    }

    /**
     * {@code PUT  /audit-company-infos/:id} : Updates an existing auditCompanyInfo.
     *
     * @param id the id of the auditCompanyInfoDTO to save.
     * @param auditCompanyInfoDTO the auditCompanyInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditCompanyInfoDTO,
     * or with status {@code 400 (Bad Request)} if the auditCompanyInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the auditCompanyInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AuditCompanyInfoDTO> updateAuditCompanyInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AuditCompanyInfoDTO auditCompanyInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AuditCompanyInfo : {}, {}", id, auditCompanyInfoDTO);
        if (auditCompanyInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, auditCompanyInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!auditCompanyInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        auditCompanyInfoDTO = auditCompanyInfoService.update(auditCompanyInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, auditCompanyInfoDTO.getId().toString()))
            .body(auditCompanyInfoDTO);
    }

    /**
     * {@code PATCH  /audit-company-infos/:id} : Partial updates given fields of an existing auditCompanyInfo, field will ignore if it is null
     *
     * @param id the id of the auditCompanyInfoDTO to save.
     * @param auditCompanyInfoDTO the auditCompanyInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated auditCompanyInfoDTO,
     * or with status {@code 400 (Bad Request)} if the auditCompanyInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the auditCompanyInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the auditCompanyInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AuditCompanyInfoDTO> partialUpdateAuditCompanyInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AuditCompanyInfoDTO auditCompanyInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AuditCompanyInfo partially : {}, {}", id, auditCompanyInfoDTO);
        if (auditCompanyInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, auditCompanyInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!auditCompanyInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AuditCompanyInfoDTO> result = auditCompanyInfoService.partialUpdate(auditCompanyInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, auditCompanyInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /audit-company-infos} : get all the auditCompanyInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of auditCompanyInfos in body.
     */
    @GetMapping("")
    public List<AuditCompanyInfoDTO> getAllAuditCompanyInfos() {
        log.debug("REST request to get all AuditCompanyInfos");
        return auditCompanyInfoService.findAll();
    }

    /**
     * {@code GET  /audit-company-infos/:id} : get the "id" auditCompanyInfo.
     *
     * @param id the id of the auditCompanyInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the auditCompanyInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AuditCompanyInfoDTO> getAuditCompanyInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get AuditCompanyInfo : {}", id);
        Optional<AuditCompanyInfoDTO> auditCompanyInfoDTO = auditCompanyInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(auditCompanyInfoDTO);
    }

    /**
     * {@code DELETE  /audit-company-infos/:id} : delete the "id" auditCompanyInfo.
     *
     * @param id the id of the auditCompanyInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuditCompanyInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete AuditCompanyInfo : {}", id);
        auditCompanyInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
