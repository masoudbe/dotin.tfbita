import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './suggested-sanction-info.reducer';

export const SuggestedSanctionInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const suggestedSanctionInfoEntity = useAppSelector(state => state.suggestedSanctionInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="suggestedSanctionInfoDetailsHeading">
          <Translate contentKey="tfbitaApp.suggestedSanctionInfo.detail.title">SuggestedSanctionInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{suggestedSanctionInfoEntity.id}</dd>
          <dt>
            <span id="sanctionSerial">
              <Translate contentKey="tfbitaApp.suggestedSanctionInfo.sanctionSerial">Sanction Serial</Translate>
            </span>
          </dt>
          <dd>{suggestedSanctionInfoEntity.sanctionSerial}</dd>
          <dt>
            <span id="personnelCode">
              <Translate contentKey="tfbitaApp.suggestedSanctionInfo.personnelCode">Personnel Code</Translate>
            </span>
          </dt>
          <dd>{suggestedSanctionInfoEntity.personnelCode}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.suggestedSanctionInfo.draft">Draft</Translate>
          </dt>
          <dd>
            {suggestedSanctionInfoEntity.drafts
              ? suggestedSanctionInfoEntity.drafts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {suggestedSanctionInfoEntity.drafts && i === suggestedSanctionInfoEntity.drafts.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/suggested-sanction-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/suggested-sanction-info/${suggestedSanctionInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default SuggestedSanctionInfoDetail;
