package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftAccountInfoRepository;
import com.dotin.tfbita.service.DraftAccountInfoService;
import com.dotin.tfbita.service.dto.DraftAccountInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftAccountInfo}.
 */
@RestController
@RequestMapping("/api/draft-account-infos")
public class DraftAccountInfoResource {

    private static final Logger log = LoggerFactory.getLogger(DraftAccountInfoResource.class);

    private static final String ENTITY_NAME = "draftAccountInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftAccountInfoService draftAccountInfoService;

    private final DraftAccountInfoRepository draftAccountInfoRepository;

    public DraftAccountInfoResource(
        DraftAccountInfoService draftAccountInfoService,
        DraftAccountInfoRepository draftAccountInfoRepository
    ) {
        this.draftAccountInfoService = draftAccountInfoService;
        this.draftAccountInfoRepository = draftAccountInfoRepository;
    }

    /**
     * {@code POST  /draft-account-infos} : Create a new draftAccountInfo.
     *
     * @param draftAccountInfoDTO the draftAccountInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftAccountInfoDTO, or with status {@code 400 (Bad Request)} if the draftAccountInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftAccountInfoDTO> createDraftAccountInfo(@RequestBody DraftAccountInfoDTO draftAccountInfoDTO)
        throws URISyntaxException {
        log.debug("REST request to save DraftAccountInfo : {}", draftAccountInfoDTO);
        if (draftAccountInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftAccountInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftAccountInfoDTO = draftAccountInfoService.save(draftAccountInfoDTO);
        return ResponseEntity.created(new URI("/api/draft-account-infos/" + draftAccountInfoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftAccountInfoDTO.getId().toString()))
            .body(draftAccountInfoDTO);
    }

    /**
     * {@code PUT  /draft-account-infos/:id} : Updates an existing draftAccountInfo.
     *
     * @param id the id of the draftAccountInfoDTO to save.
     * @param draftAccountInfoDTO the draftAccountInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftAccountInfoDTO,
     * or with status {@code 400 (Bad Request)} if the draftAccountInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftAccountInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftAccountInfoDTO> updateDraftAccountInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftAccountInfoDTO draftAccountInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftAccountInfo : {}, {}", id, draftAccountInfoDTO);
        if (draftAccountInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftAccountInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftAccountInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftAccountInfoDTO = draftAccountInfoService.update(draftAccountInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftAccountInfoDTO.getId().toString()))
            .body(draftAccountInfoDTO);
    }

    /**
     * {@code PATCH  /draft-account-infos/:id} : Partial updates given fields of an existing draftAccountInfo, field will ignore if it is null
     *
     * @param id the id of the draftAccountInfoDTO to save.
     * @param draftAccountInfoDTO the draftAccountInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftAccountInfoDTO,
     * or with status {@code 400 (Bad Request)} if the draftAccountInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftAccountInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftAccountInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftAccountInfoDTO> partialUpdateDraftAccountInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftAccountInfoDTO draftAccountInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftAccountInfo partially : {}, {}", id, draftAccountInfoDTO);
        if (draftAccountInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftAccountInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftAccountInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftAccountInfoDTO> result = draftAccountInfoService.partialUpdate(draftAccountInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftAccountInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-account-infos} : get all the draftAccountInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftAccountInfos in body.
     */
    @GetMapping("")
    public List<DraftAccountInfoDTO> getAllDraftAccountInfos() {
        log.debug("REST request to get all DraftAccountInfos");
        return draftAccountInfoService.findAll();
    }

    /**
     * {@code GET  /draft-account-infos/:id} : get the "id" draftAccountInfo.
     *
     * @param id the id of the draftAccountInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftAccountInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftAccountInfoDTO> getDraftAccountInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftAccountInfo : {}", id);
        Optional<DraftAccountInfoDTO> draftAccountInfoDTO = draftAccountInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftAccountInfoDTO);
    }

    /**
     * {@code DELETE  /draft-account-infos/:id} : delete the "id" draftAccountInfo.
     *
     * @param id the id of the draftAccountInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftAccountInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftAccountInfo : {}", id);
        draftAccountInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
