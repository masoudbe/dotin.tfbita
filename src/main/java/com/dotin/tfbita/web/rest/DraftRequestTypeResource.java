package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.DraftRequestTypeRepository;
import com.dotin.tfbita.service.DraftRequestTypeService;
import com.dotin.tfbita.service.dto.DraftRequestTypeDTO;
import com.dotin.tfbita.web.rest.errors.BadRequestAlertException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dotin.tfbita.domain.DraftRequestType}.
 */
@RestController
@RequestMapping("/api/draft-request-types")
public class DraftRequestTypeResource {

    private final Logger log = LoggerFactory.getLogger(DraftRequestTypeResource.class);

    private static final String ENTITY_NAME = "draftRequestType";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DraftRequestTypeService draftRequestTypeService;

    private final DraftRequestTypeRepository draftRequestTypeRepository;

    public DraftRequestTypeResource(
        DraftRequestTypeService draftRequestTypeService,
        DraftRequestTypeRepository draftRequestTypeRepository
    ) {
        this.draftRequestTypeService = draftRequestTypeService;
        this.draftRequestTypeRepository = draftRequestTypeRepository;
    }

    /**
     * {@code POST  /draft-request-types} : Create a new draftRequestType.
     *
     * @param draftRequestTypeDTO the draftRequestTypeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new draftRequestTypeDTO, or with status {@code 400 (Bad Request)} if the draftRequestType has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<DraftRequestTypeDTO> createDraftRequestType(@RequestBody DraftRequestTypeDTO draftRequestTypeDTO)
        throws URISyntaxException {
        log.debug("REST request to save DraftRequestType : {}", draftRequestTypeDTO);
        if (draftRequestTypeDTO.getId() != null) {
            throw new BadRequestAlertException("A new draftRequestType cannot already have an ID", ENTITY_NAME, "idexists");
        }
        draftRequestTypeDTO = draftRequestTypeService.save(draftRequestTypeDTO);
        return ResponseEntity.created(new URI("/api/draft-request-types/" + draftRequestTypeDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, draftRequestTypeDTO.getId().toString()))
            .body(draftRequestTypeDTO);
    }

    /**
     * {@code GET  /draft-request-types} : get all the draftRequestTypes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of draftRequestTypes in body.
     */
    @GetMapping("")
    public List<DraftRequestTypeDTO> getAllDraftRequestTypes() {
        log.debug("REST request to get all DraftRequestTypes");
        return draftRequestTypeService.findAll();
    }

    /**
     * {@code GET  /draft-request-types/:id} : get the "id" draftRequestType.
     *
     * @param id the id of the draftRequestTypeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the draftRequestTypeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<DraftRequestTypeDTO> getDraftRequestType(@PathVariable("id") Long id) {
        log.debug("REST request to get DraftRequestType : {}", id);
        Optional<DraftRequestTypeDTO> draftRequestTypeDTO = draftRequestTypeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(draftRequestTypeDTO);
    }

    /**
     * {@code DELETE  /draft-request-types/:id} : delete the "id" draftRequestType.
     *
     * @param id the id of the draftRequestTypeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDraftRequestType(@PathVariable("id") Long id) {
        log.debug("REST request to delete DraftRequestType : {}", id);
        draftRequestTypeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
