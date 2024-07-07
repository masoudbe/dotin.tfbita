import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './additional-broker-information.reducer';

export const AdditionalBrokerInformationDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const additionalBrokerInformationEntity = useAppSelector(state => state.additionalBrokerInformation.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="additionalBrokerInformationDetailsHeading">
          <Translate contentKey="tfbitaApp.additionalBrokerInformation.detail.title">AdditionalBrokerInformation</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.id}</dd>
          <dt>
            <span id="dateOfStartRelation">
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.dateOfStartRelation">Date Of Start Relation</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.dateOfStartRelation}</dd>
          <dt>
            <span id="creditLimit">
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.creditLimit">Credit Limit</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.creditLimit}</dd>
          <dt>
            <span id="revokedDate">
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.revokedDate">Revoked Date</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.revokedDate}</dd>
          <dt>
            <span id="revokedNote">
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.revokedNote">Revoked Note</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.revokedNote}</dd>
          <dt>
            <span id="confidential">
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.confidential">Confidential</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.confidential}</dd>
          <dt>
            <span id="otherBrokerServices">
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.otherBrokerServices">Other Broker Services</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.otherBrokerServices}</dd>
          <dt>
            <span id="sanctionedStatus">
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.sanctionedStatus">Sanctioned Status</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.sanctionedStatus}</dd>
          <dt>
            <span id="considerations">
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.considerations">Considerations</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.considerations}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.description">Description</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.description}</dd>
          <dt>
            <span id="waysOfCommunication">
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.waysOfCommunication">Ways Of Communication</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.waysOfCommunication}</dd>
          <dt>
            <span id="servicesAvailable">
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.servicesAvailable">Services Available</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.servicesAvailable}</dd>
          <dt>
            <span id="customerAcceptancePolicy">
              <Translate contentKey="tfbitaApp.additionalBrokerInformation.customerAcceptancePolicy">Customer Acceptance Policy</Translate>
            </span>
          </dt>
          <dd>{additionalBrokerInformationEntity.customerAcceptancePolicy}</dd>
        </dl>
        <Button tag={Link} to="/additional-broker-information" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/additional-broker-information/${additionalBrokerInformationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AdditionalBrokerInformationDetail;
