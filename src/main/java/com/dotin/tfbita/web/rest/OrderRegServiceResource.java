package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.OrderRegServiceRepository;
import com.dotin.tfbita.service.OrderRegServiceService;
import com.dotin.tfbita.service.dto.OrderRegServiceDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.OrderRegService}.
 */
@RestController
@RequestMapping("/api/order-reg-services")
public class OrderRegServiceResource {

    private final Logger log = LoggerFactory.getLogger(OrderRegServiceResource.class);

    private static final String ENTITY_NAME = "orderRegService";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderRegServiceService orderRegServiceService;

    private final OrderRegServiceRepository orderRegServiceRepository;

    public OrderRegServiceResource(OrderRegServiceService orderRegServiceService, OrderRegServiceRepository orderRegServiceRepository) {
        this.orderRegServiceService = orderRegServiceService;
        this.orderRegServiceRepository = orderRegServiceRepository;
    }

    /**
     * {@code POST  /order-reg-services} : Create a new orderRegService.
     *
     * @param orderRegServiceDTO the orderRegServiceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderRegServiceDTO, or with status {@code 400 (Bad Request)} if the orderRegService has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OrderRegServiceDTO> createOrderRegService(@RequestBody OrderRegServiceDTO orderRegServiceDTO)
        throws URISyntaxException {
        log.debug("REST request to save OrderRegService : {}", orderRegServiceDTO);
        if (orderRegServiceDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderRegService cannot already have an ID", ENTITY_NAME, "idexists");
        }
        orderRegServiceDTO = orderRegServiceService.save(orderRegServiceDTO);
        return ResponseEntity.created(new URI("/api/order-reg-services/" + orderRegServiceDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, orderRegServiceDTO.getId().toString()))
            .body(orderRegServiceDTO);
    }

    /**
     * {@code PUT  /order-reg-services/:id} : Updates an existing orderRegService.
     *
     * @param id the id of the orderRegServiceDTO to save.
     * @param orderRegServiceDTO the orderRegServiceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderRegServiceDTO,
     * or with status {@code 400 (Bad Request)} if the orderRegServiceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderRegServiceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderRegServiceDTO> updateOrderRegService(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderRegServiceDTO orderRegServiceDTO
    ) throws URISyntaxException {
        log.debug("REST request to update OrderRegService : {}, {}", id, orderRegServiceDTO);
        if (orderRegServiceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderRegServiceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderRegServiceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        orderRegServiceDTO = orderRegServiceService.update(orderRegServiceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderRegServiceDTO.getId().toString()))
            .body(orderRegServiceDTO);
    }

    /**
     * {@code PATCH  /order-reg-services/:id} : Partial updates given fields of an existing orderRegService, field will ignore if it is null
     *
     * @param id the id of the orderRegServiceDTO to save.
     * @param orderRegServiceDTO the orderRegServiceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderRegServiceDTO,
     * or with status {@code 400 (Bad Request)} if the orderRegServiceDTO is not valid,
     * or with status {@code 404 (Not Found)} if the orderRegServiceDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the orderRegServiceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OrderRegServiceDTO> partialUpdateOrderRegService(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderRegServiceDTO orderRegServiceDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update OrderRegService partially : {}, {}", id, orderRegServiceDTO);
        if (orderRegServiceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderRegServiceDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderRegServiceRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OrderRegServiceDTO> result = orderRegServiceService.partialUpdate(orderRegServiceDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderRegServiceDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /order-reg-services} : get all the orderRegServices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderRegServices in body.
     */
    @GetMapping("")
    public List<OrderRegServiceDTO> getAllOrderRegServices() {
        log.debug("REST request to get all OrderRegServices");
        return orderRegServiceService.findAll();
    }

    /**
     * {@code GET  /order-reg-services/:id} : get the "id" orderRegService.
     *
     * @param id the id of the orderRegServiceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderRegServiceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderRegServiceDTO> getOrderRegService(@PathVariable("id") Long id) {
        log.debug("REST request to get OrderRegService : {}", id);
        Optional<OrderRegServiceDTO> orderRegServiceDTO = orderRegServiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderRegServiceDTO);
    }

    /**
     * {@code DELETE  /order-reg-services/:id} : delete the "id" orderRegService.
     *
     * @param id the id of the orderRegServiceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderRegService(@PathVariable("id") Long id) {
        log.debug("REST request to delete OrderRegService : {}", id);
        orderRegServiceService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
