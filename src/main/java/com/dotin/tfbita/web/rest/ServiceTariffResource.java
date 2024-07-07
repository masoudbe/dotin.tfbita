package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.ServiceTariffRepository;
import com.dotin.tfbita.service.ServiceTariffService;
import com.dotin.tfbita.service.dto.ServiceTariffDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.ServiceTariff}.
 */
@RestController
@RequestMapping("/api/service-tariffs")
public class ServiceTariffResource {

    private static final Logger log = LoggerFactory.getLogger(ServiceTariffResource.class);

    private static final String ENTITY_NAME = "serviceTariff";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ServiceTariffService serviceTariffService;

    private final ServiceTariffRepository serviceTariffRepository;

    public ServiceTariffResource(ServiceTariffService serviceTariffService, ServiceTariffRepository serviceTariffRepository) {
        this.serviceTariffService = serviceTariffService;
        this.serviceTariffRepository = serviceTariffRepository;
    }

    /**
     * {@code POST  /service-tariffs} : Create a new serviceTariff.
     *
     * @param serviceTariffDTO the serviceTariffDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new serviceTariffDTO, or with status {@code 400 (Bad Request)} if the serviceTariff has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<ServiceTariffDTO> createServiceTariff(@RequestBody ServiceTariffDTO serviceTariffDTO) throws URISyntaxException {
        log.debug("REST request to save ServiceTariff : {}", serviceTariffDTO);
        if (serviceTariffDTO.getId() != null) {
            throw new BadRequestAlertException("A new serviceTariff cannot already have an ID", ENTITY_NAME, "idexists");
        }
        serviceTariffDTO = serviceTariffService.save(serviceTariffDTO);
        return ResponseEntity.created(new URI("/api/service-tariffs/" + serviceTariffDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, serviceTariffDTO.getId().toString()))
            .body(serviceTariffDTO);
    }

    /**
     * {@code PUT  /service-tariffs/:id} : Updates an existing serviceTariff.
     *
     * @param id the id of the serviceTariffDTO to save.
     * @param serviceTariffDTO the serviceTariffDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceTariffDTO,
     * or with status {@code 400 (Bad Request)} if the serviceTariffDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the serviceTariffDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<ServiceTariffDTO> updateServiceTariff(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ServiceTariffDTO serviceTariffDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ServiceTariff : {}, {}", id, serviceTariffDTO);
        if (serviceTariffDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, serviceTariffDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceTariffRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        serviceTariffDTO = serviceTariffService.update(serviceTariffDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceTariffDTO.getId().toString()))
            .body(serviceTariffDTO);
    }

    /**
     * {@code PATCH  /service-tariffs/:id} : Partial updates given fields of an existing serviceTariff, field will ignore if it is null
     *
     * @param id the id of the serviceTariffDTO to save.
     * @param serviceTariffDTO the serviceTariffDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated serviceTariffDTO,
     * or with status {@code 400 (Bad Request)} if the serviceTariffDTO is not valid,
     * or with status {@code 404 (Not Found)} if the serviceTariffDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the serviceTariffDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ServiceTariffDTO> partialUpdateServiceTariff(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ServiceTariffDTO serviceTariffDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ServiceTariff partially : {}, {}", id, serviceTariffDTO);
        if (serviceTariffDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, serviceTariffDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!serviceTariffRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ServiceTariffDTO> result = serviceTariffService.partialUpdate(serviceTariffDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, serviceTariffDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /service-tariffs} : get all the serviceTariffs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of serviceTariffs in body.
     */
    @GetMapping("")
    public List<ServiceTariffDTO> getAllServiceTariffs() {
        log.debug("REST request to get all ServiceTariffs");
        return serviceTariffService.findAll();
    }

    /**
     * {@code GET  /service-tariffs/:id} : get the "id" serviceTariff.
     *
     * @param id the id of the serviceTariffDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the serviceTariffDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ServiceTariffDTO> getServiceTariff(@PathVariable("id") Long id) {
        log.debug("REST request to get ServiceTariff : {}", id);
        Optional<ServiceTariffDTO> serviceTariffDTO = serviceTariffService.findOne(id);
        return ResponseUtil.wrapOrNotFound(serviceTariffDTO);
    }

    /**
     * {@code DELETE  /service-tariffs/:id} : delete the "id" serviceTariff.
     *
     * @param id the id of the serviceTariffDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteServiceTariff(@PathVariable("id") Long id) {
        log.debug("REST request to delete ServiceTariff : {}", id);
        serviceTariffService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
