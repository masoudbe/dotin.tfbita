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
import { IDraftType } from 'app/shared/model/draft-type.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-type.reducer';

export const DraftTypeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const drafts = useAppSelector(state => state.draft.entities);
  const draftTypeEntity = useAppSelector(state => state.draftType.entity);
  const loading = useAppSelector(state => state.draftType.loading);
  const updating = useAppSelector(state => state.draftType.updating);
  const updateSuccess = useAppSelector(state => state.draftType.updateSuccess);

  const handleClose = () => {
    navigate('/draft-type');
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
    if (values.alarmTime !== undefined && typeof values.alarmTime !== 'number') {
      values.alarmTime = Number(values.alarmTime);
    }
    if (values.duration !== undefined && typeof values.duration !== 'number') {
      values.duration = Number(values.duration);
    }
    if (values.latestCreditSerial !== undefined && typeof values.latestCreditSerial !== 'number') {
      values.latestCreditSerial = Number(values.latestCreditSerial);
    }

    const entity = {
      ...draftTypeEntity,
      ...values,
      draftType: drafts.find(it => it.id.toString() === values.draftType?.toString()),
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
          ...draftTypeEntity,
          draftType: draftTypeEntity?.draftType?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftType.home.createOrEditLabel" data-cy="DraftTypeCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftType.home.createOrEditLabel">Create or edit a DraftType</Translate>
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
                  id="draft-type-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftType.alarmTime')}
                id="draft-type-alarmTime"
                name="alarmTime"
                data-cy="alarmTime"
                type="text"
              />
              <ValidatedField label={translate('tfbitaApp.draftType.code')} id="draft-type-code" name="code" data-cy="code" type="text" />
              <ValidatedField
                label={translate('tfbitaApp.draftType.disableDate')}
                id="draft-type-disableDate"
                name="disableDate"
                data-cy="disableDate"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.duration')}
                id="draft-type-duration"
                name="duration"
                data-cy="duration"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.hasAssurance')}
                id="draft-type-hasAssurance"
                name="hasAssurance"
                data-cy="hasAssurance"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.hasSanction')}
                id="draft-type-hasSanction"
                name="hasSanction"
                data-cy="hasSanction"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.latestCreditSerial')}
                id="draft-type-latestCreditSerial"
                name="latestCreditSerial"
                data-cy="latestCreditSerial"
                type="text"
              />
              <ValidatedField label={translate('tfbitaApp.draftType.name')} id="draft-type-name" name="name" data-cy="name" type="text" />
              <ValidatedField
                label={translate('tfbitaApp.draftType.portal')}
                id="draft-type-portal"
                name="portal"
                data-cy="portal"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.usable')}
                id="draft-type-usable"
                name="usable"
                data-cy="usable"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.defaultCurrencyCode')}
                id="draft-type-defaultCurrencyCode"
                name="defaultCurrencyCode"
                data-cy="defaultCurrencyCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.accountInfoCode')}
                id="draft-type-accountInfoCode"
                name="accountInfoCode"
                data-cy="accountInfoCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftType.topicInfoCode')}
                id="draft-type-topicInfoCode"
                name="topicInfoCode"
                data-cy="topicInfoCode"
                type="text"
              />
              <ValidatedField
                id="draft-type-draftType"
                name="draftType"
                data-cy="draftType"
                label={translate('tfbitaApp.draftType.draftType')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-type" replace color="info">
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

export default DraftTypeUpdate;
