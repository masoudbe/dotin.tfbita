import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './payment-currency-rate-type.reducer';

export const PaymentCurrencyRateTypeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const paymentCurrencyRateTypeEntity = useAppSelector(state => state.paymentCurrencyRateType.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="paymentCurrencyRateTypeDetailsHeading">
          <Translate contentKey="tfbitaApp.paymentCurrencyRateType.detail.title">PaymentCurrencyRateType</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{paymentCurrencyRateTypeEntity.id}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="tfbitaApp.paymentCurrencyRateType.description">Description</Translate>
            </span>
          </dt>
          <dd>{paymentCurrencyRateTypeEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/payment-currency-rate-type" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/payment-currency-rate-type/${paymentCurrencyRateTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PaymentCurrencyRateTypeDetail;
