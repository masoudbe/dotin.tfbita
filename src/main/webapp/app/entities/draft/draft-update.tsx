import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICategoryElement } from 'app/shared/model/category-element.model';
import { getEntities as getCategoryElements } from 'app/entities/category-element/category-element.reducer';
import { ICustom } from 'app/shared/model/custom.model';
import { getEntities as getCustoms } from 'app/entities/custom/custom.reducer';
import { IDraftType } from 'app/shared/model/draft-type.model';
import { getEntities as getDraftTypes } from 'app/entities/draft-type/draft-type.reducer';
import { IDraftStatusInfo } from 'app/shared/model/draft-status-info.model';
import { getEntities as getDraftStatusInfos } from 'app/entities/draft-status-info/draft-status-info.reducer';
import { IInsuranceCompanyInfo } from 'app/shared/model/insurance-company-info.model';
import { getEntities as getInsuranceCompanyInfos } from 'app/entities/insurance-company-info/insurance-company-info.reducer';
import { IAdvisorDefinition } from 'app/shared/model/advisor-definition.model';
import { getEntities as getAdvisorDefinitions } from 'app/entities/advisor-definition/advisor-definition.reducer';
import { IAuditCompanyInfo } from 'app/shared/model/audit-company-info.model';
import { getEntities as getAuditCompanyInfos } from 'app/entities/audit-company-info/audit-company-info.reducer';
import { ITransportationType } from 'app/shared/model/transportation-type.model';
import { getEntities as getTransportationTypes } from 'app/entities/transportation-type/transportation-type.reducer';
import { ICurrencyExchangeInfo } from 'app/shared/model/currency-exchange-info.model';
import { getEntities as getCurrencyExchangeInfos } from 'app/entities/currency-exchange-info/currency-exchange-info.reducer';
import { IDraftAccountInfo } from 'app/shared/model/draft-account-info.model';
import { getEntities as getDraftAccountInfos } from 'app/entities/draft-account-info/draft-account-info.reducer';
import { IServiceTariff } from 'app/shared/model/service-tariff.model';
import { getEntities as getServiceTariffs } from 'app/entities/service-tariff/service-tariff.reducer';
import { IProduct } from 'app/shared/model/product.model';
import { getEntities as getProducts } from 'app/entities/product/product.reducer';
import { IStringValue } from 'app/shared/model/string-value.model';
import { getEntities as getStringValues } from 'app/entities/string-value/string-value.reducer';
import { ILongValue } from 'app/shared/model/long-value.model';
import { getEntities as getLongValues } from 'app/entities/long-value/long-value.reducer';
import { ISuggestedSanctionInfo } from 'app/shared/model/suggested-sanction-info.model';
import { getEntities as getSuggestedSanctionInfos } from 'app/entities/suggested-sanction-info/suggested-sanction-info.reducer';
import { IDraftDocumentTransactionContainer } from 'app/shared/model/draft-document-transaction-container.model';
import { getEntities as getDraftDocumentTransactionContainers } from 'app/entities/draft-document-transaction-container/draft-document-transaction-container.reducer';
import { IDraft } from 'app/shared/model/draft.model';
import { getEntity, updateEntity, createEntity, reset } from './draft.reducer';

