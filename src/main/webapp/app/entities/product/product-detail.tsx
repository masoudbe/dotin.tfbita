import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './product.reducer';

export const ProductDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const productEntity = useAppSelector(state => state.product.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="productDetailsHeading">
          <Translate contentKey="tfbitaApp.product.detail.title">Product</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{productEntity.id}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="tfbitaApp.product.code">Code</Translate>
            </span>
          </dt>
          <dd>{productEntity.code}</dd>
          <dt>
            <span id="modificationDate">
              <Translate contentKey="tfbitaApp.product.modificationDate">Modification Date</Translate>
            </span>
          </dt>
          <dd>{productEntity.modificationDate}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tfbitaApp.product.name">Name</Translate>
            </span>
          </dt>
          <dd>{productEntity.name}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="tfbitaApp.product.description">Description</Translate>
            </span>
          </dt>
          <dd>{productEntity.description}</dd>
          <dt>
            <span id="topicCode">
              <Translate contentKey="tfbitaApp.product.topicCode">Topic Code</Translate>
            </span>
          </dt>
          <dd>{productEntity.topicCode}</dd>
          <dt>
            <span id="attributeValueGroupName">
              <Translate contentKey="tfbitaApp.product.attributeValueGroupName">Attribute Value Group Name</Translate>
            </span>
          </dt>
          <dd>{productEntity.attributeValueGroupName}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.product.orderRegistrationInfo">Order Registration Info</Translate>
          </dt>
          <dd>
            {productEntity.orderRegistrationInfos
              ? productEntity.orderRegistrationInfos.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {productEntity.orderRegistrationInfos && i === productEntity.orderRegistrationInfos.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.product.draft">Draft</Translate>
          </dt>
          <dd>
            {productEntity.drafts
              ? productEntity.drafts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {productEntity.drafts && i === productEntity.drafts.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.product.draftProductInfos">Draft Product Infos</Translate>
          </dt>
          <dd>{productEntity.draftProductInfos ? productEntity.draftProductInfos.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/product" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/product/${productEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductDetail;
