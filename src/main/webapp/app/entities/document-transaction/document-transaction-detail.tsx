import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './document-transaction.reducer';

export const DocumentTransactionDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const documentTransactionEntity = useAppSelector(state => state.documentTransaction.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="documentTransactionDetailsHeading">
          <Translate contentKey="tfbitaApp.documentTransaction.detail.title">DocumentTransaction</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{documentTransactionEntity.id}</dd>
          <dt>
            <span id="currencyExchangeCode">
              <Translate contentKey="tfbitaApp.documentTransaction.currencyExchangeCode">Currency Exchange Code</Translate>
            </span>
          </dt>
          <dd>{documentTransactionEntity.currencyExchangeCode}</dd>
          <dt>
            <span id="transactionCode">
              <Translate contentKey="tfbitaApp.documentTransaction.transactionCode">Transaction Code</Translate>
            </span>
          </dt>
          <dd>{documentTransactionEntity.transactionCode}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.documentTransaction.otherDocumentTransactionsContainer">
              Other Document Transactions Container
            </Translate>
          </dt>
          <dd>
            {documentTransactionEntity.otherDocumentTransactionsContainer
              ? documentTransactionEntity.otherDocumentTransactionsContainer.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.documentTransaction.canceledJustificationDocumentContainer">
              Canceled Justification Document Container
            </Translate>
          </dt>
          <dd>
            {documentTransactionEntity.canceledJustificationDocumentContainer
              ? documentTransactionEntity.canceledJustificationDocumentContainer.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.documentTransaction.justificationDocumentTransactionsContainer">
              Justification Document Transactions Container
            </Translate>
          </dt>
          <dd>
            {documentTransactionEntity.justificationDocumentTransactionsContainer
              ? documentTransactionEntity.justificationDocumentTransactionsContainer.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.documentTransaction.receivedCommisionsContainer">Received Commisions Container</Translate>
          </dt>
          <dd>{documentTransactionEntity.receivedCommisionsContainer ? documentTransactionEntity.receivedCommisionsContainer.id : ''}</dd>
          <dt>
            <Translate contentKey="tfbitaApp.documentTransaction.canceledDocumentTransactionsContainer">
              Canceled Document Transactions Container
            </Translate>
          </dt>
          <dd>
            {documentTransactionEntity.canceledDocumentTransactionsContainer
              ? documentTransactionEntity.canceledDocumentTransactionsContainer.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.documentTransaction.returnedDefaultCurrencyCostsContainer">
              Returned Default Currency Costs Container
            </Translate>
          </dt>
          <dd>
            {documentTransactionEntity.returnedDefaultCurrencyCostsContainer
              ? documentTransactionEntity.returnedDefaultCurrencyCostsContainer.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.documentTransaction.defaultCurrencyCostsDocumentContainer">
              Default Currency Costs Document Container
            </Translate>
          </dt>
          <dd>
            {documentTransactionEntity.defaultCurrencyCostsDocumentContainer
              ? documentTransactionEntity.defaultCurrencyCostsDocumentContainer.id
              : ''}
          </dd>
          <dt>
            <Translate contentKey="tfbitaApp.documentTransaction.customJustification">Custom Justification</Translate>
          </dt>
          <dd>
            {documentTransactionEntity.customJustifications
              ? documentTransactionEntity.customJustifications.map((val, i) => (
                  <span key={val.id}>
                    <a>{val.id}</a>
                    {documentTransactionEntity.customJustifications && i === documentTransactionEntity.customJustifications.length - 1
                      ? ''
                      : ', '}
                  </span>
                ))
              : null}
          </dd>
        </dl>
        <Button tag={Link} to="/document-transaction" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/document-transaction/${documentTransactionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DocumentTransactionDetail;
