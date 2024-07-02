package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.CategoryElement} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CategoryElementDTO implements Serializable {

    private Long id;

    private String value;

    private String categoryName;

    private String code;

    private CategoryDTO category;

    private Set<OrderRegistrationInfoDTO> orderRegistrationInfos = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public Set<OrderRegistrationInfoDTO> getOrderRegistrationInfos() {
        return orderRegistrationInfos;
    }

    public void setOrderRegistrationInfos(Set<OrderRegistrationInfoDTO> orderRegistrationInfos) {
        this.orderRegistrationInfos = orderRegistrationInfos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryElementDTO)) {
            return false;
        }

        CategoryElementDTO categoryElementDTO = (CategoryElementDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, categoryElementDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryElementDTO{" +
            "id=" + getId() +
            ", value='" + getValue() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", code='" + getCode() + "'" +
            ", category=" + getCategory() +
            ", orderRegistrationInfos=" + getOrderRegistrationInfos() +
            "}";
    }
}
