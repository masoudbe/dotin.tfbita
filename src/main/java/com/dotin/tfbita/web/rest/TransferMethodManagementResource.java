package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.TransferMethodManagementRepository;
import com.dotin.tfbita.service.TransferMethodManagementService;
import com.dotin.tfbita.service.dto.TransferMethodManagementDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.TransferMethodManagement}.
 */
@RestController
@RequestMapping("/api/transfer-method-managements")
public class TransferMethodManagementResource {

    private final Logger log = LoggerFactory.getLogger(TransferMethodManagementResource.class);

    private static final String ENTITY_NAME = "transferMethodManagement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TransferMethodManagementService transferMethodManagementService;

    private final TransferMethodManagementRepository transferMethodManagementRepository;

    public TransferMethodManagementResource(
        TransferMethodManagementService transferMethodManagementService,
        TransferMethodManagementRepository transferMethodManagementRepository
    ) {
        this.transferMethodManagementService = transferMethodManagementService;
        this.transferMethodManagementRepository = transferMethodManagementRepository;
    }

    /**
     * {@code POST  /transfer-method-managements} : Create a new transferMethodManagement.
     *
     * @param transferMethodManagementDTO the transferMethodManagementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new transferMethodManagementDTO, or with status {@code 400 (Bad Request)} if the transferMethodManagement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TransferMethodManagementDTO> createTransferMethodManagement(
        @RequestBody TransferMethodManagementDTO transferMethodManagementDTO
    ) throws URISyntaxException {
        log.debug("REST request to save TransferMethodManagement : {}", transferMethodManagementDTO);
        if (transferMethodManagementDTO.getId() != null) {
            throw new BadRequestAlertException("A new transferMethodManagement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        transferMethodManagementDTO = transferMethodManagementService.save(transferMethodManagementDTO);
        return ResponseEntity.created(new URI("/api/transfer-method-managements/" + transferMethodManagementDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, transferMethodManagementDTO.getId().toString())
            )
            .body(transferMethodManagementDTO);
    }

    /**
     * {@code PUT  /transfer-method-managements/:id} : Updates an existing transferMethodManagement.
     *
     * @param id the id of the transferMethodManagementDTO to save.
     * @param transferMethodManagementDTO the transferMethodManagementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transferMethodManagementDTO,
     * or with status {@code 400 (Bad Request)} if the transferMethodManagementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the transferMethodManagementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TransferMethodManagementDTO> updateTransferMethodManagement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TransferMethodManagementDTO transferMethodManagementDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TransferMethodManagement : {}, {}", id, transferMethodManagementDTO);
        if (transferMethodManagementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transferMethodManagementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transferMethodManagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        transferMethodManagementDTO = transferMethodManagementService.update(transferMethodManagementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transferMethodManagementDTO.getId().toString()))
            .body(transferMethodManagementDTO);
    }

    /**
     * {@code PATCH  /transfer-method-managements/:id} : Partial updates given fields of an existing transferMethodManagement, field will ignore if it is null
     *
     * @param id the id of the transferMethodManagementDTO to save.
     * @param transferMethodManagementDTO the transferMethodManagementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated transferMethodManagementDTO,
     * or with status {@code 400 (Bad Request)} if the transferMethodManagementDTO is not valid,
     * or with status {@code 404 (Not Found)} if the transferMethodManagementDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the transferMethodManagementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TransferMethodManagementDTO> partialUpdateTransferMethodManagement(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TransferMethodManagementDTO transferMethodManagementDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TransferMethodManagement partially : {}, {}", id, transferMethodManagementDTO);
        if (transferMethodManagementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, transferMethodManagementDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!transferMethodManagementRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TransferMethodManagementDTO> result = transferMethodManagementService.partialUpdate(transferMethodManagementDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, transferMethodManagementDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /transfer-method-managements} : get all the transferMethodManagements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of transferMethodManagements in body.
     */
    @GetMapping("")
    public List<TransferMethodManagementDTO> getAllTransferMethodManagements() {
        log.debug("REST request to get all TransferMethodManagements");
        return transferMethodManagementService.findAll();
    }

    /**
     * {@code GET  /transfer-method-managements/:id} : get the "id" transferMethodManagement.
     *
     * @param id the id of the transferMethodManagementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the transferMethodManagementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TransferMethodManagementDTO> getTransferMethodManagement(@PathVariable("id") Long id) {
        log.debug("REST request to get TransferMethodManagement : {}", id);
        Optional<TransferMethodManagementDTO> transferMethodManagementDTO = transferMethodManagementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(transferMethodManagementDTO);
    }

    /**
     * {@code DELETE  /transfer-method-managements/:id} : delete the "id" transferMethodManagement.
     *
     * @param id the id of the transferMethodManagementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransferMethodManagement(@PathVariable("id") Long id) {
        log.debug("REST request to delete TransferMethodManagement : {}", id);
        transferMethodManagementService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
