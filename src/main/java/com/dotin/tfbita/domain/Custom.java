package com.dotin.tfbita.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A Custom.
 */
@Entity
@Table(name = "custom")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Custom implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "modification_date")
    private String modificationDate;

    @Column(name = "latin_name")
    private String latinName;

    @Column(name = "name")
    private String name;

    @Column(name = "temp_id")
    private Long tempId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Custom id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModificationDate() {
        return this.modificationDate;
    }

    public Custom modificationDate(String modificationDate) {
        this.setModificationDate(modificationDate);
        return this;
    }

    public void setModificationDate(String modificationDate) {
        this.modificationDate = modificationDate;
    }

    public String getLatinName() {
        return this.latinName;
    }

    public Custom latinName(String latinName) {
        this.setLatinName(latinName);
        return this;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getName() {
        return this.name;
    }

    public Custom name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getTempId() {
        return this.tempId;
    }

    public Custom tempId(Long tempId) {
        this.setTempId(tempId);
        return this;
    }

    public void setTempId(Long tempId) {
        this.tempId = tempId;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Custom)) {
            return false;
        }
        return getId() != null && getId().equals(((Custom) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Custom{" +
            "id=" + getId() +
            ", modificationDate='" + getModificationDate() + "'" +
            ", latinName='" + getLatinName() + "'" +
            ", name='" + getName() + "'" +
            ", tempId=" + getTempId() +
            "}";
    }
}
