import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-type.reducer';

export const DraftTypeDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftTypeEntity = useAppSelector(state => state.draftType.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftTypeDetailsHeading">
          <Translate contentKey="tfbitaApp.draftType.detail.title">DraftType</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.id}</dd>
          <dt>
            <span id="alarmTime">
              <Translate contentKey="tfbitaApp.draftType.alarmTime">Alarm Time</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.alarmTime}</dd>
          <dt>
            <span id="code">
              <Translate contentKey="tfbitaApp.draftType.code">Code</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.code}</dd>
          <dt>
            <span id="disableDate">
              <Translate contentKey="tfbitaApp.draftType.disableDate">Disable Date</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.disableDate}</dd>
          <dt>
            <span id="duration">
              <Translate contentKey="tfbitaApp.draftType.duration">Duration</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.duration}</dd>
          <dt>
            <span id="hasAssurance">
              <Translate contentKey="tfbitaApp.draftType.hasAssurance">Has Assurance</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.hasAssurance ? 'true' : 'false'}</dd>
          <dt>
            <span id="hasSanction">
              <Translate contentKey="tfbitaApp.draftType.hasSanction">Has Sanction</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.hasSanction ? 'true' : 'false'}</dd>
          <dt>
            <span id="latestCreditSerial">
              <Translate contentKey="tfbitaApp.draftType.latestCreditSerial">Latest Credit Serial</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.latestCreditSerial}</dd>
          <dt>
            <span id="name">
              <Translate contentKey="tfbitaApp.draftType.name">Name</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.name}</dd>
          <dt>
            <span id="portal">
              <Translate contentKey="tfbitaApp.draftType.portal">Portal</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.portal ? 'true' : 'false'}</dd>
          <dt>
            <span id="usable">
              <Translate contentKey="tfbitaApp.draftType.usable">Usable</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.usable ? 'true' : 'false'}</dd>
          <dt>
            <span id="currenciesCodes">
              <Translate contentKey="tfbitaApp.draftType.currenciesCodes">Currencies Codes</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.currenciesCodes}</dd>
          <dt>
            <span id="defaultCurrencyCode">
              <Translate contentKey="tfbitaApp.draftType.defaultCurrencyCode">Default Currency Code</Translate>
            </span>
          </dt>
          <dd>{draftTypeEntity.defaultCurrencyCode}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftType.type">Type</Translate>
          </dt>
          <dd>{draftTypeEntity.type ? draftTypeEntity.type.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftType.secondaryType">Secondary Type</Translate>
          </dt>
          <dd>{draftTypeEntity.secondaryType ? draftTypeEntity.secondaryType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftType.division">Division</Translate>
          </dt>
          <dd>{draftTypeEntity.division ? draftTypeEntity.division.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftType.topicInfo">Topic Info</Translate>
          </dt>
          <dd>{draftTypeEntity.topicInfo ? draftTypeEntity.topicInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftType.conditionInfo">Condition Info</Translate>
          </dt>
          <dd>{draftTypeEntity.conditionInfo ? draftTypeEntity.conditionInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftType.accountInfo">Account Info</Translate>
          </dt>
          <dd>{draftTypeEntity.accountInfo ? draftTypeEntity.accountInfo.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftType.requestType">Request Type</Translate>
          </dt>
          <dd>{draftTypeEntity.requestType ? draftTypeEntity.requestType.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftType.acceptableProductTypes">Acceptable Product Types</Translate>
          </dt>
          <dd>{draftTypeEntity.acceptableProductTypes ? draftTypeEntity.acceptableProductTypes.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftType.userGroups">User Groups</Translate>
          </dt>
          <dd>
            {draftTypeEntity.userGroups
              ? draftTypeEntity.userGroups.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {draftTypeEntity.userGroups && i === draftTypeEntity.userGroups.length - 1 ? '' : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/draft-type" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-type/${draftTypeEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftTypeDetail;
