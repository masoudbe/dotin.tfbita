package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.SwiftBicRepository;
import com.dotin.tfbita.service.SwiftBicService;
import com.dotin.tfbita.service.dto.SwiftBicDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.SwiftBic}.
 */
@RestController
@RequestMapping("/api/swift-bics")
public class SwiftBicResource {

    private final Logger log = LoggerFactory.getLogger(SwiftBicResource.class);

    private static final String ENTITY_NAME = "swiftBic";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SwiftBicService swiftBicService;

    private final SwiftBicRepository swiftBicRepository;

    public SwiftBicResource(SwiftBicService swiftBicService, SwiftBicRepository swiftBicRepository) {
        this.swiftBicService = swiftBicService;
        this.swiftBicRepository = swiftBicRepository;
    }

    /**
     * {@code POST  /swift-bics} : Create a new swiftBic.
     *
     * @param swiftBicDTO the swiftBicDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new swiftBicDTO, or with status {@code 400 (Bad Request)} if the swiftBic has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<SwiftBicDTO> createSwiftBic(@RequestBody SwiftBicDTO swiftBicDTO) throws URISyntaxException {
        log.debug("REST request to save SwiftBic : {}", swiftBicDTO);
        if (swiftBicDTO.getId() != null) {
            throw new BadRequestAlertException("A new swiftBic cannot already have an ID", ENTITY_NAME, "idexists");
        }
        swiftBicDTO = swiftBicService.save(swiftBicDTO);
        return ResponseEntity.created(new URI("/api/swift-bics/" + swiftBicDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, swiftBicDTO.getId().toString()))
            .body(swiftBicDTO);
    }

    /**
     * {@code PUT  /swift-bics/:id} : Updates an existing swiftBic.
     *
     * @param id the id of the swiftBicDTO to save.
     * @param swiftBicDTO the swiftBicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated swiftBicDTO,
     * or with status {@code 400 (Bad Request)} if the swiftBicDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the swiftBicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SwiftBicDTO> updateSwiftBic(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SwiftBicDTO swiftBicDTO
    ) throws URISyntaxException {
        log.debug("REST request to update SwiftBic : {}, {}", id, swiftBicDTO);
        if (swiftBicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, swiftBicDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!swiftBicRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        swiftBicDTO = swiftBicService.update(swiftBicDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, swiftBicDTO.getId().toString()))
            .body(swiftBicDTO);
    }

    /**
     * {@code PATCH  /swift-bics/:id} : Partial updates given fields of an existing swiftBic, field will ignore if it is null
     *
     * @param id the id of the swiftBicDTO to save.
     * @param swiftBicDTO the swiftBicDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated swiftBicDTO,
     * or with status {@code 400 (Bad Request)} if the swiftBicDTO is not valid,
     * or with status {@code 404 (Not Found)} if the swiftBicDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the swiftBicDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<SwiftBicDTO> partialUpdateSwiftBic(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody SwiftBicDTO swiftBicDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update SwiftBic partially : {}, {}", id, swiftBicDTO);
        if (swiftBicDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, swiftBicDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!swiftBicRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<SwiftBicDTO> result = swiftBicService.partialUpdate(swiftBicDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, swiftBicDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /swift-bics} : get all the swiftBics.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of swiftBics in body.
     */
    @GetMapping("")
    public List<SwiftBicDTO> getAllSwiftBics() {
        log.debug("REST request to get all SwiftBics");
        return swiftBicService.findAll();
    }

    /**
     * {@code GET  /swift-bics/:id} : get the "id" swiftBic.
     *
     * @param id the id of the swiftBicDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the swiftBicDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SwiftBicDTO> getSwiftBic(@PathVariable("id") Long id) {
        log.debug("REST request to get SwiftBic : {}", id);
        Optional<SwiftBicDTO> swiftBicDTO = swiftBicService.findOne(id);
        return ResponseUtil.wrapOrNotFound(swiftBicDTO);
    }

    /**
     * {@code DELETE  /swift-bics/:id} : delete the "id" swiftBic.
     *
     * @param id the id of the swiftBicDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSwiftBic(@PathVariable("id") Long id) {
        log.debug("REST request to delete SwiftBic : {}", id);
        swiftBicService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
