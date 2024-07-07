package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftTypeRepository;
import com.dotin.tfbita.service.DraftTypeService;
import com.dotin.tfbita.service.dto.DraftTypeDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftType}.
 */
@RestController
@RequestMapping("/api/draft-types")
public class DraftTypeResource {

    private static final Logger log = LoggerFactory.getLogger(DraftTypeResource.class);

    private static final String ENTITY_NAME = "draftType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftTypeService draftTypeService;

    private final DraftTypeRepository draftTypeRepository;

    public DraftTypeResource(DraftTypeService draftTypeService, DraftTypeRepository draftTypeRepository) {
        this.draftTypeService = draftTypeService;
        this.draftTypeRepository = draftTypeRepository;
    }

    /**
     * {@code POST  /draft-types} : Create a new draftType.
     *
     * @param draftTypeDTO the draftTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftTypeDTO, or with status {@code 400 (Bad Request)} if the draftType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftTypeDTO> createDraftType(@RequestBody DraftTypeDTO draftTypeDTO) throws URISyntaxException {
        log.debug("REST request to save DraftType : {}", draftTypeDTO);
        if (draftTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftTypeDTO = draftTypeService.save(draftTypeDTO);
        return ResponseEntity.created(new URI("/api/draft-types/" + draftTypeDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftTypeDTO.getId().toString()))
            .body(draftTypeDTO);
    }

    /**
     * {@code PUT  /draft-types/:id} : Updates an existing draftType.
     *
     * @param id the id of the draftTypeDTO to save.
     * @param draftTypeDTO the draftTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftTypeDTO,
     * or with status {@code 400 (Bad Request)} if the draftTypeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the draftTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<DraftTypeDTO> updateDraftType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftTypeDTO draftTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update DraftType : {}, {}", id, draftTypeDTO);
        if (draftTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        draftTypeDTO = draftTypeService.update(draftTypeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftTypeDTO.getId().toString()))
            .body(draftTypeDTO);
    }

    /**
     * {@code PATCH  /draft-types/:id} : Partial updates given fields of an existing draftType, field will ignore if it is null
     *
     * @param id the id of the draftTypeDTO to save.
     * @param draftTypeDTO the draftTypeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated draftTypeDTO,
     * or with status {@code 400 (Bad Request)} if the draftTypeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the draftTypeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the draftTypeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<DraftTypeDTO> partialUpdateDraftType(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody DraftTypeDTO draftTypeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update DraftType partially : {}, {}", id, draftTypeDTO);
        if (draftTypeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, draftTypeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!draftTypeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<DraftTypeDTO> result = draftTypeService.partialUpdate(draftTypeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, draftTypeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /draft-types} : get all the draftTypes.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftTypes in body.
     */
    @GetMapping("")
    public List<DraftTypeDTO> getAllDraftTypes(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all DraftTypes");
        return draftTypeService.findAll();
    }

    /**
     * {@code GET  /draft-types/:id} : get the "id" draftType.
     *
     * @param id the id of the draftTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftTypeDTO> getDraftType(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftType : {}", id);
        Optional<DraftTypeDTO> draftTypeDTO = draftTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftTypeDTO);
    }

    /**
     * {@code DELETE  /draft-types/:id} : delete the "id" draftType.
     *
     * @param id the id of the draftTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftType(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftType : {}", id);
        draftTypeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
