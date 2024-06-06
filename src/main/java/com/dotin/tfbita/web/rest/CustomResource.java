package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.domain.Custom;
import com.dotin.tfbita.repository.CustomRepository;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link com.dotin.tfbita.domain.Custom}.
 */
@RestController
@RequestMapping("/api/customs")
@Transactional
public class CustomResource {

    private final Logger log = LoggerFactory.getLogger(CustomResource.class);

    private static final String ENTITY_NAME = "custom";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomRepository customRepository;

    public CustomResource(CustomRepository customRepository) {
        this.customRepository = customRepository;
    }

    /**
     * {@code POST  /customs} : Create a new custom.
     *
     * @param custom the custom to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new custom, or with status {@code 400 (Bad Request)} if the custom has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<Custom> createCustom(@RequestBody Custom custom) throws URISyntaxException {
        log.debug("REST request to save Custom : {}", custom);
        if (custom.getId() != null) {
            throw new BadRequestAlertException("A new custom cannot already have an ID", ENTITY_NAME, "idexists");
        }
        custom = customRepository.save(custom);
        return ResponseEntity.created(new URI("/api/customs/" + custom.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, custom.getId().toString()))
            .body(custom);
    }

    /**
     * {@code PUT  /customs/:id} : Updates an existing custom.
     *
     * @param id the id of the custom to save.
     * @param custom the custom to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated custom,
     * or with status {@code 400 (Bad Request)} if the custom is not valid,
     * or with status {@code 500 (Internal Server Error)} if the custom couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Custom> updateCustom(@PathVariable(value = "id", required = false) final Long id, @RequestBody Custom custom)
        throws URISyntaxException {
        log.debug("REST request to update Custom : {}, {}", id, custom);
        if (custom.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, custom.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        custom = customRepository.save(custom);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, custom.getId().toString()))
            .body(custom);
    }

    /**
     * {@code PATCH  /customs/:id} : Partial updates given fields of an existing custom, field will ignore if it is null
     *
     * @param id the id of the custom to save.
     * @param custom the custom to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated custom,
     * or with status {@code 400 (Bad Request)} if the custom is not valid,
     * or with status {@code 404 (Not Found)} if the custom is not found,
     * or with status {@code 500 (Internal Server Error)} if the custom couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<Custom> partialUpdateCustom(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody Custom custom
    ) throws URISyntaxException {
        log.debug("REST request to partial update Custom partially : {}, {}", id, custom);
        if (custom.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, custom.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!customRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<Custom> result = customRepository
            .findById(custom.getId())
            .map(existingCustom -> {
                if (custom.getModificationDate() != null) {
                    existingCustom.setModificationDate(custom.getModificationDate());
                }
                if (custom.getLatinName() != null) {
                    existingCustom.setLatinName(custom.getLatinName());
                }
                if (custom.getName() != null) {
                    existingCustom.setName(custom.getName());
                }
                if (custom.getTempId() != null) {
                    existingCustom.setTempId(custom.getTempId());
                }

                return existingCustom;
            })
            .map(customRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, custom.getId().toString())
        );
    }

    /**
     * {@code GET  /customs} : get all the customs.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customs in body.
     */
    @GetMapping("")
    public List<Custom> getAllCustoms(@RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload) {
        log.debug("REST request to get all Customs");
        if (eagerload) {
            return customRepository.findAllWithEagerRelationships();
        } else {
            return customRepository.findAll();
        }
    }

    /**
     * {@code GET  /customs/:id} : get the "id" custom.
     *
     * @param id the id of the custom to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the custom, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Custom> getCustom(@PathVariable("id") Long id) {
        log.debug("REST request to get Custom : {}", id);
        Optional<Custom> custom = customRepository.findOneWithEagerRelationships(id);
        return ResponseUtil.wrapOrNotFound(custom);
    }

    /**
     * {@code DELETE  /customs/:id} : delete the "id" custom.
     *
     * @param id the id of the custom to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustom(@PathVariable("id") Long id) {
        log.debug("REST request to delete Custom : {}", id);
        customRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
