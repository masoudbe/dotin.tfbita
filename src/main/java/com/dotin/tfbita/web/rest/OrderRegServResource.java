package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.OrderRegServRepository;
import com.dotin.tfbita.service.OrderRegServService;
import com.dotin.tfbita.service.dto.OrderRegServDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.OrderRegServ}.
 */
@RestController
@RequestMapping("/api/order-reg-servs")
public class OrderRegServResource {

    private final Logger log = LoggerFactory.getLogger(OrderRegServResource.class);

    private static final String ENTITY_NAME = "orderRegServ";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderRegServService orderRegServService;

    private final OrderRegServRepository orderRegServRepository;

    public OrderRegServResource(OrderRegServService orderRegServService, OrderRegServRepository orderRegServRepository) {
        this.orderRegServService = orderRegServService;
        this.orderRegServRepository = orderRegServRepository;
    }

    /**
     * {@code POST  /order-reg-servs} : Create a new orderRegServ.
     *
     * @param orderRegServDTO the orderRegServDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderRegServDTO, or with status {@code 400 (Bad Request)} if the orderRegServ has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OrderRegServDTO> createOrderRegServ(@RequestBody OrderRegServDTO orderRegServDTO) throws URISyntaxException {
        log.debug("REST request to save OrderRegServ : {}", orderRegServDTO);
        if (orderRegServDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderRegServ cannot already have an ID", ENTITY_NAME, "idexists");
        }
        orderRegServDTO = orderRegServService.save(orderRegServDTO);
        return ResponseEntity.created(new URI("/api/order-reg-servs/" + orderRegServDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, orderRegServDTO.getId().toString()))
            .body(orderRegServDTO);
    }

    /**
     * {@code PUT  /order-reg-servs/:id} : Updates an existing orderRegServ.
     *
     * @param id the id of the orderRegServDTO to save.
     * @param orderRegServDTO the orderRegServDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderRegServDTO,
     * or with status {@code 400 (Bad Request)} if the orderRegServDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderRegServDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderRegServDTO> updateOrderRegServ(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderRegServDTO orderRegServDTO
    ) throws URISyntaxException {
        log.debug("REST request to update OrderRegServ : {}, {}", id, orderRegServDTO);
        if (orderRegServDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderRegServDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderRegServRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        orderRegServDTO = orderRegServService.update(orderRegServDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderRegServDTO.getId().toString()))
            .body(orderRegServDTO);
    }

    /**
     * {@code PATCH  /order-reg-servs/:id} : Partial updates given fields of an existing orderRegServ, field will ignore if it is null
     *
     * @param id the id of the orderRegServDTO to save.
     * @param orderRegServDTO the orderRegServDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderRegServDTO,
     * or with status {@code 400 (Bad Request)} if the orderRegServDTO is not valid,
     * or with status {@code 404 (Not Found)} if the orderRegServDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the orderRegServDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OrderRegServDTO> partialUpdateOrderRegServ(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderRegServDTO orderRegServDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update OrderRegServ partially : {}, {}", id, orderRegServDTO);
        if (orderRegServDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderRegServDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderRegServRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OrderRegServDTO> result = orderRegServService.partialUpdate(orderRegServDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderRegServDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /order-reg-servs} : get all the orderRegServs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderRegServs in body.
     */
    @GetMapping("")
    public List<OrderRegServDTO> getAllOrderRegServs() {
        log.debug("REST request to get all OrderRegServs");
        return orderRegServService.findAll();
    }

    /**
     * {@code GET  /order-reg-servs/:id} : get the "id" orderRegServ.
     *
     * @param id the id of the orderRegServDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderRegServDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderRegServDTO> getOrderRegServ(@PathVariable("id") Long id) {
        log.debug("REST request to get OrderRegServ : {}", id);
        Optional<OrderRegServDTO> orderRegServDTO = orderRegServService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderRegServDTO);
    }

    /**
     * {@code DELETE  /order-reg-servs/:id} : delete the "id" orderRegServ.
     *
     * @param id the id of the orderRegServDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderRegServ(@PathVariable("id") Long id) {
        log.debug("REST request to delete OrderRegServ : {}", id);
        orderRegServService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
