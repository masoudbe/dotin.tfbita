import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './justification-deduction-amount-part.reducer';

export const JustificationDeductionAmountPartDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const justificationDeductionAmountPartEntity = useAppSelector(state => state.justificationDeductionAmountPart.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="justificationDeductionAmountPartDetailsHeading">
          <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.detail.title">JustificationDeductionAmountPart</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountPartEntity.id}</dd>
          <dt>
            <span id="receiveTransactionCode">
              <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.receiveTransactionCode">Receive Transaction Code</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountPartEntity.receiveTransactionCode}</dd>
          <dt>
            <span id="returnTransactionCode">
              <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.returnTransactionCode">Return Transaction Code</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountPartEntity.returnTransactionCode}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.date">Date</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountPartEntity.date}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountPartEntity.amount}</dd>
          <dt>
            <span id="amountBasedOnRial">
              <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.amountBasedOnRial">Amount Based On Rial</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountPartEntity.amountBasedOnRial}</dd>
          <dt>
            <span id="depositNumber">
              <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.depositNumber">Deposit Number</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountPartEntity.depositNumber}</dd>
          <dt>
            <span id="receiveCurrencyCode">
              <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.receiveCurrencyCode">Receive Currency Code</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountPartEntity.receiveCurrencyCode}</dd>
          <dt>
            <span id="currencyRateDate">
              <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.currencyRateDate">Currency Rate Date</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountPartEntity.currencyRateDate}</dd>
          <dt>
            <span id="sellRate">
              <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.sellRate">Sell Rate</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountPartEntity.sellRate}</dd>
          <dt>
            <span id="buyRate">
              <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.buyRate">Buy Rate</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountPartEntity.buyRate}</dd>
          <dt>
            <span id="comment">
              <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.comment">Comment</Translate>
            </span>
          </dt>
          <dd>{justificationDeductionAmountPartEntity.comment}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.justificationDeductionAmountPart.justificationDeductionAmount">
              Justification Deduction Amount
            </Translate>
          </dt>
          <dd>
            {justificationDeductionAmountPartEntity.justificationDeductionAmount
              ? justificationDeductionAmountPartEntity.justificationDeductionAmount.id
              : ''}
          </dd>
        </dl>
        <Button tag={Link} to="/justification-deduction-amount-part" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button
          tag={Link}
          to={`/justification-deduction-amount-part/${justificationDeductionAmountPartEntity.id}/edit`}
          replace
          color="primary"
        >
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default JustificationDeductionAmountPartDetail;
