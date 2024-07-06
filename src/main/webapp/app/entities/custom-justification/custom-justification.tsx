import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './custom-justification.reducer';

export const CustomJustification = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const customJustificationList = useAppSelector(state => state.customJustification.entities);
  const loading = useAppSelector(state => state.customJustification.loading);

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
      <h2 id="custom-justification-heading" data-cy="CustomJustificationHeading">
        <Translate contentKey="tfbitaApp.customJustification.home.title">Custom Justifications</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.customJustification.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/custom-justification/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.customJustification.home.createLabel">Create new Custom Justification</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {customJustificationList && customJustificationList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.customJustification.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('agentId')}>
                  <Translate contentKey="tfbitaApp.customJustification.agentId">Agent Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('agentId')} />
                </th>
                <th className="hand" onClick={sort('agriculturalPublicPolicies')}>
                  <Translate contentKey="tfbitaApp.customJustification.agriculturalPublicPolicies">Agricultural Public Policies</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('agriculturalPublicPolicies')} />
                </th>
                <th className="hand" onClick={sort('assessmentPlace')}>
                  <Translate contentKey="tfbitaApp.customJustification.assessmentPlace">Assessment Place</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('assessmentPlace')} />
                </th>
                <th className="hand" onClick={sort('attachedDocuments')}>
                  <Translate contentKey="tfbitaApp.customJustification.attachedDocuments">Attached Documents</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('attachedDocuments')} />
                </th>
                <th className="hand" onClick={sort('balanceRate')}>
                  <Translate contentKey="tfbitaApp.customJustification.balanceRate">Balance Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('balanceRate')} />
                </th>
                <th className="hand" onClick={sort('bankCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.bankCode">Bank Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bankCode')} />
                </th>
                <th className="hand" onClick={sort('borderTransportType')}>
                  <Translate contentKey="tfbitaApp.customJustification.borderTransportType">Border Transport Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('borderTransportType')} />
                </th>
                <th className="hand" onClick={sort('boxCountType')}>
                  <Translate contentKey="tfbitaApp.customJustification.boxCountType">Box Count Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('boxCountType')} />
                </th>
                <th className="hand" onClick={sort('boxMarks')}>
                  <Translate contentKey="tfbitaApp.customJustification.boxMarks">Box Marks</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('boxMarks')} />
                </th>
                <th className="hand" onClick={sort('cargoIndexNumber')}>
                  <Translate contentKey="tfbitaApp.customJustification.cargoIndexNumber">Cargo Index Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cargoIndexNumber')} />
                </th>
                <th className="hand" onClick={sort('centralBankCreditCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.centralBankCreditCode">Central Bank Credit Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('centralBankCreditCode')} />
                </th>
                <th className="hand" onClick={sort('comments')}>
                  <Translate contentKey="tfbitaApp.customJustification.comments">Comments</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('comments')} />
                </th>
                <th className="hand" onClick={sort('constructorCountryCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.constructorCountryCode">Constructor Country Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('constructorCountryCode')} />
                </th>
                <th className="hand" onClick={sort('costDetails')}>
                  <Translate contentKey="tfbitaApp.customJustification.costDetails">Cost Details</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('costDetails')} />
                </th>
                <th className="hand" onClick={sort('cottageNumber')}>
                  <Translate contentKey="tfbitaApp.customJustification.cottageNumber">Cottage Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('cottageNumber')} />
                </th>
                <th className="hand" onClick={sort('creditEquivalentAmount')}>
                  <Translate contentKey="tfbitaApp.customJustification.creditEquivalentAmount">Credit Equivalent Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('creditEquivalentAmount')} />
                </th>
                <th className="hand" onClick={sort('currency')}>
                  <Translate contentKey="tfbitaApp.customJustification.currency">Currency</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currency')} />
                </th>
                <th className="hand" onClick={sort('currencyAmount')}>
                  <Translate contentKey="tfbitaApp.customJustification.currencyAmount">Currency Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencyAmount')} />
                </th>
                <th className="hand" onClick={sort('currencyRate')}>
                  <Translate contentKey="tfbitaApp.customJustification.currencyRate">Currency Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencyRate')} />
                </th>
                <th className="hand" onClick={sort('currencySwiftCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.currencySwiftCode">Currency Swift Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencySwiftCode')} />
                </th>
                <th className="hand" onClick={sort('customCompanyCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.customCompanyCode">Custom Company Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('customCompanyCode')} />
                </th>
                <th className="hand" onClick={sort('customerId')}>
                  <Translate contentKey="tfbitaApp.customJustification.customerId">Customer Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('customerId')} />
                </th>
                <th className="hand" onClick={sort('date')}>
                  <Translate contentKey="tfbitaApp.customJustification.date">Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('date')} />
                </th>
                <th className="hand" onClick={sort('destCountryCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.destCountryCode">Dest Country Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('destCountryCode')} />
                </th>
                <th className="hand" onClick={sort('destCustom')}>
                  <Translate contentKey="tfbitaApp.customJustification.destCustom">Dest Custom</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('destCustom')} />
                </th>
                <th className="hand" onClick={sort('destCustomCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.destCustomCode">Dest Custom Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('destCustomCode')} />
                </th>
                <th className="hand" onClick={sort('disciplinaryDocumentsIssued')}>
                  <Translate contentKey="tfbitaApp.customJustification.disciplinaryDocumentsIssued">
                    Disciplinary Documents Issued
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('disciplinaryDocumentsIssued')} />
                </th>
                <th className="hand" onClick={sort('discountPercent')}>
                  <Translate contentKey="tfbitaApp.customJustification.discountPercent">Discount Percent</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('discountPercent')} />
                </th>
                <th className="hand" onClick={sort('eightDigitOrderCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.eightDigitOrderCode">Eight Digit Order Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('eightDigitOrderCode')} />
                </th>
                <th className="hand" onClick={sort('entranceCustomCompany')}>
                  <Translate contentKey="tfbitaApp.customJustification.entranceCustomCompany">Entrance Custom Company</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('entranceCustomCompany')} />
                </th>
                <th className="hand" onClick={sort('entranceCustomCompanyId')}>
                  <Translate contentKey="tfbitaApp.customJustification.entranceCustomCompanyId">Entrance Custom Company Id</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('entranceCustomCompanyId')} />
                </th>
                <th className="hand" onClick={sort('evacuationPlace')}>
                  <Translate contentKey="tfbitaApp.customJustification.evacuationPlace">Evacuation Place</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('evacuationPlace')} />
                </th>
                <th className="hand" onClick={sort('evaluationMethodCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.evaluationMethodCode">Evaluation Method Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('evaluationMethodCode')} />
                </th>
                <th className="hand" onClick={sort('exportDate')}>
                  <Translate contentKey="tfbitaApp.customJustification.exportDate">Export Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('exportDate')} />
                </th>
                <th className="hand" onClick={sort('exporter')}>
                  <Translate contentKey="tfbitaApp.customJustification.exporter">Exporter</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('exporter')} />
                </th>
                <th className="hand" onClick={sort('exporterCountryCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.exporterCountryCode">Exporter Country Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('exporterCountryCode')} />
                </th>
                <th className="hand" onClick={sort('extensionDate')}>
                  <Translate contentKey="tfbitaApp.customJustification.extensionDate">Extension Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('extensionDate')} />
                </th>
                <th className="hand" onClick={sort('factorTotalAmount')}>
                  <Translate contentKey="tfbitaApp.customJustification.factorTotalAmount">Factor Total Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('factorTotalAmount')} />
                </th>
                <th className="hand" onClick={sort('freightIndexNumber')}>
                  <Translate contentKey="tfbitaApp.customJustification.freightIndexNumber">Freight Index Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('freightIndexNumber')} />
                </th>
                <th className="hand" onClick={sort('frightLetter')}>
                  <Translate contentKey="tfbitaApp.customJustification.frightLetter">Fright Letter</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('frightLetter')} />
                </th>
                <th className="hand" onClick={sort('importLicence')}>
                  <Translate contentKey="tfbitaApp.customJustification.importLicence">Import Licence</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('importLicence')} />
                </th>
                <th className="hand" onClick={sort('importLicense')}>
                  <Translate contentKey="tfbitaApp.customJustification.importLicense">Import License</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('importLicense')} />
                </th>
                <th className="hand" onClick={sort('impureWeight')}>
                  <Translate contentKey="tfbitaApp.customJustification.impureWeight">Impure Weight</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('impureWeight')} />
                </th>
                <th className="hand" onClick={sort('indices')}>
                  <Translate contentKey="tfbitaApp.customJustification.indices">Indices</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('indices')} />
                </th>
                <th className="hand" onClick={sort('internalTransportType')}>
                  <Translate contentKey="tfbitaApp.customJustification.internalTransportType">Internal Transport Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('internalTransportType')} />
                </th>
                <th className="hand" onClick={sort('issuanceDate')}>
                  <Translate contentKey="tfbitaApp.customJustification.issuanceDate">Issuance Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('issuanceDate')} />
                </th>
                <th className="hand" onClick={sort('itemNumber')}>
                  <Translate contentKey="tfbitaApp.customJustification.itemNumber">Item Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('itemNumber')} />
                </th>
                <th className="hand" onClick={sort('items')}>
                  <Translate contentKey="tfbitaApp.customJustification.items">Items</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('items')} />
                </th>
                <th className="hand" onClick={sort('justificationAgent')}>
                  <Translate contentKey="tfbitaApp.customJustification.justificationAgent">Justification Agent</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('justificationAgent')} />
                </th>
                <th className="hand" onClick={sort('justificationCurrencyRate')}>
                  <Translate contentKey="tfbitaApp.customJustification.justificationCurrencyRate">Justification Currency Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('justificationCurrencyRate')} />
                </th>
                <th className="hand" onClick={sort('licenceNumber')}>
                  <Translate contentKey="tfbitaApp.customJustification.licenceNumber">Licence Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('licenceNumber')} />
                </th>
                <th className="hand" onClick={sort('makeCertificateNumber')}>
                  <Translate contentKey="tfbitaApp.customJustification.makeCertificateNumber">Make Certificate Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('makeCertificateNumber')} />
                </th>
                <th className="hand" onClick={sort('newBorderTransportType')}>
                  <Translate contentKey="tfbitaApp.customJustification.newBorderTransportType">New Border Transport Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('newBorderTransportType')} />
                </th>
                <th className="hand" onClick={sort('newEvacuationPlace')}>
                  <Translate contentKey="tfbitaApp.customJustification.newEvacuationPlace">New Evacuation Place</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('newEvacuationPlace')} />
                </th>
                <th className="hand" onClick={sort('newInternalTransportType')}>
                  <Translate contentKey="tfbitaApp.customJustification.newInternalTransportType">New Internal Transport Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('newInternalTransportType')} />
                </th>
                <th className="hand" onClick={sort('newProductItemCustomValue')}>
                  <Translate contentKey="tfbitaApp.customJustification.newProductItemCustomValue">New Product Item Custom Value</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('newProductItemCustomValue')} />
                </th>
                <th className="hand" onClick={sort('orderRegistrationDate')}>
                  <Translate contentKey="tfbitaApp.customJustification.orderRegistrationDate">Order Registration Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderRegistrationDate')} />
                </th>
                <th className="hand" onClick={sort('orderRegistrationNumber')}>
                  <Translate contentKey="tfbitaApp.customJustification.orderRegistrationNumber">Order Registration Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderRegistrationNumber')} />
                </th>
                <th className="hand" onClick={sort('papers')}>
                  <Translate contentKey="tfbitaApp.customJustification.papers">Papers</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('papers')} />
                </th>
                <th className="hand" onClick={sort('paymentConditions')}>
                  <Translate contentKey="tfbitaApp.customJustification.paymentConditions">Payment Conditions</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('paymentConditions')} />
                </th>
                <th className="hand" onClick={sort('preferences')}>
                  <Translate contentKey="tfbitaApp.customJustification.preferences">Preferences</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('preferences')} />
                </th>
                <th className="hand" onClick={sort('procedure')}>
                  <Translate contentKey="tfbitaApp.customJustification.procedure">Procedure</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('procedure')} />
                </th>
                <th className="hand" onClick={sort('productBoxCount')}>
                  <Translate contentKey="tfbitaApp.customJustification.productBoxCount">Product Box Count</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productBoxCount')} />
                </th>
                <th className="hand" onClick={sort('productCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.productCode">Product Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productCode')} />
                </th>
                <th className="hand" onClick={sort('productItemCost')}>
                  <Translate contentKey="tfbitaApp.customJustification.productItemCost">Product Item Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productItemCost')} />
                </th>
                <th className="hand" onClick={sort('productItemCount')}>
                  <Translate contentKey="tfbitaApp.customJustification.productItemCount">Product Item Count</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productItemCount')} />
                </th>
                <th className="hand" onClick={sort('productItemCustomValue')}>
                  <Translate contentKey="tfbitaApp.customJustification.productItemCustomValue">Product Item Custom Value</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productItemCustomValue')} />
                </th>
                <th className="hand" onClick={sort('productItemValue')}>
                  <Translate contentKey="tfbitaApp.customJustification.productItemValue">Product Item Value</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productItemValue')} />
                </th>
                <th className="hand" onClick={sort('productMeasure')}>
                  <Translate contentKey="tfbitaApp.customJustification.productMeasure">Product Measure</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productMeasure')} />
                </th>
                <th className="hand" onClick={sort('productOwner')}>
                  <Translate contentKey="tfbitaApp.customJustification.productOwner">Product Owner</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productOwner')} />
                </th>
                <th className="hand" onClick={sort('productReleaseDate')}>
                  <Translate contentKey="tfbitaApp.customJustification.productReleaseDate">Product Release Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productReleaseDate')} />
                </th>
                <th className="hand" onClick={sort('productType')}>
                  <Translate contentKey="tfbitaApp.customJustification.productType">Product Type</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productType')} />
                </th>
                <th className="hand" onClick={sort('productWorth')}>
                  <Translate contentKey="tfbitaApp.customJustification.productWorth">Product Worth</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('productWorth')} />
                </th>
                <th className="hand" onClick={sort('profitRate')}>
                  <Translate contentKey="tfbitaApp.customJustification.profitRate">Profit Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('profitRate')} />
                </th>
                <th className="hand" onClick={sort('pureWeight')}>
                  <Translate contentKey="tfbitaApp.customJustification.pureWeight">Pure Weight</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('pureWeight')} />
                </th>
                <th className="hand" onClick={sort('quota')}>
                  <Translate contentKey="tfbitaApp.customJustification.quota">Quota</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('quota')} />
                </th>
                <th className="hand" onClick={sort('receiver')}>
                  <Translate contentKey="tfbitaApp.customJustification.receiver">Receiver</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('receiver')} />
                </th>
                <th className="hand" onClick={sort('referenceNumber')}>
                  <Translate contentKey="tfbitaApp.customJustification.referenceNumber">Reference Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('referenceNumber')} />
                </th>
                <th className="hand" onClick={sort('registrationNumber')}>
                  <Translate contentKey="tfbitaApp.customJustification.registrationNumber">Registration Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('registrationNumber')} />
                </th>
                <th className="hand" onClick={sort('reverseOfJustificationDisciplinaryDocumentNumber')}>
                  <Translate contentKey="tfbitaApp.customJustification.reverseOfJustificationDisciplinaryDocumentNumber">
                    Reverse Of Justification Disciplinary Document Number
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('reverseOfJustificationDisciplinaryDocumentNumber')} />
                </th>
                <th className="hand" onClick={sort('reverseOfJustificationDocumentsIssued')}>
                  <Translate contentKey="tfbitaApp.customJustification.reverseOfJustificationDocumentsIssued">
                    Reverse Of Justification Documents Issued
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('reverseOfJustificationDocumentsIssued')} />
                </th>
                <th className="hand" onClick={sort('rightsRate')}>
                  <Translate contentKey="tfbitaApp.customJustification.rightsRate">Rights Rate</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('rightsRate')} />
                </th>
                <th className="hand" onClick={sort('tradingCountryCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.tradingCountryCode">Trading Country Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('tradingCountryCode')} />
                </th>
                <th className="hand" onClick={sort('transactionTypeCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.transactionTypeCode">Transaction Type Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('transactionTypeCode')} />
                </th>
                <th className="hand" onClick={sort('warehouseFactorNumber')}>
                  <Translate contentKey="tfbitaApp.customJustification.warehouseFactorNumber">Warehouse Factor Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('warehouseFactorNumber')} />
                </th>
                <th className="hand" onClick={sort('constructorCountryName')}>
                  <Translate contentKey="tfbitaApp.customJustification.constructorCountryName">Constructor Country Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('constructorCountryName')} />
                </th>
                <th className="hand" onClick={sort('lastCountryName')}>
                  <Translate contentKey="tfbitaApp.customJustification.lastCountryName">Last Country Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('lastCountryName')} />
                </th>
                <th className="hand" onClick={sort('branchCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.branchCode">Branch Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('branchCode')} />
                </th>
                <th className="hand" onClick={sort('justificationCurrencyCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.justificationCurrencyCode">Justification Currency Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('justificationCurrencyCode')} />
                </th>
                <th className="hand" onClick={sort('creditCurrencyCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.creditCurrencyCode">Credit Currency Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('creditCurrencyCode')} />
                </th>
                <th className="hand" onClick={sort('isMigrational')}>
                  <Translate contentKey="tfbitaApp.customJustification.isMigrational">Is Migrational</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isMigrational')} />
                </th>
                <th className="hand" onClick={sort('originalLetterImage')}>
                  <Translate contentKey="tfbitaApp.customJustification.originalLetterImage">Original Letter Image</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('originalLetterImage')} />
                </th>
                <th className="hand" onClick={sort('letterImage')}>
                  <Translate contentKey="tfbitaApp.customJustification.letterImage">Letter Image</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('letterImage')} />
                </th>
                <th className="hand" onClick={sort('sourceCountryCode')}>
                  <Translate contentKey="tfbitaApp.customJustification.sourceCountryCode">Source Country Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sourceCountryCode')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.customJustification.vehicleEnterNationality">Vehicle Enter Nationality</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.customJustification.container">Container</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.customJustification.vehicleCrossNationality">Vehicle Cross Nationality</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.customJustification.exportCustom">Export Custom</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.customJustification.entranceCustom">Entrance Custom</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.customJustification.transportConditions">Transport Conditions</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.customJustification.tradeTypeCode">Trade Type Code</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.customJustification.newPaymentConditions">New Payment Conditions</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.customJustification.justificationDeductionAmount">
                    Justification Deduction Amount
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.customJustification.products">Products</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.customJustification.reverseOfJustificationDocumentTransactions">
                    Reverse Of Justification Document Transactions
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {customJustificationList.map((customJustification, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/custom-justification/${customJustification.id}`} color="link" size="sm">
                      {customJustification.id}
                    </Button>
                  </td>
                  <td>{customJustification.agentId}</td>
                  <td>{customJustification.agriculturalPublicPolicies}</td>
                  <td>{customJustification.assessmentPlace}</td>
                  <td>{customJustification.attachedDocuments}</td>
                  <td>{customJustification.balanceRate}</td>
                  <td>{customJustification.bankCode}</td>
                  <td>{customJustification.borderTransportType}</td>
                  <td>{customJustification.boxCountType}</td>
                  <td>{customJustification.boxMarks}</td>
                  <td>{customJustification.cargoIndexNumber}</td>
                  <td>{customJustification.centralBankCreditCode}</td>
                  <td>{customJustification.comments}</td>
                  <td>{customJustification.constructorCountryCode}</td>
                  <td>{customJustification.costDetails}</td>
                  <td>{customJustification.cottageNumber}</td>
                  <td>{customJustification.creditEquivalentAmount}</td>
                  <td>{customJustification.currency}</td>
                  <td>{customJustification.currencyAmount}</td>
                  <td>{customJustification.currencyRate}</td>
                  <td>{customJustification.currencySwiftCode}</td>
                  <td>{customJustification.customCompanyCode}</td>
                  <td>{customJustification.customerId}</td>
                  <td>{customJustification.date}</td>
                  <td>{customJustification.destCountryCode}</td>
                  <td>{customJustification.destCustom}</td>
                  <td>{customJustification.destCustomCode}</td>
                  <td>{customJustification.disciplinaryDocumentsIssued ? 'true' : 'false'}</td>
                  <td>{customJustification.discountPercent}</td>
                  <td>{customJustification.eightDigitOrderCode}</td>
                  <td>{customJustification.entranceCustomCompany}</td>
                  <td>{customJustification.entranceCustomCompanyId}</td>
                  <td>{customJustification.evacuationPlace}</td>
                  <td>{customJustification.evaluationMethodCode}</td>
                  <td>{customJustification.exportDate}</td>
                  <td>{customJustification.exporter}</td>
                  <td>{customJustification.exporterCountryCode}</td>
                  <td>{customJustification.extensionDate}</td>
                  <td>{customJustification.factorTotalAmount}</td>
                  <td>{customJustification.freightIndexNumber}</td>
                  <td>{customJustification.frightLetter}</td>
                  <td>{customJustification.importLicence}</td>
                  <td>{customJustification.importLicense}</td>
                  <td>{customJustification.impureWeight}</td>
                  <td>{customJustification.indices}</td>
                  <td>{customJustification.internalTransportType}</td>
                  <td>{customJustification.issuanceDate}</td>
                  <td>{customJustification.itemNumber}</td>
                  <td>{customJustification.items}</td>
                  <td>{customJustification.justificationAgent}</td>
                  <td>{customJustification.justificationCurrencyRate}</td>
                  <td>{customJustification.licenceNumber}</td>
                  <td>{customJustification.makeCertificateNumber}</td>
                  <td>{customJustification.newBorderTransportType}</td>
                  <td>{customJustification.newEvacuationPlace}</td>
                  <td>{customJustification.newInternalTransportType}</td>
                  <td>{customJustification.newProductItemCustomValue}</td>
                  <td>{customJustification.orderRegistrationDate}</td>
                  <td>{customJustification.orderRegistrationNumber}</td>
                  <td>{customJustification.papers}</td>
                  <td>{customJustification.paymentConditions}</td>
                  <td>{customJustification.preferences}</td>
                  <td>{customJustification.procedure}</td>
                  <td>{customJustification.productBoxCount}</td>
                  <td>{customJustification.productCode}</td>
                  <td>{customJustification.productItemCost}</td>
                  <td>{customJustification.productItemCount}</td>
                  <td>{customJustification.productItemCustomValue}</td>
                  <td>{customJustification.productItemValue}</td>
                  <td>{customJustification.productMeasure}</td>
                  <td>{customJustification.productOwner}</td>
                  <td>{customJustification.productReleaseDate}</td>
                  <td>{customJustification.productType}</td>
                  <td>{customJustification.productWorth}</td>
                  <td>{customJustification.profitRate}</td>
                  <td>{customJustification.pureWeight}</td>
                  <td>{customJustification.quota}</td>
                  <td>{customJustification.receiver}</td>
                  <td>{customJustification.referenceNumber}</td>
                  <td>{customJustification.registrationNumber}</td>
                  <td>{customJustification.reverseOfJustificationDisciplinaryDocumentNumber}</td>
                  <td>{customJustification.reverseOfJustificationDocumentsIssued ? 'true' : 'false'}</td>
                  <td>{customJustification.rightsRate}</td>
                  <td>{customJustification.tradingCountryCode}</td>
                  <td>{customJustification.transactionTypeCode}</td>
                  <td>{customJustification.warehouseFactorNumber}</td>
                  <td>{customJustification.constructorCountryName}</td>
                  <td>{customJustification.lastCountryName}</td>
                  <td>{customJustification.branchCode}</td>
                  <td>{customJustification.justificationCurrencyCode}</td>
                  <td>{customJustification.creditCurrencyCode}</td>
                  <td>{customJustification.isMigrational ? 'true' : 'false'}</td>
                  <td>
                    {customJustification.originalLetterImage ? (
                      <div>
                        {customJustification.originalLetterImageContentType ? (
                          <a
                            onClick={openFile(customJustification.originalLetterImageContentType, customJustification.originalLetterImage)}
                          >
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {customJustification.originalLetterImageContentType}, {byteSize(customJustification.originalLetterImage)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {customJustification.letterImage ? (
                      <div>
                        {customJustification.letterImageContentType ? (
                          <a onClick={openFile(customJustification.letterImageContentType, customJustification.letterImage)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {customJustification.letterImageContentType}, {byteSize(customJustification.letterImage)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{customJustification.sourceCountryCode}</td>
                  <td>
                    {customJustification.vehicleEnterNationality ? (
                      <Link to={`/category-element/${customJustification.vehicleEnterNationality.id}`}>
                        {customJustification.vehicleEnterNationality.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {customJustification.container ? (
                      <Link to={`/category-element/${customJustification.container.id}`}>{customJustification.container.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {customJustification.vehicleCrossNationality ? (
                      <Link to={`/category-element/${customJustification.vehicleCrossNationality.id}`}>
                        {customJustification.vehicleCrossNationality.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {customJustification.exportCustom ? (
                      <Link to={`/custom/${customJustification.exportCustom.id}`}>{customJustification.exportCustom.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {customJustification.entranceCustom ? (
                      <Link to={`/custom/${customJustification.entranceCustom.id}`}>{customJustification.entranceCustom.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {customJustification.transportConditions ? (
                      <Link to={`/transportation-type/${customJustification.transportConditions.id}`}>
                        {customJustification.transportConditions.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {customJustification.tradeTypeCode ? (
                      <Link to={`/trade-type-code/${customJustification.tradeTypeCode.id}`}>{customJustification.tradeTypeCode.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {customJustification.newPaymentConditions ? (
                      <Link to={`/payment-condition/${customJustification.newPaymentConditions.id}`}>
                        {customJustification.newPaymentConditions.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {customJustification.justificationDeductionAmount ? (
                      <Link to={`/justification-deduction-amount/${customJustification.justificationDeductionAmount.id}`}>
                        {customJustification.justificationDeductionAmount.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {customJustification.products
                      ? customJustification.products.map((val, j) => (
                          <span key={j}>
                            <Link to={`/product/${val.id}`}>{val.id}</Link>
                            {j === customJustification.products.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {customJustification.reverseOfJustificationDocumentTransactions
                      ? customJustification.reverseOfJustificationDocumentTransactions.map((val, j) => (
                          <span key={j}>
                            <Link to={`/document-transaction/${val.id}`}>{val.id}</Link>
                            {j === customJustification.reverseOfJustificationDocumentTransactions.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/custom-justification/${customJustification.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/custom-justification/${customJustification.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        onClick={() => (window.location.href = `/custom-justification/${customJustification.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.customJustification.home.notFound">No Custom Justifications found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CustomJustification;
