package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.TypeAttributeRepository;
import com.dotin.tfbita.service.TypeAttributeService;
import com.dotin.tfbita.service.dto.TypeAttributeDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.TypeAttribute}.
 */
@RestController
@RequestMapping("/api/type-attributes")
public class TypeAttributeResource {

    private final Logger log = LoggerFactory.getLogger(TypeAttributeResource.class);

    private static final String ENTITY_NAME = "typeAttribute";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TypeAttributeService typeAttributeService;

    private final TypeAttributeRepository typeAttributeRepository;

    public TypeAttributeResource(TypeAttributeService typeAttributeService, TypeAttributeRepository typeAttributeRepository) {
        this.typeAttributeService = typeAttributeService;
        this.typeAttributeRepository = typeAttributeRepository;
    }

    /**
     * {@code POST  /type-attributes} : Create a new typeAttribute.
     *
     * @param typeAttributeDTO the typeAttributeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new typeAttributeDTO, or with status {@code 400 (Bad Request)} if the typeAttribute has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TypeAttributeDTO> createTypeAttribute(@RequestBody TypeAttributeDTO typeAttributeDTO) throws URISyntaxException {
        log.debug("REST request to save TypeAttribute : {}", typeAttributeDTO);
        if (typeAttributeDTO.getId() != null) {
            throw new BadRequestAlertException("A new typeAttribute cannot already have an ID", ENTITY_NAME, "idexists");
        }
        typeAttributeDTO = typeAttributeService.save(typeAttributeDTO);
        return ResponseEntity.created(new URI("/api/type-attributes/" + typeAttributeDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, typeAttributeDTO.getId().toString()))
            .body(typeAttributeDTO);
    }

    /**
     * {@code PUT  /type-attributes/:id} : Updates an existing typeAttribute.
     *
     * @param id the id of the typeAttributeDTO to save.
     * @param typeAttributeDTO the typeAttributeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeAttributeDTO,
     * or with status {@code 400 (Bad Request)} if the typeAttributeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the typeAttributeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TypeAttributeDTO> updateTypeAttribute(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TypeAttributeDTO typeAttributeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TypeAttribute : {}, {}", id, typeAttributeDTO);
        if (typeAttributeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typeAttributeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typeAttributeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        typeAttributeDTO = typeAttributeService.update(typeAttributeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeAttributeDTO.getId().toString()))
            .body(typeAttributeDTO);
    }

    /**
     * {@code PATCH  /type-attributes/:id} : Partial updates given fields of an existing typeAttribute, field will ignore if it is null
     *
     * @param id the id of the typeAttributeDTO to save.
     * @param typeAttributeDTO the typeAttributeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated typeAttributeDTO,
     * or with status {@code 400 (Bad Request)} if the typeAttributeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the typeAttributeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the typeAttributeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TypeAttributeDTO> partialUpdateTypeAttribute(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TypeAttributeDTO typeAttributeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TypeAttribute partially : {}, {}", id, typeAttributeDTO);
        if (typeAttributeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, typeAttributeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!typeAttributeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TypeAttributeDTO> result = typeAttributeService.partialUpdate(typeAttributeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, typeAttributeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /type-attributes} : get all the typeAttributes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of typeAttributes in body.
     */
    @GetMapping("")
    public List<TypeAttributeDTO> getAllTypeAttributes() {
        log.debug("REST request to get all TypeAttributes");
        return typeAttributeService.findAll();
    }

    /**
     * {@code GET  /type-attributes/:id} : get the "id" typeAttribute.
     *
     * @param id the id of the typeAttributeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the typeAttributeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TypeAttributeDTO> getTypeAttribute(@PathVariable("id") Long id) {
        log.debug("REST request to get TypeAttribute : {}", id);
        Optional<TypeAttributeDTO> typeAttributeDTO = typeAttributeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(typeAttributeDTO);
    }

    /**
     * {@code DELETE  /type-attributes/:id} : delete the "id" typeAttribute.
     *
     * @param id the id of the typeAttributeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeAttribute(@PathVariable("id") Long id) {
        log.debug("REST request to delete TypeAttribute : {}", id);
        typeAttributeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
