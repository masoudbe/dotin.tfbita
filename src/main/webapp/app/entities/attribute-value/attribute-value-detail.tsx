import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './attribute-value.reducer';

export const AttributeValueDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const attributeValueEntity = useAppSelector(state => state.attributeValue.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="attributeValueDetailsHeading">
          <Translate contentKey="tfbitaApp.attributeValue.detail.title">AttributeValue</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{attributeValueEntity.id}</dd>
          <dt>
            <span id="value">
              <Translate contentKey="tfbitaApp.attributeValue.value">Value</Translate>
            </span>
          </dt>
          <dd>{attributeValueEntity.value}</dd>
          <dt>
            <span id="customValue">
              <Translate contentKey="tfbitaApp.attributeValue.customValue">Custom Value</Translate>
            </span>
          </dt>
          <dd>{attributeValueEntity.customValue}</dd>
          <dt>
            <span id="attributeValueGroupName">
              <Translate contentKey="tfbitaApp.attributeValue.attributeValueGroupName">Attribute Value Group Name</Translate>
            </span>
          </dt>
          <dd>{attributeValueEntity.attributeValueGroupName}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.attributeValue.typeAttribute">Type Attribute</Translate>
          </dt>
          <dd>{attributeValueEntity.typeAttribute ? attributeValueEntity.typeAttribute.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.attributeValue.attributeValueGroup">Attribute Value Group</Translate>
          </dt>
          <dd>{attributeValueEntity.attributeValueGroup ? attributeValueEntity.attributeValueGroup.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.attributeValue.product">Product</Translate>
          </dt>
          <dd>{attributeValueEntity.product ? attributeValueEntity.product.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/attribute-value" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/attribute-value/${attributeValueEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AttributeValueDetail;
