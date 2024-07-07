import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDraftTypeAccountInfo } from 'app/shared/model/draft-type-account-info.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-type-account-info.reducer';

export const DraftTypeAccountInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const draftTypeAccountInfoEntity = useAppSelector(state => state.draftTypeAccountInfo.entity);
  const loading = useAppSelector(state => state.draftTypeAccountInfo.loading);
  const updating = useAppSelector(state => state.draftTypeAccountInfo.updating);
  const updateSuccess = useAppSelector(state => state.draftTypeAccountInfo.updateSuccess);

  const handleClose = () => {
    navigate('/draft-type-account-info');
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

    const entity = {
      ...draftTypeAccountInfoEntity,
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
          ...draftTypeAccountInfoEntity,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftTypeAccountInfo.home.createOrEditLabel" data-cy="DraftTypeAccountInfoCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftTypeAccountInfo.home.createOrEditLabel">Create or edit a DraftTypeAccountInfo</Translate>
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
                  id="draft-type-account-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftTypeAccountInfo.sellCurrencyCommissionAccount')}
                id="draft-type-account-info-sellCurrencyCommissionAccount"
                name="sellCurrencyCommissionAccount"
                data-cy="sellCurrencyCommissionAccount"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftTypeAccountInfo.incomeAccountNumber')}
                id="draft-type-account-info-incomeAccountNumber"
                name="incomeAccountNumber"
                data-cy="incomeAccountNumber"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-type-account-info" replace color="info">
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

export default DraftTypeAccountInfoUpdate;
