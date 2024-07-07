import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './order-registration-info.reducer';

export const OrderRegistrationInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const orderRegistrationInfoEntity = useAppSelector(state => state.orderRegistrationInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderRegistrationInfoDetailsHeading">
          <Translate contentKey="tfbitaApp.orderRegistrationInfo.detail.title">OrderRegistrationInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.id}</dd>
          <dt>
            <span id="orderRegNum">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.orderRegNum">Order Reg Num</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.orderRegNum}</dd>
          <dt>
            <span id="startOrderRegDate">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.startOrderRegDate">Start Order Reg Date</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.startOrderRegDate}</dd>
          <dt>
            <span id="endOrderRegDate">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.endOrderRegDate">End Order Reg Date</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.endOrderRegDate}</dd>
          <dt>
            <span id="requestNumber">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.requestNumber">Request Number</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.requestNumber}</dd>
          <dt>
            <span id="bankCode">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.bankCode">Bank Code</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.bankCode}</dd>
          <dt>
            <span id="branchCode">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.branchCode">Branch Code</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.branchCode}</dd>
          <dt>
            <span id="customerNumber">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.customerNumber">Customer Number</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.customerNumber}</dd>
          <dt>
            <span id="applicantNationalcode">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.applicantNationalcode">Applicant Nationalcode</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.applicantNationalcode}</dd>
          <dt>
            <span id="performaNumber">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.performaNumber">Performa Number</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.performaNumber}</dd>
          <dt>
            <span id="performaDate">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.performaDate">Performa Date</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.performaDate}</dd>
          <dt>
            <span id="performaExpiryDate">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.performaExpiryDate">Performa Expiry Date</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.performaExpiryDate}</dd>
          <dt>
            <span id="performaDatePersian">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.performaDatePersian">Performa Date Persian</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.performaDatePersian}</dd>
          <dt>
            <span id="performaExpiryDatePersian">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.performaExpiryDatePersian">Performa Expiry Date Persian</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.performaExpiryDatePersian}</dd>
          <dt>
            <span id="infoSubmissionDate">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.infoSubmissionDate">Info Submission Date</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.infoSubmissionDate}</dd>
          <dt>
            <span id="sellerName">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.sellerName">Seller Name</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.sellerName}</dd>
          <dt>
            <span id="beneficiaryCountryCode">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.beneficiaryCountryCode">Beneficiary Country Code</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.beneficiaryCountryCode}</dd>
          <dt>
            <span id="producerCountriesCode">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.producerCountriesCode">Producer Countries Code</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.producerCountriesCode}</dd>
          <dt>
            <span id="sourceCountry">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.sourceCountry">Source Country</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.sourceCountry}</dd>
          <dt>
            <span id="multipleTransportable">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.multipleTransportable">Multiple Transportable</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.multipleTransportable ? 'true' : 'false'}</dd>
          <dt>
            <span id="deliveryTimeOfGoods">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.deliveryTimeOfGoods">Delivery Time Of Goods</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.deliveryTimeOfGoods}</dd>
          <dt>
            <span id="totalWeightInKg">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.totalWeightInKg">Total Weight In Kg</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.totalWeightInKg}</dd>
          <dt>
            <span id="possibilityCarrying">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.possibilityCarrying">Possibility Carrying</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.possibilityCarrying ? 'true' : 'false'}</dd>
          <dt>
            <span id="possibilityClearance">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.possibilityClearance">Possibility Clearance</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.possibilityClearance ? 'true' : 'false'}</dd>
          <dt>
            <span id="ableAddServiceInProductOrder">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.ableAddServiceInProductOrder">
                Able Add Service In Product Order
              </Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.ableAddServiceInProductOrder ? 'true' : 'false'}</dd>
          <dt>
            <span id="freeZone">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.freeZone">Free Zone</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.freeZone ? 'true' : 'false'}</dd>
          <dt>
            <span id="currencyCode">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.currencyCode">Currency Code</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.currencyCode}</dd>
          <dt>
            <span id="fobAmount">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.fobAmount">Fob Amount</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.fobAmount}</dd>
          <dt>
            <span id="discount">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.discount">Discount</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.discount}</dd>
          <dt>
            <span id="shipmentCost">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.shipmentCost">Shipment Cost</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.shipmentCost}</dd>
          <dt>
            <span id="othrCost">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.othrCost">Othr Cost</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.othrCost}</dd>
          <dt>
            <span id="totalAmount">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.totalAmount">Total Amount</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.totalAmount}</dd>
          <dt>
            <span id="isFile">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.isFile">Is File</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.isFile ? 'true' : 'false'}</dd>
          <dt>
            <span id="fileNumber">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.fileNumber">File Number</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.fileNumber}</dd>
          <dt>
            <span id="extended">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.extended">Extended</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.extended ? 'true' : 'false'}</dd>
          <dt>
            <span id="updated">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.updated">Updated</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.updated ? 'true' : 'false'}</dd>
          <dt>
            <span id="generateFromService">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.generateFromService">Generate From Service</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.generateFromService ? 'true' : 'false'}</dd>
          <dt>
            <span id="certificateNumber">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.certificateNumber">Certificate Number</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.certificateNumber}</dd>
          <dt>
            <span id="centralBankCode">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.centralBankCode">Central Bank Code</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.centralBankCode}</dd>
          <dt>
            <span id="isMigrational">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.isMigrational">Is Migrational</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.isMigrational ? 'true' : 'false'}</dd>
          <dt>
            <span id="externalCustomer">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.externalCustomer">External Customer</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.externalCustomer}</dd>
          <dt>
            <span id="sumRedemptionDuration">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.sumRedemptionDuration">Sum Redemption Duration</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.sumRedemptionDuration}</dd>
          <dt>
            <span id="extendedDayNumber">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.extendedDayNumber">Extended Day Number</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.extendedDayNumber}</dd>
          <dt>
            <span id="mainGroupProductCode">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.mainGroupProductCode">Main Group Product Code</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.mainGroupProductCode}</dd>
          <dt>
            <span id="producerCountries">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.producerCountries">Producer Countries</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.producerCountries}</dd>
          <dt>
            <span id="excelFile">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.excelFile">Excel File</Translate>
            </span>
          </dt>
          <dd>
            {orderRegistrationInfoEntity.excelFile ? (
              <div>
                {orderRegistrationInfoEntity.excelFileContentType ? (
                  <a onClick={openFile(orderRegistrationInfoEntity.excelFileContentType, orderRegistrationInfoEntity.excelFile)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {orderRegistrationInfoEntity.excelFileContentType}, {byteSize(orderRegistrationInfoEntity.excelFile)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="commissionTransactionNumber">
              <Translate contentKey="tfbitaApp.orderRegistrationInfo.commissionTransactionNumber">Commission Transaction Number</Translate>
            </span>
          </dt>
          <dd>{orderRegistrationInfoEntity.commissionTransactionNumber}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.orderRegType">Order Reg Type</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.orderRegType ? orderRegistrationInfoEntity.orderRegType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.requestType">Request Type</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.requestType ? orderRegistrationInfoEntity.requestType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.importType">Import Type</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.importType ? orderRegistrationInfoEntity.importType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.operationType">Operation Type</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.operationType ? orderRegistrationInfoEntity.operationType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.currencyProvisionType">Currency Provision Type</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.currencyProvisionType ? orderRegistrationInfoEntity.currencyProvisionType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.paymentTool">Payment Tool</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.paymentTool ? orderRegistrationInfoEntity.paymentTool.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.activityType">Activity Type</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.activityType ? orderRegistrationInfoEntity.activityType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.ownerType">Owner Type</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.ownerType ? orderRegistrationInfoEntity.ownerType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.status">Status</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.status ? orderRegistrationInfoEntity.status.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.externalCustomerType">External Customer Type</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.externalCustomerType ? orderRegistrationInfoEntity.externalCustomerType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.transportVehicleType">Transport Vehicle Type</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.transportVehicleType ? orderRegistrationInfoEntity.transportVehicleType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.transportType">Transport Type</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.transportType ? orderRegistrationInfoEntity.transportType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.destCoustomers">Dest Coustomers</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.destCoustomers ? orderRegistrationInfoEntity.destCoustomers.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.cargoPlaceCustoms">Cargo Place Customs</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.cargoPlaceCustoms ? orderRegistrationInfoEntity.cargoPlaceCustoms.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.entranceBorders">Entrance Borders</Translate>
          </dt>
          <dd>{orderRegistrationInfoEntity.entranceBorders ? orderRegistrationInfoEntity.entranceBorders.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.productInfo">Product Info</Translate>
          </dt>
          <dd>
            {orderRegistrationInfoEntity.productInfos
              ? orderRegistrationInfoEntity.productInfos.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {orderRegistrationInfoEntity.productInfos && i === orderRegistrationInfoEntity.productInfos.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegistrationInfo.commissionTransactionNumber">Commission Transaction Number</Translate>
          </dt>
          <dd>
            {orderRegistrationInfoEntity.commissionTransactionNumbers
              ? orderRegistrationInfoEntity.commissionTransactionNumbers.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {orderRegistrationInfoEntity.commissionTransactionNumbers &&
                    i === orderRegistrationInfoEntity.commissionTransactionNumbers.length - 1
                      ? ''
                      : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/order-registration-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order-registration-info/${orderRegistrationInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrderRegistrationInfoDetail;
