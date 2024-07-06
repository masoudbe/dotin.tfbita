import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './justification-deduction-detail.reducer';

export const JustificationDeductionDetailDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const justificationDeductionDetailEntity = useAppSelector(state => state.justificationDeductionDetail.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="justificationDeductionDetailDetailsHeading">
          <Translate contentKey="tfbitaApp.justificationDeductionDetail.detail.title">JustificationDeductionDetail</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionDetailEntity.id}</dd>
          <dt>
            <span id="deductionAmount">
              <Translate contentKey="tfbitaApp.justificationDeductionDetail.deductionAmount">Deduction Amount</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionDetailEntity.deductionAmount}</dd>
          <dt>
            <span id="equivalentDeductionAmount">
              <Translate contentKey="tfbitaApp.justificationDeductionDetail.equivalentDeductionAmount">
                Equivalent Deduction Amount
              </Translate>
            </span>
          </dt>
          <dd>{justificationDeductionDetailEntity.equivalentDeductionAmount}</dd>
          <dt>
            <span id="receiveCurrencyCode">
              <Translate contentKey="tfbitaApp.justificationDeductionDetail.receiveCurrencyCode">Receive Currency Code</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionDetailEntity.receiveCurrencyCode}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="tfbitaApp.justificationDeductionDetail.comment">Comment</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionDetailEntity.comment}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.justificationDeductionDetail.deductionReason">Deduction Reason</Translate>
          </dt>
          <dd>{justificationDeductionDetailEntity.deductionReason ? justificationDeductionDetailEntity.deductionReason.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.justificationDeductionDetail.justificationDeductionAmount">
              Justification Deduction Amount
            </Translate>
          </dt>
          <dd>
            {justificationDeductionDetailEntity.justificationDeductionAmount
              ? justificationDeductionDetailEntity.justificationDeductionAmount.id
              : ''}
          </dd>
        </dl>
        <Button tag={Link} to="/justification-deduction-detail" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/justification-deduction-detail/${justificationDeductionDetailEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default JustificationDeductionDetailDetail;
