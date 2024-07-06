import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-type-topic-info.reducer';

export const DraftTypeTopicInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftTypeTopicInfoEntity = useAppSelector(state => state.draftTypeTopicInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftTypeTopicInfoDetailsHeading">
          <Translate contentKey="tfbitaApp.draftTypeTopicInfo.detail.title">DraftTypeTopicInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftTypeTopicInfoEntity.id}</dd>
          <dt>
            <span id="currencySellCommissionTopic">
              <Translate contentKey="tfbitaApp.draftTypeTopicInfo.currencySellCommissionTopic">Currency Sell Commission Topic</Translate>
            </span>
          </dt>
          <dd>{draftTypeTopicInfoEntity.currencySellCommissionTopic}</dd>
          <dt>
            <span id="documentReceiptDisciplinaryTopic">
              <Translate contentKey="tfbitaApp.draftTypeTopicInfo.documentReceiptDisciplinaryTopic">
                Document Receipt Disciplinary Topic
              </Translate>
            </span>
          </dt>
          <dd>{draftTypeTopicInfoEntity.documentReceiptDisciplinaryTopic}</dd>
          <dt>
            <span id="draftMainTopic">
              <Translate contentKey="tfbitaApp.draftTypeTopicInfo.draftMainTopic">Draft Main Topic</Translate>
            </span>
          </dt>
          <dd>{draftTypeTopicInfoEntity.draftMainTopic}</dd>
          <dt>
            <span id="insuranceCostTopic">
              <Translate contentKey="tfbitaApp.draftTypeTopicInfo.insuranceCostTopic">Insurance Cost Topic</Translate>
            </span>
          </dt>
          <dd>{draftTypeTopicInfoEntity.insuranceCostTopic}</dd>
          <dt>
            <span id="justificationDisciplinaryTopic">
              <Translate contentKey="tfbitaApp.draftTypeTopicInfo.justificationDisciplinaryTopic">
                Justification Disciplinary Topic
              </Translate>
            </span>
          </dt>
          <dd>{draftTypeTopicInfoEntity.justificationDisciplinaryTopic}</dd>
          <dt>
            <span id="openDraftDisciplinaryTopic">
              <Translate contentKey="tfbitaApp.draftTypeTopicInfo.openDraftDisciplinaryTopic">Open Draft Disciplinary Topic</Translate>
            </span>
          </dt>
          <dd>{draftTypeTopicInfoEntity.openDraftDisciplinaryTopic}</dd>
          <dt>
            <span id="otherCostsTopic">
              <Translate contentKey="tfbitaApp.draftTypeTopicInfo.otherCostsTopic">Other Costs Topic</Translate>
            </span>
          </dt>
          <dd>{draftTypeTopicInfoEntity.otherCostsTopic}</dd>
          <dt>
            <span id="postTelegraphSwiftCostsTopic">
              <Translate contentKey="tfbitaApp.draftTypeTopicInfo.postTelegraphSwiftCostsTopic">Post Telegraph Swift Costs Topic</Translate>
            </span>
          </dt>
          <dd>{draftTypeTopicInfoEntity.postTelegraphSwiftCostsTopic}</dd>
        </dl>
        <Button tag={Link} to="/draft-type-topic-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-type-topic-info/${draftTypeTopicInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftTypeTopicInfoDetail;
