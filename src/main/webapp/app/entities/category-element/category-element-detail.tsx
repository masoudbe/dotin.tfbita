import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './category-element.reducer';

export const CategoryElementDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const categoryElementEntity = useAppSelector(state => state.categoryElement.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="categoryElementDetailsHeading">
          <Translate contentKey="tfbitaApp.categoryElement.detail.title">CategoryElement</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{categoryElementEntity.id}</dd>
          <dt>
            <span id="value">
              <Translate contentKey="tfbitaApp.categoryElement.value">Value</Translate>
            </span>
          </dt>
          <dd>{categoryElementEntity.value}</dd>
          <dt>
            <span id="categoryName">
              <Translate contentKey="tfbitaApp.categoryElement.categoryName">Category Name</Translate>
            </span>
          </dt>
          <dd>{categoryElementEntity.categoryName}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="tfbitaApp.categoryElement.code">Code</Translate>
            </span>
          </dt>
          <dd>{categoryElementEntity.code}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.orderRegType">Order Reg Type</Translate>
          </dt>
          <dd>{categoryElementEntity.orderRegType ? categoryElementEntity.orderRegType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.requestType">Request Type</Translate>
          </dt>
          <dd>{categoryElementEntity.requestType ? categoryElementEntity.requestType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.importType">Import Type</Translate>
          </dt>
          <dd>{categoryElementEntity.importType ? categoryElementEntity.importType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.operationTyp">Operation Typ</Translate>
          </dt>
          <dd>{categoryElementEntity.operationTyp ? categoryElementEntity.operationTyp.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.currencyProvisionType">Currency Provision Type</Translate>
          </dt>
          <dd>{categoryElementEntity.currencyProvisionType ? categoryElementEntity.currencyProvisionType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.paymentTool">Payment Tool</Translate>
          </dt>
          <dd>{categoryElementEntity.paymentTool ? categoryElementEntity.paymentTool.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.activityType">Activity Type</Translate>
          </dt>
          <dd>{categoryElementEntity.activityType ? categoryElementEntity.activityType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.ownerType">Owner Type</Translate>
          </dt>
          <dd>{categoryElementEntity.ownerType ? categoryElementEntity.ownerType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.status">Status</Translate>
          </dt>
          <dd>{categoryElementEntity.status ? categoryElementEntity.status.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.externalCustomerType">External Customer Type</Translate>
          </dt>
          <dd>{categoryElementEntity.externalCustomerType ? categoryElementEntity.externalCustomerType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.transportType">Transport Type</Translate>
          </dt>
          <dd>{categoryElementEntity.transportType ? categoryElementEntity.transportType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.currencySupplier">Currency Supplier</Translate>
          </dt>
          <dd>{categoryElementEntity.currencySupplier ? categoryElementEntity.currencySupplier.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.statusPurchase">Status Purchase</Translate>
          </dt>
          <dd>{categoryElementEntity.statusPurchase ? categoryElementEntity.statusPurchase.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.transportVehicleType">Transport Vehicle Type</Translate>
          </dt>
          <dd>{categoryElementEntity.transportVehicleType ? categoryElementEntity.transportVehicleType.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/category-element" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/category-element/${categoryElementEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CategoryElementDetail;
