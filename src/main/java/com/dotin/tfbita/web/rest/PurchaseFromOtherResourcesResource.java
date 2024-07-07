package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.PurchaseFromOtherResourcesRepository;
import com.dotin.tfbita.service.PurchaseFromOtherResourcesService;
import com.dotin.tfbita.service.dto.PurchaseFromOtherResourcesDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.PurchaseFromOtherResources}.
 */
@RestController
@RequestMapping("/api/purchase-from-other-resources")
public class PurchaseFromOtherResourcesResource {

    private static final Logger log = LoggerFactory.getLogger(PurchaseFromOtherResourcesResource.class);

    private static final String ENTITY_NAME = "purchaseFromOtherResources";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseFromOtherResourcesService purchaseFromOtherResourcesService;

    private final PurchaseFromOtherResourcesRepository purchaseFromOtherResourcesRepository;

    public PurchaseFromOtherResourcesResource(
        PurchaseFromOtherResourcesService purchaseFromOtherResourcesService,
        PurchaseFromOtherResourcesRepository purchaseFromOtherResourcesRepository
    ) {
        this.purchaseFromOtherResourcesService = purchaseFromOtherResourcesService;
        this.purchaseFromOtherResourcesRepository = purchaseFromOtherResourcesRepository;
    }

    /**
     * {@code POST  /purchase-from-other-resources} : Create a new purchaseFromOtherResources.
     *
     * @param purchaseFromOtherResourcesDTO the purchaseFromOtherResourcesDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseFromOtherResourcesDTO, or with status {@code 400 (Bad Request)} if the purchaseFromOtherResources has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PurchaseFromOtherResourcesDTO> createPurchaseFromOtherResources(
        @RequestBody PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO
    ) throws URISyntaxException {
        log.debug("REST request to save PurchaseFromOtherResources : {}", purchaseFromOtherResourcesDTO);
        if (purchaseFromOtherResourcesDTO.getId() != null) {
            throw new BadRequestAlertException("A new purchaseFromOtherResources cannot already have an ID", ENTITY_NAME, "idexists");
        }
        purchaseFromOtherResourcesDTO = purchaseFromOtherResourcesService.save(purchaseFromOtherResourcesDTO);
        return ResponseEntity.created(new URI("/api/purchase-from-other-resources/" + purchaseFromOtherResourcesDTO.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, purchaseFromOtherResourcesDTO.getId().toString())
            )
            .body(purchaseFromOtherResourcesDTO);
    }

    /**
     * {@code PUT  /purchase-from-other-resources/:id} : Updates an existing purchaseFromOtherResources.
     *
     * @param id the id of the purchaseFromOtherResourcesDTO to save.
     * @param purchaseFromOtherResourcesDTO the purchaseFromOtherResourcesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseFromOtherResourcesDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseFromOtherResourcesDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the purchaseFromOtherResourcesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseFromOtherResourcesDTO> updatePurchaseFromOtherResources(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO
    ) throws URISyntaxException {
        log.debug("REST request to update PurchaseFromOtherResources : {}, {}", id, purchaseFromOtherResourcesDTO);
        if (purchaseFromOtherResourcesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, purchaseFromOtherResourcesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseFromOtherResourcesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        purchaseFromOtherResourcesDTO = purchaseFromOtherResourcesService.update(purchaseFromOtherResourcesDTO);
        return ResponseEntity.ok()
            .headers(
                HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, purchaseFromOtherResourcesDTO.getId().toString())
            )
            .body(purchaseFromOtherResourcesDTO);
    }

    /**
     * {@code PATCH  /purchase-from-other-resources/:id} : Partial updates given fields of an existing purchaseFromOtherResources, field will ignore if it is null
     *
     * @param id the id of the purchaseFromOtherResourcesDTO to save.
     * @param purchaseFromOtherResourcesDTO the purchaseFromOtherResourcesDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseFromOtherResourcesDTO,
     * or with status {@code 400 (Bad Request)} if the purchaseFromOtherResourcesDTO is not valid,
     * or with status {@code 404 (Not Found)} if the purchaseFromOtherResourcesDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the purchaseFromOtherResourcesDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PurchaseFromOtherResourcesDTO> partialUpdatePurchaseFromOtherResources(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PurchaseFromOtherResourcesDTO purchaseFromOtherResourcesDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update PurchaseFromOtherResources partially : {}, {}", id, purchaseFromOtherResourcesDTO);
        if (purchaseFromOtherResourcesDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, purchaseFromOtherResourcesDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseFromOtherResourcesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PurchaseFromOtherResourcesDTO> result = purchaseFromOtherResourcesService.partialUpdate(purchaseFromOtherResourcesDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, purchaseFromOtherResourcesDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /purchase-from-other-resources} : get all the purchaseFromOtherResources.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseFromOtherResources in body.
     */
    @GetMapping("")
    public List<PurchaseFromOtherResourcesDTO> getAllPurchaseFromOtherResources() {
        log.debug("REST request to get all PurchaseFromOtherResources");
        return purchaseFromOtherResourcesService.findAll();
    }

    /**
     * {@code GET  /purchase-from-other-resources/:id} : get the "id" purchaseFromOtherResources.
     *
     * @param id the id of the purchaseFromOtherResourcesDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseFromOtherResourcesDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseFromOtherResourcesDTO> getPurchaseFromOtherResources(@PathVariable("id") Long id) {
        log.debug("REST request to get PurchaseFromOtherResources : {}", id);
        Optional<PurchaseFromOtherResourcesDTO> purchaseFromOtherResourcesDTO = purchaseFromOtherResourcesService.findOne(id);
        return ResponseUtil.wrapOrNotFound(purchaseFromOtherResourcesDTO);
    }

    /**
     * {@code DELETE  /purchase-from-other-resources/:id} : delete the "id" purchaseFromOtherResources.
     *
     * @param id the id of the purchaseFromOtherResourcesDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseFromOtherResources(@PathVariable("id") Long id) {
        log.debug("REST request to delete PurchaseFromOtherResources : {}", id);
        purchaseFromOtherResourcesService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
