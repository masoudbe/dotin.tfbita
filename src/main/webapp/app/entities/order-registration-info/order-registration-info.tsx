import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Table } from 'reactstrap';
import { openFile, byteSize, Translate, getSortState } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faSort, faSortUp, faSortDown } from '@fortawesome/free-solid-svg-icons';
import { ASC, DESC, SORT } from 'app/shared/util/pagination.constants';
import { overrideSortStateWithQueryParams } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntities } from './order-registration-info.reducer';

export const OrderRegistrationInfo = () => {
  const dispatch = useAppDispatch();

  const pageLocation = useLocation();
  const navigate = useNavigate();

  const [sortState, setSortState] = useState(overrideSortStateWithQueryParams(getSortState(pageLocation, 'id'), pageLocation.search));

  const orderRegistrationInfoList = useAppSelector(state => state.orderRegistrationInfo.entities);
  const loading = useAppSelector(state => state.orderRegistrationInfo.loading);

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
      <h2 id="order-registration-info-heading" data-cy="OrderRegistrationInfoHeading">
        <Translate contentKey="tfbitaApp.orderRegistrationInfo.home.title">Order Registration Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/order-registration-info/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.home.createLabel">Create new Order Registration Info</Translate>
          </Link>
        </div>
      </h2>
      <div className="table-responsive">
        {orderRegistrationInfoList && orderRegistrationInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th className="hand" onClick={sort('id')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.id">ID</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('id')} />
                </th>
                <th className="hand" onClick={sort('orderRegNum')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.orderRegNum">Order Reg Num</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('orderRegNum')} />
                </th>
                <th className="hand" onClick={sort('startOrderRegDate')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.startOrderRegDate">Start Order Reg Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('startOrderRegDate')} />
                </th>
                <th className="hand" onClick={sort('endOrderRegDate')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.endOrderRegDate">End Order Reg Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('endOrderRegDate')} />
                </th>
                <th className="hand" onClick={sort('requestNumber')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.requestNumber">Request Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('requestNumber')} />
                </th>
                <th className="hand" onClick={sort('bankCode')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.bankCode">Bank Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('bankCode')} />
                </th>
                <th className="hand" onClick={sort('branchCode')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.branchCode">Branch Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('branchCode')} />
                </th>
                <th className="hand" onClick={sort('customerNumber')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.customerNumber">Customer Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('customerNumber')} />
                </th>
                <th className="hand" onClick={sort('applicantNationalcode')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.applicantNationalcode">Applicant Nationalcode</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('applicantNationalcode')} />
                </th>
                <th className="hand" onClick={sort('performaNumber')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.performaNumber">Performa Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('performaNumber')} />
                </th>
                <th className="hand" onClick={sort('performaDate')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.performaDate">Performa Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('performaDate')} />
                </th>
                <th className="hand" onClick={sort('performaExpiryDate')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.performaExpiryDate">Performa Expiry Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('performaExpiryDate')} />
                </th>
                <th className="hand" onClick={sort('performaDatePersian')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.performaDatePersian">Performa Date Persian</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('performaDatePersian')} />
                </th>
                <th className="hand" onClick={sort('performaExpiryDatePersian')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.performaExpiryDatePersian">Performa Expiry Date Persian</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('performaExpiryDatePersian')} />
                </th>
                <th className="hand" onClick={sort('infoSubmissionDate')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.infoSubmissionDate">Info Submission Date</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('infoSubmissionDate')} />
                </th>
                <th className="hand" onClick={sort('sellerName')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.sellerName">Seller Name</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sellerName')} />
                </th>
                <th className="hand" onClick={sort('beneficiaryCountryCode')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.beneficiaryCountryCode">Beneficiary Country Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('beneficiaryCountryCode')} />
                </th>
                <th className="hand" onClick={sort('producerCountriesCode')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.producerCountriesCode">Producer Countries Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('producerCountriesCode')} />
                </th>
                <th className="hand" onClick={sort('sourceCountry')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.sourceCountry">Source Country</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sourceCountry')} />
                </th>
                <th className="hand" onClick={sort('multipleTransportable')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.multipleTransportable">Multiple Transportable</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('multipleTransportable')} />
                </th>
                <th className="hand" onClick={sort('deliveryTimeOfGoods')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.deliveryTimeOfGoods">Delivery Time Of Goods</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('deliveryTimeOfGoods')} />
                </th>
                <th className="hand" onClick={sort('totalWeightInKg')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.totalWeightInKg">Total Weight In Kg</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('totalWeightInKg')} />
                </th>
                <th className="hand" onClick={sort('possibilityCarrying')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.possibilityCarrying">Possibility Carrying</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('possibilityCarrying')} />
                </th>
                <th className="hand" onClick={sort('possibilityClearance')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.possibilityClearance">Possibility Clearance</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('possibilityClearance')} />
                </th>
                <th className="hand" onClick={sort('ableAddServiceInProductOrder')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.ableAddServiceInProductOrder">
                    Able Add Service In Product Order
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('ableAddServiceInProductOrder')} />
                </th>
                <th className="hand" onClick={sort('freeZone')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.freeZone">Free Zone</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('freeZone')} />
                </th>
                <th className="hand" onClick={sort('currencyCode')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.currencyCode">Currency Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('currencyCode')} />
                </th>
                <th className="hand" onClick={sort('fobAmount')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.fobAmount">Fob Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fobAmount')} />
                </th>
                <th className="hand" onClick={sort('discount')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.discount">Discount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('discount')} />
                </th>
                <th className="hand" onClick={sort('shipmentCost')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.shipmentCost">Shipment Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('shipmentCost')} />
                </th>
                <th className="hand" onClick={sort('othrCost')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.othrCost">Othr Cost</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('othrCost')} />
                </th>
                <th className="hand" onClick={sort('totalAmount')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.totalAmount">Total Amount</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('totalAmount')} />
                </th>
                <th className="hand" onClick={sort('isFile')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.isFile">Is File</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isFile')} />
                </th>
                <th className="hand" onClick={sort('fileNumber')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.fileNumber">File Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('fileNumber')} />
                </th>
                <th className="hand" onClick={sort('extended')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.extended">Extended</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('extended')} />
                </th>
                <th className="hand" onClick={sort('updated')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.updated">Updated</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('updated')} />
                </th>
                <th className="hand" onClick={sort('generateFromService')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.generateFromService">Generate From Service</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('generateFromService')} />
                </th>
                <th className="hand" onClick={sort('certificateNumber')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.certificateNumber">Certificate Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('certificateNumber')} />
                </th>
                <th className="hand" onClick={sort('centralBankCode')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.centralBankCode">Central Bank Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('centralBankCode')} />
                </th>
                <th className="hand" onClick={sort('isMigrational')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.isMigrational">Is Migrational</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('isMigrational')} />
                </th>
                <th className="hand" onClick={sort('externalCustomer')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.externalCustomer">External Customer</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('externalCustomer')} />
                </th>
                <th className="hand" onClick={sort('sumRedemptionDuration')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.sumRedemptionDuration">Sum Redemption Duration</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('sumRedemptionDuration')} />
                </th>
                <th className="hand" onClick={sort('extendedDayNumber')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.extendedDayNumber">Extended Day Number</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('extendedDayNumber')} />
                </th>
                <th className="hand" onClick={sort('mainGroupProductCode')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.mainGroupProductCode">Main Group Product Code</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('mainGroupProductCode')} />
                </th>
                <th className="hand" onClick={sort('producerCountries')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.producerCountries">Producer Countries</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('producerCountries')} />
                </th>
                <th className="hand" onClick={sort('excelFile')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.excelFile">Excel File</Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('excelFile')} />
                </th>
                <th className="hand" onClick={sort('commissionTransactionNumber')}>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.commissionTransactionNumber">
                    Commission Transaction Number
                  </Translate>{' '}
                  <FontAwesomeIcon icon={getSortIconByFieldName('commissionTransactionNumber')} />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.orderRegType">Order Reg Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.requestType">Request Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.importType">Import Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.operationType">Operation Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.currencyProvisionType">Currency Provision Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.paymentTool">Payment Tool</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.activityType">Activity Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.ownerType">Owner Type</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.status">Status</Translate> <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.externalCustomerType">External Customer Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.transportVehicleType">Transport Vehicle Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.transportType">Transport Type</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.destCoustomers">Dest Coustomers</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.cargoPlaceCustoms">Cargo Place Customs</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.entranceBorders">Entrance Borders</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.productInfo">Product Info</Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th>
                  <Translate contentKey="tfbitaApp.orderRegistrationInfo.commissionTransactionNumber">
                    Commission Transaction Number
                  </Translate>{' '}
                  <FontAwesomeIcon icon="sort" />
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {orderRegistrationInfoList.map((orderRegistrationInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/order-registration-info/${orderRegistrationInfo.id}`} color="link" size="sm">
                      {orderRegistrationInfo.id}
                    </Button>
                  </td>
                  <td>{orderRegistrationInfo.orderRegNum}</td>
                  <td>{orderRegistrationInfo.startOrderRegDate}</td>
                  <td>{orderRegistrationInfo.endOrderRegDate}</td>
                  <td>{orderRegistrationInfo.requestNumber}</td>
                  <td>{orderRegistrationInfo.bankCode}</td>
                  <td>{orderRegistrationInfo.branchCode}</td>
                  <td>{orderRegistrationInfo.customerNumber}</td>
                  <td>{orderRegistrationInfo.applicantNationalcode}</td>
                  <td>{orderRegistrationInfo.performaNumber}</td>
                  <td>{orderRegistrationInfo.performaDate}</td>
                  <td>{orderRegistrationInfo.performaExpiryDate}</td>
                  <td>{orderRegistrationInfo.performaDatePersian}</td>
                  <td>{orderRegistrationInfo.performaExpiryDatePersian}</td>
                  <td>{orderRegistrationInfo.infoSubmissionDate}</td>
                  <td>{orderRegistrationInfo.sellerName}</td>
                  <td>{orderRegistrationInfo.beneficiaryCountryCode}</td>
                  <td>{orderRegistrationInfo.producerCountriesCode}</td>
                  <td>{orderRegistrationInfo.sourceCountry}</td>
                  <td>{orderRegistrationInfo.multipleTransportable ? 'true' : 'false'}</td>
                  <td>{orderRegistrationInfo.deliveryTimeOfGoods}</td>
                  <td>{orderRegistrationInfo.totalWeightInKg}</td>
                  <td>{orderRegistrationInfo.possibilityCarrying ? 'true' : 'false'}</td>
                  <td>{orderRegistrationInfo.possibilityClearance ? 'true' : 'false'}</td>
                  <td>{orderRegistrationInfo.ableAddServiceInProductOrder ? 'true' : 'false'}</td>
                  <td>{orderRegistrationInfo.freeZone ? 'true' : 'false'}</td>
                  <td>{orderRegistrationInfo.currencyCode}</td>
                  <td>{orderRegistrationInfo.fobAmount}</td>
                  <td>{orderRegistrationInfo.discount}</td>
                  <td>{orderRegistrationInfo.shipmentCost}</td>
                  <td>{orderRegistrationInfo.othrCost}</td>
                  <td>{orderRegistrationInfo.totalAmount}</td>
                  <td>{orderRegistrationInfo.isFile ? 'true' : 'false'}</td>
                  <td>{orderRegistrationInfo.fileNumber}</td>
                  <td>{orderRegistrationInfo.extended ? 'true' : 'false'}</td>
                  <td>{orderRegistrationInfo.updated ? 'true' : 'false'}</td>
                  <td>{orderRegistrationInfo.generateFromService ? 'true' : 'false'}</td>
                  <td>{orderRegistrationInfo.certificateNumber}</td>
                  <td>{orderRegistrationInfo.centralBankCode}</td>
                  <td>{orderRegistrationInfo.isMigrational ? 'true' : 'false'}</td>
                  <td>{orderRegistrationInfo.externalCustomer}</td>
                  <td>{orderRegistrationInfo.sumRedemptionDuration}</td>
                  <td>{orderRegistrationInfo.extendedDayNumber}</td>
                  <td>{orderRegistrationInfo.mainGroupProductCode}</td>
                  <td>{orderRegistrationInfo.producerCountries}</td>
                  <td>
                    {orderRegistrationInfo.excelFile ? (
                      <div>
                        {orderRegistrationInfo.excelFileContentType ? (
                          <a onClick={openFile(orderRegistrationInfo.excelFileContentType, orderRegistrationInfo.excelFile)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {orderRegistrationInfo.excelFileContentType}, {byteSize(orderRegistrationInfo.excelFile)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{orderRegistrationInfo.commissionTransactionNumber}</td>
                  <td>
                    {orderRegistrationInfo.orderRegType ? (
                      <Link to={`/category-element/${orderRegistrationInfo.orderRegType.id}`}>{orderRegistrationInfo.orderRegType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.requestType ? (
                      <Link to={`/category-element/${orderRegistrationInfo.requestType.id}`}>{orderRegistrationInfo.requestType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.importType ? (
                      <Link to={`/category-element/${orderRegistrationInfo.importType.id}`}>{orderRegistrationInfo.importType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.operationType ? (
                      <Link to={`/category-element/${orderRegistrationInfo.operationType.id}`}>
                        {orderRegistrationInfo.operationType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.currencyProvisionType ? (
                      <Link to={`/category-element/${orderRegistrationInfo.currencyProvisionType.id}`}>
                        {orderRegistrationInfo.currencyProvisionType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.paymentTool ? (
                      <Link to={`/category-element/${orderRegistrationInfo.paymentTool.id}`}>{orderRegistrationInfo.paymentTool.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.activityType ? (
                      <Link to={`/category-element/${orderRegistrationInfo.activityType.id}`}>{orderRegistrationInfo.activityType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.ownerType ? (
                      <Link to={`/category-element/${orderRegistrationInfo.ownerType.id}`}>{orderRegistrationInfo.ownerType.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.status ? (
                      <Link to={`/category-element/${orderRegistrationInfo.status.id}`}>{orderRegistrationInfo.status.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.externalCustomerType ? (
                      <Link to={`/category-element/${orderRegistrationInfo.externalCustomerType.id}`}>
                        {orderRegistrationInfo.externalCustomerType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.transportVehicleType ? (
                      <Link to={`/category-element/${orderRegistrationInfo.transportVehicleType.id}`}>
                        {orderRegistrationInfo.transportVehicleType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.transportType ? (
                      <Link to={`/transportation-type/${orderRegistrationInfo.transportType.id}`}>
                        {orderRegistrationInfo.transportType.id}
                      </Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.destCoustomers ? (
                      <Link to={`/custom/${orderRegistrationInfo.destCoustomers.id}`}>{orderRegistrationInfo.destCoustomers.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.cargoPlaceCustoms ? (
                      <Link to={`/custom/${orderRegistrationInfo.cargoPlaceCustoms.id}`}>{orderRegistrationInfo.cargoPlaceCustoms.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.entranceBorders ? (
                      <Link to={`/custom/${orderRegistrationInfo.entranceBorders.id}`}>{orderRegistrationInfo.entranceBorders.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {orderRegistrationInfo.productInfos
                      ? orderRegistrationInfo.productInfos.map((val, j) => (
                          <span key={j}>
                            <Link to={`/product/${val.id}`}>{val.id}</Link>
                            {j === orderRegistrationInfo.productInfos.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td>
                    {orderRegistrationInfo.commissionTransactionNumbers
                      ? orderRegistrationInfo.commissionTransactionNumbers.map((val, j) => (
                          <span key={j}>
                            <Link to={`/string-value/${val.id}`}>{val.id}</Link>
                            {j === orderRegistrationInfo.commissionTransactionNumbers.length - 1 ? '' : ', '}
                          </span>
                        ))
                      : null}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/order-registration-info/${orderRegistrationInfo.id}`}
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
                        to={`/order-registration-info/${orderRegistrationInfo.id}/edit`}
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
                        onClick={() => (window.location.href = `/order-registration-info/${orderRegistrationInfo.id}/delete`)}
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
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.home.notFound">No Order Registration Infos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default OrderRegistrationInfo;
