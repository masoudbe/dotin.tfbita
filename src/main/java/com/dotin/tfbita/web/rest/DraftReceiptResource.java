package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftReceiptRepository;
import com.dotin.tfbita.service.DraftReceiptService;
import com.dotin.tfbita.service.dto.DraftReceiptDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftReceipt}.
 */
@RestController
@RequestMapping("/api/draft-receipts")
public class DraftReceiptResource {

    private static final Logger log = LoggerFactory.getLogger(DraftReceiptResource.class);

    private static final String ENTITY_NAME = "draftReceipt";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftReceiptService draftReceiptService;

    private final DraftReceiptRepository draftReceiptRepository;

    public DraftReceiptResource(DraftReceiptService draftReceiptService, DraftReceiptRepository draftReceiptRepository) {
        this.draftReceiptService = draftReceiptService;
        this.draftReceiptRepository = draftReceiptRepository;
    }

    /**
     * {@code POST  /draft-receipts} : Create a new draftReceipt.
     *
     * @param draftReceiptDTO the draftReceiptDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftReceiptDTO, or with status {@code 400 (Bad Request)} if the draftReceipt has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftReceiptDTO> createDraftReceipt(@RequestBody DraftReceiptDTO draftReceiptDTO) throws URISyntaxException {
        log.debug("REST request to save DraftReceipt : {}", draftReceiptDTO);
        if (draftReceiptDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftReceipt cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftReceiptDTO = draftReceiptService.save(draftReceiptDTO);
        return ResponseEntity.created(new URI("/api/draft-receipts/" + draftReceiptDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftReceiptDTO.getId().toString()))
            .body(draftReceiptDTO);
    }

    /**
     * {@code PUT  /draft-receipts/:id} : Updates an existing draftReceipt.
     *
     * @param id the id of the draftReceiptDTO to save.
     * @param draftReceiptDTO the draftReceiptDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftReceiptDTO,
     * or with status {@code 400 (Bad Request)} if the draftReceiptDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftReceiptDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftReceiptDTO> updateDraftReceipt(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftReceiptDTO draftReceiptDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftReceipt : {}, {}", id, draftReceiptDTO);
        if (draftReceiptDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftReceiptDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftReceiptRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftReceiptDTO = draftReceiptService.update(draftReceiptDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftReceiptDTO.getId().toString()))
            .body(draftReceiptDTO);
    }

    /**
     * {@code PATCH  /draft-receipts/:id} : Partial updates given fields of an existing draftReceipt, field will ignore if it is null
     *
     * @param id the id of the draftReceiptDTO to save.
     * @param draftReceiptDTO the draftReceiptDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftReceiptDTO,
     * or with status {@code 400 (Bad Request)} if the draftReceiptDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftReceiptDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftReceiptDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftReceiptDTO> partialUpdateDraftReceipt(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftReceiptDTO draftReceiptDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftReceipt partially : {}, {}", id, draftReceiptDTO);
        if (draftReceiptDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftReceiptDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftReceiptRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftReceiptDTO> result = draftReceiptService.partialUpdate(draftReceiptDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftReceiptDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-receipts} : get all the draftReceipts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftReceipts in body.
     */
    @GetMapping("")
    public List<DraftReceiptDTO> getAllDraftReceipts() {
        log.debug("REST request to get all DraftReceipts");
        return draftReceiptService.findAll();
    }

    /**
     * {@code GET  /draft-receipts/:id} : get the "id" draftReceipt.
     *
     * @param id the id of the draftReceiptDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftReceiptDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftReceiptDTO> getDraftReceipt(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftReceipt : {}", id);
        Optional<DraftReceiptDTO> draftReceiptDTO = draftReceiptService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftReceiptDTO);
    }

    /**
     * {@code DELETE  /draft-receipts/:id} : delete the "id" draftReceipt.
     *
     * @param id the id of the draftReceiptDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftReceipt(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftReceipt : {}", id);
        draftReceiptService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
