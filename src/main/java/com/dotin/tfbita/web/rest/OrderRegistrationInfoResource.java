package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.OrderRegistrationInfoRepository;
import com.dotin.tfbita.service.OrderRegistrationInfoService;
import com.dotin.tfbita.service.dto.OrderRegistrationInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.OrderRegistrationInfo}.
 */
@RestController
@RequestMapping("/api/order-registration-infos")
public class OrderRegistrationInfoResource {

    private final Logger log = LoggerFactory.getLogger(OrderRegistrationInfoResource.class);

    private static final String ENTITY_NAME = "orderRegistrationInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OrderRegistrationInfoService orderRegistrationInfoService;

    private final OrderRegistrationInfoRepository orderRegistrationInfoRepository;

    public OrderRegistrationInfoResource(
        OrderRegistrationInfoService orderRegistrationInfoService,
        OrderRegistrationInfoRepository orderRegistrationInfoRepository
    ) {
        this.orderRegistrationInfoService = orderRegistrationInfoService;
        this.orderRegistrationInfoRepository = orderRegistrationInfoRepository;
    }

    /**
     * {@code POST  /order-registration-infos} : Create a new orderRegistrationInfo.
     *
     * @param orderRegistrationInfoDTO the orderRegistrationInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new orderRegistrationInfoDTO, or with status {@code 400 (Bad Request)} if the orderRegistrationInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<OrderRegistrationInfoDTO> createOrderRegistrationInfo(
        @RequestBody OrderRegistrationInfoDTO orderRegistrationInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to save OrderRegistrationInfo : {}", orderRegistrationInfoDTO);
        if (orderRegistrationInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new orderRegistrationInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        orderRegistrationInfoDTO = orderRegistrationInfoService.save(orderRegistrationInfoDTO);
        return ResponseEntity.created(new URI("/api/order-registration-infos/" + orderRegistrationInfoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, orderRegistrationInfoDTO.getId().toString()))
            .body(orderRegistrationInfoDTO);
    }

    /**
     * {@code PUT  /order-registration-infos/:id} : Updates an existing orderRegistrationInfo.
     *
     * @param id the id of the orderRegistrationInfoDTO to save.
     * @param orderRegistrationInfoDTO the orderRegistrationInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderRegistrationInfoDTO,
     * or with status {@code 400 (Bad Request)} if the orderRegistrationInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the orderRegistrationInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<OrderRegistrationInfoDTO> updateOrderRegistrationInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderRegistrationInfoDTO orderRegistrationInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update OrderRegistrationInfo : {}, {}", id, orderRegistrationInfoDTO);
        if (orderRegistrationInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderRegistrationInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderRegistrationInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        orderRegistrationInfoDTO = orderRegistrationInfoService.update(orderRegistrationInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderRegistrationInfoDTO.getId().toString()))
            .body(orderRegistrationInfoDTO);
    }

    /**
     * {@code PATCH  /order-registration-infos/:id} : Partial updates given fields of an existing orderRegistrationInfo, field will ignore if it is null
     *
     * @param id the id of the orderRegistrationInfoDTO to save.
     * @param orderRegistrationInfoDTO the orderRegistrationInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated orderRegistrationInfoDTO,
     * or with status {@code 400 (Bad Request)} if the orderRegistrationInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the orderRegistrationInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the orderRegistrationInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<OrderRegistrationInfoDTO> partialUpdateOrderRegistrationInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody OrderRegistrationInfoDTO orderRegistrationInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update OrderRegistrationInfo partially : {}, {}", id, orderRegistrationInfoDTO);
        if (orderRegistrationInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, orderRegistrationInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!orderRegistrationInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<OrderRegistrationInfoDTO> result = orderRegistrationInfoService.partialUpdate(orderRegistrationInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, orderRegistrationInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /order-registration-infos} : get all the orderRegistrationInfos.
     *
     * @param eagerload flag to eager load entities from relationships (This is applicable for many-to-many).
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of orderRegistrationInfos in body.
     */
    @GetMapping("")
    public List<OrderRegistrationInfoDTO> getAllOrderRegistrationInfos(
        @RequestParam(name = "eagerload", required = false, defaultValue = "true") boolean eagerload
    ) {
        log.debug("REST request to get all OrderRegistrationInfos");
        return orderRegistrationInfoService.findAll();
    }

    /**
     * {@code GET  /order-registration-infos/:id} : get the "id" orderRegistrationInfo.
     *
     * @param id the id of the orderRegistrationInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the orderRegistrationInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<OrderRegistrationInfoDTO> getOrderRegistrationInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get OrderRegistrationInfo : {}", id);
        Optional<OrderRegistrationInfoDTO> orderRegistrationInfoDTO = orderRegistrationInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(orderRegistrationInfoDTO);
    }

    /**
     * {@code DELETE  /order-registration-infos/:id} : delete the "id" orderRegistrationInfo.
     *
     * @param id the id of the orderRegistrationInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderRegistrationInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete OrderRegistrationInfo : {}", id);
        orderRegistrationInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
