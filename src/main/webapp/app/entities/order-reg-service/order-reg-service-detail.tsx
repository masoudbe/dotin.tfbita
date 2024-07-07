import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './order-reg-service.reducer';

export const OrderRegServiceDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const orderRegServiceEntity = useAppSelector(state => state.orderRegService.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderRegServiceDetailsHeading">
          <Translate contentKey="tfbitaApp.orderRegService.detail.title">OrderRegService</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{orderRegServiceEntity.id}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="tfbitaApp.orderRegService.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{orderRegServiceEntity.amount}</dd>
          <dt>
            <span id="currencyAmount">
              <Translate contentKey="tfbitaApp.orderRegService.currencyAmount">Currency Amount</Translate>
            </span>
          </dt>
          <dd>{orderRegServiceEntity.currencyAmount}</dd>
          <dt>
            <span id="unit">
              <Translate contentKey="tfbitaApp.orderRegService.unit">Unit</Translate>
            </span>
          </dt>
          <dd>{orderRegServiceEntity.unit}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegService.serviceTariff">Service Tariff</Translate>
          </dt>
          <dd>{orderRegServiceEntity.serviceTariff ? orderRegServiceEntity.serviceTariff.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegService.orderRegistrationInfo">Order Registration Info</Translate>
          </dt>
          <dd>{orderRegServiceEntity.orderRegistrationInfo ? orderRegServiceEntity.orderRegistrationInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/order-reg-service" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order-reg-service/${orderRegServiceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrderRegServiceDetail;
