package com.dotin.tfbita.service.impl;

import com.dotin.tfbita.domain.CategoryElement;
import com.dotin.tfbita.repository.CategoryElementRepository;
import com.dotin.tfbita.service.CategoryElementService;
import com.dotin.tfbita.service.dto.CategoryElementDTO;
import com.dotin.tfbita.service.mapper.CategoryElementMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.dotin.tfbita.domain.CategoryElement}.
 */
@Service
@Transactional
public class CategoryElementServiceImpl implements CategoryElementService {

    private static final Logger log = LoggerFactory.getLogger(CategoryElementServiceImpl.class);

    private final CategoryElementRepository categoryElementRepository;

    private final CategoryElementMapper categoryElementMapper;

    public CategoryElementServiceImpl(CategoryElementRepository categoryElementRepository, CategoryElementMapper categoryElementMapper) {
        this.categoryElementRepository = categoryElementRepository;
        this.categoryElementMapper = categoryElementMapper;
    }

    @Override
    public CategoryElementDTO save(CategoryElementDTO categoryElementDTO) {
        log.debug("Request to save CategoryElement : {}", categoryElementDTO);
        CategoryElement categoryElement = categoryElementMapper.toEntity(categoryElementDTO);
        categoryElement = categoryElementRepository.save(categoryElement);
        return categoryElementMapper.toDto(categoryElement);
    }

    @Override
    public CategoryElementDTO update(CategoryElementDTO categoryElementDTO) {
        log.debug("Request to update CategoryElement : {}", categoryElementDTO);
        CategoryElement categoryElement = categoryElementMapper.toEntity(categoryElementDTO);
        categoryElement = categoryElementRepository.save(categoryElement);
        return categoryElementMapper.toDto(categoryElement);
    }

    @Override
    public Optional<CategoryElementDTO> partialUpdate(CategoryElementDTO categoryElementDTO) {
        log.debug("Request to partially update CategoryElement : {}", categoryElementDTO);

        return categoryElementRepository
            .findById(categoryElementDTO.getId())
            .map(existingCategoryElement -> {
                categoryElementMapper.partialUpdate(existingCategoryElement, categoryElementDTO);

                return existingCategoryElement;
            })
            .map(categoryElementRepository::save)
            .map(categoryElementMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CategoryElementDTO> findAll() {
        log.debug("Request to get all CategoryElements");
        return categoryElementRepository
            .findAll()
            .stream()
            .map(categoryElementMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryElementDTO> findOne(Long id) {
        log.debug("Request to get CategoryElement : {}", id);
        return categoryElementRepository.findById(id).map(categoryElementMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CategoryElement : {}", id);
        categoryElementRepository.deleteById(id);
    }
}
