package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.domain.OrderRegServ;
import com.dotin.tfbita.repository.OrderRegServRepository;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.OrderRegServ}.
 */
@RestController
@RequestMapping("/api/order-reg-servs")
@Transactional
public class OrderRegServResource {

    private final Logger log = LoggerFactory.getLogger(OrderRegServResource.class);

    private static final String ENTITY_NAME = "orderRegServ";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderRegServRepository orderRegServRepository;

    public OrderRegServResource(OrderRegServRepository orderRegServRepository) {
        this.orderRegServRepository = orderRegServRepository;
    }

    /**
     * {@code POST  /order-reg-servs} : Create a new orderRegServ.
     *
     * @param orderRegServ the orderRegServ to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderRegServ, or with status {@code 400 (Bad Request)} if the orderRegServ has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OrderRegServ> createOrderRegServ(@RequestBody OrderRegServ orderRegServ) throws URISyntaxException {
        log.debug("REST request to save OrderRegServ : {}", orderRegServ);
        if (orderRegServ.getId() != null) {
            throw new BadRequestAlertException("A new orderRegServ cannot already have an ID", ENTITY_NAME, "idexists");
        }
        orderRegServ = orderRegServRepository.save(orderRegServ);
        return ResponseEntity.created(new URI("/api/order-reg-servs/" + orderRegServ.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, orderRegServ.getId().toString()))
            .body(orderRegServ);
    }

    /**
     * {@code PUT  /order-reg-servs/:id} : Updates an existing orderRegServ.
     *
     * @param id the id of the orderRegServ to save.
     * @param orderRegServ the orderRegServ to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderRegServ,
     * or with status {@code 400 (Bad Request)} if the orderRegServ is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderRegServ couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderRegServ> updateOrderRegServ(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderRegServ orderRegServ
    ) throws URISyntaxException {
        log.debug("REST request to update OrderRegServ : {}, {}", id, orderRegServ);
        if (orderRegServ.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderRegServ.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderRegServRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        orderRegServ = orderRegServRepository.save(orderRegServ);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderRegServ.getId().toString()))
            .body(orderRegServ);
    }

    /**
     * {@code PATCH  /order-reg-servs/:id} : Partial updates given fields of an existing orderRegServ, field will ignore if it is null
     *
     * @param id the id of the orderRegServ to save.
     * @param orderRegServ the orderRegServ to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderRegServ,
     * or with status {@code 400 (Bad Request)} if the orderRegServ is not valid,
     * or with status {@code 404 (Not Found)} if the orderRegServ is not found,
     * or with status {@code 500 (Internal Server Error)} if the orderRegServ couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OrderRegServ> partialUpdateOrderRegServ(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderRegServ orderRegServ
    ) throws URISyntaxException {
        log.debug("REST request to partial update OrderRegServ partially : {}, {}", id, orderRegServ);
        if (orderRegServ.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderRegServ.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderRegServRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OrderRegServ> result = orderRegServRepository
            .findById(orderRegServ.getId())
            .map(existingOrderRegServ -> {
                if (orderRegServ.getAmount() != null) {
                    existingOrderRegServ.setAmount(orderRegServ.getAmount());
                }
                if (orderRegServ.getCurrencyAmount() != null) {
                    existingOrderRegServ.setCurrencyAmount(orderRegServ.getCurrencyAmount());
                }
                if (orderRegServ.getUnit() != null) {
                    existingOrderRegServ.setUnit(orderRegServ.getUnit());
                }
                if (orderRegServ.getTitle() != null) {
                    existingOrderRegServ.setTitle(orderRegServ.getTitle());
                }
                if (orderRegServ.getCode() != null) {
                    existingOrderRegServ.setCode(orderRegServ.getCode());
                }

                return existingOrderRegServ;
            })
            .map(orderRegServRepository::save);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderRegServ.getId().toString())
        );
    }

    /**
     * {@code GET  /order-reg-servs} : get all the orderRegServs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderRegServs in body.
     */
    @GetMapping("")
    public List<OrderRegServ> getAllOrderRegServs() {
        log.debug("REST request to get all OrderRegServs");
        return orderRegServRepository.findAll();
    }

    /**
     * {@code GET  /order-reg-servs/:id} : get the "id" orderRegServ.
     *
     * @param id the id of the orderRegServ to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderRegServ, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderRegServ> getOrderRegServ(@PathVariable("id") Long id) {
        log.debug("REST request to get OrderRegServ : {}", id);
        Optional<OrderRegServ> orderRegServ = orderRegServRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(orderRegServ);
    }

    /**
     * {@code DELETE  /order-reg-servs/:id} : delete the "id" orderRegServ.
     *
     * @param id the id of the orderRegServ to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderRegServ(@PathVariable("id") Long id) {
        log.debug("REST request to delete OrderRegServ : {}", id);
        orderRegServRepository.deleteById(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
