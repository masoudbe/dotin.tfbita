import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDraftDocumentTransactionContainer } from 'app/shared/model/draft-document-transaction-container.model';
import { getEntities as getDraftDocumentTransactionContainers } from 'app/entities/draft-document-transaction-container/draft-document-transaction-container.reducer';
import { ICustomJustification } from 'app/shared/model/custom-justification.model';
import { getEntities as getCustomJustifications } from 'app/entities/custom-justification/custom-justification.reducer';
import { IDocumentTransaction } from 'app/shared/model/document-transaction.model';
import { getEntity, updateEntity, createEntity, reset } from './document-transaction.reducer';

export const DocumentTransactionUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const draftDocumentTransactionContainers = useAppSelector(state => state.draftDocumentTransactionContainer.entities);
  const customJustifications = useAppSelector(state => state.customJustification.entities);
  const documentTransactionEntity = useAppSelector(state => state.documentTransaction.entity);
  const loading = useAppSelector(state => state.documentTransaction.loading);
  const updating = useAppSelector(state => state.documentTransaction.updating);
  const updateSuccess = useAppSelector(state => state.documentTransaction.updateSuccess);

  const handleClose = () => {
    navigate('/document-transaction');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDraftDocumentTransactionContainers({}));
    dispatch(getCustomJustifications({}));
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
      ...documentTransactionEntity,
      ...values,
      otherDocumentTransactionsContainer: draftDocumentTransactionContainers.find(
        it => it.id.toString() === values.otherDocumentTransactionsContainer?.toString(),
      ),
      canceledJustificationDocumentContainer: draftDocumentTransactionContainers.find(
        it => it.id.toString() === values.canceledJustificationDocumentContainer?.toString(),
      ),
      justificationDocumentTransactionsContainer: draftDocumentTransactionContainers.find(
        it => it.id.toString() === values.justificationDocumentTransactionsContainer?.toString(),
      ),
      receivedCommisionsContainer: draftDocumentTransactionContainers.find(
        it => it.id.toString() === values.receivedCommisionsContainer?.toString(),
      ),
      canceledDocumentTransactionsContainer: draftDocumentTransactionContainers.find(
        it => it.id.toString() === values.canceledDocumentTransactionsContainer?.toString(),
      ),
      returnedDefaultCurrencyCostsContainer: draftDocumentTransactionContainers.find(
        it => it.id.toString() === values.returnedDefaultCurrencyCostsContainer?.toString(),
      ),
      defaultCurrencyCostsDocumentContainer: draftDocumentTransactionContainers.find(
        it => it.id.toString() === values.defaultCurrencyCostsDocumentContainer?.toString(),
      ),
      customJustifications: mapIdList(values.customJustifications),
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
          ...documentTransactionEntity,
          otherDocumentTransactionsContainer: documentTransactionEntity?.otherDocumentTransactionsContainer?.id,
          canceledJustificationDocumentContainer: documentTransactionEntity?.canceledJustificationDocumentContainer?.id,
          justificationDocumentTransactionsContainer: documentTransactionEntity?.justificationDocumentTransactionsContainer?.id,
          receivedCommisionsContainer: documentTransactionEntity?.receivedCommisionsContainer?.id,
          canceledDocumentTransactionsContainer: documentTransactionEntity?.canceledDocumentTransactionsContainer?.id,
          returnedDefaultCurrencyCostsContainer: documentTransactionEntity?.returnedDefaultCurrencyCostsContainer?.id,
          defaultCurrencyCostsDocumentContainer: documentTransactionEntity?.defaultCurrencyCostsDocumentContainer?.id,
          customJustifications: documentTransactionEntity?.customJustifications?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.documentTransaction.home.createOrEditLabel" data-cy="DocumentTransactionCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.documentTransaction.home.createOrEditLabel">Create or edit a DocumentTransaction</Translate>
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
                  id="document-transaction-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.documentTransaction.currencyExchangeCode')}
                id="document-transaction-currencyExchangeCode"
                name="currencyExchangeCode"
                data-cy="currencyExchangeCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.documentTransaction.transactionCode')}
                id="document-transaction-transactionCode"
                name="transactionCode"
                data-cy="transactionCode"
                type="text"
              />
              <ValidatedField
                id="document-transaction-otherDocumentTransactionsContainer"
                name="otherDocumentTransactionsContainer"
                data-cy="otherDocumentTransactionsContainer"
                label={translate('tfbitaApp.documentTransaction.otherDocumentTransactionsContainer')}
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
              <ValidatedField
                id="document-transaction-canceledJustificationDocumentContainer"
                name="canceledJustificationDocumentContainer"
                data-cy="canceledJustificationDocumentContainer"
                label={translate('tfbitaApp.documentTransaction.canceledJustificationDocumentContainer')}
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
              <ValidatedField
                id="document-transaction-justificationDocumentTransactionsContainer"
                name="justificationDocumentTransactionsContainer"
                data-cy="justificationDocumentTransactionsContainer"
                label={translate('tfbitaApp.documentTransaction.justificationDocumentTransactionsContainer')}
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
              <ValidatedField
                id="document-transaction-receivedCommisionsContainer"
                name="receivedCommisionsContainer"
                data-cy="receivedCommisionsContainer"
                label={translate('tfbitaApp.documentTransaction.receivedCommisionsContainer')}
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
              <ValidatedField
                id="document-transaction-canceledDocumentTransactionsContainer"
                name="canceledDocumentTransactionsContainer"
                data-cy="canceledDocumentTransactionsContainer"
                label={translate('tfbitaApp.documentTransaction.canceledDocumentTransactionsContainer')}
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
              <ValidatedField
                id="document-transaction-returnedDefaultCurrencyCostsContainer"
                name="returnedDefaultCurrencyCostsContainer"
                data-cy="returnedDefaultCurrencyCostsContainer"
                label={translate('tfbitaApp.documentTransaction.returnedDefaultCurrencyCostsContainer')}
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
              <ValidatedField
                id="document-transaction-defaultCurrencyCostsDocumentContainer"
                name="defaultCurrencyCostsDocumentContainer"
                data-cy="defaultCurrencyCostsDocumentContainer"
                label={translate('tfbitaApp.documentTransaction.defaultCurrencyCostsDocumentContainer')}
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
              <ValidatedField
                label={translate('tfbitaApp.documentTransaction.customJustification')}
                id="document-transaction-customJustification"
                data-cy="customJustification"
                type="select"
                multiple
                name="customJustifications"
              >
                <option value="" key="0" />
                {customJustifications
                  ? customJustifications.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/document-transaction" replace color="info">
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

export default DocumentTransactionUpdate;
