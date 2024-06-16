import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICustom } from 'app/shared/model/custom.model';
import { getEntities as getCustoms } from 'app/entities/custom/custom.reducer';
import { IProduct } from 'app/shared/model/product.model';
import { getEntities as getProducts } from 'app/entities/product/product.reducer';
import { IServiceTariff } from 'app/shared/model/service-tariff.model';
import { getEntities as getServiceTariffs } from 'app/entities/service-tariff/service-tariff.reducer';
import { IDraft } from 'app/shared/model/draft.model';
import { getEntity, updateEntity, createEntity, reset } from './draft.reducer';

export const DraftUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const customs = useAppSelector(state => state.custom.entities);
  const products = useAppSelector(state => state.product.entities);
  const serviceTariffs = useAppSelector(state => state.serviceTariff.entities);
  const draftEntity = useAppSelector(state => state.draft.entity);
  const loading = useAppSelector(state => state.draft.loading);
  const updating = useAppSelector(state => state.draft.updating);
  const updateSuccess = useAppSelector(state => state.draft.updateSuccess);

  const handleClose = () => {
    navigate('/draft');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCustoms({}));
    dispatch(getProducts({}));
    dispatch(getServiceTariffs({}));
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
    if (values.auditCost !== undefined && typeof values.auditCost !== 'number') {
      values.auditCost = Number(values.auditCost);
    }
    if (values.branchStampCost !== undefined && typeof values.branchStampCost !== 'number') {
      values.branchStampCost = Number(values.branchStampCost);
    }
    if (values.chargedExchangeBrokerAmount !== undefined && typeof values.chargedExchangeBrokerAmount !== 'number') {
      values.chargedExchangeBrokerAmount = Number(values.chargedExchangeBrokerAmount);
    }
    if (values.deliverDuration !== undefined && typeof values.deliverDuration !== 'number') {
      values.deliverDuration = Number(values.deliverDuration);
    }
    if (values.discount !== undefined && typeof values.discount !== 'number') {
      values.discount = Number(values.discount);
    }
    if (values.draftOrderRegProductWorth !== undefined && typeof values.draftOrderRegProductWorth !== 'number') {
      values.draftOrderRegProductWorth = Number(values.draftOrderRegProductWorth);
    }
    if (values.draftOrderRegServiceWorth !== undefined && typeof values.draftOrderRegServiceWorth !== 'number') {
      values.draftOrderRegServiceWorth = Number(values.draftOrderRegServiceWorth);
    }
    if (values.draftOrderRegTotalWorth !== undefined && typeof values.draftOrderRegTotalWorth !== 'number') {
      values.draftOrderRegTotalWorth = Number(values.draftOrderRegTotalWorth);
    }
    if (values.draftOtherCost !== undefined && typeof values.draftOtherCost !== 'number') {
      values.draftOtherCost = Number(values.draftOtherCost);
    }
    if (values.insuranceCost !== undefined && typeof values.insuranceCost !== 'number') {
      values.insuranceCost = Number(values.insuranceCost);
    }
    if (values.issueDraftCommission !== undefined && typeof values.issueDraftCommission !== 'number') {
      values.issueDraftCommission = Number(values.issueDraftCommission);
    }
    if (values.mainCustomerNumber !== undefined && typeof values.mainCustomerNumber !== 'number') {
      values.mainCustomerNumber = Number(values.mainCustomerNumber);
    }
    if (values.otherCost !== undefined && typeof values.otherCost !== 'number') {
      values.otherCost = Number(values.otherCost);
    }
    if (values.paymentAmount !== undefined && typeof values.paymentAmount !== 'number') {
      values.paymentAmount = Number(values.paymentAmount);
    }
    if (values.postSwiftCost !== undefined && typeof values.postSwiftCost !== 'number') {
      values.postSwiftCost = Number(values.postSwiftCost);
    }
    if (values.registerationJustificationAmount !== undefined && typeof values.registerationJustificationAmount !== 'number') {
      values.registerationJustificationAmount = Number(values.registerationJustificationAmount);
    }
    if (values.serial !== undefined && typeof values.serial !== 'number') {
      values.serial = Number(values.serial);
    }
    if (values.shipmentCost !== undefined && typeof values.shipmentCost !== 'number') {
      values.shipmentCost = Number(values.shipmentCost);
    }
    if (values.transferAmount !== undefined && typeof values.transferAmount !== 'number') {
      values.transferAmount = Number(values.transferAmount);
    }
    if (values.customerNumbers !== undefined && typeof values.customerNumbers !== 'number') {
      values.customerNumbers = Number(values.customerNumbers);
    }

    const entity = {
      ...draftEntity,
      ...values,
      customs: mapIdList(values.customs),
      products: mapIdList(values.products),
      services: mapIdList(values.services),
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
          ...draftEntity,
          customs: draftEntity?.customs?.map(e => e.id.toString()),
          products: draftEntity?.products?.map(e => e.id.toString()),
          services: draftEntity?.services?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draft.home.createOrEditLabel" data-cy="DraftCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draft.home.createOrEditLabel">Create or edit a Draft</Translate>
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
                  id="draft-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draft.advisorDepositNumber')}
                id="draft-advisorDepositNumber"
                name="advisorDepositNumber"
                data-cy="advisorDepositNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.advisorRequestDeleted')}
                id="draft-advisorRequestDeleted"
                name="advisorRequestDeleted"
                data-cy="advisorRequestDeleted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.auditCost')}
                id="draft-auditCost"
                name="auditCost"
                data-cy="auditCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.beneficiaryInfo')}
                id="draft-beneficiaryInfo"
                name="beneficiaryInfo"
                data-cy="beneficiaryInfo"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.branchStampCost')}
                id="draft-branchStampCost"
                name="branchStampCost"
                data-cy="branchStampCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.centralBankCode')}
                id="draft-centralBankCode"
                name="centralBankCode"
                data-cy="centralBankCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.centralBankCodeReference')}
                id="draft-centralBankCodeReference"
                name="centralBankCodeReference"
                data-cy="centralBankCodeReference"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.chargedExchangeBrokerAmount')}
                id="draft-chargedExchangeBrokerAmount"
                name="chargedExchangeBrokerAmount"
                data-cy="chargedExchangeBrokerAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.charterAcceptable')}
                id="draft-charterAcceptable"
                name="charterAcceptable"
                data-cy="charterAcceptable"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.comment')}
                id="draft-comment"
                name="comment"
                data-cy="comment"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.completionDate')}
                id="draft-completionDate"
                name="completionDate"
                data-cy="completionDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.coveringBankDepositNumber')}
                id="draft-coveringBankDepositNumber"
                name="coveringBankDepositNumber"
                data-cy="coveringBankDepositNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.currencyExchangeDepositNumber')}
                id="draft-currencyExchangeDepositNumber"
                name="currencyExchangeDepositNumber"
                data-cy="currencyExchangeDepositNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.customerDepositNumber')}
                id="draft-customerDepositNumber"
                name="customerDepositNumber"
                data-cy="customerDepositNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.deliverDuration')}
                id="draft-deliverDuration"
                name="deliverDuration"
                data-cy="deliverDuration"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.discount')}
                id="draft-discount"
                name="discount"
                data-cy="discount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.draftNumber')}
                id="draft-draftNumber"
                name="draftNumber"
                data-cy="draftNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.draftOrderRegProductWorth')}
                id="draft-draftOrderRegProductWorth"
                name="draftOrderRegProductWorth"
                data-cy="draftOrderRegProductWorth"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.draftOrderRegServiceWorth')}
                id="draft-draftOrderRegServiceWorth"
                name="draftOrderRegServiceWorth"
                data-cy="draftOrderRegServiceWorth"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.draftOrderRegTotalWorth')}
                id="draft-draftOrderRegTotalWorth"
                name="draftOrderRegTotalWorth"
                data-cy="draftOrderRegTotalWorth"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.draftOtherCost')}
                id="draft-draftOtherCost"
                name="draftOtherCost"
                data-cy="draftOtherCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.hasTransportJustification')}
                id="draft-hasTransportJustification"
                name="hasTransportJustification"
                data-cy="hasTransportJustification"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.insuranceCost')}
                id="draft-insuranceCost"
                name="insuranceCost"
                data-cy="insuranceCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.insuranceDate')}
                id="draft-insuranceDate"
                name="insuranceDate"
                data-cy="insuranceDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.insuranceExpiryDate')}
                id="draft-insuranceExpiryDate"
                name="insuranceExpiryDate"
                data-cy="insuranceExpiryDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.insuranceNumber')}
                id="draft-insuranceNumber"
                name="insuranceNumber"
                data-cy="insuranceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.interfaceAdvisorDepositNumber')}
                id="draft-interfaceAdvisorDepositNumber"
                name="interfaceAdvisorDepositNumber"
                data-cy="interfaceAdvisorDepositNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.issueDate')}
                id="draft-issueDate"
                name="issueDate"
                data-cy="issueDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.issueDraftCommission')}
                id="draft-issueDraftCommission"
                name="issueDraftCommission"
                data-cy="issueDraftCommission"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.lastShipmentDate')}
                id="draft-lastShipmentDate"
                name="lastShipmentDate"
                data-cy="lastShipmentDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.mainCustomerNumber')}
                id="draft-mainCustomerNumber"
                name="mainCustomerNumber"
                data-cy="mainCustomerNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.makeCertification')}
                id="draft-makeCertification"
                name="makeCertification"
                data-cy="makeCertification"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.multipleTransportable')}
                id="draft-multipleTransportable"
                name="multipleTransportable"
                data-cy="multipleTransportable"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.orderRegistrationDate')}
                id="draft-orderRegistrationDate"
                name="orderRegistrationDate"
                data-cy="orderRegistrationDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.orderRegistrationExpiryDate')}
                id="draft-orderRegistrationExpiryDate"
                name="orderRegistrationExpiryDate"
                data-cy="orderRegistrationExpiryDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.orderRegistrationNumber')}
                id="draft-orderRegistrationNumber"
                name="orderRegistrationNumber"
                data-cy="orderRegistrationNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.otherCost')}
                id="draft-otherCost"
                name="otherCost"
                data-cy="otherCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.paymentAmount')}
                id="draft-paymentAmount"
                name="paymentAmount"
                data-cy="paymentAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.performaDate')}
                id="draft-performaDate"
                name="performaDate"
                data-cy="performaDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.performaExpiryDate')}
                id="draft-performaExpiryDate"
                name="performaExpiryDate"
                data-cy="performaExpiryDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.performaNumber')}
                id="draft-performaNumber"
                name="performaNumber"
                data-cy="performaNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.postSwiftCost')}
                id="draft-postSwiftCost"
                name="postSwiftCost"
                data-cy="postSwiftCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.productSourceChangeable')}
                id="draft-productSourceChangeable"
                name="productSourceChangeable"
                data-cy="productSourceChangeable"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.receiveAllCommission')}
                id="draft-receiveAllCommission"
                name="receiveAllCommission"
                data-cy="receiveAllCommission"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.registerationJustificationAmount')}
                id="draft-registerationJustificationAmount"
                name="registerationJustificationAmount"
                data-cy="registerationJustificationAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.requestDate')}
                id="draft-requestDate"
                name="requestDate"
                data-cy="requestDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.sanctionSerial')}
                id="draft-sanctionSerial"
                name="sanctionSerial"
                data-cy="sanctionSerial"
                type="text"
              />
              <ValidatedField label={translate('tfbitaApp.draft.serial')} id="draft-serial" name="serial" data-cy="serial" type="text" />
              <ValidatedField
                label={translate('tfbitaApp.draft.shipmentCost')}
                id="draft-shipmentCost"
                name="shipmentCost"
                data-cy="shipmentCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.sourceTransportPlace')}
                id="draft-sourceTransportPlace"
                name="sourceTransportPlace"
                data-cy="sourceTransportPlace"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.swiftComment')}
                id="draft-swiftComment"
                name="swiftComment"
                data-cy="swiftComment"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.transferAmount')}
                id="draft-transferAmount"
                name="transferAmount"
                data-cy="transferAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.transportVehicleChangeable')}
                id="draft-transportVehicleChangeable"
                name="transportVehicleChangeable"
                data-cy="transportVehicleChangeable"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.paymentTool')}
                id="draft-paymentTool"
                name="paymentTool"
                data-cy="paymentTool"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.letterNumberTazirat')}
                id="draft-letterNumberTazirat"
                name="letterNumberTazirat"
                data-cy="letterNumberTazirat"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.letterDateTazirat')}
                id="draft-letterDateTazirat"
                name="letterDateTazirat"
                data-cy="letterDateTazirat"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.loanNumber')}
                id="draft-loanNumber"
                name="loanNumber"
                data-cy="loanNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.isMigrational')}
                id="draft-isMigrational"
                name="isMigrational"
                data-cy="isMigrational"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.isNewCertificate')}
                id="draft-isNewCertificate"
                name="isNewCertificate"
                data-cy="isNewCertificate"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.isWithoutPayment')}
                id="draft-isWithoutPayment"
                name="isWithoutPayment"
                data-cy="isWithoutPayment"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.destinationCountryCode')}
                id="draft-destinationCountryCode"
                name="destinationCountryCode"
                data-cy="destinationCountryCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.beneficiaryCountryCode')}
                id="draft-beneficiaryCountryCode"
                name="beneficiaryCountryCode"
                data-cy="beneficiaryCountryCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.producerCountryCode')}
                id="draft-producerCountryCode"
                name="producerCountryCode"
                data-cy="producerCountryCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.branchCode')}
                id="draft-branchCode"
                name="branchCode"
                data-cy="branchCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.operationalBranchCode')}
                id="draft-operationalBranchCode"
                name="operationalBranchCode"
                data-cy="operationalBranchCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.certificateSenderBranchCode')}
                id="draft-certificateSenderBranchCode"
                name="certificateSenderBranchCode"
                data-cy="certificateSenderBranchCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.mainAccountCurrencyCode')}
                id="draft-mainAccountCurrencyCode"
                name="mainAccountCurrencyCode"
                data-cy="mainAccountCurrencyCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.orderRegCurrencyCode')}
                id="draft-orderRegCurrencyCode"
                name="orderRegCurrencyCode"
                data-cy="orderRegCurrencyCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.chargedExchangeBrokerCurrency')}
                id="draft-chargedExchangeBrokerCurrency"
                name="chargedExchangeBrokerCurrency"
                data-cy="chargedExchangeBrokerCurrency"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.registerationJustificationCurrencyCode')}
                id="draft-registerationJustificationCurrencyCode"
                name="registerationJustificationCurrencyCode"
                data-cy="registerationJustificationCurrencyCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.currencyExchangeInfoTitle')}
                id="draft-currencyExchangeInfoTitle"
                name="currencyExchangeInfoTitle"
                data-cy="currencyExchangeInfoTitle"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.transportationTypeName')}
                id="draft-transportationTypeName"
                name="transportationTypeName"
                data-cy="transportationTypeName"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.accountInfoCode')}
                id="draft-accountInfoCode"
                name="accountInfoCode"
                data-cy="accountInfoCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.customerNumbers')}
                id="draft-customerNumbers"
                name="customerNumbers"
                data-cy="customerNumbers"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.sanctionSerials')}
                id="draft-sanctionSerials"
                name="sanctionSerials"
                data-cy="sanctionSerials"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draft.custom')}
                id="draft-custom"
                data-cy="custom"
                type="select"
                multiple
                name="customs"
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
                label={translate('tfbitaApp.draft.products')}
                id="draft-products"
                data-cy="products"
                type="select"
                multiple
                name="products"
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
                label={translate('tfbitaApp.draft.services')}
                id="draft-services"
                data-cy="services"
                type="select"
                multiple
                name="services"
              >
                <option value="" key="0" />
                {serviceTariffs
                  ? serviceTariffs.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft" replace color="info">
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

export default DraftUpdate;
