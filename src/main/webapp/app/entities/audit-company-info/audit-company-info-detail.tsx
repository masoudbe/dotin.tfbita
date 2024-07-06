import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './audit-company-info.reducer';

export const AuditCompanyInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const auditCompanyInfoEntity = useAppSelector(state => state.auditCompanyInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="auditCompanyInfoDetailsHeading">
          <Translate contentKey="tfbitaApp.auditCompanyInfo.detail.title">AuditCompanyInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.id}</dd>
          <dt>
            <span id="address">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.address">Address</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.address}</dd>
          <dt>
            <span id="barCodes">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.barCodes">Bar Codes</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.barCodes}</dd>
          <dt>
            <span id="dateOfRegistration">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.dateOfRegistration">Date Of Registration</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.dateOfRegistration}</dd>
          <dt>
            <span id="fax">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.fax">Fax</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.fax}</dd>
          <dt>
            <span id="floor">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.floor">Floor</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.floor}</dd>
          <dt>
            <span id="inernationalobserviation">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.inernationalobserviation">Inernationalobserviation</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.inernationalobserviation}</dd>
          <dt>
            <span id="mainStreet">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.mainStreet">Main Street</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.mainStreet}</dd>
          <dt>
            <span id="mobile">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.mobile">Mobile</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.mobile}</dd>
          <dt>
            <span id="plaque">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.plaque">Plaque</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.plaque}</dd>
          <dt>
            <span id="postalCode">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.postalCode">Postal Code</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.postalCode}</dd>
          <dt>
            <span id="registrationNumber">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.registrationNumber">Registration Number</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.registrationNumber}</dd>
          <dt>
            <span id="telephone">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.telephone">Telephone</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.telephone}</dd>
          <dt>
            <span id="tempId">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.tempId">Temp Id</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.tempId}</dd>
          <dt>
            <span id="theSideStreet">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.theSideStreet">The Side Street</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.theSideStreet}</dd>
          <dt>
            <span id="title">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.title">Title</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.title}</dd>
          <dt>
            <span id="unit">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.unit">Unit</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.unit}</dd>
          <dt>
            <span id="cityCode">
              <Translate contentKey="tfbitaApp.auditCompanyInfo.cityCode">City Code</Translate>
            </span>
          </dt>
          <dd>{auditCompanyInfoEntity.cityCode}</dd>
        </dl>
        <Button tag={Link} to="/audit-company-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/audit-company-info/${auditCompanyInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AuditCompanyInfoDetail;
