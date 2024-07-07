import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './payment-item-type.reducer';

export const PaymentItemTypeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const paymentItemTypeEntity = useAppSelector(state => state.paymentItemType.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="paymentItemTypeDetailsHeading">
          <Translate contentKey="tfbitaApp.paymentItemType.detail.title">PaymentItemType</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{paymentItemTypeEntity.id}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="tfbitaApp.paymentItemType.description">Description</Translate>
            </span>
          </dt>
          <dd>{paymentItemTypeEntity.description}</dd>
        </dl>
        <Button tag={Link} to="/payment-item-type" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/payment-item-type/${paymentItemTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PaymentItemTypeDetail;
