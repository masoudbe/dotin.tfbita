import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './currency-exchange-info.reducer';

export const CurrencyExchangeInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const currencyExchangeInfoEntity = useAppSelector(state => state.currencyExchangeInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="currencyExchangeInfoDetailsHeading">
          <Translate contentKey="tfbitaApp.currencyExchangeInfo.detail.title">CurrencyExchangeInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{currencyExchangeInfoEntity.id}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="tfbitaApp.currencyExchangeInfo.title">Title</Translate>
            </span>
          </dt>
          <dd>{currencyExchangeInfoEntity.title}</dd>
        </dl>
        <Button tag={Link} to="/currency-exchange-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/currency-exchange-info/${currencyExchangeInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CurrencyExchangeInfoDetail;