export const DraftUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const categoryElements = useAppSelector(state => state.categoryElement.entities);
  const customs = useAppSelector(state => state.custom.entities);
  const draftTypes = useAppSelector(state => state.draftType.entities);
  const draftStatusInfos = useAppSelector(state => state.draftStatusInfo.entities);
  const insuranceCompanyInfos = useAppSelector(state => state.insuranceCompanyInfo.entities);
  const advisorDefinitions = useAppSelector(state => state.advisorDefinition.entities);
  const auditCompanyInfos = useAppSelector(state => state.auditCompanyInfo.entities);
  const transportationTypes = useAppSelector(state => state.transportationType.entities);
  const currencyExchangeInfos = useAppSelector(state => state.currencyExchangeInfo.entities);
  const draftAccountInfos = useAppSelector(state => state.draftAccountInfo.entities);
  const serviceTariffs = useAppSelector(state => state.serviceTariff.entities);
  const products = useAppSelector(state => state.product.entities);
  const stringValues = useAppSelector(state => state.stringValue.entities);
  const longValues = useAppSelector(state => state.longValue.entities);
  const suggestedSanctionInfos = useAppSelector(state => state.suggestedSanctionInfo.entities);
  const draftDocumentTransactionContainers = useAppSelector(state => state.draftDocumentTransactionContainer.entities);
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

    dispatch(getCategoryElements({}));
    dispatch(getCustoms({}));
    dispatch(getDraftTypes({}));
    dispatch(getDraftStatusInfos({}));
    dispatch(getInsuranceCompanyInfos({}));
    dispatch(getAdvisorDefinitions({}));
    dispatch(getAuditCompanyInfos({}));
    dispatch(getTransportationTypes({}));
    dispatch(getCurrencyExchangeInfos({}));
    dispatch(getDraftAccountInfos({}));
    dispatch(getServiceTariffs({}));
    dispatch(getProducts({}));
    dispatch(getStringValues({}));
    dispatch(getLongValues({}));
    dispatch(getSuggestedSanctionInfos({}));
    dispatch(getDraftDocumentTransactionContainers({}));
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

    const entity = {
      ...draftEntity,
      ...values,
      chargedExchangeBroker: categoryElements.find(it => it.id.toString() === values.chargedExchangeBroker?.toString()),
      insuranceLetterType: categoryElements.find(it => it.id.toString() === values.insuranceLetterType?.toString()),
      advisorDepositType: categoryElements.find(it => it.id.toString() === values.advisorDepositType?.toString()),
      interfaceAdvisorDepositType: categoryElements.find(it => it.id.toString() === values.interfaceAdvisorDepositType?.toString()),
      coveringAdvisorDepositType: categoryElements.find(it => it.id.toString() === values.coveringAdvisorDepositType?.toString()),
      impartType: categoryElements.find(it => it.id.toString() === values.impartType?.toString()),
      dealType: categoryElements.find(it => it.id.toString() === values.dealType?.toString()),
      transportVehicleType: categoryElements.find(it => it.id.toString() === values.transportVehicleType?.toString()),
      freightLetterType: categoryElements.find(it => it.id.toString() === values.freightLetterType?.toString()),
      actionCode: categoryElements.find(it => it.id.toString() === values.actionCode?.toString()),
      ownershipCode: categoryElements.find(it => it.id.toString() === values.ownershipCode?.toString()),
      currencyContainerPlace: categoryElements.find(it => it.id.toString() === values.currencyContainerPlace?.toString()),
      paymentType: categoryElements.find(it => it.id.toString() === values.paymentType?.toString()),
      draftSource: categoryElements.find(it => it.id.toString() === values.draftSource?.toString()),
      loadSwitchPlace: customs.find(it => it.id.toString() === values.loadSwitchPlace?.toString()),
      draftType: draftTypes.find(it => it.id.toString() === values.draftType?.toString()),
      statusInfo: draftStatusInfos.find(it => it.id.toString() === values.statusInfo?.toString()),
      insuranceCompanyInfo: insuranceCompanyInfos.find(it => it.id.toString() === values.insuranceCompanyInfo?.toString()),
      advisingBank: advisorDefinitions.find(it => it.id.toString() === values.advisingBank?.toString()),
      interfaceAdvisingBank: advisorDefinitions.find(it => it.id.toString() === values.interfaceAdvisingBank?.toString()),
      coveringBank: advisorDefinitions.find(it => it.id.toString() === values.coveringBank?.toString()),
      auditCompanyInfo: auditCompanyInfos.find(it => it.id.toString() === values.auditCompanyInfo?.toString()),
      transportType: transportationTypes.find(it => it.id.toString() === values.transportType?.toString()),
      currencyExchangeInfo: currencyExchangeInfos.find(it => it.id.toString() === values.currencyExchangeInfo?.toString()),
      accountInfo: draftAccountInfos.find(it => it.id.toString() === values.accountInfo?.toString()),
      destinationCustomCompanies: customs.find(it => it.id.toString() === values.destinationCustomCompanies?.toString()),
      sourceCustomCompanies: customs.find(it => it.id.toString() === values.sourceCustomCompanies?.toString()),
      services: mapIdList(values.services),
      products: mapIdList(values.products),
      sanctionSerials: mapIdList(values.sanctionSerials),
      customerNumbers: mapIdList(values.customerNumbers),
      suggestedSanctions: mapIdList(values.suggestedSanctions),
      documentTransactionContainers: mapIdList(values.documentTransactionContainers),
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
          chargedExchangeBroker: draftEntity?.chargedExchangeBroker?.id,
          insuranceLetterType: draftEntity?.insuranceLetterType?.id,
          advisorDepositType: draftEntity?.advisorDepositType?.id,
          interfaceAdvisorDepositType: draftEntity?.interfaceAdvisorDepositType?.id,
          coveringAdvisorDepositType: draftEntity?.coveringAdvisorDepositType?.id,
          impartType: draftEntity?.impartType?.id,
          dealType: draftEntity?.dealType?.id,
          transportVehicleType: draftEntity?.transportVehicleType?.id,
          freightLetterType: draftEntity?.freightLetterType?.id,
          actionCode: draftEntity?.actionCode?.id,
          ownershipCode: draftEntity?.ownershipCode?.id,
          currencyContainerPlace: draftEntity?.currencyContainerPlace?.id,
          paymentType: draftEntity?.paymentType?.id,
          draftSource: draftEntity?.draftSource?.id,
          loadSwitchPlace: draftEntity?.loadSwitchPlace?.id,
          draftType: draftEntity?.draftType?.id,
          statusInfo: draftEntity?.statusInfo?.id,
          insuranceCompanyInfo: draftEntity?.insuranceCompanyInfo?.id,
          advisingBank: draftEntity?.advisingBank?.id,
          interfaceAdvisingBank: draftEntity?.interfaceAdvisingBank?.id,
          coveringBank: draftEntity?.coveringBank?.id,
          auditCompanyInfo: draftEntity?.auditCompanyInfo?.id,
          transportType: draftEntity?.transportType?.id,
          currencyExchangeInfo: draftEntity?.currencyExchangeInfo?.id,
          accountInfo: draftEntity?.accountInfo?.id,
          destinationCustomCompanies: draftEntity?.destinationCustomCompanies?.id,
          sourceCustomCompanies: draftEntity?.sourceCustomCompanies?.id,
          services: draftEntity?.services?.map(e => e.id.toString()),
          products: draftEntity?.products?.map(e => e.id.toString()),
          sanctionSerials: draftEntity?.sanctionSerials?.map(e => e.id.toString()),
          customerNumbers: draftEntity?.customerNumbers?.map(e => e.id.toString()),
          suggestedSanctions: draftEntity?.suggestedSanctions?.map(e => e.id.toString()),
          documentTransactionContainers: draftEntity?.documentTransactionContainers?.map(e => e.id.toString()),
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
                label={translate('tfbitaApp.draft.chargedExchangeBrokerCurrencyCode')}
                id="draft-chargedExchangeBrokerCurrencyCode"
                name="chargedExchangeBrokerCurrencyCode"
                data-cy="chargedExchangeBrokerCurrencyCode"
                type="text"
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
                label={translate('tfbitaApp.draft.registerationJustificationCurrencyCode')}
                id="draft-registerationJustificationCurrencyCode"
                name="registerationJustificationCurrencyCode"
                data-cy="registerationJustificationCurrencyCode"
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
                id="draft-chargedExchangeBroker"
                name="chargedExchangeBroker"
                data-cy="chargedExchangeBroker"
                label={translate('tfbitaApp.draft.chargedExchangeBroker')}
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
                id="draft-insuranceLetterType"
                name="insuranceLetterType"
                data-cy="insuranceLetterType"
                label={translate('tfbitaApp.draft.insuranceLetterType')}
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
                id="draft-advisorDepositType"
                name="advisorDepositType"
                data-cy="advisorDepositType"
                label={translate('tfbitaApp.draft.advisorDepositType')}
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
                id="draft-interfaceAdvisorDepositType"
                name="interfaceAdvisorDepositType"
                data-cy="interfaceAdvisorDepositType"
                label={translate('tfbitaApp.draft.interfaceAdvisorDepositType')}
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
                id="draft-coveringAdvisorDepositType"
                name="coveringAdvisorDepositType"
                data-cy="coveringAdvisorDepositType"
                label={translate('tfbitaApp.draft.coveringAdvisorDepositType')}
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
                id="draft-impartType"
                name="impartType"
                data-cy="impartType"
                label={translate('tfbitaApp.draft.impartType')}
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
                id="draft-dealType"
                name="dealType"
                data-cy="dealType"
                label={translate('tfbitaApp.draft.dealType')}
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
                id="draft-transportVehicleType"
                name="transportVehicleType"
                data-cy="transportVehicleType"
                label={translate('tfbitaApp.draft.transportVehicleType')}
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
                id="draft-freightLetterType"
                name="freightLetterType"
                data-cy="freightLetterType"
                label={translate('tfbitaApp.draft.freightLetterType')}
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
                id="draft-actionCode"
                name="actionCode"
                data-cy="actionCode"
                label={translate('tfbitaApp.draft.actionCode')}
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
                id="draft-ownershipCode"
                name="ownershipCode"
                data-cy="ownershipCode"
                label={translate('tfbitaApp.draft.ownershipCode')}
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
                id="draft-currencyContainerPlace"
                name="currencyContainerPlace"
                data-cy="currencyContainerPlace"
                label={translate('tfbitaApp.draft.currencyContainerPlace')}
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
                id="draft-paymentType"
                name="paymentType"
                data-cy="paymentType"
                label={translate('tfbitaApp.draft.paymentType')}
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
                id="draft-draftSource"
                name="draftSource"
                data-cy="draftSource"
                label={translate('tfbitaApp.draft.draftSource')}
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
                id="draft-loadSwitchPlace"
                name="loadSwitchPlace"
                data-cy="loadSwitchPlace"
                label={translate('tfbitaApp.draft.loadSwitchPlace')}
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
                id="draft-draftType"
                name="draftType"
                data-cy="draftType"
                label={translate('tfbitaApp.draft.draftType')}
                type="select"
              >
                <option value="" key="0" />
                {draftTypes
                  ? draftTypes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-statusInfo"
                name="statusInfo"
                data-cy="statusInfo"
                label={translate('tfbitaApp.draft.statusInfo')}
                type="select"
              >
                <option value="" key="0" />
                {draftStatusInfos
                  ? draftStatusInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-insuranceCompanyInfo"
                name="insuranceCompanyInfo"
                data-cy="insuranceCompanyInfo"
                label={translate('tfbitaApp.draft.insuranceCompanyInfo')}
                type="select"
              >
                <option value="" key="0" />
                {insuranceCompanyInfos
                  ? insuranceCompanyInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-advisingBank"
                name="advisingBank"
                data-cy="advisingBank"
                label={translate('tfbitaApp.draft.advisingBank')}
                type="select"
              >
                <option value="" key="0" />
                {advisorDefinitions
                  ? advisorDefinitions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-interfaceAdvisingBank"
                name="interfaceAdvisingBank"
                data-cy="interfaceAdvisingBank"
                label={translate('tfbitaApp.draft.interfaceAdvisingBank')}
                type="select"
              >
                <option value="" key="0" />
                {advisorDefinitions
                  ? advisorDefinitions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-coveringBank"
                name="coveringBank"
                data-cy="coveringBank"
                label={translate('tfbitaApp.draft.coveringBank')}
                type="select"
              >
                <option value="" key="0" />
                {advisorDefinitions
                  ? advisorDefinitions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-auditCompanyInfo"
                name="auditCompanyInfo"
                data-cy="auditCompanyInfo"
                label={translate('tfbitaApp.draft.auditCompanyInfo')}
                type="select"
              >
                <option value="" key="0" />
                {auditCompanyInfos
                  ? auditCompanyInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-transportType"
                name="transportType"
                data-cy="transportType"
                label={translate('tfbitaApp.draft.transportType')}
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
                id="draft-currencyExchangeInfo"
                name="currencyExchangeInfo"
                data-cy="currencyExchangeInfo"
                label={translate('tfbitaApp.draft.currencyExchangeInfo')}
                type="select"
              >
                <option value="" key="0" />
                {currencyExchangeInfos
                  ? currencyExchangeInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-accountInfo"
                name="accountInfo"
                data-cy="accountInfo"
                label={translate('tfbitaApp.draft.accountInfo')}
                type="select"
              >
                <option value="" key="0" />
                {draftAccountInfos
                  ? draftAccountInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-destinationCustomCompanies"
                name="destinationCustomCompanies"
                data-cy="destinationCustomCompanies"
                label={translate('tfbitaApp.draft.destinationCustomCompanies')}
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
                id="draft-sourceCustomCompanies"
                name="sourceCustomCompanies"
                data-cy="sourceCustomCompanies"
                label={translate('tfbitaApp.draft.sourceCustomCompanies')}
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
                label={translate('tfbitaApp.draft.sanctionSerials')}
                id="draft-sanctionSerials"
                data-cy="sanctionSerials"
                type="select"
                multiple
                name="sanctionSerials"
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
              <ValidatedField
                label={translate('tfbitaApp.draft.customerNumbers')}
                id="draft-customerNumbers"
                data-cy="customerNumbers"
                type="select"
                multiple
                name="customerNumbers"
              >
                <option value="" key="0" />
                {longValues
                  ? longValues.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('tfbitaApp.draft.suggestedSanctions')}
                id="draft-suggestedSanctions"
                data-cy="suggestedSanctions"
                type="select"
                multiple
                name="suggestedSanctions"
              >
                <option value="" key="0" />
                {suggestedSanctionInfos
                  ? suggestedSanctionInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('tfbitaApp.draft.documentTransactionContainer')}
                id="draft-documentTransactionContainer"
                data-cy="documentTransactionContainer"
                type="select"
                multiple
                name="documentTransactionContainers"
              >
                <option value="" key="0" />
                {draftDocumentTransactionContainers
                  ? draftDocumentTransactionContainers.map(otherEntity => (
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
