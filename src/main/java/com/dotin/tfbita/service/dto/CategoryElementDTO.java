package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.CategoryElement} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CategoryElementDTO implements Serializable {

    private Long id;

    private String val;

    private String categoryName;

    private String code;

    private CategoryDTO category;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
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
            ", val='" + getVal() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", code='" + getCode() + "'" +
            ", category=" + getCategory() +
            "}";
    }
}
