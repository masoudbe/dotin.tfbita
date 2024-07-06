import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-account-info.reducer';

export const DraftAccountInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftAccountInfoEntity = useAppSelector(state => state.draftAccountInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftAccountInfoDetailsHeading">
          <Translate contentKey="tfbitaApp.draftAccountInfo.detail.title">DraftAccountInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftAccountInfoEntity.id}</dd>
          <dt>
            <span id="documentReceiptDisciplinaryAccountId">
              <Translate contentKey="tfbitaApp.draftAccountInfo.documentReceiptDisciplinaryAccountId">
                Document Receipt Disciplinary Account Id
              </Translate>
            </span>
          </dt>
          <dd>{draftAccountInfoEntity.documentReceiptDisciplinaryAccountId}</dd>
          <dt>
            <span id="draftMainAccountId">
              <Translate contentKey="tfbitaApp.draftAccountInfo.draftMainAccountId">Draft Main Account Id</Translate>
            </span>
          </dt>
          <dd>{draftAccountInfoEntity.draftMainAccountId}</dd>
          <dt>
            <span id="insuranceCostAccountId">
              <Translate contentKey="tfbitaApp.draftAccountInfo.insuranceCostAccountId">Insurance Cost Account Id</Translate>
            </span>
          </dt>
          <dd>{draftAccountInfoEntity.insuranceCostAccountId}</dd>
          <dt>
            <span id="justificationDisciplinaryAccountId">
              <Translate contentKey="tfbitaApp.draftAccountInfo.justificationDisciplinaryAccountId">
                Justification Disciplinary Account Id
              </Translate>
            </span>
          </dt>
          <dd>{draftAccountInfoEntity.justificationDisciplinaryAccountId}</dd>
          <dt>
            <span id="openDraftDisciplinaryAccountId">
              <Translate contentKey="tfbitaApp.draftAccountInfo.openDraftDisciplinaryAccountId">
                Open Draft Disciplinary Account Id
              </Translate>
            </span>
          </dt>
          <dd>{draftAccountInfoEntity.openDraftDisciplinaryAccountId}</dd>
          <dt>
            <span id="otherCostsAccountId">
              <Translate contentKey="tfbitaApp.draftAccountInfo.otherCostsAccountId">Other Costs Account Id</Translate>
            </span>
          </dt>
          <dd>{draftAccountInfoEntity.otherCostsAccountId}</dd>
          <dt>
            <span id="postSwiftCostsAccountId">
              <Translate contentKey="tfbitaApp.draftAccountInfo.postSwiftCostsAccountId">Post Swift Costs Account Id</Translate>
            </span>
          </dt>
          <dd>{draftAccountInfoEntity.postSwiftCostsAccountId}</dd>
          <dt>
            <span id="amountDeductionAccountId">
              <Translate contentKey="tfbitaApp.draftAccountInfo.amountDeductionAccountId">Amount Deduction Account Id</Translate>
            </span>
          </dt>
          <dd>{draftAccountInfoEntity.amountDeductionAccountId}</dd>
        </dl>
        <Button tag={Link} to="/draft-account-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/draft-account-info/${draftAccountInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftAccountInfoDetail;
