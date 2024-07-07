package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.AttributeValueGroup} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AttributeValueGroupDTO implements Serializable {

    private Long id;

    private Boolean mandatory;

    private String name;

    private AttributeDTO attribute;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getMandatory() {
        return mandatory;
    }

    public void setMandatory(Boolean mandatory) {
        this.mandatory = mandatory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AttributeDTO getAttribute() {
        return attribute;
    }

    public void setAttribute(AttributeDTO attribute) {
        this.attribute = attribute;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AttributeValueGroupDTO)) {
            return false;
        }

        AttributeValueGroupDTO attributeValueGroupDTO = (AttributeValueGroupDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, attributeValueGroupDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AttributeValueGroupDTO{" +
            "id=" + getId() +
            ", mandatory='" + getMandatory() + "'" +
            ", name='" + getName() + "'" +
            ", attribute=" + getAttribute() +
            "}";
    }
}
