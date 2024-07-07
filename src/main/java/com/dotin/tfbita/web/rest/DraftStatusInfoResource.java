package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftStatusInfoRepository;
import com.dotin.tfbita.service.DraftStatusInfoService;
import com.dotin.tfbita.service.dto.DraftStatusInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftStatusInfo}.
 */
@RestController
@RequestMapping("/api/draft-status-infos")
public class DraftStatusInfoResource {

    private static final Logger log = LoggerFactory.getLogger(DraftStatusInfoResource.class);

    private static final String ENTITY_NAME = "draftStatusInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftStatusInfoService draftStatusInfoService;

    private final DraftStatusInfoRepository draftStatusInfoRepository;

    public DraftStatusInfoResource(DraftStatusInfoService draftStatusInfoService, DraftStatusInfoRepository draftStatusInfoRepository) {
        this.draftStatusInfoService = draftStatusInfoService;
        this.draftStatusInfoRepository = draftStatusInfoRepository;
    }

    /**
     * {@code POST  /draft-status-infos} : Create a new draftStatusInfo.
     *
     * @param draftStatusInfoDTO the draftStatusInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftStatusInfoDTO, or with status {@code 400 (Bad Request)} if the draftStatusInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftStatusInfoDTO> createDraftStatusInfo(@RequestBody DraftStatusInfoDTO draftStatusInfoDTO)
        throws URISyntaxException {
        log.debug("REST request to save DraftStatusInfo : {}", draftStatusInfoDTO);
        if (draftStatusInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftStatusInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftStatusInfoDTO = draftStatusInfoService.save(draftStatusInfoDTO);
        return ResponseEntity.created(new URI("/api/draft-status-infos/" + draftStatusInfoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftStatusInfoDTO.getId().toString()))
            .body(draftStatusInfoDTO);
    }

    /**
     * {@code PUT  /draft-status-infos/:id} : Updates an existing draftStatusInfo.
     *
     * @param id the id of the draftStatusInfoDTO to save.
     * @param draftStatusInfoDTO the draftStatusInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftStatusInfoDTO,
     * or with status {@code 400 (Bad Request)} if the draftStatusInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftStatusInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftStatusInfoDTO> updateDraftStatusInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftStatusInfoDTO draftStatusInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftStatusInfo : {}, {}", id, draftStatusInfoDTO);
        if (draftStatusInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftStatusInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftStatusInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftStatusInfoDTO = draftStatusInfoService.update(draftStatusInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftStatusInfoDTO.getId().toString()))
            .body(draftStatusInfoDTO);
    }

    /**
     * {@code PATCH  /draft-status-infos/:id} : Partial updates given fields of an existing draftStatusInfo, field will ignore if it is null
     *
     * @param id the id of the draftStatusInfoDTO to save.
     * @param draftStatusInfoDTO the draftStatusInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftStatusInfoDTO,
     * or with status {@code 400 (Bad Request)} if the draftStatusInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftStatusInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftStatusInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftStatusInfoDTO> partialUpdateDraftStatusInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftStatusInfoDTO draftStatusInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftStatusInfo partially : {}, {}", id, draftStatusInfoDTO);
        if (draftStatusInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftStatusInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftStatusInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftStatusInfoDTO> result = draftStatusInfoService.partialUpdate(draftStatusInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftStatusInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-status-infos} : get all the draftStatusInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftStatusInfos in body.
     */
    @GetMapping("")
    public List<DraftStatusInfoDTO> getAllDraftStatusInfos() {
        log.debug("REST request to get all DraftStatusInfos");
        return draftStatusInfoService.findAll();
    }

    /**
     * {@code GET  /draft-status-infos/:id} : get the "id" draftStatusInfo.
     *
     * @param id the id of the draftStatusInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftStatusInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftStatusInfoDTO> getDraftStatusInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftStatusInfo : {}", id);
        Optional<DraftStatusInfoDTO> draftStatusInfoDTO = draftStatusInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftStatusInfoDTO);
    }

    /**
     * {@code DELETE  /draft-status-infos/:id} : delete the "id" draftStatusInfo.
     *
     * @param id the id of the draftStatusInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftStatusInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftStatusInfo : {}", id);
        draftStatusInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
