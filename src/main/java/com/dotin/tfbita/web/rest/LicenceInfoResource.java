package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.LicenceInfoRepository;
import com.dotin.tfbita.service.LicenceInfoService;
import com.dotin.tfbita.service.dto.LicenceInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.LicenceInfo}.
 */
@RestController
@RequestMapping("/api/licence-infos")
public class LicenceInfoResource {

    private final Logger log = LoggerFactory.getLogger(LicenceInfoResource.class);

    private static final String ENTITY_NAME = "licenceInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LicenceInfoService licenceInfoService;

    private final LicenceInfoRepository licenceInfoRepository;

    public LicenceInfoResource(LicenceInfoService licenceInfoService, LicenceInfoRepository licenceInfoRepository) {
        this.licenceInfoService = licenceInfoService;
        this.licenceInfoRepository = licenceInfoRepository;
    }

    /**
     * {@code POST  /licence-infos} : Create a new licenceInfo.
     *
     * @param licenceInfoDTO the licenceInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new licenceInfoDTO, or with status {@code 400 (Bad Request)} if the licenceInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<LicenceInfoDTO> createLicenceInfo(@RequestBody LicenceInfoDTO licenceInfoDTO) throws URISyntaxException {
        log.debug("REST request to save LicenceInfo : {}", licenceInfoDTO);
        if (licenceInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new licenceInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        licenceInfoDTO = licenceInfoService.save(licenceInfoDTO);
        return ResponseEntity.created(new URI("/api/licence-infos/" + licenceInfoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, licenceInfoDTO.getId().toString()))
            .body(licenceInfoDTO);
    }

    /**
     * {@code PUT  /licence-infos/:id} : Updates an existing licenceInfo.
     *
     * @param id the id of the licenceInfoDTO to save.
     * @param licenceInfoDTO the licenceInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated licenceInfoDTO,
     * or with status {@code 400 (Bad Request)} if the licenceInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the licenceInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<LicenceInfoDTO> updateLicenceInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LicenceInfoDTO licenceInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update LicenceInfo : {}, {}", id, licenceInfoDTO);
        if (licenceInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, licenceInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!licenceInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        licenceInfoDTO = licenceInfoService.update(licenceInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, licenceInfoDTO.getId().toString()))
            .body(licenceInfoDTO);
    }

    /**
     * {@code PATCH  /licence-infos/:id} : Partial updates given fields of an existing licenceInfo, field will ignore if it is null
     *
     * @param id the id of the licenceInfoDTO to save.
     * @param licenceInfoDTO the licenceInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated licenceInfoDTO,
     * or with status {@code 400 (Bad Request)} if the licenceInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the licenceInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the licenceInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<LicenceInfoDTO> partialUpdateLicenceInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody LicenceInfoDTO licenceInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update LicenceInfo partially : {}, {}", id, licenceInfoDTO);
        if (licenceInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, licenceInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!licenceInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<LicenceInfoDTO> result = licenceInfoService.partialUpdate(licenceInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, licenceInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /licence-infos} : get all the licenceInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of licenceInfos in body.
     */
    @GetMapping("")
    public List<LicenceInfoDTO> getAllLicenceInfos() {
        log.debug("REST request to get all LicenceInfos");
        return licenceInfoService.findAll();
    }

    /**
     * {@code GET  /licence-infos/:id} : get the "id" licenceInfo.
     *
     * @param id the id of the licenceInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the licenceInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LicenceInfoDTO> getLicenceInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get LicenceInfo : {}", id);
        Optional<LicenceInfoDTO> licenceInfoDTO = licenceInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(licenceInfoDTO);
    }

    /**
     * {@code DELETE  /licence-infos/:id} : delete the "id" licenceInfo.
     *
     * @param id the id of the licenceInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLicenceInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete LicenceInfo : {}", id);
        licenceInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
