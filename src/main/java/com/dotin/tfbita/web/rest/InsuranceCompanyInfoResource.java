package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.InsuranceCompanyInfoRepository;
import com.dotin.tfbita.service.InsuranceCompanyInfoService;
import com.dotin.tfbita.service.dto.InsuranceCompanyInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.InsuranceCompanyInfo}.
 */
@RestController
@RequestMapping("/api/insurance-company-infos")
public class InsuranceCompanyInfoResource {

    private final Logger log = LoggerFactory.getLogger(InsuranceCompanyInfoResource.class);

    private static final String ENTITY_NAME = "insuranceCompanyInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InsuranceCompanyInfoService insuranceCompanyInfoService;

    private final InsuranceCompanyInfoRepository insuranceCompanyInfoRepository;

    public InsuranceCompanyInfoResource(
        InsuranceCompanyInfoService insuranceCompanyInfoService,
        InsuranceCompanyInfoRepository insuranceCompanyInfoRepository
    ) {
        this.insuranceCompanyInfoService = insuranceCompanyInfoService;
        this.insuranceCompanyInfoRepository = insuranceCompanyInfoRepository;
    }

    /**
     * {@code POST  /insurance-company-infos} : Create a new insuranceCompanyInfo.
     *
     * @param insuranceCompanyInfoDTO the insuranceCompanyInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new insuranceCompanyInfoDTO, or with status {@code 400 (Bad Request)} if the insuranceCompanyInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<InsuranceCompanyInfoDTO> createInsuranceCompanyInfo(@RequestBody InsuranceCompanyInfoDTO insuranceCompanyInfoDTO)
        throws URISyntaxException {
        log.debug("REST request to save InsuranceCompanyInfo : {}", insuranceCompanyInfoDTO);
        if (insuranceCompanyInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new insuranceCompanyInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        insuranceCompanyInfoDTO = insuranceCompanyInfoService.save(insuranceCompanyInfoDTO);
        return ResponseEntity.created(new URI("/api/insurance-company-infos/" + insuranceCompanyInfoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, insuranceCompanyInfoDTO.getId().toString()))
            .body(insuranceCompanyInfoDTO);
    }

    /**
     * {@code PUT  /insurance-company-infos/:id} : Updates an existing insuranceCompanyInfo.
     *
     * @param id the id of the insuranceCompanyInfoDTO to save.
     * @param insuranceCompanyInfoDTO the insuranceCompanyInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceCompanyInfoDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceCompanyInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the insuranceCompanyInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<InsuranceCompanyInfoDTO> updateInsuranceCompanyInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InsuranceCompanyInfoDTO insuranceCompanyInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update InsuranceCompanyInfo : {}, {}", id, insuranceCompanyInfoDTO);
        if (insuranceCompanyInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, insuranceCompanyInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceCompanyInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        insuranceCompanyInfoDTO = insuranceCompanyInfoService.update(insuranceCompanyInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insuranceCompanyInfoDTO.getId().toString()))
            .body(insuranceCompanyInfoDTO);
    }

    /**
     * {@code PATCH  /insurance-company-infos/:id} : Partial updates given fields of an existing insuranceCompanyInfo, field will ignore if it is null
     *
     * @param id the id of the insuranceCompanyInfoDTO to save.
     * @param insuranceCompanyInfoDTO the insuranceCompanyInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated insuranceCompanyInfoDTO,
     * or with status {@code 400 (Bad Request)} if the insuranceCompanyInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the insuranceCompanyInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the insuranceCompanyInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<InsuranceCompanyInfoDTO> partialUpdateInsuranceCompanyInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody InsuranceCompanyInfoDTO insuranceCompanyInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update InsuranceCompanyInfo partially : {}, {}", id, insuranceCompanyInfoDTO);
        if (insuranceCompanyInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, insuranceCompanyInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!insuranceCompanyInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<InsuranceCompanyInfoDTO> result = insuranceCompanyInfoService.partialUpdate(insuranceCompanyInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, insuranceCompanyInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /insurance-company-infos} : get all the insuranceCompanyInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of insuranceCompanyInfos in body.
     */
    @GetMapping("")
    public List<InsuranceCompanyInfoDTO> getAllInsuranceCompanyInfos() {
        log.debug("REST request to get all InsuranceCompanyInfos");
        return insuranceCompanyInfoService.findAll();
    }

    /**
     * {@code GET  /insurance-company-infos/:id} : get the "id" insuranceCompanyInfo.
     *
     * @param id the id of the insuranceCompanyInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the insuranceCompanyInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<InsuranceCompanyInfoDTO> getInsuranceCompanyInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get InsuranceCompanyInfo : {}", id);
        Optional<InsuranceCompanyInfoDTO> insuranceCompanyInfoDTO = insuranceCompanyInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(insuranceCompanyInfoDTO);
    }

    /**
     * {@code DELETE  /insurance-company-infos/:id} : delete the "id" insuranceCompanyInfo.
     *
     * @param id the id of the insuranceCompanyInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInsuranceCompanyInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete InsuranceCompanyInfo : {}", id);
        insuranceCompanyInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
