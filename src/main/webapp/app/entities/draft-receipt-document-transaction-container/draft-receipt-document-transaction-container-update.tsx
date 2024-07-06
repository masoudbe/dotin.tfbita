import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDocumentTransaction } from 'app/shared/model/document-transaction.model';
import { getEntities as getDocumentTransactions } from 'app/entities/document-transaction/document-transaction.reducer';
import { IDraftDocumentTransactionContainer } from 'app/shared/model/draft-document-transaction-container.model';
import { getEntities as getDraftDocumentTransactionContainers } from 'app/entities/draft-document-transaction-container/draft-document-transaction-container.reducer';
import { IDraftReceiptDocumentTransactionContainer } from 'app/shared/model/draft-receipt-document-transaction-container.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-receipt-document-transaction-container.reducer';

export const DraftReceiptDocumentTransactionContainerUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const documentTransactions = useAppSelector(state => state.documentTransaction.entities);
  const draftDocumentTransactionContainers = useAppSelector(state => state.draftDocumentTransactionContainer.entities);
  const draftReceiptDocumentTransactionContainerEntity = useAppSelector(state => state.draftReceiptDocumentTransactionContainer.entity);
  const loading = useAppSelector(state => state.draftReceiptDocumentTransactionContainer.loading);
  const updating = useAppSelector(state => state.draftReceiptDocumentTransactionContainer.updating);
  const updateSuccess = useAppSelector(state => state.draftReceiptDocumentTransactionContainer.updateSuccess);

  const handleClose = () => {
    navigate('/draft-receipt-document-transaction-container');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDocumentTransactions({}));
    dispatch(getDraftDocumentTransactionContainers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  // eslint-disable-next-line complexity
  const saveEntity = values => {
    if (values.id !== undefined && typeof values.id !== 'number') {
      values.id = Number(values.id);
    }

    const entity = {
      ...draftReceiptDocumentTransactionContainerEntity,
      ...values,
      receiptIssueDocumentTransaction: documentTransactions.find(
        it => it.id.toString() === values.receiptIssueDocumentTransaction?.toString(),
      ),
      freightLetterStampCostDocumentTransaction: documentTransactions.find(
        it => it.id.toString() === values.freightLetterStampCostDocumentTransaction?.toString(),
      ),
      deliverDocumentTransaction: documentTransactions.find(it => it.id.toString() === values.deliverDocumentTransaction?.toString()),
      documentTransactionCanceledDeliver: documentTransactions.find(
        it => it.id.toString() === values.documentTransactionCanceledDeliver?.toString(),
      ),
      documentTransactionCanceledReceiptIssue: documentTransactions.find(
        it => it.id.toString() === values.documentTransactionCanceledReceiptIssue?.toString(),
      ),
      receiptCommissionDocumentTransactions: documentTransactions.find(
        it => it.id.toString() === values.receiptCommissionDocumentTransactions?.toString(),
      ),
      draftDocumentTransactionContainer: draftDocumentTransactionContainers.find(
        it => it.id.toString() === values.draftDocumentTransactionContainer?.toString(),
      ),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          ...draftReceiptDocumentTransactionContainerEntity,
          receiptIssueDocumentTransaction: draftReceiptDocumentTransactionContainerEntity?.receiptIssueDocumentTransaction?.id,
          freightLetterStampCostDocumentTransaction:
            draftReceiptDocumentTransactionContainerEntity?.freightLetterStampCostDocumentTransaction?.id,
          deliverDocumentTransaction: draftReceiptDocumentTransactionContainerEntity?.deliverDocumentTransaction?.id,
          documentTransactionCanceledDeliver: draftReceiptDocumentTransactionContainerEntity?.documentTransactionCanceledDeliver?.id,
          documentTransactionCanceledReceiptIssue:
            draftReceiptDocumentTransactionContainerEntity?.documentTransactionCanceledReceiptIssue?.id,
          receiptCommissionDocumentTransactions: draftReceiptDocumentTransactionContainerEntity?.receiptCommissionDocumentTransactions?.id,
          draftDocumentTransactionContainer: draftReceiptDocumentTransactionContainerEntity?.draftDocumentTransactionContainer?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="tfbitaApp.draftReceiptDocumentTransactionContainer.home.createOrEditLabel"
            data-cy="DraftReceiptDocumentTransactionContainerCreateUpdateHeading"
          >
            <Translate contentKey="tfbitaApp.draftReceiptDocumentTransactionContainer.home.createOrEditLabel">
              Create or edit a DraftReceiptDocumentTransactionContainer
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="draft-receipt-document-transaction-container-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftReceiptDocumentTransactionContainer.receiveReceiptCommission')}
                id="draft-receipt-document-transaction-container-receiveReceiptCommission"
                name="receiveReceiptCommission"
                data-cy="receiveReceiptCommission"
                check
                type="checkbox"
              />
              <ValidatedField
                id="draft-receipt-document-transaction-container-receiptIssueDocumentTransaction"
                name="receiptIssueDocumentTransaction"
                data-cy="receiptIssueDocumentTransaction"
                label={translate('tfbitaApp.draftReceiptDocumentTransactionContainer.receiptIssueDocumentTransaction')}
                type="select"
              >
                <option value="" key="0" />
                {documentTransactions
                  ? documentTransactions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-receipt-document-transaction-container-freightLetterStampCostDocumentTransaction"
                name="freightLetterStampCostDocumentTransaction"
                data-cy="freightLetterStampCostDocumentTransaction"
                label={translate('tfbitaApp.draftReceiptDocumentTransactionContainer.freightLetterStampCostDocumentTransaction')}
                type="select"
              >
                <option value="" key="0" />
                {documentTransactions
                  ? documentTransactions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-receipt-document-transaction-container-deliverDocumentTransaction"
                name="deliverDocumentTransaction"
                data-cy="deliverDocumentTransaction"
                label={translate('tfbitaApp.draftReceiptDocumentTransactionContainer.deliverDocumentTransaction')}
                type="select"
              >
                <option value="" key="0" />
                {documentTransactions
                  ? documentTransactions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-receipt-document-transaction-container-documentTransactionCanceledDeliver"
                name="documentTransactionCanceledDeliver"
                data-cy="documentTransactionCanceledDeliver"
                label={translate('tfbitaApp.draftReceiptDocumentTransactionContainer.documentTransactionCanceledDeliver')}
                type="select"
              >
                <option value="" key="0" />
                {documentTransactions
                  ? documentTransactions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-receipt-document-transaction-container-documentTransactionCanceledReceiptIssue"
                name="documentTransactionCanceledReceiptIssue"
                data-cy="documentTransactionCanceledReceiptIssue"
                label={translate('tfbitaApp.draftReceiptDocumentTransactionContainer.documentTransactionCanceledReceiptIssue')}
                type="select"
              >
                <option value="" key="0" />
                {documentTransactions
                  ? documentTransactions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-receipt-document-transaction-container-receiptCommissionDocumentTransactions"
                name="receiptCommissionDocumentTransactions"
                data-cy="receiptCommissionDocumentTransactions"
                label={translate('tfbitaApp.draftReceiptDocumentTransactionContainer.receiptCommissionDocumentTransactions')}
                type="select"
              >
                <option value="" key="0" />
                {documentTransactions
                  ? documentTransactions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="draft-receipt-document-transaction-container-draftDocumentTransactionContainer"
                name="draftDocumentTransactionContainer"
                data-cy="draftDocumentTransactionContainer"
                label={translate('tfbitaApp.draftReceiptDocumentTransactionContainer.draftDocumentTransactionContainer')}
                type="select"
              >
                <option value="" key="0" />
                {draftDocumentTransactionContainers
                  ? draftDocumentTransactionContainers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button
                tag={Link}
                id="cancel-save"
                data-cy="entityCreateCancelButton"
                to="/draft-receipt-document-transaction-container"
                replace
                color="info"
              >
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default DraftReceiptDocumentTransactionContainerUpdate;
