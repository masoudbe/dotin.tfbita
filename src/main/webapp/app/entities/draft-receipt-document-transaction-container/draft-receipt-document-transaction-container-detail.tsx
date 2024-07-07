import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './draft-receipt-document-transaction-container.reducer';

export const DraftReceiptDocumentTransactionContainerDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const draftReceiptDocumentTransactionContainerEntity = useAppSelector(state => state.draftReceiptDocumentTransactionContainer.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="draftReceiptDocumentTransactionContainerDetailsHeading">
          <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.detail.title">
            DraftReceiptDocumentTransactionContainer
          </Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{draftReceiptDocumentTransactionContainerEntity.id}</dd>
          <dt>
            <span id="receiveReceiptCommission">
              <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.receiveReceiptCommission">
                Receive Receipt Commission
              </Translate>
            </span>
          </dt>
          <dd>{draftReceiptDocumentTransactionContainerEntity.receiveReceiptCommission ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.receiptIssueDocumentTransaction">
              Receipt Issue Document Transaction
            </Translate>
          </dt>
          <dd>
            {draftReceiptDocumentTransactionContainerEntity.receiptIssueDocumentTransaction
              ? draftReceiptDocumentTransactionContainerEntity.receiptIssueDocumentTransaction.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.freightLetterStampCostDocumentTransaction">
              Freight Letter Stamp Cost Document Transaction
            </Translate>
          </dt>
          <dd>
            {draftReceiptDocumentTransactionContainerEntity.freightLetterStampCostDocumentTransaction
              ? draftReceiptDocumentTransactionContainerEntity.freightLetterStampCostDocumentTransaction.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.deliverDocumentTransaction">
              Deliver Document Transaction
            </Translate>
          </dt>
          <dd>
            {draftReceiptDocumentTransactionContainerEntity.deliverDocumentTransaction
              ? draftReceiptDocumentTransactionContainerEntity.deliverDocumentTransaction.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.documentTransactionCanceledDeliver">
              Document Transaction Canceled Deliver
            </Translate>
          </dt>
          <dd>
            {draftReceiptDocumentTransactionContainerEntity.documentTransactionCanceledDeliver
              ? draftReceiptDocumentTransactionContainerEntity.documentTransactionCanceledDeliver.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.documentTransactionCanceledReceiptIssue">
              Document Transaction Canceled Receipt Issue
            </Translate>
          </dt>
          <dd>
            {draftReceiptDocumentTransactionContainerEntity.documentTransactionCanceledReceiptIssue
              ? draftReceiptDocumentTransactionContainerEntity.documentTransactionCanceledReceiptIssue.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.receiptCommissionDocumentTransactions">
              Receipt Commission Document Transactions
            </Translate>
          </dt>
          <dd>
            {draftReceiptDocumentTransactionContainerEntity.receiptCommissionDocumentTransactions
              ? draftReceiptDocumentTransactionContainerEntity.receiptCommissionDocumentTransactions.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.draftDocumentTransactionContainer">
              Draft Document Transaction Container
            </Translate>
          </dt>
          <dd>
            {draftReceiptDocumentTransactionContainerEntity.draftDocumentTransactionContainer
              ? draftReceiptDocumentTransactionContainerEntity.draftDocumentTransactionContainer.id
              : ''}
          </dd>
        </dl>
        <Button tag={Link} to="/draft-receipt-document-transaction-container" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button
          tag={Link}
          to={`/draft-receipt-document-transaction-container/${draftReceiptDocumentTransactionContainerEntity.id}/edit`}
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

export default DraftReceiptDocumentTransactionContainerDetail;
