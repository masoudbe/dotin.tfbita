import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './credit-type-condition-info.reducer';

export const CreditTypeConditionInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const creditTypeConditionInfoEntity = useAppSelector(state => state.creditTypeConditionInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="creditTypeConditionInfoDetailsHeading">
          <Translate contentKey="tfbitaApp.creditTypeConditionInfo.detail.title">CreditTypeConditionInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionInfoEntity.id}</dd>
          <dt>
            <span id="durationFrom">
              <Translate contentKey="tfbitaApp.creditTypeConditionInfo.durationFrom">Duration From</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionInfoEntity.durationFrom}</dd>
          <dt>
            <span id="durationTo">
              <Translate contentKey="tfbitaApp.creditTypeConditionInfo.durationTo">Duration To</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionInfoEntity.durationTo}</dd>
          <dt>
            <span id="priceFrom">
              <Translate contentKey="tfbitaApp.creditTypeConditionInfo.priceFrom">Price From</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionInfoEntity.priceFrom}</dd>
          <dt>
            <span id="priceTo">
              <Translate contentKey="tfbitaApp.creditTypeConditionInfo.priceTo">Price To</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionInfoEntity.priceTo}</dd>
          <dt>
            <span id="justificationDisciplinaryTopic">
              <Translate contentKey="tfbitaApp.creditTypeConditionInfo.justificationDisciplinaryTopic">
                Justification Disciplinary Topic
              </Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionInfoEntity.justificationDisciplinaryTopic}</dd>
          <dt>
            <span id="openDraftDisciplinaryTopic">
              <Translate contentKey="tfbitaApp.creditTypeConditionInfo.openDraftDisciplinaryTopic">Open Draft Disciplinary Topic</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionInfoEntity.openDraftDisciplinaryTopic}</dd>
          <dt>
            <span id="otherCostsTopic">
              <Translate contentKey="tfbitaApp.creditTypeConditionInfo.otherCostsTopic">Other Costs Topic</Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionInfoEntity.otherCostsTopic}</dd>
          <dt>
            <span id="postTelegraphSwiftCostsTopic">
              <Translate contentKey="tfbitaApp.creditTypeConditionInfo.postTelegraphSwiftCostsTopic">
                Post Telegraph Swift Costs Topic
              </Translate>
            </span>
          </dt>
          <dd>{creditTypeConditionInfoEntity.postTelegraphSwiftCostsTopic}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.creditTypeConditionInfo.defaultCondition">Default Condition</Translate>
          </dt>
          <dd>{creditTypeConditionInfoEntity.defaultCondition ? creditTypeConditionInfoEntity.defaultCondition.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/credit-type-condition-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/credit-type-condition-info/${creditTypeConditionInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CreditTypeConditionInfoDetail;
