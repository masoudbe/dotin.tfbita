package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftCertificateTypeRepository;
import com.dotin.tfbita.service.DraftCertificateTypeService;
import com.dotin.tfbita.service.dto.DraftCertificateTypeDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftCertificateType}.
 */
@RestController
@RequestMapping("/api/draft-certificate-types")
public class DraftCertificateTypeResource {

    private static final Logger log = LoggerFactory.getLogger(DraftCertificateTypeResource.class);

    private static final String ENTITY_NAME = "draftCertificateType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftCertificateTypeService draftCertificateTypeService;

    private final DraftCertificateTypeRepository draftCertificateTypeRepository;

    public DraftCertificateTypeResource(
        DraftCertificateTypeService draftCertificateTypeService,
        DraftCertificateTypeRepository draftCertificateTypeRepository
    ) {
        this.draftCertificateTypeService = draftCertificateTypeService;
        this.draftCertificateTypeRepository = draftCertificateTypeRepository;
    }

    /**
     * {@code POST  /draft-certificate-types} : Create a new draftCertificateType.
     *
     * @param draftCertificateTypeDTO the draftCertificateTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftCertificateTypeDTO, or with status {@code 400 (Bad Request)} if the draftCertificateType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftCertificateTypeDTO> createDraftCertificateType(@RequestBody DraftCertificateTypeDTO draftCertificateTypeDTO)
        throws URISyntaxException {
        log.debug("REST request to save DraftCertificateType : {}", draftCertificateTypeDTO);
        if (draftCertificateTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftCertificateType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftCertificateTypeDTO = draftCertificateTypeService.save(draftCertificateTypeDTO);
        return ResponseEntity.created(new URI("/api/draft-certificate-types/" + draftCertificateTypeDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftCertificateTypeDTO.getId().toString()))
            .body(draftCertificateTypeDTO);
    }

    /**
     * {@code PUT  /draft-certificate-types/:id} : Updates an existing draftCertificateType.
     *
     * @param id the id of the draftCertificateTypeDTO to save.
     * @param draftCertificateTypeDTO the draftCertificateTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftCertificateTypeDTO,
     * or with status {@code 400 (Bad Request)} if the draftCertificateTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftCertificateTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftCertificateTypeDTO> updateDraftCertificateType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftCertificateTypeDTO draftCertificateTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftCertificateType : {}, {}", id, draftCertificateTypeDTO);
        if (draftCertificateTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftCertificateTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftCertificateTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftCertificateTypeDTO = draftCertificateTypeService.update(draftCertificateTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftCertificateTypeDTO.getId().toString()))
            .body(draftCertificateTypeDTO);
    }

    /**
     * {@code PATCH  /draft-certificate-types/:id} : Partial updates given fields of an existing draftCertificateType, field will ignore if it is null
     *
     * @param id the id of the draftCertificateTypeDTO to save.
     * @param draftCertificateTypeDTO the draftCertificateTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftCertificateTypeDTO,
     * or with status {@code 400 (Bad Request)} if the draftCertificateTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftCertificateTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftCertificateTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftCertificateTypeDTO> partialUpdateDraftCertificateType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftCertificateTypeDTO draftCertificateTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftCertificateType partially : {}, {}", id, draftCertificateTypeDTO);
        if (draftCertificateTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftCertificateTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftCertificateTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftCertificateTypeDTO> result = draftCertificateTypeService.partialUpdate(draftCertificateTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftCertificateTypeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-certificate-types} : get all the draftCertificateTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftCertificateTypes in body.
     */
    @GetMapping("")
    public List<DraftCertificateTypeDTO> getAllDraftCertificateTypes() {
        log.debug("REST request to get all DraftCertificateTypes");
        return draftCertificateTypeService.findAll();
    }

    /**
     * {@code GET  /draft-certificate-types/:id} : get the "id" draftCertificateType.
     *
     * @param id the id of the draftCertificateTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftCertificateTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftCertificateTypeDTO> getDraftCertificateType(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftCertificateType : {}", id);
        Optional<DraftCertificateTypeDTO> draftCertificateTypeDTO = draftCertificateTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftCertificateTypeDTO);
    }

    /**
     * {@code DELETE  /draft-certificate-types/:id} : delete the "id" draftCertificateType.
     *
     * @param id the id of the draftCertificateTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftCertificateType(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftCertificateType : {}", id);
        draftCertificateTypeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
