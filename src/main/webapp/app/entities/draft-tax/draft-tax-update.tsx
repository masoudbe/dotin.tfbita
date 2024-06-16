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
import { IDraftTax } from 'app/shared/model/draft-tax.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-tax.reducer';

export const DraftTaxUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const drafts = useAppSelector(state => state.draft.entities);
  const draftTaxEntity = useAppSelector(state => state.draftTax.entity);
  const loading = useAppSelector(state => state.draftTax.loading);
  const updating = useAppSelector(state => state.draftTax.updating);
  const updateSuccess = useAppSelector(state => state.draftTax.updateSuccess);

  const handleClose = () => {
    navigate('/draft-tax');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

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
    if (values.orderRegCurrencyAmount !== undefined && typeof values.orderRegCurrencyAmount !== 'number') {
      values.orderRegCurrencyAmount = Number(values.orderRegCurrencyAmount);
    }
    if (values.mainAccountCurrencyAmount !== undefined && typeof values.mainAccountCurrencyAmount !== 'number') {
      values.mainAccountCurrencyAmount = Number(values.mainAccountCurrencyAmount);
    }
    if (values.orderRegRate !== undefined && typeof values.orderRegRate !== 'number') {
      values.orderRegRate = Number(values.orderRegRate);
    }
    if (values.mainAccountRate !== undefined && typeof values.mainAccountRate !== 'number') {
      values.mainAccountRate = Number(values.mainAccountRate);
    }

    const entity = {
      ...draftTaxEntity,
      ...values,
      taxes: drafts.find(it => it.id.toString() === values.taxes?.toString()),
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
          ...draftTaxEntity,
          taxes: draftTaxEntity?.taxes?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftTax.home.createOrEditLabel" data-cy="DraftTaxCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftTax.home.createOrEditLabel">Create or edit a DraftTax</Translate>
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
                  id="draft-tax-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftTax.orderRegCurrencyAmount')}
                id="draft-tax-orderRegCurrencyAmount"
                name="orderRegCurrencyAmount"
                data-cy="orderRegCurrencyAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTax.mainAccountCurrencyAmount')}
                id="draft-tax-mainAccountCurrencyAmount"
                name="mainAccountCurrencyAmount"
                data-cy="mainAccountCurrencyAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTax.letterNumber')}
                id="draft-tax-letterNumber"
                name="letterNumber"
                data-cy="letterNumber"
                type="text"
              />
              <ValidatedBlobField
                label={translate('tfbitaApp.draftTax.letterImage')}
                id="draft-tax-letterImage"
                name="letterImage"
                data-cy="letterImage"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTax.description')}
                id="draft-tax-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTax.registrationDate')}
                id="draft-tax-registrationDate"
                name="registrationDate"
                data-cy="registrationDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTax.returnTaxesAmount')}
                id="draft-tax-returnTaxesAmount"
                name="returnTaxesAmount"
                data-cy="returnTaxesAmount"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTax.orderRegRate')}
                id="draft-tax-orderRegRate"
                name="orderRegRate"
                data-cy="orderRegRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTax.mainAccountRate')}
                id="draft-tax-mainAccountRate"
                name="mainAccountRate"
                data-cy="mainAccountRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTax.documentTransactionNumber')}
                id="draft-tax-documentTransactionNumber"
                name="documentTransactionNumber"
                data-cy="documentTransactionNumber"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTax.returnDocumentTransactionNumber')}
                id="draft-tax-returnDocumentTransactionNumber"
                name="returnDocumentTransactionNumber"
                data-cy="returnDocumentTransactionNumber"
                type="text"
              />
              <ValidatedField id="draft-tax-taxes" name="taxes" data-cy="taxes" label={translate('tfbitaApp.draftTax.taxes')} type="select">
                <option value="" key="0" />
                {drafts
                  ? drafts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-tax" replace color="info">
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

export default DraftTaxUpdate;
