import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './attribute-value-group.reducer';

export const AttributeValueGroupDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const attributeValueGroupEntity = useAppSelector(state => state.attributeValueGroup.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="attributeValueGroupDetailsHeading">
          <Translate contentKey="tfbitaApp.attributeValueGroup.detail.title">AttributeValueGroup</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{attributeValueGroupEntity.id}</dd>
          <dt>
            <span id="mandatory">
              <Translate contentKey="tfbitaApp.attributeValueGroup.mandatory">Mandatory</Translate>
            </span>
          </dt>
          <dd>{attributeValueGroupEntity.mandatory ? 'true' : 'false'}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tfbitaApp.attributeValueGroup.name">Name</Translate>
            </span>
          </dt>
          <dd>{attributeValueGroupEntity.name}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.attributeValueGroup.attribute">Attribute</Translate>
          </dt>
          <dd>{attributeValueGroupEntity.attribute ? attributeValueGroupEntity.attribute.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/attribute-value-group" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/attribute-value-group/${attributeValueGroupEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AttributeValueGroupDetail;
