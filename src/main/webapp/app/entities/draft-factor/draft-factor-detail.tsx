import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-factor.reducer';

export const DraftFactorDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftFactorEntity = useAppSelector(state => state.draftFactor.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftFactorDetailsHeading">
          <Translate contentKey="tfbitaApp.draftFactor.detail.title">DraftFactor</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftFactorEntity.id}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="tfbitaApp.draftFactor.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{draftFactorEntity.amount}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="tfbitaApp.draftFactor.description">Description</Translate>
            </span>
          </dt>
          <dd>{draftFactorEntity.description}</dd>
          <dt>
            <span id="eqAmount">
              <Translate contentKey="tfbitaApp.draftFactor.eqAmount">Eq Amount</Translate>
            </span>
          </dt>
          <dd>{draftFactorEntity.eqAmount}</dd>
          <dt>
            <span id="factorDate">
              <Translate contentKey="tfbitaApp.draftFactor.factorDate">Factor Date</Translate>
            </span>
          </dt>
          <dd>{draftFactorEntity.factorDate}</dd>
          <dt>
            <span id="issueDate">
              <Translate contentKey="tfbitaApp.draftFactor.issueDate">Issue Date</Translate>
            </span>
          </dt>
          <dd>{draftFactorEntity.issueDate}</dd>
          <dt>
            <span id="serial">
              <Translate contentKey="tfbitaApp.draftFactor.serial">Serial</Translate>
            </span>
          </dt>
          <dd>{draftFactorEntity.serial}</dd>
          <dt>
            <span id="currencyCode">
              <Translate contentKey="tfbitaApp.draftFactor.currencyCode">Currency Code</Translate>
            </span>
          </dt>
          <dd>{draftFactorEntity.currencyCode}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftFactor.draft">Draft</Translate>
          </dt>
          <dd>{draftFactorEntity.draft ? draftFactorEntity.draft.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/draft-factor" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-factor/${draftFactorEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftFactorDetail;
