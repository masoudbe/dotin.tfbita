import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './objective-category-element.reducer';

export const ObjectiveCategoryElementDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const objectiveCategoryElementEntity = useAppSelector(state => state.objectiveCategoryElement.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="objectiveCategoryElementDetailsHeading">
          <Translate contentKey="tfbitaApp.objectiveCategoryElement.detail.title">ObjectiveCategoryElement</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{objectiveCategoryElementEntity.id}</dd>
          <dt>
            <span id="entityClass">
              <Translate contentKey="tfbitaApp.objectiveCategoryElement.entityClass">Entity Class</Translate>
            </span>
          </dt>
          <dd>{objectiveCategoryElementEntity.entityClass}</dd>
          <dt>
            <span id="entityId">
              <Translate contentKey="tfbitaApp.objectiveCategoryElement.entityId">Entity Id</Translate>
            </span>
          </dt>
          <dd>{objectiveCategoryElementEntity.entityId}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.objectiveCategoryElement.objectiveCategory">Objective Category</Translate>
          </dt>
          <dd>{objectiveCategoryElementEntity.objectiveCategory ? objectiveCategoryElementEntity.objectiveCategory.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/objective-category-element" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/objective-category-element/${objectiveCategoryElementEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ObjectiveCategoryElementDetail;
