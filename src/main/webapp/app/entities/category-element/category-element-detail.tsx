import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './category-element.reducer';

export const CategoryElementDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const categoryElementEntity = useAppSelector(state => state.categoryElement.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="categoryElementDetailsHeading">
          <Translate contentKey="tfbitaApp.categoryElement.detail.title">CategoryElement</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{categoryElementEntity.id}</dd>
          <dt>
            <span id="value">
              <Translate contentKey="tfbitaApp.categoryElement.value">Value</Translate>
            </span>
          </dt>
          <dd>{categoryElementEntity.value}</dd>
          <dt>
            <span id="categoryName">
              <Translate contentKey="tfbitaApp.categoryElement.categoryName">Category Name</Translate>
            </span>
          </dt>
          <dd>{categoryElementEntity.categoryName}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="tfbitaApp.categoryElement.code">Code</Translate>
            </span>
          </dt>
          <dd>{categoryElementEntity.code}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.category">Category</Translate>
          </dt>
          <dd>{categoryElementEntity.category ? categoryElementEntity.category.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.categoryElement.orderRegistrationInfo">Order Registration Info</Translate>
          </dt>
          <dd>
            {categoryElementEntity.orderRegistrationInfos
              ? categoryElementEntity.orderRegistrationInfos.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {categoryElementEntity.orderRegistrationInfos && i === categoryElementEntity.orderRegistrationInfos.length - 1
                      ? ''
                      : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/category-element" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/category-element/${categoryElementEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CategoryElementDetail;
