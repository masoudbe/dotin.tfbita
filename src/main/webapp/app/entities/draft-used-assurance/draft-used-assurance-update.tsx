import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDraft } from 'app/shared/model/draft.model';
import { getEntities as getDrafts } from 'app/entities/draft/draft.reducer';
import { IDraftUsedAssurance } from 'app/shared/model/draft-used-assurance.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-used-assurance.reducer';

export const DraftUsedAssuranceUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const drafts = useAppSelector(state => state.draft.entities);
  const draftUsedAssuranceEntity = useAppSelector(state => state.draftUsedAssurance.entity);
  const loading = useAppSelector(state => state.draftUsedAssurance.loading);
  const updating = useAppSelector(state => state.draftUsedAssurance.updating);
  const updateSuccess = useAppSelector(state => state.draftUsedAssurance.updateSuccess);

  const handleClose = () => {
    navigate('/draft-used-assurance');
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
    if (values.exchangeRate !== undefined && typeof values.exchangeRate !== 'number') {
      values.exchangeRate = Number(values.exchangeRate);
    }
    if (values.defaultCurrencyUsedCost !== undefined && typeof values.defaultCurrencyUsedCost !== 'number') {
      values.defaultCurrencyUsedCost = Number(values.defaultCurrencyUsedCost);
    }
    if (values.usedCost !== undefined && typeof values.usedCost !== 'number') {
      values.usedCost = Number(values.usedCost);
    }

    const entity = {
      ...draftUsedAssuranceEntity,
      ...values,
      draft: drafts.find(it => it.id.toString() === values.draft?.toString()),
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
          ...draftUsedAssuranceEntity,
          draft: draftUsedAssuranceEntity?.draft?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftUsedAssurance.home.createOrEditLabel" data-cy="DraftUsedAssuranceCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftUsedAssurance.home.createOrEditLabel">Create or edit a DraftUsedAssurance</Translate>
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
                  id="draft-used-assurance-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftUsedAssurance.assuranceRateId')}
                id="draft-used-assurance-assuranceRateId"
                name="assuranceRateId"
                data-cy="assuranceRateId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftUsedAssurance.assuranceSerial')}
                id="draft-used-assurance-assuranceSerial"
                name="assuranceSerial"
                data-cy="assuranceSerial"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftUsedAssurance.exchangeRate')}
                id="draft-used-assurance-exchangeRate"
                name="exchangeRate"
                data-cy="exchangeRate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftUsedAssurance.defaultCurrencyUsedCost')}
                id="draft-used-assurance-defaultCurrencyUsedCost"
                name="defaultCurrencyUsedCost"
                data-cy="defaultCurrencyUsedCost"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftUsedAssurance.usedCost')}
                id="draft-used-assurance-usedCost"
                name="usedCost"
                data-cy="usedCost"
                type="text"
              />
              <ValidatedField
                id="draft-used-assurance-draft"
                name="draft"
                data-cy="draft"
                label={translate('tfbitaApp.draftUsedAssurance.draft')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-used-assurance" replace color="info">
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

export default DraftUsedAssuranceUpdate;
