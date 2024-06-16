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
import { IDraftExtend } from 'app/shared/model/draft-extend.model';
import { getEntity, updateEntity, createEntity, reset } from './draft-extend.reducer';

export const DraftExtendUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const drafts = useAppSelector(state => state.draft.entities);
  const draftExtendEntity = useAppSelector(state => state.draftExtend.entity);
  const loading = useAppSelector(state => state.draftExtend.loading);
  const updating = useAppSelector(state => state.draftExtend.updating);
  const updateSuccess = useAppSelector(state => state.draftExtend.updateSuccess);

  const handleClose = () => {
    navigate('/draft-extend');
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
    if (values.duration !== undefined && typeof values.duration !== 'number') {
      values.duration = Number(values.duration);
    }

    const entity = {
      ...draftExtendEntity,
      ...values,
      extensions: drafts.find(it => it.id.toString() === values.extensions?.toString()),
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
          ...draftExtendEntity,
          extensions: draftExtendEntity?.extensions?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="tfbitaApp.draftExtend.home.createOrEditLabel" data-cy="DraftExtendCreateUpdateHeading">
            <Translate contentKey="tfbitaApp.draftExtend.home.createOrEditLabel">Create or edit a DraftExtend</Translate>
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
                  id="draft-extend-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('tfbitaApp.draftExtend.date')}
                id="draft-extend-date"
                name="date"
                data-cy="date"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftExtend.duration')}
                id="draft-extend-duration"
                name="duration"
                data-cy="duration"
                type="text"
              />
              <ValidatedField
                label={translate('tfbitaApp.draftExtend.time')}
                id="draft-extend-time"
                name="time"
                data-cy="time"
                type="text"
              />
              <ValidatedField
                id="draft-extend-extensions"
                name="extensions"
                data-cy="extensions"
                label={translate('tfbitaApp.draftExtend.extensions')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/draft-extend" replace color="info">
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

export default DraftExtendUpdate;
