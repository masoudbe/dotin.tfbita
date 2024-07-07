package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.CurrencyExchangeInfoRepository;
import com.dotin.tfbita.service.CurrencyExchangeInfoService;
import com.dotin.tfbita.service.dto.CurrencyExchangeInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.CurrencyExchangeInfo}.
 */
@RestController
@RequestMapping("/api/currency-exchange-infos")
public class CurrencyExchangeInfoResource {

    private final Logger log = LoggerFactory.getLogger(CurrencyExchangeInfoResource.class);

    private static final String ENTITY_NAME = "currencyExchangeInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CurrencyExchangeInfoService currencyExchangeInfoService;

    private final CurrencyExchangeInfoRepository currencyExchangeInfoRepository;

    public CurrencyExchangeInfoResource(
        CurrencyExchangeInfoService currencyExchangeInfoService,
        CurrencyExchangeInfoRepository currencyExchangeInfoRepository
    ) {
        this.currencyExchangeInfoService = currencyExchangeInfoService;
        this.currencyExchangeInfoRepository = currencyExchangeInfoRepository;
    }

    /**
     * {@code POST  /currency-exchange-infos} : Create a new currencyExchangeInfo.
     *
     * @param currencyExchangeInfoDTO the currencyExchangeInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new currencyExchangeInfoDTO, or with status {@code 400 (Bad Request)} if the currencyExchangeInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<CurrencyExchangeInfoDTO> createCurrencyExchangeInfo(@RequestBody CurrencyExchangeInfoDTO currencyExchangeInfoDTO)
        throws URISyntaxException {
        log.debug("REST request to save CurrencyExchangeInfo : {}", currencyExchangeInfoDTO);
        if (currencyExchangeInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new currencyExchangeInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        currencyExchangeInfoDTO = currencyExchangeInfoService.save(currencyExchangeInfoDTO);
        return ResponseEntity.created(new URI("/api/currency-exchange-infos/" + currencyExchangeInfoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, currencyExchangeInfoDTO.getId().toString()))
            .body(currencyExchangeInfoDTO);
    }

    /**
     * {@code PUT  /currency-exchange-infos/:id} : Updates an existing currencyExchangeInfo.
     *
     * @param id the id of the currencyExchangeInfoDTO to save.
     * @param currencyExchangeInfoDTO the currencyExchangeInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated currencyExchangeInfoDTO,
     * or with status {@code 400 (Bad Request)} if the currencyExchangeInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the currencyExchangeInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<CurrencyExchangeInfoDTO> updateCurrencyExchangeInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CurrencyExchangeInfoDTO currencyExchangeInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update CurrencyExchangeInfo : {}, {}", id, currencyExchangeInfoDTO);
        if (currencyExchangeInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, currencyExchangeInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!currencyExchangeInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        currencyExchangeInfoDTO = currencyExchangeInfoService.update(currencyExchangeInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, currencyExchangeInfoDTO.getId().toString()))
            .body(currencyExchangeInfoDTO);
    }

    /**
     * {@code PATCH  /currency-exchange-infos/:id} : Partial updates given fields of an existing currencyExchangeInfo, field will ignore if it is null
     *
     * @param id the id of the currencyExchangeInfoDTO to save.
     * @param currencyExchangeInfoDTO the currencyExchangeInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated currencyExchangeInfoDTO,
     * or with status {@code 400 (Bad Request)} if the currencyExchangeInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the currencyExchangeInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the currencyExchangeInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<CurrencyExchangeInfoDTO> partialUpdateCurrencyExchangeInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody CurrencyExchangeInfoDTO currencyExchangeInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update CurrencyExchangeInfo partially : {}, {}", id, currencyExchangeInfoDTO);
        if (currencyExchangeInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, currencyExchangeInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!currencyExchangeInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<CurrencyExchangeInfoDTO> result = currencyExchangeInfoService.partialUpdate(currencyExchangeInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, currencyExchangeInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /currency-exchange-infos} : get all the currencyExchangeInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of currencyExchangeInfos in body.
     */
    @GetMapping("")
    public List<CurrencyExchangeInfoDTO> getAllCurrencyExchangeInfos() {
        log.debug("REST request to get all CurrencyExchangeInfos");
        return currencyExchangeInfoService.findAll();
    }

    /**
     * {@code GET  /currency-exchange-infos/:id} : get the "id" currencyExchangeInfo.
     *
     * @param id the id of the currencyExchangeInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the currencyExchangeInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CurrencyExchangeInfoDTO> getCurrencyExchangeInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get CurrencyExchangeInfo : {}", id);
        Optional<CurrencyExchangeInfoDTO> currencyExchangeInfoDTO = currencyExchangeInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(currencyExchangeInfoDTO);
    }

    /**
     * {@code DELETE  /currency-exchange-infos/:id} : delete the "id" currencyExchangeInfo.
     *
     * @param id the id of the currencyExchangeInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurrencyExchangeInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete CurrencyExchangeInfo : {}", id);
        currencyExchangeInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
