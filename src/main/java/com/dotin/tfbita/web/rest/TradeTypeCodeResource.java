package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.TradeTypeCodeRepository;
import com.dotin.tfbita.service.TradeTypeCodeService;
import com.dotin.tfbita.service.dto.TradeTypeCodeDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.TradeTypeCode}.
 */
@RestController
@RequestMapping("/api/trade-type-codes")
public class TradeTypeCodeResource {

    private final Logger log = LoggerFactory.getLogger(TradeTypeCodeResource.class);

    private static final String ENTITY_NAME = "tradeTypeCode";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TradeTypeCodeService tradeTypeCodeService;

    private final TradeTypeCodeRepository tradeTypeCodeRepository;

    public TradeTypeCodeResource(TradeTypeCodeService tradeTypeCodeService, TradeTypeCodeRepository tradeTypeCodeRepository) {
        this.tradeTypeCodeService = tradeTypeCodeService;
        this.tradeTypeCodeRepository = tradeTypeCodeRepository;
    }

    /**
     * {@code POST  /trade-type-codes} : Create a new tradeTypeCode.
     *
     * @param tradeTypeCodeDTO the tradeTypeCodeDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new tradeTypeCodeDTO, or with status {@code 400 (Bad Request)} if the tradeTypeCode has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<TradeTypeCodeDTO> createTradeTypeCode(@RequestBody TradeTypeCodeDTO tradeTypeCodeDTO) throws URISyntaxException {
        log.debug("REST request to save TradeTypeCode : {}", tradeTypeCodeDTO);
        if (tradeTypeCodeDTO.getId() != null) {
            throw new BadRequestAlertException("A new tradeTypeCode cannot already have an ID", ENTITY_NAME, "idexists");
        }
        tradeTypeCodeDTO = tradeTypeCodeService.save(tradeTypeCodeDTO);
        return ResponseEntity.created(new URI("/api/trade-type-codes/" + tradeTypeCodeDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, tradeTypeCodeDTO.getId().toString()))
            .body(tradeTypeCodeDTO);
    }

    /**
     * {@code PUT  /trade-type-codes/:id} : Updates an existing tradeTypeCode.
     *
     * @param id the id of the tradeTypeCodeDTO to save.
     * @param tradeTypeCodeDTO the tradeTypeCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradeTypeCodeDTO,
     * or with status {@code 400 (Bad Request)} if the tradeTypeCodeDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the tradeTypeCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<TradeTypeCodeDTO> updateTradeTypeCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TradeTypeCodeDTO tradeTypeCodeDTO
    ) throws URISyntaxException {
        log.debug("REST request to update TradeTypeCode : {}, {}", id, tradeTypeCodeDTO);
        if (tradeTypeCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradeTypeCodeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tradeTypeCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        tradeTypeCodeDTO = tradeTypeCodeService.update(tradeTypeCodeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tradeTypeCodeDTO.getId().toString()))
            .body(tradeTypeCodeDTO);
    }

    /**
     * {@code PATCH  /trade-type-codes/:id} : Partial updates given fields of an existing tradeTypeCode, field will ignore if it is null
     *
     * @param id the id of the tradeTypeCodeDTO to save.
     * @param tradeTypeCodeDTO the tradeTypeCodeDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated tradeTypeCodeDTO,
     * or with status {@code 400 (Bad Request)} if the tradeTypeCodeDTO is not valid,
     * or with status {@code 404 (Not Found)} if the tradeTypeCodeDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the tradeTypeCodeDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<TradeTypeCodeDTO> partialUpdateTradeTypeCode(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody TradeTypeCodeDTO tradeTypeCodeDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update TradeTypeCode partially : {}, {}", id, tradeTypeCodeDTO);
        if (tradeTypeCodeDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, tradeTypeCodeDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!tradeTypeCodeRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<TradeTypeCodeDTO> result = tradeTypeCodeService.partialUpdate(tradeTypeCodeDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, tradeTypeCodeDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /trade-type-codes} : get all the tradeTypeCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of tradeTypeCodes in body.
     */
    @GetMapping("")
    public List<TradeTypeCodeDTO> getAllTradeTypeCodes() {
        log.debug("REST request to get all TradeTypeCodes");
        return tradeTypeCodeService.findAll();
    }

    /**
     * {@code GET  /trade-type-codes/:id} : get the "id" tradeTypeCode.
     *
     * @param id the id of the tradeTypeCodeDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the tradeTypeCodeDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<TradeTypeCodeDTO> getTradeTypeCode(@PathVariable("id") Long id) {
        log.debug("REST request to get TradeTypeCode : {}", id);
        Optional<TradeTypeCodeDTO> tradeTypeCodeDTO = tradeTypeCodeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(tradeTypeCodeDTO);
    }

    /**
     * {@code DELETE  /trade-type-codes/:id} : delete the "id" tradeTypeCode.
     *
     * @param id the id of the tradeTypeCodeDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTradeTypeCode(@PathVariable("id") Long id) {
        log.debug("REST request to delete TradeTypeCode : {}", id);
        tradeTypeCodeService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
