package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftProductInfoRepository;
import com.dotin.tfbita.service.DraftProductInfoService;
import com.dotin.tfbita.service.dto.DraftProductInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftProductInfo}.
 */
@RestController
@RequestMapping("/api/draft-product-infos")
public class DraftProductInfoResource {

    private static final Logger log = LoggerFactory.getLogger(DraftProductInfoResource.class);

    private static final String ENTITY_NAME = "draftProductInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftProductInfoService draftProductInfoService;

    private final DraftProductInfoRepository draftProductInfoRepository;

    public DraftProductInfoResource(
        DraftProductInfoService draftProductInfoService,
        DraftProductInfoRepository draftProductInfoRepository
    ) {
        this.draftProductInfoService = draftProductInfoService;
        this.draftProductInfoRepository = draftProductInfoRepository;
    }

    /**
     * {@code POST  /draft-product-infos} : Create a new draftProductInfo.
     *
     * @param draftProductInfoDTO the draftProductInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftProductInfoDTO, or with status {@code 400 (Bad Request)} if the draftProductInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftProductInfoDTO> createDraftProductInfo(@RequestBody DraftProductInfoDTO draftProductInfoDTO)
        throws URISyntaxException {
        log.debug("REST request to save DraftProductInfo : {}", draftProductInfoDTO);
        if (draftProductInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftProductInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftProductInfoDTO = draftProductInfoService.save(draftProductInfoDTO);
        return ResponseEntity.created(new URI("/api/draft-product-infos/" + draftProductInfoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftProductInfoDTO.getId().toString()))
            .body(draftProductInfoDTO);
    }

    /**
     * {@code PUT  /draft-product-infos/:id} : Updates an existing draftProductInfo.
     *
     * @param id the id of the draftProductInfoDTO to save.
     * @param draftProductInfoDTO the draftProductInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftProductInfoDTO,
     * or with status {@code 400 (Bad Request)} if the draftProductInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftProductInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftProductInfoDTO> updateDraftProductInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftProductInfoDTO draftProductInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftProductInfo : {}, {}", id, draftProductInfoDTO);
        if (draftProductInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftProductInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftProductInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftProductInfoDTO = draftProductInfoService.update(draftProductInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftProductInfoDTO.getId().toString()))
            .body(draftProductInfoDTO);
    }

    /**
     * {@code PATCH  /draft-product-infos/:id} : Partial updates given fields of an existing draftProductInfo, field will ignore if it is null
     *
     * @param id the id of the draftProductInfoDTO to save.
     * @param draftProductInfoDTO the draftProductInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftProductInfoDTO,
     * or with status {@code 400 (Bad Request)} if the draftProductInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftProductInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftProductInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftProductInfoDTO> partialUpdateDraftProductInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftProductInfoDTO draftProductInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftProductInfo partially : {}, {}", id, draftProductInfoDTO);
        if (draftProductInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftProductInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftProductInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftProductInfoDTO> result = draftProductInfoService.partialUpdate(draftProductInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftProductInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-product-infos} : get all the draftProductInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftProductInfos in body.
     */
    @GetMapping("")
    public List<DraftProductInfoDTO> getAllDraftProductInfos() {
        log.debug("REST request to get all DraftProductInfos");
        return draftProductInfoService.findAll();
    }

    /**
     * {@code GET  /draft-product-infos/:id} : get the "id" draftProductInfo.
     *
     * @param id the id of the draftProductInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftProductInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftProductInfoDTO> getDraftProductInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftProductInfo : {}", id);
        Optional<DraftProductInfoDTO> draftProductInfoDTO = draftProductInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftProductInfoDTO);
    }

    /**
     * {@code DELETE  /draft-product-infos/:id} : delete the "id" draftProductInfo.
     *
     * @param id the id of the draftProductInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftProductInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftProductInfo : {}", id);
        draftProductInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
