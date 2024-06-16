import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './order-reg-serv.reducer';

export const OrderRegServDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const orderRegServEntity = useAppSelector(state => state.orderRegServ.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="orderRegServDetailsHeading">
          <Translate contentKey="tfbitaApp.orderRegServ.detail.title">OrderRegServ</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{orderRegServEntity.id}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="tfbitaApp.orderRegServ.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{orderRegServEntity.amount}</dd>
          <dt>
            <span id="currencyAmount">
              <Translate contentKey="tfbitaApp.orderRegServ.currencyAmount">Currency Amount</Translate>
            </span>
          </dt>
          <dd>{orderRegServEntity.currencyAmount}</dd>
          <dt>
            <span id="unit">
              <Translate contentKey="tfbitaApp.orderRegServ.unit">Unit</Translate>
            </span>
          </dt>
          <dd>{orderRegServEntity.unit}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="tfbitaApp.orderRegServ.title">Title</Translate>
            </span>
          </dt>
          <dd>{orderRegServEntity.title}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="tfbitaApp.orderRegServ.code">Code</Translate>
            </span>
          </dt>
          <dd>{orderRegServEntity.code}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.orderRegServ.orderRegService">Order Reg Service</Translate>
          </dt>
          <dd>{orderRegServEntity.orderRegService ? orderRegServEntity.orderRegService.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/order-reg-serv" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/order-reg-serv/${orderRegServEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OrderRegServDetail;
