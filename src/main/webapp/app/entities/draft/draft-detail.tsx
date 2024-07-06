import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft.reducer';

export const DraftDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftEntity = useAppSelector(state => state.draft.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftDetailsHeading">
          <Translate contentKey="tfbitaApp.draft.detail.title">Draft</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftEntity.id}</dd>
          <dt>
            <span id="advisorDepositNumber">
              <Translate contentKey="tfbitaApp.draft.advisorDepositNumber">Advisor Deposit Number</Translate>
            </span>
          </dt>
          <dd>{draftEntity.advisorDepositNumber}</dd>
          <dt>
            <span id="advisorRequestDeleted">
              <Translate contentKey="tfbitaApp.draft.advisorRequestDeleted">Advisor Request Deleted</Translate>
            </span>
          </dt>
          <dd>{draftEntity.advisorRequestDeleted ? 'true' : 'false'}</dd>
          <dt>
            <span id="auditCost">
              <Translate contentKey="tfbitaApp.draft.auditCost">Audit Cost</Translate>
            </span>
          </dt>
          <dd>{draftEntity.auditCost}</dd>
          <dt>
            <span id="beneficiaryInfo">
              <Translate contentKey="tfbitaApp.draft.beneficiaryInfo">Beneficiary Info</Translate>
            </span>
          </dt>
          <dd>{draftEntity.beneficiaryInfo}</dd>
          <dt>
            <span id="branchStampCost">
              <Translate contentKey="tfbitaApp.draft.branchStampCost">Branch Stamp Cost</Translate>
            </span>
          </dt>
          <dd>{draftEntity.branchStampCost}</dd>
          <dt>
            <span id="centralBankCode">
              <Translate contentKey="tfbitaApp.draft.centralBankCode">Central Bank Code</Translate>
            </span>
          </dt>
          <dd>{draftEntity.centralBankCode}</dd>
          <dt>
            <span id="centralBankCodeReference">
              <Translate contentKey="tfbitaApp.draft.centralBankCodeReference">Central Bank Code Reference</Translate>
            </span>
          </dt>
          <dd>{draftEntity.centralBankCodeReference}</dd>
          <dt>
            <span id="chargedExchangeBrokerAmount">
              <Translate contentKey="tfbitaApp.draft.chargedExchangeBrokerAmount">Charged Exchange Broker Amount</Translate>
            </span>
          </dt>
          <dd>{draftEntity.chargedExchangeBrokerAmount}</dd>
          <dt>
            <span id="charterAcceptable">
              <Translate contentKey="tfbitaApp.draft.charterAcceptable">Charter Acceptable</Translate>
            </span>
          </dt>
          <dd>{draftEntity.charterAcceptable ? 'true' : 'false'}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="tfbitaApp.draft.comment">Comment</Translate>
            </span>
          </dt>
          <dd>{draftEntity.comment}</dd>
          <dt>
            <span id="completionDate">
              <Translate contentKey="tfbitaApp.draft.completionDate">Completion Date</Translate>
            </span>
          </dt>
          <dd>{draftEntity.completionDate}</dd>
          <dt>
            <span id="coveringBankDepositNumber">
              <Translate contentKey="tfbitaApp.draft.coveringBankDepositNumber">Covering Bank Deposit Number</Translate>
            </span>
          </dt>
          <dd>{draftEntity.coveringBankDepositNumber}</dd>
          <dt>
            <span id="currencyExchangeDepositNumber">
              <Translate contentKey="tfbitaApp.draft.currencyExchangeDepositNumber">Currency Exchange Deposit Number</Translate>
            </span>
          </dt>
          <dd>{draftEntity.currencyExchangeDepositNumber}</dd>
          <dt>
            <span id="customerDepositNumber">
              <Translate contentKey="tfbitaApp.draft.customerDepositNumber">Customer Deposit Number</Translate>
            </span>
          </dt>
          <dd>{draftEntity.customerDepositNumber}</dd>
          <dt>
            <span id="deliverDuration">
              <Translate contentKey="tfbitaApp.draft.deliverDuration">Deliver Duration</Translate>
            </span>
          </dt>
          <dd>{draftEntity.deliverDuration}</dd>
          <dt>
            <span id="discount">
              <Translate contentKey="tfbitaApp.draft.discount">Discount</Translate>
            </span>
          </dt>
          <dd>{draftEntity.discount}</dd>
          <dt>
            <span id="draftNumber">
              <Translate contentKey="tfbitaApp.draft.draftNumber">Draft Number</Translate>
            </span>
          </dt>
          <dd>{draftEntity.draftNumber}</dd>
          <dt>
            <span id="draftOrderRegProductWorth">
              <Translate contentKey="tfbitaApp.draft.draftOrderRegProductWorth">Draft Order Reg Product Worth</Translate>
            </span>
          </dt>
          <dd>{draftEntity.draftOrderRegProductWorth}</dd>
          <dt>
            <span id="draftOrderRegServiceWorth">
              <Translate contentKey="tfbitaApp.draft.draftOrderRegServiceWorth">Draft Order Reg Service Worth</Translate>
            </span>
          </dt>
          <dd>{draftEntity.draftOrderRegServiceWorth}</dd>
          <dt>
            <span id="draftOrderRegTotalWorth">
              <Translate contentKey="tfbitaApp.draft.draftOrderRegTotalWorth">Draft Order Reg Total Worth</Translate>
            </span>
          </dt>
          <dd>{draftEntity.draftOrderRegTotalWorth}</dd>
          <dt>
            <span id="draftOtherCost">
              <Translate contentKey="tfbitaApp.draft.draftOtherCost">Draft Other Cost</Translate>
            </span>
          </dt>
          <dd>{draftEntity.draftOtherCost}</dd>
          <dt>
            <span id="hasTransportJustification">
              <Translate contentKey="tfbitaApp.draft.hasTransportJustification">Has Transport Justification</Translate>
            </span>
          </dt>
          <dd>{draftEntity.hasTransportJustification ? 'true' : 'false'}</dd>
          <dt>
            <span id="insuranceCost">
              <Translate contentKey="tfbitaApp.draft.insuranceCost">Insurance Cost</Translate>
            </span>
          </dt>
          <dd>{draftEntity.insuranceCost}</dd>
          <dt>
            <span id="insuranceDate">
              <Translate contentKey="tfbitaApp.draft.insuranceDate">Insurance Date</Translate>
            </span>
          </dt>
          <dd>{draftEntity.insuranceDate}</dd>
          <dt>
            <span id="insuranceExpiryDate">
              <Translate contentKey="tfbitaApp.draft.insuranceExpiryDate">Insurance Expiry Date</Translate>
            </span>
          </dt>
          <dd>{draftEntity.insuranceExpiryDate}</dd>
          <dt>
            <span id="insuranceNumber">
              <Translate contentKey="tfbitaApp.draft.insuranceNumber">Insurance Number</Translate>
            </span>
          </dt>
          <dd>{draftEntity.insuranceNumber}</dd>
          <dt>
            <span id="interfaceAdvisorDepositNumber">
              <Translate contentKey="tfbitaApp.draft.interfaceAdvisorDepositNumber">Interface Advisor Deposit Number</Translate>
            </span>
          </dt>
          <dd>{draftEntity.interfaceAdvisorDepositNumber}</dd>
          <dt>
            <span id="issueDate">
              <Translate contentKey="tfbitaApp.draft.issueDate">Issue Date</Translate>
            </span>
          </dt>
          <dd>{draftEntity.issueDate}</dd>
          <dt>
            <span id="issueDraftCommission">
              <Translate contentKey="tfbitaApp.draft.issueDraftCommission">Issue Draft Commission</Translate>
            </span>
          </dt>
          <dd>{draftEntity.issueDraftCommission}</dd>
          <dt>
            <span id="lastShipmentDate">
              <Translate contentKey="tfbitaApp.draft.lastShipmentDate">Last Shipment Date</Translate>
            </span>
          </dt>
          <dd>{draftEntity.lastShipmentDate}</dd>
          <dt>
            <span id="mainCustomerNumber">
              <Translate contentKey="tfbitaApp.draft.mainCustomerNumber">Main Customer Number</Translate>
            </span>
          </dt>
          <dd>{draftEntity.mainCustomerNumber}</dd>
          <dt>
            <span id="makeCertification">
              <Translate contentKey="tfbitaApp.draft.makeCertification">Make Certification</Translate>
            </span>
          </dt>
          <dd>{draftEntity.makeCertification ? 'true' : 'false'}</dd>
          <dt>
            <span id="multipleTransportable">
              <Translate contentKey="tfbitaApp.draft.multipleTransportable">Multiple Transportable</Translate>
            </span>
          </dt>
          <dd>{draftEntity.multipleTransportable ? 'true' : 'false'}</dd>
          <dt>
            <span id="orderRegistrationDate">
              <Translate contentKey="tfbitaApp.draft.orderRegistrationDate">Order Registration Date</Translate>
            </span>
          </dt>
          <dd>{draftEntity.orderRegistrationDate}</dd>
          <dt>
            <span id="orderRegistrationExpiryDate">
              <Translate contentKey="tfbitaApp.draft.orderRegistrationExpiryDate">Order Registration Expiry Date</Translate>
            </span>
          </dt>
          <dd>{draftEntity.orderRegistrationExpiryDate}</dd>
          <dt>
            <span id="orderRegistrationNumber">
              <Translate contentKey="tfbitaApp.draft.orderRegistrationNumber">Order Registration Number</Translate>
            </span>
          </dt>
          <dd>{draftEntity.orderRegistrationNumber}</dd>
          <dt>
            <span id="otherCost">
              <Translate contentKey="tfbitaApp.draft.otherCost">Other Cost</Translate>
            </span>
          </dt>
          <dd>{draftEntity.otherCost}</dd>
          <dt>
            <span id="paymentAmount">
              <Translate contentKey="tfbitaApp.draft.paymentAmount">Payment Amount</Translate>
            </span>
          </dt>
          <dd>{draftEntity.paymentAmount}</dd>
          <dt>
            <span id="performaDate">
              <Translate contentKey="tfbitaApp.draft.performaDate">Performa Date</Translate>
            </span>
          </dt>
          <dd>{draftEntity.performaDate}</dd>
          <dt>
            <span id="performaExpiryDate">
              <Translate contentKey="tfbitaApp.draft.performaExpiryDate">Performa Expiry Date</Translate>
            </span>
          </dt>
          <dd>{draftEntity.performaExpiryDate}</dd>
          <dt>
            <span id="performaNumber">
              <Translate contentKey="tfbitaApp.draft.performaNumber">Performa Number</Translate>
            </span>
          </dt>
          <dd>{draftEntity.performaNumber}</dd>
          <dt>
            <span id="postSwiftCost">
              <Translate contentKey="tfbitaApp.draft.postSwiftCost">Post Swift Cost</Translate>
            </span>
          </dt>
          <dd>{draftEntity.postSwiftCost}</dd>
          <dt>
            <span id="productSourceChangeable">
              <Translate contentKey="tfbitaApp.draft.productSourceChangeable">Product Source Changeable</Translate>
            </span>
          </dt>
          <dd>{draftEntity.productSourceChangeable ? 'true' : 'false'}</dd>
          <dt>
            <span id="receiveAllCommission">
              <Translate contentKey="tfbitaApp.draft.receiveAllCommission">Receive All Commission</Translate>
            </span>
          </dt>
          <dd>{draftEntity.receiveAllCommission ? 'true' : 'false'}</dd>
          <dt>
            <span id="registerationJustificationAmount">
              <Translate contentKey="tfbitaApp.draft.registerationJustificationAmount">Registeration Justification Amount</Translate>
            </span>
          </dt>
          <dd>{draftEntity.registerationJustificationAmount}</dd>
          <dt>
            <span id="requestDate">
              <Translate contentKey="tfbitaApp.draft.requestDate">Request Date</Translate>
            </span>
          </dt>
          <dd>{draftEntity.requestDate}</dd>
          <dt>
            <span id="sanctionSerial">
              <Translate contentKey="tfbitaApp.draft.sanctionSerial">Sanction Serial</Translate>
            </span>
          </dt>
          <dd>{draftEntity.sanctionSerial}</dd>
          <dt>
            <span id="serial">
              <Translate contentKey="tfbitaApp.draft.serial">Serial</Translate>
            </span>
          </dt>
          <dd>{draftEntity.serial}</dd>
          <dt>
            <span id="shipmentCost">
              <Translate contentKey="tfbitaApp.draft.shipmentCost">Shipment Cost</Translate>
            </span>
          </dt>
          <dd>{draftEntity.shipmentCost}</dd>
          <dt>
            <span id="sourceTransportPlace">
              <Translate contentKey="tfbitaApp.draft.sourceTransportPlace">Source Transport Place</Translate>
            </span>
          </dt>
          <dd>{draftEntity.sourceTransportPlace}</dd>
          <dt>
            <span id="swiftComment">
              <Translate contentKey="tfbitaApp.draft.swiftComment">Swift Comment</Translate>
            </span>
          </dt>
          <dd>{draftEntity.swiftComment}</dd>
          <dt>
            <span id="transferAmount">
              <Translate contentKey="tfbitaApp.draft.transferAmount">Transfer Amount</Translate>
            </span>
          </dt>
          <dd>{draftEntity.transferAmount}</dd>
          <dt>
            <span id="transportVehicleChangeable">
              <Translate contentKey="tfbitaApp.draft.transportVehicleChangeable">Transport Vehicle Changeable</Translate>
            </span>
          </dt>
          <dd>{draftEntity.transportVehicleChangeable ? 'true' : 'false'}</dd>
          <dt>
            <span id="paymentTool">
              <Translate contentKey="tfbitaApp.draft.paymentTool">Payment Tool</Translate>
            </span>
          </dt>
          <dd>{draftEntity.paymentTool}</dd>
          <dt>
            <span id="letterNumberTazirat">
              <Translate contentKey="tfbitaApp.draft.letterNumberTazirat">Letter Number Tazirat</Translate>
            </span>
          </dt>
          <dd>{draftEntity.letterNumberTazirat}</dd>
          <dt>
            <span id="letterDateTazirat">
              <Translate contentKey="tfbitaApp.draft.letterDateTazirat">Letter Date Tazirat</Translate>
            </span>
          </dt>
          <dd>{draftEntity.letterDateTazirat}</dd>
          <dt>
            <span id="loanNumber">
              <Translate contentKey="tfbitaApp.draft.loanNumber">Loan Number</Translate>
            </span>
          </dt>
          <dd>{draftEntity.loanNumber}</dd>
          <dt>
            <span id="isMigrational">
              <Translate contentKey="tfbitaApp.draft.isMigrational">Is Migrational</Translate>
            </span>
          </dt>
          <dd>{draftEntity.isMigrational ? 'true' : 'false'}</dd>
          <dt>
            <span id="isNewCertificate">
              <Translate contentKey="tfbitaApp.draft.isNewCertificate">Is New Certificate</Translate>
            </span>
          </dt>
          <dd>{draftEntity.isNewCertificate ? 'true' : 'false'}</dd>
          <dt>
            <span id="isWithoutPayment">
              <Translate contentKey="tfbitaApp.draft.isWithoutPayment">Is Without Payment</Translate>
            </span>
          </dt>
          <dd>{draftEntity.isWithoutPayment ? 'true' : 'false'}</dd>
          <dt>
            <span id="mainAccountCurrencyCode">
              <Translate contentKey="tfbitaApp.draft.mainAccountCurrencyCode">Main Account Currency Code</Translate>
            </span>
          </dt>
          <dd>{draftEntity.mainAccountCurrencyCode}</dd>
          <dt>
            <span id="orderRegCurrencyCode">
              <Translate contentKey="tfbitaApp.draft.orderRegCurrencyCode">Order Reg Currency Code</Translate>
            </span>
          </dt>
          <dd>{draftEntity.orderRegCurrencyCode}</dd>
          <dt>
            <span id="chargedExchangeBrokerCurrencyCode">
              <Translate contentKey="tfbitaApp.draft.chargedExchangeBrokerCurrencyCode">Charged Exchange Broker Currency Code</Translate>
            </span>
          </dt>
          <dd>{draftEntity.chargedExchangeBrokerCurrencyCode}</dd>
          <dt>
            <span id="destinationCountryCode">
              <Translate contentKey="tfbitaApp.draft.destinationCountryCode">Destination Country Code</Translate>
            </span>
          </dt>
          <dd>{draftEntity.destinationCountryCode}</dd>
          <dt>
            <span id="beneficiaryCountryCode">
              <Translate contentKey="tfbitaApp.draft.beneficiaryCountryCode">Beneficiary Country Code</Translate>
            </span>
          </dt>
          <dd>{draftEntity.beneficiaryCountryCode}</dd>
          <dt>
            <span id="producerCountryCode">
              <Translate contentKey="tfbitaApp.draft.producerCountryCode">Producer Country Code</Translate>
            </span>
          </dt>
          <dd>{draftEntity.producerCountryCode}</dd>
          <dt>
            <span id="registerationJustificationCurrencyCode">
              <Translate contentKey="tfbitaApp.draft.registerationJustificationCurrencyCode">
                Registeration Justification Currency Code
              </Translate>
            </span>
          </dt>
          <dd>{draftEntity.registerationJustificationCurrencyCode}</dd>
          <dt>
            <span id="branchCode">
              <Translate contentKey="tfbitaApp.draft.branchCode">Branch Code</Translate>
            </span>
          </dt>
          <dd>{draftEntity.branchCode}</dd>
          <dt>
            <span id="operationalBranchCode">
              <Translate contentKey="tfbitaApp.draft.operationalBranchCode">Operational Branch Code</Translate>
            </span>
          </dt>
          <dd>{draftEntity.operationalBranchCode}</dd>
          <dt>
            <span id="certificateSenderBranchCode">
              <Translate contentKey="tfbitaApp.draft.certificateSenderBranchCode">Certificate Sender Branch Code</Translate>
            </span>
          </dt>
          <dd>{draftEntity.certificateSenderBranchCode}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.chargedExchangeBroker">Charged Exchange Broker</Translate>
          </dt>
          <dd>{draftEntity.chargedExchangeBroker ? draftEntity.chargedExchangeBroker.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.insuranceLetterType">Insurance Letter Type</Translate>
          </dt>
          <dd>{draftEntity.insuranceLetterType ? draftEntity.insuranceLetterType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.advisorDepositType">Advisor Deposit Type</Translate>
          </dt>
          <dd>{draftEntity.advisorDepositType ? draftEntity.advisorDepositType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.interfaceAdvisorDepositType">Interface Advisor Deposit Type</Translate>
          </dt>
          <dd>{draftEntity.interfaceAdvisorDepositType ? draftEntity.interfaceAdvisorDepositType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.coveringAdvisorDepositType">Covering Advisor Deposit Type</Translate>
          </dt>
          <dd>{draftEntity.coveringAdvisorDepositType ? draftEntity.coveringAdvisorDepositType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.impartType">Impart Type</Translate>
          </dt>
          <dd>{draftEntity.impartType ? draftEntity.impartType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.dealType">Deal Type</Translate>
          </dt>
          <dd>{draftEntity.dealType ? draftEntity.dealType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.transportVehicleType">Transport Vehicle Type</Translate>
          </dt>
          <dd>{draftEntity.transportVehicleType ? draftEntity.transportVehicleType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.freightLetterType">Freight Letter Type</Translate>
          </dt>
          <dd>{draftEntity.freightLetterType ? draftEntity.freightLetterType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.actionCode">Action Code</Translate>
          </dt>
          <dd>{draftEntity.actionCode ? draftEntity.actionCode.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.ownershipCode">Ownership Code</Translate>
          </dt>
          <dd>{draftEntity.ownershipCode ? draftEntity.ownershipCode.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.currencyContainerPlace">Currency Container Place</Translate>
          </dt>
          <dd>{draftEntity.currencyContainerPlace ? draftEntity.currencyContainerPlace.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.paymentType">Payment Type</Translate>
          </dt>
          <dd>{draftEntity.paymentType ? draftEntity.paymentType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.draftSource">Draft Source</Translate>
          </dt>
          <dd>{draftEntity.draftSource ? draftEntity.draftSource.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.loadSwitchPlace">Load Switch Place</Translate>
          </dt>
          <dd>{draftEntity.loadSwitchPlace ? draftEntity.loadSwitchPlace.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.draftType">Draft Type</Translate>
          </dt>
          <dd>{draftEntity.draftType ? draftEntity.draftType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.statusInfo">Status Info</Translate>
          </dt>
          <dd>{draftEntity.statusInfo ? draftEntity.statusInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.insuranceCompanyInfo">Insurance Company Info</Translate>
          </dt>
          <dd>{draftEntity.insuranceCompanyInfo ? draftEntity.insuranceCompanyInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.advisingBank">Advising Bank</Translate>
          </dt>
          <dd>{draftEntity.advisingBank ? draftEntity.advisingBank.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.interfaceAdvisingBank">Interface Advising Bank</Translate>
          </dt>
          <dd>{draftEntity.interfaceAdvisingBank ? draftEntity.interfaceAdvisingBank.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.coveringBank">Covering Bank</Translate>
          </dt>
          <dd>{draftEntity.coveringBank ? draftEntity.coveringBank.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.auditCompanyInfo">Audit Company Info</Translate>
          </dt>
          <dd>{draftEntity.auditCompanyInfo ? draftEntity.auditCompanyInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.transportType">Transport Type</Translate>
          </dt>
          <dd>{draftEntity.transportType ? draftEntity.transportType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.currencyExchangeInfo">Currency Exchange Info</Translate>
          </dt>
          <dd>{draftEntity.currencyExchangeInfo ? draftEntity.currencyExchangeInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.accountInfo">Account Info</Translate>
          </dt>
          <dd>{draftEntity.accountInfo ? draftEntity.accountInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.destinationCustomCompanies">Destination Custom Companies</Translate>
          </dt>
          <dd>{draftEntity.destinationCustomCompanies ? draftEntity.destinationCustomCompanies.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.sourceCustomCompanies">Source Custom Companies</Translate>
          </dt>
          <dd>{draftEntity.sourceCustomCompanies ? draftEntity.sourceCustomCompanies.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.services">Services</Translate>
          </dt>
          <dd>
            {draftEntity.services
              ? draftEntity.services.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {draftEntity.services && i === draftEntity.services.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.products">Products</Translate>
          </dt>
          <dd>
            {draftEntity.products
              ? draftEntity.products.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {draftEntity.products && i === draftEntity.products.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.sanctionSerials">Sanction Serials</Translate>
          </dt>
          <dd>
            {draftEntity.sanctionSerials
              ? draftEntity.sanctionSerials.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {draftEntity.sanctionSerials && i === draftEntity.sanctionSerials.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.customerNumbers">Customer Numbers</Translate>
          </dt>
          <dd>
            {draftEntity.customerNumbers
              ? draftEntity.customerNumbers.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {draftEntity.customerNumbers && i === draftEntity.customerNumbers.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.suggestedSanctions">Suggested Sanctions</Translate>
          </dt>
          <dd>
            {draftEntity.suggestedSanctions
              ? draftEntity.suggestedSanctions.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {draftEntity.suggestedSanctions && i === draftEntity.suggestedSanctions.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draft.documentTransactionContainer">Document Transaction Container</Translate>
          </dt>
          <dd>
            {draftEntity.documentTransactionContainers
              ? draftEntity.documentTransactionContainers.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {draftEntity.documentTransactionContainers && i === draftEntity.documentTransactionContainers.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/draft" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft/${draftEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftDetail;
