import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './basic-info.reducer';

export const BasicInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const basicInfoEntity = useAppSelector(state => state.basicInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="basicInfoDetailsHeading">
          <Translate contentKey="tfbitaApp.basicInfo.detail.title">BasicInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{basicInfoEntity.id}</dd>
          <dt>
            <span id="applyDate">
              <Translate contentKey="tfbitaApp.basicInfo.applyDate">Apply Date</Translate>
            </span>
          </dt>
          <dd>{basicInfoEntity.applyDate}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="tfbitaApp.basicInfo.code">Code</Translate>
            </span>
          </dt>
          <dd>{basicInfoEntity.code}</dd>
          <dt>
            <span id="disabled">
              <Translate contentKey="tfbitaApp.basicInfo.disabled">Disabled</Translate>
            </span>
          </dt>
          <dd>{basicInfoEntity.disabled ? 'true' : 'false'}</dd>
        </dl>
        <Button tag={Link} to="/basic-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/basic-info/${basicInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BasicInfoDetail;
