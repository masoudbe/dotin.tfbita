package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.OrderRegServ} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderRegServDTO implements Serializable {

    private Long id;

    private BigDecimal amount;

    private BigDecimal currencyAmount;

    private String unit;

    private String title;

    private String code;

    private OrderRegistrationInfoDTO orderRegService;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCurrencyAmount() {
        return currencyAmount;
    }

    public void setCurrencyAmount(BigDecimal currencyAmount) {
        this.currencyAmount = currencyAmount;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public OrderRegistrationInfoDTO getOrderRegService() {
        return orderRegService;
    }

    public void setOrderRegService(OrderRegistrationInfoDTO orderRegService) {
        this.orderRegService = orderRegService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderRegServDTO)) {
            return false;
        }

        OrderRegServDTO orderRegServDTO = (OrderRegServDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderRegServDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderRegServDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", currencyAmount=" + getCurrencyAmount() +
            ", unit='" + getUnit() + "'" +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", orderRegService=" + getOrderRegService() +
            "}";
    }
}
