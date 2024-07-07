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
import { ISuggestedSanctionInfo } from 'app/shared/model/suggested-sanction-info.model';
import { getEntity, updateEntity, createEntity, reset } from './suggested-sanction-info.reducer';

export const SuggestedSanctionInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const drafts = useAppSelector(state => state.draft.entities);
  const suggestedSanctionInfoEntity = useAppSelector(state => state.suggestedSanctionInfo.entity);
  const loading = useAppSelector(state => state.suggestedSanctionInfo.loading);
  const updating = useAppSelector(state => state.suggestedSanctionInfo.updating);
  const updateSuccess = useAppSelector(state => state.suggestedSanctionInfo.updateSuccess);

  const handleClose = () => {
    navigate('/suggested-sanction-info');
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

    const entity = {
      ...suggestedSanctionInfoEntity,
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
          ...suggestedSanctionInfoEntity,
          drafts: suggestedSanctionInfoEntity?.drafts?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.suggestedSanctionInfo.home.createOrEditLabel" data-cy="SuggestedSanctionInfoCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.suggestedSanctionInfo.home.createOrEditLabel">
              Create or edit a SuggestedSanctionInfo
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
                  id="suggested-sanction-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.suggestedSanctionInfo.sanctionSerial')}
                id="suggested-sanction-info-sanctionSerial"
                name="sanctionSerial"
                data-cy="sanctionSerial"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.suggestedSanctionInfo.personnelCode')}
                id="suggested-sanction-info-personnelCode"
                name="personnelCode"
                data-cy="personnelCode"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.suggestedSanctionInfo.draft')}
                id="suggested-sanction-info-draft"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/suggested-sanction-info" replace color="info">
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

export default SuggestedSanctionInfoUpdate;
