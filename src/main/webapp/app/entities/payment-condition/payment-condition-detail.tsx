import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './payment-condition.reducer';

export const PaymentConditionDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const paymentConditionEntity = useAppSelector(state => state.paymentCondition.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="paymentConditionDetailsHeading">
          <Translate contentKey="tfbitaApp.paymentCondition.detail.title">PaymentCondition</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{paymentConditionEntity.id}</dd>
          <dt>
            <span id="latinName">
              <Translate contentKey="tfbitaApp.paymentCondition.latinName">Latin Name</Translate>
            </span>
          </dt>
          <dd>{paymentConditionEntity.latinName}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tfbitaApp.paymentCondition.name">Name</Translate>
            </span>
          </dt>
          <dd>{paymentConditionEntity.name}</dd>
        </dl>
        <Button tag={Link} to="/payment-condition" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/payment-condition/${paymentConditionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PaymentConditionDetail;
