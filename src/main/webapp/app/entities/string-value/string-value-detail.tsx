import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './string-value.reducer';

export const StringValueDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const stringValueEntity = useAppSelector(state => state.stringValue.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="stringValueDetailsHeading">
          <Translate contentKey="tfbitaApp.stringValue.detail.title">StringValue</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{stringValueEntity.id}</dd>
          <dt>
            <span id="value">
              <Translate contentKey="tfbitaApp.stringValue.value">Value</Translate>
            </span>
          </dt>
          <dd>{stringValueEntity.value}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.stringValue.orderRegistrationInfo">Order Registration Info</Translate>
          </dt>
          <dd>
            {stringValueEntity.orderRegistrationInfos
              ? stringValueEntity.orderRegistrationInfos.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {stringValueEntity.orderRegistrationInfos && i === stringValueEntity.orderRegistrationInfos.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/string-value" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/string-value/${stringValueEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default StringValueDetail;
