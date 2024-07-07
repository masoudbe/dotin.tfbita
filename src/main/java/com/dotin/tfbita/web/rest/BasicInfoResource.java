package com.dotin.tfbita.web.rest;

import com.dotin.tfbita.repository.BasicInfoRepository;
import com.dotin.tfbita.service.BasicInfoService;
import com.dotin.tfbita.service.dto.BasicInfoDTO;
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
 * REST controller for managing {@link com.dotin.tfbita.domain.BasicInfo}.
 */
@RestController
@RequestMapping("/api/basic-infos")
public class BasicInfoResource {

    private static final Logger log = LoggerFactory.getLogger(BasicInfoResource.class);

    private static final String ENTITY_NAME = "basicInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BasicInfoService basicInfoService;

    private final BasicInfoRepository basicInfoRepository;

    public BasicInfoResource(BasicInfoService basicInfoService, BasicInfoRepository basicInfoRepository) {
        this.basicInfoService = basicInfoService;
        this.basicInfoRepository = basicInfoRepository;
    }

    /**
     * {@code POST  /basic-infos} : Create a new basicInfo.
     *
     * @param basicInfoDTO the basicInfoDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new basicInfoDTO, or with status {@code 400 (Bad Request)} if the basicInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("")
    public ResponseEntity<BasicInfoDTO> createBasicInfo(@RequestBody BasicInfoDTO basicInfoDTO) throws URISyntaxException {
        log.debug("REST request to save BasicInfo : {}", basicInfoDTO);
        if (basicInfoDTO.getId() != null) {
            throw new BadRequestAlertException("A new basicInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        basicInfoDTO = basicInfoService.save(basicInfoDTO);
        return ResponseEntity.created(new URI("/api/basic-infos/" + basicInfoDTO.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, basicInfoDTO.getId().toString()))
            .body(basicInfoDTO);
    }

    /**
     * {@code PUT  /basic-infos/:id} : Updates an existing basicInfo.
     *
     * @param id the id of the basicInfoDTO to save.
     * @param basicInfoDTO the basicInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated basicInfoDTO,
     * or with status {@code 400 (Bad Request)} if the basicInfoDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the basicInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/{id}")
    public ResponseEntity<BasicInfoDTO> updateBasicInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BasicInfoDTO basicInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to update BasicInfo : {}, {}", id, basicInfoDTO);
        if (basicInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, basicInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!basicInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        basicInfoDTO = basicInfoService.update(basicInfoDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, basicInfoDTO.getId().toString()))
            .body(basicInfoDTO);
    }

    /**
     * {@code PATCH  /basic-infos/:id} : Partial updates given fields of an existing basicInfo, field will ignore if it is null
     *
     * @param id the id of the basicInfoDTO to save.
     * @param basicInfoDTO the basicInfoDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated basicInfoDTO,
     * or with status {@code 400 (Bad Request)} if the basicInfoDTO is not valid,
     * or with status {@code 404 (Not Found)} if the basicInfoDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the basicInfoDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<BasicInfoDTO> partialUpdateBasicInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody BasicInfoDTO basicInfoDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update BasicInfo partially : {}, {}", id, basicInfoDTO);
        if (basicInfoDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, basicInfoDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!basicInfoRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<BasicInfoDTO> result = basicInfoService.partialUpdate(basicInfoDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, basicInfoDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /basic-infos} : get all the basicInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of basicInfos in body.
     */
    @GetMapping("")
    public List<BasicInfoDTO> getAllBasicInfos() {
        log.debug("REST request to get all BasicInfos");
        return basicInfoService.findAll();
    }

    /**
     * {@code GET  /basic-infos/:id} : get the "id" basicInfo.
     *
     * @param id the id of the basicInfoDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the basicInfoDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/{id}")
    public ResponseEntity<BasicInfoDTO> getBasicInfo(@PathVariable("id") Long id) {
        log.debug("REST request to get BasicInfo : {}", id);
        Optional<BasicInfoDTO> basicInfoDTO = basicInfoService.findOne(id);
        return ResponseUtil.wrapOrNotFound(basicInfoDTO);
    }

    /**
     * {@code DELETE  /basic-infos/:id} : delete the "id" basicInfo.
     *
     * @param id the id of the basicInfoDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBasicInfo(@PathVariable("id") Long id) {
        log.debug("REST request to delete BasicInfo : {}", id);
        basicInfoService.delete(id);
        return ResponseEntity.noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
