package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.JustificationDeductionDetailRepository;
import com.dotin.tfbita.service.JustificationDeductionDetailService;
import com.dotin.tfbita.service.dto.JustificationDeductionDetailDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.JustificationDeductionDetail}.
 */
@RestController
@RequestMapping("/api/justification-deduction-details")
public class JustificationDeductionDetailResource {

    private static final Logger log = LoggerFactory.getLogger(JustificationDeductionDetailResource.class);

    private static final String ENTITY_NAME = "justificationDeductionDetail";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final JustificationDeductionDetailService justificationDeductionDetailService;

    private final JustificationDeductionDetailRepository justificationDeductionDetailRepository;

    public JustificationDeductionDetailResource(
        JustificationDeductionDetailService justificationDeductionDetailService,
        JustificationDeductionDetailRepository justificationDeductionDetailRepository
    ) {
        this.justificationDeductionDetailService = justificationDeductionDetailService;
        this.justificationDeductionDetailRepository = justificationDeductionDetailRepository;
    }

    /**
     * {@code POST  /justification-deduction-details} : Create a new justificationDeductionDetail.
     *
     * @param justificationDeductionDetailDTO the justificationDeductionDetailDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new justificationDeductionDetailDTO, or with status {@code 400 (Bad Request)} if the justificationDeductionDetail has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<JustificationDeductionDetailDTO> createJustificationDeductionDetail(
        @RequestBody JustificationDeductionDetailDTO justificationDeductionDetailDTO
    ) throws URISyntaxException {
        log.debug("REST request to save JustificationDeductionDetail : {}", justificationDeductionDetailDTO);
        if (justificationDeductionDetailDTO.getId() != null) {
            throw new BadRequestAlertException("A new justificationDeductionDetail cannot already have an ID", ENTITY_NAME, "idexists");
        }
        justificationDeductionDetailDTO = justificationDeductionDetailService.save(justificationDeductionDetailDTO);
        return ResponseEntity.created(new URI("/api/justification-deduction-details/" + justificationDeductionDetailDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, justificationDeductionDetailDTO.getId().toString())
            )
            .body(justificationDeductionDetailDTO);
    }

    /**
     * {@code PUT  /justification-deduction-details/:id} : Updates an existing justificationDeductionDetail.
     *
     * @param id the id of the justificationDeductionDetailDTO to save.
     * @param justificationDeductionDetailDTO the justificationDeductionDetailDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated justificationDeductionDetailDTO,
     * or with status {@code 400 (Bad Request)} if the justificationDeductionDetailDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the justificationDeductionDetailDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<JustificationDeductionDetailDTO> updateJustificationDeductionDetail(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JustificationDeductionDetailDTO justificationDeductionDetailDTO
    ) throws URISyntaxException {
        log.debug("REST request to update JustificationDeductionDetail : {}, {}", id, justificationDeductionDetailDTO);
        if (justificationDeductionDetailDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, justificationDeductionDetailDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!justificationDeductionDetailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        justificationDeductionDetailDTO = justificationDeductionDetailService.update(justificationDeductionDetailDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, justificationDeductionDetailDTO.getId().toString())
            )
            .body(justificationDeductionDetailDTO);
    }

    /**
     * {@code PATCH  /justification-deduction-details/:id} : Partial updates given fields of an existing justificationDeductionDetail, field will ignore if it is null
     *
     * @param id the id of the justificationDeductionDetailDTO to save.
     * @param justificationDeductionDetailDTO the justificationDeductionDetailDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated justificationDeductionDetailDTO,
     * or with status {@code 400 (Bad Request)} if the justificationDeductionDetailDTO is not valid,
     * or with status {@code 404 (Not Found)} if the justificationDeductionDetailDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the justificationDeductionDetailDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<JustificationDeductionDetailDTO> partialUpdateJustificationDeductionDetail(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody JustificationDeductionDetailDTO justificationDeductionDetailDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update JustificationDeductionDetail partially : {}, {}", id, justificationDeductionDetailDTO);
        if (justificationDeductionDetailDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, justificationDeductionDetailDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!justificationDeductionDetailRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<JustificationDeductionDetailDTO> result = justificationDeductionDetailService.partialUpdate(
            justificationDeductionDetailDTO
        );

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, justificationDeductionDetailDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /justification-deduction-details} : get all the justificationDeductionDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of justificationDeductionDetails in body.
     */
    @GetMapping("")
    public List<JustificationDeductionDetailDTO> getAllJustificationDeductionDetails() {
        log.debug("REST request to get all JustificationDeductionDetails");
        return justificationDeductionDetailService.findAll();
    }

    /**
     * {@code GET  /justification-deduction-details/:id} : get the "id" justificationDeductionDetail.
     *
     * @param id the id of the justificationDeductionDetailDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the justificationDeductionDetailDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<JustificationDeductionDetailDTO> getJustificationDeductionDetail(@PathVariable("id") Long id) {
        log.debug("REST request to get JustificationDeductionDetail : {}", id);
        Optional<JustificationDeductionDetailDTO> justificationDeductionDetailDTO = justificationDeductionDetailService.findOne(id);
        return ResponseUtil.wrapOrNotFound(justificationDeductionDetailDTO);
    }

    /**
     * {@code DELETE  /justification-deduction-details/:id} : delete the "id" justificationDeductionDetail.
     *
     * @param id the id of the justificationDeductionDetailDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteJustificationDeductionDetail(@PathVariable("id") Long id) {
        log.debug("REST request to delete JustificationDeductionDetail : {}", id);
        justificationDeductionDetailService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
