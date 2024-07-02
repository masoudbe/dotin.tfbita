import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './objective-category.reducer';

export const ObjectiveCategoryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const objectiveCategoryEntity = useAppSelector(state => state.objectiveCategory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="objectiveCategoryDetailsHeading">
          <Translate contentKey="tfbitaApp.objectiveCategory.detail.title">ObjectiveCategory</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{objectiveCategoryEntity.id}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tfbitaApp.objectiveCategory.name">Name</Translate>
            </span>
          </dt>
          <dd>{objectiveCategoryEntity.name}</dd>
        </dl>
        <Button tag={Link} to="/objective-category" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/objective-category/${objectiveCategoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ObjectiveCategoryDetail;
