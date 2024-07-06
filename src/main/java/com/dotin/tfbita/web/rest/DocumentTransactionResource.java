package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DocumentTransactionRepository;
import com.dotin.tfbita.service.DocumentTransactionService;
import com.dotin.tfbita.service.dto.DocumentTransactionDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DocumentTransaction}.
 */
@RestController
@RequestMapping("/api/document-transactions")
public class DocumentTransactionResource {

    private final Logger log = LoggerFactory.getLogger(DocumentTransactionResource.class);

    private static final String ENTITY_NAME = "documentTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DocumentTransactionService documentTransactionService;

    private final DocumentTransactionRepository documentTransactionRepository;

    public DocumentTransactionResource(
        DocumentTransactionService documentTransactionService,
        DocumentTransactionRepository documentTransactionRepository
    ) {
        this.documentTransactionService = documentTransactionService;
        this.documentTransactionRepository = documentTransactionRepository;
    }

    /**
     * {@code POST  /document-transactions} : Create a new documentTransaction.
     *
     * @param documentTransactionDTO the documentTransactionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new documentTransactionDTO, or with status {@code 400 (Bad Request)} if the documentTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DocumentTransactionDTO> createDocumentTransaction(@RequestBody DocumentTransactionDTO documentTransactionDTO)
        throws URISyntaxException {
        log.debug("REST request to save DocumentTransaction : {}", documentTransactionDTO);
        if (documentTransactionDTO.getId() != null) {
            throw new BadRequestAlertException("A new documentTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        documentTransactionDTO = documentTransactionService.save(documentTransactionDTO);
        return ResponseEntity.created(new URI("/api/document-transactions/" + documentTransactionDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, documentTransactionDTO.getId().toString()))
            .body(documentTransactionDTO);
    }

    /**
     * {@code PUT  /document-transactions/:id} : Updates an existing documentTransaction.
     *
     * @param id the id of the documentTransactionDTO to save.
     * @param documentTransactionDTO the documentTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the documentTransactionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the documentTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DocumentTransactionDTO> updateDocumentTransaction(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DocumentTransactionDTO documentTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DocumentTransaction : {}, {}", id, documentTransactionDTO);
        if (documentTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentTransactionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentTransactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        documentTransactionDTO = documentTransactionService.update(documentTransactionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, documentTransactionDTO.getId().toString()))
            .body(documentTransactionDTO);
    }

    /**
     * {@code PATCH  /document-transactions/:id} : Partial updates given fields of an existing documentTransaction, field will ignore if it is null
     *
     * @param id the id of the documentTransactionDTO to save.
     * @param documentTransactionDTO the documentTransactionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated documentTransactionDTO,
     * or with status {@code 400 (Bad Request)} if the documentTransactionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the documentTransactionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the documentTransactionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DocumentTransactionDTO> partialUpdateDocumentTransaction(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DocumentTransactionDTO documentTransactionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DocumentTransaction partially : {}, {}", id, documentTransactionDTO);
        if (documentTransactionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, documentTransactionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!documentTransactionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DocumentTransactionDTO> result = documentTransactionService.partialUpdate(documentTransactionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, documentTransactionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /document-transactions} : get all the documentTransactions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of documentTransactions in body.
     */
    @GetMapping("")
    public List<DocumentTransactionDTO> getAllDocumentTransactions() {
        log.debug("REST request to get all DocumentTransactions");
        return documentTransactionService.findAll();
    }

    /**
     * {@code GET  /document-transactions/:id} : get the "id" documentTransaction.
     *
     * @param id the id of the documentTransactionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the documentTransactionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DocumentTransactionDTO> getDocumentTransaction(@PathVariable("id") Long id) {
        log.debug("REST request to get DocumentTransaction : {}", id);
        Optional<DocumentTransactionDTO> documentTransactionDTO = documentTransactionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(documentTransactionDTO);
    }

    /**
     * {@code DELETE  /document-transactions/:id} : delete the "id" documentTransaction.
     *
     * @param id the id of the documentTransactionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDocumentTransaction(@PathVariable("id") Long id) {
        log.debug("REST request to delete DocumentTransaction : {}", id);
        documentTransactionService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
