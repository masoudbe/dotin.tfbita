import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './trade-type-code.reducer';

export const TradeTypeCodeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const tradeTypeCodeEntity = useAppSelector(state => state.tradeTypeCode.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="tradeTypeCodeDetailsHeading">
          <Translate contentKey="tfbitaApp.tradeTypeCode.detail.title">TradeTypeCode</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{tradeTypeCodeEntity.id}</dd>
          <dt>
            <span id="latinName">
              <Translate contentKey="tfbitaApp.tradeTypeCode.latinName">Latin Name</Translate>
            </span>
          </dt>
          <dd>{tradeTypeCodeEntity.latinName}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tfbitaApp.tradeTypeCode.name">Name</Translate>
            </span>
          </dt>
          <dd>{tradeTypeCodeEntity.name}</dd>
        </dl>
        <Button tag={Link} to="/trade-type-code" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/trade-type-code/${tradeTypeCodeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TradeTypeCodeDetail;
