import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './advisor-definition.reducer';

export const AdvisorDefinitionDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const advisorDefinitionEntity = useAppSelector(state => state.advisorDefinition.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="advisorDefinitionDetailsHeading">
          <Translate contentKey="tfbitaApp.advisorDefinition.detail.title">AdvisorDefinition</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.id}</dd>
          <dt>
            <span id="caption">
              <Translate contentKey="tfbitaApp.advisorDefinition.caption">Caption</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.caption}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="tfbitaApp.advisorDefinition.code">Code</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.code}</dd>
          <dt>
            <span id="countryIsoCode">
              <Translate contentKey="tfbitaApp.advisorDefinition.countryIsoCode">Country Iso Code</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.countryIsoCode}</dd>
          <dt>
            <span id="depositNum">
              <Translate contentKey="tfbitaApp.advisorDefinition.depositNum">Deposit Num</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.depositNum}</dd>
          <dt>
            <span id="swiftCode">
              <Translate contentKey="tfbitaApp.advisorDefinition.swiftCode">Swift Code</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.swiftCode}</dd>
          <dt>
            <span id="creditDate">
              <Translate contentKey="tfbitaApp.advisorDefinition.creditDate">Credit Date</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.creditDate}</dd>
          <dt>
            <span id="bankCode">
              <Translate contentKey="tfbitaApp.advisorDefinition.bankCode">Bank Code</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.bankCode}</dd>
          <dt>
            <span id="branchCode">
              <Translate contentKey="tfbitaApp.advisorDefinition.branchCode">Branch Code</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.branchCode}</dd>
          <dt>
            <span id="defaultCurrencyCode">
              <Translate contentKey="tfbitaApp.advisorDefinition.defaultCurrencyCode">Default Currency Code</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.defaultCurrencyCode}</dd>
          <dt>
            <span id="countryCode">
              <Translate contentKey="tfbitaApp.advisorDefinition.countryCode">Country Code</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.countryCode}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.advisorDefinition.advisingBank">Advising Bank</Translate>
          </dt>
          <dd>{advisorDefinitionEntity.advisingBank ? advisorDefinitionEntity.advisingBank.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.advisorDefinition.interfaceAdvisingBank">Interface Advising Bank</Translate>
          </dt>
          <dd>{advisorDefinitionEntity.interfaceAdvisingBank ? advisorDefinitionEntity.interfaceAdvisingBank.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.advisorDefinition.coveringBank">Covering Bank</Translate>
          </dt>
          <dd>{advisorDefinitionEntity.coveringBank ? advisorDefinitionEntity.coveringBank.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/advisor-definition" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/advisor-definition/${advisorDefinitionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AdvisorDefinitionDetail;
