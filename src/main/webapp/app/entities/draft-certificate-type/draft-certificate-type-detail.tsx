import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-certificate-type.reducer';

export const DraftCertificateTypeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftCertificateTypeEntity = useAppSelector(state => state.draftCertificateType.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftCertificateTypeDetailsHeading">
          <Translate contentKey="tfbitaApp.draftCertificateType.detail.title">DraftCertificateType</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftCertificateTypeEntity.id}</dd>
          <dt>
            <span id="mandatory">
              <Translate contentKey="tfbitaApp.draftCertificateType.mandatory">Mandatory</Translate>
            </span>
          </dt>
          <dd>{draftCertificateTypeEntity.mandatory ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftCertificateType.draftRequestType">Draft Request Type</Translate>
          </dt>
          <dd>{draftCertificateTypeEntity.draftRequestType ? draftCertificateTypeEntity.draftRequestType.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/draft-certificate-type" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-certificate-type/${draftCertificateTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftCertificateTypeDetail;
