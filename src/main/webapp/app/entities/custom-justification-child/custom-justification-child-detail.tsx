import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './custom-justification-child.reducer';

export const CustomJustificationChildDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const customJustificationChildEntity = useAppSelector(state => state.customJustificationChild.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="customJustificationChildDetailsHeading">
          <Translate contentKey="tfbitaApp.customJustificationChild.detail.title">CustomJustificationChild</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{customJustificationChildEntity.id}</dd>
          <dt>
            <span id="item">
              <Translate contentKey="tfbitaApp.customJustificationChild.item">Item</Translate>
            </span>
          </dt>
          <dd>{customJustificationChildEntity.item}</dd>
          <dt>
            <span id="tariffCode">
              <Translate contentKey="tfbitaApp.customJustificationChild.tariffCode">Tariff Code</Translate>
            </span>
          </dt>
          <dd>{customJustificationChildEntity.tariffCode}</dd>
          <dt>
            <span id="productName">
              <Translate contentKey="tfbitaApp.customJustificationChild.productName">Product Name</Translate>
            </span>
          </dt>
          <dd>{customJustificationChildEntity.productName}</dd>
          <dt>
            <span id="productId">
              <Translate contentKey="tfbitaApp.customJustificationChild.productId">Product Id</Translate>
            </span>
          </dt>
          <dd>{customJustificationChildEntity.productId}</dd>
          <dt>
            <span id="boxCount">
              <Translate contentKey="tfbitaApp.customJustificationChild.boxCount">Box Count</Translate>
            </span>
          </dt>
          <dd>{customJustificationChildEntity.boxCount}</dd>
          <dt>
            <span id="boxType">
              <Translate contentKey="tfbitaApp.customJustificationChild.boxType">Box Type</Translate>
            </span>
          </dt>
          <dd>{customJustificationChildEntity.boxType}</dd>
          <dt>
            <span id="pureWeight">
              <Translate contentKey="tfbitaApp.customJustificationChild.pureWeight">Pure Weight</Translate>
            </span>
          </dt>
          <dd>{customJustificationChildEntity.pureWeight}</dd>
          <dt>
            <span id="impureWeight">
              <Translate contentKey="tfbitaApp.customJustificationChild.impureWeight">Impure Weight</Translate>
            </span>
          </dt>
          <dd>{customJustificationChildEntity.impureWeight}</dd>
          <dt>
            <span id="amountCurrency">
              <Translate contentKey="tfbitaApp.customJustificationChild.amountCurrency">Amount Currency</Translate>
            </span>
          </dt>
          <dd>{customJustificationChildEntity.amountCurrency}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.customJustificationChild.customJustification">Custom Justification</Translate>
          </dt>
          <dd>{customJustificationChildEntity.customJustification ? customJustificationChildEntity.customJustification.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/custom-justification-child" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/custom-justification-child/${customJustificationChildEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CustomJustificationChildDetail;
