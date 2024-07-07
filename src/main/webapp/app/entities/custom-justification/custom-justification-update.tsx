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
import { ICustom } from 'app/shared/model/custom.model';
import { getEntities as getCustoms } from 'app/entities/custom/custom.reducer';
import { ITransportationType } from 'app/shared/model/transportation-type.model';
import { getEntities as getTransportationTypes } from 'app/entities/transportation-type/transportation-type.reducer';
import { ITradeTypeCode } from 'app/shared/model/trade-type-code.model';
import { getEntities as getTradeTypeCodes } from 'app/entities/trade-type-code/trade-type-code.reducer';
import { IPaymentCondition } from 'app/shared/model/payment-condition.model';
import { getEntities as getPaymentConditions } from 'app/entities/payment-condition/payment-condition.reducer';
import { IJustificationDeductionAmount } from 'app/shared/model/justification-deduction-amount.model';
import { getEntities as getJustificationDeductionAmounts } from 'app/entities/justification-deduction-amount/justification-deduction-amount.reducer';
import { IProduct } from 'app/shared/model/product.model';
import { getEntities as getProducts } from 'app/entities/product/product.reducer';
import { IDocumentTransaction } from 'app/shared/model/document-transaction.model';
import { getEntities as getDocumentTransactions } from 'app/entities/document-transaction/document-transaction.reducer';
import { ICustomJustification } from 'app/shared/model/custom-justification.model';
import { getEntity, updateEntity, createEntity, reset } from './custom-justification.reducer';

