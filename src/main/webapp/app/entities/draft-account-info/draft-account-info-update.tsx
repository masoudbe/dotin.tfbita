import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDraftAccountInfo } from 'app/shared/model/draft-account-info.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-account-info.reducer';

export const DraftAccountInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const draftAccountInfoEntity = useAppSelector(state => state.draftAccountInfo.entity);
  const loading = useAppSelector(state => state.draftAccountInfo.loading);
  const updating = useAppSelector(state => state.draftAccountInfo.updating);
  const updateSuccess = useAppSelector(state => state.draftAccountInfo.updateSuccess);

  const handleClose = () => {
    navigate('/draft-account-info');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
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
    if (values.documentReceiptDisciplinaryAccountId !== undefined && typeof values.documentReceiptDisciplinaryAccountId !== 'number') {
      values.documentReceiptDisciplinaryAccountId = Number(values.documentReceiptDisciplinaryAccountId);
    }
    if (values.draftMainAccountId !== undefined && typeof values.draftMainAccountId !== 'number') {
      values.draftMainAccountId = Number(values.draftMainAccountId);
    }
    if (values.insuranceCostAccountId !== undefined && typeof values.insuranceCostAccountId !== 'number') {
      values.insuranceCostAccountId = Number(values.insuranceCostAccountId);
    }
    if (values.justificationDisciplinaryAccountId !== undefined && typeof values.justificationDisciplinaryAccountId !== 'number') {
      values.justificationDisciplinaryAccountId = Number(values.justificationDisciplinaryAccountId);
    }
    if (values.openDraftDisciplinaryAccountId !== undefined && typeof values.openDraftDisciplinaryAccountId !== 'number') {
      values.openDraftDisciplinaryAccountId = Number(values.openDraftDisciplinaryAccountId);
    }
    if (values.otherCostsAccountId !== undefined && typeof values.otherCostsAccountId !== 'number') {
      values.otherCostsAccountId = Number(values.otherCostsAccountId);
    }
    if (values.postSwiftCostsAccountId !== undefined && typeof values.postSwiftCostsAccountId !== 'number') {
      values.postSwiftCostsAccountId = Number(values.postSwiftCostsAccountId);
    }
    if (values.amountDeductionAccountId !== undefined && typeof values.amountDeductionAccountId !== 'number') {
      values.amountDeductionAccountId = Number(values.amountDeductionAccountId);
    }

    const entity = {
      ...draftAccountInfoEntity,
      ...values,
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
          ...draftAccountInfoEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftAccountInfo.home.createOrEditLabel" data-cy="DraftAccountInfoCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftAccountInfo.home.createOrEditLabel">Create or edit a DraftAccountInfo</Translate>
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
                  id="draft-account-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftAccountInfo.documentReceiptDisciplinaryAccountId')}
                id="draft-account-info-documentReceiptDisciplinaryAccountId"
                name="documentReceiptDisciplinaryAccountId"
                data-cy="documentReceiptDisciplinaryAccountId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftAccountInfo.draftMainAccountId')}
                id="draft-account-info-draftMainAccountId"
                name="draftMainAccountId"
                data-cy="draftMainAccountId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftAccountInfo.insuranceCostAccountId')}
                id="draft-account-info-insuranceCostAccountId"
                name="insuranceCostAccountId"
                data-cy="insuranceCostAccountId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftAccountInfo.justificationDisciplinaryAccountId')}
                id="draft-account-info-justificationDisciplinaryAccountId"
                name="justificationDisciplinaryAccountId"
                data-cy="justificationDisciplinaryAccountId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftAccountInfo.openDraftDisciplinaryAccountId')}
                id="draft-account-info-openDraftDisciplinaryAccountId"
                name="openDraftDisciplinaryAccountId"
                data-cy="openDraftDisciplinaryAccountId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftAccountInfo.otherCostsAccountId')}
                id="draft-account-info-otherCostsAccountId"
                name="otherCostsAccountId"
                data-cy="otherCostsAccountId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftAccountInfo.postSwiftCostsAccountId')}
                id="draft-account-info-postSwiftCostsAccountId"
                name="postSwiftCostsAccountId"
                data-cy="postSwiftCostsAccountId"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftAccountInfo.amountDeductionAccountId')}
                id="draft-account-info-amountDeductionAccountId"
                name="amountDeductionAccountId"
                data-cy="amountDeductionAccountId"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-account-info" replace color="info">
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

export default DraftAccountInfoUpdate;
