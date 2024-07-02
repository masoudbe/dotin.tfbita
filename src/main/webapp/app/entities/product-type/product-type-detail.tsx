import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './product-type.reducer';

export const ProductTypeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const productTypeEntity = useAppSelector(state => state.productType.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="productTypeDetailsHeading">
          <Translate contentKey="tfbitaApp.productType.detail.title">ProductType</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{productTypeEntity.id}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="tfbitaApp.productType.description">Description</Translate>
            </span>
          </dt>
          <dd>{productTypeEntity.description}</dd>
          <dt>
            <span id="modificationDate">
              <Translate contentKey="tfbitaApp.productType.modificationDate">Modification Date</Translate>
            </span>
          </dt>
          <dd>{productTypeEntity.modificationDate}</dd>
          <dt>
            <span id="topicCode">
              <Translate contentKey="tfbitaApp.productType.topicCode">Topic Code</Translate>
            </span>
          </dt>
          <dd>{productTypeEntity.topicCode}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.productType.productTypeAttributes">Product Type Attributes</Translate>
          </dt>
          <dd>
            {productTypeEntity.productTypeAttributes
              ? productTypeEntity.productTypeAttributes.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {productTypeEntity.productTypeAttributes && i === productTypeEntity.productTypeAttributes.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/product-type" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/product-type/${productTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductTypeDetail;