export const CustomJustificationUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const categoryElements = useAppSelector(state => state.categoryElement.entities);
  const customs = useAppSelector(state => state.custom.entities);
  const transportationTypes = useAppSelector(state => state.transportationType.entities);
  const tradeTypeCodes = useAppSelector(state => state.tradeTypeCode.entities);
  const paymentConditions = useAppSelector(state => state.paymentCondition.entities);
  const justificationDeductionAmounts = useAppSelector(state => state.justificationDeductionAmount.entities);
  const products = useAppSelector(state => state.product.entities);
  const documentTransactions = useAppSelector(state => state.documentTransaction.entities);
  const customJustificationEntity = useAppSelector(state => state.customJustification.entity);
  const loading = useAppSelector(state => state.customJustification.loading);
  const updating = useAppSelector(state => state.customJustification.updating);
  const updateSuccess = useAppSelector(state => state.customJustification.updateSuccess);

  const handleClose = () => {
    navigate('/custom-justification');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCategoryElements({}));
    dispatch(getCustoms({}));
    dispatch(getTransportationTypes({}));
    dispatch(getTradeTypeCodes({}));
    dispatch(getPaymentConditions({}));
    dispatch(getJustificationDeductionAmounts({}));
    dispatch(getProducts({}));
    dispatch(getDocumentTransactions({}));
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
    if (values.agentId !== undefined && typeof values.agentId !== 'number') {
      values.agentId = Number(values.agentId);
    }
    if (values.balanceRate !== undefined && typeof values.balanceRate !== 'number') {
      values.balanceRate = Number(values.balanceRate);
    }
    if (values.cottageNumber !== undefined && typeof values.cottageNumber !== 'number') {
      values.cottageNumber = Number(values.cottageNumber);
    }
    if (values.creditEquivalentAmount !== undefined && typeof values.creditEquivalentAmount !== 'number') {
      values.creditEquivalentAmount = Number(values.creditEquivalentAmount);
    }
    if (values.currencyAmount !== undefined && typeof values.currencyAmount !== 'number') {
      values.currencyAmount = Number(values.currencyAmount);
    }
    if (values.currencyRate !== undefined && typeof values.currencyRate !== 'number') {
      values.currencyRate = Number(values.currencyRate);
    }
    if (values.customerId !== undefined && typeof values.customerId !== 'number') {
      values.customerId = Number(values.customerId);
    }
    if (values.discountPercent !== undefined && typeof values.discountPercent !== 'number') {
      values.discountPercent = Number(values.discountPercent);
    }
    if (values.entranceCustomCompany !== undefined && typeof values.entranceCustomCompany !== 'number') {
      values.entranceCustomCompany = Number(values.entranceCustomCompany);
    }
    if (values.entranceCustomCompanyId !== undefined && typeof values.entranceCustomCompanyId !== 'number') {
      values.entranceCustomCompanyId = Number(values.entranceCustomCompanyId);
    }
    if (values.impureWeight !== undefined && typeof values.impureWeight !== 'number') {
      values.impureWeight = Number(values.impureWeight);
    }
    if (values.items !== undefined && typeof values.items !== 'number') {
      values.items = Number(values.items);
    }
    if (values.justificationCurrencyRate !== undefined && typeof values.justificationCurrencyRate !== 'number') {
      values.justificationCurrencyRate = Number(values.justificationCurrencyRate);
    }
    if (values.newBorderTransportType !== undefined && typeof values.newBorderTransportType !== 'number') {
      values.newBorderTransportType = Number(values.newBorderTransportType);
    }
    if (values.newEvacuationPlace !== undefined && typeof values.newEvacuationPlace !== 'number') {
      values.newEvacuationPlace = Number(values.newEvacuationPlace);
    }
    if (values.newInternalTransportType !== undefined && typeof values.newInternalTransportType !== 'number') {
      values.newInternalTransportType = Number(values.newInternalTransportType);
    }
    if (values.newProductItemCustomValue !== undefined && typeof values.newProductItemCustomValue !== 'number') {
      values.newProductItemCustomValue = Number(values.newProductItemCustomValue);
    }
    if (values.papers !== undefined && typeof values.papers !== 'number') {
      values.papers = Number(values.papers);
    }
    if (values.productBoxCount !== undefined && typeof values.productBoxCount !== 'number') {
      values.productBoxCount = Number(values.productBoxCount);
    }
    if (values.productItemCost !== undefined && typeof values.productItemCost !== 'number') {
      values.productItemCost = Number(values.productItemCost);
    }
    if (values.productWorth !== undefined && typeof values.productWorth !== 'number') {
      values.productWorth = Number(values.productWorth);
    }
    if (values.profitRate !== undefined && typeof values.profitRate !== 'number') {
      values.profitRate = Number(values.profitRate);
    }
    if (values.pureWeight !== undefined && typeof values.pureWeight !== 'number') {
      values.pureWeight = Number(values.pureWeight);
    }
    if (values.referenceNumber !== undefined && typeof values.referenceNumber !== 'number') {
      values.referenceNumber = Number(values.referenceNumber);
    }
    if (values.rightsRate !== undefined && typeof values.rightsRate !== 'number') {
      values.rightsRate = Number(values.rightsRate);
    }

    const entity = {
      ...customJustificationEntity,
      ...values,
      vehicleEnterNationality: categoryElements.find(it => it.id.toString() === values.vehicleEnterNationality?.toString()),
      container: categoryElements.find(it => it.id.toString() === values.container?.toString()),
      vehicleCrossNationality: categoryElements.find(it => it.id.toString() === values.vehicleCrossNationality?.toString()),
      exportCustom: customs.find(it => it.id.toString() === values.exportCustom?.toString()),
      entranceCustom: customs.find(it => it.id.toString() === values.entranceCustom?.toString()),
      transportConditions: transportationTypes.find(it => it.id.toString() === values.transportConditions?.toString()),
      tradeTypeCode: tradeTypeCodes.find(it => it.id.toString() === values.tradeTypeCode?.toString()),
      newPaymentConditions: paymentConditions.find(it => it.id.toString() === values.newPaymentConditions?.toString()),
      justificationDeductionAmount: justificationDeductionAmounts.find(
        it => it.id.toString() === values.justificationDeductionAmount?.toString(),
      ),
      products: mapIdList(values.products),
      reverseOfJustificationDocumentTransactions: mapIdList(values.reverseOfJustificationDocumentTransactions),
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
          ...customJustificationEntity,
          vehicleEnterNationality: customJustificationEntity?.vehicleEnterNationality?.id,
          container: customJustificationEntity?.container?.id,
          vehicleCrossNationality: customJustificationEntity?.vehicleCrossNationality?.id,
          exportCustom: customJustificationEntity?.exportCustom?.id,
          entranceCustom: customJustificationEntity?.entranceCustom?.id,
          transportConditions: customJustificationEntity?.transportConditions?.id,
          tradeTypeCode: customJustificationEntity?.tradeTypeCode?.id,
          newPaymentConditions: customJustificationEntity?.newPaymentConditions?.id,
          justificationDeductionAmount: customJustificationEntity?.justificationDeductionAmount?.id,
          products: customJustificationEntity?.products?.map(e => e.id.toString()),
          reverseOfJustificationDocumentTransactions: customJustificationEntity?.reverseOfJustificationDocumentTransactions?.map(e =>
            e.id.toString(),
          ),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.customJustification.home.createOrEditLabel" data-cy="CustomJustificationCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.customJustification.home.createOrEditLabel">Create or edit a CustomJustification</Translate>
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
                  id="custom-justification-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.customJustification.agentId')}
                id="custom-justification-agentId"
                name="agentId"
                data-cy="agentId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.agriculturalPublicPolicies')}
                id="custom-justification-agriculturalPublicPolicies"
                name="agriculturalPublicPolicies"
                data-cy="agriculturalPublicPolicies"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.assessmentPlace')}
                id="custom-justification-assessmentPlace"
                name="assessmentPlace"
                data-cy="assessmentPlace"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.attachedDocuments')}
                id="custom-justification-attachedDocuments"
                name="attachedDocuments"
                data-cy="attachedDocuments"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.balanceRate')}
                id="custom-justification-balanceRate"
                name="balanceRate"
                data-cy="balanceRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.bankCode')}
                id="custom-justification-bankCode"
                name="bankCode"
                data-cy="bankCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.borderTransportType')}
                id="custom-justification-borderTransportType"
                name="borderTransportType"
                data-cy="borderTransportType"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.boxCountType')}
                id="custom-justification-boxCountType"
                name="boxCountType"
                data-cy="boxCountType"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.boxMarks')}
                id="custom-justification-boxMarks"
                name="boxMarks"
                data-cy="boxMarks"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.cargoIndexNumber')}
                id="custom-justification-cargoIndexNumber"
                name="cargoIndexNumber"
                data-cy="cargoIndexNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.centralBankCreditCode')}
                id="custom-justification-centralBankCreditCode"
                name="centralBankCreditCode"
                data-cy="centralBankCreditCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.comments')}
                id="custom-justification-comments"
                name="comments"
                data-cy="comments"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.constructorCountryCode')}
                id="custom-justification-constructorCountryCode"
                name="constructorCountryCode"
                data-cy="constructorCountryCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.costDetails')}
                id="custom-justification-costDetails"
                name="costDetails"
                data-cy="costDetails"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.cottageNumber')}
                id="custom-justification-cottageNumber"
                name="cottageNumber"
                data-cy="cottageNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.creditEquivalentAmount')}
                id="custom-justification-creditEquivalentAmount"
                name="creditEquivalentAmount"
                data-cy="creditEquivalentAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.currency')}
                id="custom-justification-currency"
                name="currency"
                data-cy="currency"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.currencyAmount')}
                id="custom-justification-currencyAmount"
                name="currencyAmount"
                data-cy="currencyAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.currencyRate')}
                id="custom-justification-currencyRate"
                name="currencyRate"
                data-cy="currencyRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.currencySwiftCode')}
                id="custom-justification-currencySwiftCode"
                name="currencySwiftCode"
                data-cy="currencySwiftCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.customCompanyCode')}
                id="custom-justification-customCompanyCode"
                name="customCompanyCode"
                data-cy="customCompanyCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.customerId')}
                id="custom-justification-customerId"
                name="customerId"
                data-cy="customerId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.date')}
                id="custom-justification-date"
                name="date"
                data-cy="date"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.destCountryCode')}
                id="custom-justification-destCountryCode"
                name="destCountryCode"
                data-cy="destCountryCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.destCustom')}
                id="custom-justification-destCustom"
                name="destCustom"
                data-cy="destCustom"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.destCustomCode')}
                id="custom-justification-destCustomCode"
                name="destCustomCode"
                data-cy="destCustomCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.disciplinaryDocumentsIssued')}
                id="custom-justification-disciplinaryDocumentsIssued"
                name="disciplinaryDocumentsIssued"
                data-cy="disciplinaryDocumentsIssued"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.discountPercent')}
                id="custom-justification-discountPercent"
                name="discountPercent"
                data-cy="discountPercent"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.eightDigitOrderCode')}
                id="custom-justification-eightDigitOrderCode"
                name="eightDigitOrderCode"
                data-cy="eightDigitOrderCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.entranceCustomCompany')}
                id="custom-justification-entranceCustomCompany"
                name="entranceCustomCompany"
                data-cy="entranceCustomCompany"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.entranceCustomCompanyId')}
                id="custom-justification-entranceCustomCompanyId"
                name="entranceCustomCompanyId"
                data-cy="entranceCustomCompanyId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.evacuationPlace')}
                id="custom-justification-evacuationPlace"
                name="evacuationPlace"
                data-cy="evacuationPlace"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.evaluationMethodCode')}
                id="custom-justification-evaluationMethodCode"
                name="evaluationMethodCode"
                data-cy="evaluationMethodCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.exportDate')}
                id="custom-justification-exportDate"
                name="exportDate"
                data-cy="exportDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.exporter')}
                id="custom-justification-exporter"
                name="exporter"
                data-cy="exporter"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.exporterCountryCode')}
                id="custom-justification-exporterCountryCode"
                name="exporterCountryCode"
                data-cy="exporterCountryCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.extensionDate')}
                id="custom-justification-extensionDate"
                name="extensionDate"
                data-cy="extensionDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.factorTotalAmount')}
                id="custom-justification-factorTotalAmount"
                name="factorTotalAmount"
                data-cy="factorTotalAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.freightIndexNumber')}
                id="custom-justification-freightIndexNumber"
                name="freightIndexNumber"
                data-cy="freightIndexNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.frightLetter')}
                id="custom-justification-frightLetter"
                name="frightLetter"
                data-cy="frightLetter"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.importLicence')}
                id="custom-justification-importLicence"
                name="importLicence"
                data-cy="importLicence"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.importLicense')}
                id="custom-justification-importLicense"
                name="importLicense"
                data-cy="importLicense"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.impureWeight')}
                id="custom-justification-impureWeight"
                name="impureWeight"
                data-cy="impureWeight"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.indices')}
                id="custom-justification-indices"
                name="indices"
                data-cy="indices"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.internalTransportType')}
                id="custom-justification-internalTransportType"
                name="internalTransportType"
                data-cy="internalTransportType"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.issuanceDate')}
                id="custom-justification-issuanceDate"
                name="issuanceDate"
                data-cy="issuanceDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.itemNumber')}
                id="custom-justification-itemNumber"
                name="itemNumber"
                data-cy="itemNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.items')}
                id="custom-justification-items"
                name="items"
                data-cy="items"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.justificationAgent')}
                id="custom-justification-justificationAgent"
                name="justificationAgent"
                data-cy="justificationAgent"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.justificationCurrencyRate')}
                id="custom-justification-justificationCurrencyRate"
                name="justificationCurrencyRate"
                data-cy="justificationCurrencyRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.licenceNumber')}
                id="custom-justification-licenceNumber"
                name="licenceNumber"
                data-cy="licenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.makeCertificateNumber')}
                id="custom-justification-makeCertificateNumber"
                name="makeCertificateNumber"
                data-cy="makeCertificateNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.newBorderTransportType')}
                id="custom-justification-newBorderTransportType"
                name="newBorderTransportType"
                data-cy="newBorderTransportType"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.newEvacuationPlace')}
                id="custom-justification-newEvacuationPlace"
                name="newEvacuationPlace"
                data-cy="newEvacuationPlace"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.newInternalTransportType')}
                id="custom-justification-newInternalTransportType"
                name="newInternalTransportType"
                data-cy="newInternalTransportType"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.newProductItemCustomValue')}
                id="custom-justification-newProductItemCustomValue"
                name="newProductItemCustomValue"
                data-cy="newProductItemCustomValue"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.orderRegistrationDate')}
                id="custom-justification-orderRegistrationDate"
                name="orderRegistrationDate"
                data-cy="orderRegistrationDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.orderRegistrationNumber')}
                id="custom-justification-orderRegistrationNumber"
                name="orderRegistrationNumber"
                data-cy="orderRegistrationNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.papers')}
                id="custom-justification-papers"
                name="papers"
                data-cy="papers"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.paymentConditions')}
                id="custom-justification-paymentConditions"
                name="paymentConditions"
                data-cy="paymentConditions"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.preferences')}
                id="custom-justification-preferences"
                name="preferences"
                data-cy="preferences"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.procedure')}
                id="custom-justification-procedure"
                name="procedure"
                data-cy="procedure"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.productBoxCount')}
                id="custom-justification-productBoxCount"
                name="productBoxCount"
                data-cy="productBoxCount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.productCode')}
                id="custom-justification-productCode"
                name="productCode"
                data-cy="productCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.productItemCost')}
                id="custom-justification-productItemCost"
                name="productItemCost"
                data-cy="productItemCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.productItemCount')}
                id="custom-justification-productItemCount"
                name="productItemCount"
                data-cy="productItemCount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.productItemCustomValue')}
                id="custom-justification-productItemCustomValue"
                name="productItemCustomValue"
                data-cy="productItemCustomValue"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.productItemValue')}
                id="custom-justification-productItemValue"
                name="productItemValue"
                data-cy="productItemValue"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.productMeasure')}
                id="custom-justification-productMeasure"
                name="productMeasure"
                data-cy="productMeasure"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.productOwner')}
                id="custom-justification-productOwner"
                name="productOwner"
                data-cy="productOwner"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.productReleaseDate')}
                id="custom-justification-productReleaseDate"
                name="productReleaseDate"
                data-cy="productReleaseDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.productType')}
                id="custom-justification-productType"
                name="productType"
                data-cy="productType"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.productWorth')}
                id="custom-justification-productWorth"
                name="productWorth"
                data-cy="productWorth"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.profitRate')}
                id="custom-justification-profitRate"
                name="profitRate"
                data-cy="profitRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.pureWeight')}
                id="custom-justification-pureWeight"
                name="pureWeight"
                data-cy="pureWeight"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.quota')}
                id="custom-justification-quota"
                name="quota"
                data-cy="quota"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.receiver')}
                id="custom-justification-receiver"
                name="receiver"
                data-cy="receiver"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.referenceNumber')}
                id="custom-justification-referenceNumber"
                name="referenceNumber"
                data-cy="referenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.registrationNumber')}
                id="custom-justification-registrationNumber"
                name="registrationNumber"
                data-cy="registrationNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.reverseOfJustificationDisciplinaryDocumentNumber')}
                id="custom-justification-reverseOfJustificationDisciplinaryDocumentNumber"
                name="reverseOfJustificationDisciplinaryDocumentNumber"
                data-cy="reverseOfJustificationDisciplinaryDocumentNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.reverseOfJustificationDocumentsIssued')}
                id="custom-justification-reverseOfJustificationDocumentsIssued"
                name="reverseOfJustificationDocumentsIssued"
                data-cy="reverseOfJustificationDocumentsIssued"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.rightsRate')}
                id="custom-justification-rightsRate"
                name="rightsRate"
                data-cy="rightsRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.tradingCountryCode')}
                id="custom-justification-tradingCountryCode"
                name="tradingCountryCode"
                data-cy="tradingCountryCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.transactionTypeCode')}
                id="custom-justification-transactionTypeCode"
                name="transactionTypeCode"
                data-cy="transactionTypeCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.warehouseFactorNumber')}
                id="custom-justification-warehouseFactorNumber"
                name="warehouseFactorNumber"
                data-cy="warehouseFactorNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.constructorCountryName')}
                id="custom-justification-constructorCountryName"
                name="constructorCountryName"
                data-cy="constructorCountryName"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.lastCountryName')}
                id="custom-justification-lastCountryName"
                name="lastCountryName"
                data-cy="lastCountryName"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.branchCode')}
                id="custom-justification-branchCode"
                name="branchCode"
                data-cy="branchCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.justificationCurrencyCode')}
                id="custom-justification-justificationCurrencyCode"
                name="justificationCurrencyCode"
                data-cy="justificationCurrencyCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.creditCurrencyCode')}
                id="custom-justification-creditCurrencyCode"
                name="creditCurrencyCode"
                data-cy="creditCurrencyCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.isMigrational')}
                id="custom-justification-isMigrational"
                name="isMigrational"
                data-cy="isMigrational"
                check
                type="checkbox"
              />
              <ValidatedBlobField
                label={translate('tfbitaApp.customJustification.originalLetterImage')}
                id="custom-justification-originalLetterImage"
                name="originalLetterImage"
                data-cy="originalLetterImage"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedBlobField
                label={translate('tfbitaApp.customJustification.letterImage')}
                id="custom-justification-letterImage"
                name="letterImage"
                data-cy="letterImage"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('tfbitaApp.customJustification.sourceCountryCode')}
                id="custom-justification-sourceCountryCode"
                name="sourceCountryCode"
                data-cy="sourceCountryCode"
                type="text"
              />
              <ValidatedField
                id="custom-justification-vehicleEnterNationality"
                name="vehicleEnterNationality"
                data-cy="vehicleEnterNationality"
                label={translate('tfbitaApp.customJustification.vehicleEnterNationality')}
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
                id="custom-justification-container"
                name="container"
                data-cy="container"
                label={translate('tfbitaApp.customJustification.container')}
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
                id="custom-justification-vehicleCrossNationality"
                name="vehicleCrossNationality"
                data-cy="vehicleCrossNationality"
                label={translate('tfbitaApp.customJustification.vehicleCrossNationality')}
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
                id="custom-justification-exportCustom"
                name="exportCustom"
                data-cy="exportCustom"
                label={translate('tfbitaApp.customJustification.exportCustom')}
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
                id="custom-justification-entranceCustom"
                name="entranceCustom"
                data-cy="entranceCustom"
                label={translate('tfbitaApp.customJustification.entranceCustom')}
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
                id="custom-justification-transportConditions"
                name="transportConditions"
                data-cy="transportConditions"
                label={translate('tfbitaApp.customJustification.transportConditions')}
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
                id="custom-justification-tradeTypeCode"
                name="tradeTypeCode"
                data-cy="tradeTypeCode"
                label={translate('tfbitaApp.customJustification.tradeTypeCode')}
                type="select"
              >
                <option value="" key="0" />
                {tradeTypeCodes
                  ? tradeTypeCodes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="custom-justification-newPaymentConditions"
                name="newPaymentConditions"
                data-cy="newPaymentConditions"
                label={translate('tfbitaApp.customJustification.newPaymentConditions')}
                type="select"
              >
                <option value="" key="0" />
                {paymentConditions
                  ? paymentConditions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="custom-justification-justificationDeductionAmount"
                name="justificationDeductionAmount"
                data-cy="justificationDeductionAmount"
                label={translate('tfbitaApp.customJustification.justificationDeductionAmount')}
                type="select"
              >
                <option value="" key="0" />
                {justificationDeductionAmounts
                  ? justificationDeductionAmounts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('tfbitaApp.customJustification.products')}
                id="custom-justification-products"
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
                label={translate('tfbitaApp.customJustification.reverseOfJustificationDocumentTransactions')}
                id="custom-justification-reverseOfJustificationDocumentTransactions"
                data-cy="reverseOfJustificationDocumentTransactions"
                type="select"
                multiple
                name="reverseOfJustificationDocumentTransactions"
              >
                <option value="" key="0" />
                {documentTransactions
                  ? documentTransactions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/custom-justification" replace color="info">
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

export default CustomJustificationUpdate;
