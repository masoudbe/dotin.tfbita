import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDraft } from 'app/shared/model/draft.model';
import { getEntities as getDrafts } from 'app/entities/draft/draft.reducer';
import { IDraftCustomJustification } from 'app/shared/model/draft-custom-justification.model';
import { getEntities as getDraftCustomJustifications } from 'app/entities/draft-custom-justification/draft-custom-justification.reducer';
import { IDraftReceipt } from 'app/shared/model/draft-receipt.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-receipt.reducer';

export const DraftReceiptUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const drafts = useAppSelector(state => state.draft.entities);
  const draftCustomJustifications = useAppSelector(state => state.draftCustomJustification.entities);
  const draftReceiptEntity = useAppSelector(state => state.draftReceipt.entity);
  const loading = useAppSelector(state => state.draftReceipt.loading);
  const updating = useAppSelector(state => state.draftReceipt.updating);
  const updateSuccess = useAppSelector(state => state.draftReceipt.updateSuccess);

  const handleClose = () => {
    navigate('/draft-receipt');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDrafts({}));
    dispatch(getDraftCustomJustifications({}));
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
    if (values.deliverDuration !== undefined && typeof values.deliverDuration !== 'number') {
      values.deliverDuration = Number(values.deliverDuration);
    }
    if (values.freightLetterStampCost !== undefined && typeof values.freightLetterStampCost !== 'number') {
      values.freightLetterStampCost = Number(values.freightLetterStampCost);
    }
    if (values.productAmount !== undefined && typeof values.productAmount !== 'number') {
      values.productAmount = Number(values.productAmount);
    }
    if (values.receiptAmount !== undefined && typeof values.receiptAmount !== 'number') {
      values.receiptAmount = Number(values.receiptAmount);
    }
    if (values.row !== undefined && typeof values.row !== 'number') {
      values.row = Number(values.row);
    }
    if (values.netWeight !== undefined && typeof values.netWeight !== 'number') {
      values.netWeight = Number(values.netWeight);
    }
    if (values.grossWeight !== undefined && typeof values.grossWeight !== 'number') {
      values.grossWeight = Number(values.grossWeight);
    }
    if (values.totalNetWeight !== undefined && typeof values.totalNetWeight !== 'number') {
      values.totalNetWeight = Number(values.totalNetWeight);
    }
    if (values.totalGrossWeight !== undefined && typeof values.totalGrossWeight !== 'number') {
      values.totalGrossWeight = Number(values.totalGrossWeight);
    }
    if (values.fobAmount !== undefined && typeof values.fobAmount !== 'number') {
      values.fobAmount = Number(values.fobAmount);
    }
    if (values.shippingFare !== undefined && typeof values.shippingFare !== 'number') {
      values.shippingFare = Number(values.shippingFare);
    }
    if (values.inspectionCost !== undefined && typeof values.inspectionCost !== 'number') {
      values.inspectionCost = Number(values.inspectionCost);
    }
    if (values.discount !== undefined && typeof values.discount !== 'number') {
      values.discount = Number(values.discount);
    }
    if (values.otherCost !== undefined && typeof values.otherCost !== 'number') {
      values.otherCost = Number(values.otherCost);
    }

    const entity = {
      ...draftReceiptEntity,
      ...values,
      receipts: drafts.find(it => it.id.toString() === values.receipts?.toString()),
      draftCustomJustifications: mapIdList(values.draftCustomJustifications),
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
          ...draftReceiptEntity,
          receipts: draftReceiptEntity?.receipts?.id,
          draftCustomJustifications: draftReceiptEntity?.draftCustomJustifications?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftReceipt.home.createOrEditLabel" data-cy="DraftReceiptCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftReceipt.home.createOrEditLabel">Create or edit a DraftReceipt</Translate>
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
                  id="draft-receipt-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.comment')}
                id="draft-receipt-comment"
                name="comment"
                data-cy="comment"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.customerDeliverDate')}
                id="draft-receipt-customerDeliverDate"
                name="customerDeliverDate"
                data-cy="customerDeliverDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.date')}
                id="draft-receipt-date"
                name="date"
                data-cy="date"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.deleteDate')}
                id="draft-receipt-deleteDate"
                name="deleteDate"
                data-cy="deleteDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.deliverDate')}
                id="draft-receipt-deliverDate"
                name="deliverDate"
                data-cy="deliverDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.deliverDuration')}
                id="draft-receipt-deliverDuration"
                name="deliverDuration"
                data-cy="deliverDuration"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.delivered')}
                id="draft-receipt-delivered"
                name="delivered"
                data-cy="delivered"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.documentTransactionEffectiveDate')}
                id="draft-receipt-documentTransactionEffectiveDate"
                name="documentTransactionEffectiveDate"
                data-cy="documentTransactionEffectiveDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.freightLetterDate')}
                id="draft-receipt-freightLetterDate"
                name="freightLetterDate"
                data-cy="freightLetterDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.freightLetterNumber')}
                id="draft-receipt-freightLetterNumber"
                name="freightLetterNumber"
                data-cy="freightLetterNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.freightLetterStampCost')}
                id="draft-receipt-freightLetterStampCost"
                name="freightLetterStampCost"
                data-cy="freightLetterStampCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.issueDate')}
                id="draft-receipt-issueDate"
                name="issueDate"
                data-cy="issueDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.issueDocument')}
                id="draft-receipt-issueDocument"
                name="issueDocument"
                data-cy="issueDocument"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.productAmount')}
                id="draft-receipt-productAmount"
                name="productAmount"
                data-cy="productAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.receiptAmount')}
                id="draft-receipt-receiptAmount"
                name="receiptAmount"
                data-cy="receiptAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.receiptDate')}
                id="draft-receipt-receiptDate"
                name="receiptDate"
                data-cy="receiptDate"
                type="text"
              />
              <ValidatedField label={translate('tfbitaApp.draftReceipt.row')} id="draft-receipt-row" name="row" data-cy="row" type="text" />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.serial')}
                id="draft-receipt-serial"
                name="serial"
                data-cy="serial"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.transportRow')}
                id="draft-receipt-transportRow"
                name="transportRow"
                data-cy="transportRow"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.usable')}
                id="draft-receipt-usable"
                name="usable"
                data-cy="usable"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.paymentCurrencyRateTypeDesc')}
                id="draft-receipt-paymentCurrencyRateTypeDesc"
                name="paymentCurrencyRateTypeDesc"
                data-cy="paymentCurrencyRateTypeDesc"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.paymentItemTypeDesc')}
                id="draft-receipt-paymentItemTypeDesc"
                name="paymentItemTypeDesc"
                data-cy="paymentItemTypeDesc"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.netWeight')}
                id="draft-receipt-netWeight"
                name="netWeight"
                data-cy="netWeight"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.grossWeight')}
                id="draft-receipt-grossWeight"
                name="grossWeight"
                data-cy="grossWeight"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.totalNetWeight')}
                id="draft-receipt-totalNetWeight"
                name="totalNetWeight"
                data-cy="totalNetWeight"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.totalGrossWeight')}
                id="draft-receipt-totalGrossWeight"
                name="totalGrossWeight"
                data-cy="totalGrossWeight"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.letterNumberTazirat')}
                id="draft-receipt-letterNumberTazirat"
                name="letterNumberTazirat"
                data-cy="letterNumberTazirat"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.letterDateTazirat')}
                id="draft-receipt-letterDateTazirat"
                name="letterDateTazirat"
                data-cy="letterDateTazirat"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.fobAmount')}
                id="draft-receipt-fobAmount"
                name="fobAmount"
                data-cy="fobAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.shippingFare')}
                id="draft-receipt-shippingFare"
                name="shippingFare"
                data-cy="shippingFare"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.inspectionCost')}
                id="draft-receipt-inspectionCost"
                name="inspectionCost"
                data-cy="inspectionCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.discount')}
                id="draft-receipt-discount"
                name="discount"
                data-cy="discount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.deadlineSubmitDocumentDate')}
                id="draft-receipt-deadlineSubmitDocumentDate"
                name="deadlineSubmitDocumentDate"
                data-cy="deadlineSubmitDocumentDate"
                type="text"
              />
              <ValidatedBlobField
                label={translate('tfbitaApp.draftReceipt.currencyProvisionFile')}
                id="draft-receipt-currencyProvisionFile"
                name="currencyProvisionFile"
                data-cy="currencyProvisionFile"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.isMigrational')}
                id="draft-receipt-isMigrational"
                name="isMigrational"
                data-cy="isMigrational"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.otherCost')}
                id="draft-receipt-otherCost"
                name="otherCost"
                data-cy="otherCost"
                type="text"
              />
              <ValidatedField
                id="draft-receipt-receipts"
                name="receipts"
                data-cy="receipts"
                label={translate('tfbitaApp.draftReceipt.receipts')}
                type="select"
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
              <ValidatedField
                label={translate('tfbitaApp.draftReceipt.draftCustomJustification')}
                id="draft-receipt-draftCustomJustification"
                data-cy="draftCustomJustification"
                type="select"
                multiple
                name="draftCustomJustifications"
              >
                <option value="" key="0" />
                {draftCustomJustifications
                  ? draftCustomJustifications.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-receipt" replace color="info">
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

export default DraftReceiptUpdate;
