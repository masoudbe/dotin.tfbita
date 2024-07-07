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
import { IDraft } from 'app/shared/model/draft.model';
import { getEntities as getDrafts } from 'app/entities/draft/draft.reducer';
import { IDraftDocumentTransactionContainer } from 'app/shared/model/draft-document-transaction-container.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-document-transaction-container.reducer';

export const DraftDocumentTransactionContainerUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const documentTransactions = useAppSelector(state => state.documentTransaction.entities);
  const drafts = useAppSelector(state => state.draft.entities);
  const draftDocumentTransactionContainerEntity = useAppSelector(state => state.draftDocumentTransactionContainer.entity);
  const loading = useAppSelector(state => state.draftDocumentTransactionContainer.loading);
  const updating = useAppSelector(state => state.draftDocumentTransactionContainer.updating);
  const updateSuccess = useAppSelector(state => state.draftDocumentTransactionContainer.updateSuccess);

  const handleClose = () => {
    navigate('/draft-document-transaction-container');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDocumentTransactions({}));
    dispatch(getDrafts({}));
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
      ...draftDocumentTransactionContainerEntity,
      ...values,
      issueCommissionDocumentTransaction: documentTransactions.find(
        it => it.id.toString() === values.issueCommissionDocumentTransaction?.toString(),
      ),
      paymentDocumentTransaction: documentTransactions.find(it => it.id.toString() === values.paymentDocumentTransaction?.toString()),
      settleDocumentTransaction: documentTransactions.find(it => it.id.toString() === values.settleDocumentTransaction?.toString()),
      settleExcessDocumentTransaction: documentTransactions.find(
        it => it.id.toString() === values.settleExcessDocumentTransaction?.toString(),
      ),
      commissionDeleteDraftDocumentTransaction: documentTransactions.find(
        it => it.id.toString() === values.commissionDeleteDraftDocumentTransaction?.toString(),
      ),
      commissionDraftExtendDocumentTransaction: documentTransactions.find(
        it => it.id.toString() === values.commissionDraftExtendDocumentTransaction?.toString(),
      ),
      drafts: mapIdList(values.drafts),
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
          ...draftDocumentTransactionContainerEntity,
          issueCommissionDocumentTransaction: draftDocumentTransactionContainerEntity?.issueCommissionDocumentTransaction?.id,
          paymentDocumentTransaction: draftDocumentTransactionContainerEntity?.paymentDocumentTransaction?.id,
          settleDocumentTransaction: draftDocumentTransactionContainerEntity?.settleDocumentTransaction?.id,
          settleExcessDocumentTransaction: draftDocumentTransactionContainerEntity?.settleExcessDocumentTransaction?.id,
          commissionDeleteDraftDocumentTransaction: draftDocumentTransactionContainerEntity?.commissionDeleteDraftDocumentTransaction?.id,
          commissionDraftExtendDocumentTransaction: draftDocumentTransactionContainerEntity?.commissionDraftExtendDocumentTransaction?.id,
          drafts: draftDocumentTransactionContainerEntity?.drafts?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="tfbitaApp.draftDocumentTransactionContainer.home.createOrEditLabel"
            data-cy="DraftDocumentTransactionContainerCreateUpdateHeading"
          >
            <Translate contentKey="tfbitaApp.draftDocumentTransactionContainer.home.createOrEditLabel">
              Create or edit a DraftDocumentTransactionContainer
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
                  id="draft-document-transaction-container-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                id="draft-document-transaction-container-issueCommissionDocumentTransaction"
                name="issueCommissionDocumentTransaction"
                data-cy="issueCommissionDocumentTransaction"
                label={translate('tfbitaApp.draftDocumentTransactionContainer.issueCommissionDocumentTransaction')}
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
                id="draft-document-transaction-container-paymentDocumentTransaction"
                name="paymentDocumentTransaction"
                data-cy="paymentDocumentTransaction"
                label={translate('tfbitaApp.draftDocumentTransactionContainer.paymentDocumentTransaction')}
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
                id="draft-document-transaction-container-settleDocumentTransaction"
                name="settleDocumentTransaction"
                data-cy="settleDocumentTransaction"
                label={translate('tfbitaApp.draftDocumentTransactionContainer.settleDocumentTransaction')}
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
                id="draft-document-transaction-container-settleExcessDocumentTransaction"
                name="settleExcessDocumentTransaction"
                data-cy="settleExcessDocumentTransaction"
                label={translate('tfbitaApp.draftDocumentTransactionContainer.settleExcessDocumentTransaction')}
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
                id="draft-document-transaction-container-commissionDeleteDraftDocumentTransaction"
                name="commissionDeleteDraftDocumentTransaction"
                data-cy="commissionDeleteDraftDocumentTransaction"
                label={translate('tfbitaApp.draftDocumentTransactionContainer.commissionDeleteDraftDocumentTransaction')}
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
                id="draft-document-transaction-container-commissionDraftExtendDocumentTransaction"
                name="commissionDraftExtendDocumentTransaction"
                data-cy="commissionDraftExtendDocumentTransaction"
                label={translate('tfbitaApp.draftDocumentTransactionContainer.commissionDraftExtendDocumentTransaction')}
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
                label={translate('tfbitaApp.draftDocumentTransactionContainer.draft')}
                id="draft-document-transaction-container-draft"
                data-cy="draft"
                type="select"
                multiple
                name="drafts"
              >
                <option value="" key="0" />
                {drafts
                  ? drafts.map(otherEntity => (
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
                to="/draft-document-transaction-container"
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

export default DraftDocumentTransactionContainerUpdate;
