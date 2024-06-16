import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-used-assurance.reducer';

export const DraftUsedAssuranceDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftUsedAssuranceEntity = useAppSelector(state => state.draftUsedAssurance.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftUsedAssuranceDetailsHeading">
          <Translate contentKey="tfbitaApp.draftUsedAssurance.detail.title">DraftUsedAssurance</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftUsedAssuranceEntity.id}</dd>
          <dt>
            <span id="assuranceRateId">
              <Translate contentKey="tfbitaApp.draftUsedAssurance.assuranceRateId">Assurance Rate Id</Translate>
            </span>
          </dt>
          <dd>{draftUsedAssuranceEntity.assuranceRateId}</dd>
          <dt>
            <span id="assuranceSerial">
              <Translate contentKey="tfbitaApp.draftUsedAssurance.assuranceSerial">Assurance Serial</Translate>
            </span>
          </dt>
          <dd>{draftUsedAssuranceEntity.assuranceSerial}</dd>
          <dt>
            <span id="exchangeRate">
              <Translate contentKey="tfbitaApp.draftUsedAssurance.exchangeRate">Exchange Rate</Translate>
            </span>
          </dt>
          <dd>{draftUsedAssuranceEntity.exchangeRate}</dd>
          <dt>
            <span id="defaultCurrencyUsedCost">
              <Translate contentKey="tfbitaApp.draftUsedAssurance.defaultCurrencyUsedCost">Default Currency Used Cost</Translate>
            </span>
          </dt>
          <dd>{draftUsedAssuranceEntity.defaultCurrencyUsedCost}</dd>
          <dt>
            <span id="usedCost">
              <Translate contentKey="tfbitaApp.draftUsedAssurance.usedCost">Used Cost</Translate>
            </span>
          </dt>
          <dd>{draftUsedAssuranceEntity.usedCost}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftUsedAssurance.usedAssurances">Used Assurances</Translate>
          </dt>
          <dd>{draftUsedAssuranceEntity.usedAssurances ? draftUsedAssuranceEntity.usedAssurances.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/draft-used-assurance" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-used-assurance/${draftUsedAssuranceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftUsedAssuranceDetail;
