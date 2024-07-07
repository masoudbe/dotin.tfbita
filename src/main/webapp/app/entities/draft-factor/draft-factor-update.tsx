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
import { IDraftFactor } from 'app/shared/model/draft-factor.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-factor.reducer';

export const DraftFactorUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const drafts = useAppSelector(state => state.draft.entities);
  const draftFactorEntity = useAppSelector(state => state.draftFactor.entity);
  const loading = useAppSelector(state => state.draftFactor.loading);
  const updating = useAppSelector(state => state.draftFactor.updating);
  const updateSuccess = useAppSelector(state => state.draftFactor.updateSuccess);

  const handleClose = () => {
    navigate('/draft-factor');
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
    if (values.amount !== undefined && typeof values.amount !== 'number') {
      values.amount = Number(values.amount);
    }
    if (values.eqAmount !== undefined && typeof values.eqAmount !== 'number') {
      values.eqAmount = Number(values.eqAmount);
    }

    const entity = {
      ...draftFactorEntity,
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
          ...draftFactorEntity,
          draft: draftFactorEntity?.draft?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftFactor.home.createOrEditLabel" data-cy="DraftFactorCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftFactor.home.createOrEditLabel">Create or edit a DraftFactor</Translate>
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
                  id="draft-factor-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftFactor.amount')}
                id="draft-factor-amount"
                name="amount"
                data-cy="amount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftFactor.description')}
                id="draft-factor-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftFactor.eqAmount')}
                id="draft-factor-eqAmount"
                name="eqAmount"
                data-cy="eqAmount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftFactor.factorDate')}
                id="draft-factor-factorDate"
                name="factorDate"
                data-cy="factorDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftFactor.issueDate')}
                id="draft-factor-issueDate"
                name="issueDate"
                data-cy="issueDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftFactor.serial')}
                id="draft-factor-serial"
                name="serial"
                data-cy="serial"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftFactor.currencyCode')}
                id="draft-factor-currencyCode"
                name="currencyCode"
                data-cy="currencyCode"
                type="text"
              />
              <ValidatedField
                id="draft-factor-draft"
                name="draft"
                data-cy="draft"
                label={translate('tfbitaApp.draftFactor.draft')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-factor" replace color="info">
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

export default DraftFactorUpdate;
