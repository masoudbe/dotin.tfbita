import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './custom.reducer';

export const CustomDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const customEntity = useAppSelector(state => state.custom.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="customDetailsHeading">
          <Translate contentKey="tfbitaApp.custom.detail.title">Custom</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{customEntity.id}</dd>
          <dt>
            <span id="modificationDate">
              <Translate contentKey="tfbitaApp.custom.modificationDate">Modification Date</Translate>
            </span>
          </dt>
          <dd>{customEntity.modificationDate}</dd>
          <dt>
            <span id="latinName">
              <Translate contentKey="tfbitaApp.custom.latinName">Latin Name</Translate>
            </span>
          </dt>
          <dd>{customEntity.latinName}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tfbitaApp.custom.name">Name</Translate>
            </span>
          </dt>
          <dd>{customEntity.name}</dd>
          <dt>
            <span id="tempId">
              <Translate contentKey="tfbitaApp.custom.tempId">Temp Id</Translate>
            </span>
          </dt>
          <dd>{customEntity.tempId}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.custom.loadSwitchPlace">Load Switch Place</Translate>
          </dt>
          <dd>{customEntity.loadSwitchPlace ? customEntity.loadSwitchPlace.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.custom.orderRegistrationInfo">Order Registration Info</Translate>
          </dt>
          <dd>
            {customEntity.orderRegistrationInfos
              ? customEntity.orderRegistrationInfos.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {customEntity.orderRegistrationInfos && i === customEntity.orderRegistrationInfos.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.custom.draft">Draft</Translate>
          </dt>
          <dd>
            {customEntity.drafts
              ? customEntity.drafts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {customEntity.drafts && i === customEntity.drafts.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/custom" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/custom/${customEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CustomDetail;
