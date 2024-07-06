package com.dotin.tfbita.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A TransferMethodManagement.
 */
@Entity
@Table(name = "transfer_method_management")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TransferMethodManagement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "code")
    private String code;

    @Column(name = "jhi_desc")
    private String desc;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties(value = { "category" }, allowSetters = true)
    private CategoryElement type;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TransferMethodManagement id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return this.code;
    }

    public TransferMethodManagement code(String code) {
        this.setCode(code);
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return this.desc;
    }

    public TransferMethodManagement desc(String desc) {
        this.setDesc(desc);
        return this;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public CategoryElement getType() {
        return this.type;
    }

    public void setType(CategoryElement categoryElement) {
        this.type = categoryElement;
    }

    public TransferMethodManagement type(CategoryElement categoryElement) {
        this.setType(categoryElement);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TransferMethodManagement)) {
            return false;
        }
        return getId() != null && getId().equals(((TransferMethodManagement) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TransferMethodManagement{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", desc='" + getDesc() + "'" +
            "}";
    }
}
