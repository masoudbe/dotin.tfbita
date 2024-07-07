package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftTypeAccountInfoRepository;
import com.dotin.tfbita.service.DraftTypeAccountInfoService;
import com.dotin.tfbita.service.dto.DraftTypeAccountInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftTypeAccountInfo}.
 */
@RestController
@RequestMapping("/api/draft-type-account-infos")
public class DraftTypeAccountInfoResource {

    private static final Logger log = LoggerFactory.getLogger(DraftTypeAccountInfoResource.class);

    private static final String ENTITY_NAME = "draftTypeAccountInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftTypeAccountInfoService draftTypeAccountInfoService;

    private final DraftTypeAccountInfoRepository draftTypeAccountInfoRepository;

    public DraftTypeAccountInfoResource(
        DraftTypeAccountInfoService draftTypeAccountInfoService,
        DraftTypeAccountInfoRepository draftTypeAccountInfoRepository
    ) {
        this.draftTypeAccountInfoService = draftTypeAccountInfoService;
        this.draftTypeAccountInfoRepository = draftTypeAccountInfoRepository;
    }

    /**
     * {@code POST  /draft-type-account-infos} : Create a new draftTypeAccountInfo.
     *
     * @param draftTypeAccountInfoDTO the draftTypeAccountInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftTypeAccountInfoDTO, or with status {@code 400 (Bad Request)} if the draftTypeAccountInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftTypeAccountInfoDTO> createDraftTypeAccountInfo(@RequestBody DraftTypeAccountInfoDTO draftTypeAccountInfoDTO)
        throws URISyntaxException {
        log.debug("REST request to save DraftTypeAccountInfo : {}", draftTypeAccountInfoDTO);
        if (draftTypeAccountInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftTypeAccountInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftTypeAccountInfoDTO = draftTypeAccountInfoService.save(draftTypeAccountInfoDTO);
        return ResponseEntity.created(new URI("/api/draft-type-account-infos/" + draftTypeAccountInfoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftTypeAccountInfoDTO.getId().toString()))
            .body(draftTypeAccountInfoDTO);
    }

    /**
     * {@code PUT  /draft-type-account-infos/:id} : Updates an existing draftTypeAccountInfo.
     *
     * @param id the id of the draftTypeAccountInfoDTO to save.
     * @param draftTypeAccountInfoDTO the draftTypeAccountInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftTypeAccountInfoDTO,
     * or with status {@code 400 (Bad Request)} if the draftTypeAccountInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftTypeAccountInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftTypeAccountInfoDTO> updateDraftTypeAccountInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftTypeAccountInfoDTO draftTypeAccountInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftTypeAccountInfo : {}, {}", id, draftTypeAccountInfoDTO);
        if (draftTypeAccountInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftTypeAccountInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftTypeAccountInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftTypeAccountInfoDTO = draftTypeAccountInfoService.update(draftTypeAccountInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftTypeAccountInfoDTO.getId().toString()))
            .body(draftTypeAccountInfoDTO);
    }

    /**
     * {@code PATCH  /draft-type-account-infos/:id} : Partial updates given fields of an existing draftTypeAccountInfo, field will ignore if it is null
     *
     * @param id the id of the draftTypeAccountInfoDTO to save.
     * @param draftTypeAccountInfoDTO the draftTypeAccountInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftTypeAccountInfoDTO,
     * or with status {@code 400 (Bad Request)} if the draftTypeAccountInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftTypeAccountInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftTypeAccountInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftTypeAccountInfoDTO> partialUpdateDraftTypeAccountInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftTypeAccountInfoDTO draftTypeAccountInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftTypeAccountInfo partially : {}, {}", id, draftTypeAccountInfoDTO);
        if (draftTypeAccountInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftTypeAccountInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftTypeAccountInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftTypeAccountInfoDTO> result = draftTypeAccountInfoService.partialUpdate(draftTypeAccountInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftTypeAccountInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-type-account-infos} : get all the draftTypeAccountInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftTypeAccountInfos in body.
     */
    @GetMapping("")
    public List<DraftTypeAccountInfoDTO> getAllDraftTypeAccountInfos() {
        log.debug("REST request to get all DraftTypeAccountInfos");
        return draftTypeAccountInfoService.findAll();
    }

    /**
     * {@code GET  /draft-type-account-infos/:id} : get the "id" draftTypeAccountInfo.
     *
     * @param id the id of the draftTypeAccountInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftTypeAccountInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftTypeAccountInfoDTO> getDraftTypeAccountInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftTypeAccountInfo : {}", id);
        Optional<DraftTypeAccountInfoDTO> draftTypeAccountInfoDTO = draftTypeAccountInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftTypeAccountInfoDTO);
    }

    /**
     * {@code DELETE  /draft-type-account-infos/:id} : delete the "id" draftTypeAccountInfo.
     *
     * @param id the id of the draftTypeAccountInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftTypeAccountInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftTypeAccountInfo : {}", id);
        draftTypeAccountInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
