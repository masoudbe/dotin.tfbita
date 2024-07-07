import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './long-value.reducer';

export const LongValueDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const longValueEntity = useAppSelector(state => state.longValue.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="longValueDetailsHeading">
          <Translate contentKey="tfbitaApp.longValue.detail.title">LongValue</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{longValueEntity.id}</dd>
          <dt>
            <span id="val">
              <Translate contentKey="tfbitaApp.longValue.val">Val</Translate>
            </span>
          </dt>
          <dd>{longValueEntity.val}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.longValue.draft">Draft</Translate>
          </dt>
          <dd>
            {longValueEntity.drafts
              ? longValueEntity.drafts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {longValueEntity.drafts && i === longValueEntity.drafts.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/long-value" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/long-value/${longValueEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LongValueDetail;
