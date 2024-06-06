package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.domain.LicenceInfo;
import com.dotin.tfbita.repository.LicenceInfoRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dotin.tfbita.domain.LicenceInfo}.
 */
@RestController
@RequestMapping("/api/licence-infos")
@Transactional
public class LicenceInfoResource {

    private final Logger log = LoggerFactory.getLogger(LicenceInfoResource.class);

    private static final String ENTITY_NAME = "licenceInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LicenceInfoRepository licenceInfoRepository;

    public LicenceInfoResource(LicenceInfoRepository licenceInfoRepository) {
        this.licenceInfoRepository = licenceInfoRepository;
    }

    /**
     * {@code POST  /licence-infos} : Create a new licenceInfo.
     *
     * @param licenceInfo the licenceInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new licenceInfo, or with status {@code 400 (Bad Request)} if the licenceInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LicenceInfo> createLicenceInfo(@RequestBody LicenceInfo licenceInfo) throws URISyntaxException {
        log.debug("REST request to save LicenceInfo : {}", licenceInfo);
        if (licenceInfo.getId() != null) {
            throw new BadRequestAlertException("A new licenceInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        licenceInfo = licenceInfoRepository.save(licenceInfo);
        return ResponseEntity.created(new URI("/api/licence-infos/" + licenceInfo.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, licenceInfo.getId().toString()))
            .body(licenceInfo);
    }

    /**
     * {@code PUT  /licence-infos/:id} : Updates an existing licenceInfo.
     *
     * @param id the id of the licenceInfo to save.
     * @param licenceInfo the licenceInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated licenceInfo,
     * or with status {@code 400 (Bad Request)} if the licenceInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the licenceInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LicenceInfo> updateLicenceInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LicenceInfo licenceInfo
    ) throws URISyntaxException {
        log.debug("REST request to update LicenceInfo : {}, {}", id, licenceInfo);
        if (licenceInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, licenceInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!licenceInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        licenceInfo = licenceInfoRepository.save(licenceInfo);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, licenceInfo.getId().toString()))
            .body(licenceInfo);
    }

    /**
     * {@code PATCH  /licence-infos/:id} : Partial updates given fields of an existing licenceInfo, field will ignore if it is null
     *
     * @param id the id of the licenceInfo to save.
     * @param licenceInfo the licenceInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated licenceInfo,
     * or with status {@code 400 (Bad Request)} if the licenceInfo is not valid,
     * or with status {@code 404 (Not Found)} if the licenceInfo is not found,
     * or with status {@code 500 (Internal Server Error)} if the licenceInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LicenceInfo> partialUpdateLicenceInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LicenceInfo licenceInfo
    ) throws URISyntaxException {
        log.debug("REST request to partial update LicenceInfo partially : {}, {}", id, licenceInfo);
        if (licenceInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, licenceInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!licenceInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LicenceInfo> result = licenceInfoRepository
            .findById(licenceInfo.getId())
            .map(existingLicenceInfo -> {
                if (licenceInfo.getOrganizationLicence() != null) {
                    existingLicenceInfo.setOrganizationLicence(licenceInfo.getOrganizationLicence());
                }
                if (licenceInfo.getLicenceNumber() != null) {
                    existingLicenceInfo.setLicenceNumber(licenceInfo.getLicenceNumber());
                }
                if (licenceInfo.getLicenceDate() != null) {
                    existingLicenceInfo.setLicenceDate(licenceInfo.getLicenceDate());
                }
                if (licenceInfo.getHavingProduct() != null) {
                    existingLicenceInfo.setHavingProduct(licenceInfo.getHavingProduct());
                }
                if (licenceInfo.getHavingService() != null) {
                    existingLicenceInfo.setHavingService(licenceInfo.getHavingService());
                }
                if (licenceInfo.getCreditDate() != null) {
                    existingLicenceInfo.setCreditDate(licenceInfo.getCreditDate());
                }

                return existingLicenceInfo;
            })
            .map(licenceInfoRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, licenceInfo.getId().toString())
        );
    }

    /**
     * {@code GET  /licence-infos} : get all the licenceInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of licenceInfos in body.
     */
    @GetMapping("")
    public List<LicenceInfo> getAllLicenceInfos() {
        log.debug("REST request to get all LicenceInfos");
        return licenceInfoRepository.findAll();
    }

    /**
     * {@code GET  /licence-infos/:id} : get the "id" licenceInfo.
     *
     * @param id the id of the licenceInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the licenceInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LicenceInfo> getLicenceInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get LicenceInfo : {}", id);
        Optional<LicenceInfo> licenceInfo = licenceInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(licenceInfo);
    }

    /**
     * {@code DELETE  /licence-infos/:id} : delete the "id" licenceInfo.
     *
     * @param id the id of the licenceInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicenceInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete LicenceInfo : {}", id);
        licenceInfoRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
