import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './transportation-type.reducer';

export const TransportationTypeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const transportationTypeEntity = useAppSelector(state => state.transportationType.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="transportationTypeDetailsHeading">
          <Translate contentKey="tfbitaApp.transportationType.detail.title">TransportationType</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{transportationTypeEntity.id}</dd>
          <dt>
            <span id="latinName">
              <Translate contentKey="tfbitaApp.transportationType.latinName">Latin Name</Translate>
            </span>
          </dt>
          <dd>{transportationTypeEntity.latinName}</dd>
          <dt>
            <span id="modificationDate">
              <Translate contentKey="tfbitaApp.transportationType.modificationDate">Modification Date</Translate>
            </span>
          </dt>
          <dd>{transportationTypeEntity.modificationDate}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tfbitaApp.transportationType.name">Name</Translate>
            </span>
          </dt>
          <dd>{transportationTypeEntity.name}</dd>
        </dl>
        <Button tag={Link} to="/transportation-type" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/transportation-type/${transportationTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TransportationTypeDetail;
