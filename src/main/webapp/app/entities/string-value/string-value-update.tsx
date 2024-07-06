import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IOrderRegistrationInfo } from 'app/shared/model/order-registration-info.model';
import { getEntities as getOrderRegistrationInfos } from 'app/entities/order-registration-info/order-registration-info.reducer';
import { IDraft } from 'app/shared/model/draft.model';
import { getEntities as getDrafts } from 'app/entities/draft/draft.reducer';
import { IDraftType } from 'app/shared/model/draft-type.model';
import { getEntities as getDraftTypes } from 'app/entities/draft-type/draft-type.reducer';
import { IStringValue } from 'app/shared/model/string-value.model';
import { getEntity, updateEntity, createEntity, reset } from './string-value.reducer';

export const StringValueUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const orderRegistrationInfos = useAppSelector(state => state.orderRegistrationInfo.entities);
  const drafts = useAppSelector(state => state.draft.entities);
  const draftTypes = useAppSelector(state => state.draftType.entities);
  const stringValueEntity = useAppSelector(state => state.stringValue.entity);
  const loading = useAppSelector(state => state.stringValue.loading);
  const updating = useAppSelector(state => state.stringValue.updating);
  const updateSuccess = useAppSelector(state => state.stringValue.updateSuccess);

  const handleClose = () => {
    navigate('/string-value');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getOrderRegistrationInfos({}));
    dispatch(getDrafts({}));
    dispatch(getDraftTypes({}));
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
      ...stringValueEntity,
      ...values,
      orderRegistrationInfos: mapIdList(values.orderRegistrationInfos),
      drafts: mapIdList(values.drafts),
      draftTypes: mapIdList(values.draftTypes),
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
          ...stringValueEntity,
          orderRegistrationInfos: stringValueEntity?.orderRegistrationInfos?.map(e => e.id.toString()),
          drafts: stringValueEntity?.drafts?.map(e => e.id.toString()),
          draftTypes: stringValueEntity?.draftTypes?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.stringValue.home.createOrEditLabel" data-cy="StringValueCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.stringValue.home.createOrEditLabel">Create or edit a StringValue</Translate>
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
                  id="string-value-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField label={translate('tfbitaApp.stringValue.val')} id="string-value-val" name="val" data-cy="val" type="text" />
              <ValidatedField
                label={translate('tfbitaApp.stringValue.orderRegistrationInfo')}
                id="string-value-orderRegistrationInfo"
                data-cy="orderRegistrationInfo"
                type="select"
                multiple
                name="orderRegistrationInfos"
              >
                <option value="" key="0" />
                {orderRegistrationInfos
                  ? orderRegistrationInfos.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                label={translate('tfbitaApp.stringValue.draft')}
                id="string-value-draft"
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
              <ValidatedField
                label={translate('tfbitaApp.stringValue.draftType')}
                id="string-value-draftType"
                data-cy="draftType"
                type="select"
                multiple
                name="draftTypes"
              >
                <option value="" key="0" />
                {draftTypes
                  ? draftTypes.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/string-value" replace color="info">
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

export default StringValueUpdate;
