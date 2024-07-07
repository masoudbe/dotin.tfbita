package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A CategoryElement.
 */
@Entity
@Table(name = "category_element")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CategoryElement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "val")
    private String val;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "possibleValues" }, allowSetters = true)
    private Category category;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public CategoryElement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVal() {
        return this.val;
    }

    public CategoryElement val(String val) {
        this.setVal(val);
        return this;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public CategoryElement categoryName(String categoryName) {
        this.setCategoryName(categoryName);
        return this;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCode() {
        return this.code;
    }

    public CategoryElement code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public CategoryElement category(Category category) {
        this.setCategory(category);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CategoryElement)) {
            return false;
        }
        return getId() != null && getId().equals(((CategoryElement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CategoryElement{" +
            "id=" + getId() +
            ", val='" + getVal() + "'" +
            ", categoryName='" + getCategoryName() + "'" +
            ", code='" + getCode() + "'" +
            "}";
    }
}
