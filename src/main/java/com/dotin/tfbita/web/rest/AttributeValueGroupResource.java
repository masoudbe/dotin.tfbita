package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.AttributeValueGroupRepository;
import com.dotin.tfbita.service.AttributeValueGroupService;
import com.dotin.tfbita.service.dto.AttributeValueGroupDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.AttributeValueGroup}.
 */
@RestController
@RequestMapping("/api/attribute-value-groups")
public class AttributeValueGroupResource {

    private final Logger log = LoggerFactory.getLogger(AttributeValueGroupResource.class);

    private static final String ENTITY_NAME = "attributeValueGroup";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AttributeValueGroupService attributeValueGroupService;

    private final AttributeValueGroupRepository attributeValueGroupRepository;

    public AttributeValueGroupResource(
        AttributeValueGroupService attributeValueGroupService,
        AttributeValueGroupRepository attributeValueGroupRepository
    ) {
        this.attributeValueGroupService = attributeValueGroupService;
        this.attributeValueGroupRepository = attributeValueGroupRepository;
    }

    /**
     * {@code POST  /attribute-value-groups} : Create a new attributeValueGroup.
     *
     * @param attributeValueGroupDTO the attributeValueGroupDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new attributeValueGroupDTO, or with status {@code 400 (Bad Request)} if the attributeValueGroup has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<AttributeValueGroupDTO> createAttributeValueGroup(@RequestBody AttributeValueGroupDTO attributeValueGroupDTO)
        throws URISyntaxException {
        log.debug("REST request to save AttributeValueGroup : {}", attributeValueGroupDTO);
        if (attributeValueGroupDTO.getId() != null) {
            throw new BadRequestAlertException("A new attributeValueGroup cannot already have an ID", ENTITY_NAME, "idexists");
        }
        attributeValueGroupDTO = attributeValueGroupService.save(attributeValueGroupDTO);
        return ResponseEntity.created(new URI("/api/attribute-value-groups/" + attributeValueGroupDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, attributeValueGroupDTO.getId().toString()))
            .body(attributeValueGroupDTO);
    }

    /**
     * {@code PUT  /attribute-value-groups/:id} : Updates an existing attributeValueGroup.
     *
     * @param id the id of the attributeValueGroupDTO to save.
     * @param attributeValueGroupDTO the attributeValueGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attributeValueGroupDTO,
     * or with status {@code 400 (Bad Request)} if the attributeValueGroupDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the attributeValueGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<AttributeValueGroupDTO> updateAttributeValueGroup(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AttributeValueGroupDTO attributeValueGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to update AttributeValueGroup : {}, {}", id, attributeValueGroupDTO);
        if (attributeValueGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, attributeValueGroupDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!attributeValueGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        attributeValueGroupDTO = attributeValueGroupService.update(attributeValueGroupDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, attributeValueGroupDTO.getId().toString()))
            .body(attributeValueGroupDTO);
    }

    /**
     * {@code PATCH  /attribute-value-groups/:id} : Partial updates given fields of an existing attributeValueGroup, field will ignore if it is null
     *
     * @param id the id of the attributeValueGroupDTO to save.
     * @param attributeValueGroupDTO the attributeValueGroupDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated attributeValueGroupDTO,
     * or with status {@code 400 (Bad Request)} if the attributeValueGroupDTO is not valid,
     * or with status {@code 404 (Not Found)} if the attributeValueGroupDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the attributeValueGroupDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<AttributeValueGroupDTO> partialUpdateAttributeValueGroup(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody AttributeValueGroupDTO attributeValueGroupDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update AttributeValueGroup partially : {}, {}", id, attributeValueGroupDTO);
        if (attributeValueGroupDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, attributeValueGroupDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!attributeValueGroupRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<AttributeValueGroupDTO> result = attributeValueGroupService.partialUpdate(attributeValueGroupDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, attributeValueGroupDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /attribute-value-groups} : get all the attributeValueGroups.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of attributeValueGroups in body.
     */
    @GetMapping("")
    public List<AttributeValueGroupDTO> getAllAttributeValueGroups() {
        log.debug("REST request to get all AttributeValueGroups");
        return attributeValueGroupService.findAll();
    }

    /**
     * {@code GET  /attribute-value-groups/:id} : get the "id" attributeValueGroup.
     *
     * @param id the id of the attributeValueGroupDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the attributeValueGroupDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<AttributeValueGroupDTO> getAttributeValueGroup(@PathVariable("id") Long id) {
        log.debug("REST request to get AttributeValueGroup : {}", id);
        Optional<AttributeValueGroupDTO> attributeValueGroupDTO = attributeValueGroupService.findOne(id);
        return ResponseUtil.wrapOrNotFound(attributeValueGroupDTO);
    }

    /**
     * {@code DELETE  /attribute-value-groups/:id} : delete the "id" attributeValueGroup.
     *
     * @param id the id of the attributeValueGroupDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAttributeValueGroup(@PathVariable("id") Long id) {
        log.debug("REST request to delete AttributeValueGroup : {}", id);
        attributeValueGroupService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
