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
import { ICustom } from 'app/shared/model/custom.model';
import { getEntity, updateEntity, createEntity, reset } from './custom.reducer';

export const CustomUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const drafts = useAppSelector(state => state.draft.entities);
  const customEntity = useAppSelector(state => state.custom.entity);
  const loading = useAppSelector(state => state.custom.loading);
  const updating = useAppSelector(state => state.custom.updating);
  const updateSuccess = useAppSelector(state => state.custom.updateSuccess);

  const handleClose = () => {
    navigate('/custom');
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
    if (values.tempId !== undefined && typeof values.tempId !== 'number') {
      values.tempId = Number(values.tempId);
    }

    const entity = {
      ...customEntity,
      ...values,
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
          ...customEntity,
          drafts: customEntity?.drafts?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.custom.home.createOrEditLabel" data-cy="CustomCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.custom.home.createOrEditLabel">Create or edit a Custom</Translate>
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
                  id="custom-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.custom.modificationDate')}
                id="custom-modificationDate"
                name="modificationDate"
                data-cy="modificationDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.custom.latinName')}
                id="custom-latinName"
                name="latinName"
                data-cy="latinName"
                type="text"
              />
              <ValidatedField label={translate('tfbitaApp.custom.name')} id="custom-name" name="name" data-cy="name" type="text" />
              <ValidatedField label={translate('tfbitaApp.custom.tempId')} id="custom-tempId" name="tempId" data-cy="tempId" type="text" />
              <ValidatedField
                label={translate('tfbitaApp.custom.draft')}
                id="custom-draft"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/custom" replace color="info">
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

export default CustomUpdate;
