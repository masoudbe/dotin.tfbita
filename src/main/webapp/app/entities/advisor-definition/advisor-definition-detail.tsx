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
            <span id="defaultCurrencyCode">
              <Translate contentKey="tfbitaApp.advisorDefinition.defaultCurrencyCode">Default Currency Code</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.defaultCurrencyCode}</dd>
          <dt>
            <span id="currenciesCodes">
              <Translate contentKey="tfbitaApp.advisorDefinition.currenciesCodes">Currencies Codes</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.currenciesCodes}</dd>
          <dt>
            <span id="countryCode">
              <Translate contentKey="tfbitaApp.advisorDefinition.countryCode">Country Code</Translate>
            </span>
          </dt>
          <dd>{advisorDefinitionEntity.countryCode}</dd>
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
            <Translate contentKey="tfbitaApp.advisorDefinition.additionalBrokerInformation">Additional Broker Information</Translate>
          </dt>
          <dd>{advisorDefinitionEntity.additionalBrokerInformation ? advisorDefinitionEntity.additionalBrokerInformation.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.advisorDefinition.defaultVostroDeposit">Default Vostro Deposit</Translate>
          </dt>
          <dd>{advisorDefinitionEntity.defaultVostroDeposit ? advisorDefinitionEntity.defaultVostroDeposit.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.advisorDefinition.defaultNostroDeposit">Default Nostro Deposit</Translate>
          </dt>
          <dd>{advisorDefinitionEntity.defaultNostroDeposit ? advisorDefinitionEntity.defaultNostroDeposit.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.advisorDefinition.receiveMethod">Receive Method</Translate>
          </dt>
          <dd>{advisorDefinitionEntity.receiveMethod ? advisorDefinitionEntity.receiveMethod.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.advisorDefinition.payMethod">Pay Method</Translate>
          </dt>
          <dd>{advisorDefinitionEntity.payMethod ? advisorDefinitionEntity.payMethod.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.advisorDefinition.swiftBic">Swift Bic</Translate>
          </dt>
          <dd>{advisorDefinitionEntity.swiftBic ? advisorDefinitionEntity.swiftBic.id : ''}</dd>
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
