import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './justification-deduction-amount.reducer';

export const JustificationDeductionAmountDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const justificationDeductionAmountEntity = useAppSelector(state => state.justificationDeductionAmount.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="justificationDeductionAmountDetailsHeading">
          <Translate contentKey="tfbitaApp.justificationDeductionAmount.detail.title">JustificationDeductionAmount</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountEntity.id}</dd>
          <dt>
            <span id="deductionAmount">
              <Translate contentKey="tfbitaApp.justificationDeductionAmount.deductionAmount">Deduction Amount</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountEntity.deductionAmount}</dd>
          <dt>
            <span id="remainingDeductionAmount">
              <Translate contentKey="tfbitaApp.justificationDeductionAmount.remainingDeductionAmount">Remaining Deduction Amount</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountEntity.remainingDeductionAmount}</dd>
          <dt>
            <span id="receivedDeductionAmount">
              <Translate contentKey="tfbitaApp.justificationDeductionAmount.receivedDeductionAmount">Received Deduction Amount</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountEntity.receivedDeductionAmount}</dd>
        </dl>
        <Button tag={Link} to="/justification-deduction-amount" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/justification-deduction-amount/${justificationDeductionAmountEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default JustificationDeductionAmountDetail;
