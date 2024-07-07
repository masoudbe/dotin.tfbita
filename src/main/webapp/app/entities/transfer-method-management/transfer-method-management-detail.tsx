import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './transfer-method-management.reducer';

export const TransferMethodManagementDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const transferMethodManagementEntity = useAppSelector(state => state.transferMethodManagement.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="transferMethodManagementDetailsHeading">
          <Translate contentKey="tfbitaApp.transferMethodManagement.detail.title">TransferMethodManagement</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{transferMethodManagementEntity.id}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="tfbitaApp.transferMethodManagement.code">Code</Translate>
            </span>
          </dt>
          <dd>{transferMethodManagementEntity.code}</dd>
          <dt>
            <span id="desc">
              <Translate contentKey="tfbitaApp.transferMethodManagement.desc">Desc</Translate>
            </span>
          </dt>
          <dd>{transferMethodManagementEntity.desc}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.transferMethodManagement.type">Type</Translate>
          </dt>
          <dd>{transferMethodManagementEntity.type ? transferMethodManagementEntity.type.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/transfer-method-management" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/transfer-method-management/${transferMethodManagementEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default TransferMethodManagementDetail;
