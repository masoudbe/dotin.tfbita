import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './custom-justification.reducer';

export const CustomJustificationDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const customJustificationEntity = useAppSelector(state => state.customJustification.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="customJustificationDetailsHeading">
          <Translate contentKey="tfbitaApp.customJustification.detail.title">CustomJustification</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.id}</dd>
          <dt>
            <span id="agentId">
              <Translate contentKey="tfbitaApp.customJustification.agentId">Agent Id</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.agentId}</dd>
          <dt>
            <span id="agriculturalPublicPolicies">
              <Translate contentKey="tfbitaApp.customJustification.agriculturalPublicPolicies">Agricultural Public Policies</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.agriculturalPublicPolicies}</dd>
          <dt>
            <span id="assessmentPlace">
              <Translate contentKey="tfbitaApp.customJustification.assessmentPlace">Assessment Place</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.assessmentPlace}</dd>
          <dt>
            <span id="attachedDocuments">
              <Translate contentKey="tfbitaApp.customJustification.attachedDocuments">Attached Documents</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.attachedDocuments}</dd>
          <dt>
            <span id="balanceRate">
              <Translate contentKey="tfbitaApp.customJustification.balanceRate">Balance Rate</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.balanceRate}</dd>
          <dt>
            <span id="bankCode">
              <Translate contentKey="tfbitaApp.customJustification.bankCode">Bank Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.bankCode}</dd>
          <dt>
            <span id="borderTransportType">
              <Translate contentKey="tfbitaApp.customJustification.borderTransportType">Border Transport Type</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.borderTransportType}</dd>
          <dt>
            <span id="boxCountType">
              <Translate contentKey="tfbitaApp.customJustification.boxCountType">Box Count Type</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.boxCountType}</dd>
          <dt>
            <span id="boxMarks">
              <Translate contentKey="tfbitaApp.customJustification.boxMarks">Box Marks</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.boxMarks}</dd>
          <dt>
            <span id="cargoIndexNumber">
              <Translate contentKey="tfbitaApp.customJustification.cargoIndexNumber">Cargo Index Number</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.cargoIndexNumber}</dd>
          <dt>
            <span id="centralBankCreditCode">
              <Translate contentKey="tfbitaApp.customJustification.centralBankCreditCode">Central Bank Credit Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.centralBankCreditCode}</dd>
          <dt>
            <span id="comments">
              <Translate contentKey="tfbitaApp.customJustification.comments">Comments</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.comments}</dd>
          <dt>
            <span id="constructorCountryCode">
              <Translate contentKey="tfbitaApp.customJustification.constructorCountryCode">Constructor Country Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.constructorCountryCode}</dd>
          <dt>
            <span id="costDetails">
              <Translate contentKey="tfbitaApp.customJustification.costDetails">Cost Details</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.costDetails}</dd>
          <dt>
            <span id="cottageNumber">
              <Translate contentKey="tfbitaApp.customJustification.cottageNumber">Cottage Number</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.cottageNumber}</dd>
          <dt>
            <span id="creditEquivalentAmount">
              <Translate contentKey="tfbitaApp.customJustification.creditEquivalentAmount">Credit Equivalent Amount</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.creditEquivalentAmount}</dd>
          <dt>
            <span id="currency">
              <Translate contentKey="tfbitaApp.customJustification.currency">Currency</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.currency}</dd>
          <dt>
            <span id="currencyAmount">
              <Translate contentKey="tfbitaApp.customJustification.currencyAmount">Currency Amount</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.currencyAmount}</dd>
          <dt>
            <span id="currencyRate">
              <Translate contentKey="tfbitaApp.customJustification.currencyRate">Currency Rate</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.currencyRate}</dd>
          <dt>
            <span id="currencySwiftCode">
              <Translate contentKey="tfbitaApp.customJustification.currencySwiftCode">Currency Swift Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.currencySwiftCode}</dd>
          <dt>
            <span id="customCompanyCode">
              <Translate contentKey="tfbitaApp.customJustification.customCompanyCode">Custom Company Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.customCompanyCode}</dd>
          <dt>
            <span id="customerId">
              <Translate contentKey="tfbitaApp.customJustification.customerId">Customer Id</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.customerId}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="tfbitaApp.customJustification.date">Date</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.date}</dd>
          <dt>
            <span id="destCountryCode">
              <Translate contentKey="tfbitaApp.customJustification.destCountryCode">Dest Country Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.destCountryCode}</dd>
          <dt>
            <span id="destCustom">
              <Translate contentKey="tfbitaApp.customJustification.destCustom">Dest Custom</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.destCustom}</dd>
          <dt>
            <span id="destCustomCode">
              <Translate contentKey="tfbitaApp.customJustification.destCustomCode">Dest Custom Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.destCustomCode}</dd>
          <dt>
            <span id="disciplinaryDocumentsIssued">
              <Translate contentKey="tfbitaApp.customJustification.disciplinaryDocumentsIssued">Disciplinary Documents Issued</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.disciplinaryDocumentsIssued ? 'true' : 'false'}</dd>
          <dt>
            <span id="discountPercent">
              <Translate contentKey="tfbitaApp.customJustification.discountPercent">Discount Percent</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.discountPercent}</dd>
          <dt>
            <span id="eightDigitOrderCode">
              <Translate contentKey="tfbitaApp.customJustification.eightDigitOrderCode">Eight Digit Order Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.eightDigitOrderCode}</dd>
          <dt>
            <span id="entranceCustomCompany">
              <Translate contentKey="tfbitaApp.customJustification.entranceCustomCompany">Entrance Custom Company</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.entranceCustomCompany}</dd>
          <dt>
            <span id="entranceCustomCompanyId">
              <Translate contentKey="tfbitaApp.customJustification.entranceCustomCompanyId">Entrance Custom Company Id</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.entranceCustomCompanyId}</dd>
          <dt>
            <span id="evacuationPlace">
              <Translate contentKey="tfbitaApp.customJustification.evacuationPlace">Evacuation Place</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.evacuationPlace}</dd>
          <dt>
            <span id="evaluationMethodCode">
              <Translate contentKey="tfbitaApp.customJustification.evaluationMethodCode">Evaluation Method Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.evaluationMethodCode}</dd>
          <dt>
            <span id="exportDate">
              <Translate contentKey="tfbitaApp.customJustification.exportDate">Export Date</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.exportDate}</dd>
          <dt>
            <span id="exporter">
              <Translate contentKey="tfbitaApp.customJustification.exporter">Exporter</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.exporter}</dd>
          <dt>
            <span id="exporterCountryCode">
              <Translate contentKey="tfbitaApp.customJustification.exporterCountryCode">Exporter Country Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.exporterCountryCode}</dd>
          <dt>
            <span id="extensionDate">
              <Translate contentKey="tfbitaApp.customJustification.extensionDate">Extension Date</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.extensionDate}</dd>
          <dt>
            <span id="factorTotalAmount">
              <Translate contentKey="tfbitaApp.customJustification.factorTotalAmount">Factor Total Amount</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.factorTotalAmount}</dd>
          <dt>
            <span id="freightIndexNumber">
              <Translate contentKey="tfbitaApp.customJustification.freightIndexNumber">Freight Index Number</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.freightIndexNumber}</dd>
          <dt>
            <span id="frightLetter">
              <Translate contentKey="tfbitaApp.customJustification.frightLetter">Fright Letter</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.frightLetter}</dd>
          <dt>
            <span id="importLicence">
              <Translate contentKey="tfbitaApp.customJustification.importLicence">Import Licence</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.importLicence}</dd>
          <dt>
            <span id="importLicense">
              <Translate contentKey="tfbitaApp.customJustification.importLicense">Import License</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.importLicense}</dd>
          <dt>
            <span id="impureWeight">
              <Translate contentKey="tfbitaApp.customJustification.impureWeight">Impure Weight</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.impureWeight}</dd>
          <dt>
            <span id="indices">
              <Translate contentKey="tfbitaApp.customJustification.indices">Indices</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.indices}</dd>
          <dt>
            <span id="internalTransportType">
              <Translate contentKey="tfbitaApp.customJustification.internalTransportType">Internal Transport Type</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.internalTransportType}</dd>
          <dt>
            <span id="issuanceDate">
              <Translate contentKey="tfbitaApp.customJustification.issuanceDate">Issuance Date</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.issuanceDate}</dd>
          <dt>
            <span id="itemNumber">
              <Translate contentKey="tfbitaApp.customJustification.itemNumber">Item Number</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.itemNumber}</dd>
          <dt>
            <span id="items">
              <Translate contentKey="tfbitaApp.customJustification.items">Items</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.items}</dd>
          <dt>
            <span id="justificationAgent">
              <Translate contentKey="tfbitaApp.customJustification.justificationAgent">Justification Agent</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.justificationAgent}</dd>
          <dt>
            <span id="justificationCurrencyRate">
              <Translate contentKey="tfbitaApp.customJustification.justificationCurrencyRate">Justification Currency Rate</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.justificationCurrencyRate}</dd>
          <dt>
            <span id="licenceNumber">
              <Translate contentKey="tfbitaApp.customJustification.licenceNumber">Licence Number</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.licenceNumber}</dd>
          <dt>
            <span id="makeCertificateNumber">
              <Translate contentKey="tfbitaApp.customJustification.makeCertificateNumber">Make Certificate Number</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.makeCertificateNumber}</dd>
          <dt>
            <span id="newBorderTransportType">
              <Translate contentKey="tfbitaApp.customJustification.newBorderTransportType">New Border Transport Type</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.newBorderTransportType}</dd>
          <dt>
            <span id="newEvacuationPlace">
              <Translate contentKey="tfbitaApp.customJustification.newEvacuationPlace">New Evacuation Place</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.newEvacuationPlace}</dd>
          <dt>
            <span id="newInternalTransportType">
              <Translate contentKey="tfbitaApp.customJustification.newInternalTransportType">New Internal Transport Type</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.newInternalTransportType}</dd>
          <dt>
            <span id="newProductItemCustomValue">
              <Translate contentKey="tfbitaApp.customJustification.newProductItemCustomValue">New Product Item Custom Value</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.newProductItemCustomValue}</dd>
          <dt>
            <span id="orderRegistrationDate">
              <Translate contentKey="tfbitaApp.customJustification.orderRegistrationDate">Order Registration Date</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.orderRegistrationDate}</dd>
          <dt>
            <span id="orderRegistrationNumber">
              <Translate contentKey="tfbitaApp.customJustification.orderRegistrationNumber">Order Registration Number</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.orderRegistrationNumber}</dd>
          <dt>
            <span id="papers">
              <Translate contentKey="tfbitaApp.customJustification.papers">Papers</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.papers}</dd>
          <dt>
            <span id="paymentConditions">
              <Translate contentKey="tfbitaApp.customJustification.paymentConditions">Payment Conditions</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.paymentConditions}</dd>
          <dt>
            <span id="preferences">
              <Translate contentKey="tfbitaApp.customJustification.preferences">Preferences</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.preferences}</dd>
          <dt>
            <span id="procedure">
              <Translate contentKey="tfbitaApp.customJustification.procedure">Procedure</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.procedure}</dd>
          <dt>
            <span id="productBoxCount">
              <Translate contentKey="tfbitaApp.customJustification.productBoxCount">Product Box Count</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.productBoxCount}</dd>
          <dt>
            <span id="productCode">
              <Translate contentKey="tfbitaApp.customJustification.productCode">Product Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.productCode}</dd>
          <dt>
            <span id="productItemCost">
              <Translate contentKey="tfbitaApp.customJustification.productItemCost">Product Item Cost</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.productItemCost}</dd>
          <dt>
            <span id="productItemCount">
              <Translate contentKey="tfbitaApp.customJustification.productItemCount">Product Item Count</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.productItemCount}</dd>
          <dt>
            <span id="productItemCustomValue">
              <Translate contentKey="tfbitaApp.customJustification.productItemCustomValue">Product Item Custom Value</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.productItemCustomValue}</dd>
          <dt>
            <span id="productItemValue">
              <Translate contentKey="tfbitaApp.customJustification.productItemValue">Product Item Value</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.productItemValue}</dd>
          <dt>
            <span id="productMeasure">
              <Translate contentKey="tfbitaApp.customJustification.productMeasure">Product Measure</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.productMeasure}</dd>
          <dt>
            <span id="productOwner">
              <Translate contentKey="tfbitaApp.customJustification.productOwner">Product Owner</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.productOwner}</dd>
          <dt>
            <span id="productReleaseDate">
              <Translate contentKey="tfbitaApp.customJustification.productReleaseDate">Product Release Date</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.productReleaseDate}</dd>
          <dt>
            <span id="productType">
              <Translate contentKey="tfbitaApp.customJustification.productType">Product Type</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.productType}</dd>
          <dt>
            <span id="productWorth">
              <Translate contentKey="tfbitaApp.customJustification.productWorth">Product Worth</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.productWorth}</dd>
          <dt>
            <span id="profitRate">
              <Translate contentKey="tfbitaApp.customJustification.profitRate">Profit Rate</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.profitRate}</dd>
          <dt>
            <span id="pureWeight">
              <Translate contentKey="tfbitaApp.customJustification.pureWeight">Pure Weight</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.pureWeight}</dd>
          <dt>
            <span id="quota">
              <Translate contentKey="tfbitaApp.customJustification.quota">Quota</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.quota}</dd>
          <dt>
            <span id="receiver">
              <Translate contentKey="tfbitaApp.customJustification.receiver">Receiver</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.receiver}</dd>
          <dt>
            <span id="referenceNumber">
              <Translate contentKey="tfbitaApp.customJustification.referenceNumber">Reference Number</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.referenceNumber}</dd>
          <dt>
            <span id="registrationNumber">
              <Translate contentKey="tfbitaApp.customJustification.registrationNumber">Registration Number</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.registrationNumber}</dd>
          <dt>
            <span id="reverseOfJustificationDisciplinaryDocumentNumber">
              <Translate contentKey="tfbitaApp.customJustification.reverseOfJustificationDisciplinaryDocumentNumber">
                Reverse Of Justification Disciplinary Document Number
              </Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.reverseOfJustificationDisciplinaryDocumentNumber}</dd>
          <dt>
            <span id="reverseOfJustificationDocumentsIssued">
              <Translate contentKey="tfbitaApp.customJustification.reverseOfJustificationDocumentsIssued">
                Reverse Of Justification Documents Issued
              </Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.reverseOfJustificationDocumentsIssued ? 'true' : 'false'}</dd>
          <dt>
            <span id="rightsRate">
              <Translate contentKey="tfbitaApp.customJustification.rightsRate">Rights Rate</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.rightsRate}</dd>
          <dt>
            <span id="tradingCountryCode">
              <Translate contentKey="tfbitaApp.customJustification.tradingCountryCode">Trading Country Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.tradingCountryCode}</dd>
          <dt>
            <span id="transactionTypeCode">
              <Translate contentKey="tfbitaApp.customJustification.transactionTypeCode">Transaction Type Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.transactionTypeCode}</dd>
          <dt>
            <span id="warehouseFactorNumber">
              <Translate contentKey="tfbitaApp.customJustification.warehouseFactorNumber">Warehouse Factor Number</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.warehouseFactorNumber}</dd>
          <dt>
            <span id="constructorCountryName">
              <Translate contentKey="tfbitaApp.customJustification.constructorCountryName">Constructor Country Name</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.constructorCountryName}</dd>
          <dt>
            <span id="lastCountryName">
              <Translate contentKey="tfbitaApp.customJustification.lastCountryName">Last Country Name</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.lastCountryName}</dd>
          <dt>
            <span id="branchCode">
              <Translate contentKey="tfbitaApp.customJustification.branchCode">Branch Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.branchCode}</dd>
          <dt>
            <span id="justificationCurrencyCode">
              <Translate contentKey="tfbitaApp.customJustification.justificationCurrencyCode">Justification Currency Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.justificationCurrencyCode}</dd>
          <dt>
            <span id="creditCurrencyCode">
              <Translate contentKey="tfbitaApp.customJustification.creditCurrencyCode">Credit Currency Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.creditCurrencyCode}</dd>
          <dt>
            <span id="isMigrational">
              <Translate contentKey="tfbitaApp.customJustification.isMigrational">Is Migrational</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.isMigrational ? 'true' : 'false'}</dd>
          <dt>
            <span id="originalLetterImage">
              <Translate contentKey="tfbitaApp.customJustification.originalLetterImage">Original Letter Image</Translate>
            </span>
          </dt>
          <dd>
            {customJustificationEntity.originalLetterImage ? (
              <div>
                {customJustificationEntity.originalLetterImageContentType ? (
                  <a
                    onClick={openFile(
                      customJustificationEntity.originalLetterImageContentType,
                      customJustificationEntity.originalLetterImage,
                    )}
                  >
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {customJustificationEntity.originalLetterImageContentType}, {byteSize(customJustificationEntity.originalLetterImage)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="letterImage">
              <Translate contentKey="tfbitaApp.customJustification.letterImage">Letter Image</Translate>
            </span>
          </dt>
          <dd>
            {customJustificationEntity.letterImage ? (
              <div>
                {customJustificationEntity.letterImageContentType ? (
                  <a onClick={openFile(customJustificationEntity.letterImageContentType, customJustificationEntity.letterImage)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {customJustificationEntity.letterImageContentType}, {byteSize(customJustificationEntity.letterImage)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="sourceCountryCode">
              <Translate contentKey="tfbitaApp.customJustification.sourceCountryCode">Source Country Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationEntity.sourceCountryCode}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.customJustification.vehicleEnterNationality">Vehicle Enter Nationality</Translate>
          </dt>
          <dd>{customJustificationEntity.vehicleEnterNationality ? customJustificationEntity.vehicleEnterNationality.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.customJustification.container">Container</Translate>
          </dt>
          <dd>{customJustificationEntity.container ? customJustificationEntity.container.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.customJustification.vehicleCrossNationality">Vehicle Cross Nationality</Translate>
          </dt>
          <dd>{customJustificationEntity.vehicleCrossNationality ? customJustificationEntity.vehicleCrossNationality.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.customJustification.exportCustom">Export Custom</Translate>
          </dt>
          <dd>{customJustificationEntity.exportCustom ? customJustificationEntity.exportCustom.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.customJustification.entranceCustom">Entrance Custom</Translate>
          </dt>
          <dd>{customJustificationEntity.entranceCustom ? customJustificationEntity.entranceCustom.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.customJustification.transportConditions">Transport Conditions</Translate>
          </dt>
          <dd>{customJustificationEntity.transportConditions ? customJustificationEntity.transportConditions.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.customJustification.tradeTypeCode">Trade Type Code</Translate>
          </dt>
          <dd>{customJustificationEntity.tradeTypeCode ? customJustificationEntity.tradeTypeCode.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.customJustification.newPaymentConditions">New Payment Conditions</Translate>
          </dt>
          <dd>{customJustificationEntity.newPaymentConditions ? customJustificationEntity.newPaymentConditions.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.customJustification.justificationDeductionAmount">Justification Deduction Amount</Translate>
          </dt>
          <dd>{customJustificationEntity.justificationDeductionAmount ? customJustificationEntity.justificationDeductionAmount.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.customJustification.products">Products</Translate>
          </dt>
          <dd>
            {customJustificationEntity.products
              ? customJustificationEntity.products.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {customJustificationEntity.products && i === customJustificationEntity.products.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.customJustification.reverseOfJustificationDocumentTransactions">
              Reverse Of Justification Document Transactions
            </Translate>
          </dt>
          <dd>
            {customJustificationEntity.reverseOfJustificationDocumentTransactions
              ? customJustificationEntity.reverseOfJustificationDocumentTransactions.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {customJustificationEntity.reverseOfJustificationDocumentTransactions &&
                    i === customJustificationEntity.reverseOfJustificationDocumentTransactions.length - 1
                      ? ''
                      : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/custom-justification" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/custom-justification/${customJustificationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CustomJustificationDetail;
