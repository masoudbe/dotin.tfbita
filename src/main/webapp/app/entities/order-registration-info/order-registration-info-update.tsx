import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICategoryElement } from 'app/shared/model/category-element.model';
import { getEntities as getCategoryElements } from 'app/entities/category-element/category-element.reducer';
import { ITransportationType } from 'app/shared/model/transportation-type.model';
import { getEntities as getTransportationTypes } from 'app/entities/transportation-type/transportation-type.reducer';
import { ICustom } from 'app/shared/model/custom.model';
import { getEntities as getCustoms } from 'app/entities/custom/custom.reducer';
import { IProduct } from 'app/shared/model/product.model';
import { getEntities as getProducts } from 'app/entities/product/product.reducer';
import { IStringValue } from 'app/shared/model/string-value.model';
import { getEntities as getStringValues } from 'app/entities/string-value/string-value.reducer';
import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';
import { getEntity, updateEntity, createEntity, reset } from './order-registration-info.reducer';

export const OrderRegistrationInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const categoryElements = useAppSelector(state => state.categoryElement.entities);
  const transportationTypes = useAppSelector(state => state.transportationType.entities);
  const customs = useAppSelector(state => state.custom.entities);
  const products = useAppSelector(state => state.product.entities);
  const stringValues = useAppSelector(state => state.stringValue.entities);
  const orderRegistrationInfoEntity = useAppSelector(state => state.orderRegistrationInfo.entity);
  const loading = useAppSelector(state => state.orderRegistrationInfo.loading);
  const updating = useAppSelector(state => state.orderRegistrationInfo.updating);
  const updateSuccess = useAppSelector(state => state.orderRegistrationInfo.updateSuccess);

  const handleClose = () => {
    navigate('/order-registration-info');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCategoryElements({}));
    dispatch(getTransportationTypes({}));
    dispatch(getCustoms({}));
    dispatch(getProducts({}));
    dispatch(getStringValues({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }
    if (values.totalWeightInKg !== undefined && typeof values.totalWeightInKg !== 'number') {
      values.totalWeightInKg = Number(values.totalWeightInKg);
    }
    if (values.fobAmount !== undefined && typeof values.fobAmount !== 'number') {
      values.fobAmount = Number(values.fobAmount);
    }
    if (values.discount !== undefined && typeof values.discount !== 'number') {
      values.discount = Number(values.discount);
    }
    if (values.shipmentCost !== undefined && typeof values.shipmentCost !== 'number') {
      values.shipmentCost = Number(values.shipmentCost);
    }
    if (values.othrCost !== undefined && typeof values.othrCost !== 'number') {
      values.othrCost = Number(values.othrCost);
    }
    if (values.totalAmount !== undefined && typeof values.totalAmount !== 'number') {
      values.totalAmount = Number(values.totalAmount);
    }
    if (values.externalCustomer !== undefined && typeof values.externalCustomer !== 'number') {
      values.externalCustomer = Number(values.externalCustomer);
    }
    if (values.sumRedemptionDuration !== undefined && typeof values.sumRedemptionDuration !== 'number') {
      values.sumRedemptionDuration = Number(values.sumRedemptionDuration);
    }
    if (values.extendedDayNumber !== undefined && typeof values.extendedDayNumber !== 'number') {
      values.extendedDayNumber = Number(values.extendedDayNumber);
    }

    const entity = {
      ...orderRegistrationInfoEntity,
      ...values,
      orderRegType: categoryElements.find(it => it.id.toString() === values.orderRegType?.toString()),
      requestType: categoryElements.find(it => it.id.toString() === values.requestType?.toString()),
      importType: categoryElements.find(it => it.id.toString() === values.importType?.toString()),
      operationType: categoryElements.find(it => it.id.toString() === values.operationType?.toString()),
      currencyProvisionType: categoryElements.find(it => it.id.toString() === values.currencyProvisionType?.toString()),
      paymentTool: categoryElements.find(it => it.id.toString() === values.paymentTool?.toString()),
      activityType: categoryElements.find(it => it.id.toString() === values.activityType?.toString()),
      ownerType: categoryElements.find(it => it.id.toString() === values.ownerType?.toString()),
      status: categoryElements.find(it => it.id.toString() === values.status?.toString()),
      externalCustomerType: categoryElements.find(it => it.id.toString() === values.externalCustomerType?.toString()),
      transportType: transportationTypes.find(it => it.id.toString() === values.transportType?.toString()),
      destCoustomers: customs.find(it => it.id.toString() === values.destCoustomers?.toString()),
      cargoPlaceCustoms: customs.find(it => it.id.toString() === values.cargoPlaceCustoms?.toString()),
      entranceBorders: customs.find(it => it.id.toString() === values.entranceBorders?.toString()),
      transportVehicleTypes: mapIdList(values.transportVehicleTypes),
      productInfos: mapIdList(values.productInfos),
      commissionTransactionNumbers: mapIdList(values.commissionTransactionNumbers),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...orderRegistrationInfoEntity,
          orderRegType: orderRegistrationInfoEntity?.orderRegType?.id,
          requestType: orderRegistrationInfoEntity?.requestType?.id,
          importType: orderRegistrationInfoEntity?.importType?.id,
          operationType: orderRegistrationInfoEntity?.operationType?.id,
          currencyProvisionType: orderRegistrationInfoEntity?.currencyProvisionType?.id,
          paymentTool: orderRegistrationInfoEntity?.paymentTool?.id,
          activityType: orderRegistrationInfoEntity?.activityType?.id,
          ownerType: orderRegistrationInfoEntity?.ownerType?.id,
          status: orderRegistrationInfoEntity?.status?.id,
          externalCustomerType: orderRegistrationInfoEntity?.externalCustomerType?.id,
          transportType: orderRegistrationInfoEntity?.transportType?.id,
          destCoustomers: orderRegistrationInfoEntity?.destCoustomers?.id,
          cargoPlaceCustoms: orderRegistrationInfoEntity?.cargoPlaceCustoms?.id,
          entranceBorders: orderRegistrationInfoEntity?.entranceBorders?.id,
          transportVehicleTypes: orderRegistrationInfoEntity?.transportVehicleTypes?.map(e => e.id.toString()),
          productInfos: orderRegistrationInfoEntity?.productInfos?.map(e => e.id.toString()),
          commissionTransactionNumbers: orderRegistrationInfoEntity?.commissionTransactionNumbers?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.orderRegistrationInfo.home.createOrEditLabel" data-cy="OrderRegistrationInfoCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.home.createOrEditLabel">
              Create or edit a OrderRegistrationInfo
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="order-registration-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.orderRegNum')}
                id="order-registration-info-orderRegNum"
                name="orderRegNum"
                data-cy="orderRegNum"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.startOrderRegDate')}
                id="order-registration-info-startOrderRegDate"
                name="startOrderRegDate"
                data-cy="startOrderRegDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.endOrderRegDate')}
                id="order-registration-info-endOrderRegDate"
                name="endOrderRegDate"
                data-cy="endOrderRegDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.requestNumber')}
                id="order-registration-info-requestNumber"
                name="requestNumber"
                data-cy="requestNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.bankCode')}
                id="order-registration-info-bankCode"
                name="bankCode"
                data-cy="bankCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.branchCode')}
                id="order-registration-info-branchCode"
                name="branchCode"
                data-cy="branchCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.customerNumber')}
                id="order-registration-info-customerNumber"
                name="customerNumber"
                data-cy="customerNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.applicantNationalcode')}
                id="order-registration-info-applicantNationalcode"
                name="applicantNationalcode"
                data-cy="applicantNationalcode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.performaNumber')}
                id="order-registration-info-performaNumber"
                name="performaNumber"
                data-cy="performaNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.performaDate')}
                id="order-registration-info-performaDate"
                name="performaDate"
                data-cy="performaDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.performaExpiryDate')}
                id="order-registration-info-performaExpiryDate"
                name="performaExpiryDate"
                data-cy="performaExpiryDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.performaDatePersian')}
                id="order-registration-info-performaDatePersian"
                name="performaDatePersian"
                data-cy="performaDatePersian"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.performaExpiryDatePersian')}
                id="order-registration-info-performaExpiryDatePersian"
                name="performaExpiryDatePersian"
                data-cy="performaExpiryDatePersian"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.infoSubmissionDate')}
                id="order-registration-info-infoSubmissionDate"
                name="infoSubmissionDate"
                data-cy="infoSubmissionDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.sellerName')}
                id="order-registration-info-sellerName"
                name="sellerName"
                data-cy="sellerName"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.beneficiaryCountryCode')}
                id="order-registration-info-beneficiaryCountryCode"
                name="beneficiaryCountryCode"
                data-cy="beneficiaryCountryCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.producerCountriesCode')}
                id="order-registration-info-producerCountriesCode"
                name="producerCountriesCode"
                data-cy="producerCountriesCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.sourceCountry')}
                id="order-registration-info-sourceCountry"
                name="sourceCountry"
                data-cy="sourceCountry"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.multipleTransportable')}
                id="order-registration-info-multipleTransportable"
                name="multipleTransportable"
                data-cy="multipleTransportable"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.deliveryTimeOfGoods')}
                id="order-registration-info-deliveryTimeOfGoods"
                name="deliveryTimeOfGoods"
                data-cy="deliveryTimeOfGoods"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.totalWeightInKg')}
                id="order-registration-info-totalWeightInKg"
                name="totalWeightInKg"
                data-cy="totalWeightInKg"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.possibilityCarrying')}
                id="order-registration-info-possibilityCarrying"
                name="possibilityCarrying"
                data-cy="possibilityCarrying"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.possibilityClearance')}
                id="order-registration-info-possibilityClearance"
                name="possibilityClearance"
                data-cy="possibilityClearance"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.ableAddServiceInProductOrder')}
                id="order-registration-info-ableAddServiceInProductOrder"
                name="ableAddServiceInProductOrder"
                data-cy="ableAddServiceInProductOrder"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.freeZone')}
                id="order-registration-info-freeZone"
                name="freeZone"
                data-cy="freeZone"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.currencyCode')}
                id="order-registration-info-currencyCode"
                name="currencyCode"
                data-cy="currencyCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.fobAmount')}
                id="order-registration-info-fobAmount"
                name="fobAmount"
                data-cy="fobAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.discount')}
                id="order-registration-info-discount"
                name="discount"
                data-cy="discount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.shipmentCost')}
                id="order-registration-info-shipmentCost"
                name="shipmentCost"
                data-cy="shipmentCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.othrCost')}
                id="order-registration-info-othrCost"
                name="othrCost"
                data-cy="othrCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.totalAmount')}
                id="order-registration-info-totalAmount"
                name="totalAmount"
                data-cy="totalAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.isFile')}
                id="order-registration-info-isFile"
                name="isFile"
                data-cy="isFile"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.fileNumber')}
                id="order-registration-info-fileNumber"
                name="fileNumber"
                data-cy="fileNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.extended')}
                id="order-registration-info-extended"
                name="extended"
                data-cy="extended"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.updated')}
                id="order-registration-info-updated"
                name="updated"
                data-cy="updated"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.generateFromService')}
                id="order-registration-info-generateFromService"
                name="generateFromService"
                data-cy="generateFromService"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.certificateNumber')}
                id="order-registration-info-certificateNumber"
                name="certificateNumber"
                data-cy="certificateNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.centralBankCode')}
                id="order-registration-info-centralBankCode"
                name="centralBankCode"
                data-cy="centralBankCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.isMigrational')}
                id="order-registration-info-isMigrational"
                name="isMigrational"
                data-cy="isMigrational"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.externalCustomer')}
                id="order-registration-info-externalCustomer"
                name="externalCustomer"
                data-cy="externalCustomer"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.sumRedemptionDuration')}
                id="order-registration-info-sumRedemptionDuration"
                name="sumRedemptionDuration"
                data-cy="sumRedemptionDuration"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.extendedDayNumber')}
                id="order-registration-info-extendedDayNumber"
                name="extendedDayNumber"
                data-cy="extendedDayNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.mainGroupProductCode')}
                id="order-registration-info-mainGroupProductCode"
                name="mainGroupProductCode"
                data-cy="mainGroupProductCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.producerCountries')}
                id="order-registration-info-producerCountries"
                name="producerCountries"
                data-cy="producerCountries"
                type="text"
              />
              <ValidatedBlobField
                label={translate('tfbitaApp.orderRegistrationInfo.excelFile')}
                id="order-registration-info-excelFile"
                name="excelFile"
                data-cy="excelFile"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.commissionTransactionNumber')}
                id="order-registration-info-commissionTransactionNumber"
                name="commissionTransactionNumber"
                data-cy="commissionTransactionNumber"
                type="text"
              />
              <ValidatedField
                id="order-registration-info-orderRegType"
                name="orderRegType"
                data-cy="orderRegType"
                label={translate('tfbitaApp.orderRegistrationInfo.orderRegType')}
                type="select"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-requestType"
                name="requestType"
                data-cy="requestType"
                label={translate('tfbitaApp.orderRegistrationInfo.requestType')}
                type="select"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-importType"
                name="importType"
                data-cy="importType"
                label={translate('tfbitaApp.orderRegistrationInfo.importType')}
                type="select"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-operationType"
                name="operationType"
                data-cy="operationType"
                label={translate('tfbitaApp.orderRegistrationInfo.operationType')}
                type="select"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-currencyProvisionType"
                name="currencyProvisionType"
                data-cy="currencyProvisionType"
                label={translate('tfbitaApp.orderRegistrationInfo.currencyProvisionType')}
                type="select"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-paymentTool"
                name="paymentTool"
                data-cy="paymentTool"
                label={translate('tfbitaApp.orderRegistrationInfo.paymentTool')}
                type="select"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-activityType"
                name="activityType"
                data-cy="activityType"
                label={translate('tfbitaApp.orderRegistrationInfo.activityType')}
                type="select"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-ownerType"
                name="ownerType"
                data-cy="ownerType"
                label={translate('tfbitaApp.orderRegistrationInfo.ownerType')}
                type="select"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-status"
                name="status"
                data-cy="status"
                label={translate('tfbitaApp.orderRegistrationInfo.status')}
                type="select"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-externalCustomerType"
                name="externalCustomerType"
                data-cy="externalCustomerType"
                label={translate('tfbitaApp.orderRegistrationInfo.externalCustomerType')}
                type="select"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-transportType"
                name="transportType"
                data-cy="transportType"
                label={translate('tfbitaApp.orderRegistrationInfo.transportType')}
                type="select"
              >
                <option value="" key="0" />
                {transportationTypes
                  ? transportationTypes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-destCoustomers"
                name="destCoustomers"
                data-cy="destCoustomers"
                label={translate('tfbitaApp.orderRegistrationInfo.destCoustomers')}
                type="select"
              >
                <option value="" key="0" />
                {customs
                  ? customs.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-cargoPlaceCustoms"
                name="cargoPlaceCustoms"
                data-cy="cargoPlaceCustoms"
                label={translate('tfbitaApp.orderRegistrationInfo.cargoPlaceCustoms')}
                type="select"
              >
                <option value="" key="0" />
                {customs
                  ? customs.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="order-registration-info-entranceBorders"
                name="entranceBorders"
                data-cy="entranceBorders"
                label={translate('tfbitaApp.orderRegistrationInfo.entranceBorders')}
                type="select"
              >
                <option value="" key="0" />
                {customs
                  ? customs.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.transportVehicleType')}
                id="order-registration-info-transportVehicleType"
                data-cy="transportVehicleType"
                type="select"
                multiple
                name="transportVehicleTypes"
              >
                <option value="" key="0" />
                {categoryElements
                  ? categoryElements.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.productInfo')}
                id="order-registration-info-productInfo"
                data-cy="productInfo"
                type="select"
                multiple
                name="productInfos"
              >
                <option value="" key="0" />
                {products
                  ? products.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('tfbitaApp.orderRegistrationInfo.commissionTransactionNumber')}
                id="order-registration-info-commissionTransactionNumber"
                data-cy="commissionTransactionNumber"
                type="select"
                multiple
                name="commissionTransactionNumbers"
              >
                <option value="" key="0" />
                {stringValues
                  ? stringValues.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/order-registration-info" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default OrderRegistrationInfoUpdate;
