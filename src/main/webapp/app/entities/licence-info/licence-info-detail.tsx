import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './licence-info.reducer';

export const LicenceInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const licenceInfoEntity = useAppSelector(state => state.licenceInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="licenceInfoDetailsHeading">
          <Translate contentKey="tfbitaApp.licenceInfo.detail.title">LicenceInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{licenceInfoEntity.id}</dd>
          <dt>
            <span id="organizationLicence">
              <Translate contentKey="tfbitaApp.licenceInfo.organizationLicence">Organization Licence</Translate>
            </span>
          </dt>
          <dd>{licenceInfoEntity.organizationLicence}</dd>
          <dt>
            <span id="licenceNumber">
              <Translate contentKey="tfbitaApp.licenceInfo.licenceNumber">Licence Number</Translate>
            </span>
          </dt>
          <dd>{licenceInfoEntity.licenceNumber}</dd>
          <dt>
            <span id="licenceDate">
              <Translate contentKey="tfbitaApp.licenceInfo.licenceDate">Licence Date</Translate>
            </span>
          </dt>
          <dd>{licenceInfoEntity.licenceDate}</dd>
          <dt>
            <span id="havingProduct">
              <Translate contentKey="tfbitaApp.licenceInfo.havingProduct">Having Product</Translate>
            </span>
          </dt>
          <dd>{licenceInfoEntity.havingProduct ? 'true' : 'false'}</dd>
          <dt>
            <span id="havingService">
              <Translate contentKey="tfbitaApp.licenceInfo.havingService">Having Service</Translate>
            </span>
          </dt>
          <dd>{licenceInfoEntity.havingService ? 'true' : 'false'}</dd>
          <dt>
            <span id="creditDate">
              <Translate contentKey="tfbitaApp.licenceInfo.creditDate">Credit Date</Translate>
            </span>
          </dt>
          <dd>{licenceInfoEntity.creditDate}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.licenceInfo.product">Product</Translate>
          </dt>
          <dd>{licenceInfoEntity.product ? licenceInfoEntity.product.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.licenceInfo.orderRegServ">Order Reg Serv</Translate>
          </dt>
          <dd>{licenceInfoEntity.orderRegServ ? licenceInfoEntity.orderRegServ.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.licenceInfo.orderRegistrationInfo">Order Registration Info</Translate>
          </dt>
          <dd>{licenceInfoEntity.orderRegistrationInfo ? licenceInfoEntity.orderRegistrationInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/licence-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/licence-info/${licenceInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LicenceInfoDetail;
