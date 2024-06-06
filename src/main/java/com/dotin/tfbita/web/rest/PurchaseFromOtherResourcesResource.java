package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.domain.PurchaseFromOtherResources;
import com.dotin.tfbita.repository.PurchaseFromOtherResourcesRepository;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.PurchaseFromOtherResources}.
 */
@RestController
@RequestMapping("/api/purchase-from-other-resources")
@Transactional
public class PurchaseFromOtherResourcesResource {

    private final Logger log = LoggerFactory.getLogger(PurchaseFromOtherResourcesResource.class);

    private static final String ENTITY_NAME = "purchaseFromOtherResources";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PurchaseFromOtherResourcesRepository purchaseFromOtherResourcesRepository;

    public PurchaseFromOtherResourcesResource(PurchaseFromOtherResourcesRepository purchaseFromOtherResourcesRepository) {
        this.purchaseFromOtherResourcesRepository = purchaseFromOtherResourcesRepository;
    }

    /**
     * {@code POST  /purchase-from-other-resources} : Create a new purchaseFromOtherResources.
     *
     * @param purchaseFromOtherResources the purchaseFromOtherResources to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new purchaseFromOtherResources, or with status {@code 400 (Bad Request)} if the purchaseFromOtherResources has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<PurchaseFromOtherResources> createPurchaseFromOtherResources(
        @RequestBody PurchaseFromOtherResources purchaseFromOtherResources
    ) throws URISyntaxException {
        log.debug("REST request to save PurchaseFromOtherResources : {}", purchaseFromOtherResources);
        if (purchaseFromOtherResources.getId() != null) {
            throw new BadRequestAlertException("A new purchaseFromOtherResources cannot already have an ID", ENTITY_NAME, "idexists");
        }
        purchaseFromOtherResources = purchaseFromOtherResourcesRepository.save(purchaseFromOtherResources);
        return ResponseEntity.created(new URI("/api/purchase-from-other-resources/" + purchaseFromOtherResources.getId()))
            .headers(
                HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, purchaseFromOtherResources.getId().toString())
            )
            .body(purchaseFromOtherResources);
    }

    /**
     * {@code PUT  /purchase-from-other-resources/:id} : Updates an existing purchaseFromOtherResources.
     *
     * @param id the id of the purchaseFromOtherResources to save.
     * @param purchaseFromOtherResources the purchaseFromOtherResources to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseFromOtherResources,
     * or with status {@code 400 (Bad Request)} if the purchaseFromOtherResources is not valid,
     * or with status {@code 500 (Internal Server Error)} if the purchaseFromOtherResources couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<PurchaseFromOtherResources> updatePurchaseFromOtherResources(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PurchaseFromOtherResources purchaseFromOtherResources
    ) throws URISyntaxException {
        log.debug("REST request to update PurchaseFromOtherResources : {}, {}", id, purchaseFromOtherResources);
        if (purchaseFromOtherResources.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, purchaseFromOtherResources.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseFromOtherResourcesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        purchaseFromOtherResources = purchaseFromOtherResourcesRepository.save(purchaseFromOtherResources);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, purchaseFromOtherResources.getId().toString()))
            .body(purchaseFromOtherResources);
    }

    /**
     * {@code PATCH  /purchase-from-other-resources/:id} : Partial updates given fields of an existing purchaseFromOtherResources, field will ignore if it is null
     *
     * @param id the id of the purchaseFromOtherResources to save.
     * @param purchaseFromOtherResources the purchaseFromOtherResources to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated purchaseFromOtherResources,
     * or with status {@code 400 (Bad Request)} if the purchaseFromOtherResources is not valid,
     * or with status {@code 404 (Not Found)} if the purchaseFromOtherResources is not found,
     * or with status {@code 500 (Internal Server Error)} if the purchaseFromOtherResources couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<PurchaseFromOtherResources> partialUpdatePurchaseFromOtherResources(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody PurchaseFromOtherResources purchaseFromOtherResources
    ) throws URISyntaxException {
        log.debug("REST request to partial update PurchaseFromOtherResources partially : {}, {}", id, purchaseFromOtherResources);
        if (purchaseFromOtherResources.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, purchaseFromOtherResources.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!purchaseFromOtherResourcesRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<PurchaseFromOtherResources> result = purchaseFromOtherResourcesRepository
            .findById(purchaseFromOtherResources.getId())
            .map(existingPurchaseFromOtherResources -> {
                if (purchaseFromOtherResources.getEvidenceCode() != null) {
                    existingPurchaseFromOtherResources.setEvidenceCode(purchaseFromOtherResources.getEvidenceCode());
                }
                if (purchaseFromOtherResources.getCurrencySupplierDescription() != null) {
                    existingPurchaseFromOtherResources.setCurrencySupplierDescription(
                        purchaseFromOtherResources.getCurrencySupplierDescription()
                    );
                }
                if (purchaseFromOtherResources.getAmount() != null) {
                    existingPurchaseFromOtherResources.setAmount(purchaseFromOtherResources.getAmount());
                }
                if (purchaseFromOtherResources.getPurchaseRate() != null) {
                    existingPurchaseFromOtherResources.setPurchaseRate(purchaseFromOtherResources.getPurchaseRate());
                }
                if (purchaseFromOtherResources.getOrderRegistrationAmount() != null) {
                    existingPurchaseFromOtherResources.setOrderRegistrationAmount(purchaseFromOtherResources.getOrderRegistrationAmount());
                }
                if (purchaseFromOtherResources.getRequestDate() != null) {
                    existingPurchaseFromOtherResources.setRequestDate(purchaseFromOtherResources.getRequestDate());
                }
                if (purchaseFromOtherResources.getConfirmationDate() != null) {
                    existingPurchaseFromOtherResources.setConfirmationDate(purchaseFromOtherResources.getConfirmationDate());
                }
                if (purchaseFromOtherResources.getDescription() != null) {
                    existingPurchaseFromOtherResources.setDescription(purchaseFromOtherResources.getDescription());
                }
                if (purchaseFromOtherResources.getPurchaseNumber() != null) {
                    existingPurchaseFromOtherResources.setPurchaseNumber(purchaseFromOtherResources.getPurchaseNumber());
                }
                if (purchaseFromOtherResources.getPurchaseCurrencyName() != null) {
                    existingPurchaseFromOtherResources.setPurchaseCurrencyName(purchaseFromOtherResources.getPurchaseCurrencyName());
                }

                return existingPurchaseFromOtherResources;
            })
            .map(purchaseFromOtherResourcesRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, purchaseFromOtherResources.getId().toString())
        );
    }

    /**
     * {@code GET  /purchase-from-other-resources} : get all the purchaseFromOtherResources.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of purchaseFromOtherResources in body.
     */
    @GetMapping("")
    public List<PurchaseFromOtherResources> getAllPurchaseFromOtherResources() {
        log.debug("REST request to get all PurchaseFromOtherResources");
        return purchaseFromOtherResourcesRepository.findAll();
    }

    /**
     * {@code GET  /purchase-from-other-resources/:id} : get the "id" purchaseFromOtherResources.
     *
     * @param id the id of the purchaseFromOtherResources to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the purchaseFromOtherResources, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<PurchaseFromOtherResources> getPurchaseFromOtherResources(@PathVariable("id") Long id) {
        log.debug("REST request to get PurchaseFromOtherResources : {}", id);
        Optional<PurchaseFromOtherResources> purchaseFromOtherResources = purchaseFromOtherResourcesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(purchaseFromOtherResources);
    }

    /**
     * {@code DELETE  /purchase-from-other-resources/:id} : delete the "id" purchaseFromOtherResources.
     *
     * @param id the id of the purchaseFromOtherResources to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePurchaseFromOtherResources(@PathVariable("id") Long id) {
        log.debug("REST request to delete PurchaseFromOtherResources : {}", id);
        purchaseFromOtherResourcesRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
