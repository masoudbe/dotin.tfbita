package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.SuggestedSanctionInfoRepository;
import com.dotin.tfbita.service.SuggestedSanctionInfoService;
import com.dotin.tfbita.service.dto.SuggestedSanctionInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.SuggestedSanctionInfo}.
 */
@RestController
@RequestMapping("/api/suggested-sanction-infos")
public class SuggestedSanctionInfoResource {

    private static final Logger log = LoggerFactory.getLogger(SuggestedSanctionInfoResource.class);

    private static final String ENTITY_NAME = "suggestedSanctionInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SuggestedSanctionInfoService suggestedSanctionInfoService;

    private final SuggestedSanctionInfoRepository suggestedSanctionInfoRepository;

    public SuggestedSanctionInfoResource(
        SuggestedSanctionInfoService suggestedSanctionInfoService,
        SuggestedSanctionInfoRepository suggestedSanctionInfoRepository
    ) {
        this.suggestedSanctionInfoService = suggestedSanctionInfoService;
        this.suggestedSanctionInfoRepository = suggestedSanctionInfoRepository;
    }

    /**
     * {@code POST  /suggested-sanction-infos} : Create a new suggestedSanctionInfo.
     *
     * @param suggestedSanctionInfoDTO the suggestedSanctionInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new suggestedSanctionInfoDTO, or with status {@code 400 (Bad Request)} if the suggestedSanctionInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SuggestedSanctionInfoDTO> createSuggestedSanctionInfo(
        @RequestBody SuggestedSanctionInfoDTO suggestedSanctionInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to save SuggestedSanctionInfo : {}", suggestedSanctionInfoDTO);
        if (suggestedSanctionInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new suggestedSanctionInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        suggestedSanctionInfoDTO = suggestedSanctionInfoService.save(suggestedSanctionInfoDTO);
        return ResponseEntity.created(new URI("/api/suggested-sanction-infos/" + suggestedSanctionInfoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, suggestedSanctionInfoDTO.getId().toString()))
            .body(suggestedSanctionInfoDTO);
    }

    /**
     * {@code PUT  /suggested-sanction-infos/:id} : Updates an existing suggestedSanctionInfo.
     *
     * @param id the id of the suggestedSanctionInfoDTO to save.
     * @param suggestedSanctionInfoDTO the suggestedSanctionInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated suggestedSanctionInfoDTO,
     * or with status {@code 400 (Bad Request)} if the suggestedSanctionInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the suggestedSanctionInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SuggestedSanctionInfoDTO> updateSuggestedSanctionInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SuggestedSanctionInfoDTO suggestedSanctionInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SuggestedSanctionInfo : {}, {}", id, suggestedSanctionInfoDTO);
        if (suggestedSanctionInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, suggestedSanctionInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!suggestedSanctionInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        suggestedSanctionInfoDTO = suggestedSanctionInfoService.update(suggestedSanctionInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, suggestedSanctionInfoDTO.getId().toString()))
            .body(suggestedSanctionInfoDTO);
    }

    /**
     * {@code PATCH  /suggested-sanction-infos/:id} : Partial updates given fields of an existing suggestedSanctionInfo, field will ignore if it is null
     *
     * @param id the id of the suggestedSanctionInfoDTO to save.
     * @param suggestedSanctionInfoDTO the suggestedSanctionInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated suggestedSanctionInfoDTO,
     * or with status {@code 400 (Bad Request)} if the suggestedSanctionInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the suggestedSanctionInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the suggestedSanctionInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SuggestedSanctionInfoDTO> partialUpdateSuggestedSanctionInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SuggestedSanctionInfoDTO suggestedSanctionInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SuggestedSanctionInfo partially : {}, {}", id, suggestedSanctionInfoDTO);
        if (suggestedSanctionInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, suggestedSanctionInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!suggestedSanctionInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SuggestedSanctionInfoDTO> result = suggestedSanctionInfoService.partialUpdate(suggestedSanctionInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, suggestedSanctionInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /suggested-sanction-infos} : get all the suggestedSanctionInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of suggestedSanctionInfos in body.
     */
    @GetMapping("")
    public List<SuggestedSanctionInfoDTO> getAllSuggestedSanctionInfos() {
        log.debug("REST request to get all SuggestedSanctionInfos");
        return suggestedSanctionInfoService.findAll();
    }

    /**
     * {@code GET  /suggested-sanction-infos/:id} : get the "id" suggestedSanctionInfo.
     *
     * @param id the id of the suggestedSanctionInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the suggestedSanctionInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SuggestedSanctionInfoDTO> getSuggestedSanctionInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get SuggestedSanctionInfo : {}", id);
        Optional<SuggestedSanctionInfoDTO> suggestedSanctionInfoDTO = suggestedSanctionInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(suggestedSanctionInfoDTO);
    }

    /**
     * {@code DELETE  /suggested-sanction-infos/:id} : delete the "id" suggestedSanctionInfo.
     *
     * @param id the id of the suggestedSanctionInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSuggestedSanctionInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete SuggestedSanctionInfo : {}", id);
        suggestedSanctionInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
