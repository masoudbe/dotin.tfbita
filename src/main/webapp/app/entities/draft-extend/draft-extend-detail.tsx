import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-extend.reducer';

export const DraftExtendDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftExtendEntity = useAppSelector(state => state.draftExtend.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftExtendDetailsHeading">
          <Translate contentKey="tfbitaApp.draftExtend.detail.title">DraftExtend</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftExtendEntity.id}</dd>
          <dt>
            <span id="date">
              <Translate contentKey="tfbitaApp.draftExtend.date">Date</Translate>
            </span>
          </dt>
          <dd>{draftExtendEntity.date}</dd>
          <dt>
            <span id="duration">
              <Translate contentKey="tfbitaApp.draftExtend.duration">Duration</Translate>
            </span>
          </dt>
          <dd>{draftExtendEntity.duration}</dd>
          <dt>
            <span id="time">
              <Translate contentKey="tfbitaApp.draftExtend.time">Time</Translate>
            </span>
          </dt>
          <dd>{draftExtendEntity.time}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftExtend.draftField">Draft Field</Translate>
          </dt>
          <dd>{draftExtendEntity.draftField ? draftExtendEntity.draftField.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/draft-extend" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-extend/${draftExtendEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftExtendDetail;
