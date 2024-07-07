package com.dotin.tfbita.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.dotin.tfbita.domain.OrderRegService} entity.
 */
@SuppressWarnings("common-java:DuplicatedBlocks")
public class OrderRegServiceDTO implements Serializable {

    private Long id;

    private BigDecimal amount;

    private BigDecimal currencyAmount;

    private String unit;

    private ServiceTariffDTO serviceTariff;

    private OrderRegistrationInfoDTO orderRegistrationInfo;

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

    public ServiceTariffDTO getServiceTariff() {
        return serviceTariff;
    }

    public void setServiceTariff(ServiceTariffDTO serviceTariff) {
        this.serviceTariff = serviceTariff;
    }

    public OrderRegistrationInfoDTO getOrderRegistrationInfo() {
        return orderRegistrationInfo;
    }

    public void setOrderRegistrationInfo(OrderRegistrationInfoDTO orderRegistrationInfo) {
        this.orderRegistrationInfo = orderRegistrationInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof OrderRegServiceDTO)) {
            return false;
        }

        OrderRegServiceDTO orderRegServiceDTO = (OrderRegServiceDTO) o;
        if (this.id == null) {
            return false;
        }
        return Objects.equals(this.id, orderRegServiceDTO.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "OrderRegServiceDTO{" +
            "id=" + getId() +
            ", amount=" + getAmount() +
            ", currencyAmount=" + getCurrencyAmount() +
            ", unit='" + getUnit() + "'" +
            ", serviceTariff=" + getServiceTariff() +
            ", orderRegistrationInfo=" + getOrderRegistrationInfo() +
            "}";
    }
}
