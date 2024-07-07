import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-document-transaction-container.reducer';

export const DraftDocumentTransactionContainerDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftDocumentTransactionContainerEntity = useAppSelector(state => state.draftDocumentTransactionContainer.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftDocumentTransactionContainerDetailsHeading">
          <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.detail.title">DraftDocumentTransactionContainer</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftDocumentTransactionContainerEntity.id}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.issueCommissionDocumentTransaction">
              Issue Commission Document Transaction
            </Translate>
          </dt>
          <dd>
            {draftDocumentTransactionContainerEntity.issueCommissionDocumentTransaction
              ? draftDocumentTransactionContainerEntity.issueCommissionDocumentTransaction.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.paymentDocumentTransaction">
              Payment Document Transaction
            </Translate>
          </dt>
          <dd>
            {draftDocumentTransactionContainerEntity.paymentDocumentTransaction
              ? draftDocumentTransactionContainerEntity.paymentDocumentTransaction.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.settleDocumentTransaction">
              Settle Document Transaction
            </Translate>
          </dt>
          <dd>
            {draftDocumentTransactionContainerEntity.settleDocumentTransaction
              ? draftDocumentTransactionContainerEntity.settleDocumentTransaction.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.settleExcessDocumentTransaction">
              Settle Excess Document Transaction
            </Translate>
          </dt>
          <dd>
            {draftDocumentTransactionContainerEntity.settleExcessDocumentTransaction
              ? draftDocumentTransactionContainerEntity.settleExcessDocumentTransaction.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.commissionDeleteDraftDocumentTransaction">
              Commission Delete Draft Document Transaction
            </Translate>
          </dt>
          <dd>
            {draftDocumentTransactionContainerEntity.commissionDeleteDraftDocumentTransaction
              ? draftDocumentTransactionContainerEntity.commissionDeleteDraftDocumentTransaction.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.commissionDraftExtendDocumentTransaction">
              Commission Draft Extend Document Transaction
            </Translate>
          </dt>
          <dd>
            {draftDocumentTransactionContainerEntity.commissionDraftExtendDocumentTransaction
              ? draftDocumentTransactionContainerEntity.commissionDraftExtendDocumentTransaction.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.draft">Draft</Translate>
          </dt>
          <dd>
            {draftDocumentTransactionContainerEntity.drafts
              ? draftDocumentTransactionContainerEntity.drafts.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {draftDocumentTransactionContainerEntity.drafts && i === draftDocumentTransactionContainerEntity.drafts.length - 1
                      ? ''
                      : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/draft-document-transaction-container" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button
          tag={Link}
          to={`/draft-document-transaction-container/${draftDocumentTransactionContainerEntity.id}/edit`}
          replace
          color="primary"
        >
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DraftDocumentTransactionContainerDetail;
