import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './attribute.reducer';

export const AttributeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const attributeEntity = useAppSelector(state => state.attribute.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="attributeDetailsHeading">
          <Translate contentKey="tfbitaApp.attribute.detail.title">Attribute</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{attributeEntity.id}</dd>
          <dt>
            <span id="modificationDate">
              <Translate contentKey="tfbitaApp.attribute.modificationDate">Modification Date</Translate>
            </span>
          </dt>
          <dd>{attributeEntity.modificationDate}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tfbitaApp.attribute.name">Name</Translate>
            </span>
          </dt>
          <dd>{attributeEntity.name}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.attribute.format">Format</Translate>
          </dt>
          <dd>{attributeEntity.format ? attributeEntity.format.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.attribute.type">Type</Translate>
          </dt>
          <dd>{attributeEntity.type ? attributeEntity.type.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/attribute" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/attribute/${attributeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AttributeDetail;
