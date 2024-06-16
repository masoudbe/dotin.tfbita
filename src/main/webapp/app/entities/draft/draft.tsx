import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './draft.reducer';

export const Draft = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const draftList = useAppSelector(state => state.draft.entities);
  const loading = useAppSelector(state => state.draft.loading);

  const getAllEntities = () => {
    dispatch(
      getEntities({
        sort: `${sortState.sort},${sortState.order}`,
      }),
    );
  };

  const sortEntities = () => {
    getAllEntities();
    const endURL = `?sort=${sortState.sort},${sortState.order}`;
    if (pageLocation.search !== endURL) {
      navigate(`${pageLocation.pathname}${endURL}`);
    }
  };

  useEffect(() => {
    sortEntities();
  }, [sortState.order, sortState.sort]);

  const sort = p => () => {
    setSortState({
      ...sortState,
      order: sortState.order === ASC ? DESC : ASC,
      sort: p,
    });
  };

  const handleSyncList = () => {
    sortEntities();
  };

  const getSortIconByFieldName = (fieldName: string) => {
    const sortFieldName = sortState.sort;
    const order = sortState.order;
    if (sortFieldName !== fieldName) {
      return faSort;
    } else {
      return order === ASC ? faSortUp : faSortDown;
    }
  };

  return (
    <div>
      <h2 id="draft-heading" data-cy="DraftHeading">
        <Translate contentKey="tfbitaApp.draft.home.title">Drafts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.draft.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/draft/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.draft.home.createLabel">Create new Draft</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {draftList && draftList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.draft.id">ID</Translate> <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('advisorDepositNumber')}>
                  <Translate contentKey="tfbitaApp.draft.advisorDepositNumber">Advisor Deposit Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('advisorDepositNumber')} />
                </th>
                <th className="hand" onClick={sort('advisorRequestDeleted')}>
                  <Translate contentKey="tfbitaApp.draft.advisorRequestDeleted">Advisor Request Deleted</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('advisorRequestDeleted')} />
                </th>
                <th className="hand" onClick={sort('auditCost')}>
                  <Translate contentKey="tfbitaApp.draft.auditCost">Audit Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('auditCost')} />
                </th>
                <th className="hand" onClick={sort('beneficiaryInfo')}>
                  <Translate contentKey="tfbitaApp.draft.beneficiaryInfo">Beneficiary Info</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('beneficiaryInfo')} />
                </th>
                <th className="hand" onClick={sort('branchStampCost')}>
                  <Translate contentKey="tfbitaApp.draft.branchStampCost">Branch Stamp Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('branchStampCost')} />
                </th>
                <th className="hand" onClick={sort('centralBankCode')}>
                  <Translate contentKey="tfbitaApp.draft.centralBankCode">Central Bank Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('centralBankCode')} />
                </th>
                <th className="hand" onClick={sort('centralBankCodeReference')}>
                  <Translate contentKey="tfbitaApp.draft.centralBankCodeReference">Central Bank Code Reference</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('centralBankCodeReference')} />
                </th>
                <th className="hand" onClick={sort('chargedExchangeBrokerAmount')}>
                  <Translate contentKey="tfbitaApp.draft.chargedExchangeBrokerAmount">Charged Exchange Broker Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('chargedExchangeBrokerAmount')} />
                </th>
                <th className="hand" onClick={sort('charterAcceptable')}>
                  <Translate contentKey="tfbitaApp.draft.charterAcceptable">Charter Acceptable</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('charterAcceptable')} />
                </th>
                <th className="hand" onClick={sort('comment')}>
                  <Translate contentKey="tfbitaApp.draft.comment">Comment</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('comment')} />
                </th>
                <th className="hand" onClick={sort('completionDate')}>
                  <Translate contentKey="tfbitaApp.draft.completionDate">Completion Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('completionDate')} />
                </th>
                <th className="hand" onClick={sort('coveringBankDepositNumber')}>
                  <Translate contentKey="tfbitaApp.draft.coveringBankDepositNumber">Covering Bank Deposit Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('coveringBankDepositNumber')} />
                </th>
                <th className="hand" onClick={sort('currencyExchangeDepositNumber')}>
                  <Translate contentKey="tfbitaApp.draft.currencyExchangeDepositNumber">Currency Exchange Deposit Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencyExchangeDepositNumber')} />
                </th>
                <th className="hand" onClick={sort('customerDepositNumber')}>
                  <Translate contentKey="tfbitaApp.draft.customerDepositNumber">Customer Deposit Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('customerDepositNumber')} />
                </th>
                <th className="hand" onClick={sort('deliverDuration')}>
                  <Translate contentKey="tfbitaApp.draft.deliverDuration">Deliver Duration</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deliverDuration')} />
                </th>
                <th className="hand" onClick={sort('discount')}>
                  <Translate contentKey="tfbitaApp.draft.discount">Discount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('discount')} />
                </th>
                <th className="hand" onClick={sort('draftNumber')}>
                  <Translate contentKey="tfbitaApp.draft.draftNumber">Draft Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('draftNumber')} />
                </th>
                <th className="hand" onClick={sort('draftOrderRegProductWorth')}>
                  <Translate contentKey="tfbitaApp.draft.draftOrderRegProductWorth">Draft Order Reg Product Worth</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('draftOrderRegProductWorth')} />
                </th>
                <th className="hand" onClick={sort('draftOrderRegServiceWorth')}>
                  <Translate contentKey="tfbitaApp.draft.draftOrderRegServiceWorth">Draft Order Reg Service Worth</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('draftOrderRegServiceWorth')} />
                </th>
                <th className="hand" onClick={sort('draftOrderRegTotalWorth')}>
                  <Translate contentKey="tfbitaApp.draft.draftOrderRegTotalWorth">Draft Order Reg Total Worth</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('draftOrderRegTotalWorth')} />
                </th>
                <th className="hand" onClick={sort('draftOtherCost')}>
                  <Translate contentKey="tfbitaApp.draft.draftOtherCost">Draft Other Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('draftOtherCost')} />
                </th>
                <th className="hand" onClick={sort('hasTransportJustification')}>
                  <Translate contentKey="tfbitaApp.draft.hasTransportJustification">Has Transport Justification</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('hasTransportJustification')} />
                </th>
                <th className="hand" onClick={sort('insuranceCost')}>
                  <Translate contentKey="tfbitaApp.draft.insuranceCost">Insurance Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('insuranceCost')} />
                </th>
                <th className="hand" onClick={sort('insuranceDate')}>
                  <Translate contentKey="tfbitaApp.draft.insuranceDate">Insurance Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('insuranceDate')} />
                </th>
                <th className="hand" onClick={sort('insuranceExpiryDate')}>
                  <Translate contentKey="tfbitaApp.draft.insuranceExpiryDate">Insurance Expiry Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('insuranceExpiryDate')} />
                </th>
                <th className="hand" onClick={sort('insuranceNumber')}>
                  <Translate contentKey="tfbitaApp.draft.insuranceNumber">Insurance Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('insuranceNumber')} />
                </th>
                <th className="hand" onClick={sort('interfaceAdvisorDepositNumber')}>
                  <Translate contentKey="tfbitaApp.draft.interfaceAdvisorDepositNumber">Interface Advisor Deposit Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('interfaceAdvisorDepositNumber')} />
                </th>
                <th className="hand" onClick={sort('issueDate')}>
                  <Translate contentKey="tfbitaApp.draft.issueDate">Issue Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('issueDate')} />
                </th>
                <th className="hand" onClick={sort('issueDraftCommission')}>
                  <Translate contentKey="tfbitaApp.draft.issueDraftCommission">Issue Draft Commission</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('issueDraftCommission')} />
                </th>
                <th className="hand" onClick={sort('lastShipmentDate')}>
                  <Translate contentKey="tfbitaApp.draft.lastShipmentDate">Last Shipment Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lastShipmentDate')} />
                </th>
                <th className="hand" onClick={sort('mainCustomerNumber')}>
                  <Translate contentKey="tfbitaApp.draft.mainCustomerNumber">Main Customer Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mainCustomerNumber')} />
                </th>
                <th className="hand" onClick={sort('makeCertification')}>
                  <Translate contentKey="tfbitaApp.draft.makeCertification">Make Certification</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('makeCertification')} />
                </th>
                <th className="hand" onClick={sort('multipleTransportable')}>
                  <Translate contentKey="tfbitaApp.draft.multipleTransportable">Multiple Transportable</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('multipleTransportable')} />
                </th>
                <th className="hand" onClick={sort('orderRegistrationDate')}>
                  <Translate contentKey="tfbitaApp.draft.orderRegistrationDate">Order Registration Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderRegistrationDate')} />
                </th>
                <th className="hand" onClick={sort('orderRegistrationExpiryDate')}>
                  <Translate contentKey="tfbitaApp.draft.orderRegistrationExpiryDate">Order Registration Expiry Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderRegistrationExpiryDate')} />
                </th>
                <th className="hand" onClick={sort('orderRegistrationNumber')}>
                  <Translate contentKey="tfbitaApp.draft.orderRegistrationNumber">Order Registration Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderRegistrationNumber')} />
                </th>
                <th className="hand" onClick={sort('otherCost')}>
                  <Translate contentKey="tfbitaApp.draft.otherCost">Other Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('otherCost')} />
                </th>
                <th className="hand" onClick={sort('paymentAmount')}>
                  <Translate contentKey="tfbitaApp.draft.paymentAmount">Payment Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('paymentAmount')} />
                </th>
                <th className="hand" onClick={sort('performaDate')}>
                  <Translate contentKey="tfbitaApp.draft.performaDate">Performa Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('performaDate')} />
                </th>
                <th className="hand" onClick={sort('performaExpiryDate')}>
                  <Translate contentKey="tfbitaApp.draft.performaExpiryDate">Performa Expiry Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('performaExpiryDate')} />
                </th>
                <th className="hand" onClick={sort('performaNumber')}>
                  <Translate contentKey="tfbitaApp.draft.performaNumber">Performa Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('performaNumber')} />
                </th>
                <th className="hand" onClick={sort('postSwiftCost')}>
                  <Translate contentKey="tfbitaApp.draft.postSwiftCost">Post Swift Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('postSwiftCost')} />
                </th>
                <th className="hand" onClick={sort('productSourceChangeable')}>
                  <Translate contentKey="tfbitaApp.draft.productSourceChangeable">Product Source Changeable</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productSourceChangeable')} />
                </th>
                <th className="hand" onClick={sort('receiveAllCommission')}>
                  <Translate contentKey="tfbitaApp.draft.receiveAllCommission">Receive All Commission</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('receiveAllCommission')} />
                </th>
                <th className="hand" onClick={sort('registerationJustificationAmount')}>
                  <Translate contentKey="tfbitaApp.draft.registerationJustificationAmount">Registeration Justification Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('registerationJustificationAmount')} />
                </th>
                <th className="hand" onClick={sort('requestDate')}>
                  <Translate contentKey="tfbitaApp.draft.requestDate">Request Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('requestDate')} />
                </th>
                <th className="hand" onClick={sort('sanctionSerial')}>
                  <Translate contentKey="tfbitaApp.draft.sanctionSerial">Sanction Serial</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sanctionSerial')} />
                </th>
                <th className="hand" onClick={sort('serial')}>
                  <Translate contentKey="tfbitaApp.draft.serial">Serial</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('serial')} />
                </th>
                <th className="hand" onClick={sort('shipmentCost')}>
                  <Translate contentKey="tfbitaApp.draft.shipmentCost">Shipment Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('shipmentCost')} />
                </th>
                <th className="hand" onClick={sort('sourceTransportPlace')}>
                  <Translate contentKey="tfbitaApp.draft.sourceTransportPlace">Source Transport Place</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sourceTransportPlace')} />
                </th>
                <th className="hand" onClick={sort('swiftComment')}>
                  <Translate contentKey="tfbitaApp.draft.swiftComment">Swift Comment</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('swiftComment')} />
                </th>
                <th className="hand" onClick={sort('transferAmount')}>
                  <Translate contentKey="tfbitaApp.draft.transferAmount">Transfer Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transferAmount')} />
                </th>
                <th className="hand" onClick={sort('transportVehicleChangeable')}>
                  <Translate contentKey="tfbitaApp.draft.transportVehicleChangeable">Transport Vehicle Changeable</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transportVehicleChangeable')} />
                </th>
                <th className="hand" onClick={sort('paymentTool')}>
                  <Translate contentKey="tfbitaApp.draft.paymentTool">Payment Tool</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('paymentTool')} />
                </th>
                <th className="hand" onClick={sort('letterNumberTazirat')}>
                  <Translate contentKey="tfbitaApp.draft.letterNumberTazirat">Letter Number Tazirat</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('letterNumberTazirat')} />
                </th>
                <th className="hand" onClick={sort('letterDateTazirat')}>
                  <Translate contentKey="tfbitaApp.draft.letterDateTazirat">Letter Date Tazirat</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('letterDateTazirat')} />
                </th>
                <th className="hand" onClick={sort('loanNumber')}>
                  <Translate contentKey="tfbitaApp.draft.loanNumber">Loan Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('loanNumber')} />
                </th>
                <th className="hand" onClick={sort('isMigrational')}>
                  <Translate contentKey="tfbitaApp.draft.isMigrational">Is Migrational</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isMigrational')} />
                </th>
                <th className="hand" onClick={sort('isNewCertificate')}>
                  <Translate contentKey="tfbitaApp.draft.isNewCertificate">Is New Certificate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isNewCertificate')} />
                </th>
                <th className="hand" onClick={sort('isWithoutPayment')}>
                  <Translate contentKey="tfbitaApp.draft.isWithoutPayment">Is Without Payment</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isWithoutPayment')} />
                </th>
                <th className="hand" onClick={sort('destinationCountryCode')}>
                  <Translate contentKey="tfbitaApp.draft.destinationCountryCode">Destination Country Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('destinationCountryCode')} />
                </th>
                <th className="hand" onClick={sort('beneficiaryCountryCode')}>
                  <Translate contentKey="tfbitaApp.draft.beneficiaryCountryCode">Beneficiary Country Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('beneficiaryCountryCode')} />
                </th>
                <th className="hand" onClick={sort('producerCountryCode')}>
                  <Translate contentKey="tfbitaApp.draft.producerCountryCode">Producer Country Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('producerCountryCode')} />
                </th>
                <th className="hand" onClick={sort('branchCode')}>
                  <Translate contentKey="tfbitaApp.draft.branchCode">Branch Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('branchCode')} />
                </th>
                <th className="hand" onClick={sort('operationalBranchCode')}>
                  <Translate contentKey="tfbitaApp.draft.operationalBranchCode">Operational Branch Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('operationalBranchCode')} />
                </th>
                <th className="hand" onClick={sort('certificateSenderBranchCode')}>
                  <Translate contentKey="tfbitaApp.draft.certificateSenderBranchCode">Certificate Sender Branch Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('certificateSenderBranchCode')} />
                </th>
                <th className="hand" onClick={sort('mainAccountCurrencyCode')}>
                  <Translate contentKey="tfbitaApp.draft.mainAccountCurrencyCode">Main Account Currency Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mainAccountCurrencyCode')} />
                </th>
                <th className="hand" onClick={sort('orderRegCurrencyCode')}>
                  <Translate contentKey="tfbitaApp.draft.orderRegCurrencyCode">Order Reg Currency Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderRegCurrencyCode')} />
                </th>
                <th className="hand" onClick={sort('chargedExchangeBrokerCurrency')}>
                  <Translate contentKey="tfbitaApp.draft.chargedExchangeBrokerCurrency">Charged Exchange Broker Currency</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('chargedExchangeBrokerCurrency')} />
                </th>
                <th className="hand" onClick={sort('registerationJustificationCurrencyCode')}>
                  <Translate contentKey="tfbitaApp.draft.registerationJustificationCurrencyCode">
                    Registeration Justification Currency Code
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('registerationJustificationCurrencyCode')} />
                </th>
                <th className="hand" onClick={sort('currencyExchangeInfoTitle')}>
                  <Translate contentKey="tfbitaApp.draft.currencyExchangeInfoTitle">Currency Exchange Info Title</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencyExchangeInfoTitle')} />
                </th>
                <th className="hand" onClick={sort('transportationTypeName')}>
                  <Translate contentKey="tfbitaApp.draft.transportationTypeName">Transportation Type Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transportationTypeName')} />
                </th>
                <th className="hand" onClick={sort('accountInfoCode')}>
                  <Translate contentKey="tfbitaApp.draft.accountInfoCode">Account Info Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('accountInfoCode')} />
                </th>
                <th className="hand" onClick={sort('customerNumbers')}>
                  <Translate contentKey="tfbitaApp.draft.customerNumbers">Customer Numbers</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('customerNumbers')} />
                </th>
                <th className="hand" onClick={sort('sanctionSerials')}>
                  <Translate contentKey="tfbitaApp.draft.sanctionSerials">Sanction Serials</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sanctionSerials')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draft.custom">Custom</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draft.products">Products</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.draft.services">Services</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {draftList.map((draft, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/draft/${draft.id}`} color="link" size="sm">
                      {draft.id}
                    </Button>
                  </td>
                  <td>{draft.advisorDepositNumber}</td>
                  <td>{draft.advisorRequestDeleted ? 'true' : 'false'}</td>
                  <td>{draft.auditCost}</td>
                  <td>{draft.beneficiaryInfo}</td>
                  <td>{draft.branchStampCost}</td>
                  <td>{draft.centralBankCode}</td>
                  <td>{draft.centralBankCodeReference}</td>
                  <td>{draft.chargedExchangeBrokerAmount}</td>
                  <td>{draft.charterAcceptable ? 'true' : 'false'}</td>
                  <td>{draft.comment}</td>
                  <td>{draft.completionDate}</td>
                  <td>{draft.coveringBankDepositNumber}</td>
                  <td>{draft.currencyExchangeDepositNumber}</td>
                  <td>{draft.customerDepositNumber}</td>
                  <td>{draft.deliverDuration}</td>
                  <td>{draft.discount}</td>
                  <td>{draft.draftNumber}</td>
                  <td>{draft.draftOrderRegProductWorth}</td>
                  <td>{draft.draftOrderRegServiceWorth}</td>
                  <td>{draft.draftOrderRegTotalWorth}</td>
                  <td>{draft.draftOtherCost}</td>
                  <td>{draft.hasTransportJustification ? 'true' : 'false'}</td>
                  <td>{draft.insuranceCost}</td>
                  <td>{draft.insuranceDate}</td>
                  <td>{draft.insuranceExpiryDate}</td>
                  <td>{draft.insuranceNumber}</td>
                  <td>{draft.interfaceAdvisorDepositNumber}</td>
                  <td>{draft.issueDate}</td>
                  <td>{draft.issueDraftCommission}</td>
                  <td>{draft.lastShipmentDate}</td>
                  <td>{draft.mainCustomerNumber}</td>
                  <td>{draft.makeCertification ? 'true' : 'false'}</td>
                  <td>{draft.multipleTransportable ? 'true' : 'false'}</td>
                  <td>{draft.orderRegistrationDate}</td>
                  <td>{draft.orderRegistrationExpiryDate}</td>
                  <td>{draft.orderRegistrationNumber}</td>
                  <td>{draft.otherCost}</td>
                  <td>{draft.paymentAmount}</td>
                  <td>{draft.performaDate}</td>
                  <td>{draft.performaExpiryDate}</td>
                  <td>{draft.performaNumber}</td>
                  <td>{draft.postSwiftCost}</td>
                  <td>{draft.productSourceChangeable ? 'true' : 'false'}</td>
                  <td>{draft.receiveAllCommission ? 'true' : 'false'}</td>
                  <td>{draft.registerationJustificationAmount}</td>
                  <td>{draft.requestDate}</td>
                  <td>{draft.sanctionSerial}</td>
                  <td>{draft.serial}</td>
                  <td>{draft.shipmentCost}</td>
                  <td>{draft.sourceTransportPlace}</td>
                  <td>{draft.swiftComment}</td>
                  <td>{draft.transferAmount}</td>
                  <td>{draft.transportVehicleChangeable ? 'true' : 'false'}</td>
                  <td>{draft.paymentTool}</td>
                  <td>{draft.letterNumberTazirat}</td>
                  <td>{draft.letterDateTazirat}</td>
                  <td>{draft.loanNumber}</td>
                  <td>{draft.isMigrational ? 'true' : 'false'}</td>
                  <td>{draft.isNewCertificate ? 'true' : 'false'}</td>
                  <td>{draft.isWithoutPayment ? 'true' : 'false'}</td>
                  <td>{draft.destinationCountryCode}</td>
                  <td>{draft.beneficiaryCountryCode}</td>
                  <td>{draft.producerCountryCode}</td>
                  <td>{draft.branchCode}</td>
                  <td>{draft.operationalBranchCode}</td>
                  <td>{draft.certificateSenderBranchCode}</td>
                  <td>{draft.mainAccountCurrencyCode}</td>
                  <td>{draft.orderRegCurrencyCode}</td>
                  <td>{draft.chargedExchangeBrokerCurrency}</td>
                  <td>{draft.registerationJustificationCurrencyCode}</td>
                  <td>{draft.currencyExchangeInfoTitle}</td>
                  <td>{draft.transportationTypeName}</td>
                  <td>{draft.accountInfoCode}</td>
                  <td>{draft.customerNumbers}</td>
                  <td>{draft.sanctionSerials}</td>
                  <td>
                    {draft.customs
                      ? draft.customs.map((val, j) => (
                          <span key={j}>
                            <Link to={`/custom/${val.id}`}>{val.id}</Link>
                            {j === draft.customs.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {draft.products
                      ? draft.products.map((val, j) => (
                          <span key={j}>
                            <Link to={`/product/${val.id}`}>{val.id}</Link>
                            {j === draft.products.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {draft.services
                      ? draft.services.map((val, j) => (
                          <span key={j}>
                            <Link to={`/service-tariff/${val.id}`}>{val.id}</Link>
                            {j === draft.services.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/draft/${draft.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/draft/${draft.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/draft/${draft.id}/delete`)}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="tfbitaApp.draft.home.notFound">No Drafts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Draft;
