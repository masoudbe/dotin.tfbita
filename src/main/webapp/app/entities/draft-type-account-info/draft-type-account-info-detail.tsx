import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-type-account-info.reducer';

export const DraftTypeAccountInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftTypeAccountInfoEntity = useAppSelector(state => state.draftTypeAccountInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftTypeAccountInfoDetailsHeading">
          <Translate contentKey="tfbitaApp.draftTypeAccountInfo.detail.title">DraftTypeAccountInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftTypeAccountInfoEntity.id}</dd>
          <dt>
            <span id="sellCurrencyCommissionAccount">
              <Translate contentKey="tfbitaApp.draftTypeAccountInfo.sellCurrencyCommissionAccount">
                Sell Currency Commission Account
              </Translate>
            </span>
          </dt>
          <dd>{draftTypeAccountInfoEntity.sellCurrencyCommissionAccount}</dd>
          <dt>
            <span id="incomeAccountNumber">
              <Translate contentKey="tfbitaApp.draftTypeAccountInfo.incomeAccountNumber">Income Account Number</Translate>
            </span>
          </dt>
          <dd>{draftTypeAccountInfoEntity.incomeAccountNumber}</dd>
        </dl>
        <Button tag={Link} to="/draft-type-account-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-type-account-info/${draftTypeAccountInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftTypeAccountInfoDetail;
