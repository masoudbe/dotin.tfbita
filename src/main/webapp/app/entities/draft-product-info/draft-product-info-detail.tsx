import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-product-info.reducer';

export const DraftProductInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftProductInfoEntity = useAppSelector(state => state.draftProductInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftProductInfoDetailsHeading">
          <Translate contentKey="tfbitaApp.draftProductInfo.detail.title">DraftProductInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftProductInfoEntity.id}</dd>
          <dt>
            <span id="productAmount">
              <Translate contentKey="tfbitaApp.draftProductInfo.productAmount">Product Amount</Translate>
            </span>
          </dt>
          <dd>{draftProductInfoEntity.productAmount}</dd>
          <dt>
            <span id="productDimension">
              <Translate contentKey="tfbitaApp.draftProductInfo.productDimension">Product Dimension</Translate>
            </span>
          </dt>
          <dd>{draftProductInfoEntity.productDimension}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftProductInfo.product">Product</Translate>
          </dt>
          <dd>{draftProductInfoEntity.product ? draftProductInfoEntity.product.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftProductInfo.draftReceipt">Draft Receipt</Translate>
          </dt>
          <dd>{draftProductInfoEntity.draftReceipt ? draftProductInfoEntity.draftReceipt.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/draft-product-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-product-info/${draftProductInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftProductInfoDetail;
