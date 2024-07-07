import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-custom-justification.reducer';

export const DraftCustomJustificationDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftCustomJustificationEntity = useAppSelector(state => state.draftCustomJustification.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftCustomJustificationDetailsHeading">
          <Translate contentKey="tfbitaApp.draftCustomJustification.detail.title">DraftCustomJustification</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftCustomJustificationEntity.id}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftCustomJustification.draftReceipts">Draft Receipts</Translate>
          </dt>
          <dd>
            {draftCustomJustificationEntity.draftReceipts
              ? draftCustomJustificationEntity.draftReceipts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {draftCustomJustificationEntity.draftReceipts && i === draftCustomJustificationEntity.draftReceipts.length - 1
                      ? ''
                      : ', '}
                  </span>
                ))
              : null}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftCustomJustification.draft">Draft</Translate>
          </dt>
          <dd>{draftCustomJustificationEntity.draft ? draftCustomJustificationEntity.draft.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/draft-custom-justification" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-custom-justification/${draftCustomJustificationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftCustomJustificationDetail;
