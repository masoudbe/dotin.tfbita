import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './purchase-from-other-resources.reducer';

export const PurchaseFromOtherResourcesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const purchaseFromOtherResourcesEntity = useAppSelector(state => state.purchaseFromOtherResources.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="purchaseFromOtherResourcesDetailsHeading">
          <Translate contentKey="tfbitaApp.purchaseFromOtherResources.detail.title">PurchaseFromOtherResources</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{purchaseFromOtherResourcesEntity.id}</dd>
          <dt>
            <span id="evidenceCode">
              <Translate contentKey="tfbitaApp.purchaseFromOtherResources.evidenceCode">Evidence Code</Translate>
            </span>
          </dt>
          <dd>{purchaseFromOtherResourcesEntity.evidenceCode}</dd>
          <dt>
            <span id="currencySupplierDescription">
              <Translate contentKey="tfbitaApp.purchaseFromOtherResources.currencySupplierDescription">
                Currency Supplier Description
              </Translate>
            </span>
          </dt>
          <dd>{purchaseFromOtherResourcesEntity.currencySupplierDescription}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="tfbitaApp.purchaseFromOtherResources.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{purchaseFromOtherResourcesEntity.amount}</dd>
          <dt>
            <span id="purchaseRate">
              <Translate contentKey="tfbitaApp.purchaseFromOtherResources.purchaseRate">Purchase Rate</Translate>
            </span>
          </dt>
          <dd>{purchaseFromOtherResourcesEntity.purchaseRate}</dd>
          <dt>
            <span id="orderRegistrationAmount">
              <Translate contentKey="tfbitaApp.purchaseFromOtherResources.orderRegistrationAmount">Order Registration Amount</Translate>
            </span>
          </dt>
          <dd>{purchaseFromOtherResourcesEntity.orderRegistrationAmount}</dd>
          <dt>
            <span id="requestDate">
              <Translate contentKey="tfbitaApp.purchaseFromOtherResources.requestDate">Request Date</Translate>
            </span>
          </dt>
          <dd>{purchaseFromOtherResourcesEntity.requestDate}</dd>
          <dt>
            <span id="confirmationDate">
              <Translate contentKey="tfbitaApp.purchaseFromOtherResources.confirmationDate">Confirmation Date</Translate>
            </span>
          </dt>
          <dd>{purchaseFromOtherResourcesEntity.confirmationDate}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="tfbitaApp.purchaseFromOtherResources.description">Description</Translate>
            </span>
          </dt>
          <dd>{purchaseFromOtherResourcesEntity.description}</dd>
          <dt>
            <span id="purchaseNumber">
              <Translate contentKey="tfbitaApp.purchaseFromOtherResources.purchaseNumber">Purchase Number</Translate>
            </span>
          </dt>
          <dd>{purchaseFromOtherResourcesEntity.purchaseNumber}</dd>
          <dt>
            <span id="purchaseCurrencyName">
              <Translate contentKey="tfbitaApp.purchaseFromOtherResources.purchaseCurrencyName">Purchase Currency Name</Translate>
            </span>
          </dt>
          <dd>{purchaseFromOtherResourcesEntity.purchaseCurrencyName}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.purchaseFromOtherResources.orderRegistrationInfo">Order Registration Info</Translate>
          </dt>
          <dd>{purchaseFromOtherResourcesEntity.orderRegistrationInfo ? purchaseFromOtherResourcesEntity.orderRegistrationInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/purchase-from-other-resources" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/purchase-from-other-resources/${purchaseFromOtherResourcesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PurchaseFromOtherResourcesDetail;
