import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './swift-bic.reducer';

export const SwiftBicDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const swiftBicEntity = useAppSelector(state => state.swiftBic.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="swiftBicDetailsHeading">
          <Translate contentKey="tfbitaApp.swiftBic.detail.title">SwiftBic</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.id}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="tfbitaApp.swiftBic.address">Address</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.address}</dd>
          <dt>
            <span id="address2">
              <Translate contentKey="tfbitaApp.swiftBic.address2">Address 2</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.address2}</dd>
          <dt>
            <span id="address3">
              <Translate contentKey="tfbitaApp.swiftBic.address3">Address 3</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.address3}</dd>
          <dt>
            <span id="address4">
              <Translate contentKey="tfbitaApp.swiftBic.address4">Address 4</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.address4}</dd>
          <dt>
            <span id="bank">
              <Translate contentKey="tfbitaApp.swiftBic.bank">Bank</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.bank}</dd>
          <dt>
            <span id="bankName">
              <Translate contentKey="tfbitaApp.swiftBic.bankName">Bank Name</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.bankName}</dd>
          <dt>
            <span id="bankName2">
              <Translate contentKey="tfbitaApp.swiftBic.bankName2">Bank Name 2</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.bankName2}</dd>
          <dt>
            <span id="bankName3">
              <Translate contentKey="tfbitaApp.swiftBic.bankName3">Bank Name 3</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.bankName3}</dd>
          <dt>
            <span id="branch">
              <Translate contentKey="tfbitaApp.swiftBic.branch">Branch</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.branch}</dd>
          <dt>
            <span id="branchName">
              <Translate contentKey="tfbitaApp.swiftBic.branchName">Branch Name</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.branchName}</dd>
          <dt>
            <span id="branchName2">
              <Translate contentKey="tfbitaApp.swiftBic.branchName2">Branch Name 2</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.branchName2}</dd>
          <dt>
            <span id="city">
              <Translate contentKey="tfbitaApp.swiftBic.city">City</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.city}</dd>
          <dt>
            <span id="country">
              <Translate contentKey="tfbitaApp.swiftBic.country">Country</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.country}</dd>
          <dt>
            <span id="location">
              <Translate contentKey="tfbitaApp.swiftBic.location">Location</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.location}</dd>
          <dt>
            <span id="subTypeIndicator">
              <Translate contentKey="tfbitaApp.swiftBic.subTypeIndicator">Sub Type Indicator</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.subTypeIndicator}</dd>
          <dt>
            <span id="zip">
              <Translate contentKey="tfbitaApp.swiftBic.zip">Zip</Translate>
            </span>
          </dt>
          <dd>{swiftBicEntity.zip}</dd>
        </dl>
        <Button tag={Link} to="/swift-bic" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/swift-bic/${swiftBicEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SwiftBicDetail;
