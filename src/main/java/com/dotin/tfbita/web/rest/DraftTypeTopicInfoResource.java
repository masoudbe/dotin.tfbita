package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftTypeTopicInfoRepository;
import com.dotin.tfbita.service.DraftTypeTopicInfoService;
import com.dotin.tfbita.service.dto.DraftTypeTopicInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftTypeTopicInfo}.
 */
@RestController
@RequestMapping("/api/draft-type-topic-infos")
public class DraftTypeTopicInfoResource {

    private static final Logger log = LoggerFactory.getLogger(DraftTypeTopicInfoResource.class);

    private static final String ENTITY_NAME = "draftTypeTopicInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftTypeTopicInfoService draftTypeTopicInfoService;

    private final DraftTypeTopicInfoRepository draftTypeTopicInfoRepository;

    public DraftTypeTopicInfoResource(
        DraftTypeTopicInfoService draftTypeTopicInfoService,
        DraftTypeTopicInfoRepository draftTypeTopicInfoRepository
    ) {
        this.draftTypeTopicInfoService = draftTypeTopicInfoService;
        this.draftTypeTopicInfoRepository = draftTypeTopicInfoRepository;
    }

    /**
     * {@code POST  /draft-type-topic-infos} : Create a new draftTypeTopicInfo.
     *
     * @param draftTypeTopicInfoDTO the draftTypeTopicInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftTypeTopicInfoDTO, or with status {@code 400 (Bad Request)} if the draftTypeTopicInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftTypeTopicInfoDTO> createDraftTypeTopicInfo(@RequestBody DraftTypeTopicInfoDTO draftTypeTopicInfoDTO)
        throws URISyntaxException {
        log.debug("REST request to save DraftTypeTopicInfo : {}", draftTypeTopicInfoDTO);
        if (draftTypeTopicInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftTypeTopicInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftTypeTopicInfoDTO = draftTypeTopicInfoService.save(draftTypeTopicInfoDTO);
        return ResponseEntity.created(new URI("/api/draft-type-topic-infos/" + draftTypeTopicInfoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftTypeTopicInfoDTO.getId().toString()))
            .body(draftTypeTopicInfoDTO);
    }

    /**
     * {@code PUT  /draft-type-topic-infos/:id} : Updates an existing draftTypeTopicInfo.
     *
     * @param id the id of the draftTypeTopicInfoDTO to save.
     * @param draftTypeTopicInfoDTO the draftTypeTopicInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftTypeTopicInfoDTO,
     * or with status {@code 400 (Bad Request)} if the draftTypeTopicInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftTypeTopicInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftTypeTopicInfoDTO> updateDraftTypeTopicInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftTypeTopicInfoDTO draftTypeTopicInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftTypeTopicInfo : {}, {}", id, draftTypeTopicInfoDTO);
        if (draftTypeTopicInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftTypeTopicInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftTypeTopicInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftTypeTopicInfoDTO = draftTypeTopicInfoService.update(draftTypeTopicInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftTypeTopicInfoDTO.getId().toString()))
            .body(draftTypeTopicInfoDTO);
    }

    /**
     * {@code PATCH  /draft-type-topic-infos/:id} : Partial updates given fields of an existing draftTypeTopicInfo, field will ignore if it is null
     *
     * @param id the id of the draftTypeTopicInfoDTO to save.
     * @param draftTypeTopicInfoDTO the draftTypeTopicInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftTypeTopicInfoDTO,
     * or with status {@code 400 (Bad Request)} if the draftTypeTopicInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftTypeTopicInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftTypeTopicInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftTypeTopicInfoDTO> partialUpdateDraftTypeTopicInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftTypeTopicInfoDTO draftTypeTopicInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftTypeTopicInfo partially : {}, {}", id, draftTypeTopicInfoDTO);
        if (draftTypeTopicInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftTypeTopicInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftTypeTopicInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftTypeTopicInfoDTO> result = draftTypeTopicInfoService.partialUpdate(draftTypeTopicInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftTypeTopicInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-type-topic-infos} : get all the draftTypeTopicInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftTypeTopicInfos in body.
     */
    @GetMapping("")
    public List<DraftTypeTopicInfoDTO> getAllDraftTypeTopicInfos() {
        log.debug("REST request to get all DraftTypeTopicInfos");
        return draftTypeTopicInfoService.findAll();
    }

    /**
     * {@code GET  /draft-type-topic-infos/:id} : get the "id" draftTypeTopicInfo.
     *
     * @param id the id of the draftTypeTopicInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftTypeTopicInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftTypeTopicInfoDTO> getDraftTypeTopicInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftTypeTopicInfo : {}", id);
        Optional<DraftTypeTopicInfoDTO> draftTypeTopicInfoDTO = draftTypeTopicInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftTypeTopicInfoDTO);
    }

    /**
     * {@code DELETE  /draft-type-topic-infos/:id} : delete the "id" draftTypeTopicInfo.
     *
     * @param id the id of the draftTypeTopicInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftTypeTopicInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftTypeTopicInfo : {}", id);
        draftTypeTopicInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
