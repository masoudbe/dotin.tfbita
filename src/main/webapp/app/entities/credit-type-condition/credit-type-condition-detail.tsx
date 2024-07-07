import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './credit-type-condition.reducer';

export const CreditTypeConditionDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const creditTypeConditionEntity = useAppSelector(state => state.creditTypeCondition.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="creditTypeConditionDetailsHeading">
          <Translate contentKey="tfbitaApp.creditTypeCondition.detail.title">CreditTypeCondition</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.id}</dd>
          <dt>
            <span id="assurancePercentage">
              <Translate contentKey="tfbitaApp.creditTypeCondition.assurancePercentage">Assurance Percentage</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.assurancePercentage}</dd>
          <dt>
            <span id="commissionRate">
              <Translate contentKey="tfbitaApp.creditTypeCondition.commissionRate">Commission Rate</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.commissionRate}</dd>
          <dt>
            <span id="customerPrepaymentRateFrom">
              <Translate contentKey="tfbitaApp.creditTypeCondition.customerPrepaymentRateFrom">Customer Prepayment Rate From</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.customerPrepaymentRateFrom}</dd>
          <dt>
            <span id="customerPrepaymentRateTo">
              <Translate contentKey="tfbitaApp.creditTypeCondition.customerPrepaymentRateTo">Customer Prepayment Rate To</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.customerPrepaymentRateTo}</dd>
          <dt>
            <span id="durationFrom">
              <Translate contentKey="tfbitaApp.creditTypeCondition.durationFrom">Duration From</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.durationFrom}</dd>
          <dt>
            <span id="durationTo">
              <Translate contentKey="tfbitaApp.creditTypeCondition.durationTo">Duration To</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.durationTo}</dd>
          <dt>
            <span id="orderRegistrationRightFrom">
              <Translate contentKey="tfbitaApp.creditTypeCondition.orderRegistrationRightFrom">Order Registration Right From</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.orderRegistrationRightFrom}</dd>
          <dt>
            <span id="orderRegistrationRightTo">
              <Translate contentKey="tfbitaApp.creditTypeCondition.orderRegistrationRightTo">Order Registration Right To</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.orderRegistrationRightTo}</dd>
          <dt>
            <span id="postSuspensionPeriodPenaltyRate">
              <Translate contentKey="tfbitaApp.creditTypeCondition.postSuspensionPeriodPenaltyRate">
                Post Suspension Period Penalty Rate
              </Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.postSuspensionPeriodPenaltyRate}</dd>
          <dt>
            <span id="priceFrom">
              <Translate contentKey="tfbitaApp.creditTypeCondition.priceFrom">Price From</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.priceFrom}</dd>
          <dt>
            <span id="priceTo">
              <Translate contentKey="tfbitaApp.creditTypeCondition.priceTo">Price To</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.priceTo}</dd>
          <dt>
            <span id="suspensionDurationFrom">
              <Translate contentKey="tfbitaApp.creditTypeCondition.suspensionDurationFrom">Suspension Duration From</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.suspensionDurationFrom}</dd>
          <dt>
            <span id="suspensionDurationTo">
              <Translate contentKey="tfbitaApp.creditTypeCondition.suspensionDurationTo">Suspension Duration To</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.suspensionDurationTo}</dd>
          <dt>
            <span id="suspensionPeriodInterestRate">
              <Translate contentKey="tfbitaApp.creditTypeCondition.suspensionPeriodInterestRate">Suspension Period Interest Rate</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.suspensionPeriodInterestRate}</dd>
          <dt>
            <span id="updateCommissionRate">
              <Translate contentKey="tfbitaApp.creditTypeCondition.updateCommissionRate">Update Commission Rate</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.updateCommissionRate}</dd>
          <dt>
            <span id="currencyCode">
              <Translate contentKey="tfbitaApp.creditTypeCondition.currencyCode">Currency Code</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionEntity.currencyCode}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.creditTypeCondition.serviceOrProduct">Service Or Product</Translate>
          </dt>
          <dd>{creditTypeConditionEntity.serviceOrProduct ? creditTypeConditionEntity.serviceOrProduct.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.creditTypeCondition.neededIdentificationDocTypes">Needed Identification Doc Types</Translate>
          </dt>
          <dd>{creditTypeConditionEntity.neededIdentificationDocTypes ? creditTypeConditionEntity.neededIdentificationDocTypes.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.creditTypeCondition.productTypes">Product Types</Translate>
          </dt>
          <dd>{creditTypeConditionEntity.productTypes ? creditTypeConditionEntity.productTypes.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.creditTypeCondition.assuranceTypes">Assurance Types</Translate>
          </dt>
          <dd>{creditTypeConditionEntity.assuranceTypes ? creditTypeConditionEntity.assuranceTypes.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.creditTypeCondition.creditTypeConditionInfo">Credit Type Condition Info</Translate>
          </dt>
          <dd>{creditTypeConditionEntity.creditTypeConditionInfo ? creditTypeConditionEntity.creditTypeConditionInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/credit-type-condition" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/credit-type-condition/${creditTypeConditionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CreditTypeConditionDetail;
