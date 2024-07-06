package com.dotin.tfbita.domain;

import jakarta.persistence.*;
import java.io.Serializable;

/**
 * A TradeTypeCode.
 */
@Entity
@Table(name = "trade_type_code")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class TradeTypeCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @Column(name = "id")
    private Long id;

    @Column(name = "latin_name")
    private String latinName;

    @Column(name = "name")
    private String name;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public TradeTypeCode id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLatinName() {
        return this.latinName;
    }

    public TradeTypeCode latinName(String latinName) {
        this.setLatinName(latinName);
        return this;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getName() {
        return this.name;
    }

    public TradeTypeCode name(String name) {
        this.setName(name);
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TradeTypeCode)) {
            return false;
        }
        return getId() != null && getId().equals(((TradeTypeCode) o).getId());
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TradeTypeCode{" +
            "id=" + getId() +
            ", latinName='" + getLatinName() + "'" +
            ", name='" + getName() + "'" +
            "}";
    }
}
