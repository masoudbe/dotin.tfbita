import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-status-info.reducer';

export const DraftStatusInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftStatusInfoEntity = useAppSelector(state => state.draftStatusInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftStatusInfoDetailsHeading">
          <Translate contentKey="tfbitaApp.draftStatusInfo.detail.title">DraftStatusInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftStatusInfoEntity.id}</dd>
          <dt>
            <span id="approved">
              <Translate contentKey="tfbitaApp.draftStatusInfo.approved">Approved</Translate>
            </span>
          </dt>
          <dd>{draftStatusInfoEntity.approved ? 'true' : 'false'}</dd>
          <dt>
            <span id="draftRequestAccepted">
              <Translate contentKey="tfbitaApp.draftStatusInfo.draftRequestAccepted">Draft Request Accepted</Translate>
            </span>
          </dt>
          <dd>{draftStatusInfoEntity.draftRequestAccepted ? 'true' : 'false'}</dd>
          <dt>
            <span id="insuranceCostPaid">
              <Translate contentKey="tfbitaApp.draftStatusInfo.insuranceCostPaid">Insurance Cost Paid</Translate>
            </span>
          </dt>
          <dd>{draftStatusInfoEntity.insuranceCostPaid ? 'true' : 'false'}</dd>
          <dt>
            <span id="issued">
              <Translate contentKey="tfbitaApp.draftStatusInfo.issued">Issued</Translate>
            </span>
          </dt>
          <dd>{draftStatusInfoEntity.issued ? 'true' : 'false'}</dd>
          <dt>
            <span id="otherCostsPaid">
              <Translate contentKey="tfbitaApp.draftStatusInfo.otherCostsPaid">Other Costs Paid</Translate>
            </span>
          </dt>
          <dd>{draftStatusInfoEntity.otherCostsPaid ? 'true' : 'false'}</dd>
          <dt>
            <span id="postSwiftCostPaied">
              <Translate contentKey="tfbitaApp.draftStatusInfo.postSwiftCostPaied">Post Swift Cost Paied</Translate>
            </span>
          </dt>
          <dd>{draftStatusInfoEntity.postSwiftCostPaied ? 'true' : 'false'}</dd>
          <dt>
            <span id="rejectDescription">
              <Translate contentKey="tfbitaApp.draftStatusInfo.rejectDescription">Reject Description</Translate>
            </span>
          </dt>
          <dd>{draftStatusInfoEntity.rejectDescription}</dd>
          <dt>
            <span id="remainAmount">
              <Translate contentKey="tfbitaApp.draftStatusInfo.remainAmount">Remain Amount</Translate>
            </span>
          </dt>
          <dd>{draftStatusInfoEntity.remainAmount}</dd>
          <dt>
            <span id="stampCostPaid">
              <Translate contentKey="tfbitaApp.draftStatusInfo.stampCostPaid">Stamp Cost Paid</Translate>
            </span>
          </dt>
          <dd>{draftStatusInfoEntity.stampCostPaid ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftStatusInfo.statusInfo">Status Info</Translate>
          </dt>
          <dd>{draftStatusInfoEntity.statusInfo ? draftStatusInfoEntity.statusInfo.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/draft-status-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-status-info/${draftStatusInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftStatusInfoDetail;
