package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.DraftExtend} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DraftExtendDTO implements Serializable {

    private Long id;

    private String date;

    private Integer duration;

    private String time;

    private DraftDTO draftField;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public DraftDTO getDraftField() {
        return draftField;
    }

    public void setDraftField(DraftDTO draftField) {
        this.draftField = draftField;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DraftExtendDTO)) {
            return false;
        }

        DraftExtendDTO draftExtendDTO = (DraftExtendDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, draftExtendDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DraftExtendDTO{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", duration=" + getDuration() +
            ", time='" + getTime() + "'" +
            ", draftField=" + getDraftField() +
            "}";
    }
}
