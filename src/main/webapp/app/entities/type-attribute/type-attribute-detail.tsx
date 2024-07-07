import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './type-attribute.reducer';

export const TypeAttributeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const typeAttributeEntity = useAppSelector(state => state.typeAttribute.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="typeAttributeDetailsHeading">
          <Translate contentKey="tfbitaApp.typeAttribute.detail.title">TypeAttribute</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{typeAttributeEntity.id}</dd>
          <dt>
            <span id="necessary">
              <Translate contentKey="tfbitaApp.typeAttribute.necessary">Necessary</Translate>
            </span>
          </dt>
          <dd>{typeAttributeEntity.necessary ? 'true' : 'false'}</dd>
          <dt>
            <span id="isUnique">
              <Translate contentKey="tfbitaApp.typeAttribute.isUnique">Is Unique</Translate>
            </span>
          </dt>
          <dd>{typeAttributeEntity.isUnique ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.typeAttribute.attribute">Attribute</Translate>
          </dt>
          <dd>{typeAttributeEntity.attribute ? typeAttributeEntity.attribute.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.typeAttribute.productType">Product Type</Translate>
          </dt>
          <dd>
            {typeAttributeEntity.productTypes
              ? typeAttributeEntity.productTypes.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {typeAttributeEntity.productTypes && i === typeAttributeEntity.productTypes.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/type-attribute" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/type-attribute/${typeAttributeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TypeAttributeDetail;
