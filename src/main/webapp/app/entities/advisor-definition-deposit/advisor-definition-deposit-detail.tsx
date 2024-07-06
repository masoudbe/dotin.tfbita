import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './advisor-definition-deposit.reducer';

export const AdvisorDefinitionDepositDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const advisorDefinitionDepositEntity = useAppSelector(state => state.advisorDefinitionDeposit.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="advisorDefinitionDepositDetailsHeading">
          <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.detail.title">AdvisorDefinitionDeposit</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionDepositEntity.id}</dd>
          <dt>
            <span id="advisorDepositNumber">
              <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.advisorDepositNumber">Advisor Deposit Number</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionDepositEntity.advisorDepositNumber}</dd>
          <dt>
            <span id="defaultAdvisorDeposit">
              <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.defaultAdvisorDeposit">Default Advisor Deposit</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionDepositEntity.defaultAdvisorDeposit ? 'true' : 'false'}</dd>
          <dt>
            <span id="depositNum">
              <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.depositNum">Deposit Num</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionDepositEntity.depositNum}</dd>
          <dt>
            <span id="swiftCode">
              <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.swiftCode">Swift Code</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionDepositEntity.swiftCode}</dd>
          <dt>
            <span id="currencyCode">
              <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.currencyCode">Currency Code</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionDepositEntity.currencyCode}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.depositType">Deposit Type</Translate>
          </dt>
          <dd>{advisorDefinitionDepositEntity.depositType ? advisorDefinitionDepositEntity.depositType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.advisorDefinitionDeposit.advisorDefinition">Advisor Definition</Translate>
          </dt>
          <dd>{advisorDefinitionDepositEntity.advisorDefinition ? advisorDefinitionDepositEntity.advisorDefinition.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/advisor-definition-deposit" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/advisor-definition-deposit/${advisorDefinitionDepositEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AdvisorDefinitionDepositDetail;
